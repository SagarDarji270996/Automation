package projects.trackzee.tests.reports.activity;

import java.util.Arrays;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import listeners.CustomTestListener;
import projects.trackzee.tests.common.header.TC_Favorite;
import projects.trackzee.tests.common.header.TC_Filter;
import projects.trackzee.tests.common.header.TC_GlobalSearch;
import projects.trackzee.tests.common.header.TC_Setting;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_ObjectBatteryStatus extends TestBase implements InitializePages {
	TC_Filter tc_filter = new TC_Filter();
	TC_GlobalSearch tc_globalSerach = new TC_GlobalSearch();

	TC_Setting tc_setting = new TC_Setting();
	TC_Favorite tc_favorite = new TC_Favorite();

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

	@Description("To select the project and navigate to the given screen")
	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		System.setProperty("mainModule", "Reports");
		System.setProperty("subModule", "Activity");
		System.setProperty("deepModule", "Vehicle Battery Status");
		System.setProperty("pageTitleAtHeaderBar", "Vehicle Battery Status");
		System.setProperty("globalSearchValue", "Vehicle Battery Status");

		System.setProperty("pageName", "VehicleBatteryStatus");
		System.setProperty("sheetName", "ObjectBatteryStatus");

		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Vehicle Battery Status"));
		getCommonPage().checkPageLoadingOnDefault(10);
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

	@Test(enabled = true, dataProvider = "filterDataProviderForActivitySubModule", dataProviderClass = TC_Filter.class, description = "ObjectBatteryStatus"/*this is sheetname*/, testName = "ObjectBatteryStatus" /*This is page page*/)
	public void testFilterAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		//to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_filter.testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(filterData);
		getSoftAssert().assertAll();
	}

	@Test(enabled = true, dataProvider = "settingDataProviderForActivitySubModule", dataProviderClass = TC_Setting.class)
	public void testSettingAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData,
			ITestContext iTestContext) throws Exception {
		//to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_setting.testSettingFunctionalityActivitySubModuleUsingExcelSheet(filterData);
		getSoftAssert().assertAll();
	}

	String settingPageTitle = "Settings";
	String listOfColumnTitleCommaSeperated = "Column,Showable,Printable,Sortable,Sort Priority,Sorting";
	String listOfRowEntriesTitleCommaSeperated = "Status,Company,Branch,Object,Object Brand,Object Model,Driver,Distance,Running,Idle,Stop,Inactive,Work Hour,Duration,Speed,Odometer,Location,Google Location,Address,Geofence,Data Time,Alert,IGN,PWR,AC,GPS,Battery %,Fuel,Temperature,Logo";
	String searchKey = "Branch";
	int searchKeyColumnIndex = 1;
	Double indexForDragAndDrop = Double.parseDouble("5");
	int[] actionColumnIndexes = { 2, 3, 4 };

	@Feature("Settings")
	@Test(enabled = false)
	public void testSettingFunctionality() throws InterruptedException {
		//TESTCASE_DONE: Test setting functionality
		getSettingPage().verifyThatUserAbleHandleTheSettingFunctionality(settingPageTitle, indexForDragAndDrop,
				listOfColumnTitleCommaSeperated, listOfRowEntriesTitleCommaSeperated, searchKey, searchKeyColumnIndex,
				actionColumnIndexes);
		getSoftAssert().assertAll();
	}



}