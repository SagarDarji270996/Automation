package projects.trakzee.web.pages.common.header;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.PL_ScheduleReport;
import projects.trakzee.web.locators.common.PL_Setting;
import projects.trakzee.web.projectUtility.CheckboxHandler;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.DropdownHandler;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.RowListHandling;
import projects.trakzee.web.projectUtility.ScrollAction;
import projects.trakzee.web.projectUtility.StringHandling;
import projects.trakzee.web.projectUtility.XpathExtractor;

public class POM_ScheduleReport implements InitializePages {

	private WebDriver driver = TestBase.getWebDriver();

	public POM_ScheduleReport(WebDriver driver) {
		this.driver = driver;
	}

	ProjectUtility pu = new ProjectUtility();

	@Description("This is used to hanlde the schedule reports functionality irrespective of pages by passing the page name and possible arguments present on pages.")
	@Feature("Hanlde end to end schedule reports functionality for all the pages throught out the project")
	public boolean schdeulReportFunctionalityForAllPagesBasedOnField(Map<String, Object> args)
			throws InterruptedException {
		args = StringHandling.convertKeysToCamelCase(args);
		Thread.sleep(2000);
		getTravelPage().clickOnScheduleIconButtonAndVerifyTooltip();
		clickOnCreateScheduleReportButton();
		boolean isScheduleReportStatusIsActive = false;

		Set<String> filedNames = args.keySet();
		boolean isClickedOnActionButton = false;
		System.out.println("List of filed present as per given data: " + filedNames);
		for (String field : filedNames) {
			if (!"na".equalsIgnoreCase((String) args.get(field))) {
				switch (field) {
				case "relativeType": {
					selectRelativeTypes((String) args.get("relativeType"));
					break;
				}
				case "wantToSelectScheduleType": {
					selectScheduleTypes((String) args.get("scheduleTypeDays"), (String) args.get("scheduleTypeDate"));
					break;
				}
				case "wantToSelectTimeRange": {
					if (((String) args.get("wantToSelectTimeRange")).equalsIgnoreCase("true")
							|| ((String) args.get("wantToSelectTimeRange")).equalsIgnoreCase("yes")) {
						checkTimeRangeAtScheduleReport();
						selectStartTimeRangeAtScheduleReport((String) args.get("startTime"),
								(String) args.get("timeHourMinuteSeperator"));
						selectEndTimeRangeAtScheduleReport((String) args.get("endTime"),
								(String) args.get("timeHourMinuteSeperator"));
					}
					break;
				}
				case "reportGenerationTime": {
					if (((String) args.get("reportGenerationTime")).equalsIgnoreCase("true")) {
						LocalTime localTime = LocalTime.now().plusMinutes(1);
						System.setProperty("reportGenerationTime", localTime.toString());
						String reportGenerationTime = localTime.toString().substring(0, 5);
						System.out.println("Report generation time selected: " + reportGenerationTime);
						selectReportGenerationTime(reportGenerationTime);
					}

					break;
				}
				case "timeZone": {
					selectTimezone((String) args.get("timeZone"));
					break;
				}
				case "reportType": {
					selectReportType((String) args.get("reportType"));
					break;
				}
				case "actionType": {
					selectActionTypeAndFillData((String) args.get("actionType"), (String) args.get("emailAddress"),
							(String) args.get("message"), (String) args.get("subject"),
							(String) args.get("hostAddress"), (String) args.get("port"), (String) args.get("username"),
							(String) args.get("password"), (String) args.get("folderName"),
							(String) args.get("plateform"), (String) args.get("accessKeyId"),
							(String) args.get("seceretAcesssKey"), (String) args.get("region"),
							(String) args.get("bucketName"), (String) args.get("folderNameSFTP"),
							(String) args.get("gateway"), (String) args.get("recipientNumber"));
					break;
				}
				case "fileFormate": {
					selectFileFormat((String) args.get("fileFormat"));
					break;
				}
				case "reportName": {
					setReportName((String) args.get("reportName"));
					break;
				}

				case "scheduleReportSubject": {
					//This is used for the cross verification once email received at TC_GMail class
					System.setProperty("scheduleReportSubject", (String) args.get("scheduleReportSubject"));
					break;
				}
				case "attachmentTitle": {
					System.setProperty("attachmentTitle", (String) args.get("attachmentTitle"));
					break;
				}
				case "actionButton": {
					if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("save")) {
						isClickedOnActionButton = clickOnSaveButton();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("delete")) {
						isClickedOnActionButton = clickOnDeleteButton();
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
					getSchedulePage().verifyCreatedScheduleReportPresenceInList((String) args.get("reportName"));
					isScheduleReportStatusIsActive = getScheduleReportStatus((String) args.get("reportName"));
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

		return isClickedOnActionButton && isScheduleReportStatusIsActive;

	}

	public void verifyThatUserAbleToCreateShceduleReportWithoutAdvanceConfiguration(String startTime, String endTime,
			String timeHourMinuteSeperator, String reportGenerationTime, String timezone, String reportType,
			String actionType, String email, String emailMessage, String emailSubject, String hostAddress, String port,
			String username, String password, String foldername, String plateform, String accessKeyId, String accessKey,
			String region, String bucketName, String foldernameSFTP, String gateway, String recipientNumber,
			String fileFormat, String reportName)
			throws InterruptedException {
		//	 SCENARIOS_DONE: verify that user able to create shcedule report without advance configuration
		getTravelPage().clickOnScheduleIconButtonAndVerifyTooltip();
		clickOnCreateScheduleReportButton();
		selectRelativeTypes();
		selectScheduleTypes();
		checkTimeRangeAtScheduleReport();
		selectStartTimeRangeAtScheduleReport(startTime, timeHourMinuteSeperator);
		selectEndTimeRangeAtScheduleReport(endTime, timeHourMinuteSeperator);
		selectReportGenerationTime(reportGenerationTime);
		selectTimezone(timezone);
		selectReportType(reportType);
		selectActionTypeAndFillData(actionType, email, emailMessage, emailSubject, hostAddress, port, username,
				password, foldername, plateform, accessKeyId, accessKey, region, bucketName, foldernameSFTP, gateway,
				recipientNumber);
		selectFileFormat(fileFormat);
		setReportName(reportName);
		clickOnSaveButton();
	}

	public void verifyTheAllTheErrorMessageHandlingForTheScheduleReport() {
		// TODO SCENARIOS: verify the all the error message handling for the schedule report

	}

	String expectedColumnNamesList = "Column,Sortable,Printable,Showable,Sort Priority,Sorting";

	public void verifyThatUserAbleToHandleAdvanceConfiguration(String advanceConfigurationPageTitle,
			String expectedColumnNamesList, String listOfRowEntriesTitleCommaSeperated, String searchKey,
			String sortingType, String downloadReportType, String expectedScheduleReport) throws InterruptedException {
		// SCENARIOS_DONE: verify that user able to handle advance configuration
		getTravelPage().clickOnScheduleIconButtonAndVerifyTooltip();
		clickOnCreateScheduleReportButton();
		clickOnAdvanceConfigurationButton();
		checkPageTitle(advanceConfigurationPageTitle);
		clickOnCrossIconButtonAtAdvanceConfigurationPage();
		clickOnAdvanceConfigurationButton();

		//to click on delete button and verify
		clickOnDeleteIconButtonAtAdvanceConfigurationPage();
		Thread.sleep(3000);
		getTravelPage().clickOnScheduleIconButtonAndVerifyTooltip();
		clickOnCreateScheduleReportButton();
		clickOnAdvanceConfigurationButton();
		clickOnRefreshIconButtonAtAdvanceConfigurationPage();
		clickOnSaveIconButtonAtAdvanceConfigurationPage();
		clickOnAdvanceConfigurationButton();

		dragAndDropColumnRowListAtAdvanceConfigurationSettingsPage();
		verifyColumnHeadingTitle(expectedColumnNamesList);
		verifyRowEntriesTitleUnderTheColumn(listOfRowEntriesTitleCommaSeperated);
		checkShowableCheckbox(searchKey);
		checkPrintableCheckbox(searchKey);
		checkSortableCheckbox(searchKey);
		selectSortPriority(searchKey);
		selectSorting(searchKey, sortingType);
		scrollAdvanceConfigurationRowList(); //not working
		selectDetailsReportsDownloadSingleOrMultiple(downloadReportType);
		clickOnSaveIconButtonAtAdvanceConfigurationPage();
		verifyCreatedScheduleReportPresenceInList(expectedScheduleReport);

	}

	public void verifyTheUiElementPresentOnTheCreateScheduleReportPage_lhs_mdl_rhs_WithoutObjectList() {
		// TODO SCENARIOS: verify the ui element present on the create schedule report page,[LHS][MDL without object list][RHS] only

	}

	public void verifyCreatedScheduleReportPresenceInList(String expectedScheduleReport) {
		// STEP_DONE: verify the created schedule reports present in the list
		getIFramePage().switchToContentFrame();
		CommonMethods.verifyValuePresenceInWebElementList(driver, PL_ScheduleReport.listCreatedScheduleReportTitle,
				expectedScheduleReport);
	}

	public boolean verifyDeletedScheduleReportAbsentInList(String expectedScheduleReport) {
		// STEP_DONE: verify the created schedule reports present in the list
		getIFramePage().switchToContentFrame();
		return CommonMethods.verifyValueAbsentInWebElementList(driver,
				PL_ScheduleReport.listCreatedScheduleReportTitle,
				expectedScheduleReport);
	}

	public boolean getScheduleReportStatus(String reportName) {
		getIFramePage().switchToContentFrame();
		return pu.getTextByAttributeAndVerify(driver, "title", "Schedule Active",
				String.format(PL_ScheduleReport.iconScheduleReportStatusStringFormat, reportName));
	}

	public void clickOnCreateScheduleReportButton() {
		// STEP_DONE: click on the create schedule report button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_ScheduleReport.btnCreateScheduleReport, 10);
	}

	public void clickOnCreatedScheduleReportText(String reportName) {
		// STEP_DONE: click on the create schedule report button
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver,
				String.format(PL_ScheduleReport.textCreatedScheduleReportTitleStringFormat, reportName));
	}

	public void clickOnAdvanceConfigurationButton() throws InterruptedException {
		// STEP_DONE: click on the advance configuration button
		getIFramePage().switchToScheduleScreen();
		ScrollAction.scrollElementTillNotVisible(driver, PL_ScheduleReport.btnAdvanceConfigurations);
		pu.clickOnButton(driver, PL_ScheduleReport.btnAdvanceConfigurations);
	}

	public void selectRelativeTypes(String relativeType) {
		// STEP_DONE: select relative types
		getIFramePage().switchToScheduleScreen();
		pu.clickOnButton(driver, PL_ScheduleReport.btnRelativeTypes, 10);
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listRelativeTypes, relativeType);

	}

	public void selectRelativeTypes() {
		// STEP_DONE: select relative types
		getIFramePage().switchToScheduleScreen();
		pu.clickOnButton(driver, PL_ScheduleReport.btnRelativeTypes, 10);
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listRelativeTypes);

	}

	public void selectScheduleTypes(String days, String date) throws InterruptedException {
		// STEP_DONE: select schedule types
		getIFramePage().switchToScheduleScreen();
		String scheduleType = pu.getElementText(driver, PL_ScheduleReport.listScheduleTypes);
		switch (scheduleType) {
		case "Every Week": {
			selectWeekDays(days);
		}
		case "Every Month": {
			selectDays(date);
		}
		}

	}

	public void selectScheduleTypes() throws InterruptedException {
		// STEP_DONE: select schedule types
		getIFramePage().switchToScheduleScreen();
		String scheduleType = pu.getElementText(driver, PL_ScheduleReport.listScheduleTypes);
		switch (scheduleType) {
		case "Every Week": {
			selectWeekDays();
		}
		case "Every Month": {
			selectDays();
		}
		}

	}

	public void selectWeekDays(String days) {
		// STEP_DONE: select week days
		getIFramePage().switchToScheduleScreen();
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listWeekDays, days);
	}

