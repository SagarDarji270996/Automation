package projects.taskeye.configurations.base;

import projects.taskeye.configurations.config.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {
    private static final Logger LOGGER = Logger.getLogger(TestBase.class.getName());
    private static AppiumDriverLocalService service;

    @Getter
    protected WebDriver driver;

    @Getter
    protected AppiumDriver<MobileElement> mobileDriver;

    private final ConfigLoader configLoader;

    public TestBase() {
        this.configLoader = new ConfigLoader();
    }

    public void setUpMobile() throws Exception {
        setUpMobileDriver();
    }

    public void setUpWeb() {
        setUpWebDriver();
    }

    private boolean isHeadlessMode() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    private void setUpWebDriver() {
        LOGGER.info("Setting up WebDriver...");

        try {
            Runtime.getRuntime().exec("pkill -f chrome || true");
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            LOGGER.warning("Error while terminating Chrome processes: " + e.getMessage());
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        if (System.getenv("JENKINS_HOME") != null || isHeadlessMode()) {
            options.addArguments("--headless=new");
            LOGGER.info("Running in Headless mode");
        } else {
            options.addArguments("--start-maximized");
            LOGGER.info("Running in normal mode");
        }

        options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920x1080");
        if (new File("/usr/bin/google-chrome").exists()) {
            options.setBinary("/usr/bin/google-chrome");
        }

        Map<String, Object> loggingPrefs = new HashMap<>();
        loggingPrefs.put("driver", Level.ALL);
        loggingPrefs.put("browser", Level.ALL);
        options.setCapability("goog:loggingPrefs", loggingPrefs);

        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        LOGGER.info("WebDriver setup complete.");
    }

    private void setUpMobileDriver() throws Exception {
        String platformName = System.getProperty("platform", "android");
        String platformVersion = configLoader.getProperty("platformVersion");
        String deviceName = configLoader.getProperty("deviceName");

        if (platformVersion == null || deviceName == null) {
            throw new IllegalStateException("Mobile platform configuration is incomplete.");
        }

        URL url = new URL("http://127.0.0.1:4723/");
        DesiredCapabilities caps = new DesiredCapabilities();

        switch (platformName.toLowerCase()) {
            case "android":
                caps.setCapability("platformName", "Android");
                caps.setCapability("platformVersion", platformVersion);
                caps.setCapability("deviceName", deviceName);
                caps.setCapability("automationName", "uiautomator2");
                caps.setCapability("appPackage", "com.uffizio.taskeye");
                caps.setCapability("appActivity", "ui.activity.startup.SplashScreenActivity");
                caps.setCapability("app", configLoader.getProperty("appApkFilePath"));
                break;
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }

        startAppiumServer();
        this.mobileDriver = new AppiumDriver<>(url, caps);
        this.mobileDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        LOGGER.info("Mobile driver initialized successfully.");
    }

    private void startAppiumServer() {
        if (!isServerRunning(4723)) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
            LOGGER.info("Appium server started.");
        } else {
            LOGGER.info("Appium server already running.");
        }
    }

    private boolean isServerRunning(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    public AppiumDriver<MobileElement> getMobileDriver() {
        return this.mobileDriver;
    }

    public String getWebsiteUrl() {
        return configLoader.getProperty("website.url");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            LOGGER.info("WebDriver terminated.");
        }
        if (mobileDriver != null) {
            mobileDriver.quit();
            LOGGER.info("Mobile driver terminated.");
        }
        if (service != null) {
            service.stop();
            LOGGER.info("Appium service stopped.");
        }
    }
}
