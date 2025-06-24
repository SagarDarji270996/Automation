package projects.trackzee.tests.settings.master;

import java.io.File;
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
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;
import projects.trakzee.web.projectUtility.TimeHandling;

@Listeners({ CustomTestListener.class })
public class TC_Geofence_Migrassion extends TestBase implements InitializePages {
	String excelFileName = "AUTOMATED Sheet";
	String sheetName = "Sheet2";
	int totalRecordsColumnIndex = 13;
	int statusColumnIndex = 4;
	int companyNameColumnIndex = 0;
	Map<Integer, List<String>> columnData = new HashMap<>();
	List<String> totalRecordToWriteInSheet = new ArrayList<>();
	List<Object> totalRecordToWriteInSheet2 = new ArrayList<>();

	@BeforeClass()
	public void selectProjectAndNavigateToGeofenceScreen() throws InterruptedException {
		getHomePage().selectProject("Netstar FleetAI");
		getHomePage().navigateToScreen(Arrays.asList("Settings", "General", "Alert"));
	}

	
	@Test(enabled = false, dataProvider = "geofenceDataProvider")
	public void testTotalRecordsPresenceBasedOnTheAppliedCompanyFilter(String[] excelData,
			ITestContext context) throws Exception {
		// TESTCASE_DONE: Check the object entries count based on the filter and geofence applied
		int iteration = context.getAllTestMethods()[0].getCurrentInvocationCount() + 1;
		System.out.println("iteration: " + iteration);
		String company = excelData[0];
		String geofenceType = excelData[1];
		String excpectedTotalRecords = excelData[3];
		if (iteration == 1) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
			getFilterPage().clickOnDeleteFilterButton();
		}

		getCommonPage().checkScreenLoadingTime(30);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
		String totalRecords = "-1";
		boolean isCompanyPresent = getFilterPage().selectCompanyCommon(company);
		if (isCompanyPresent) {
			// getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			totalRecords = getFooterPage().getTotalRecordsPresent();
		} else {
			totalRecords = "Company not found";
		}
		totalRecordToWriteInSheet.add(totalRecords);

	}

	@Test(enabled = false, dependsOnMethods = "testTotalRecordsPresenceBasedOnTheAppliedCompanyFilter")
	public void excelFileWriter() throws IOException, InterruptedException {
		columnData.put(totalRecordsColumnIndex, totalRecordToWriteInSheet); // Writing data to column index 2
		getCommonPage().writeDataInExcelSheetWithMultipleEntriesInSingleColumn("AUTOMATED Sheet", "Sheet1", columnData);
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
		String excpectedTotalRecords = excelData[10];

		if (iteration == 1) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
			getFilterPage().clickOnDeleteFilterButton();
		}

		getCommonPage().checkScreenLoadingTime(30);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
		String totalRecords = "-1";
		boolean isCompanyPresent = getFilterPage().selectCompanyCommon(company);

		if (isCompanyPresent) {
			// getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			totalRecords = getFooterPage().getTotalRecordsPresent();
		} else {
			totalRecords = "Company not found";
		}
		// Store row index
		rowIndexes3.add(iteration);

		// Store data in List format for multiple rows
		columnData3.computeIfAbsent(totalRecordsColumnIndex, k -> new ArrayList<>()).add(totalRecords);
//		columnData3.computeIfAbsent(statusColumnIndex, k -> new ArrayList<>())
//				.add(excpectedTotalRecords.equalsIgnoreCase(totalRecords) ? "Pass" : "Fail");
	}

	@Test(dependsOnMethods = "testTotalRecordsPresenceBasedOnTheAppliedCompanyFilterAndWriteInExcelSheet")
	public void excelFileWriter3() throws IOException, InterruptedException {
		getCommonPage().writeDataInExcelSheetWithMultipleEntriesInMultipleColumnsBasedOnGivenRows("AUTOMATED Sheet",
				"Sheet2", rowIndexes3,
				columnData3);
	}

	@DataProvider()
	public Object[][] geofenceDataProvider() throws IOException {
		// Fetch data from Excel file using the DataProviders class
		String[][] rawData = dataProviderGetDataFromExcelFile(excelFileName, sheetName);
		return rawData; // Return the fetched data to the test
	}



	public static String[][] dataProviderGetDataFromExcelFile(String excelFileName, String worksheetName)
			throws IOException {
		// DATE PROVIDER METHODS CONCEPT TO ACCESS DATA FROM THE EXCEL FILES, FOR THIS
		// IT IS ALSO CALLED TO THE ReadDataFromExcelFile CLASS AND NOTE ACTUAL DATA
		// READING DONE AT(ReadDataFromExcelFile)

		// TO TRACK THE CALLER METHOD NAME
		ReadDataFromExcelFile rdfef = null;
		System.out
				.println("XLSX workbook name: " + excelFileName.trim() + " and worksheet name " + worksheetName.trim());
		try {
			String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
					+ File.separator + "Migrassion"
					+ File.separator + excelFileName.trim() + ".xlsx";
			// String filePath = "/home/uffizio/Eclipse
			// Project/taskeye_watm/src/test/resources/trakzee/DataProviderXLS/CheckNavigationList.xlsx";
			System.out.println("Excel file path: " + filePath);
			rdfef = new ReadDataFromExcelFile(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int rownum = rdfef.getRowCount(worksheetName.trim()); // edfef = read data from excel file
		int colcount = rdfef.getCellCount(worksheetName.trim(), 1);
		System.out.println("Number of rows is: " + rownum + "  and column is: " + colcount);

		String UserData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				UserData[i - 1][j] = rdfef.getCellData(worksheetName.trim(), i, j);
			}
		}
		return UserData;
	}
}
