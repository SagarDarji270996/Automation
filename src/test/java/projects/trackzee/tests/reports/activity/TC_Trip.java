package projects.trackzee.tests.reports.activity;

import java.util.Arrays;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import projects.trackzee.tests.common.header.TC_Favorite;
import projects.trackzee.tests.common.header.TC_Filter;
import projects.trackzee.tests.common.header.TC_GlobalSearch;
import projects.trackzee.tests.common.header.TC_ScheduleReport;
import projects.trackzee.tests.common.header.TC_Setting;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.ExcelComparator;
import projects.trakzee.web.projectUtility.PDFComparator;

@Listeners({ AllureTestNg.class })
public class TC_Trip extends TestBase implements InitializePages {
	TC_Filter tc_filter = new TC_Filter();
	TC_GlobalSearch tc_globalSerach = new TC_GlobalSearch();

	TC_Setting tc_setting = new TC_Setting();
	TC_Favorite tc_favorite = new TC_Favorite();

	TC_ScheduleReport tc_scheduleReport = new TC_ScheduleReport();
	String dateSeparator = ":";
	String mostRecentDownloadFile = "Travel Summary";
	String fileDownloadDefaultAddress = "/home/uffizio/Downloads";
	String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
	String oldFile = System.getProperty("user.dir")
			+ "/src/test/resources/trakzee/pdf/report/activity/travel/Travel_Summary_Old.pdf";
	String newFile = System.getProperty("user.dir") + "/src/test/resources/trakzee/pdf/report/activity/travel/"
			+ mostRecentDownloadFile + ".pdf";

	String[] objectSelection = { "HP Object 1.1", "HP Object 1.2", "HP Object 1.3", "HP Object 1.4" };

	String globalSearchValue = "Travel";
	String expectedOverviewScreenTitle = "Travel Summary";

	String dateType = "Custom Range";
	String customStartDateInDDMMYYYY = "01:12:2024";
	String customEndDateInDDMMYYYY = "31:12:2024";
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
			+ "/src/test/resources/trakzee/reportCompare/activity/travel/";

	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		System.setProperty("mainModule", "Reports");
		System.setProperty("subModule", "Activity");
		System.setProperty("deepModule", "Trip Summary");
		System.setProperty("pageTitleAtHeaderBar", "Trip Summary");

		System.setProperty("globalSearchValue", "Trip");

