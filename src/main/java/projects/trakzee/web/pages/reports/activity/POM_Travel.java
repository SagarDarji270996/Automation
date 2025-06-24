package projects.trakzee.web.pages.reports.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Description;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Charts;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.PL_FavoriteButton;
import projects.trakzee.web.locators.common.PL_ScheduleReport;
import projects.trakzee.web.locators.reports.activity.PL_Travel;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.HoverOnElement;
import projects.trakzee.web.projectUtility.MethodGeneratorFromTODOList;
import projects.trakzee.web.projectUtility.ProjectUtility;

public class POM_Travel implements InitializePages {
	WebDriver driver = TestBase.getWebDriver();;

	public POM_Travel(WebDriver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
		String[] testSteps = { "uncheck all the showable checkboxes", "uncheck all the printable checkboxes" };
		MethodGeneratorFromTODOList.generateMethodsFromSteps(testSteps, "SCENARIOS");
	}

	private WebDriverWait wait = new WebDriverWait(driver, 5);

	private SoftAssert softAssert = new SoftAssert();
	private ProjectUtility pu = new ProjectUtility();

	String pageTitleName = "Travel Summary";

	public void verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(String dateType,
			String customStartDateInDDMMYYYY, String customEndDateInDDMMYYYY, String dateSeparator,
			boolean wantToCheckTimeRange, String company, String branch, String vehicleGroupName, String vehicleType,
			String vehilcBandName, String vehicleModel, String distance, String distanceValue, String durationFormat,
			String startTime, String endTime, String searchAbleObjectName, String timeRangeOrDateSeperator,
			String[] objectSelection) throws InterruptedException {
		// SCENARIOS_DONE: verify that user able to select the filter data from the LHS
		// and RHS both
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		// Left hand side panel
		getFilterPage().selectCompany(company);
		getFilterPage().selectBranch(branch);
		getFilterPage().selectVehicleGroup(vehicleGroupName);
		getFilterPage().selectVehicleType(vehicleType);
		getFilterPage().selectVehicleBrand(vehilcBandName);
		getFilterPage().selectVehicleModel(vehicleModel);
		getFilterPage().selectDistance(distance);
		getFilterPage().setDistance(distanceValue);
		getFilterPage().selectDurationFormat(durationFormat);

		// Right hand side panel
		getFilterPage().selectDateSelection(dateType, customStartDateInDDMMYYYY, customEndDateInDDMMYYYY,
				dateSeparator);

		// time range selection
		if (wantToCheckTimeRange) {
			getFilterPage().checkTimeRangeAtFilter();
			getFilterPage().selectStartTimeRangeAtFilter(startTime, timeRangeOrDateSeperator);
			getFilterPage().selectEndTimeRangeAtFilter(endTime, timeRangeOrDateSeperator);
		}

		// object selection
		getFilterPage().relaodObjectUnderSearchObjectField();
		getFilterPage().searchObjectSelection(searchAbleObjectName);
		getFilterPage().selectObjectFromThreeLevelCheckbox(objectSelection);
		getFilterPage().clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	public void verifyThatUserAbleToSeeSearchBoxAndAbleToSearchAndOnSearchItNavigateToCorrectScreen(
			String globalSearchValue, String expectedOverviewScreenTitle) throws InterruptedException {
		// SCENARIOS_DONE: Verify that user able to see search box and able to search
		// and on search it navigate to correct screen
		clickOnSearchAtHeaderButtonAndVerifyTooltip();
		searchAndSelectFromGlobalSearchList(globalSearchValue, expectedOverviewScreenTitle);
	}

	public Exception verifyTheUiElementPresentOnTheScreenForTheFavoriteReportAddNewFolderMode() throws Exception {
		// SCENARIOS_DONE: Verify the UI element present on the screen for the favorite
		// report add new folder mode
		removeReportFromFavorite();
		clickFavoriteIconAndVerifyTooltip();
		String existingImageName = "Add favorite report";
		String existingScreenShortsPath = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/screenshotForUIElementComparision/reports/activity/activity/Add favorite report.png";
		String outputScreenShortsPath = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/screenshotForUIElementComparision/reports/activity/activity";
		Thread.sleep(1000);
		getFavoritePage().checkUIElementOnFavoritePage(existingScreenShortsPath, existingImageName,
				outputScreenShortsPath);

		return null;

	}

	public void removeReportFromFavorite() throws Exception {
		// STEP_DONE: remove the report from the favorite
		clickFavoriteIconAndVerifyTooltip();

		try {
			String colorOfFavoriteButton = getCommonPage().getFavoriteButtonColor();
			System.out.println("colorOfFavoriteButton: " + colorOfFavoriteButton);
			if (colorOfFavoriteButton.equalsIgnoreCase("yellow")) {
				getFavoritePage().clickRemoveButton();
			} else {
				getFavoritePage().clickCrossIconButton();
			}
		} catch (Exception e) {
			System.out.println("Exception from: removeReportFromFavorite " + e.getMessage());
		}
	}

	public void verifyThatUserAbleToCreatedANewFavoriteFolder(String folderName) throws Exception {
		// SCENARIOS_DONE: Verify that user able to created a new favorite folder
		String folder = "New";
		boolean isClicked = clickFavoriteIconAndVerifyTooltip();

		if (!isClicked) {
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
			getFilterPage().clickOnDeleteFilterButton();
			clickFavoriteIconAndVerifyTooltip();
		}

		getFavoritePage().clickCrossIconButton();
		clickFavoriteIconAndVerifyTooltip();
		getFavoritePage().clickRemoveButton();
		clickFavoriteIconAndVerifyTooltip();
		getFavoritePage().selectOptionFromDropdown(folder);
		getFavoritePage().setFolderName(folderName);
		getFavoritePage().clickSaveButton();
	}

	public void verifyThatUserAbleToRemoveTheFavoriteFolder(String folderName) throws Exception {
		// SCENARIOS_DONE: Verify that user able to remove the favorite folder
		clickFavoriteIconAndVerifyTooltip();
		getFavoritePage().selectOptionFromDropdown(folderName);
		getFavoritePage().clickRemoveButton();
	}

	public void verifyThatOnceUserAddedTheReportsInFavoriteFolderThenItShoudlBePrsentOnTheRightHandSideFavoirteButton(
			String folderName, String reportNameOnHeaderBar) throws InterruptedException {
		// SCENARIOS_DONE: Verify that once user added the reports in favorite folder
		// then it shoudl be prsent on the right hand side favoirte button
		clickOnFavariteIconButtonAndVerifyTooltipRHS();
		Thread.sleep(1000);
		getHomePage().navigateToFavoriteReports(folderName, reportNameOnHeaderBar);
		Thread.sleep(1000);
		verifyReportPageTitle(reportNameOnHeaderBar);
	}

	public void verifyThatUserAbleToDeleteTheFolderFromTheRightHandSideFavoriteButton(String folderName,
			String reportName) throws InterruptedException {
		// SCENARIOS_DONE: Verify that user able to delete the folder from the right
		// hand side favorite button
		clickOnFavariteIconButtonAndVerifyTooltipRHS();
		Thread.sleep(1000);
		getHomePage().navigateToFavoriteReports(folderName, reportName);
		Thread.sleep(1000);
		getFavoritePage().clickDeleteButtonFavoriteReport(reportName);

	}

	public void verifyTheColumnValuesBasedOnTheGivenTableHeaderName(String expectedListCommaSeperated,
			String columnNameAttribute) {
		// STEP_DONE: Verify the list of object present on the overview screen based on
		// the selected object in filter
		getIFramePage().switchToContentFrame();
		CommonMethods.compareListByTextByRemovingDuplicateFromActualListIgnorOrder(driver,
				PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(columnNameAttribute),
				expectedListCommaSeperated);
	}

	public void verifyTheListOfColumnNamePresentOnOverviewScreenBasedOnTheAppliedSettingsAsShowable() {
		// TODO SCENARIOS: Verify the list of column name present on overview screen
		// based on the applied settings as showable

	}

	// ==================================================================

	public void checkTravelSummaryPageLoadingOnDefault(int expectedpageloadTimeInSeconds) {
		// STEP_DONE: Check the travel summary page loading on default
		getIFramePage().switchToContentFrame();
		CommonMethods.getInvisibilityWaitTime(driver, PL_Commons.loadingIcon, expectedpageloadTimeInSeconds);
	}

	public void clickScheduleIconButton(int expectedpageloadTimeInSeconds) {
		// STEP_DONE: click on the schedule icon button
		getIFramePage().switchToTitleFrame();
		pu.clickOnButton(driver, PL_Commons.loadingIcon, expectedpageloadTimeInSeconds);
	}

	public void checkHeaderElements() {
		getIFramePage().switchToTitleFrame();
		// STEP_DONE: check the list of header elements present
		String[] list = new String[] { "Add Favourite Report", "Travel Summary", "Search Screen", "Favorite Report",
				"Filter", "Schedule", "Settings", "Chart", "Help" };
		List<String> listIconOnHeader = new ArrayList<>(Arrays.asList(list));
		pu.getTextFromListAndVerify(driver,
				String.format(PL_Commons.listHeaderElementAndHeaderTitleReplaceable, "Travel Summary"), "title",
				listIconOnHeader);

	}

	public boolean clickFavoriteIconAndVerifyTooltip() throws Exception {
		// STEP_DONE: click on favorite icon button and verify tool tip
		getIFramePage().switchToTitleFrame();
		try {
			pu.clickOnButtonNew(driver, PL_Commons.btnAddFavoriteAtHeader, 05);
			pu.getTextByAttributeAndVerify(driver, "title", "Add Favourite Report", PL_Commons.btnAddFavoriteAtHeader);
			return true;
		} catch (Exception e) {
			TestBase.getSoftAssert().fail("Favorite button not present on the given reports");
			return false;
		}
	}

	public void verifyReportPageTitle(String pageTitleName) throws InterruptedException {
		// STEP_DONE: verify the report page title name
		getIFramePage().switchToTitleFrame();
		pu.getElementTextAndCompare(driver,
				String.format(PL_Commons.textHeaderNameStringFormat, pageTitleName), pageTitleName);

	}

	@Description("This is used when the date type is selected Today | Yesterday | Lask Week | This Week | This Month | Last Month | Last 7 Days")
	public void verifyHeaderDateFromAndTo(String startDateTime, String endDataTime) throws InterruptedException {
		// STEP_DONE: verify the time stamp present on the header
		getIFramePage().switchToTitleFrame();
		pu.getElementTextAndCompare(driver, PL_Commons.fromDateAtHeaderbar, startDateTime, 5);
		pu.getElementTextAndCompare(driver, PL_Commons.toDateAtHeaderbar, endDataTime, 5);

	}

	@Description("This is used when the date type is no selected as custom time range")
	public void verifyHeaderDateFromAndToWhenDateTypeNotCustomRange(String startDateTime, String endDateTime)
			throws InterruptedException {
		// STEP_DONE: verify the time stamp present on the header
		getIFramePage().switchToTitleFrame();

		String actualStartDateTime = pu.getElementText(driver, PL_Commons.fromDateAtHeaderbar).trim();
		actualStartDateTime = actualStartDateTime.substring(0, 10);
		TestBase.getSoftAssert().assertEquals(actualStartDateTime, startDateTime);

		String actualEndDateTime = pu.getElementText(driver, PL_Commons.toDateAtHeaderbar);
		if (actualEndDateTime != null) {
			String actualEndDateTime2 = actualEndDateTime.trim().substring(0, 10);
			TestBase.getSoftAssert().assertEquals(actualEndDateTime2, endDateTime);
		}

	}

	@Description("This is used when the custom time range selected")
	public void verifyHeaderTimeStartAndEnd(String startAndEndTime) throws InterruptedException {
		// STEP_DONE: verify the time stamp present on the header
		getIFramePage().switchToTitleFrame();
		pu.getElementTextAndCompare(driver, PL_Commons.startAndEndTimeAtHeaderbar, startAndEndTime, 5);
	}

	@Description("This is used to check when the time range not checked")
	public void verifyHeaderDefualtTimeStartAndEnd(String timeFormat, String dateType)
			throws InterruptedException, ParseException {
		getIFramePage().switchToTitleFrame();
		String startTime = null;
		String endTime = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf;

		if (timeFormat.equalsIgnoreCase("12") || timeFormat.equalsIgnoreCase("ampm")) {
			startTime = "12:00 AM";
			sdf = new SimpleDateFormat("hh:mm a"); // 12-hour format with AM/PM
		} else if (timeFormat.equalsIgnoreCase("24")) {
			startTime = "00:00";
			sdf = new SimpleDateFormat("HH:mm"); // 24-hour format
		} else {
			throw new IllegalArgumentException("Invalid time format: " + timeFormat);
		}

		endTime = sdf.format(cal.getTime());

		System.out.println("Start time: " + startTime + " end time: " + endTime);

		String actualStartDateTime = pu.getElementText(driver, PL_Commons.fromDateAtHeaderbar).trim();
		String actualStartDateTimeFormated = actualStartDateTime.substring(11, 19);
		String actualEndDateTime = pu.getElementText(driver, PL_Commons.toDateAtHeaderbar).trim();
		String actualEndDateTimeFormated = actualEndDateTime.substring(11, 19);

		System.out.println("Actual Start Time: " + actualStartDateTime + " Actual End Time: " + actualEndDateTime);

		// Tolerance in minutes
		final int TOLERANCE_MINUTES = 2;

		// Convert times to Date objects
		Date expectedEndTime = sdf.parse(endTime);
		Date actualEndTimeDate = sdf.parse(actualEndDateTimeFormated);
		boolean isFixedEndTime = false;
		if (dateType.trim().replaceAll(" ", "").equalsIgnoreCase("LastMonth") || dateType.equalsIgnoreCase("LastWeek")
				|| dateType.equalsIgnoreCase("LastMonth") || dateType.equalsIgnoreCase("Yesterday")) {
			isFixedEndTime = true;
			if (timeFormat.equalsIgnoreCase("24")) {
				endTime = "23:59 PM";
			} else {
				endTime = "11:59 PM";
			}

		}

		if (isFixedEndTime) {
			// Perform soft assertion with tolerance
			TestBase.getSoftAssert().assertEquals(actualEndDateTimeFormated, endTime, "End time not matched");
		} else {
			// Calculate the time difference in minutes
			long timeDifference = Math.abs(expectedEndTime.getTime() - actualEndTimeDate.getTime()) / (60 * 1000);
			if (timeDifference > TOLERANCE_MINUTES) {
				TestBase.getSoftAssert().fail("End time difference exceeded tolerance of " + TOLERANCE_MINUTES
						+ " minutes. Expected: " + endTime + ", Actual: " + actualEndDateTime);
			}

			System.out.println("Time difference in minutes: " + timeDifference);
		}

		// Perform soft assertion with tolerance
		TestBase.getSoftAssert().assertEquals(actualStartDateTimeFormated, startTime, "Start time not matched");

	}

	public void clickOnScheduleIconButtonAndVerifyTooltip() throws InterruptedException {
		// STEP_DONE: click on the schedule icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Schedule", PL_Commons.btnScheduleReportAtHeader);

		pu.clickOnButton(driver, PL_Commons.btnScheduleReportAtHeader, 10);
		getIFramePage().switchToContentFrame();
		pu.getElementTextAndCompare(driver, PL_ScheduleReport.btnCreateScheduleReport, "Create Schedule Report", 5);
	}

	public void clickOnChartIconButtonAndVerifyTooltip() throws InterruptedException {
		// STEP_DONE: click on the chart icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Chart", PL_Commons.btnChartIcon);
		pu.clickOnButton(driver, PL_Commons.btnChartIcon, 10);
		pu.getTextByAttributeAndVerify(driver, "value", "Cancel", PL_Charts.btnSaveOnCharts);
	}

	public void clickOnFavariteIconButtonAndVerifyTooltipRHS() throws InterruptedException {
		// STEP_DONE: click on the favortie icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Favorite Report", PL_Commons.btnFavoriteAtHeader);
		pu.clickOnButton(driver, PL_Commons.btnFavoriteAtHeader, 10);
	}

	public void clickOnHelpIconButtonAndVerifyTooltip() {
		// STEP_DONE: click on the help icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Help", PL_Commons.btnHelpAtHeader);
		pu.clickOnButton(driver, PL_Commons.btnAddFavoriteAtHeader, 10);
		pu.getTextByAttributeAndVerify(driver, "data", "description", PL_Commons.btnDescription);

	}

	public void clickOnSearchAtHeaderButtonAndVerifyTooltip() {
		// STEP_DONE: click on search icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Search Screen", PL_Commons.btnSearchScreenIconAtHeader, 15);
		pu.clickOnButton(driver, PL_Commons.btnSearchScreenIconAtHeader, 10);
		getIFramePage().switchToDivFrame();
		pu.getTextByAttributeAndVerify(driver, "placeholder", "Search", PL_Commons.inputFieldUniversalSearchBox, 10);

	}

	public void searchAndSelectFromGlobalSearchList(String searchValue, String expectedOverviewScreenTitle)
			throws InterruptedException {
		// STEP_DONE: search and select from global search list
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_Commons.inputFieldUniversalSearchBox, 02);
		pu.setDataInField(driver, PL_Commons.inputFieldUniversalSearchBox, searchValue, 5);
		pu.selectElementFromList(driver, PL_Commons.listUniversalSearchAbleElement, searchValue);

		getIFramePage().switchToTitleFrame();
		pu.getElementTextAndCompare(driver,
				PL_Commons.textHeaderName.replace("headerName", expectedOverviewScreenTitle),
				expectedOverviewScreenTitle);
	}

