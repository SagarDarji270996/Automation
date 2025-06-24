package projects.trakzee.web.pages.common.footer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.footer.PL_Footer;
import projects.trakzee.web.locators.reports.activity.PL_Travel;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.DropdownHandler;
import projects.trakzee.web.projectUtility.FileHandling;
import projects.trakzee.web.projectUtility.HoverOnElement;
import projects.trakzee.web.projectUtility.MethodGeneratorFromTODOList;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.RowListHandling;
import projects.trakzee.web.projectUtility.ScreenshotHandler;
import projects.trakzee.web.projectUtility.WebElementList;
import projects.trakzee.web.projectUtility.XpathExtractor;

public class POM_Footer implements InitializePages {
	private WebDriver driver = TestBase.getWebDriver();
	WebDriverWait wait;
	public POM_Footer(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}

	public static void main(String[] args) {
		String[] testSteps = { "verify the overview screen records present in the list based on the column name" };
		MethodGeneratorFromTODOList.generateMethodsFromSteps(testSteps, "STEP");

	}



	SoftAssert softAssert = new SoftAssert();
	ProjectUtility pu = new ProjectUtility();



	@Step()
	public boolean clickOnSaveIconButtonAtFooter() {
		// DONE STEP: click on save icon or insert button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnSaveAtFooter);

	}

	@Step()
	public boolean clickDeleteIconButtonAtFooter() {
		// DONE STEP: click on dlete button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnDeleteAtFooter);

	}

	@Step()
	public boolean clickBackIconButtonAtFooter() {
		// DONE STEP: click on back button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnBackAtFooter);

	}

	@Step()
	public boolean clickOnPlusNewIconOrInsertButtonAtFooter() {
		// TODO STEP: click on plus new icon or insert button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnPlusAtFooter);

	}

	@Step()
	public boolean clickOnResetButtonAtFooter() {
		// TODO STEP: click on reset button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnResetAtFooter);

	}

