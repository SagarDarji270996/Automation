package projects.trakzee.web.pages.common.header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.PL_Filter;
import projects.trakzee.web.locators.reports.activity.PL_LiveMatrix;
import projects.trakzee.web.locators.reports.activity.PL_Travel;
import projects.trakzee.web.pages.common.POM_Common;
import projects.trakzee.web.pages.reports.activity.POM_SpeedVsDistance;
import projects.trakzee.web.projectUtility.CheckboxHandler;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.DatePickerUtils;
import projects.trakzee.web.projectUtility.DropdownHandler;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.StringHandling;
import projects.trakzee.web.projectUtility.WebElementList;
import projects.trakzee.web.projectUtility.XpathExtractor;

public class POM_Filter implements InitializePages {
	//	public static void main(String[] args) {
	//		String[] testSteps = { "check the status of the report added to the cloud download",
	//				"click on download icon button present at cloud donwload list", };
	//		MethodGeneratorFromTODOList.generateMethodsFromSteps(testSteps, "STEP");
	//	}

	WebDriverWait wait;
	private WebDriver driver = TestBase.getWebDriver();

	public POM_Filter(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}

	SoftAssert softAssert = new SoftAssert();
	ProjectUtility pu = new ProjectUtility();

	@Description("This is used to hanlde the filter functionality irrespective of pages by passing the page name and possible arguments present on pages.")
	@Feature("Hanlde end to end filter functionality for all the pages throught out the project")
	public boolean filterFunctionalityForAllPagesBasedOnField(Map<String, Object> args) throws InterruptedException {
		args = StringHandling.convertKeysToCamelCase(args);
		Thread.sleep(1000);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		Set<String> filedNames = args.keySet();
		boolean isClickedOnActionButton = false;
		System.out.println("List of filed present as per given data: " + filedNames);
		for (String field : filedNames) {
			if (!"na".equalsIgnoreCase((String) args.get(field))) {
				switch (field) {
				case "company": {
					//selectCompany((String) args.get("company"));
					selectCompanyCommon((String) args.get("company"));
					break;
				}
				case "branch": {
					//selectBranch((String) args.get("branch"));
					selectBranchCommon((String) args.get("branch"));

					break;
				}
				case "vehicleGroup": {
					//selectVehicleGroup((String) args.get("vehicleGroup"));
					selectVehicleGroupCommon((String) args.get("vehicleGroup"));
					break;
				}
				case "vehicleType": {
					//selectVehicleType((String) args.get("vehicleType"));
					selectVehicleTypeCommon((String) args.get("vehicleType"));
					break;
				}
				case "vehicleBrand": {
					selectVehicleBrand((String) args.get("vehicleBrand"));
					break;
				}
				case "vehicleModel": {
					selectVehicleModel((String) args.get("vehicleModel"));
					break;
				}
				case "distance": {
					selectDistance((String) args.get("distance"));
					break;
				}
				case "tripCalculationFor": {
					selectTripCalculationFor((String) args.get("tripCalculationFor"));
					break;
				}
				case "distanceValue": {
					setDistance((String) args.get("distanceValue"));
					break;
				}
				case "calculationFor": {
					selectTripCalculationFor((String) args.get("calculationFor"));
					break;
				}
				case "stopDuration": {
					selectStopDuration((String) args.get("stopDuration"));
					break;
				}
				case "idleDuration": {
					selectIdleDuration((String) args.get("idleDuration"));
					break;
				}
				case "durationFormat": {
					selectDurationFormat((String) args.get("durationFormat"));
					break;
				}
				case "status": {
					//selectStatus((String) args.get("status"));
					selectStatusCommon((String) args.get("status"));
					break;
				}

				case "statusWithCheckbox": {
					selectStatusWithCheckbox((String) args.get("statusWithCheckbox"));
					break;
				}

				case "countryWithCheckbox": {
					selectCountryWithCheckbox((String) args.get("countryWithCheckbox"));
					break;
				}
				case "vehicleCategory": {
					selectVehicleCategoryCommon((String) args.get("vehicleCategory"));
					break;
				}
				case "dateSelection": {
					if (((String) args.get("dateSelection")).equalsIgnoreCase("true")
							|| ((String) args.get("dateSelection")).equalsIgnoreCase("yes")) {
						selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
								(String) args.get("customEndDate"), (String) args.get("dateSeperator"));
					}

					break;
				}

				case "dateSelectionMonthYear": {
					String[] monthyear = ((String) args.get("dateSelectionMonthYear")).split(" ");
					getCommonPage().selectMonthAndYear(monthyear[0], monthyear[1]);
					break;
				}

				case "fromDateSelection": {
					if (((String) args.get("fromDateSelection")).equalsIgnoreCase("true")
							|| ((String) args.get("fromDateSelection")).equalsIgnoreCase("yes")) {
						if ("Yes".equalsIgnoreCase((String) args.get("wantToSetTime"))
								|| "True".equalsIgnoreCase((String) args.get("wantToSetTime"))) {
							selectFromDateSelectionWithHourAndMinute((String) args.get("fromDate"),
									(String) args.get("dateSeperator"));
						} else {
							selectFromDateSelection((String) args.get("fromDate"), (String) args.get("dateSeperator"));
						}
					}

					break;
				}
				case "startDatePickerSeperateDropdownForMonthYearAndDateInGreed": {
					System.out.println("1");
					selectStartDateSelection(
							(String) args.get("startDatePickerSeperateDropdownForMonthYearAndDateInGreed"),
							(String) args.get("dateSeperator"));
					break;
				}
				case "endDatePickerSeperateDropdownForMonthYearAndDateInGreed": {
					System.out.println("2");
					selectEndDateSelection((String) args.get("endDatePickerSeperateDropdownForMonthYearAndDateInGreed"),
							(String) args.get("dateSeperator"));
					break;
				}

				case "days": {
					selectDaysCommon((String) args.get("days"));
					break;
				}
				case "speedRange": {
					if (((String) args.get("wantToChangeSpeedRange")).equalsIgnoreCase("true")
							|| ((String) args.get("wantToChangeSpeedRange")).equalsIgnoreCase("yes")) {
						changeSpeedRange((String) args.get("speedRange"));
					}

					break;
				}
				case "statusSince": {
					selectStatusSinceCommon((String) args.get("statusSince"));
					break;
				}
				case "alertType": {
					selectAlertTypeCommon((String) args.get("alertType"));
					break;
				}
				case "alertSelection": {
					selectAlertSelectionCommon((String) args.get("alertSelection"));
					break;
				}
				case "batteryTolerance": {
					setBatteryTolerance((String) args.get("batteryTolerance"));
					break;
				}
				case "xHours": {
					selectXHoursCommon((String) args.get("xHours"));
					break;
				}
				case "toTimeDropdown": {
					selectToTimeCommon((String) args.get("toTimeDropdown"));
					break;
				}
				case "fromTimeDropdown": {
					selectFromTimeCommon((String) args.get("fromTimeDropdown"));
					break;
				}
				case "redValue": {
					setRed((String) args.get("redValue"));
					break;
				}
				case "greenValue": {
					setGreen((String) args.get("greenValue"));
					break;
				}
				case "yellowValue": {
					setYellow((String) args.get("yellowValue"));
					break;
				}

				case "roundOff": {
					if (((String) args.get("roundOff")).equalsIgnoreCase("true")
							|| ((String) args.get("roundOff")).equalsIgnoreCase("yes")) {
						clickOnRoundOffCheckbox();
					}
					break;
				}
				case "wantToSetSpeedLimit": {
					if (((String) args.get("wantToSetSpeedLimit")).equalsIgnoreCase("true")
							|| ((String) args.get("wantToSetSpeedLimit")).equalsIgnoreCase("yes")) {
						selectAndSetSppedLimitCommon((String) args.get("speedLimitType"),
								(String) args.get("customValue"), (String) args.get("asPerAlertValue"));
					}
					break;
				}
				case "objectRating": {
					selectObjectRatingCommon((String) args.get("objectRating"));
					break;
				}
				case "shiftGroup": {
					selectShiftGroupCommon((String) args.get("shiftGroup"));
					break;
				}

				case "shift": {
					selectShiftCommon((String) args.get("shift"));
					break;
				}

				case "timeRange": {
					if (((String) args.get("timeRange")).equalsIgnoreCase("true")
							|| ((String) args.get("timeRange")).equalsIgnoreCase("yes")) {
						checkTimeRangeAtFilter();
						selectStartTimeRangeAtFilter((String) args.get("startTime"),
								(String) args.get("timeSeperator"));
						selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
					}
					break;
				}
				case "objectSelection": {

					// Convert object selection from Object to List<String>
					List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
					if (objectSelection != null && !objectSelection.isEmpty()) {
						selectObjectFromThreeLevelCheckbox(objectSelection);
					}
					break;
				}
				case "actionButton": {
					if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("apply")) {
						isClickedOnActionButton = clickOnAppyFilterButton();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("save")) {
						isClickedOnActionButton = clickOnSaveFilterButton();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("update")) {
						isClickedOnActionButton = clickOnUpdateFilterButton();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("delete")) {
						isClickedOnActionButton = clickOnDeleteFilterButton();
					}
				}
				}
			}
		}

		if (isClickedOnActionButton) {
			if (!(args.get("errorMessage") == null
					|| ((String) args.get("errorMessage")).trim().matches("(?i)^\\s*(|NA|NULL)\\s*$"))) {
				// The (?i) makes it case-insensitive.
				// The regex ^\\s*(|NA|NULL)\\s*$ ensures only "NA", "NULL", empty, or whitespace strings are matched.
				getCommonPage().getJAlertMessageAndVerify((String) args.get("errorMessage"));
				getCommonPage().clickOnJAlertCloseButton();
			} else {
				boolean jalertMessage = false;
				try {
					getIFramePage().switchToDivFrame();
					WebDriverWait wait = new WebDriverWait(driver, 1);
					jalertMessage = driver.findElement(PL_Commons.textJAlertMessage).isDisplayed();
				} catch (Exception e) {
				}
				if (jalertMessage) {
					getCommonPage().getJAlertMessageAndVerify((String) args.get(""));
					getCommonPage().clickOnJAlertCloseButton();
					TestBase.getSoftAssert().fail("Gets message on click apply button: " + jalertMessage);
					TestBase.getSoftAssert().assertAll();
				}

			}
		} else {
			System.out.println("Not clicked on the apply button");
		}
		System.out.println("End filter selection");

		return isClickedOnActionButton;

	}

	@Description("This is used to hanlde the filter functionality irrespective of pages by passing the page name and possible arguments present on pages.")
	@Feature("Hanlde end to end filter functionality for all the pages throught out the project")
	public void filterFunctionalityForAllPages(String pageName, Map<String, Object> args) throws InterruptedException {

		switch (pageName.toLowerCase()) {
		case "todayactivity":
			filterHanldingForTodayActivityReports(args);
			break;
		case "travel":
			filterHandlingForTravelSummaryReports(args);
			break;
		case "travelhistory":
			filterHanldingForTodayHistoryReports(args);
			break;
		case "trip":
			filterHandlingForTripSummaryReports(args);
			break;
		case "stoppage":
			filterHandlingForStoppageSummaryReports(args);
			break;
		case "idle":
			filterHandlingForIdleSummaryReports(args);
			break;
		}

	}

	@Story("Filter functionality for the today activity reports")
	private void filterHanldingForTodayActivityReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Today Activity " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		selectVehicleGroup((String) args.get("vehicleGroup"));
		selectVehicleType((String) args.get("vehicleType"));
		selectVehicleBrand((String) args.get("vehicleBrand"));
		selectVehicleModel((String) args.get("vehicleModel"));
		selectStatus((String) args.get("vehicleStatus"));
		clickOnAppyFilterButtonRHS();
	}

	@Story("Filter functionality for the travel summary reports")
	private void filterHandlingForTravelSummaryReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Travel " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		selectVehicleGroup((String) args.get("vehicleGroup"));
		selectVehicleType((String) args.get("vehicleType"));
		selectVehicleBrand((String) args.get("vehicleBrand"));
		selectVehicleModel((String) args.get("vehicleModel"));
		selectDistance((String) args.get("distance"));
		setDistance((String) args.get("distanceValue"));
		selectDurationFormat((String) args.get("durationFormat"));

		// Right-hand side panel
		selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
				(String) args.get("customEndDate"), (String) args.get("dateSeperator"));

		// Time range selection
		if (((String) args.get("wantToCheckTimeRange")).equalsIgnoreCase("true")) {
			checkTimeRangeAtFilter();
			selectStartTimeRangeAtFilter((String) args.get("startTime"), (String) args.get("timeSeperator"));
			selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
		}

		// Object selection (Updated to use a List)
		relaodObjectUnderSearchObjectField();
		searchObjectSelection((String) args.get("searchableObjectName"));

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			selectObjectFromThreeLevelCheckbox(objectSelection);
		}

		clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	@Story("Filter functionality for the travel history reports")
	private void filterHanldingForTodayHistoryReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Travel History " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();
		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		// Right-hand side panel
		selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
				(String) args.get("customEndDate"), (String) args.get("dateSeperator"));
		// Time range selection
		if (args.get("wantToCheckTimeRange").toString().equalsIgnoreCase("true")) {
			checkTimeRangeAtFilter();
			selectStartTimeRangeAtFilter((String) args.get("startTime"), (String) args.get("timeSeperator"));
			selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
		}

		// Object selection (Updated to use a List)
		relaodObjectUnderSearchObjectField();
		searchObjectSelection((String) args.get("searchableObjectName"));

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			selectObjectFromThreeLevelCheckbox(objectSelection);
		}
		clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	@Story("Filter functionality for the trip summary reports")
	private void filterHandlingForTripSummaryReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Trip " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		selectVehicleGroup((String) args.get("vehicleGroup"));
		selectVehicleType((String) args.get("vehicleType"));
		selectVehicleBrand((String) args.get("vehicleBrand"));
		selectVehicleModel((String) args.get("vehicleModel"));
		selectTripCalculationFor((String) args.get("tripCalculationFor"));
		selectDistance((String) args.get("distance"));
		setDistance((String) args.get("distanceValue"));

		// Right-hand side panel
		selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
				(String) args.get("customEndDate"), (String) args.get("dateSeperator"));

		// Time range selection
		if (Boolean.parseBoolean(args.get("wantToCheckTimeRange").toString().toLowerCase())) {
			checkTimeRangeAtFilter();
			selectStartTimeRangeAtFilter((String) args.get("startTime"), (String) args.get("timeSeperator"));
			selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
		}

		// Object selection (Updated to use a List)
		relaodObjectUnderSearchObjectField();

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			selectObjectFromThreeLevelCheckbox(objectSelection);
		}
		clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	@Story("Filter functionality for the Stoppage summary reports")
	private void filterHandlingForStoppageSummaryReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Stoppage " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		selectVehicleGroup((String) args.get("vehicleGroup"));
		selectVehicleType((String) args.get("vehicleType"));
		selectVehicleBrand((String) args.get("vehicleBrand"));
		selectVehicleModel((String) args.get("vehicleModel"));
		selectTripCalculationFor((String) args.get("calculationFor"));
		selectStopDuration((String) args.get("stopDuration"));

		// Right-hand side panel
		selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
				(String) args.get("customEndDate"), (String) args.get("dateSeperator"));

		// Time range selection
		if (Boolean.parseBoolean(args.get("wantToCheckTimeRange").toString().toLowerCase())) {
			checkTimeRangeAtFilter();
			selectStartTimeRangeAtFilter((String) args.get("startTime"), (String) args.get("timeSeperator"));
			selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
		}

		// Object selection (Updated to use a List)
		relaodObjectUnderSearchObjectField();

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			selectObjectFromThreeLevelCheckbox(objectSelection);
		}
		clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	@Story("Filter functionality for the Idle summary reports")
	private void filterHandlingForIdleSummaryReports(Map<String, Object> args) throws InterruptedException {
		System.out.println("Selected Page Filter: Idle " + " Filder data: " + args);
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltip();

		selectCompany((String) args.get("company"));
		selectBranch((String) args.get("branch"));
		selectVehicleGroup((String) args.get("vehicleGroup"));
		selectVehicleType((String) args.get("vehicleType"));
		selectVehicleBrand((String) args.get("vehicleBrand"));
		selectVehicleModel((String) args.get("vehicleModel"));
		selectTripCalculationFor((String) args.get("calculationFor"));
		selectIdleDuration((String) args.get("idleDuration"));

		// Right-hand side panel
		selectDateSelection((String) args.get("dateType"), (String) args.get("customStartDate"),
				(String) args.get("customEndDate"), (String) args.get("dateSeperator"));

		// Time range selection
		if (Boolean.parseBoolean(args.get("wantToCheckTimeRange").toString().toLowerCase())) {
			checkTimeRangeAtFilter();
			selectStartTimeRangeAtFilter((String) args.get("startTime"), (String) args.get("timeSeperator"));
			selectEndTimeRangeAtFilter((String) args.get("endTime"), (String) args.get("timeSeperator"));
		}

		// Object selection (Updated to use a List)
		relaodObjectUnderSearchObjectField();

		// Convert object selection from Object to List<String>
		List<String> objectSelection = Arrays.asList(args.get("objectSelection").toString().split(","));
		if (objectSelection != null && !objectSelection.isEmpty()) {
			selectObjectFromThreeLevelCheckbox(objectSelection);
		}
		clickOnAppyFilterButton();
		getCommonPage().clickOnJAlertCloseButton();
	}

	@Step("Select date selection")
	public void selectDateSelection(String dateType, String customStartDateInDDMMYYYY, String customEndDateInDDMMYYYY,
			String dateSeperator) {
		// STEP_DONE: select date selection or date picker to and from or start and end
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.DropdownDateSelection, 3);
		WebElementList.selectElementFromList(TestBase.getWebDriver(), PL_Filter.ListDateSelectionOption, dateType);

		if (dateType.trim().replace(" ", "").equalsIgnoreCase("CustomRange")) {
			getIFramePage().switchToContentFrame();
			System.out.println("dateType: " + dateType);
			DatePickerUtils.pickDateWhenStartAndEndDateAreTogether(driver, customStartDateInDDMMYYYY,
					customEndDateInDDMMYYYY, dateSeperator, PL_Filter.listDateGridStart, PL_Filter.listDateGridEnd,
					PL_Filter.btnNextDatePicker, PL_Filter.btnPrevoustDatePicker, PL_Filter.textStartMonthYear,
					PL_Filter.textEndMonthYear, PL_Filter.btnApplyAtDatePicker, PL_Filter.btnCancelAtDatePicker);
		}
	}

	@Step("Select from date selection")
	public void selectFromDateSelection(String startDateDMYYYY, String dateSeperator) {
		// STEP_DONE: select start date selection or date picker
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnFromDateSelection, 3);
		DatePickerUtils.selectDate(driver, startDateDMYYYY, PL_Filter.listFromDateGridStart,
				PL_Filter.btnNextFromDatePicker, PL_Filter.btnPrevoustFromDatePicker,
				PL_Filter.textFromDateStartMonthYear, dateSeperator);
	}

	@Step("Select start date when year and month in dropdown and date in greed")
	public void selectStartDateSelection(String startDateDMYYYY, String dateSeperator) {
		// STEP_DONE: select start date selection or date picker
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnFromDate, 3);
		DatePickerUtils.selectDate(driver, startDateDMYYYY, PL_Filter.listDateGrid, PL_Filter.btnNextFromDatePicker,
				PL_Filter.btnPrevoustFromDatePicker, PL_Filter.listMonthDropdownSelectTag,
				PL_Filter.listYearDropdownSelectTag, dateSeperator, true);
	}

	@Step("Select end date when year and month in dropdown and date in greed")
	public void selectEndDateSelection(String endDateDMYYYY, String dateSeperator) {
		// STEP_DONE: select start date selection or date picker
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnToDate, 3);
		DatePickerUtils.selectDate(driver, endDateDMYYYY, PL_Filter.listDateGrid, PL_Filter.btnNextFromDatePicker,
				PL_Filter.btnPrevoustFromDatePicker, PL_Filter.listMonthDropdownSelectTag,
				PL_Filter.listYearDropdownSelectTag, dateSeperator, true);
	}

	@Step("Select from date selection")
	public void selectFromDateSelectionWithHourAndMinute(String startDateDMYYYY, String dateSeperator) {
		// STEP_DONE: select start date selection or date picker
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnFromDateSelection, 3);

		getCommonPage().dragAndDrop(driver, PL_Filter.hourSliderInsideDatePicker, -20, 0);
		getCommonPage().dragAndDrop(driver, PL_Filter.minuteSliderInsideDatePicker, 20, 0);

		DatePickerUtils.selectDate(driver, startDateDMYYYY, PL_Filter.listFromDateGridStart,
				PL_Filter.btnNextFromDatePicker, PL_Filter.btnPrevoustFromDatePicker,
				PL_Filter.textFromDateStartMonthYear, dateSeperator);
	}

	boolean isTimeRangeChecked = false;

	@Step("Check time range checkbox")
	public void checkTimeRangeAtFilter() {
		// STEP_DONE: check time range
		getIFramePage().switchToContentFrame();
		isTimeRangeChecked = CheckboxHandler.checkCheckboxIfNotSelected(driver, "Time Range",
				PL_Filter.checkboxTimeRange);
	}

	@Step("Select the start time range at filter")
	public void selectStartTimeRangeAtFilter(String startTime, String timeHourMinuteSeperator)
			throws InterruptedException {
		// STEP_DONE: select start time range

		if (isTimeRangeChecked) {
			getIFramePage().switchToContentFrame();
			String[] startTimeData = startTime.split(timeHourMinuteSeperator);
			System.out.println("User start time: " + startTimeData[0].trim() + " , " + startTimeData[1].trim() + " , "
					+ startTimeData[2].trim());
			pu.clickOnButton(driver, PL_Filter.fieldTimeRangeFrom, 10);

			// To select from/start time
			pu.selectElementFromList(driver, PL_Filter.listHoursPicker, startTimeData[0].trim());
			pu.selectElementFromList(driver, PL_Filter.listAM_PM, startTimeData[2].trim());
			pu.selectElementFromList(driver, PL_Filter.listMinutePicker, startTimeData[1].trim());
			pu.clickOnButton(driver, PL_Commons.btnNoActionAtFilterWithAndWithStringFormat, 10);
		}

	}

	@Step("Select end time range at filter")
	public void selectEndTimeRangeAtFilter(String endTime, String timeHourMinuteSeperator) throws InterruptedException {
		// STEP_DONE: select end time range
		if (isTimeRangeChecked) {
			getIFramePage().switchToContentFrame();

			String[] endTimeData = endTime.split(timeHourMinuteSeperator);
			System.out.println("User end time: " + endTimeData[0].trim() + " , " + endTimeData[1].trim() + " , "
					+ endTimeData[2].trim());
			// To select to/end time
			pu.clickOnButton(driver, PL_Filter.fieldTimeRangeTo, 10);

			pu.selectElementFromList(driver, PL_Filter.listHoursPicker, endTimeData[0].trim());
			pu.selectElementFromList(driver, PL_Filter.listMinutePicker, endTimeData[1].trim());
			pu.selectElementFromList(driver, PL_Filter.listAM_PM, endTimeData[2].trim());
			pu.clickOnButton(driver, PL_Commons.btnNoActionAtFilterWithAndWithStringFormat, 10);
		}
	}

	@Step("Search object selection")
	public void searchObjectSelection(String searchAbleObjectName) {
		// STEP_DONE: perform the search object selection
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Filter.searchBarObjectSelection, searchAbleObjectName);
	}

	@Step("Click on the reload button present inside search box")
	public void relaodObjectUnderSearchObjectField() {
		//STEP_DONE: check the reload search object feature
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnRelaod, 10);

	}

	@Step("Select object at three level checkboxes")
	public void selectObjectFromThreeLevelCheckbox(String[] objectArray) {
		// STEP_DONE: select object from three-level checkbox
		getIFramePage().switchToContentFrame();
		for (String objectName : objectArray) {
			searchObjectSelection(objectName);
			String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Filter.checkBoxObjectReplace);
			pu.clickOnButton(driver, String.format(xpath, objectName));
		}

	}

	@Step("Select object at three level checkboxes")
	public void selectObjectFromThreeLevelCheckbox(List<String> objectList) {
		// STEP_DONE: select object from three-level checkbox
		getIFramePage().switchToContentFrame();
		relaodObjectUnderSearchObjectField();
		for (String objectName : objectList) {
			searchObjectSelection(objectName);
			String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Filter.checkBoxObjectReplace);
			pu.clickOnButton(driver, String.format(xpath, objectName));
		}
	}

	@Step("Scroll list element")
	public void scrollObjectList() throws InterruptedException {
		// STEP_DONE: scroll through the object list
		getIFramePage().switchToContentFrame();

	}

	@Step("Select company present at left hand side")
	public void selectCompany(String companyName) throws InterruptedException {
		// STEP_DONE: select the company
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Copmany",
				PL_Filter.btnDropdownComapnyLHS, PL_Filter.searchboxCompanyLHS, PL_Filter.listCompanyLHS, companyName,
				PL_Filter.textAlradySelectedCompanyDropdownLHS);
	}

	@Step("Select company present at right hand side ")
	public boolean selectCompanyRHS(String companyName) throws InterruptedException {
		// STEP_DONE: select the company
		getIFramePage().switchToContentFrame();
		return DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Copmany",
				PL_Filter.btnDropdownComapnyRHS, PL_Filter.searchboxCompanyRHS, PL_Filter.listCompanyRHS, companyName,
				PL_Filter.textAlradySelectedCompanyDropdownRHS);
	}

	@Step("Select company for common use")
	public boolean selectCompanyCommon(String companyName) throws InterruptedException {
		// STEP_DONE: select the company
		getIFramePage().switchToContentFrame();
		return DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Copmany",
				PL_Filter.btnDropdownComapnyCommon, PL_Filter.searchboxCompanyCommon, PL_Filter.listCompanyCommon,
				companyName, PL_Filter.textAlradySelectedCompanyDropdownCommon);
	}

	@Step("Select the branch present at left hand side")
	public void selectBranch(String branchName) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Branch",
				PL_Filter.btnDropdownBranchLHS, PL_Filter.searchboxBranchLHS, PL_Filter.listBranchLHS, branchName,
				PL_Filter.textAlradySelectedBranchDropdownLHS);

	}

	@Step("Select the branch work as common")
	public void selectBranchCommon(String branchName) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Branch",
				PL_Filter.btnDropdownBranchCommon, PL_Filter.searchboxBranchCommon, PL_Filter.listBranchCommon,
				branchName, PL_Filter.textAlradySelectedBranchDropdownCommon);

	}

	@Step("Select the vehicle status common")
	public void selectStatusCommon(String vehicleStatus) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Vehicle Status",
				PL_Filter.btnVehicleStatusCommon, PL_Filter.searchboxVehicleStatusCommon,
				PL_Filter.listVehicleStatusCommon, vehicleStatus, PL_Filter.textAlreadyDropdownVehicleStatusCommon);

	}

	@Step("Select the vehicle status")
	public void selectStatus(String vehicleStatus) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Vehicle Status",
				PL_Filter.btnVehicleStatus, PL_Filter.searchboxVehicleStatus, PL_Filter.listVehicleStatus,
				vehicleStatus, PL_Filter.textAlreadyDropdownVehicleStatus);

	}

	@Step("Select the vehicle status with checkbox type selection")
	public void selectStatusWithCheckbox(String vehicleStatus) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver,
				"Vehicle Status With Checkbox Type", PL_Filter.btnStatusWithCheckboxCommon,
				PL_Filter.searchboxStatusWithCheckboxCommon, PL_Filter.listStatusWithCheckboxCommon, vehicleStatus,
				PL_Filter.textAlreadyDropdownStatusWithCheckboxCommon);

	}

	@Step("Select the country with checkbox type selection")
	public void selectCountryWithCheckbox(String vehileCountry) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver,
				"Vehicle Country With Checkbox Type", PL_Filter.btnCountryWithCheckboxCommon,
				PL_Filter.searchboxCountryWithCheckboxCommon, PL_Filter.listCountryWithCheckboxCommon, vehileCountry,
				PL_Filter.textAlreadyDropdownCountryWithCheckboxCommon);

	}

	@Step("Select the x hours common")
	public void selectXHoursCommon(String xHours) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "X Hours",
				PL_Filter.btnXHoursCommon, PL_Filter.searchboxXHoursCommon, PL_Filter.listXHoursCommon, xHours,
				PL_Filter.textAlreadyDropdownXHoursCommon);

	}

	@Step("Select the to time common")
	public void selectToTimeCommon(String toTime) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "To Time",
				PL_Filter.btnToTimeCommon, PL_Filter.searchboxToTimeCommon, PL_Filter.listToTimeCommon, toTime,
				PL_Filter.textAlreadyDropdownToTimeCommon);
	}

	@Step("Select the from time common")
	public void selectFromTimeCommon(String fromTime) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "From Time",
				PL_Filter.btnFromTimeCommon, PL_Filter.searchboxFromTimeCommon, PL_Filter.listFromTimeCommon, fromTime,
				PL_Filter.textAlreadyDropdownFromTimeCommon);
	}

	@Step("Select the trip calculation for")
	public void selectTripCalculationFor(String tripCalculationFor) throws InterruptedException {
		// STEP_DONE: select the branch
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver,
				"Trip Calculation For", PL_Filter.btnTripCalculationFor, PL_Filter.searchboxTripCalculationFor,
				PL_Filter.listTripCalculationFor, tripCalculationFor, PL_Filter.textAlreadyDropdownTripCalculationFor);

	}

	@Step("Select alert type work as common")
	public void selectAlertTypeCommon(String alertType) throws InterruptedException {
		// STEP_DONE: select the alert type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Alert Type",
				PL_Filter.btnAlertTypeCommon, PL_Filter.searchboxAlertTypeCommon, PL_Filter.listAlertTypeCommon,
				alertType, PL_Filter.textAlreadySelectedAlertTypeCommon);
	}

	@Step("Select alert selection with checkbox common")
	public void selectAlertSelectionCommon(String alertType) throws InterruptedException {
		// STEP_DONE: select the alert type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Alert Selection",
				PL_Filter.btnAlertSelectionCommon, PL_Filter.searchboxAlertSelectionCommon,
				PL_Filter.listAlertSelectionCommon, alertType, PL_Filter.textAlreadySelectedAlertSelectionCommon);
	}

	@Step("Select object rating common")
	public void selectObjectRatingCommon(String objectRating) throws InterruptedException {
		// STEP_DONE: select object rating
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Object Rating",
				PL_Filter.btnObjectRatingCommon, PL_Filter.searchboxObjectRatingCommon,
				PL_Filter.listObjectRatingCommon, objectRating, PL_Filter.textAlreadySelectedObjectRatingCommon);
	}

	@Step("Select shift group common")
	public void selectShiftGroupCommon(String shiftGroup) throws InterruptedException {
		// STEP_DONE: select object rating
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Shift Group",
				PL_Filter.btnShiftGroupCommon, PL_Filter.searchboxShiftGroupCommon, PL_Filter.listShiftGroupCommon,
				shiftGroup, PL_Filter.textAlreadySelectedShiftGroupCommon);
	}

	@Step("Select shift with multiple checkbox selection dropdown common")
	public void selectShiftCommon(String shift) throws InterruptedException {
		// STEP_DONE: select the alert type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Shift",
				PL_Filter.btnShiftCommon, PL_Filter.searchboxShiftCommon, PL_Filter.listShiftCommon, shift,
				PL_Filter.textAlreadySelectedShiftCommon,
				PL_Filter.checkboxShiftWithValueAll);
	}

	@Step("Select state workd as common")
	public void selectStateCommon(String state) throws InterruptedException {
		// STEP_DONE: select the state
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "State",
				PL_Filter.btnStateCommon, PL_Filter.searchboxStateCommon, PL_Filter.listStateCommon, state,
				PL_Filter.textAlreadySelectedStateCommon);
	}

	@Step("Selecgt geofence present at right hand side")
	public void selectGeofenceTypeRHS(String geofenceType) throws InterruptedException {
		// STEP_DONE: select the geofence type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "GeofenceType",
				PL_Filter.btnGeofenceTypeRHS, PL_Filter.searchboxGeofenceTypeRHS, PL_Filter.listGeofenceTypeRHS,
				geofenceType, PL_Filter.textAlreadySelectedGeofenceTypeRHS);

	}

	@Step("Select vehicle group present at left hand side")
	public void selectVehicleGroup(String vehileGroupName) throws InterruptedException {
		// STEP_DONE: select the vehicle group
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Group",
				PL_Filter.btnDropdownVehicleGroupdLHS, PL_Filter.searchboxVehicleLHS, PL_Filter.listVehicleGroupLHS,
				vehileGroupName, PL_Filter.textAlradySelectedVehicleDropdownLHS);

	}

	@Step("Select vehicle type present at left hand side")
	public void selectVehicleType(String vehicleType) throws InterruptedException {
		// STEP_DONE: select the vehicle type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Type",
				PL_Filter.btnVehicleTypeLHS, PL_Filter.searchboxVehicleTypeLHS, PL_Filter.listVehicleTypeLHS,
				vehicleType, PL_Filter.textAlradySelectedVehicleTypeDropdownLHS);

	}

	@Step("Select vehicle group common")
	public void selectVehicleGroupCommon(String vehileGroupName) throws InterruptedException {
		// STEP_DONE: select the vehicle group
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Group",
				PL_Filter.btnVehicleGroup, PL_Filter.searchboxVehicleGroup, PL_Filter.listVehicleGroup, vehileGroupName,
				PL_Filter.textAlreadyDropdownVehicleGroup);

	}

	@Step("Select vehicle type present common")
	public void selectVehicleTypeCommon(String vehicleType) throws InterruptedException {
		// STEP_DONE: select the vehicle type
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Type",
				PL_Filter.btnVehicleType, PL_Filter.searchboxVehicleType, PL_Filter.listVehicleType, vehicleType,
				PL_Filter.textAlreadyDropdownVehicleType);

	}

	@Step("Select vehicle brand present at left hand side")
	public void selectVehicleBrand(String vehilcBandName) throws InterruptedException {
		// STEP_DONE: select the vehicle brand
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Brand",
				PL_Filter.btnVehicleBrandLHS, PL_Filter.searchboxVehicleBrandLHS, PL_Filter.listVehicleBrandLHS,
				vehilcBandName, PL_Filter.textAlradySelectedVehicleBrandDropdownLHS);

	}

	@Step("Select vehicle model present at left hand side")
	public void selectVehicleModel(String vehicleModel) throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Model",
				PL_Filter.btnVehicleModelLHS, PL_Filter.searchboxVehicleModelLHS, PL_Filter.listVehilcleModelLHS,
				vehicleModel, PL_Filter.textAlradySelectedVehicleModelDropdownLHS);

	}

	@Step("Select vehicle brand common")
	public void selectVehicleBrandCommon(String vehilcBandName) throws InterruptedException {
		// STEP_DONE: select the vehicle brand
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Brand",
				PL_Filter.btnVehicleBrand, PL_Filter.searchboxVehicleBrand, PL_Filter.listVehicleBrand, vehilcBandName,
				PL_Filter.textAlreadyDropdownVehicleBrand);

	}

	@Step("Select vehicle model common")
	public void selectVehicleModelCommon(String vehicleModel) throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Model",
				PL_Filter.btnVehicleModel, PL_Filter.searchboxVehicleModel, PL_Filter.listVehicleModel, vehicleModel,
				PL_Filter.textAlreadyDropdownVehicleModel);

	}

	@Step("Select vehicle category common")
	public void selectVehicleCategoryCommon(String vehicleCategory) throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Vehicle Model",
				PL_Filter.btnVehicleCategory, PL_Filter.searchboxVehicleCategory, PL_Filter.listVehicleCategory,
				vehicleCategory, PL_Filter.textAlreadyDropdownVehicleCategory);

	}

	@Step("Select vehicle distance present at left hand side")
	public void selectDistance(String distance) throws InterruptedException {
		// STEP_DONE: select the distance
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Distance",
				PL_Filter.btnDistanceLHS, PL_Filter.searchboxDistanceLHS, PL_Filter.listDistanceLHS, distance,
				PL_Filter.textAlradySelectedDistanceDropdownLHS);

	}

	@Step("Select vehicle distance present at left hand side")
	public void setDistance(String distanceValue) {
		// STEP_DONE: set the distance
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Filter.inputDistanceValueField, distanceValue);
	}

	@Step("Select stop duration present at left hand side")
	public void selectStopDuration(String stopDuration) throws InterruptedException {
		// STEP_DONE: select the distance
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Stop Duration",
				PL_Filter.btnStopDuration, PL_Filter.searchboxStopDuration, PL_Filter.listStopDuration, stopDuration,
				PL_Filter.textAlreadyDropdownStopDuration);

	}

	@Step("Select idle duration present at left hand side")
	public void selectIdleDuration(String idleDuration) throws InterruptedException {
		// STEP_DONE: select the distance
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Idle Duration",
				PL_Filter.btnIdleDuration, PL_Filter.searchboxIdleDuration, PL_Filter.listIdleDuration, idleDuration,
				PL_Filter.textAlreadyDropdownIdleDuration);

	}

	@Step("Select vehicle duration format present at left hand side")
	public void selectDurationFormat(String durationFormat) {
		// STEP_DONE: select the duration format
		getIFramePage().switchToContentFrame();
		if (durationFormat.trim().equalsIgnoreCase("hh:mm")) {
			CommonMethods.selectRadioButton(driver, "HH:MM", PL_Filter.radioButtonHHMM);
		} else if (durationFormat.equalsIgnoreCase("decimal")) {
			CommonMethods.selectRadioButton(driver, "Decimal", PL_Filter.radioButtonDecimal);
		}
	}

	@Step("Select days common")
	public void selectDaysCommon(String daysIntegerValue) throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Days", PL_Filter.btnDays,
				PL_Filter.searchboxDays, PL_Filter.listDays, daysIntegerValue, PL_Filter.textAlreadyDropdownDays);

	}

	@Step("Select status since common")
	public void selectStatusSinceCommon(String daysIntegerValue) throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Days", PL_Filter.btnStatusSince,
				PL_Filter.searchboxStatusSince, PL_Filter.listStatusSince, daysIntegerValue,
				PL_Filter.textAlreadyDropdownStatusSince);

	}

	@Step("Set battery tolerange left hand side")
	public void setBatteryTolerance(String batteryTolerance) {
		// STEP_DONE: set the battery tolerance
		getIFramePage().switchToContentFrame();
		pu.clickOnButtonWithBooleanReturn(driver, PL_Filter.fieldBatteryTolerance);
		pu.setDataInFieldWithClear(driver, PL_Filter.fieldBatteryTolerance, batteryTolerance);
	}

	@Step("Set the speed limit and it's values")
	public void selectAndSetSppedLimitCommon(String speedLimitType, String customValue, String asPerAlertValue)
			throws InterruptedException {
		// STEP_DONE: select the vehicle model
		getIFramePage().switchToContentFrame();
		System.out.println("speedLimitType: " + speedLimitType);
		if (speedLimitType.trim().equalsIgnoreCase("custom")) {
			CommonMethods.selectRadioButton(driver, speedLimitType, PL_Filter.radioButtonCustom);
			pu.setDataInFieldWithClear(driver, PL_Filter.fieldCustomValue, customValue);
		} else if (speedLimitType.trim().replaceAll(" ", "").equalsIgnoreCase("asPerAlert")) {
			CommonMethods.selectRadioButton(driver, speedLimitType, PL_Filter.radioButtonAsPerAlert);
			DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "As Per Alert",
					PL_Filter.btnAsPerAlertDropdown, PL_Filter.searchboxAsPerAlertDropdown,
					PL_Filter.listAsPerAlertDropdown, asPerAlertValue, PL_Filter.textAlreadySelectedAsPerAlertDropdown);
		}
	}

	@Step("Click on save filter button")
	public boolean clickOnSaveFilterButton() {
		// STEP_DONE: click on the save filter button
		getIFramePage().switchToContentFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Commons.btnsaveFilter);

	}

	@Step("Click round off checkbox")
	public void clickOnRoundOffCheckbox() {
		// STEP_DONE: click on the save filter button
		getIFramePage().switchToContentFrame();
		CheckboxHandler.checkCheckboxIfNotSelected(driver, "Round off", PL_Filter.checkboxRoundOff);
	}

	@Step("Click on delete filter button")
	public boolean clickOnDeleteFilterButton() {
		// STEP_DONE: click on the delete filter button
		getIFramePage().switchToContentFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Commons.btnDeleteFilter);
	}

	@Step("Click on update filter button")
	public boolean clickOnUpdateFilterButton() {
		// STEP_DONE: click on the delete filter button
		getIFramePage().switchToContentFrame();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_Commons.btnUpdateFilter);
	}

	@Step("Click on apply filter button at index 2")
	public boolean clickOnAppyFilterButton() {
		// STEP_DONE: click on the apply filter button
		getIFramePage().switchToContentFrame();
		return pu.clickOnButtonIfLocatorIsMultiple(driver, PL_Filter.btnApplyAtFilter, 3);
	}

	@Step("Click on delete filter button present at right hand side")
	public void clickOnAppyFilterButtonRHS() {
		// STEP_DONE: click on the apply filter button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnApplyAtFilterAtIndexOne, 5);
	}

	@Step("Click on apply filter button present at cloud donwnload filter button")
	public void clickOnAppyFilterPrsentAtInsideCloudDonwloadFilerButton() {
		// STEP_DONE: click on the apply filter button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnAppy, 10);
	}

	@Step("Click on download xls button present at filter")
	public void clickOnDownloadXLSFilterButton() {
		// STEP_DONE: click on the download XLS filter button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnXLSDownloadAtFilter, 10);
	}

	@Step("Click on download pdf button present at filter")
	public void clickOnPDFDownlaodFilterButton() {
		// STEP_DONE: click on the PDF Download filter button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnPDFDownloadAtFilter, 10);
	}

	@Step("Click on download csv button present at filter")
	public void clickOnCSVDownlaodFilterButton() {
		// STEP_DONE: click on the CSV Download filter button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnCSVDownloadAtFilter, 10);
	}

	@Step("Click on apply button present at filter for date picker")
	public void clickOnApplyButtonAtDatePickerAtFilter() {
		// STEP_DONE: click on the apply button at date picker at filter
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnApplyAtDatePicker, 10);
	}

	@Step("range speed range ")
	public void changeSpeedRange(String speedRange) {
		// STEP_DONE: click on the apply button at date picker at filter
		getIFramePage().switchToContentFrame();
		try {
			driver.findElement(PL_Filter.rangesContainer);
			System.out.println("Check box already checked");
		} catch (Exception e) {
			pu.clickOnButton(driver, PL_Filter.speedRangeCheckbox, 10);
		}

		String[] speedRanges = speedRange.split(",");
		for (String range : speedRanges) {
			CheckboxHandler.checkCheckboxIfNotSelected(driver, "Speed Ranges",
					PL_Filter.selectSpeedRangeCheckboxXpath(range));
		}

	}

	@Step("Set Red Value")
	public void setRed(String redValue) {
		// STEP_DONE: perform the search object selection
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Filter.fieldRed, redValue);
	}

	@Step("Search object selection")
	public void setYellow(String yellowValue) {
		// STEP_DONE: perform the search object selection
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Filter.fieldYellow, yellowValue);
	}

	@Step("Search object selection")
	public void setGreen(String greenValue) {
		// STEP_DONE: perform the search object selection
		getIFramePage().switchToContentFrame();
		pu.setDataInFieldWithClear(driver, PL_Filter.fieldGreen, greenValue);
	}

	@Step("Click on cancle button present at filter for date picker")
	public void clickOnCancelButtonAtDatePickerAtFilter() {
		// STEP_DONE: click on the cancel button at date picker at filter
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Filter.btnCancelAtDatePicker, 10);
	}

	@Step("Select download report type based on expected values")
	public void selectTheDownlaodRequestFor(String expectedValue) throws InterruptedException {
		// DONE STEP: select the downlaod request for
		getIFramePage().switchToContentFrame();
		DropdownHandler.veriryThenClickAndSearchAndSelectIfNotAlreadySelected(driver, "Download request for",
				PL_Filter.btnDownlaodReportFor, PL_Filter.searchboxDownloadReportFor, PL_Filter.listDownloadRequestFor,
				expectedValue, PL_Filter.textAlreadySelectedDowloadReportFor);
	}

	@Step("Relfection verification on the overview screen as per the filter selected")
	public void verifyTheColumnValuesBasedOnTheGivenTableHeaderName(String overviewScreenColumnNameForDataAsPerCss,
			String expectedListCommaSeperated) {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		CommonMethods.compareListByTextByRemovingDuplicateFromActualListIgnorOrder(driver,
				PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(
						overviewScreenColumnNameForDataAsPerCss.trim()),
				expectedListCommaSeperated);
	}

	@Step("Relfection verification on the overview screen as per the filter selected")
	public void verifyTheColumnValuesBasedOnTheGivenTableHeaderName(String overviewScreenColumnNameForDataAsPerCss,
			String expectedListCommaSeperated, String attributeName) {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		CommonMethods
				.compareListByTextByRemovingDuplicateFromActualListIgnorOrderByAttribute(driver,
						PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(
								overviewScreenColumnNameForDataAsPerCss.trim()),
						expectedListCommaSeperated, attributeName);
	}

	@Step("Relfection verification on the overview screen as per the filter selected")
	public void verifyTheColumnValuesBasedOnTheGivenTableHeaderName(String overviewScreenColumnNameForDataAsPerCss,
			String expectedListCommaSeperated, boolean isIcon) {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		CommonMethods.compareListByTextByRemovingDuplicateFromActualListIgnorOrder(driver,
				PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(
						overviewScreenColumnNameForDataAsPerCss.trim(), isIcon),
				expectedListCommaSeperated);
	}

	@Step("Relfection verification on the overview screen as per the filter selected")
	public void verifyAtualListWithinExpectedListBasedOnTheGivenTableHeaderName(
			String overviewScreenColumnNameForDataAsPerCss, String expectedListCommaSeperated) {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		CommonMethods.compareListByTextCheckAtualListWithExpectedByRemovingDuplicateFromActualListIgnoreOrder(driver,
				PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(
						overviewScreenColumnNameForDataAsPerCss.trim()),
				expectedListCommaSeperated);
	}

	@Step("Relfection verification on the overview screen as per duration formate in filter selected")
	public void verifyDurationFormateForTheGivenTableHeaderName(By locator, String durationFormate) {
		// STEP_DONE: Verify the list of object present on the overview screen based on the selected object in filter
		getIFramePage().switchToContentFrame();
		List<WebElement> listData = driver.findElements(locator);
		String seperator = null;
		if (durationFormate.equalsIgnoreCase("HH:MM")) {
			seperator = ":";
		} else if (durationFormate.equalsIgnoreCase("decimal")) {
			seperator = ".";
		}
		for (WebElement ele : listData) {
			String value = ele.getText();
			System.out.println("selected duration format: " + durationFormate + " and seperator: " + seperator
					+ " acutal value: " + value);
			if (!value.contains(seperator)) {
				TestBase.getSoftAssert()
						.fail("Duratino format not matched expected: " + durationFormate + " and found" + value);
			}
		}
	}

	@Step("To cross verify the distance values on the overview screen as per selected in the filter")
	public void verifyDistanceValuesOnOverviewScreenAsPerSelectedInFilter(Double expectedValueFilledInDistanceValue,
			String durationType, By locator) {
		getIFramePage().switchToContentFrame();
		List<Double> intValues = WebElementList.getVisibleTextFromWebElementList(driver, locator).stream()
				.map(Double::parseDouble) // Convert each String to Integer
				.collect(Collectors.toList()); // Collect into a List

		for (Double value : intValues) {
			System.out.println("durationType: " + durationType + " and expected Value: "
					+ expectedValueFilledInDistanceValue + " actual value: " + value);
			if (durationType.trim().replaceAll(" ", "").equalsIgnoreCase("GreaterThan")) {
				if (!(value > expectedValueFilledInDistanceValue)) {
					TestBase.getSoftAssert().fail("Value not matched expected greater than: "
							+ expectedValueFilledInDistanceValue + " but found: " + value);
				}
			} else if (durationType.trim().replaceAll(" ", "").equalsIgnoreCase("LessThan")) {
				if (!(value < expectedValueFilledInDistanceValue)) {
					TestBase.getSoftAssert().fail("Value not matched expected leses than: "
							+ expectedValueFilledInDistanceValue + " but found: " + value);
				}
			}

		}
	}

	public String dateGeneratorBasedOnDateTypeAndWeekStart(String dateType, String startDate, String endDate,
			String dateSeperator, int weekStartDay, String dateSeperatorAtHeader) {
		SimpleDateFormat sdf = null;
		if (dateSeperatorAtHeader != null) {
			sdf = new SimpleDateFormat("dd" + dateSeperatorAtHeader + "MM" + dateSeperatorAtHeader + "yyyy");
		} else {
			sdf = new SimpleDateFormat("dd-MM-yyyy");
		}

		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();

		switch (dateType.trim().replaceAll(" ", "").toLowerCase()) {
		case "today": {
			startDate = sdf.format(today);
			endDate = startDate;
			break;
		}

		case "yesterday": {
			cal.add(Calendar.DATE, -1);
			startDate = sdf.format(cal.getTime());
			endDate = startDate;
			break;
		}

		case "thisweek": {
			cal.set(Calendar.DAY_OF_WEEK, weekStartDay);
			startDate = sdf.format(cal.getTime());

			// Limit the end date to today
			Calendar endCal = Calendar.getInstance();
			endCal.set(Calendar.DAY_OF_WEEK, weekStartDay);
			endCal.add(Calendar.DATE, 6);
			if (endCal.getTime().after(today)) {
				endDate = sdf.format(today);
			} else {
				endDate = sdf.format(endCal.getTime());
			}
			break;
		}

		case "lastweek": {
			cal.add(Calendar.WEEK_OF_YEAR, -1);
			cal.set(Calendar.DAY_OF_WEEK, weekStartDay);
			startDate = sdf.format(cal.getTime());

			// Limit the end date to today
			cal.add(Calendar.DATE, 6);
			if (cal.getTime().after(today)) {
				endDate = sdf.format(today);
			} else {
				endDate = sdf.format(cal.getTime());
			}
			break;
		}

		case "thismonth": {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf.format(cal.getTime());

			// Limit end date to today
			endDate = sdf.format(today);
			break;
		}

		case "lastmonth": {
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf.format(cal.getTime());

			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			if (cal.getTime().after(today)) {
				endDate = sdf.format(today);
			} else {
				endDate = sdf.format(cal.getTime());
			}
			break;
		}

		case "last7days": {
			cal.add(Calendar.DATE, -6);
			startDate = sdf.format(cal.getTime());
			endDate = sdf.format(today);
			break;
		}

		case "customrange": {
			String startDateTime = startDate.replaceAll(dateSeperator, dateSeperatorAtHeader);
			String endDateTime = endDate.replaceAll(dateSeperator, dateSeperatorAtHeader);

			SimpleDateFormat inputFormat = new SimpleDateFormat(
					"d" + dateSeperatorAtHeader + "M" + dateSeperatorAtHeader + "yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat(
					"dd" + dateSeperatorAtHeader + "MM" + dateSeperatorAtHeader + "yyyy");

			try {
				Date dateStart = inputFormat.parse(startDateTime);
				Date dateEnd = inputFormat.parse(endDateTime);
				String formattedDateStart = outputFormat.format(dateStart);
				String formattedDateEnd = outputFormat.format(dateEnd);
				System.out.println("Custom Start date: " + formattedDateStart + " end Date: " + formattedDateEnd);
				startDate = formattedDateStart;
				endDate = formattedDateEnd;
			} catch (Exception e) {
				System.out.println("Exception while formatting the date to verify the header date");
			}
			break;
		}

		default: {
			System.out.println("Invalid date type");
			return null;
		}
		}
		System.out.println("Start date: " + startDate + " | End date: " + endDate);
		return (startDate + "|" + endDate);
	}

	@Description("This is used to to check the reflection on the overview screen for the selected filter")
	@Feature("Filter reflection verification on the overview screen")
	public void verifyTheFilterReflectionAsPerTheSelection(Map<String, Object> args)
			throws InterruptedException, ParseException {
		args = StringHandling.convertKeysToCamelCase(args);
		String pageName = System.getProperty("pageName").toLowerCase().trim().replaceAll(" ", "");
		Set<String> filedNames = args.keySet();
		System.out.println("List of filed present as per given data: " + filedNames);
		for (String field : filedNames) {
			if (!"na".equalsIgnoreCase((String) args.get(field)) && !"all".equalsIgnoreCase((String) args.get(field))) {
				String cssNameBasedOnColumnName = voidGetColumnNameAsPerCssBasedOnPageName(field, pageName);
				if (cssNameBasedOnColumnName == null) {
					continue;
				}

				switch (field) {
				case "company": {
					if (pageName.equalsIgnoreCase("liveMatrix")) {
						//this is only for the live matrix
						getIFramePage().switchToContentFrame();
						CommonMethods
								.compareListByTextCheckAtualListWithExpectedByRemovingDuplicateFromActualListIgnoreOrder(
										driver, PL_LiveMatrix.listMatrixOnLiveMatrixPage, (String) args.get("company"));
					} else {
						System.out.println(
								"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
						verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
								(String) args.get("company"));
					}
					break;
				}
				case "branch": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("branch"));
					break;
				}
				case "vehicleGroup": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("vehicleGroup"));
					break;
				}
				case "vehicleType": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("vehicleType"));
					break;
				}
				case "vehicleBrand": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("vehicleBrand"));
					break;
				}
				case "vehicleModel": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("vehicleModel"));
					break;
				}
				case "distance": {
					//to catch distance column data
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					Double expectedValues = Double.parseDouble((String) args.get("distanceValue"));
					String distanceDuration = (String) args.get("distance");
					By locatory = PL_Travel.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(
							cssNameBasedOnColumnName.trim());
					verifyDistanceValuesOnOverviewScreenAsPerSelectedInFilter(expectedValues, distanceDuration,
							locatory);
					break;
				}
				case "tripCalculationFor": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("tripCalculationFor"));
					break;
				}

				case "calculationFor": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("calculationFor"));
					break;
				}
				case "stopDuration": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("stopDuration"));
					break;
				}
				case "idleDuration": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("idleDuration"));
					break;
				}
				case "durationFormat": {
					//update this
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					List<String> columnNameInWhichDurationFormatApplicable = new ArrayList<>(Arrays.asList("Running",
							"Idle", "Stop", "Inactive", "Duration", "Work Duration", "Max Stoppage"));
					for (String columnName : columnNameInWhichDurationFormatApplicable) {
						System.out.println(
								"columnName: " + StringHandling.toCamelCase(columnName) + " and pageName: " + pageName);
						String cssName = voidGetColumnNameAsPerCssBasedOnPageName(
								StringHandling.toCamelCase(columnName), pageName);
						System.out.println("Column Name: " + columnName + " and css name: " + cssName);
						if (cssName == null) {
							TestBase.getSoftAssert().fail("Requested column: " + columnName + " css attribute not found"
									+ " for the page name: " + pageName);
							continue;
						}

						By locatory = PL_Travel
								.getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(cssName.trim());
						verifyDurationFormateForTheGivenTableHeaderName(locatory, (String) args.get("durationFormat"));
					}
					break;
				}
				case "vehicleCategory": {
					//update this
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("vehicleCategory"), true);
					break;
				}
				case "status": {
					//update this
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyTheColumnValuesBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("status"), true);
					break;
				}
				case "speedRange": {
					//update this
					POM_SpeedVsDistance.verifyTheSpeedRangeColumnAsPerSelectedInFilter(driver, pageName);
					break;
				}
				case "dateSelection": {
					//write code to verify the date range selected
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					if (((String) args.get("dateSelection")).equalsIgnoreCase("true")
							|| ((String) args.get("dateSelection")).equalsIgnoreCase("yes")) {

						String dateSeperatorAtHeader = (String) args.get("dateSeperatorAtHeader");

						String dateRange = dateGeneratorBasedOnDateTypeAndWeekStart((String) args.get("dateType"),
								(String) args.get("customStartDate"), (String) args.get("customEndDate"),
								(String) args.get("dateSeperator"), Calendar.SUNDAY, dateSeperatorAtHeader);

						String dates[] = dateRange.split("\\|");
						String startDate = dates[0].trim();
						String endDate = dates[1].trim();
						System.out.println("Dates are: " + startDate + " " + endDate);
						if (((String) args.get("dateType")).equalsIgnoreCase("CustomRange")) {
							getTravelPage().verifyHeaderDateFromAndTo(startDate, endDate);
						} else {
							getTravelPage().verifyHeaderDateFromAndToWhenDateTypeNotCustomRange(startDate, endDate);
						}
					}

					break;
				}
				case "timeRange": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					if (((String) args.get("timeRange")).equalsIgnoreCase("true")
							|| ((String) args.get("timeRange")).equalsIgnoreCase("yes")) {

						String startTime = ((String) args.get("startTime")).trim();
						String endTime = ((String) args.get("endTime")).trim();
						String seperator = ((String) args.get("timeSeperator")).trim();
						String startTimeAmPm = startTime.substring(6, 8);
						String endTimeAmPm = endTime.substring(6, 8);
						startTime = startTime.substring(0, 5);
						endTime = endTime.substring(0, 5);

						String startTimeFormat = startTime.replaceAll(seperator, ":");
						String endTimeFormat = endTime.replaceAll(seperator, ":");
						String timeRange = startTimeFormat + " " + startTimeAmPm + "-" + endTimeFormat + " "
								+ endTimeAmPm;
						System.out.println("startTimeFormat: " + timeRange);
						getTravelPage().verifyHeaderTimeStartAndEnd(timeRange);
					} else {
						getTravelPage().verifyHeaderDefualtTimeStartAndEnd("12", (String) args.get("dateType"));
					}
					break;
				}
				case "objectSelection": {
					System.out.println(
							"Column Name: " + field + " cssNameBasedOnColumnName: " + cssNameBasedOnColumnName);
					verifyAtualListWithinExpectedListBasedOnTheGivenTableHeaderName(cssNameBasedOnColumnName,
							(String) args.get("objectSelection"));
					break;
				}
				}
			}
		}
		System.out.println("Filter reflection verification end");

	}

	@Description("Get the css column name based on the filter field and report name")
	public String voidGetColumnNameAsPerCssBasedOnPageName(String filterFields, String pageName) {
		//METHOD: This is used to get the column css name, outer switch-case represent the filter field name and inner is column name as per the report name 

		//This is under testing once it working fine, I will remove the below switch cases
		return FilterMapper.getFilterField(filterFields, pageName);
		/*
		
				//filed selection
				switch (filterFields) {
				case "company": {
					switch (pageName) {
					case "todayactivity":
						return "short_name"; //done
					case "travel":
						return "company";
					case "travelhistory":
						return null; //done
					case "trip":
						return "company_name"; //done
					case "stoppage":
						return "company_name"; //done
					case "idle":
						return "company"; //done
					case "inactive":
						return "company_name"; //done
					}
				}
				case "branch": {
					switch (pageName) {
					case "todayactivity":
						return "location_name"; //done
					case "travel":
						return "branch"; //done
					case "travelhistory":
						return null;
					case "trip":
						return "location_name"; //done
					case "stoppage":
						return "location_name"; //done
					case "idle":
						return "branch"; //done
					case "inactive":
						return "location_name"; //done
					}
					break;
				}
				case "vehilceGroup": {
					switch (pageName) {
					case "todayactivity":
						return null; //done
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "object_brand"; //done
					case "stoppage":
						return "object_brand"; //done
					}
					break;
				}
				case "vehilceType": {
					switch (pageName) {
					case "todayactivity":
						return null; //done
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "company_name"; //done
					case "stoppage":
						return "object_brand";
					}
					break;
				}
				case "vehicleBrand": {
					switch (pageName) {
					case "todayactivity":
						return "object_brand"; //done
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "object_brand"; //done
					case "stoppage":
						return "object_brand"; //done
					case "idle":
						return "object_brand"; //done
					case "inactive":
						return "object_brand"; //done
					}
					break;
				}
		
				case "vehilceModel": {
					switch (pageName) {
					case "todayactivity":
						return "object_model"; //done
					case "travel":
						return "object_model";
					case "travelhistory":
						return null;
					case "trip":
						return "object_model"; //done
					case "stoppage":
						return "object_model"; //done
					case "idle":
						return "object_model"; //done
					case "inactive":
						return "object_model"; //done
					}
					break;
				}
				case "distance": {
					switch (pageName) {
					case "todayactivity":
						return "total_running_km"; //done
					case "travel":
						return "running_km";
					case "travelhistory":
						return null;
					case "trip":
						return "total_running_km"; //done
					}
					break;
				}
				case "tripCalculationFor": {
					switch (pageName) {
					case "todayactivity":
						return "location_name";
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					}
					break;
				}
		
				case "calculationFor": {
					switch (pageName) {
					case "todayactivity":
						return "location_name";
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					}
					break;
				}
				case "stopDuration": {
					switch (pageName) {
					case "todayactivity":
						return "location_name";
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "company_name";
					}
					break;
				}
				case "idleDuration": {
					switch (pageName) {
					case "todayactivity":
						return "location_name";
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "company_name";
					}
					break;
				}
				case "durationFormat": {
					return "na"; // not applicable
				}
				case "idle": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "idle_duration";
					case "travelhistory":
		
					case "trip":
					case "stoppage":
						return "total_idle_duration";
					}
					break;
				}
				case "stop": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "stop_duration";
					case "travelhistory":
		
					case "trip":
					case "stoppage":
						return "total_stop_duration";
					}
					break;
				}
				case "inactive": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "inactive_duration";
					case "travelhistory":
		
					case "trip":
		
					}
					break;
				}
				case "running": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "running_duration";
					case "travelhistory":
		
					case "trip":
					case "stoppage":
						return "total_running_duration";
		
					}
					break;
				}
				case "duration": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "total_duration";
					case "travelhistory":
		
					case "trip":
		
					}
					break;
				}
				case "workDuration": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "work_duration";
					case "travelhistory":
		
					case "trip":
		
					}
					break;
				}
				case "maxStoppage": {
					switch (pageName) {
					case "todayactivity":
		
					case "travel":
						return "max_stop_duration";
					case "travelhistory":
		
					case "trip":
					case "stoppage":
						return "max_stop_duration";
					}
					break;
				}
				case "status": {
					switch (pageName) {
					case "todayactivity":
						return "vehicle_status"; //done
					case "travel":
						return "branch";
					case "travelhistory":
						return null;
					case "trip":
						return "company_name";
					}
					break;
				}
				case "dateSelection": {
					return "na";
				}
				case "timeRange": {
					return "na";
				}
				case "objectSelection": {
					switch (pageName) {
					case "todayactivity":
						return "vehicle_info"; //done
					case "travel":
						return "vehicle_information"; //done
					case "travelhistory":
						return null;
					case "trip":
						return "vehicle_info";
					case "stoppage":
						return "vehicle_info";
					case "inactive":
						return "vehicle_information";
					}
					break;
				}
				}
				return null;
				
				*/
	}

}