	public void verifyColumnTitleList() {
		// STEP_DONE: Verify the column title list based on the settings selection
		getIFramePage().switchToTitleFrame();
		String expectedColumnNamesList = "Company,Branch,Object,Vehicle Model,Vehicle Branch, Driver,IMEI No,Start Location,Start Odometer, Distance,Running V/s Stop, Running, Idel, Stop, Inactive, Duration,Work Duratin, Working Day,Max Stoppage, No Of Idel, Speed, Cost based on , Over speed , Approx toll cost,End Odometer, End Location, Playback  ";
		getSchedulePage().verifyColumnHeadingTitle(expectedColumnNamesList);
	}

	public void verifyTotalForRowListEntries() {
		// TODO: Verify the total for the row list entries
		getIFramePage().switchToContentFrame();

	}

	public void clickPlusOrMinusIcon() {
		// STEP_DONE: Click on the plus or minus icon button present inside row list
		// entries
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Travel.btnplusAndMinusIconStringForamtRowIndex, 10);
	}

	public void verifyColumnTitleUnderRowListEntries() {
		// TODO: Verify the column title list present under the row list entries
		getIFramePage().switchToContentFrame();
	}

	public void scrollOverviewPage() throws InterruptedException {
		// STEP_DONE: Scroll the overview page
		getIFramePage().switchToContentFrame();

	}

	public void scrollSummaryListUnderRowListEntries() throws InterruptedException {
		// TODO: Scroll summary list present under the row list entries
		getIFramePage().switchToContentFrame();
	}

	public void selectDownloadTypeByHoverOnXLSIconButtonAtFilter() {
		// TODO: Select download type by hovering on XLS icon button at filter
		getIFramePage().switchToBottomFrame();
	}

	public void selectDownloadTypeByHoverOnPDFIconButtonAtFilter() {
		// TODO: Select download type by hovering on PDF icon button at filter
		getIFramePage().switchToBottomFrame();
	}

	public void selectDownloadTypeByHoverOnCSVIconButtonAtFilter() {
		// TODO: Select download type by hovering on CSV icon button at filter
		getIFramePage().switchToBottomFrame();
	}

}