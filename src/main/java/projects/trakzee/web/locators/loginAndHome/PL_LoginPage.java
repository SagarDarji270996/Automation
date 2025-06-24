package projects.trakzee.web.locators.loginAndHome;
import org.openqa.selenium.By;

public class PL_LoginPage {
    public static By usernameInput = By.id("username");
	public static By passwordInput = By.xpath("//input[@id='password' or @name='password']");
    public static By loginButton = By.xpath("//button[contains(text(), 'Login')]");
}
