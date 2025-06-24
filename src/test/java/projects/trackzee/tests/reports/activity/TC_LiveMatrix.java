package projects.trackzee.tests.reports.activity;

import java.util.Arrays;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import listeners.CustomTestListener;
import projects.trackzee.tests.common.header.TC_Filter;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;


@Listeners({ CustomTestListener.class })
public class TC_LiveMatrix extends TestBase implements InitializePages {
	TC_Filter tc_filter = new TC_Filter();

	@Description("To select the project and navigate to the given screen")
	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Live Matrix"));
	}

	@Test(enabled = true, dataProvider = "filterDataProviderForActivitySubModule", dataProviderClass = TC_Filter.class, description = "LiveMatrix"/*this is sheetname*/, testName = "LiveMatrix" /*This is page page*/)
	public void testFilterAndVerifyReflectionOnOverviewScreen(Map<String, Object> filterData, ITestContext iTestContext)
			throws Exception {
		//to apply filter
		int iterationCount = iTestContext.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("\n Current iteration count is: " + iterationCount + " and data set is: " + filterData);
		tc_filter.testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(filterData);
		getSoftAssert().assertAll();
	}
}