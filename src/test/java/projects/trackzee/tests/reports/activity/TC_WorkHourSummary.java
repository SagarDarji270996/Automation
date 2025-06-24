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
import projects.trackzee.tests.common.header.TC_ScheduleReport;
import projects.trackzee.tests.common.header.TC_Setting;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_WorkHourSummary extends TestBase implements InitializePages {
	TC_Filter tc_filter = new TC_Filter();
	TC_GlobalSearch tc_globalSerach = new TC_GlobalSearch();
	TC_Favorite tc_favorite = new TC_Favorite();
	TC_Setting tc_setting = new TC_Setting();
	TC_ScheduleReport tc_scheduleReport = new TC_ScheduleReport();

	@Description("To select the project and navigate to the given screen")
	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		System.setProperty("mainModule", "Reports");
		System.setProperty("subModule", "Activity");
		System.setProperty("deepModule", "Work Hour Summary");
		System.setProperty("pageTitleAtHeaderBar", "Work Hour Summary");

		System.setProperty("globalSearchValue", "Work Hour Summary");

		System.setProperty("pageName", "WorkHourSummary");
		System.setProperty("sheetName", "WorkHourSummary");
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Work Hour Summary"));
	}

	@Test(enabled = true, dataProvider = "filterDataProviderForActivitySubModule", dataProviderClass = TC_Filter.class, description = "WorkHourSummary"/*this is sheetname*/, testName = "WorkHourSummary" /*This is page page*/)
	public void testFilterAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		//to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_filter.testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(filterData);
		getSoftAssert().assertAll();
	}

	@Test(enabled = true, priority = 1, dataProvider = "scheduleReportDataProviderForActivitySubModule", dataProviderClass = TC_ScheduleReport.class, description = "WorkHourSummary", /*this is sheetname*/ testName = "WorkHourSummary"/*This is page page*/)
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

}