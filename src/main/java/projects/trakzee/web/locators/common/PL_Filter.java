package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_Filter {

	public static final By listFieldPresentOnTheLHSOfFilerScreen = By
			.xpath("//tbody//tr//td[@align='left' ]//div[@class='filter-caption' and not(@title='DATETIMEPICKER')]");
	public static final By listButtonsPresentOnTheLHSOfFilerScreen = By
			.xpath("//div[@id='filter_setting_btn_container']//a");
	public static final By listFieldPresentOnTheRHSOfFilterScreen = By.xpath(
			"//div[@class='objectsearchdropdown_container']//div[@id='object-selection-label'] |  //div[@id='date-selection-label'] |  //div[@id='report-generation-time-range']");
	public static final By listButtonsPresentOnTheRHSOfFilerScreen = By
			.xpath("//div[@class='objectsearchdropdown_container']//div//div[@id='report_btn_container']//a//span");
	public static final By listButtonsPresentOnTheRHSOfFilerScreenReplace = By.xpath(
			"//div[@class='objectsearchdropdown_container']//div//div[@id='report_btn_container']//a//span[text()='Your Text']");

	public static final By listDropDownReplace = By
			.xpath("//div[@id='YourText']//a[@class='chosen-single']//input[@type='text']");
	public static final By SearchBarDropDownReplace = By
			.xpath("//div[@id='YourText']//div[@class='chosen-drop']//input[@type='text']");
	public static final By listDropDownOptionReplace = By
			.xpath("//div[@id='YourText']//ul[@class='chosen-results scrollable-content']//li");
	public static final By valueSelectedDropdownReplace = By.xpath("//div[@id='YourText']//span");
	public static final By inputDistanceValueField = By.xpath("//input[@id='distance_filter_value']");

	public static final By DropdownDateSelection = By.xpath("//input[@class='trakzeesearchfilter_date']");
	public static final By ListDateSelectionOption = By.xpath("//div[contains(@class,'datepicker-show-flex')]//ul//li");
	public static final By lableDateSelection = By
			.xpath("//div[@class='daterangepicker ltr show-ranges opensleft datepicker-show-flex']//ul//li");

	// Object Selection From Object List
	public static final By objecLocator = By
			.xpath("//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']");
	public static final By searchBarObjectSelection = By.xpath("//input[@id='search_object_dropdown']");
	public static final By btnRelaod = By.xpath("//div[@class='objectsearchdropdown_container']//div[@id='reload']");
	public static final By listObjectSelection = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//div[@class='object_group']//div[@class='object_value']//div//div//label");
	public static final By checkBoxObjectReplace = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//div[@class='object_group']//div[@class='object_value']//div//div//label[text()='%s']//parent::div//parent::div");
	public static final By labelDateAndTimeOfFilterScreen = By
			.xpath("//tbody//tr//span[@class='TitleFrame top-frame-bg-title']/label[2]");
	public static final By buttonExportOfFilterReplace = By.xpath(
			"//div[@class='objectsearchdropdown_container']//div//div[@id='report_btn_container']//a//span[text()='Export']");
	public static final By listExportOfFilteroption = By.xpath("//div[@id='summary-details-button-container']//a");

	public static final By labelsofObjectsFromDataReport = By
			.xpath("//tbody//tr[@class='z-grid-odd']//td[@class='TableBorderColor' and @name='vehicle_information']");
	public static final By searchBoxObjectSelection = By
			.xpath("//input[@id='search_object_dropdown' and @class='inputbox-search']");
	public static final By listMainListObjectOrLevel1 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']");
	public static final By listMainListObjectOrLevel1_1 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_header']//div[contains(@class, 'state p-primary')]//label");
	public static final By listObjectGroupsOrLevel2 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_group']");
	public static final By listObjectGroupOrLevel2_2 = By
			.xpath("//div[@class='object_group' and not(ancestor::*[contains(@class, 'some_class')])]");
	public static final By listOjectValuesOrLevel3 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_group']//div[@class='object_value']");
	public static final By listObjectValuesOrLevel3_2 = By
			.xpath("//div[@class='object_value'and not(ancestor::*[contains(@class, 'some_class')])");

	// Object list
	//Company
	public static final By textAlradySelectedCompanyDropdownCommon = By.xpath(
			"//div[starts-with(@id,'filter_company')]//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By btnDropdownComapnyCommon = By.xpath("//div[starts-with(@id,'filter_company')]");
	public static final By searchboxCompanyCommon = By
			.xpath("//div[starts-with(@id,'filter_company')]//input[@class='chosen-search-input']");
	public static final By listCompanyObjectCommon = By
			.xpath("//div[starts-with(@id,'filter_company')]//ul//li[contains(@class,'active-result')]");

	public static final By textAlradySelectedCompanyDropdownLHS = By
			.xpath("//div[@id='filter_company_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By textAlradySelectedCompanyDropdownRHS = By.xpath(
			"//div[@id='filter_company_id_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By btnDropdownComapnyLHS = By
			.xpath("//div[starts-with(@id,'filter_company')] | //div[@id='filter_company_chosen']"); //It handle LHS and RHS both
	public static final By btnDropdownComapnyRHS = By.xpath("//div[@id='filter_company_id_chosen']");
	public static final By searchboxCompanyLHS = By
			.xpath("//div[@id='filter_company_chosen']//input[@class='chosen-search-input']");
	public static final By searchboxCompanyRHS = By
			.xpath("//div[@id='filter_company_id_chosen']//input[@class='chosen-search-input']");
	public static final By listCompanyObjectOrLevel1 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']");
	public static final By listCompanyObjectRHS = By
			.xpath("//div[@id='filter_company_id_chosen']//ul//li[contains(@class,'active-result')]");
	public static final By listCompanyObjectTitleOrLevel1Title = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//div[@class='object_header']//div[(contains(@class, 'state p-primary '))]//label[1]");

	//Branch
	public static final By textAlradySelectedBranchDropdownLHS = By
			.xpath("//div[@id='filter_branch_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By btnDropdownBranchLHS = By
			.xpath("//div[@id='filter_branch_chosen']| //div[starts-with(@id,'filter_branch')]");

	public static final By searchboxBranchLHS = By
			.xpath("//div[@id='filter_branch_chosen']//input[@class='chosen-search-input']");
	public static final By listBranchObjectOrLevel2 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_group']");
	public static final By listBranchObjectOrLevel2_2 = By
			.xpath("//div[@class='object_group' and not(ancestor::*[contains(@class, 'some_class')])]");
	public static final By listBranchObjectTitleOrLevel2Title = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//div[@class='object_group']//div[(contains(@class, 'state p-primary '))]//label[1]");

	//Branch Common xpath 
	public static final By textAlradySelectedBranchDropdownCommon = By.xpath(
			"//div[starts-with(@id,'filter_branch') or starts-with(@id,'filter_location')]//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By btnDropdownBranchCommon = By
			.xpath("//div[starts-with(@id,'filter_branch') or starts-with(@id,'filter_location')]");

	public static final By searchboxBranchCommon = By.xpath(
			"//div[starts-with(@id,'filter_branch') or starts-with(@id,'filter_location')]//input[@class='chosen-search-input']");
	public static final By listBranchObjectOrCommon = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_group']");
	public static final By listBranchObjectOrLevel2_2_Common = By
			.xpath("//div[@class='object_group' and not(ancestor::*[contains(@class, 'some_class')])]");
	public static final By listBranchObjectTitleOrLevel2TitleCommon = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//div[@class='object_group']//div[(contains(@class, 'state p-primary '))]//label[1]");

	//Vehicle
	public static final By textAlradySelectedVehicleDropdownLHS = By.xpath(
			"//div[@id='filter_vehicle_group_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By btnDropdownVehicleGroupdLHS = By.xpath("//div[@id='filter_vehicle_group_chosen']");
	public static final By searchboxVehicleLHS = By
			.xpath("//div[@id='filter_vehicle_group_chosen']//input[@class='chosen-focus-input']");
	public static final By listVehicleObjectOrLevel3 = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//li[@class='object_header_li']//div[@class='object_group']//div[@class='object_value']");
	public static final By listVehicleObjectOrLevel3_2 = By
			.xpath("//div[@class='object_value'and not(ancestor::*[contains(@class, 'some_class')])]");
	public static final By listVehicleObjectTitleOrLevel3Title = By.xpath(
			"//div[@id='object_main_list_container_dropdown']//ul[@class='object_list scrollable-content']//div[@class='object_value']//label[1]");

	public static final By listDropdownPresentInFilterLHSForAnyLabel = By
			.xpath("//ul[contains(@class,'chosen-results scrollable-content')]//li[contains(@class,'active-result')]");

	//Duration format
	public static final By textAlradySelectedDurationDropdownLHS = By
			.xpath("//div[@id='duration_format']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By listRatioBtnDurationFormat = By.xpath("//input[@name='duration_format']");
	public static final By radioButtonHHMM = By.xpath("//input[@id='duration_format_1']");
	public static final By radioButtonDecimal = By.xpath("//input[@id='duration_format_2']");

	public static final By listReportDownloadTypesSummaryOrSummaryWithDetails = By
			.xpath("//div[@id='summary-details-button-container']//a");
	public static final By btnDownloadSummaryReport = By.xpath("//a[@id='summary-details-button-summary']");
	public static final By btnDownloadSummaryWithDetailsReport = By
			.xpath("//a[@id='summary-details-button-summary-detail']");

	//Custom Date picker
	public static final By listDateGridStart = By.xpath(
			"//div[@class='drp-calendar left']//tr//td[contains(@class, 'available') and not(contains(@class, 'off available'))]");
	public static final By listDateGridEnd = By.xpath(
			"//div[@class='drp-calendar right']//tr//td[contains(@class, 'available') and not(contains(@class, 'off available'))]");
	public static final By btnPrevoustDatePicker = By.xpath("(//th[@class='prev available'])[1]");
	public static final By btnNextDatePicker = By.xpath("(//th[@class='next available'])[1]");
	public static final By textStartMonthYear = By.cssSelector("div[class='drp-calendar left'] th[class='month']");
	public static final By textEndMonthYear = By.cssSelector("div[class='drp-calendar right'] th[class='month']");
	public static final By btnCancelAtDatePicker = By
			.xpath("(//div[@class='drp-buttons'])[2]//button[contains(@class,'cancelBtn')]");
	public static final By btnApplyAtDatePicker = By
			.xpath("(//div[@class='drp-buttons'])[2]//button[contains(@class,'applyBtn')]");

	//From Date picker[Object efficiency]
	public static final By btnFromDateSelection = By
			.xpath("//input[starts-with(@id, 'from_date_filter_data') or starts-with(@id, 'to_date_filter')]");
	public static final By btnPrevoustFromDatePicker = By.xpath("//a[@title='Prev']");
	public static final By btnNextFromDatePicker = By.xpath("//a[@title='Next']");
	public static final By listFromDateGridStart = By.xpath("//td[@data-handler='selectDay']");
	public static final By textFromDateStartMonthYear = By.xpath("//div[@class='ui-datepicker-title']");

	//Time range
	public static final By checkboxTimeRange = By
			.xpath("//div[@id='trakzeesearch_dropdown']//input[@id='timepicker_filter']");
	public static final By fieldTimeRangeFrom = By
			.xpath("//div[@id='trakzeesearch_dropdown']//input[@id='from-time-picker']");
	public static final By fieldTimeRangeTo = By
			.xpath("//div[@id='trakzeesearch_dropdown']//input[@id='to-time-picker']");
	public static final By listHoursPicker = By
			.xpath("//div[@id='trakzeesearch_dropdown']//div[contains(@class,'ttp-hour')]//div");
	public static final By listMinutePicker = By
			.xpath("//div[@id='trakzeesearch_dropdown']//div[contains(@class,'ttp-min')]//div");
	public static final By listAM_PM = By.xpath("//div[@id='trakzeesearch_dropdown']//div[@class='ttp-aa']//div");

	//Fiter button
	public static final By btnApplyAtFilter = By
			.xpath("//div[@id='report_btn_container']//a[@id='filter_apply']//span");
	public static final By btnApplyAtFilterAtIndexOne = By
			.xpath("(//div[@id='report_btn_container']//a[@id='filter_apply'])[1]//span");
	public static final By btnXLSDownloadAtFilter = By
			.xpath("(//div[@id='report_btn_container']//a[@title='Download in XLS format'])[2]//span");
	public static final By btnPDFDownloadAtFilter = By
			.xpath("(//div[@id='report_btn_container']//a[@title='Download in PDF format'])[2]//span");
	public static final By btnCSVDownloadAtFilter = By
			.xpath("(//div[@id='report_btn_container']//a[@title='Download in CSV format'])[2]//span");

	//Lefr hand side
	public static final By textAlradySelectedVehicleTypeDropdownLHS = By.xpath(
			"//div[@id='filter_vehicle_type_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By searchboxVehicleTypeLHS = By
			.xpath("//div[@id='filter_vehicle_type_chosen']//div[@class='chosen-drop']//input[@type='text']");

	public static final By textAlradySelectedVehicleBrandDropdownLHS = By.xpath(
			"//div[@id='filter_vehicle_brand_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By searchboxVehicleBrandLHS = By
			.xpath("//div[@id='filter_vehicle_brand_chosen']//div[@class='chosen-drop']//input[@type='text']");

	public static final By textAlradySelectedVehicleModelDropdownLHS = By.xpath(
			"//div[@id='filter_vehicle_model_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By searchboxVehicleModelLHS = By
			.xpath("//div[@id='filter_vehicle_model_chosen']//div[@class='chosen-drop']//input[@type='text']");

	public static final By textAlradySelectedDistanceDropdownLHS = By.xpath(
			"//div[@id='distance_filter_condition_chosen']//a//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By searchboxDistanceLHS = By
			.xpath("//div[@id='distance_filter_condition_chosen']//div[@class='chosen-drop']//input[@type='text']");
	public static final By btnDistanceLHS = By
			.xpath("//div[@id='distance_filter_condition_chosen']//a[@class='chosen-single']");
	public static final By btnVehicleModelLHS = By
			.xpath("//div[@id='filter_vehicle_model_chosen']//a[@class='chosen-single']");
	public static final By btnVehicleBrandLHS = By
			.xpath("//div[@id='filter_vehicle_brand_chosen']//a[@class='chosen-single']");
	public static final By btnVehicleTypeLHS = By
			.xpath("//div[@id='filter_vehicle_type_chosen']//a[@class='chosen-single']");
	public static final By listCompanyLHS = By
			.xpath("//div[@id='filter_company_chosen']//ul[@class='chosen-results scrollable-content']//li");
	public static final By listCompanyRHS = By
			.xpath("//div[@id='filter_company_id_chosen']//ul//li[contains(@class,'active-result')]");
	public static final By listCompanyCommon = By
			.xpath("//div[starts-with(@id,'filter_company')]//ul//li[contains(@class,'active-result')]");
	public static final By listBranchLHS = By.xpath(
			"//div[@id='filter_branch_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");
	public static final By listBranchCommon = By.xpath(
			"//div[starts-with(@id,'filter_branch') or starts-with(@id,'filter_location')]//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");

	public static final By listVehicleGroupLHS = By.xpath(
			"//div[@id='filter_vehicle_group_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");
	public static final By listVehicleTypeLHS = By.xpath(
			"//div[@id='filter_vehicle_type_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");
	public static final By listVehicleBrandLHS = By.xpath(
			"//div[@id='filter_vehicle_brand_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");
	public static final By listVehilcleModelLHS = By.xpath(
			"//div[@id='filter_vehicle_model_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");
	public static final By listDistanceLHS = By.xpath(
			"//div[@id='distance_filter_condition_chosen']//ul[@class='chosen-results scrollable-content']//li[contains(@class,'active-result')]");

	//Filter on cloud download page
	public static final By btnDownlaodReportFor = By.xpath("//div[@id='filter_download_request_for_chosen']");
	public static final By textAlreadySelectedDowloadReportFor = By
			.xpath("//input[@class='chosen-focus-input']/following-sibling::span");
	public static final By searchboxDownloadReportFor = By.xpath("//input[@class='chosen-search-input']");
	public static final By listDownloadRequestFor = By
			.xpath("//div[@id='filter_download_request_for_chosen']//div//li[contains(@class,'active-result')]");
	public static final By btnAppy = By.xpath("//a[@id='filter_apply']//span");

	//Geofence Type
	public static final By btnGeofenceTypeRHS = By.xpath("//div[@container_name='geofence_type_f']");
	public static final By listGeofenceTypeRHS = By
			.xpath("//div[@id='filter_geofence_type_f_chosen']//ul//li[contains(@class,'active-result')]");
	public static final By searchboxGeofenceTypeRHS = By
			.cssSelector("div[id='filter_geofence_type_f_chosen'] div[class='chosen-search'] input");
	public static final By textAlreadySelectedGeofenceTypeRHS = By.xpath(
			"//div[@id='filter_geofence_type_f_chosen']//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Alert type
	public static final By btnAlertTypeCommon = By.xpath("//div[starts-with(@id,'filter_alert')]");
	public static final By listAlertTypeCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxAlertTypeCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//div[@class='chosen-search']//input");
	public static final By textAlreadySelectedAlertTypeCommon = By.xpath(
			"//div[starts-with(@id,'filter_alert')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//vehicle status
	public static final By btnVehicleStatus = By.xpath("//div[starts-with(@id,'filter_vehicle_status')]");
	public static final By listVehicleStatus = By
			.xpath("//div[starts-with(@id,'filter_vehicle_status')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleStatus = By
			.xpath("//div[starts-with(@id,'filter_vehicle_status')]//a[@class='chosen-single']//input[@type='text']");
	public static final By textAlreadyDropdownVehicleStatus = By.xpath(
			"//div[starts-with(@id,'filter_vehicle_status')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//vehicle status common
	public static final By btnVehicleStatusCommon = By
			.xpath("//div[starts-with(@id,'filter_vehicle_status') or starts-with(@id,'filter_status')]");
	public static final By listVehicleStatusCommon = By.xpath(
			"//div[starts-with(@id,'filter_vehicle_status') or starts-with(@id,'filter_status')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleStatusCommon = By.xpath(
			"//div[starts-with(@id,'filter_vehicle_status') or starts-with(@id,'filter_status')]//*[@class='chosen-search']//input[@type='text']");
	public static final By textAlreadyDropdownVehicleStatusCommon = By.xpath(
			"//div[starts-with(@id,'filter_vehicle_status') or starts-with(@id,'filter_status')]//*[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//vehicle status with checkbox common
	public static final By btnStatusWithCheckboxCommon = By
			.xpath("(//div[starts-with(@id,'filter_vehicle_status') or starts-with(@id,'filter_status')])[1]");
	public static final By listStatusWithCheckboxCommon = By
			.xpath("//div[@id='filter_status_chosen']//li[contains(@class,'active-result')]//input");
	public static final By searchboxStatusWithCheckboxCommon = By
			.xpath("//div[@id='filter_status_chosen']//li[@class='search-field']//input");
	public static final By textAlreadyDropdownStatusWithCheckboxCommon = By.xpath("//li[@class='search-choice']//span");

	//State
	public static final By btnStateCommon = By.xpath("//div[@container_name='state_id']");
	public static final By listStateCommon = By
			.xpath("//div[@container_name='state_id']//ul//li[contains(@class,'active-result')]");
	public static final By searchboxStateCommon = By
			.xpath("div[container_name='state_id'] div[class='chosen-search'] input");
	public static final By textAlreadySelectedStateCommon = By.xpath(
			"//div[@container_name='state_id']//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Trip Calculation for
	public static final By btnTripCalculationFor = By
			.xpath("//div[starts-with(@container_name,'trip_calculation_for')]");
	public static final By listTripCalculationFor = By.xpath(
			"//div[starts-with(@container_name,'trip_calculation_for')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxTripCalculationFor = By.xpath(
			"//div[starts-with(@container_name,'trip_calculation_for')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownTripCalculationFor = By.xpath(
			"//div[starts-with(@container_name,'trip_calculation_for')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Stop Duration for common
	public static final By btnStopDuration = By.xpath("//div[starts-with(@id,'filter_park_time')]");
	public static final By listStopDuration = By
			.xpath("//div[starts-with(@id,'filter_park_time')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxStopDuration = By
			.xpath("//div[starts-with(@id,'filter_park_time')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownStopDuration = By.xpath(
			"//div[starts-with(@id,'filter_park_time')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Idle Duration fork
	public static final By btnIdleDuration = By.xpath("//div[starts-with(@id,'filter_idle_duration')]");
	public static final By listIdleDuration = By
			.xpath("//div[starts-with(@id,'filter_idle_duration')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxIdleDuration = By
			.xpath("//div[starts-with(@id,'filter_idle_duration')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownIdleDuration = By.xpath(
			"//div[starts-with(@id,'filter_idle_duration')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Vehicle group common
	public static final By btnVehicleGroup = By
			.xpath("//div[starts-with(@id,'filter_group') or starts-with(@id,'filter_vehicle_group')]");
	public static final By listVehicleGroup = By.xpath(
			"//div[starts-with(@id,'filter_group') or starts-with(@id,'filter_vehicle_group')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleGroup = By.xpath(
			"//div[starts-with(@id,'filter_group') or starts-with(@id,'filter_vehicle_group')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownVehicleGroup = By.xpath(
			"//div[starts-with(@id,'filter_group') or starts-with(@id,'filter_vehicle_group')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Vehicle type common
	public static final By btnVehicleType = By
			.xpath("//div[starts-with(@id,'filter_type') or starts-with(@id,'filter_vehicle_type')]");
	public static final By listVehicleType = By.xpath(
			"//div[starts-with(@id,'filter_type') or starts-with(@id,'filter_vehicle_type')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleType = By.xpath(
			"//div[starts-with(@id,'filter_type') or starts-with(@id,'filter_vehicle_type')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownVehicleType = By.xpath(
			"//div[starts-with(@id,'filter_type') or starts-with(@id,'filter_vehicle_type')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Vehicle brand common
	public static final By btnVehicleBrand = By
			.xpath("//div[starts-with(@id,'filter_brand') or starts-with(@id,'filter_vehicle_brand')]");
	public static final By listVehicleBrand = By.xpath(
			"//div[starts-with(@id,'filter_brand') or starts-with(@id,'filter_vehicle_brand')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleBrand = By.xpath(
			"//div[starts-with(@id,'filter_brand') or starts-with(@id,'filter_vehicle_brand')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownVehicleBrand = By.xpath(
			"//div[starts-with(@id,'filter_brand') or starts-with(@id,'filter_vehicle_brand')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Vehicle model common
	public static final By btnVehicleModel = By
			.xpath("//div[starts-with(@id,'vehicle_model') or starts-with(@id,'filter_vehicle_model')]");
	public static final By listVehicleModel = By.xpath(
			"//div[starts-with(@id,'vehicle_model') or starts-with(@id,'filter_vehicle_model')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleModel = By.xpath(
			"//div[starts-with(@id,'filter_type') or starts-with(@id,'filter_vehicle_model')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownVehicleModel = By.xpath(
			"//div[starts-with(@id,'filter_model') or starts-with(@id,'filter_vehicle_model')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Vehicle model common
	public static final By btnVehicleCategory = By
			.xpath("//div[starts-with(@id,'filter_category') or starts-with(@id,'filter_vehicle_category')]");
	public static final By listVehicleCategory = By.xpath(
			"//div[starts-with(@id,'vehicle_category') or starts-with(@id,'filter_vehicle_category')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxVehicleCategory = By.xpath(
			"//div[starts-with(@id,'filter_category') or starts-with(@id,'filter_vehicle_category')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownVehicleCategory = By.xpath(
			"//div[starts-with(@id,'filter_category') or starts-with(@id,'filter_vehicle_category')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	public static final By hourSliderInsideDatePicker = By
			.xpath("//dd[@class='ui_tpicker_hour']//a[contains(@class,'ui-slider-handle')]");
	public static final By minuteSliderInsideDatePicker = By
			.xpath("//dd[contains(@class,'ui_tpicker_minute')]//a[contains(@class,'ui-slider-handle')]");

	//Days common
	public static final By btnDays = By
			.xpath("//div[starts-with(@id,'filter_day_interval') or starts-with(@id,'filter_day_interval_temp')]");
	public static final By listDays = By.xpath(
			"//div[starts-with(@id,'filter_day_interval') or starts-with(@id,'filter_day_interval_temp')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxDays = By.xpath(
			"//div[starts-with(@id,'filter_day_interval') or starts-with(@id,'filter_day_interval_temp')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownDays = By.xpath(
			"//div[starts-with(@id,'filter_day_interval') or starts-with(@id,'filter_day_interval_temp')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Status Since common
	public static final By btnStatusSince = By
			.xpath("//div[starts-with(@id,'filter_status_since') or starts-with(@id,'filter_status_since_chosen')]");
	public static final By listStatusSince = By.xpath(
			"//div[starts-with(@id,'filter_status_since') or starts-with(@id,'filter_status_since_chosen')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxStatusSince = By.xpath(
			"//div[starts-with(@id,'filter_status_since') or starts-with(@id,'filter_status_since_chosen')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownStatusSince = By.xpath(
			"//div[starts-with(@id,'filter_status_since') or starts-with(@id,'filter_status_since_chosen')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//Change speed range
	public static final By speedRangeCheckbox = By.xpath("//input[starts-with(@id,'change_speed_range')]");
	public static final By rangesContainer = By.xpath("//div[@id='change_range']");

	public static By selectSpeedRangeCheckboxXpath(String speedRange) {
		int xppathIndex = 0;
		switch (speedRange) {
		case "0-20":
			xppathIndex = 1;
			break;
		case "21-40":
			xppathIndex = 2;
			break;
		case "41-60":
			xppathIndex = 3;
			break;
		case "61-80":
			xppathIndex = 4;
			break;
		case "81-100":
			xppathIndex = 5;
			break;
		case "101-120":
			xppathIndex = 6;
			break;
		case "121-140":
			xppathIndex = 7;
			break;
		case "141-160":
			xppathIndex = 8;
			break;
		case "161-180":
			xppathIndex = 9;
			break;
		case "181-200":
			xppathIndex = 10;
			break;
		default:
			xppathIndex = 1;
			throw new IllegalArgumentException("Invalid speed range: " + speedRange + " and selected first");
		}
		return By.xpath("(//div[@id='change_range']//div//input[@type='checkbox'])[" + xppathIndex + "]");
	}

	public static final By speedRangeColumnNameStringFormat = By
			.xpath("//div[@title='Travelled distance in %s speed range']");

	//Battery tolerance
	public static final By fieldBatteryTolerance = By.xpath("//input[@name='battery_tolerance']");

	//Round off
	public static final By checkboxRoundOff = By.xpath("//input[@id='round_off_id']");

	//Select Month
	public static final By selectMonthDropdownSelectTag = By.xpath("(//select[@class='monthselect'])[1]");

	//Select year
	public static final By selectYearDropdownSelectTag = By.xpath("(//select[@class='yearselect'])[1]");

	//Pre button icon on date selection
	public static final By btnIconPrevousInsideDatePicker = By.xpath("//*[@class='prev available']");

	//Next button icon on date selection
	public static final By btnIconNextInsideDatePicker = By.xpath("//*[@class='next available']");

	//Speed Limit
	public static final By radioButtonCustom = By.xpath("//input[@id='speed_limit_filter_1']");
	public static final By radioButtonAsPerAlert = By.xpath("//input[@id='speed_limit_filter_2']");
	public static final By fieldCustomValue = By.xpath("//input[@name='speed_filter']");
	public static final By btnAsPerAlertDropdown = By.xpath("//div[starts-with(@id,'filter_as_per_alert_dd')]");
	public static final By listAsPerAlertDropdown = By
			.xpath("//div[starts-with(@id,'filter_as_per_alert_dd')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxAsPerAlertDropdown = By
			.xpath("//div[starts-with(@id,'filter_as_per_alert_dd')]//div//div[@class='chosen-search']//input");
	public static final By textAlreadySelectedAsPerAlertDropdown = By.xpath(
			"//div[starts-with(@id,'filter_as_per_alert_dd')]//a//input[@class='chosen-focus-input']/following-sibling::span");

	//X Hours common
	public static final By btnXHoursCommon = By.xpath("//div[starts-with(@id,'filter_x_hours')]");
	public static final By listXHoursCommon = By
			.xpath("//div[starts-with(@id,'filter_x_hours')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxXHoursCommon = By
			.xpath("//div[starts-with(@id,'filter_x_hours')]//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownXHoursCommon = By.xpath(
			"//div[starts-with(@id,'filter_x_hours')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//To Time Dropdown
	public static final By btnToTimeCommon = By.xpath("//div[starts-with(@id,'filter_to_time')]");
	public static final By listToTimeCommon = By
			.xpath("//div[starts-with(@id,'filter_to_time')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxToTimeCommon = By
			.xpath("//div[starts-with(@id,'filter_to_time')]//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownToTimeCommon = By.xpath(
			"//div[starts-with(@id,'filter_to_time')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//To Time Dropdown
	public static final By btnFromTimeCommon = By.xpath("//div[starts-with(@id,'filter_from_time')]");
	public static final By listFromTimeCommon = By
			.xpath("//div[starts-with(@id,'filter_from_time')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxFromTimeCommon = By
			.xpath("//div[starts-with(@id,'filter_from_time')]//div[@class='chosen-search']//input");
	public static final By textAlreadyDropdownFromTimeCommon = By.xpath(
			"//div[starts-with(@id,'filter_from_time')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	public static final By fieldRed = By.xpath("//input[@name='red_utilization']");
	public static final By fieldYellow = By.xpath("//input[@name='yellow_utilization']");
	public static final By fieldGreen = By.xpath("//input[@name='green_utilization']");

	public static final By btnFromDate = By.xpath("//input[starts-with(@id, 'from_date_filter')]");
	public static final By btnToDate = By.xpath("//input[starts-with(@id, 'to_date_filter')]");
	public static final By listDateGrid = By.xpath("//td[@data-handler='selectDay']//a");
	public static final By listYearDropdownSelectTag = By.xpath("//select[@class='ui-datepicker-year']");
	public static final By listMonthDropdownSelectTag = By.xpath("//select[@class='ui-datepicker-month']");

	//vehicle country with checkbox common
	public static final By btnCountryWithCheckboxCommon = By
			.xpath("//div[starts-with(@id,'filter_country_data') or starts-with(@id,'filter_status')]");
	public static final By listCountryWithCheckboxCommon = By.xpath("//select[@id='filter_country_data']//option");
	public static final By searchboxCountryWithCheckboxCommon = By
			.xpath("//div[starts-with(@id,'filter_country_data')]//li[@class='search-field']//input");
	public static final By textAlreadyDropdownCountryWithCheckboxCommon = By
			.xpath("//select[@id='filter_country_data']//option[@selected]");

	//Object Rating common
	public static final By btnObjectRatingCommon = By.xpath("//div[starts-with(@id,'filter_rating')]");
	public static final By listObjectRatingCommon = By
			.xpath("//div[starts-with(@id,'filter_rating')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxObjectRatingCommon = By
			.xpath("//div[starts-with(@id,'filter_rating')]//div[@class='chosen-search']//input");
	public static final By textAlreadySelectedObjectRatingCommon = By.xpath(
			"//div[starts-with(@id,'filter_rating')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Alert Selection common
	public static final By btnAlertSelectionCommon = By.xpath("//div[starts-with(@id,'filter_alert')]");
	public static final By listAlertSelectionCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//ul[contains(@class,'chosen-results')]//li//div//label");
	public static final By searchboxAlertSelectionCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//*[@class='search-field']//input");
	public static final By textAlreadySelectedAlertSelectionCommon = By.xpath(
			"//div[starts-with(@id,'filter_alert')]//ul[contains(@class,'chosen-results')]//li[contains(@class,'result-selected')]//div//label");
	public static final By checkboxWithValueAll = By
			.xpath(
			"//div[starts-with(@id,'filter_alert')]//div[contains(@class,'chosen-drop')]//li[@title='All' and contains(@class,'result-selected')]");

	//Shift Group common
	public static final By btnShiftGroupCommon = By.xpath("//div[starts-with(@id,'filter_shift_group')]");
	public static final By listShiftGroupCommon = By
			.xpath("//div[starts-with(@id,'filter_shift_group')]//ul//li[contains(@class,'active-result')]");
	public static final By searchboxShiftGroupCommon = By
			.xpath("//div[starts-with(@id,'filter_shift_group')]//div[@class='chosen-search']//input");
	public static final By textAlreadySelectedShiftGroupCommon = By.xpath(
			"//div[starts-with(@id,'filter_shift_group')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");

	//Shift Selection common
	public static final By btnShiftCommon = By.xpath("//div[starts-with(@id,'filter_alert')]");
	public static final By listShiftCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//ul[contains(@class,'chosen-results')]//li//div//label");
	public static final By searchboxShiftCommon = By
			.xpath("//div[starts-with(@id,'filter_alert')]//*[@class='search-field']//input");
	public static final By textAlreadySelectedShiftCommon = By.xpath(
			"//div[starts-with(@id,'filter_alert')]//ul[contains(@class,'chosen-results')]//li[contains(@class,'result-selected')]//div//label");
	public static final By checkboxShiftWithValueAll = By.xpath(
			"//div[starts-with(@id,'filter_alert')]//div[contains(@class,'chosen-drop')]//li[@title='All' and contains(@class,'result-selected')]");

}