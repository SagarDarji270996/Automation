package projects.trackzee.tests.reports.activity;

import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import listeners.CustomTestListener;
import projects.trackzee.tests.common.header.TC_GlobalSearch;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_StatusSummary extends TestBase implements InitializePages {

	TC_GlobalSearch tc_globalSerach = new TC_GlobalSearch();

	@Description("To select the project and navigate to the given screen")
	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		System.setProperty("mainModule", "Reports");
		System.setProperty("subModule", "Activity");
		System.setProperty("deepModule", "Status Summary");
		System.setProperty("pageTitleAtHeaderBar", "Status Summary");

		System.setProperty("globalSearchValue", "Status Summary");

		System.setProperty("pageName", "StatusSummary");
		System.setProperty("sheetName", "StatusSummary");

		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Reports", "Activity", "Status Summary"));
		getCommonPage().checkPageLoadingOnDefault(10);
	}

	@Feature("Global Search")
	@Test(enabled = true)
	public void testGlobalSearchFunctionality() throws Exception {
		// TESTCASE_DONE: Global search feature
		tc_globalSerach.testGlobalSearchFunctionality();
		TestBase.getSoftAssert().assertAll();
	}

}