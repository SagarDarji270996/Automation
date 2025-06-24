package projects.trackzee.tests.settings.general;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;

@Listeners({ CustomTestListener.class })
public class TC_Admin extends TestBase implements InitializePages {

	@BeforeClass()
	public void selectProjectAndNavigateToTravelSummaryReports() throws InterruptedException {
		System.setProperty("mainModule", "Settings");
		System.setProperty("subModule", "General");
		System.setProperty("deepModule", "Admin");
		System.setProperty("pageTitleAtHeaderBar", "Admin");

		System.setProperty("pageName", "Admin");
		System.setProperty("sheetName", "MyAccountTab");

		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Settings", "General", "Admin"));
		getCommonPage().checkPageLoadingOnDefault(30);
	}

	@Test(dataProvider = "adminDataProviderForAdminDeepModule")
	public void testAdminFunctionalityForAdminDeepModuleUsingExcelSheetWithReflection(Map<String, Object> filterData)
			throws Exception {
		// TESTCASE_DONE: Test admin my account tab functionality
		boolean isClickedOnActionButton = getAdminPage()
				.adminFunctionalityForAllTabsAndAllPagesBasedOnField(filterData);
		if (isClickedOnActionButton) {
			getCommonPage().checkPageLoadingOnDefault(60);
			String totalRecordsValue = getFooterPage().getTotalRecordsPresent();
			int totalRecords = Integer.parseInt(totalRecordsValue);
			if (totalRecords != 0) {
				// getFilterPage().verifyTheFilterReflectionAsPerTheSelection(filterData);
			} else {
				System.out.println("No records found for the applied filter");
			}
		}
		TestBase.getSoftAssert().assertAll();
	}

	@DataProvider(name = "adminDataProviderForAdminDeepModule")
	public Object[][] filterDataProviderForActivitySubModuleNew(Method method) throws IOException {
		// Fetch data from Excel file using the DataProviders class
		Test testAnnotation = method.getAnnotation(Test.class);
		String sheetName = testAnnotation.description();
		String pageName = testAnnotation.testName();

		// this line is run use-able only if the page name and sheet name is not present
		sheetName = sheetName.isEmpty() || sheetName.equals("") ? System.getProperty("sheetName") : sheetName;
		pageName = pageName.isEmpty() || pageName.equals("") ? System.getProperty("pageName") : pageName;

		if (pageName.isEmpty() || pageName.equals("")) {
			System.setProperty("pageName", pageName);
		}

		System.setProperty("pageName", pageName);
		System.out.println("Found description as sheet name: " + sheetName);

		String fileName = "Admin";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Settings" + File.separator + "General" + File.separator + fileName + ".xlsx";

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
