package com.example.pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MobileLoginPage extends BasePageMobile {

    // Example locators: Use appropriate locators for your app
    // These are just placeholders
    @AndroidFindBy(accessibility = "usernameInput") // Example for Android
    @iOSXCUITFindBy(accessibility = "usernameInput")   // Example for iOS
    private WebElement usernameInput;

    @AndroidFindBy(accessibility = "passwordInput")
    @iOSXCUITFindBy(accessibility = "passwordInput")
    private WebElement passwordInput;

    @AndroidFindBy(accessibility = "loginButton")
    @iOSXCUITFindBy(accessibility = "loginButton")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.example.app:id/error_message") // More specific Android ID
    @iOSXCUITFindBy(accessibility = "errorMessageLabel")
    private WebElement errorMessageLabel;


    public MobileLoginPage(AppiumDriver driver) {
        super(driver); // Calls BasePageMobile constructor, which initializes PageFactory with AppiumFieldDecorator
    }

    // Constructor for when the driver is initialized within the BasePageMobile or a test class
    public MobileLoginPage() {
        super();
        // Similar to web, driver needs to be initialized before PageFactory elements can be used.
        // This is typically handled in a test setup method.
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
        // Visibility check might be different or need adjustments for mobile
        if (waitForVisibility(errorMessageLabel).isDisplayed()) {
            return getElementText(errorMessageLabel);
        }
        return null;
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        // Optionally, return the next page object, e.g., return new MobileDashboardPage(driver);
    }

    // Mobile apps are typically launched, not navigated to via URL in the same way as web.
    // Navigation to a specific screen within the app would be handled by interactions from the current screen.
    // The initial launch is handled by driver capabilities.
}
