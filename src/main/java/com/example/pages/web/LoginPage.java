package com.example.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username") // Example locator
    private WebElement usernameInput;

    @FindBy(id = "password") // Example locator
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']") // Example locator
    private WebElement loginButton;

    @FindBy(id = "errorMessage") // Example locator for an error message
    private WebElement errorMessageLabel;

    public LoginPage(WebDriver driver) {
        super(driver); // Calls the BasePage constructor and initializes PageFactory
    }

    // Constructor for when the driver is initialized within the BasePage or a test class
    public LoginPage() {
        super(); // Calls the BasePage constructor
        // If the driver is not yet initialized, it needs to be set before PageFactory can work.
        // This is typically handled in a test setup method that calls setupDriver() then new LoginPage(driver).
        // Or, if driver is initialized in BasePage constructor that takes no args, ensure it's done before PageFactory.
        // For this example, we assume the driver is passed in or set up before methods are called.
    }


    public void enterUsername(String username) {
        sendKeysToElement(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordInput, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public String getErrorMessage() {
        if (errorMessageLabel.isDisplayed()) {
            return getElementText(errorMessageLabel);
        }
        return null;
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        // Optionally, return the next page object, e.g., return new DashboardPage(driver);
    }

    public void navigateToLoginPage() {
        // Assuming the driver is initialized and baseUrl is available from ConfigLoader
        // The BasePage constructor already initializes configLoader
        // The driver should be initialized in a @BeforeMethod or @BeforeClass in the test
        if (driver == null) {
            // Initialize driver if not already done.
            // This might be better handled in a test setup method.
            // For simplicity here, let's assume a default browser from config if not set.
            String browser = configLoader.getProperty("defaultBrowser");
            super.setupDriver(browser); // Calls initializeWebDriver from BasePage
        }
        navigateTo(configLoader.getBaseUrl() + "/login"); // Example login path
    }
}
