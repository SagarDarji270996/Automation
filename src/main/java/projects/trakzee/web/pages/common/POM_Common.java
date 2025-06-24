package projects.trakzee.web.pages.common;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.PL_FavoriteButton;
import projects.trakzee.web.locators.common.PL_Filter;
import projects.trakzee.web.locators.reports.activity.PL_Travel;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.DragAndDrop;
import projects.trakzee.web.projectUtility.DropdownHandler;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;
import projects.trakzee.web.projectUtility.TimeHandling;
import projects.trakzee.web.projectUtility.VisibleElement;

public class POM_Common implements InitializePages {

	private WebDriverWait wait;
	private static WebDriver driver = TestBase.getWebDriver();

	public POM_Common(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}

	private ProjectUtility pu = new ProjectUtility();

	public void clickOnResetButtonPresentAtFooter() {
		//STEP_DONE: click on reset button present at footer
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_Commons.btnResetAtFooter, 10);
	}

	public String getFavoriteButtonColor() {
		// STEP_DONE: check favorite button color
		getIFramePage().switchToTitleFrame();
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(PL_FavoriteButton.btnfavoriteYellow));
			return "yellow";
		} catch (Exception e1) {
			System.out.println("Excpetion: " + e1.getMessage());
			return "white";
		}
	}

	public Boolean checkPageTitlePresence(String pageTilte, int waitTimeInSeconds) throws InterruptedException {
		// STEP_DONE: Get page title and verify
		getIFramePage().switchToTitleFrame();
		return pu.getElementTextAndCompare(driver, String.format(PL_Commons.pageTitleStringFormat, pageTilte.trim()),
				waitTimeInSeconds);
	}

	public void getJAlertMessageAndVerifyWhileReportDownload(List<String> jalertExpectedMessage)
			throws InterruptedException {
		//STEP_DONE: Get JAlert message and verify
		getIFramePage().switchToDivFrame();
		pu.getElementTextAndCheckPresenceListExpectedValues(driver, PL_Commons.textJAlertMessage,
				jalertExpectedMessage);

	}

	public String getJAlertMessageAndVerify(String jalertExpectedMessage) throws InterruptedException {
		//STEP_DONE: Get JAlert message and verify
		getIFramePage().switchToDivFrame();
		return pu.getElementTextAndCompare(driver, PL_Commons.textJAlertMessage, jalertExpectedMessage, 2);
	}

	public void clickOnJAlertCloseButton() {
		//STEP_DONE: Click on JAlert close button
		getIFramePage().switchToDivFrame();
		if (isJAlertPresent()) {
			pu.clickOnButton(driver, PL_Commons.btnJAlertClose, 1);
		}
	}

	public void dragAndDrop(WebDriver driver, By locator, int xOffSet, int yOffSet) {
		//STEP_DONE: Click on JAlert close button
		getIFramePage().switchToContentFrame();
		DragAndDrop.dragAndDropInSamePlane(driver, locator, xOffSet, yOffSet);
	}

	public boolean isJAlertPresent() {
		//STEP_DONE: to click on the jalert close button
		return VisibleElement.isElementVisible(driver, PL_Commons.btnJAlertClose, 1);
	}

	public void clickOnFilterIconButtonAndVerifyTooltip() throws InterruptedException {
		// STEP_DONE: click on the filter icon button and verify tooltip
		getIFramePage().switchToContentFrame();
		boolean isApplyButtonVisible = pu.getElementTextAndCompareIfLocatorHaveMulitpleOption(driver,
				PL_Filter.btnApplyAtFilter, "Apply", 5, false);
		System.out.println("isApplyButtonVisible: " + isApplyButtonVisible);
		if (!isApplyButtonVisible) {
			getIFramePage().switchToTitleFrame();
			pu.getTextByAttributeAndVerify(driver, "title", "Filter", PL_Commons.btnFilterAtHeader, 5);
			pu.clickOnButton(driver, PL_Commons.btnFilterAtHeader);
		}
	}

	public boolean isDisplayedNoResultMatchedAfterSearchedInSearchBox() {
		//STEP_DONE: check on drop down search no result matched displayed
		return VisibleElement.isElementVisible(driver, PL_Commons.textNoResultMatched);
	}

	public void clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne() throws InterruptedException {
		// STEP_DONE: click on the filter icon button and verify tooltip
		getIFramePage().switchToContentFrame();
		boolean isApplyButtonVisible = pu.getElementTextAndCompare(driver, PL_Filter.btnApplyAtFilterAtIndexOne,
				"Apply");
		System.out.println("isApplyButtonVisible: " + isApplyButtonVisible);
		if (!isApplyButtonVisible) {
			getIFramePage().switchToTitleFrame();
			pu.clickOnButton(driver, PL_Commons.btnFilterAtHeader);
		}
	}

	public void checkScreenLoadingTime(int expectedpageloadTimeInSeconds) {
		//STEP_DONE: Check the travel summary page loading on default
		getIFramePage().switchToContentFrame();
		TimeHandling.getPageLoadingIconVisibilityAndInvisibilityTimeDifference(driver, PL_Commons.loadingIcon,
				30);
		// CommonMethods.getInvisibilityWaitTime(driver, PL_Commons.loadingIcon,
		// expectedpageloadTimeInSeconds);
	}

	public void writeDataInExcelSheet(String excelFileName, String sheetName, int rowIndex,
			int columnIndexToSetActaulValue, int columnIndexToSetStatus, String values, String expectedValue,
			int columnIndexToFillColor) throws IOException, InterruptedException {
		//STEP_DONE: Write data in the excel sheet

		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + excelFileName.trim() + ".xlsx";
		ReadDataFromExcelFile rdfef = new ReadDataFromExcelFile(filePath);
		rdfef.setCellData(sheetName, rowIndex, columnIndexToSetActaulValue, values);
		rdfef.setCellData(sheetName, rowIndex, columnIndexToSetStatus, expectedValue.equals(values) ? "Pass" : "Fail");
		rdfef.fillColor(sheetName, rowIndex, columnIndexToFillColor, expectedValue.equals(values) ? "Green" : "Red");
		Thread.sleep(500);

	}

	//	public static void main(String[] args) throws IOException, InterruptedException {
	//		Map<Integer, String> columnData = new HashMap<>();
	//		List<Integer> data = new ArrayList<>();
	//		data.add(1);
	//		columnData.put(2, "12");
	//		columnData.put(4, "54");// Writing data to column index 2
	//		writeDataInExcelSheetWithMultipleEntriesInSingleColumn("Geofence2", "Geofence", data, columnData);
	//	}

	public void writeDataInExcelSheetWithMultipleEntriesInSingleColumn(String excelFileName, String sheetName,
			Map<Integer, List<String>> columnData) throws IOException {

		// Construct the file path
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + excelFileName.trim() + ".xlsx";

		// Initialize ReadDataFromExcelFile class
		ReadDataFromExcelFile rdfef = new ReadDataFromExcelFile(filePath);

		// Write data to the Excel file
		rdfef.setCellDataInSingleColumn(sheetName, columnData);

		System.out.println("Data written successfully to Excel file: " + filePath);
	}

	public void writeDataInExcelSheetWithMultipleEntriesInMultipleColumnsBasedOnGivenRows(String excelFileName,
			String sheetName, List<Integer> rowCounts, Map<Integer, List<String>> columnData) throws IOException {
		System.out.println("columnData: " + columnData);
		// Construct the file path
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Migrassion"
				+ File.separator + excelFileName.trim() + ".xlsx";

		// Initialize ReadDataFromExcelFile class
		ReadDataFromExcelFile rdfef = new ReadDataFromExcelFile(
				"C:\\Project\\Web Automation\\taskeye_watm-ReportTestCasesHandling\\src\\test\\resources\\trakzee\\DataProviderXLS\\Migration\\Migration.xlsx");

		// Write data to the Excel file
		rdfef.setCellDataInMultipleColumns(sheetName, rowCounts, columnData);

		System.out.println("Data written successfully to Excel file: " + filePath);
	}

	public void checkPageLoadingOnDefault(int expectedpageloadTimeInSeconds) {
		//STEP_DONE: Check the travel summary page loading on default
		getIFramePage().switchToContentFrame();
		CommonMethods.getInvisibilityWaitTime(driver, PL_Commons.loadingIcon, expectedpageloadTimeInSeconds);
	}

	public void verifyTheColumnValuesBasedOnTheGivenTableHeaderName() {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		String overviewScreenColumnName = "Company";
		String expectedListCommaSeperated = "HP Company 1";
		CommonMethods.compareListByTextByRemovingDuplicateFromActualListIgnorOrder(driver,
				PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(overviewScreenColumnName),
				expectedListCommaSeperated);
	}

	@Step("Select the month and year")
	public void selectMonthAndYear(String monthJanFeb, String yearYYYY) {
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.DropdownDateSelection, 5);
		//to select year
		DropdownHandler.selectDropdownValueBySelectClass(driver, PL_Filter.selectYearDropdownSelectTag,
				yearYYYY);

		//to select month
		DropdownHandler.selectDropdownValueBySelectClass(driver, PL_Filter.selectMonthDropdownSelectTag, monthJanFeb);
	}
}