package com.example.tests.web;

import com.example.config.ConfigLoader;
import com.example.pages.web.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WebLoginTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private ConfigLoader configLoader;
    private String browserType;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void suiteSetup(@Optional("chrome") String browser) {
        this.browserType = browser; // Store browser type from TestNG XML
        configLoader = new ConfigLoader();
        // Driver is initialized in beforeMethod to ensure a fresh session for each test
    }

    @BeforeMethod
    public void setupTest() {
        // Initialize WebDriver and LoginPage for each test method for isolation
        loginPage = new LoginPage(); // Uses constructor that allows driver init later
        loginPage.setupDriver(this.browserType); // Initialize driver via BasePage
        driver = loginPage.getDriver(); // Get the initialized driver
        loginPage = new LoginPage(driver); // Re-initialize LoginPage with the now active driver for PageFactory
        loginPage.navigateToLoginPage();
    }

    @Test(description = "Test successful login with SuperAdmin credentials")
    public void testSuccessfulLoginSuperAdmin() {
        String username = configLoader.getUsername("SuperAdmin");
        String password = configLoader.getPassword("SuperAdmin");
        loginPage.login(username, password);

        // Add assertions here to verify successful login, e.g.,
        // Assert.assertTrue(dashboardPage.isUserLoggedIn(username), "User should be logged in.");
        // For this example, we'll just assert the URL changes or a welcome message appears
        // This depends on the actual application behavior after login
        // Assert.assertNotEquals(driver.getCurrentUrl(), configLoader.getBaseUrl() + "/login", "URL should change after login.");
        System.out.println("Attempted login with SuperAdmin. Add actual assertions for verification.");
        // Example: (Assuming a dashboard page is loaded)
        // DashboardPage dashboardPage = new DashboardPage(driver);
        // Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after successful login.");
    }

    @Test(description = "Test login with invalid credentials")
    public void testInvalidLogin() {
        loginPage.login("invalidUser", "invalidPassword");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed for invalid login.");
        // Further assert the content of the error message if known, e.g.:
        // Assert.assertEquals(errorMessage, "Invalid username or password", "Error message content is incorrect.");
        System.out.println("Attempted invalid login. Error message: " + errorMessage);
    }

    @Test(description = "Test successful login with Admin credentials")
    public void testSuccessfulLoginAdmin() {
        String username = configLoader.getUsername("Admin");
        String password = configLoader.getPassword("Admin");
        loginPage.login(username, password);
        System.out.println("Attempted login with Admin. Add actual assertions for verification.");
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
