package com.example.pages.mobile;

import com.example.config.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasePageMobile {
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected ConfigLoader configLoader;

    public BasePageMobile(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait time
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // Overloaded constructor for initializing without an existing driver
    public BasePageMobile() {
        this.configLoader = new ConfigLoader();
        // Driver initialization will be handled by a dedicated method or in subclasses
    }

    protected void initializeMobileDriver(String platformName, String deviceName, String appPathOrPackage, String appiumServerUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName); // e.g., "Android", "iOS"
        capabilities.setCapability("deviceName", deviceName);     // e.g., "Android Emulator", "iPhone Simulator"
        // capabilities.setCapability("udid", configLoader.getProperty("mobile.udid")); // Optional: if specific device
        capabilities.setCapability("automationName", configLoader.getProperty("mobile.automationName", "UiAutomator2")); // Or "XCUITest" for iOS

        if (appPathOrPackage.endsWith(".apk") || appPathOrPackage.endsWith(".app") || appPathOrPackage.endsWith(".zip")) {
            capabilities.setCapability("app", appPathOrPackage); // Path to app
        } else { // Assume it's an app package and app activity for Android
            capabilities.setCapability("appPackage", appPathOrPackage);
            capabilities.setCapability("appActivity", configLoader.getProperty("mobile.appActivity")); // e.g., ".MainActivity"
        }

        capabilities.setCapability("noReset", Boolean.parseBoolean(configLoader.getProperty("mobile.noReset", "false")));
        capabilities.setCapability("fullReset", Boolean.parseBoolean(configLoader.getProperty("mobile.fullReset", "false")));

        // Add any other desired capabilities from config
        // Example: capabilities.setCapability("someOtherCapability", configLoader.getProperty("mobile.someOtherCapability"));

        try {
            this.driver = new AppiumDriver(new URL(appiumServerUrl), capabilities);
        } catch (MalformedURLException e) {
            System.err.println("Error creating Appium driver session: Invalid Appium server URL");
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }

        long defaultWait = Long.parseLong(configLoader.getProperty("mobile.defaultWaitTimeout", "20"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWait));
        // Implicit wait can also be set if desired, but explicit waits are generally preferred
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configLoader.getProperty("mobile.implicitWaitTimeout", "10"))));
    }

    // Call this method in your test setup
    public void setupMobileDriver(String platformName, String deviceName, String appPathOrPackage, String appiumServerUrl) {
        initializeMobileDriver(platformName, deviceName, appPathOrPackage, appiumServerUrl);
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(WebElement element) {
        waitForClickability(element).click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    public void closeApp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
