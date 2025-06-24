package projects.trackzee.tests.settings.master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.DataProviders;

@Listeners({ CustomTestListener.class })
public class TC_Geofence extends TestBase implements InitializePages {
	String excelFileName = "Geofence2";
	String sheetName = "Geofence";
	int totalRecordsColumnIndex = 2;
	int statusColumnIndex = 4;
	int companyNameColumnIndex = 0;
	Map<Integer, List<String>> columnData = new HashMap<>();
	List<String> totalRecordToWriteInSheet = new ArrayList<>();
	List<Object> totalRecordToWriteInSheet2 = new ArrayList<>();

	@BeforeClass()
	public void selectProjectAndNavigateToGeofenceScreen() throws InterruptedException {
		getHomePage().selectProject("Netstar FleetAI");
		getHomePage().navigateToScreen(Arrays.asList("Settings", "Master", "Geofence"));

	}

	
	@Test(dataProvider = "geofenceDataProvider")
	public void testTotalRecordsPresenceBasedOnTheAppliedCompanyFilter(String[] excelData,
			ITestContext context) throws Exception {
		// TESTCASE_DONE: Check the object entries count based on the filter and geofence applied
		int iteration = context.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("iteration: " + iteration);
		String company = excelData[0];
		String geofenceType = excelData[1];
		String excpectedTotalRecords = excelData[3];
		if (iteration == 1) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
			getFilterPage().clickOnDeleteFilterButton();
		}

		getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
		String totalRecords = "-1";
		boolean isCompanyPresent = getFilterPage().selectCompanyCommon(company);
		if (isCompanyPresent) {
			getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			totalRecords = getFooterPage().getTotalRecordsPresent();
		} else {
			totalRecords = "Company not found";
		}
		totalRecordToWriteInSheet.add(totalRecords);

	}

	@Test(dependsOnMethods = "testTotalRecordsPresenceBasedOnTheAppliedCompanyFilter")
	public void excelFileWriter() throws IOException, InterruptedException {
		columnData.put(totalRecordsColumnIndex, totalRecordToWriteInSheet); // Writing data to column index 2
		getCommonPage().writeDataInExcelSheetWithMultipleEntriesInSingleColumn("Geofence2", "Geofence", columnData);
	}

	Map<Integer, List<String>> columnData3 = new HashMap<>();
	List<Integer> rowIndexes3 = new ArrayList<>();

	@Test(dataProvider = "geofenceDataProvider")
	public void testTotalRecordsPresenceBasedOnTheAppliedCompanyFilterAndWriteInExcelSheet(String[] excelData,
			ITestContext context)
			throws Exception {
		int iteration = context.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("iteration: " + iteration);

		String company = excelData[0];
		String geofenceType = excelData[1];
		String excpectedTotalRecords = excelData[3];

		if (iteration == 1) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
			getFilterPage().clickOnDeleteFilterButton();
		}

		getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
		String totalRecords = "-1";
		boolean isCompanyPresent = getFilterPage().selectCompanyCommon(company);

		if (isCompanyPresent) {
			getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			totalRecords = getFooterPage().getTotalRecordsPresent();
		} else {
			totalRecords = "Company not found";
		}
		// Store row index
		rowIndexes3.add(iteration);

		// Store data in List format for multiple rows
		columnData3.computeIfAbsent(totalRecordsColumnIndex, k -> new ArrayList<>()).add(totalRecords);
		columnData3.computeIfAbsent(statusColumnIndex, k -> new ArrayList<>())
				.add(excpectedTotalRecords.equalsIgnoreCase(totalRecords) ? "Pass" : "Fail");
	}

	@Test(dependsOnMethods = "testTotalRecordsPresenceBasedOnTheAppliedCompanyFilterAndWriteInExcelSheet")
	public void excelFileWriter3() throws IOException, InterruptedException {
		getCommonPage().writeDataInExcelSheetWithMultipleEntriesInMultipleColumnsBasedOnGivenRows("Geofence2",
				"Geofence", rowIndexes3,
				columnData3);
	}

	@DataProvider()
	public Object[][] geofenceDataProvider() throws IOException {
		// Fetch data from Excel file using the DataProviders class
		String[][] rawData = DataProviders.dataProviderGetDataFromExcelFile(excelFileName, sheetName);
		return rawData; // Return the fetched data to the test
	}

}
