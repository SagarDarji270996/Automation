package projects.trackzee.tests.common.footer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import listeners.CustomTestListener;
import projects.trackzee.tests.common.header.TC_Filter;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.PDFComparator;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;

@Listeners({ CustomTestListener.class })
public class TC_Footer extends TestBase implements InitializePages {
	TC_Filter tc_filter = new TC_Filter();

	static {
		System.setProperty("debuggerMode", "true");
	}

	@BeforeClass()
	private void navigateToTravelSummaryScreen() throws InterruptedException {
		if (Boolean.parseBoolean(System.getProperty("debuggerMode"))) {
			return;
		}
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Travel"));
	}

	String[] jalertExpectedMessage = {
			"File is been added to Download list please check status on Cloud Download(Menu -> Cloud Download).",
			"A download request is already in progress. Please wait until it's completed before initiating another download." };

	@Test()
	public void testReportDownloadButtons() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getFooterPage().verifyTheReportDonwloadButtonAtFooter(jalertExpectedMessage);
	}

	String tooltipTitleForExcel = "Excel Download";
	String tooltipTitleForPDF = "Download in PDF format";
	String tooltipTitleForCSV = "Download in CSV format";

	String[] expectedValuesReportDownloadType = { "Summary", "Summary With Detail" };

	@Test()
	public void testReportDownloadTypeOptionsPresence() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getFooterPage().verifyTheListOfReportTypeDownloadOptions(tooltipTitleForExcel, tooltipTitleForCSV,
				tooltipTitleForPDF, expectedValuesReportDownloadType);
	}

	String reportTypeDownload = "Summary";

	@Test()
	public void testUserAbleToSelectAnyOneReportTypeDownloadOptionsForAllThreeType() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getFooterPage().verifyThatUserAbleToSelectTheAnyReportTypeDownloadAfterHover(reportTypeDownload,
				jalertExpectedMessage);

	}

	String tooltipTitleForReset = "Reset";
	String expectedScreenTitle = "Travel Summary";

	@Test()
	public void testPageRefreshFunctionality() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getFooterPage().clickRefreshButtonAndVerifyTooltipTitleAtFooter(tooltipTitleForReset, expectedScreenTitle);
	}

	@Test(enabled = true, priority = 2, dataProvider = "filterDataProviderForActivitySubModule", dataProviderClass = TC_Filter.class, description = "Travel", testName = "Travel")
	public void testFilterAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		// Note: description is sheetName in the excel and testName is pageName on the
		// overview screen

		// to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_filter.testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(filterData);
		getSoftAssert().assertAll();
	}


	@Feature("Bottom Search")
	@Test(dataProvider = "filterDataProviderForActivitySubModuleForBottomSearch")
	public void testSearchFunctionailty() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		String saerchValue = "1021729836974497";
		String searchAbleColumn = "IMEI No";

		// To delete the settings already selected
		getSettingPage().clickOnSettingIconButtonAndVerifyTooltip();
		getSettingPage().clickOnDeleteButtonAndVerify();

		// to apply filter and visit the overview screen

		// To select search box filter then search and verify on the overview screen
		getFooterPage().checkTheFooterSearchFuntionality(saerchValue, searchAbleColumn);
		TestBase.getSoftAssert().assertAll();
	}

	@DataProvider(name = "filterDataProviderForActivitySubModuleForBottomSearch")
	public Object[][] filterDataProviderForActivitySubModuleForBottomSearch(Method method) throws IOException {
		// Fetch data from Excel file using the DataProviders class
		Test testAnnotation = method.getAnnotation(Test.class);
		String sheetName = testAnnotation.description();
		String pageName = testAnnotation.testName();
		System.setProperty("pageName", pageName);

		// this line is run use-able only if the page name and sheet name is not present
		// in the test case description and testname
		sheetName = sheetName.isEmpty() || sheetName.equals("") ? System.getProperty("sheetName") : sheetName;
		pageName = pageName.isEmpty() || pageName.equals("") ? System.getProperty("pageName") : pageName;

		if (pageName.isEmpty() || pageName.equals("")) {
			System.setProperty("pageName", pageName);
		}

		System.out.println("Found description as sheet name: " + sheetName);

		String fileName = "BottomSearch";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Reports" + File.separator + "ActivitySubModule"
				+ File.separator
				+ fileName + ".xlsx";

		List<Map<String, Object>> rawData = ReadDataFromExcelFile
				.readExcelFileAndCatchDataUsingColumnNameWithSkipDataSet(filePath, sheetName);

		// Convert List<Map<String, Object>> to Object[][]
		Object[][] data = new Object[rawData.size()][1];
		for (int i = 0; i < rawData.size(); i++) {
			data[i][0] = rawData.get(i); // Each test method receives one Map<String, Object>
		}
		return data;
	}

	String recordsPerPage = "10";
	String pageNumber = "3";

	@Test()
	public void testPaginationFunctionailty() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getFooterPage().checkThePaginationFuncatinality(recordsPerPage, pageNumber);
	}

	String mostRecentDownloadFile = "Travel Summary";
	String fileDownloadDefaultAddress = "/home/uffizio/Downloads";
	String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/pdf/report/Activity";
	String oldFile = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/pdf/report/Activity/Trip_Summary_old.pdf";
	String newFile = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/pdf/report/Activity/Travel Summary.pdf";

	String[] objectSelection = { "HP Object 1.1", "HP Object 1.2", "HP Object 1.3", "HP Object 1.4" };
	String folderName = "My folder";
	String reportName = "Travel Summary";

	String globalSearchValue = "Travel";
	String expectedOverviewScreenTitle = "Travel Summary";

	String dateType = "Today";
	String customStartDateInDDMMYYYY = "1:12:2024";
	String customEndDateInDDMMYYYY = "1:31:2024";
	String dateSeperator = ":";
	boolean wantToCheckTimeRange = true;
	String searchAbleObjectName = "";
	String endTime = "00:00:PM";
	String startTime = "23:59:AM";
	String timeRangeOrDateSeperator = ":";

	String company = "HP Company 1";
	String branch = "HP Branch 1.1";
	String vehicleGroupName = "All";
	String vehicleType = "All";
	String vehilcBandName = "All";
	String vehicleModel = "All";
	String distance = "Greater Than";
	String distanceValue = "";
	String durationFormat = "hh:mm";
	String differenceFileAddress = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/pdf/report/Activity/HaveDifferences.txt";

	@Test()
	public void testToCompateTwoSummaryReportAsPDF() throws Exception {
		// TESTCASE_DONE: Check the travel summary page loading
		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnPDFIconButton("Summary");

		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);

		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		PDFComparator.comparetTwoPDFFileAndFindTheDifferences(newFile, oldFile,
				differenceFileAddress + "Have_Difference_PDF_Summary.txt");
	}

}
