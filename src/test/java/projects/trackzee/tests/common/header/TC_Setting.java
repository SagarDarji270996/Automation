package projects.trackzee.tests.common.header;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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
public class TC_Setting extends TestBase implements InitializePages {

	@BeforeClass
	private void navigateToSettingsScreen() throws InterruptedException {
		System.setProperty("pageName", "TodayActivity");
		System.setProperty("sheetName", "TodayActivity");

		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Today Activity");
	}

	String settingPageTitle = "Settings";
	String listOfColumnTitleCommaSeperated = "Column,Showable,Printable,Sortable,Sort Priority,Sorting";
	String listOfRowEntriesTitleCommaSeperated = "Company,Branch,Object,Vehicle Brand,Vehicle Model,Driver,IMEI No,Start Location,Start Odometer,Distance,Running V/S Stop,Running,Idle,Stop,Inactive,Duration,Work Duration,Working Days,Max Stoppage,No of Idle,Speed,AVG,MAX,Cost based on,Distance,Duration,Over speed,Alert(s),Alert(s),Approx Toll Cost,End Odometer,End Location,Playback,Logo";
	String searchKey = "Branch";
	int searchKeyColumnIndex = 1;
	Double indexForDragAndDrop = Double.parseDouble("5");
	int[] actionColumnIndexes = { 2, 3, 4 };

	@Test()
	public void testSettingFunctionality() throws InterruptedException {
		//TESTCASE_DONE: Test setting functionality

		getSettingPage().verifyThatUserAbleHandleTheSettingFunctionality(settingPageTitle, indexForDragAndDrop,
				listOfColumnTitleCommaSeperated, listOfRowEntriesTitleCommaSeperated, searchKey, searchKeyColumnIndex,
				actionColumnIndexes);
	}

	@Description("Data driven testing")
	@Test(dataProvider = "settingDataProviderForActivitySubModule")
	public void testSettingFunctionalityActivitySubModuleUsingExcelSheet(Map<String, Object> filterData)
			throws Exception {
		//TESTCASE_DONE: Test filter functionality
		boolean isClickedOnActionButton = getSettingPage().settingFunctionalityForAllPagesBasedOnField(filterData);
		if (isClickedOnActionButton) {
			System.out.println("Write here once setting is applied");
		}
	}

	@Description("This is used to provide data for: testSettingFunctionalityActivitySubModuleUsingExcelSheet")
	@DataProvider(name = "settingDataProviderForActivitySubModule")
	public Object[][] settingDataProviderForActivitySubModuleNew(Method method) throws IOException {
		// Fetch data from Excel file using the DataProviders class
		Test testAnnotation = method.getAnnotation(Test.class);
		String sheetName = testAnnotation.description();
		String pageName = testAnnotation.testName();

		//this line is run use-able only if the page name and sheet name is not present in the test case description and testname
		sheetName = sheetName.isEmpty() || sheetName.equals("") ? System.getProperty("sheetName") : sheetName;
		pageName = pageName.isEmpty() || pageName.equals("") ? System.getProperty("pageName") : pageName;

		if (pageName.isEmpty() || pageName.equals("")) {
			System.setProperty("pageName", pageName);
		}

		System.out.println("Found description as sheet name: " + sheetName);

		String fileName = "Setting";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Reports" + File.separator + "ActivitySubModule" + File.separator + fileName
				+ ".xlsx";
		System.out.println("XLS File path: " + filePath);
		List<Map<String, Object>> rawData = ReadDataFromExcelFile
				.readExcelFileAndCatchDataUsingColumnNameWithSkipDataSet(filePath, sheetName);

		// Convert List<Map<String, Object>> to Object[][]
		Object[][] data = new Object[rawData.size()][1];
		for (int i = 0; i < rawData.size(); i++) {
			data[i][0] = rawData.get(i); // Each test method receives one Map<String, Object>
		}
		return data;
	}

	String[] expectedShowAbleListCommaSeparated = { "Company", "Branch", "Object", "Vehicle Brand", "Vehicle Model",
			"Driver", "IMEI No" };
	
	@Test()
	public void testOverviewScreenColumnTitleNameBasedOnSettingShowableSelected() throws InterruptedException {
		//TESTCASE_DONE: Test setting functionality
		getSettingPage().verifyTheListOfColumnNamePresentOnOverviewScreenBasedOnTheAppliedSettingsForShowable(
				settingPageTitle, expectedShowAbleListCommaSeparated);

	}

}