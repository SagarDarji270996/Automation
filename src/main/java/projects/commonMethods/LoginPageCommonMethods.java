package projects.commonMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import projects.taskeye.web.locators.LoginPageLocators;

public class LoginPageCommonMethods {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPageCommonMethods(WebDriver driver) { // Corrected constructor name
        this.driver = driver;
        this.wait = new WebDriverWait(driver, (10));
    }

    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.usernameInput));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.loginButton));
        loginButton.click();
    }
}
