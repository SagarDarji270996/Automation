package projects.trakzee.web.pages.loginAndHome;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.loginAndHome.PL_LoginPage;

public class POM_LoginPage implements InitializePages {
	private WebDriver driver = TestBase.getWebDriver();
	public POM_LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriverWait wait = new WebDriverWait(driver, 5);

	public void enterUsername(String username) {
		WebElement usernameField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(PL_LoginPage.usernameInput));
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(PL_LoginPage.passwordInput));
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(PL_LoginPage.loginButton));
		loginButton.click();
	}
}
