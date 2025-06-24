package projects.trakzee.web.pages.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.common.IframesOfApplication;
import projects.trakzee.web.locators.downloads.PL_CloudDonwload;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.ProjectUtility;

public class POM_CloudDownlaod {

	private final WebDriverWait wait;
	private final WebDriver driver;
	private SoftAssert softAssert = new SoftAssert();
	private final CommonMethods commonMethods;
	private final IframesOfApplication iframe;
	private ProjectUtility pu = new ProjectUtility();

	public POM_CloudDownlaod(WebDriver driver) {
		this.driver = driver;
		this.commonMethods = new CommonMethods(driver);
		this.wait = new WebDriverWait(driver, 60);
		this.iframe = new IframesOfApplication(driver);
	}
	

}