	public void selectDays(String date) {
		// STEP_DONE: select days
		getIFramePage().switchToScheduleScreen();
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listDatePicker, date);
	}

	public void selectWeekDays() {
		// STEP_DONE: select week days
		getIFramePage().switchToScheduleScreen();
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listWeekDays);
	}

	public void selectDays() {
		// STEP_DONE: select days
		getIFramePage().switchToScheduleScreen();
		CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listDatePicker);
	}

	boolean isTimeRangeChecked = false;

	public void checkTimeRangeAtScheduleReport() {
		// STEP_DONE: check time range
		getIFramePage().switchToScheduleScreen();
		isTimeRangeChecked = CheckboxHandler.checkCheckboxIfNotSelected(driver, "Time Range",
				PL_ScheduleReport.checkBoxTimeRange);
	}

	public void selectStartTimeRangeAtScheduleReport(String startTime, String timeHourMinuteSeperator)
			throws InterruptedException {
		// STEP_DONE: select start time range

		if (isTimeRangeChecked) {
			getIFramePage().switchToScheduleScreen();
			String[] startTimeData = startTime.split(timeHourMinuteSeperator);

			pu.clickOnButton(driver, PL_ScheduleReport.fieldTimeRangeFrom, 10);

			// To select from/start time
			pu.selectElementFromList(driver, PL_ScheduleReport.listHoursPicker, startTimeData[0].trim());
			pu.selectElementFromList(driver, PL_ScheduleReport.listMinutePicker, startTimeData[1].trim());
			pu.selectElementFromList(driver, PL_ScheduleReport.listAM_PM, startTimeData[2].trim());
		}

	}

	public void selectEndTimeRangeAtScheduleReport(String endTime, String timeHourMinuteSeperator)
			throws InterruptedException {
		// STEP_DONE: select end time range
		if (isTimeRangeChecked) {
			getIFramePage().switchToScheduleScreen();

			String[] endTimeData = endTime.split(timeHourMinuteSeperator);
			// To select to/end time
			pu.clickOnButton(driver, PL_ScheduleReport.fieldTimeRangeTo, 10);

			pu.selectElementFromList(driver, PL_ScheduleReport.listHoursPicker, endTimeData[0].trim());
			pu.selectElementFromList(driver, PL_ScheduleReport.listMinutePicker, endTimeData[1].trim());
			pu.selectElementFromList(driver, PL_ScheduleReport.listAM_PM, endTimeData[2].trim());

		}
	}

	public void selectReportGenerationTime(String reportGenerationTime) throws InterruptedException {
		// STEP_DONE: select report generation time
		getIFramePage().switchToScheduleScreen();
		pu.clickOnButton(driver, PL_ScheduleReport.btnReportGenerationTime, 10);
		pu.setDataInField(driver, PL_ScheduleReport.searchboxReportGenerationTime, reportGenerationTime, 15);
		pu.selectElementFromList(driver, PL_ScheduleReport.listReportGenerationTime, reportGenerationTime.trim());
	}

	public void selectTimezone(String timezone) throws InterruptedException {
		// STEP_DONE: select timezone
		getIFramePage().switchToScheduleScreen();
		DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Time Zone",
				PL_ScheduleReport.btnTimeZone, PL_ScheduleReport.searchboxTimeZone, PL_ScheduleReport.listTimeZone,
				timezone, PL_ScheduleReport.textAlreadySelectedTimeZone);
		//		DropdownHandler.clickAndSearchAndSelectDropdownValueBySelectClass(driver, PL_ScheduleReport.btnTimeZone,
		//				PL_ScheduleReport.searchboxTimeZone, PL_ScheduleReport.listTimeZone, timezone);
	}

	public void selectReportType(String reportType) throws InterruptedException {
		// STEP_DONE: select report type
		getIFramePage().switchToScheduleScreen();
		DropdownHandler.selectElementFromList(driver, PL_ScheduleReport.listReportType, reportType);
	}

	public void selectActionTypeAndFillData(String actionType, String email, String emailMessage, String emailSubject,
			String hostAddress, String port, String username, String password, String foldername, String plateform,
			String accessKeyId, String accessKey, String region, String bucketName, String foldernameSFTP,
			String gateway, String recipientNumber)
			throws InterruptedException {
		// STEP_DONE: select action type and fill data
		String actionTypeSelected;
		getIFramePage().switchToScheduleScreen();
		actionTypeSelected = DropdownHandler.selectElementFromListWithStringReturn(driver,
				PL_ScheduleReport.listActionType, actionType);
		//		actionTypeSelected = CommonMethods.pickRandomValueFromList(driver, PL_ScheduleReport.listActionType);
		switch (actionTypeSelected.toLowerCase()) {
		case "email": {
			setEmailAddress(email);
			boolean isclikeOnVerifyEmailButton = clickOnVerifyButton();
			if (isclikeOnVerifyEmailButton) {
				if (getCommonMethodPage().getErrorMessage("Email not sent",
						PL_ScheduleReport.textErrorMessageEmailNotSent, 1)) {
					getCommonPage().clickOnJAlertCloseButton();
				}
			}
			setMessage(emailMessage);
			setSubject(emailSubject);
			break;

		}
		case "ftp": {
			setHostAddress(hostAddress);
			setPort(port);
			setUsername(username);
			setPassword(password);
			setFolderName(foldername);
			break;
		}
		case "sftp": {
			selectPlatform(plateform);
			setAccessKeyId(accessKeyId);
			setAccessKey(accessKey);
			setRegion(region);
			setBucketName(bucketName);
			setFolderNameSFTP(foldernameSFTP);
			break;
		}
		case "mediaAPI": {
			selectGateway(gateway);
			setRecipientAddress(recipientNumber);
			break;
		}
		}
	}

	public void setEmailAddress(String email) {
		// STEP_DONE: set email address
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.textAreaEmailAddress, email, 05);

	}

	public boolean clickOnVerifyButton() {
		// STEP_DONE: click on verify button
		getIFramePage().switchToScheduleScreen();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_ScheduleReport.btnVerifyEmail);

	}

	public void setSubject(String subject) throws InterruptedException {
		// STEP_DONE: set email subject
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.fieldEmailSubject, subject);

	}

	public void setMessage(String message) {
		// STEP_DONE: set email message
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.textAreaEmailMessag, message, 05);

	}

	public void setHostAddress(String hostAddress) throws InterruptedException {
		// STEP_DONE: set host address
		getIFramePage().switchToScheduleScreen();
		Thread.sleep(2000);
		pu.setDataInField(driver, PL_ScheduleReport.fieldHostName, hostAddress, 05);

	}

	public void setPort(String port) {
		// STEP_DONE: set port
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.fieldPort, port, 05);

	}

	public void setUsername(String username) {
		// STEP_DONE: set username
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.fieldUserName, username, 05);

	}

	public void setPassword(String password) {
		// STEP_DONE: set password
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.fieldPassword, password, 05);
	}

	public void setFolderName(String foldername) {
		// STEP_DONE: set folder name
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.fieldFolderName, foldername, 05);
	}

	public void setFolderNameSFTP(String foldername) {
		// STEP_DONE: set folder name
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.filedFolderNameForSFTP, foldername, 05);
	}

	public void selectPlatform(String plateform) {
		// STEP_DONE: select platform

		getIFramePage().switchToScheduleScreen();
		String xpath = XpathExtractor.getXpathValueFromByDataType(PL_ScheduleReport.listPlateformStringFormat);
		xpath = String.format(xpath, plateform);
		By newPath = By.xpath(xpath);
		DropdownHandler.clickAndSearchAndSelectRandomlyWithinList(driver, "Plateform",
				PL_ScheduleReport.dropdownBtnPlateform, PL_ScheduleReport.searchboxPlateform, newPath, plateform);
	}

	public void selectGateway(String gateway) {
		// STEP_DONE: select gateway
		getIFramePage().switchToScheduleScreen();
		String xpath = XpathExtractor.getXpathValueFromByDataType(PL_ScheduleReport.listGateway);
		xpath = String.format(xpath, gateway);
		By newPath = By.xpath(xpath);
		DropdownHandler.clickAndSearchAndSelectRandomlyWithinList(driver, "Plateform",
				PL_ScheduleReport.btnGateway, PL_ScheduleReport.searchBoxGateway, newPath, gateway);
	}

	public void setAccessKeyId(String accessKeyId) {
		// STEP_DONE: set access key ID
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.fieldSekretAccessKeyId, accessKeyId, 05);
	}

	public void setRecipientAddress(String recipientAddress) {
		// STEP_DONE: set access key ID
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.textAreaRecipientNumber, recipientAddress, 05);
	}

	public void setAccessKey(String accessKey) {
		// STEP_DONE: set access key
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.filedSecretAccessKey, accessKey, 05);
	}

	public void setRegion(String region) {
		// STEP_DONE: set region
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.fieldRegion, region, 05);
	}

	public void setBucketName(String bucketName) {
		// STEP_DONE: set bucket name
		getIFramePage().switchToScheduleScreen();
		pu.setDataInField(driver, PL_ScheduleReport.feildBucketName, bucketName, 05);
	}

	public void selectFileFormat(String fileFormate) throws InterruptedException {
		// STEP_DONE: select file format
		getIFramePage().switchToScheduleScreen();
		pu.selectElementFromList(driver, PL_ScheduleReport.listFileFormat, fileFormate);

	}

	public void setReportName(String reportName) {
		// STEP_DONE: set report name
		getIFramePage().switchToScheduleScreen();

		pu.setDataInField(driver, PL_ScheduleReport.fieldReportName, reportName, 05);
	}

	public boolean clickOnSaveButton() {
		// STEP_DONE: click on save button
		getIFramePage().switchToScheduleScreen();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_ScheduleReport.btnSave);
	}

	public void clickOnCancelButton() {
		// STEP_DONE: click on cancel button
		getIFramePage().switchToScheduleScreen();
		pu.clickOnButton(driver, PL_ScheduleReport.btnCancel, 10);
	}

	@Step("Click on delete button to delete the schedule reports")
	public boolean clickOnDeleteButton() {
		// STEP_DONE: click on delete button
		getIFramePage().switchToScheduleScreen();
		return pu.clickOnButtonWithBooleanReturn(driver, PL_ScheduleReport.btnDelete);
	}


	public void checkPageTitle(String advanceConfigurationPageTitle) throws InterruptedException {
		//STEP_DONE: check page title
		getIFramePage().switchToDivFrame();
		CommonMethods.getElementTextAndCompare(driver, PL_Setting.textTitleOfDragableMiniwindow,
				advanceConfigurationPageTitle);
	}

	public void clickOnCrossIconButtonAtAdvanceConfigurationPage() {
		//TS STEP_DONE: click on the cross icon button present at the advance configuration page
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_ScheduleReport.AdvanceScheduleReport.btnCrossIcon);
	}

	public void verifyColumnHeadingTitle(String expectedColumnNamesList) {
		//STEP_DONE: verify column heading title
		getIFramePage().switchToPopUpDialogFrame();
		CommonMethods.compareListByText(driver, PL_ScheduleReport.AdvanceScheduleReport.listHeaderTitle,
				expectedColumnNamesList);

	}

	public void checkShowableCheckbox(String searchKey) throws InterruptedException {
		// STEP_DONE: check the showable checkbox
		getIFramePage().switchToPopUpDialogFrame();

		String[] actionColumnIndexesXpathVerticalFlow = { PL_Setting.listShowAbleCheckboxCommon };
		int[] actionColumnIndex = { 2 };
		RowListHandling.clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(driver,
				PL_Setting.listRowEntriesWithCheckboxOnly, 1, searchKey, actionColumnIndex,
				actionColumnIndexesXpathVerticalFlow);
	}

	public void checkPrintableCheckbox(String searchKey) throws InterruptedException {
		// STEP_DONE: check the printable checkbox
		getIFramePage().switchToPopUpDialogFrame();
		String[] actionColumnIndexesXpathVerticalFlow = { PL_Setting.listPrintAbleCheckboxCommon };
		int[] actionColumnIndex = { 3 };
		RowListHandling.clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(driver,
				PL_Setting.listRowEntriesWithCheckboxOnly, 1, searchKey, actionColumnIndex,
				actionColumnIndexesXpathVerticalFlow);

	}

	public void checkSortableCheckbox(String searchKey) throws InterruptedException {
		// STEP_DONE: check the sortable checkbox
		getIFramePage().switchToPopUpDialogFrame();
		String[] actionColumnIndexesXpathVerticalFlow = { PL_Setting.listSortAbleCheckboxCommon };
		int[] actionColumnIndex = { 4 };
		RowListHandling.clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(driver,
				PL_Setting.listRowEntriesWithCheckboxOnly, 1, searchKey, actionColumnIndex,
				actionColumnIndexesXpathVerticalFlow);
	}

	public void selectSortPriority(String searchKey) throws InterruptedException {
		// STEP_DONE: select the sort priority
		getIFramePage().switchToPopUpDialogFrame();
		// Then click on sort-priority
		int rowCount = RowListHandling.findElementFromRowListAndClickOnAnyColumnElement(driver,
				PL_Setting.listSettingColumns, PL_Setting.listSortSequenceCommon, searchKey, 1, true, false);

		//		int rowCount = RowListHandling.findElementFromRowListAndSelectCheckboxIfNotSelected(driver,
		//				PL_Setting.listSettingColumns, PL_Setting.listSortSequenceCommon, searchKey.trim(), 1);

		String xpathSortPriority = "(" + XpathExtractor.getXpathValueFromByDataType(PL_Setting.listSortPriority) + ")["
				+ rowCount + "]";
		System.out.println("xpathSortPriority: " + xpathSortPriority);
		// To pick the one option from the list
		DropdownHandler.clickAndSelectDropdownValueBySelectClass(driver, xpathSortPriority,
				PL_Setting.listSortPrioritySelectTag, "2");
	}

	public void selectSorting(String searchKey, String sortingType) throws InterruptedException {
		// STEP_DONE: select the sorting
		getIFramePage().switchToPopUpDialogFrame();
		if (sortingType.equalsIgnoreCase("des")) {
			int matchedRowCount = RowListHandling.getMatchedRowListCount(driver, PL_Setting.listSettingColumns,
					searchKey.trim(), 1);
			matchedRowCount++;
			String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Setting.listSortingDescendingCommon);
			pu.clickOnButton(driver, "(" + xpath + ")[" + matchedRowCount + "]");
		} else if (sortingType.equalsIgnoreCase("asc")) {
			int matchedRowCount = RowListHandling.getMatchedRowListCount(driver, PL_Setting.listSettingColumns,
					searchKey.trim(), 1);
			matchedRowCount++;
			String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Setting.listSortingAscendingCommon);
			pu.clickOnButton(driver, "(" + xpath + ")[" + matchedRowCount + "]");
		}

	}

	public void scrollAdvanceConfigurationRowList() throws InterruptedException {
		// STEP_DONE: scroll the advance configuration row list
		getIFramePage().switchToPopUpDialogFrame();

	}

	public void selectDetailsReportsDownloadSingleOrMultiple(String downloadReportType) {
		// STEP_DONE: select details reports download (single or multiple)
		getIFramePage().switchToPopUpDialogFrame();
		CommonMethods.selectRadioButton(driver, "Details report download",
				PL_Setting.selectDetailReportDownloadRadioButtonLocatory(downloadReportType));
	}

	public void dragAndDropColumnRowListAtAdvanceConfigurationSettingsPage() {
		// STEP_DONE: drag and drop the column row list at the advance configuration settings page
		getIFramePage().switchToPopUpDialogFrame();
		String xpath = XpathExtractor
				.getXpathValueFromByDataType(PL_Setting.handlerIconForDragAndDropRowEntriesStringFormat);
		CommonMethods.dragAndDropInSamePlane(driver, String.format(xpath, "5"), 20, 0);
	}

	public void clickOnSaveIconButtonAtAdvanceConfigurationPage() {
		// STEP_DONE: click on the save icon button at the advance configuration page
		getIFramePage().switchToPopUpDialogFrame();
		pu.clickOnButton(driver, PL_ScheduleReport.AdvanceScheduleReport.btnSaveIcon);

	}

	public void clickOnRefreshIconButtonAtAdvanceConfigurationPage() {
		// STEP_DONE: click on the refresh icon button at the advance configuration page
		getIFramePage().switchToPopUpDialogFrame();
		pu.clickOnButton(driver, PL_ScheduleReport.AdvanceScheduleReport.btnRestoreIcon);

	}

	public void clickOnDeleteIconButtonAtAdvanceConfigurationPage() {
		// STEP_DONE: click on the delete icon button at the advance configuration page
		getIFramePage().switchToPopUpDialogFrame();
		pu.clickOnButton(driver, PL_ScheduleReport.AdvanceScheduleReport.btnDeleteIcon);

	}

	public void verifyRowEntriesTitleUnderTheColumn(String listOfRowEntriesTitleCommaSeperated) {
		// STEP_DONE: verify row entries title under the column
		getIFramePage().switchToPopUpDialogFrame();
		String expectedColumnSettingTitlte = listOfRowEntriesTitleCommaSeperated;
		CommonMethods.compareListByText(driver, PL_Setting.listSettingColumns, expectedColumnSettingTitlte);

	}

}
