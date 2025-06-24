package projects.smartbus.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static By usernameInput = By.id("username");
    public static By passwordInput = By.id("password");
    public static By loginButton = By.xpath("//button[contains(text(), 'Login')]");
}
