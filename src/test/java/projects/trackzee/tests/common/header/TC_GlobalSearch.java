package projects.trackzee.tests.common.header;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_GlobalSearch extends TestBase implements InitializePages {


	@BeforeClass
	private void navigateToGlobalSearchScreen() throws InterruptedException {
		System.setProperty("mainModule", "Reports");
		System.setProperty("subModule", "Activity");
		System.setProperty("deepModule", "Today Activity");
		System.setProperty("pageTitleAtHeaderBar", "Today Activity");
		System.setProperty("globalSearchValue", "Today Activity");

		System.setProperty("pageName", "TodayActivity");
		System.setProperty("sheetName", "TodayActivity");


		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Today Activity");
	}

	@Test()
	public void testGlobalSearchFunctionality() throws InterruptedException {
		//TESTCASE_DONE: Test global search functionality
		getCommonPage().checkPageLoadingOnDefault(30);
		getTravelPage().verifyThatUserAbleToSeeSearchBoxAndAbleToSearchAndOnSearchItNavigateToCorrectScreen(
				System.getProperty("globalSearchValue"), System.getProperty("pageTitleAtHeaderBar"));
		getCommonPage().checkPageLoadingOnDefault(30);
		getCommonPage().checkPageTitlePresence(System.getProperty("pageTitleAtHeaderBar"), 30);
	}

}