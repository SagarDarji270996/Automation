package projects.trackzee.tests.common.header;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;

@Listeners({ CustomTestListener.class })
public class TC_Filter extends TestBase implements InitializePages {

	@BeforeClass
	private void navigateToFilterScreen() throws InterruptedException {
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Trip");
	}

	String[] objectSelection = { "" };
	String folderName = "My folder";
	String reportName = "Travel Summary";

	String globalSearchValue = "Travel";
	String expectedOverviewScreenTitle = "Travel Summary";

	String dateType = "Today";
	String customStartDateInDDMMYYYY = "12:11:2024";
	String customEndDateInDDMMYYYY = "11:12:2024";
	String dateSeperator = ":";
	boolean wantToCheckTimeRange = false;
	String searchAbleObjectName = "";
	String endTime = "08:56:PM";
	String startTime = "05:48:AM";
	String timeRangeOrDateSeperator = ":";

	String company = "All";
	String branch = "All";
	String vehicleGroupName = "All";
	String vehicleType = "All";
	String vehilcBandName = "All";
	String vehicleModel = "All";
	String distance = "Greater Than";
	String distanceValue = "";
	String durationFormat = "hh:mm";


	@Test(dataProvider = "filterDataProviderForActivity")
	public void testFilterFunctionalityForActivityUsingExcelSheet(Map<String, Object> filterData) throws Exception {
		//TESTCASE_DONE: Test filter functionality
		getFilterPage().filterFunctionalityForAllPages("TodayActivity", filterData);
	}

	@DataProvider(name = "filterDataProviderForActivity")
	public Object[][] filterDataProviderForActivity() throws IOException {
		// Fetch data from Excel file using the DataProviders class
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Filter" + File.separator + "Reports" + File.separator + "Activity" + File.separator
				+ "Activity" + ".xlsx";
		List<Map<String, Object>> rawData = ReadDataFromExcelFile.readExcelFileAndCatchDataUsingColumnName(filePath,
				"TodayActivity");

		// Convert List<Map<String, Object>> to Object[][]
		Object[][] data = new Object[rawData.size()][1];
		for (int i = 0; i < rawData.size(); i++) {
			data[i][0] = rawData.get(i); // Each test method receives one Map<String, Object>
		}
		return data;

	}

	@Description("Test travel history filter")
	@Test(dataProvider = "filterDataProviderForTravelHistory")
	public void testFilterFunctionalityForTravelHistory(Map<String, Object> filterData) throws Exception {
		//TESTCASE_DONE: Test filter functionality
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
		getFilterPage().selectCompany((String) filterData.get("company"));
		getFilterPage().selectBranch((String) filterData.get("branch"));
		// Right-hand side panel
		getFilterPage().selectDateSelection((String) filterData.get("dateType"),
				(String) filterData.get("customStartDate"), (String) filterData.get("customEndDate"),
				(String) filterData.get("dateSeparator"));
		// Time range selection
		if (Boolean.parseBoolean(filterData.getOrDefault("wantToCheckTimeRange", "false").toString())) {
			getFilterPage().checkTimeRangeAtFilter();
			getFilterPage().selectStartTimeRangeAtFilter((String) filterData.get("startTime"),
					(String) filterData.get("timeSeparator"));
			getFilterPage().selectEndTimeRangeAtFilter((String) filterData.get("endTime"),
					(String) filterData.get("timeSeparator"));
		}

		// Object selection (Updated to use a List)
		getFilterPage().relaodObjectUnderSearchObjectField();
		getFilterPage().searchObjectSelection((String) filterData.get("searchableObjectName"));

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(filterData.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			getFilterPage().selectObjectFromThreeLevelCheckbox(objectSelection);
		}
		getFilterPage().clickOnAppyFilterButton();
	}

	@DataProvider(name = "filterDataProviderForTravelHistory")
	public Object[][] filterDataProviderForTravelHistory() {
		Map<String, Object> data1 = new HashMap<>();
		data1.put("company", "HP Company");
		data1.put("branch", "HP Branch 1");
		data1.put("dateType", "Today");
		data1.put("dateSeperator", ":");
		data1.put("customStartDate", "1:12:2024");
		data1.put("customEndDate", "15:1:2025");
		data1.put("wantToCheckTimeRange", "true");
		data1.put("timeSeparator", ":");
		data1.put("startTime", "08:05:AM");
		data1.put("endTime", "06:06:PM");
		data1.put("searchableObjectName", "HP FMC 125 Object");
		data1.put("objectSelection", "HP FMC 125 Object");
		return new Object[][] { { data1 } };
	}


	@Test(dataProvider = "filterDataProviderForActivitySubModule")
	public void testFilterFunctionalityForActivitySubModuleUsingExcelSheetWithReflection(Map<String, Object> filterData)
			throws Exception {
		//TESTCASE_DONE: Test filter functionality
		boolean isClickedOnActionButton = getFilterPage().filterFunctionalityForAllPagesBasedOnField(filterData);
		if (isClickedOnActionButton) {
			if (!(System.getProperty("pageName").equalsIgnoreCase("LiveMatrix"))) {
				getCommonPage().checkPageLoadingOnDefault(60);
				String totalRecordsValue = getFooterPage().getTotalRecordsPresent();
					int totalRecords = Integer.parseInt(totalRecordsValue);
					int recordsPerPage = 0;
					if (totalRecords != 0) {
						if (totalRecords > recordsPerPage) {
							getFilterPage().verifyTheFilterReflectionAsPerTheSelection(filterData);
						}
					} else {
						System.out.println("No records found for the applied filter");
					}
			} else {
				getFilterPage().verifyTheFilterReflectionAsPerTheSelection(filterData);
			}
		}
		
	}

	@DataProvider(name = "filterDataProviderForActivitySubModule")
	public Object[][] filterDataProviderForActivitySubModuleNew(Method method) throws IOException {
		// Fetch data from Excel file using the DataProviders class
		Test testAnnotation = method.getAnnotation(Test.class);
		String sheetName = testAnnotation.description();
		String pageName = testAnnotation.testName();
		System.setProperty("pageName", pageName);
		System.out.println("Found description as sheet name: " + sheetName);

		String fileName = "Activity";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Reports" + File.separator + "Filter" + File.separator + "Activity" + File.separator
				+ fileName + ".xlsx";

		List<Map<String, Object>> rawData = ReadDataFromExcelFile
				.readExcelFileAndCatchDataUsingColumnNameWithSkipDataSet(filePath, sheetName);

		// Convert List<Map<String, Object>> to Object[][]
		Object[][] data = new Object[rawData.size()][1];
		for (int i = 0; i < rawData.size(); i++) {
			data[i][0] = rawData.get(i); // Each test method receives one Map<String, Object>
		}
		return data;
	}



}