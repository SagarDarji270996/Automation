package com.example.tests.mobile;

import com.example.config.ConfigLoader;
import com.example.pages.mobile.MobileLoginPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MobileLoginTests {

    private AppiumDriver driver;
    private MobileLoginPage mobileLoginPage;
    private ConfigLoader configLoader;

    // Parameters can be passed from TestNG XML for platform, device, app etc.
    private String platformName;
    private String deviceName;
    private String appPathOrPackage;
    private String appiumServerUrl;


    @Parameters({"platformName", "deviceName", "appPathOrPackage", "appiumServerUrl"})
    @BeforeClass(alwaysRun = true)
    public void suiteSetup(@Optional("Android") String platformName,
                           @Optional("Android Emulator") String deviceName,
                           @Optional("com.example.app") String appPathOrPackage, // Default to a package name
                           @Optional("http://127.0.0.1:4723/wd/hub") String appiumServerUrl) {
        this.configLoader = new ConfigLoader();
        // Use TestNG parameters if provided, otherwise fallback to config file values
        this.platformName = System.getProperty("mobile.platformName", configLoader.getProperty("mobile.platformName", platformName));
        this.deviceName = System.getProperty("mobile.deviceName", configLoader.getProperty("mobile.deviceName", deviceName));

        // For app, prioritize system property, then TestNG param, then config
        String appConfigValue = this.platformName.equalsIgnoreCase("Android") ?
                                configLoader.getProperty("mobile.appPath", appPathOrPackage) : // or mobile.appPackage
                                configLoader.getProperty("mobile.ios.appPath", appPathOrPackage); // if you have iOS specific
        this.appPathOrPackage = System.getProperty("mobile.app", appConfigValue);

        this.appiumServerUrl = System.getProperty("appium.server.url", configLoader.getProperty("mobile.appiumServerUrl", appiumServerUrl));

        // Driver is initialized in beforeMethod
    }

    @BeforeMethod
    public void setupTest() {
        mobileLoginPage = new MobileLoginPage(); // Uses constructor that allows driver init later
        // Initialize AppiumDriver using parameters (which can be overridden by config or system props)
        mobileLoginPage.setupMobileDriver(
                this.platformName,
                this.deviceName,
                this.appPathOrPackage,
                this.appiumServerUrl
        );
        driver = mobileLoginPage.getDriver(); // Get the initialized driver
        mobileLoginPage = new MobileLoginPage(driver); // Re-initialize with the active driver for PageFactory
    }

    @Test(description = "Test successful mobile login with SuperAdmin credentials")
    public void testSuccessfulMobileLoginSuperAdmin() {
        String username = configLoader.getUsername("SuperAdmin");
        String password = configLoader.getPassword("SuperAdmin");
        mobileLoginPage.login(username, password);

        // Add assertions here to verify successful login on the mobile app
        // This is highly dependent on the app's behavior after login
        // Example: Assert that a welcome message or dashboard screen is displayed
        System.out.println("Attempted mobile login with SuperAdmin. Add actual assertions.");
        // MobileDashboardPage mobileDashboardPage = new MobileDashboardPage(driver);
        // Assert.assertTrue(mobileDashboardPage.isUserOnDashboard(), "User should be on the dashboard after login.");
    }

    @Test(description = "Test mobile login with invalid credentials")
    public void testInvalidMobileLogin() {
        mobileLoginPage.login("invalidMobileUser", "wrongPassword");
        String errorMessage = mobileLoginPage.getErrorMessage(); // Assuming this method works as expected
        Assert.assertNotNull(errorMessage, "Error message should be displayed for invalid mobile login.");
        // Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Error message content is incorrect.");
        System.out.println("Attempted invalid mobile login. Error message: " + errorMessage);
    }

    @AfterMethod
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void suiteTeardown() {
        // Any cleanup for the entire class if needed
    }
}
