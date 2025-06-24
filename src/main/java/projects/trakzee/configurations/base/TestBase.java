package projects.trakzee.configurations.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.github.bonigarcia.wdm.WebDriverManager;
import projects.trakzee.configurations.config.ConfigLoader;
import projects.trakzee.web.projectUtility.StartChromeWithRemoteDebugging;

public class TestBase implements InitializePages {

	String defualtDownloadDirectory = null;
	Robot robot;
	private static AppiumDriverLocalService service;
	private static WebDriver driver;

	protected static AppiumDriver<MobileElement> mobileDriver;
	private final ConfigLoader configLoader;
	private final String userType;

	public static WebDriver getWebDriver() {
		return DriverManager.getDriver();
	}

	private static Thread thread;
	private static boolean isThreadCreated = false;

	public synchronized void createThread(Class<?> className) {
		try {
			Runnable instance = (Runnable) className.getDeclaredConstructor().newInstance();
			thread = new Thread(instance);
			isThreadCreated = true;
			thread.setDaemon(false);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public synchronized void createThread(Class<?> className, SoftAssert softAssert) {
		//Use this when you want to share the assertion with the child thread
		try {
			Runnable instance;

			// Check if the class has a constructor that accepts SoftAssert
			try {
				instance = (Runnable) className.getDeclaredConstructor(SoftAssert.class).newInstance(softAssert);
			} catch (NoSuchMethodException e) {
				// Fallback to default constructor if SoftAssert is not supported
				instance = (Runnable) className.getDeclaredConstructor().newInstance();
			}

			thread = new Thread(instance);
			isThreadCreated = true;
			thread.setDaemon(false);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean joinCreatedThread() {
		try {
			System.out.println("Is thread is alive: " + thread.isAlive());
			if (thread.isAlive()) {
				thread.join();
			}
			System.out.println("Created thread join successfully");
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}


	public static void setWebDriver(WebDriver driverInstance) {
		DriverManager.setDriver(driverInstance);
	}

	public TestBase() {
		driver = DriverManager.getDriver();
		configLoader = new ConfigLoader();
		userType = System.getProperty("userType", "admin");
	}

	public void setUpMobileAndWebDriver() throws Exception {
		setUpWebDriver();
		setUpMobileDriver();
	}

	private boolean isHeadlessMode() {
		String headlessArgument = System.getProperty("headless", "false"); // Default is "false"
		return headlessArgument.equalsIgnoreCase("true");
	}

	public void hardRefreshPage() throws InterruptedException {
		if (isHeadlessMode() == false) {
			((JavascriptExecutor) driver).executeScript("window.location.reload(true);");
			((JavascriptExecutor) driver).executeScript("window.location.reload(true);");
			Thread.sleep(2000);
			System.out.println("Page refreshed successfully...");
		}

	}

	private boolean isIncognitoMode() {
		String IsIncognito = System.getProperty("incognito", "true"); // Default is "false"
		return IsIncognito.equalsIgnoreCase("true");
	}


	@BeforeClass
	public void setUpWebDriver() {
		System.out.println("Setting up WebDriver...");
		System.out.println("Is debugger mode: " + Boolean.parseBoolean(System.getProperty("debuggerMode")));
		if (Boolean.parseBoolean(System.getProperty("debuggerMode", "false"))) {
			setUpWebDriverInDebuggerMode();
			return;
		}
		System.out.println("123");

		// Ensure the correct version of ChromeDriver is used
		WebDriverManager.chromedriver().driverVersion("latest").setup();

		ChromeOptions options = new ChromeOptions();

		//Ignore SSL Certificate Warnings in Selenium
		if (Boolean.parseBoolean(System.getProperty("wantToIgnoreWebSecurity", "true"))) {
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-web-security"); 
			options.addArguments("--allow-running-insecure-content");
		}
		//Check if headless mode is enabled via command-line argument or system property
		if (isHeadlessMode()) {
			options.addArguments("--headless"); // Run in headless mode
			System.out.println("Running in Headless mode");
		} else {
			options.addArguments("--start-maximized"); // Start in maximized window for non-headless
			System.out.println("Running in regular (non-headless) mode");
		}

		// Common arguments regardless of headless mode
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-notifications");
		if (isIncognitoMode()) {
			options.addArguments("incognito");
		}

		// Set the path to Chrome binary if it’s not in the default location
		if (isWindows()) {
			defualtDownloadDirectory = "C:\\Users\\uffizio\\Downloads";
			options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); // Path for Windows (Update as needed)
		} else if (isLinux()) {
			defualtDownloadDirectory = "/home/uffizio/Downloads";
			options.setBinary("/usr/bin/google-chrome"); // Path for Linux

		}


		// Correct logging preferences capability setup
		Map<String, Object> loggingPrefs = new HashMap<>();
		loggingPrefs.put("driver", Level.ALL); // Set logging level for driver
		loggingPrefs.put("browser", Level.ALL); // Set logging level for browser
		options.setCapability("goog:loggingPrefs", loggingPrefs);

		try {
			// Initialize WebDriver with the given Chrome options
			driver = new ChromeDriver(options);
			setWebDriver(driver);
			System.out.println("WebDriver initialized successfully.");

			// Set timeouts for WebDriver
			driver.manage().window().maximize(); // Maximize the window for visibility
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			// Clear all cookies
			driver.manage().deleteAllCookies();


			driver.get(configLoader.getProperty("website.url"));
			System.out.println("Login page launched successfully");

		} catch (Exception e) {
			// Capture any errors during initialization and print the stack trace
			System.err.println("Failed to initialize WebDriver: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

	private boolean isLinux() {
		return System.getProperty("os.name").toLowerCase().contains("nix")
				|| System.getProperty("os.name").toLowerCase().contains("nux");
	}

	public void setUpMobileDriver() throws Exception {
		String platformName = System.getProperty("platform", "android");
		String platformVersion = configLoader.getProperty("platformVersion");
		String deviceName = configLoader.getProperty("deviceName");
		String appApkFilePath = configLoader.getProperty("appApkFilePath");
		String appIosFilePath = configLoader.getProperty("appIosFilePath");

		if (platformName == null || platformVersion == null || deviceName == null) {
			throw new IllegalStateException(
					"Mobile platform configuration is incomplete in the config.properties file.");
		}

		URL url;
		DesiredCapabilities caps = new DesiredCapabilities();
		if ("Android".equalsIgnoreCase(platformName)) {
			String deviceType = System.getProperty("deviceType", "real");
			if ("browserstack".equalsIgnoreCase(deviceType)) {
				caps.setCapability("browserstack.username", configLoader.getProperty("browserstack.username"));
				caps.setCapability("browserstack.accesskey", configLoader.getProperty("browserstack.accesskey"));
				caps.setCapability("device", configLoader.getProperty("browserstack.android.deviceName"));
				caps.setCapability("os_version", configLoader.getProperty("browserstack.android.platformVersion"));
				caps.setCapability("app", configLoader.getProperty("browserstack.android.app"));
				url = new URL("https://hub-cloud.browserstack.com/wd/hub");
			} else {
				caps.setCapability("platformName", "Android");
				caps.setCapability("platformVersion", platformVersion);
				caps.setCapability("deviceName", deviceName);
				caps.setCapability("automationName", "uiautomator2");
				caps.setCapability("appPackage", "com.uffizio.taskeye");
				caps.setCapability("appActivity", "ui.activity.startup.SplashScreenActivity");
				caps.setCapability("app", appApkFilePath);
				url = new URL("http://127.0.0.1:4723/"); // Local Appium server URL
			}
		} else if ("iOS".equalsIgnoreCase(platformName)) {
			String deviceType = System.getProperty("deviceType", "real"); // Default to real device if not set
			if ("browserstack".equalsIgnoreCase(deviceType)) {
				caps.setCapability("browserstack.username", configLoader.getProperty("browserstack.username"));
				caps.setCapability("browserstack.accesskey", configLoader.getProperty("browserstack.accesskey"));
				caps.setCapability("device", configLoader.getProperty("browserstack.ios.deviceName"));
				caps.setCapability("os_version", configLoader.getProperty("browserstack.ios.platformVersion"));
				caps.setCapability("app", configLoader.getProperty("browserstack.ios.app"));
				url = new URL("https://hub-cloud.browserstack.com/wd/hub");
			} else {
				caps.setCapability("platformName", "iOS");
				caps.setCapability("platformVersion", platformVersion);
				caps.setCapability("deviceName", deviceName);
				caps.setCapability("automationName", "XCUITest");
				caps.setCapability("app", appIosFilePath);
				url = new URL("http://127.0.0.1:4723/"); // Local Appium server URL
			}
		} else {
			throw new IllegalArgumentException("Unsupported platform: " + platformName);
		}

		startAppiumServer();
		try {
			mobileDriver = new AppiumDriver<>(url, caps);
			mobileDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println("Mobile driver initialized successfully: " + mobileDriver.getClass().getName());
		} catch (Exception e) {
			System.err.println("Error initializing mobile driver: " + e.getMessage());
			throw e;
		}
	}

	public static void setUpWebDriverInDebuggerMode() {
		System.out.println("Setting up WebDriver in debugger mode...");

		// Ensure the correct version of ChromeDriver is used
		WebDriverManager.chromedriver().driverVersion("latest").setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:" + 9222);

		StartChromeWithRemoteDebugging.startChromeIfNotRunning(9222, "C:\\DebuggerData", "Profile 1");



		try {
			// Initialize WebDriver with the given Chrome options
			driver = new ChromeDriver(options);
			setWebDriver(driver);
		} catch (Exception e) {
			// Capture any errors during initialization and print the stack trace
			System.err.println("Failed to initialize WebDriver: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private void startAppiumServer() {
		boolean isServerRunning = checkIfServerIsRunnning(4723);
		if (!isServerRunning) {
			service = AppiumDriverLocalService.buildDefaultService();
			try {
				service.start();
				System.out.println("Appium server started on port 4723");
			} catch (Exception e) {
				System.err.println("Error starting Appium server: " + e.getMessage());
				throw e;
			}
		} else {
			System.out.println("Appium server is already running on port 4723");
		}
	}

	private boolean checkIfServerIsRunnning(int port) {
		System.out.println("checkIfServerIsRunnning: 1");
		boolean isServerRunning = false;
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		}
		return isServerRunning;
	}



	@AfterClass
	public void tearDown() {
		System.out.println("isThreadCreated: " + isThreadCreated);
		if (isThreadCreated) {
			joinCreatedThread();
		}

		if (getWebDriver() != null) {
			DriverManager.quitDriver();
		}
		if (mobileDriver != null) {
			mobileDriver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}

	@BeforeClass(dependsOnMethods = "setUpWebDriver")
	public void loginToApplication() throws InterruptedException {
		if (Boolean.parseBoolean(System.getProperty("debuggerMode"))) {
			return;
		}
		String userType = System.getProperty("userType", "superadmin");
		String project = System.getProperty("project", "trakzee");
		String credentials = configLoader.getUserCredentials(project, userType);
		String[] credentialParts = credentials.split(":");
		String username = credentialParts[0];
		String password = credentialParts[1];

		//POM_LoginPage pom_loginPage = new POM_LoginPage(driver);
		getLoginPage().enterUsername(username);
		getLoginPage().enterPassword(password);
		getLoginPage().clickLoginButton();
		getCommonPage().clickOnJAlertCloseButton();
		getHomePage().clickOnCrossButtonAvoidServiceInteruption();


	}

	public AppiumDriver<MobileElement> getMobileDriver() {
		return mobileDriver;
	}

	public static void hardRefreshPage(WebDriver driver) throws InterruptedException {
		// Non-headless: you could use the Robot class if needed
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_SHIFT);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_R);
			Thread.sleep(100);
			robot.keyRelease(KeyEvent.VK_R);
			Thread.sleep(100);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			System.out.println("Page refreshed successfully using Robot.");
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

	@BeforeMethod
	public void setSoftAssert() {
		softAssert.set(new SoftAssert());
	}

	public static SoftAssert getSoftAssert() {
		if (softAssert.get() == null) {
			softAssert.set(new SoftAssert()); // Initialize if missing
		}
		return softAssert.get();
	}

	@AfterMethod
	public void closeSoftAssert() {
		if (softAssert.get() != null) {
			softAssert.get().assertAll();
			softAssert.remove();
		}
	}


}
