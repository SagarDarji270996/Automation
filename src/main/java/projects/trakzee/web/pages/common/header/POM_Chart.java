package projects.trakzee.web.pages.common.header;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Charts;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.ScreenshotHandler;

public class POM_Chart implements InitializePages {
	private WebDriver driver = TestBase.getWebDriver();
	WebDriverWait wait;
	public POM_Chart(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}

	SoftAssert softAssert = new SoftAssert();
	ProjectUtility pu = new ProjectUtility();

	String pageTitle = "Chart";
	String headerValue = "Running V/S Stop";
	String yaxis = "Idel";
	String xaxis = "Inactive";

	public void verifyTheChartsFunctionality(String pageTitle, String headerValue, String yaxis, String xaxis)
			throws InterruptedException, IOException {
		// SCENARIOS_DONE: Verify the charts functionality
		getTravelPage().clickOnChartIconButtonAndVerifyTooltip();
		checkPageHeaderTitle(pageTitle);
		clickOnCancelButton();
		getTravelPage().clickOnChartIconButtonAndVerifyTooltip();
		checkListOfUIElementsPresentOnChartsPage();
		selectYAxis(yaxis);
		setHeader(headerValue);
		selectXAxis(xaxis);
		clickOnSaveButton();

	}

	public void checkPageHeaderTitle(String pageTitle) throws InterruptedException {
		// STEP_DONE: check page header title
		getIFramePage().switchToContentFrame();
		pu.getElementTextAndCompare(driver, PL_Charts.pageTitle, pageTitle, 5);

	}

	public void checkListOfUIElementsPresentOnChartsPage() throws IOException, InterruptedException {
		// STEP_DONE: check list of UI elements present on charts page
		getIFramePage().switchToContentFrame();
		ScreenshotHandler.compareElementScreenshot(driver, PL_Charts.screenshortUIElement,
				"/home/uffizio/Automation/MP/taskeye_watm/src/test/resources/trakzee/screenshotForUIElementComprision/reports/activity/travel/charts.png",
				"charts",
				"/home/uffizio/Automation/MP/taskeye_watm/src/test/resources/trakzee/screenshotForUIElementComprision/reports/activity/travel");

	}

	public void setHeader(String headerValue) {
		// STEP_DONE: set header
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Charts.textHeader, headerValue);
	}

	public void selectXAxis(String xaxis) {
		// STEP_DONE: select x-axis
		getIFramePage().switchToContentFrame();
		//CommonMethods.pickRandomValueFromList(driver, PL_Charts.listXAxis);
		getCommonMethodPage().selectFromSelectDropdown(PL_Charts.listXAxis, xaxis);

	}

	public void selectYAxis(String yaxis) {
		// STEP_DONE: select y-axis
		getIFramePage().switchToContentFrame();
		getCommonMethodPage().selectFromSelectDropdown(PL_Charts.listYAxis, yaxis);
		//CommonMethods.pickRandomValueFromList(driver, PL_Charts.listXAxis);
	}

	public void clickOnSaveButton() {
		// STEP_DONE: click on save button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Charts.btnSaveOnCharts, 10);
	}

	public void clickOnCancelButton() {
		// STEP_DONE: click on cancel button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Charts.btnCancelOnCharts, 10);
	}
}