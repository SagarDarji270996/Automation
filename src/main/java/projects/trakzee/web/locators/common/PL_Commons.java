package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_Commons {

	public static final String pageTitleStringFormat = "//div[contains(@class,'titlebox')]//label[normalize-space()='%s']";

	public static final By btnNoActionAtFilterWithAndWithStringFormat = By.xpath(
			"//div[@class='filter-caption' and contains(@id, 'Company')] | //div[@class='filter-caption' and contains(@id, '%s')]");
	static String uniqueVisibleText;
	public static final By screenTitle = By
			.xpath("//span[@class='TitleFrame top-frame-bg-title' and @id='title']//label[1]");

	// User Icon and Notifications
	public static final By addressUserIcon = By.xpath("//div[@id='tree-user']//span[@title='User']");
	public static final By addressNotifications = By.xpath("//div[@id='tree-user']//span[@title='Notifications']");

	//Date on header bar
	public static final By fromDateAtHeaderbar = By.xpath("//label//titlefromdate");
	public static final By toDateAtHeaderbar = By.xpath("//label//titletodate");
	public static final By startAndEndTimeAtHeaderbar = By.xpath("//label//timeframetime");

	// User Menus
	public static final By addressChangePassword = By.xpath("//div[@class='user_menu']//a[text()='Change Password']");
	public static final By addressChangeLanguage = By.xpath("//div[@class='user_menu']//a[text()='Change Language']");
	public static final By addressCustomization = By.xpath("//div[@class='user_menu']//a[text()='Customisation']");
	public static final By addressSetUser = By.xpath("//div[@class='user_menu']//a[text()='Set Subuser']");
	public static final By addressChangePasswords = By.xpath("//div[@class='user_menu']//a[text()='Applications']");
	public static final By addresslogout = By.xpath("(//a[@id='username-a'])[2]");

	// Left Navigation Panel
	public static final By addressNavigationDashboard = By
			.xpath("//div[@id='tree-module']//button[@title='Dashboard']");
	public static final By addressNavigationTracking = By.xpath("//div[@id='tree-module']//button[@title='Tracking']");
	public static final By addressNavigationChart = By.xpath("//div[@id='tree-module']//button[@title='Chart']");
	public static final By addressNavigationReports = By.xpath("//div[@id='tree-module']//button[@title='Reports']");
	public static final By addressNavigationSettings = By.xpath("//div[@id='tree-module']//button[@title='Settings']");
	public static final By addressNavigationDownlaods = By
			.xpath("//div[@id='tree-download']//span[@title='Cloud  Download']");

	// MainModule
	public static final String hoverMainModule = "//div[@id='tree-module']//button[@title='YourText']";

	// SubModule
	public static final String hoverSubModule = "//ul[contains(@class,'subMenuUl scrollable-content')]//span[@title='YourText' or normalize-space()='YourText']";
	public static final String hoverSubModule2 = "//div[@id='subMenu']//ul[contains(@class,'subMenuUl')]//span[@title='YourText' or normalize-space()='YourText']";

	// Screens
	public static final String hoverScreen = "//ul[contains(@class,'subMenuUl scrollable-content')]//li//div[@id='deepMenu']//ul//li[@title='YourText']//a[@href='#']//span[@class='nav-text ng-binding' and normalize-space()='YourText']";
	public static final String hoverScreen2 = "//ul[contains(@class,'subMenuUl')]//li//div[@id='deepMenu']//ul//li[@title='YourText']//a[@href='#']//span[@class='nav-text ng-binding'][normalize-space()='YourText']";

	// Loading icons
	public static final By loadingIcon = By.xpath("//div[@id='container']//div//*[name()='svg']");

	// Header
	public static final String textHeaderName = "//label[text()='headerName']";
	public static final String textHeaderNameStringFormat = "//label[text()='%s']";
	public static final By OverviewScreenName = By
			.xpath("//label[text()='OverviewScreenName' or normalize-space()='OverviewScreenName']");
	public static final By btnApply = By
			.xpath("//div[@class='objectsearchdropdown_container']//div//a[@id='filter_apply']");
	public static final String listHeaderElementAndHeaderTitleReplaceable = "//td[@class='report_date_info']//a[not(contains(@style, 'display: none'))] | //label[text()='%s']";
	public static final By listHeaderIcons = By.xpath("//td[@class='report_date_info']//a");
	public static final By alradyOpenedFilter = By
			.xpath("//a[@title='Filter' and contains(@class,'active-filter-btn')]");
	public static final By btnFilterAtHeader = By.xpath("//a[@title='Filter']");
	public static final By btnHelpAtHeader = By.xpath("//a[@title='Help']");
	public static final By btnSettingAtHeader = By.xpath("//a[@title='Settings']");
	public static final By btnScheduleReportAtHeader = By.xpath("//a[@title='Schedule']");
	public static final By btnFavoriteAtHeader = By.xpath("//a[@title='Favorite Report']");
	public static final By btnAddFavoriteAtHeader = By.xpath("//a[@name='favouriteReportAdd']");
	public static final By btnSearchScreenIconAtHeader = By.xpath("//a[@title='Search Screen']");
	public static final By inputFieldUniversalSearchBox = By
			.xpath("//input[contains(@class,'universalSearchSelectorInput')]");
	public static final By btnChartIcon = By.xpath("//a[@title='Chart']");
	public static final By frameScrollableUniversalSearch = By.xpath("//div[@id='allScreensList']");
	public static final By listUniversalSearchAbleElement = By.xpath(
			"//div[@id='allScreensList']//div[contains(@class,'screenRedirectCont visible universalSelectorElem')]//div");
	public static final By btnHeaderRightSideIcons = By.xpath("(//a[@title='YourText'])[1]");
	public static final By btnDescription = By.xpath("//span[@data='description']");

	// Footer
	public static final By listBottomIconElementAtFooter = By.xpath(
			"//td[@class='bottom-icon-box']//a[not(@style='display: none;') and not(@style='display:none;')] | //input[@name='searchterm'] | //select[@name='searchfield'] | //a[@class='navItem ft-icon icon bt-bottomsearch'] | //select[@id='recordsperpages'] | //select[@id='pagecounter'] | //span[@id='F2'] | //span[@id='F1'] | //div[@class='navItem2 totalrecord-box']");
	public static final By listBottomIconsRHSAtFooter = By
			.xpath("//td[@class='bottom-icon-box']//a[not(@style='display: none;') and not(@style='display:none;')]");
	public static final By btnPlusAtFooter = By.xpath("(//a[@title='New'])[1]");
	public static final By btnResetAtFooter = By.xpath("//a[@title='Reset']");
	public static final By btnExcelDownloadAtFooter = By.xpath("//a[@title='Excel Download']");
	public static final By btnPDFDownloadAtFooter = By.xpath("//a[@title='Download in PDF format']");
	public static final By btnCSVDownloadAtFooter = By.xpath("//a[@title='Download in CSV format']");
	public static final By searchBoxBottomAtFooter = By.xpath("//input[@name='searchterm']");
	public static final By searchAbleItemsAtFooter = By.xpath("//select[@name='searchfield']");
	public static final By searchfilterDropdownAtFooter = By
			.xpath("//select[@name='searchfield']//select[@name='searchfield']");
	public static final By searchIconBottomAtFooter = By.xpath("//a[@class='navItem ft-icon icon bt-bottomsearch']");
	public static final By iconRecordPerPageAtFooter = By.xpath("//select[@id='recordsperpages']");
	public static final By pageCounterAtFooter = By.xpath("//select[@id='pagecounter']");
	public static final By btnArrowNextPageAtFooter = By.xpath("//span[@id='F2']");
	public static final By btnArrowPreviousPageAtFooter = By.xpath("//span[@id='F1']");
	public static final By textTotalRecordsAtFooter = By.xpath("//div[@class='navItem2 totalrecord-box']");
	public static final By textTotalRecordsPresentAtFooter = By
			.xpath("//div[@class='navItem2 totalrecord-box']//span[@id='totrec']");
	public static final By btnBackAtFooter = By.xpath("(//a[@title='Back'])[1]");
	public static final By btnSaveAtFooter = By.xpath("(//a[@title='Save'])[1]");
	public static final By btnDeleteAtFooter = By.xpath("(//a[@title='Delete'])[1]");

	// Setting
	public static final By btnDeleteSettings = By.xpath("//span[@id='popup-delete-btn']");
	public static final By listColumnNameForSummary = By.xpath(
			"//table[@id='dataTable']//thead//tr[@class='z-columns'][1]//td[@class='TableRowHeader']//div[@id='__']//a");
	public static final By listLHSFieldsOfFilter = By.xpath(
			"//tbody//tr//td[@align=\\\"left\\\" ]//div[@class='filter-caption' and not(@title=\\\"DATETIMEPICKER\\\")]");
	public static final By btnDeleteFilter = By.xpath("//a[@id='deleteTrakzeeFilterSetting']");
	public static final By btnsaveFilter = By.xpath("//a[@id='saveTrakzeeFilterSetting']");
	public static final By btnUpdateFilter = By.xpath("//a[@id='updateTrakzeeFilterSetting']");

	// JAlert
	public static final By jAlertTextMessage = By.xpath("//div[contains(@class,'jalert')]//span[@class='content'][1]");
	public static final By listTextJAlertMessageTitleAndMssageBoth = By
			.xpath("//div[starts-with(@id, 'jalert')]//header[1] | //div[starts-with(@id, 'jalert')]//span[1]");
	public static final By textJAlertMessage = By.xpath("//div[contains(@class,'jalert')]//span[@class='content']");
	public static final By btnJAlertClose = By.xpath("//div[contains(@class,'jalert')]//span[@class='jclosebtn']");

	public static final By btnVissibleText = By.xpath("//*[normalized-space()='" + uniqueVisibleText + "']");

	// Filter
	public static final By labelDateSelection = By.xpath("//div[@id='date-selection-label']");
	public static final By labelTimeRange = By.xpath("//label[@id='object-selection-time-label']");
	public static final By labelObjectSelection = By
			.xpath("//div[@class='objectsearchdropdown_container']//div[@id='object-selection-label']");
	public static final By listLHSBtnOfFilter = By.xpath("//div[@id='filter_setting_btn_container']//a");
	public static final By listRHSBtnOfFilter = By
			.xpath("//div[@class='objectsearchdropdown_container']//div//div[@id='report_btn_container']//a//span");
	public static final By btnApplyFilter = By
			.xpath("//div[@class='objectsearchdropdown_container']//div//a[@id='filter_apply']//span");
	public static final By btnApplyFilter2 = By.xpath("//a[@id='filter_apply']//span");

	// Button name visible on UI.
	public static final By btnReplaceBUTTONwithYourButtonName = By.xpath("//a[@title='BUTTONwithYourButtonName']");

	// Footer search items
	public static final By iconBtnSearch = By.xpath("//*[contains(@class,'navItem ft-icon icon bt-bottomsearch')]");
	public static final By listSearchAbleFieldsOrColumnName = By.xpath("//select[@name='searchfield']");
	public static final By fieldSearchBox = By.xpath("//input[@name='searchterm']");
	public static final By btnSearchAbleField = By.xpath("//select[@name='searchfield']");

	// Drop down search box
	public static final By searchboxDropdownlist = By.xpath("//div[@class='chosen-search']//input[@type='text']");

	// Email verification mandatory message
	public static final By textEmailVerificationMandatoryMessage = By
			.xpath("//div[@id='jalert_3'][1]//div/span[position()=1]");
	public static final By btnOkayEmailVerificationMandatoryMessage = By
			.xpath("//div[@id='jalert_3'][1]//div/span[position()=2]");

	//Global search 
	public static final By listGlobalSearchAbleElement = By.xpath(
			"//div[@class='screenRedirectCont universalSelectorElem visible']//div[@class='screenRedirectElem']");

	//Report donwload option
	public static final By listReportTypeDonwload = By.xpath("//div[@id='summary-details-button-container']//a");
	public static final By listReportTypeDonwloadSummaryWithDetails = By
			.xpath("(//div[@id='summary-details-button-container']//a)[2]");

	public static final By listRowEntriesInOverViewScreen = By.xpath("//tbody//tr[contains(@class,'z-grid-odd')]");
	public static final By listColumnSpecificValueEntriesInOverViewScreen = By.xpath("//td[@name='imei_no']");

	public static final By textNoResultMatched = By.xpath("(//li[@class='no-results'])[1]");

}