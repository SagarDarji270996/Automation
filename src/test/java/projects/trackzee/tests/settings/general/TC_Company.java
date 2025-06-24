package projects.trackzee.tests.settings.general;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trackzee.tests.settings.master.TC_Geofence;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.DataProviders;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;

@Listeners({ CustomTestListener.class })
public class TC_Company extends TestBase implements InitializePages {
	@BeforeClass()
	public void selectProjectAndNavigateToGeofenceScreen() throws InterruptedException {
		getHomePage().selectProject("Netstar FleetAI");
		getHomePage().navigateToScreen(Arrays.asList("Settings", "General", "Company"));
		getSoftAssert().assertAll();
	}

	@Test(dataProvider = "alertDataProvider")
	public void testTotalRecordsPresenceBasedOnTheAppliedCompanyFilter(String[] excelData,
			ITestContext context) throws Exception {
		// TESTCASE_DONE: Check the object entries count based on the filter and geofence applied

		int iteration = context.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("iteration: " + iteration);
		String company = excelData[0];
		String state = excelData[1];
		String excpectedTotalRecords = excelData[3];
		if (iteration == 1) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
			getFilterPage().clickOnDeleteFilterButton();
		}

		getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
		String totalRecords = "-1";
		boolean isCompanyPresent = getFilterPage().selectCompanyCommon(company);
		if (isCompanyPresent) {
			getFilterPage().selectStateCommon(state);
			getFilterPage().clickOnAppyFilterButtonRHS();
			totalRecords = getFooterPage().getTotalRecordsPresent();
		} else {
			totalRecords = "Company not found";
		}

		getCommonPage().writeDataInExcelSheet("Alerts", "Alerts", iteration, 2, 4, totalRecords,
				excpectedTotalRecords, 0);
	}

	@DataProvider()
	public Object[][] alertDataProvider() throws IOException {
		// Fetch data from Excel file using the DataProviders class
		String[][] rawData = DataProviders.dataProviderGetDataFromExcelFile("Alerts", "Alerts");
		return rawData; // Return the fetched data to the test
	}

}