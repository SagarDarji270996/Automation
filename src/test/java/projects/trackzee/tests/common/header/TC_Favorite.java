package projects.trackzee.tests.common.header;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.projectUtility.ProjectUtility;

@Listeners({ CustomTestListener.class })
public class TC_Favorite extends TestBase implements InitializePages {
	private ProjectUtility pu = new ProjectUtility();
	@BeforeClass
	private void navigateToTravelSummaryScreen() throws InterruptedException {
		System.setProperty("pageTitleAtHeaderBar", "Travel Summary");
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Travel");
	}

	@Feature("Favorite")
	@Test(enabled = true)
	public void testFavoriteFunctionality() throws Exception {
		String folderName = "My folder";
		String reportName = System.getProperty("pageTitleAtHeaderBar", "Travel Summary");
		// TESTCASE_DONE: Check the travel summary page loading
		try {
			getTravelPage().checkTravelSummaryPageLoadingOnDefault(30);
			getTravelPage().verifyThatUserAbleToCreatedANewFavoriteFolder(folderName);
			getTravelPage().verifyThatUserAbleToRemoveTheFavoriteFolder(folderName);
			getTravelPage().verifyThatUserAbleToCreatedANewFavoriteFolder(folderName);
			if (System.getProperty("pageTitleAtHeaderBar").equals("Today Activity")) {
				Thread.sleep(5000);
			}
			getTravelPage()
					.verifyThatOnceUserAddedTheReportsInFavoriteFolderThenItShoudlBePrsentOnTheRightHandSideFavoirteButton(
							folderName, reportName);

			// Navigation again because favorite icon disabled in between
			getHomePage().navigateToScreen(System.getProperty("mainModule", "Reports"),
					System.getProperty("subModule", "Activity"),
					System.getProperty("deepModule", "Travel"));
			getTravelPage().checkTravelSummaryPageLoadingOnDefault(30);

			if (System.getProperty("pageTitleAtHeaderBar").equals("Today Activity")) {
				Thread.sleep(5000);
			}
			getTravelPage().verifyThatUserAbleToDeleteTheFolderFromTheRightHandSideFavoriteButton(folderName,
					reportName);
		} catch (Exception e) {
			getSoftAssert().fail(e.getMessage());
		}
	}

}