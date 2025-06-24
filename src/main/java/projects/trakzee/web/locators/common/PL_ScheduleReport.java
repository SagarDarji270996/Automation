package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_ScheduleReport {
	static String uniqueVisibleText;
	// Schedule Reports
	public static final By btnCreateScheduleReport = By.xpath("//span[@id='schedule_title_span']");
	public static final By listFieldPresentForScheduleReportSection = By.xpath(
			"//td[@class='TableRowContent1' and not(ancestor::*[contains(@style, 'display: none')]) and not(ancestor::*[contains(@style, 'visibility: hidden')])] | //div[@id='adv-schedule-config-id']//a");

	public static final String textCreatedScheduleReportTitleStringFormat = ("//div[contains(@class,'schedule_report')]//label[text()='%s']");
	public static final By listCreatedScheduleReportTitle = By
			.xpath("//div[contains(@class,'created-report')]//label[position()=1]");
	public static final By textCreatedScheduleReportDateTimeReplaceable = By
			.xpath("//div[contains(@class,'schedule_report')]//label[text()='YourText']//following::label[1]");
	public static final By iconScheduleReportStatusReplaceable = By.xpath(
			"//div[contains(@class,'schedule_report')]//label[text()='YourText']//following::div[1]//img[@title]");
	public static final String iconScheduleReportStatusStringFormat = "//div[contains(@class,'schedule_report')]//label[text()='%s']//following::div[1]//img[@title]";

	// Schedule
	public static final By checkBox = By.xpath("//*[@type='checkbox']");

	// Time Range selection
	public static final By checkBoxTimeRange = By
			.xpath("//div[@id='report-generation-time-range']//input[@id='timepicker_filter']");
	public static final By fieldTimeRangeFrom = By.xpath(
			"//div[@id='report-generation-time-range' and not(@style=\"display: none;\")]//input[@id='from-time-picker']");
	public static final By fieldTimeRangeTo = By.xpath(
			"//div[@id='report-generation-time-range' and not(@style=\"display: none;\")]//input[@id='to-time-picker']");
	public static final By listHoursPicker = By
			.xpath("//*[@class='trakzee-time-picker']//div[contains(@class,'ttp-hour')]//div");
	public static final By listMinutePicker = By
			.xpath("//*[@class='trakzee-time-picker']//div[contains(@class,'ttp-min')]//div");
	public static final By listAM_PM = By.xpath("//*[@class='trakzee-time-picker']//div[@class='ttp-aa']//div");

	// Report generation
	public static final By listReportGenerationTime = By.xpath("//div[@id='generate_time_chosen']//li");
	public static final By btnReportGenerationTime = By
			.xpath("//div[@id='generate_time_chosen']//a[@class='chosen-single']");
	public static final By searchboxReportGenerationTime = By
			.xpath("//div[@id='generate_time_chosen']//div[@class='chosen-drop']//input[@type='text']");

	// Time Zone
	public static final By btnTimeZone = By.xpath("//td//span[@title='Timezone']/following-sibling::div");
	public static final By searchboxTimeZone = By.xpath(
			"//td//span[@title='Timezone']/following-sibling::div//div[@class='chosen-drop']//input");
	public static final By listTimeZone = By.xpath(
			"//td//span[@title='Timezone']/following-sibling::div//div[@class='chosen-drop']//ul//li[@class='active-result']");
	public static final By textAlreadySelectedTimeZone = By
			.xpath("//td//span[@title='Timezone']/following-sibling::div//a//span");


	public static final By listReportType = By.xpath("//div[@class='radiobutton screentype']//span");
	public static final By listActionType = By
			.xpath("//div[@class='radiobutton actiontype']//span[not(contains(@style, 'display: none'))]");
	public static final By btnVerifyEmail = By.xpath("//label[@id='verify_email_button']");

	//Relative type
	public static final By btnRelativeTypes = By.xpath("//select[@name='relative_type']");
	public static final By listRelativeTypes = By.xpath("//select[@name='relative_type']//option");

	//Schedule type
	public static final By listScheduleTypes = By
			.xpath("//div[@class='radiobutton reporttype']//span[not(contains(@style, 'display: none'))]");
	public static final By listWeekDays = By.xpath("//ul[@class='filter_control_days']//li");
	public static final By listDatePicker = By.xpath("//div[@class='selectiondatepicker']//ul//li");

	// For the Email Tab
	public static final By textAreaEmailAddress = By.xpath("//textarea[@id='-email']");
	public static final By fieldEmailSubject = By.xpath("//input[@id='-subject']");
	public static final By textAreaEmailMessag = By.xpath("//textarea[@id='-message']");

	// For FTP Tab
	public static final By fieldHostName = By.xpath("//input[@id='-host_address']");
	public static final By fieldPort = By.xpath("//input[@id='-port']");
	public static final By fieldUserName = By.xpath("//input[@id='-username']");
	public static final By fieldPassword = By.xpath("//input[@id='-password']");
	public static final By fieldFolderName = By.xpath("//input[@id='-foldername']");

	// For SFTP Tab
	public static final By dropdownBtnPlateform = By.xpath("//div[@id='sftp_platform_chosen']");
	public static final By searchboxPlateform = By
			.xpath("//div[@id='sftp_platform_chosen']//a[@class='chosen-single']//input[@type='text']");
	public static final By listPlateformStringFormat = By.xpath(
			"//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')][text()='%s']");
	public static final By fieldSekretAccessKeyId = By.xpath("//input[@id='-access_key_id']");
	public static final By filedSecretAccessKey = By.xpath("//input[@id='-secret_access_key']");
	public static final By fieldRegion = By.xpath("//input[@id='-region']");
	public static final By feildBucketName = By.xpath("//input[@id='-bucket_name']");
	public static final By filedFolderNameForSFTP = By.xpath("(//input[@id='-sub_bucket_name'])[1]");

	// For Media API
	public static final By btnGateway = By
			.xpath("//div[@id='social_gateway_chosen']//a[@class='chosen-single']//input[@type='text']");
	public static final By searchBoxGateway = By
			.xpath("//div[@id='social_gateway_chosen']//div[@class='chosen-drop']//input[@type='text']");
	public static final By listGateway = By.xpath(
			"//div[@class='chosen-drop']//ul[@class='chosen-results scrollable-content']//li[@class='active-result highlighted']");
	public static final By textAreaRecipientNumber = By.xpath("//textarea[@id='-recipient_number']");

	public static final By listFileFormat = By.xpath("//ul[@class='fileformatdownload']//li");
	public static final By fieldReportName = By.xpath("//input[@id='-reportname']");

	//Button
	public static final By btnSave = By.xpath("//span[@id='applyfilter']");
	public static final By btnCancel = By.xpath("//span[@id='cancelfilter']");
	public static final By btnDelete = By.xpath("//span[@id='deletefilter']");
	public static final By btnAdvanceConfigurations = By.xpath("//a[normalize-space()='Advance Configuration']");

	public static final By textErrorMessageEmailNotSent = By.xpath("//p[normalize-space()='Email not Sent !!']");

	public class AdvanceScheduleReport {

		// Advance configuration
		public static final By listHeaderTitle = By
				.xpath("//table[@id='screencustomisation']//thead//tr//th[normalize-space() !='']");
		public static final By textPageTitle = By
				.xpath("//span[@class='ui-dialog-title'][text()='Advance Configuration']");
		public static final By textAdvanceConfigurationPageTitle = By.xpath("//span[text()='Advance Configuration']");
		public static final By btnCrossIcon = By
				.xpath("//span[text()='Advance Configuration']/following-sibling::button[2]");
		public static final By box = By.xpath("//div[@class='sort-box-container']");
		//Button
		public static final By btnSaveIcon = By.xpath("//span[@title='Save']");
		public static final By btnRestoreIcon = By.xpath("span[title='Restore Default']");
		public static final By btnDeleteIcon = By.xpath("//span[@id='popup-delete-btn']");

	}

}