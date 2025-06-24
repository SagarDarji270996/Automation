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
import org.testng.asserts.SoftAssert;

import listeners.CustomTestListener;
import projects.trackzee.tests.gmail.TC_Gmail;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;
import projects.trakzee.web.projectUtility.StringHandling;

@Listeners({ CustomTestListener.class })
public class TC_ScheduleReport extends TestBase implements InitializePages {

	@BeforeClass
	private void navigateToScheduleReportScreen() throws InterruptedException {
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Travel");
	}

	@Test(dataProvider = "scheduleReportDataProviderForActivitySubModule")
	public void testSchedulreReportFunctionalityForActivitySubModuleUsingExcelSheet(
			Map<String, Object> filterData) throws Exception {
		//TESTCASE_DONE: Test filter functionality

		boolean isClickedOnActionButtonAndScheduleReportStatusIsActive = getSchedulePage()
				.schdeulReportFunctionalityForAllPagesBasedOnField(filterData);

		//This thread is responsible for the scheduled report cross verification on the gmail
		if (isClickedOnActionButtonAndScheduleReportStatusIsActive) {
			System.out.println("✅ Clicked on action button and found schedule report status as active");
			SoftAssert mainSoftAssert = TestBase.getSoftAssert();
			//TC_Gmail gmailThread = new TC_Gmail(mainSoftAssert);
			createThread(TC_Gmail.class, mainSoftAssert);
			//createThread(TC_Gmail.class);
		} else {
			System.out.println(
					"❌ Either not clicked on the schedule report button or schedule report status is incative");
		}

		if (joinCreatedThread()) {
			Map<String, Object> args = StringHandling.convertKeysToCamelCase(filterData);
			//to delete the schedule reports
			getSchedulePage().clickOnCreatedScheduleReportText((String) args.get("reportName"));
			boolean isScheduleReportDeleted = getSchedulePage().clickOnDeleteButton();
			if (isScheduleReportDeleted) {
				System.out.println("✅ Schedule report deleted successfully");
				boolean isStillPresent = getSchedulePage()
						.verifyDeletedScheduleReportAbsentInList((String) args.get("reportName"));
				if (isStillPresent) {
					System.out.println("✅ Duplicate schedule report present");
				}
			}
		}


	}

	@DataProvider(name = "scheduleReportDataProviderForActivitySubModule")
	public Object[][] scheduleReportDataProviderForActivitySubModuleNew(Method method) throws IOException {
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

		String fileName = "ActivitySubModule";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
				+ File.separator + "Reports" + File.separator + "ScheduleReport" + File.separator + fileName + ".xlsx";
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

	String startTimeScheduleReport = "04:06:PM";
	String endTimeScheduleReport = "03:08:AM";
	String timeHourMinuteSeperator = ":";
	String reportGenerationTime = "00:04";
	String timezone = "Asia/Kabul(UTC+04:30)";
	String fileFormat = "PDF";
	String reportType = "Summary";
	String actionType = "SFTP";
	String reportNameScheduleReport = "AutomationTesting";

	String email = "sanjiv.koiri@uffizio.com";
	String emailSubject = "Testing";
	String emailMessage = "Testing";
	String hostAddress = "127.05.201.0";
	String port = "5225";
	String username = "sanjiv.koiri";
	String password = "Admin@123";
	String folderNameScheduleReport = "Schedule Report";
	String plateform = "Amazon S3";
	String accessKeyId = "SDFFDSFSDFF";
	String accessKey = "SDFKFSFFSFDfFD";
	String region = "Testing";
	String bucketName = "sanjiv.koiri";
	String folderNameSFTP = "FolderSFTP";

	String gateway = "sanjiv.koiri";
	String recipientNumber = "FolderSFTP";

	@Test()
	public void testScheduleReportFunctionality() throws InterruptedException {
		//TESTCASE_DONE: Test schedule report functionality
		getSchedulePage().verifyThatUserAbleToCreateShceduleReportWithoutAdvanceConfiguration(startTimeScheduleReport,
				endTimeScheduleReport, timeHourMinuteSeperator, reportGenerationTime, timezone, reportType, actionType,
				email, emailMessage, emailSubject, hostAddress, port, username, password, folderNameScheduleReport,
				plateform, accessKeyId, accessKey, region, bucketName, folderNameSFTP, fileFormat,
				reportNameScheduleReport, gateway, recipientNumber);
	}

	String advanceConfigurationPageTitle = "Advance Configuration";
	String expectedColumnHeadingNamesListScheduleReport = "Column,Showable,Printable,Sortable,Sort Priority,Sorting";
	String searchKeyAdvanceScheduleReport = "Vehicle Model";
	String downloadReportType = "Single Sheet";
	String sortingType = "Des";
	String listOfRowEntriesTitleCommaSeperatedScheduleReport = "Company,Branch,Object,Vehicle Brand,Vehicle Model,Driver,IMEI No,Start Location,Start Odometer,Distance,Chart Data,Running,Idle,Stop,Inactive,Duration,Work Duration,Working Days,Max Stoppage,No of Idle,Speed,AVG,MAX,Cost based on,Distance,Duration,Over speed,Alert(s),Approx Toll Cost,End Odometer,End Location,Playback";
	String expectedScheduleReport = "Travel Daily";

	@Test
	public void testAdvanceScheduleReportFunctionality() throws InterruptedException {
		//	TESTCASE_DONE: Test advance setting functionality
		getSchedulePage().verifyThatUserAbleToHandleAdvanceConfiguration(advanceConfigurationPageTitle,
				expectedColumnHeadingNamesListScheduleReport, listOfRowEntriesTitleCommaSeperatedScheduleReport,
				searchKeyAdvanceScheduleReport, sortingType, downloadReportType, expectedScheduleReport);
	}

}