		System.setProperty("pageName", "Trip");
		System.setProperty("sheetName", "Trip");

		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Trip"));
	}

	@Test(enabled = true, dataProvider = "filterDataProviderForActivitySubModule", dataProviderClass = TC_Filter.class, description = "Trip"/*this is sheetname*/, testName = "Trip" /*This is page page*/)
	public void testFilterAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		//to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_filter.testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(filterData);
		getSoftAssert().assertAll();
	}

	@Test(enabled = true, priority = 1, dataProvider = "scheduleReportDataProviderForActivitySubModule", dataProviderClass = TC_ScheduleReport.class, description = "Trip", /*this is sheetname*/ testName = "Trip"/*This is page page*/)
	public void testScheduleReportFunctiionality(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_scheduleReport.testSchedulreReportFunctionalityForActivitySubModuleUsingExcelSheet(filterData);
		getSoftAssert().assertAll();
	}

	@Test(enabled = true, dataProvider = "settingDataProviderForActivitySubModule", dataProviderClass = TC_Setting.class)
	public void testSettingAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData,
			ITestContext iTestContext) throws Exception {
		// to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_setting.testSettingFunctionalityActivitySubModuleUsingExcelSheet(filterData);
		getSoftAssert().assertAll();
	}

	@Feature("Global Search")
	@Test(enabled = true)
	public void testGlobalSearchFunctionality() throws Exception {
		// TESTCASE_DONE: Global search feature
		tc_globalSerach.testGlobalSearchFunctionality();
		TestBase.getSoftAssert().assertAll();
	}

	@Feature("Favorite")
	@Test(enabled = true)
	public void testFavoriteFunctionality() throws Exception {
		// TESTCASE_DONE: Today activity favorite feature
		tc_favorite.testFavoriteFunctionality();
		getSoftAssert().assertAll();
	}

	@Test(enabled = false)
	public void testCompareTwoSummaryReportAsPDF() throws Exception {
		// TESTCASE_DONE: Check the travel summary report when downloaded as pdf
		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_Old.pdf";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_New.pdf";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnPDFIconButton("Summary");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);

		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		PDFComparator.comparetTwoPDFFileAndFindTheDifferences(newFile, oldFile,
				differenceFileAddress + "Have_Difference_PDF_Summary.txt");
	}

	@Test(enabled = false)
	public void testToCompareTwoSummaryWithDetailsReportAsPDF() throws Exception {
		// TESTCASE_DONE: Check the travel summary with details report when downloaded as pdf

		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_Old.pdf";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_New.pdf";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnPDFIconButton("Summary With Detail");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);

		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_With_Details_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		PDFComparator.comparetTwoPDFFileAndFindTheDifferences(newFile, oldFile,
				differenceFileAddress + "Have_Difference_PDF_Summary_With_Details.txt");
	}

	@Test(enabled = false)
	public void testCompareTwoSummaryReportAsXLS() throws Exception {
		// TESTCASE_DONE: Check the travel summary report when downloaded as xls

		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_Old.xlsx";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_New.xlsx";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnXLSIconButton("Summary");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);

		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		//to compare the file data
		ExcelComparator.compareTwoExcelSheet(newFile, oldFile,
				differenceFileAddress + "Have_Difference_XLS_Summary_With_Details.txt");
	}

	@Test(enabled = false)
	public void testCompareTwoSummaryWithDetailsReportAsXLS() throws Exception {
		// TESTCASE_DONE: Check the travel summary with details report when downloaded as xls 

		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_Old.xlsx";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_New.xlsx";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnXLSIconButton("Summary With Detail");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);
		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_With_Details_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		//to compare the file data
		ExcelComparator.compareTwoExcelSheet(newFile, oldFile,
				differenceFileAddress + "Have_Difference_XLS_Summary_With_Details.txt");
	}

	@Test(enabled = false)
	public void testCompareTwoSummaryReportAsCSV() throws Exception {
		// TESTCASE_DONE: Check the travel summary report when downloaded as csv

		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_Old.csv";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_New.csv";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnCSVIconButton("Summary");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);

		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		//to compare the file data
		ExcelComparator.compareTwoExcelSheet(newFile, oldFile,
				differenceFileAddress + "Have_Difference_CSV_Summary_With_Details.txt");
	}

	@Test(enabled = false)
	public void testCompareTwoSummaryWithDetailsReportAsCSV() throws Exception {
		// TODO: Check the travel summary with details report when downloaded as csv

		String projectFolderAddressToPlaceReports = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/";
		String oldFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_Old.csv";
		String newFile = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_With_Details_New.csv";

		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getFooterPage().selectDownloadTypeByHoverOnCSVIconButton("Summary With Detail");
		getFooterPage().verifyDownloadedReportAfterClickFromFooter(mostRecentDownloadFile);
		getFooterPage().updateFileName(fileDownloadDefaultAddress, "Travel_Summary_With_Details_New");
		getFooterPage().moveDownloadedFileFromDefaultDownloadToWithinProject(fileDownloadDefaultAddress,
				projectFolderAddressToPlaceReports);

		//to compare the file data
		ExcelComparator.compareTwoExcelSheet(newFile, oldFile,
				differenceFileAddress + "Have_Difference_CSV_Summary_With_Details.txt");
	}

	String expectedObjectThatSelectedInFilterListCommaSeparated = String.join(",", objectSelection);
	String columnNameAttribute = "vehicle_information";

	@Test(enabled = false)
	public void testListOfObjectPresentOnOverviewScreenAsPerSelectedInsideFilter() throws InterruptedException {
		getTravelPage().verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(dateType, customStartDateInDDMMYYYY,
				customEndDateInDDMMYYYY, dateSeperator, wantToCheckTimeRange, company, branch, vehicleGroupName,
				vehicleType, vehilcBandName, vehicleModel, distance, distanceValue, durationFormat, startTime, endTime,
				searchAbleObjectName, timeRangeOrDateSeperator, objectSelection);
		getTravelPage().verifyTheColumnValuesBasedOnTheGivenTableHeaderName(
				expectedObjectThatSelectedInFilterListCommaSeparated, columnNameAttribute);
	}

}