	@Step()
	public boolean clickOnDownloadXlsIconButtonAtFooter() {
		// TODO STEP: click on download xls icon button at footer
		getIFramePage().switchToBottomFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Footer.btnExcelDownloadAtFooter);

	}


	public void verifyTheReportDonwloadButtonAtFooter(String[] jalertExpectedMessage) throws InterruptedException {
		// DONE SCENARIOS: verify that user able to move and click on the reports download type and on hover check report download type present
		clickOnDownloadXLSButtonAtFooter();
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();
		clickOnDownloadPDFButtonAtFooter();
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();
		clickOnDownloadCSVButtonAtFooter();
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();
	}

	public void verifyTheListOfReportTypeDownloadOptions(String tootipTitleForExcel, String tootipTitleForCSV,
			String tootipTitleForPDF, String... expectedReportDownloadTypes) throws InterruptedException {
		//SCENARIOS_DONE: verify the list of report type download options
		verifyDownloadTypeByHoverOnXLSIconButton(tootipTitleForExcel, expectedReportDownloadTypes);
		verifyDownloadTypeByHoverOnPDFIconButton(tootipTitleForPDF, expectedReportDownloadTypes);
		verifyDownloadTypeByHoverOnCSVIconButton(tootipTitleForCSV, expectedReportDownloadTypes);

	}

	public void verifyThatUserAbleToSelectTheAnyReportTypeDownloadAfterHover(String reportTypeDownload,
			String[] jalertExpectedMessage) throws InterruptedException {
		// SCENARIOS_DONE: verify that user able to select the any report type download after hover
		selectDownloadTypeByHoverOnXLSIconButton(reportTypeDownload);
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();
		selectDownloadTypeByHoverOnPDFIconButton(reportTypeDownload);
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();
		selectDownloadTypeByHoverOnCSVIconButton(reportTypeDownload);
		getCommonPage().getJAlertMessageAndVerifyWhileReportDownload(Arrays.asList(jalertExpectedMessage));
		getCommonPage().clickOnJAlertCloseButton();

	}

	@Story("Bottom filter and search functionality")
	public void checkTheFooterSearchFuntionality(String searchValue, String searchAbleColumn)
			throws InterruptedException {
		//  SCENARIOS_DONE: Check the footer search funtionality
		setDataInFooterSearchFox(searchValue);
		selectDataFromSearchAbleListAtFooter(searchAbleColumn);
		clickOnSearchIconButtonPresentAtFooter();
		verifyTheOverviewScreenRecordsPresentInTheListBasedOnTheColumnName(searchValue);
	}

	public void checkThePaginationFuncatinality(String recordsPerPage, String pageNumber) {
		// SCENARIOS_DONE: Check the pagination funcatinality
		selectRecordsPerPageAndVerifyResult(recordsPerPage);
		selectPageNumberAndVerifyResult(pageNumber);
		checkRecordsPerPageOutOfTotalRecords(recordsPerPage, pageNumber);
	}

	public void verifyDownloadedReportAfterClickFromFooter(String mostRecentDownloadedReportName)
			throws IOException, InterruptedException {
		// SCENARIOS_DONE: write here steps for verifying the downloaded report data with the existing already downloaded reports for the PDF report for summary report type
		getCommonPage().clickOnJAlertCloseButton();
		getHomePage().clickOnCloudDownloadIcon();
		getCloudDownloadPage().verifyThatUserAbleToDownloadTheReportFromCloudDownload(mostRecentDownloadedReportName);
	}

	public void updateFileName(String fileDownloadDefaultAddress, String fileNewName) {
		File file = FileHandling.getMostRecentDownloadedFile(fileDownloadDefaultAddress);
		FileHandling.renameFile(file, fileNewName);
	}

	public void clickOnDownloadXLSButtonAtFooter() {
		//STEP_DONE: Click on download XLS button at footer
		getIFramePage().switchToBottomFrame();
		pu.clickOnButton(driver, PL_Commons.btnExcelDownloadAtFooter);

	}

	public void clickOnDownloadPDFButtonAtFooter() {
		//STEP_DONE: Click on download PDF button at footer
		getIFramePage().switchToBottomFrame();
		pu.clickOnButton(driver, PL_Commons.btnPDFDownloadAtFooter);
	}

	public void clickOnDownloadCSVButtonAtFooter() {
		//STEP_DONE: Click on download CSV button at footer
		getIFramePage().switchToBottomFrame();
		pu.clickOnButton(driver, PL_Commons.btnCSVDownloadAtFooter);
	}

	public void setDataInFooterSearchFox(String saerchValue) {
		//STEP_DONE: set data in footer search box
		getIFramePage().switchToBottomFrame();
		pu.setDataInFieldWithClear(driver, PL_Commons.searchBoxBottomAtFooter, saerchValue);

	}

	public void selectDataFromSearchAbleListAtFooter(String searchAbleColumn) {
		//STEP_DONE: select options from the search able list
		getIFramePage().switchToBottomFrame();
		DropdownHandler.clickAndSelectDropdownValueBySelectClass(driver, PL_Commons.btnSearchAbleField,
				PL_Commons.listSearchAbleFieldsOrColumnName, searchAbleColumn);
	}

	public void clickOnSearchIconButtonPresentAtFooter() {
		// STEP_DONE: click on search icon button present at footer
		getIFramePage().switchToBottomFrame();
		pu.clickOnButton(driver, PL_Commons.searchIconBottomAtFooter);
	}

	public void verifyTheOverviewScreenRecordsPresentInTheListBasedOnTheColumnName(String searchKey)
			throws InterruptedException {
		// STEP_DONE: verify the overview screen records present in the list based on the column name
		getIFramePage().switchToContentFrame();
		RowListHandling.findElementFromRowListAndVerifyByAttribute(driver,
				PL_Commons.listColumnSpecificValueEntriesInOverViewScreen, "title", searchKey, 30);
	}

	String recordsPerPage = "10";
	String pageNumber = "1";

	public void selectRecordsPerPageAndVerifyResult(String recordsPerPage) {
		//STEP_DONE: Select records per page and verify the result
		getIFramePage().switchToBottomFrame();
		String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Commons.iconRecordPerPageAtFooter);
		String selectedRecordsPerpage = DropdownHandler.selectDropdownValueBySelectClass(driver, xpath, recordsPerPage);
		int numberOfEntries = WebElementList.getWebElementListSize(driver, PL_Commons.iconRecordPerPageAtFooter);
		softAssert.assertTrue(Integer.parseInt(selectedRecordsPerpage) <= numberOfEntries,
				"Not matached selected records per page and reconds on page");
	}

	public void getRecordsPerPageSelected() {
		getIFramePage().switchToBottomFrame();

	}

	public void selectPageNumberAndVerifyResult(String pageNumber) {
		//STEP_DONE: Select the page number and verify the result
		getIFramePage().switchToBottomFrame();
		String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Commons.pageCounterAtFooter);
		DropdownHandler.selectDropdownValueBySelectClass(driver, xpath, pageNumber);

	}

	public void checkRecordsPerPageOutOfTotalRecords(String recordsPerPage, String pageNumber) {
		//STEP_DONE: Check records per page out of total records
		getIFramePage().switchToBottomFrame();
		int startCount = Integer.parseInt(recordsPerPage) * Integer.parseInt(pageNumber)
				- Integer.parseInt(recordsPerPage) + 1;
		String recordsEtries = "" + startCount + "-" + Integer.parseInt(recordsPerPage) * Integer.parseInt(pageNumber);
		String actualRecordsEntries = CommonMethods.getElementText(driver, PL_Commons.textTotalRecordsAtFooter);
		String result = actualRecordsEntries.contains("(") ? actualRecordsEntries.split("\\(")[0].trim()
				: actualRecordsEntries.trim();
		System.out.println("Actual result is: " + result);
		softAssert.assertEquals(recordsEtries, result, "Enteries not matched");
	}

	public String getTotalRecordsPresent() throws InterruptedException {
		getIFramePage().switchToBottomFrame();
		String records = CommonMethods.getElementText(driver, PL_Commons.textTotalRecordsPresentAtFooter, 10);
		System.out.println("Total records is: " + records);
		return records;
	}

	public void verifyDownloadedReportForSummaryXLS() {
		// TODO: write here steps for verifying the downloaded report data with the existing already downloaded reports for the XLS report for summary report type

	}

	public void verifyDownloadedReportForSummaryWithDetailsXLS() {
		// TODO: write here steps for verifying the downloaded report data with the existing already downloaded reports for the XLS report for summary with details report type
	}

	public String moveDownloadedFileFromDefaultDownloadToWithinProject(String fileDownloadDefaultAddress,
			//STEP_DONE: Move the downloaded report from the default address to within project folder
			String projectFolderAddressToPlaceReports) throws IOException {
		String fileName = FileHandling.moveMostRecentDownloadedFileToNewLocation(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);
		System.out.println("FileName: " + fileName);

		return fileName;

	}

	public void verifyListOfUIElementsPresentOnFooter() throws IOException {
		//STEP_DONE: Verify the list of UI elements present on the footer	
		getIFramePage().switchToBottomFrame();
		ScreenshotHandler.compareElementScreenshot(driver, PL_Travel.footerUIElemnet, System.getProperty("user.dir")
				+ "/taskeye_watm/src/test/resources/trakzee/screenshotForUIElementComprision/reports/activity/travel/footerUI.png");

	}

	public boolean clickRefreshButtonAndVerifyTooltipTitleAtFooter(String tooltipTitleForReset,
			String expectedScreenTitle)
			throws InterruptedException {
		//STEP_DONE: Click refresh button and verify tooltip title at footer
		String pageTitleBeforeClick = null;
		String pageTitleAfterClick = null;

		getIFramePage().switchToTitleFrame();
		pageTitleBeforeClick = pu.getElementTextAndCompare(driver, PL_Commons.screenTitle, expectedScreenTitle, 5);
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverAndVerifyAndClicks(driver, PL_Commons.btnResetAtFooter, "title", tooltipTitleForReset);
		getIFramePage().switchToTitleFrame();
		pageTitleAfterClick = pu.getElementTextAndCompare(driver, PL_Commons.screenTitle, expectedScreenTitle, 5);
		TestBase.getSoftAssert().assertEquals(pageTitleBeforeClick.trim(), pageTitleAfterClick.trim(),
				"Both page title not matched");
		return true;
	}

	public void verifyDownloadedReportForSummaryCSV() {
		// TODO: write here steps for verifying the downloaded report data with the existing already downloaded reports for the CSV report for summary report type

	}

	public void verifyDownloadedReportForSummaryWithDetailsCSV() {
		// TODO: write here steps for verifying the downloaded report data with the existing already downloaded reports for the CSV report for summary with details report type

	}

	public void selectDownloadTypeByHoverOnXLSIconButton(String expectedReportDownloadType)
			throws InterruptedException {
		//STEP_DONE: Select download type by hovering on XLS icon button
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElement(driver, PL_Commons.btnExcelDownloadAtFooter);
		getIFramePage().switchToContentFrame();
		WebElementList.selectElementFromList(driver, PL_Commons.listReportTypeDonwload, expectedReportDownloadType);
	}

	public void selectDownloadTypeByHoverOnPDFIconButton(String expectedReportDownloadType)
			throws InterruptedException {
		//STEP_DONE: Select download type by hovering on PDF icon button 
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElement(driver, PL_Commons.btnPDFDownloadAtFooter);
		getIFramePage().switchToContentFrame();
		WebElementList.selectElementFromList(driver, PL_Commons.listReportTypeDonwload, expectedReportDownloadType);

	}

	public void selectDownloadTypeByHoverOnCSVIconButton(String expectedReportDownloadType)
			throws InterruptedException {
		//STEP_DONE: Select download type by hovering on CSV icon button
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElement(driver, PL_Commons.btnExcelDownloadAtFooter);
		getIFramePage().switchToContentFrame();
		WebElementList.selectElementFromList(driver, PL_Commons.listReportTypeDonwload, expectedReportDownloadType);

	}

	public void verifyDownloadTypeByHoverOnXLSIconButton(String tooltipTitleForExcel,
			String... expectedReportDownloadTypeList) throws InterruptedException {
		//STEP_DONE: Select download type by hovering on XLS icon button
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElementAndVerifyTooltip(driver, PL_Commons.btnExcelDownloadAtFooter, "title",
				tooltipTitleForExcel);
		getIFramePage().switchToContentFrame();
		WebElementList.compareWebElmentListWithExpectedList(driver, PL_Commons.listReportTypeDonwload,
				expectedReportDownloadTypeList);

	}

	public void verifyDownloadTypeByHoverOnPDFIconButton(String tooltipTitleForPDF,
			String... expectedReportDownloadTypeList) throws InterruptedException {
		//STEP_DONE: Select download type by hovering on PDF icon button
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElementAndVerifyTooltip(driver, PL_Commons.btnPDFDownloadAtFooter, "title",
				tooltipTitleForPDF);
		getIFramePage().switchToContentFrame();
		WebElementList.compareWebElmentListWithExpectedList(driver, PL_Commons.listReportTypeDonwload,
				expectedReportDownloadTypeList);

	}

	public void verifyDownloadTypeByHoverOnCSVIconButton(String tooltipTitleForCSV,
			String... expectedReportDownloadTypeList) throws InterruptedException {
		//STEP_DONE: Select download type by hovering on CSV icon button
		getIFramePage().switchToBottomFrame();
		HoverOnElement.hoverOnElementAndVerifyTooltip(driver, PL_Commons.btnCSVDownloadAtFooter, "title",
				tooltipTitleForCSV);
		getIFramePage().switchToContentFrame();
		WebElementList.compareWebElmentListWithExpectedList(driver, PL_Commons.listReportTypeDonwload,
				expectedReportDownloadTypeList);

	}

}
