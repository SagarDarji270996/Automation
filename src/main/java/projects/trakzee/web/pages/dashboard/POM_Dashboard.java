package projects.trakzee.web.pages.dashboard;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.ProjectUtility;

public class POM_Dashboard {
	private final WebDriverWait wait;
	private WebDriver driver = TestBase.getWebDriver();
	private SoftAssert softAssert = new SoftAssert();
	private ProjectUtility pu = new ProjectUtility();

	public POM_Dashboard(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}
	

}
