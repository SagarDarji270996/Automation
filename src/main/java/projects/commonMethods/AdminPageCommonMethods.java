package projects.commonMethods;

import projects.taskeye.configurations.common.CommonMethods;
import projects.taskeye.configurations.common.IframesOfApplication;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.taskeye.web.locators.AdminPageLocators;
import projects.taskeye.web.locators.CompanyPageLocators;

import java.util.Objects;

public class AdminPageCommonMethods {

    public final String password = "Test@123";
    private final WebDriverWait wait;
    private final WebDriver driver;
    private final CommonMethods commonMethods;
    private final IframesOfApplication iframe;
    public String userName;
    public String userNameOfAdminSubUser;
    public String shortName;
    public String editShortName;
    public String editMobileNumber;
    public String editUserName;

    public AdminPageCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        this.wait = new WebDriverWait(driver, 60);
        this.iframe = new IframesOfApplication(driver);
    }

    public void addAdmin() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ADD_BUTTON));
        addButton.click();
        Thread.sleep(5000);
    }

    public void enterShortNameForAdmin() {
        this.shortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        enterShortNameField.sendKeys(this.shortName);
    }

    public void searchTheAdminUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.shortName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void enterUserNameForAdmin() {
        this.userName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        enterUserNameField.sendKeys(this.userName);
    }

    public void enterConfirmUserNameForAdmin() {
        iframe.switchToContentFrame();
        WebElement enterConfirmUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONFIRM_USERNAME_FIELD));
        enterConfirmUserNameField.sendKeys(this.userName);
    }

    public void selectCountryForAdmin(String countryName) throws InterruptedException {
        iframe.switchToContentFrame();
        By countryDropdownLocator = AdminPageLocators.ADMIN_DETAILS_COUNTRY_DROPDOWN;
        By countryOptionLocator = By.xpath(String.format(AdminPageLocators.ADMIN_DETAILS_COUNTRY_DROPDOWN_OPTION, countryName));
        commonMethods.selectFromDropdown(countryDropdownLocator, countryOptionLocator);
    }

    public void selectStateForAdmin(String stateName) throws InterruptedException {
        Thread.sleep(500);
        iframe.switchToContentFrame();
        By stateDropdownLocator = AdminPageLocators.ADMIN_DETAILS_STATE_DROPDOWN;
        By stateOptionLocator = By.xpath(String.format(AdminPageLocators.ADMIN_DETAILS_STATE_DROPDOWN_OPTION, stateName));
        commonMethods.selectFromDropdown(stateDropdownLocator, stateOptionLocator);
    }

    public void enterPasswordForAdmin() {
        iframe.switchToContentFrame();
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.PASSWORD_FIELD));
        passwordField.sendKeys(password);
    }

    public void addButtonInsideRuleOfAdmin(String name) {
        iframe.switchToAdminRuleFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
    }

    public void setStartUpScreen(String startUpScreen) {
        iframe.switchToPopUpDialogFrame();
        By startUpScreenDropdownLocator = AdminPageLocators.STARTUP_SCREEN_DROPDOWN;
        By startUpScreenOptionLocator = By.xpath(String.format(AdminPageLocators.STARTUP_SCREEN_DROPDOWN_OPTION, startUpScreen));
        commonMethods.selectFromDropdown(startUpScreenDropdownLocator, startUpScreenOptionLocator);
    }

    public void selectTimeZoneInUserSettingOfAdmin(String timezoneName) {
        iframe.switchToContentFrame();
        By timezoneDropdownLocator = AdminPageLocators.TIMEZONE_DROPDOWN;
        By timezoneOptionLocator = By.xpath(String.format(AdminPageLocators.TIMEZONE_DROPDOWN_OPTION, timezoneName));
        commonMethods.selectFromDropdown(timezoneDropdownLocator, timezoneOptionLocator);
    }

    public void selectWeekStartDay(String days) {
        iframe.switchToContentFrame();
        By weekStartDayDropdownLocator = AdminPageLocators.WEEK_START_DAY_DROPDOWN;
        By weekStartDayOptionLocator = By.xpath(String.format(AdminPageLocators.WEEK_START_DAY_DROPDOWN_OPTION, days));
        commonMethods.selectFromDropdown(weekStartDayDropdownLocator, weekStartDayOptionLocator);
    }

    public void clickOnDashboardOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();
        WebElement dashBoardModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DASHBOARD_SUB_MODULE));
        dashBoardModule.click();
        WebElement viewPermissionOfDashboard = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_DASHBOARD));
        viewPermissionOfDashboard.click();
        WebElement viewPermissionOfDashboard2 = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MODIFY_PERMISSION_OF_DASHBOARD2));
        viewPermissionOfDashboard2.click();
    }

    public void clickOnTrackingOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();
        WebElement trackingModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.TRACKING_SUB_MODULE));
        trackingModule.click();
        WebElement viewPermissionOfTracking1 = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_TRACKING));
        viewPermissionOfTracking1.click();
        WebElement viewPermissionOfTracking2 = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_TRACKING));
        viewPermissionOfTracking2.click();
    }

    public void clickOnChartOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();
        WebElement chartModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.CHART_SUB_MODULE));
        chartModule.click();
        WebElement viewPermissionOfDistance = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_DISTANCE));
        viewPermissionOfDistance.click();
        WebElement viewPermissionOfAlerts = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_ALERTS));
        viewPermissionOfAlerts.click();
    }

    public void clickOnReportsOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();

        // Report main module
        WebElement reportsModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.REPORTS_SUB_MODULE));
        reportsModule.click();

        // activity submodule of Reports
        WebElement activityChildModuleOfReports = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ACTIVITY_CHILD_MODULE));
        activityChildModuleOfReports.click();

        // activity submodule reports list
        WebElement viewPermissionOfAttendanceReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_ATTENDANCE_REPORT));
        viewPermissionOfAttendanceReport.click();
        WebElement viewPermissionOfEmployeeAttendanceLogsReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_ATTENDANCE_LOGS_REPORT));
        viewPermissionOfEmployeeAttendanceLogsReport.click();
        WebElement viewPermissionOfEmployeeWorkHistoryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_TASK_HISTORY_REPORT));
        viewPermissionOfEmployeeWorkHistoryReport.click();
        WebElement viewPermissionOfWorkHourReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_WORK_HOUR_REPORT));
        viewPermissionOfWorkHourReport.click();
        WebElement viewPermissionOfEmployeeDayWiseWorkHourReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_DAY_WISE_WORK_HOUR_REPORT));
        viewPermissionOfEmployeeDayWiseWorkHourReport.click();
        WebElement viewPermissionOfTaskEyeEmployeeDayWiseDistanceReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MODIFY_PERMISSION_OF_TASK_EYE_EMPLOYEE_DAY_WISE_DISTANCE_REPORT));
        viewPermissionOfTaskEyeEmployeeDayWiseDistanceReport.click();
        WebElement viewPermissionOfEmployeePerformanceReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_PERFORMANCE_REPORT));
        viewPermissionOfEmployeePerformanceReport.click();
        WebElement viewPermissionOfEmployeeStatusReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_STATUS_REPORT));
        viewPermissionOfEmployeeStatusReport.click();
        WebElement viewPermissionOfEmployeeNotesReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MODIFY_PERMISSION_OF_EMPLOYEE_NOTES_REPORT));
        viewPermissionOfEmployeeNotesReport.click();
        WebElement viewPermissionOfEmployeeUnreachableEventReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_UNREACHABLE_EVENT_REPORT));
        viewPermissionOfEmployeeUnreachableEventReport.click();

        // approvals submodule of Reports
        WebElement approvalsChildModuleOfReports = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.APPROVALS_CHILD_MODULE));
        approvalsChildModuleOfReports.click();

        // approvals submodule reports list
        WebElement viewPermissionOfExpenseReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EXPENSE_REPORT));
        viewPermissionOfExpenseReport.click();
        WebElement viewPermissionOfRegularizeRequestReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_REGULARIZE_REQUEST_REPORT));
        viewPermissionOfRegularizeRequestReport.click();

        // geofence-POI submodule of Reports
        WebElement geofencePoiChildModuleOfReports = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.GEOFENCE_POI_CHILD_MODULE));
        geofencePoiChildModuleOfReports.click();

        // geofence-POI submodule reports list
        WebElement viewPermissionOfGeofenceHistoryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_GEOFENCE_HISTORY_REPORT));
        viewPermissionOfGeofenceHistoryReport.click();
        WebElement viewPermissionOfPoiVisitsReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_POI_VISITS_REPORT));
        viewPermissionOfPoiVisitsReport.click();

        // alerts submodule of Reports
        WebElement alertsChildModuleOfReports = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ALERTS_CHILD_MODULE));
        alertsChildModuleOfReports.click();

        // alerts submodule reports list
        WebElement viewPermissionOfEmployeeAlertReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_ALERT_REPORT));
        viewPermissionOfEmployeeAlertReport.click();
        WebElement viewPermissionOfSoSSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_SOS_SUMMARY_REPORT));
        viewPermissionOfSoSSummaryReport.click();
        WebElement viewPermissionOfEmailSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMAIL_SUMMARY_REPORT));
        viewPermissionOfEmailSummaryReport.click();

        // logs submodule of Reports
        WebElement logsChildModuleOfReports = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.LOGS_CHILD_MODULE));
        logsChildModuleOfReports.click();

        // logs submodule reports list
        WebElement viewPermissionOfUserAccessSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_USER_ACCESS_SUMMARY_REPORT));
        viewPermissionOfUserAccessSummaryReport.click();
        WebElement viewPermissionOfSystemLogSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_SYSTEM_LOG_SUMMARY_REPORT));
        viewPermissionOfSystemLogSummaryReport.click();
        WebElement viewPermissionOfTravelSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_TRAVEL_SUMMARY_REPORT));
        viewPermissionOfTravelSummaryReport.click();
        WebElement viewPermissionOfStoppageSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_STOPPAGE_SUMMARY_REPORT));
        viewPermissionOfStoppageSummaryReport.click();
        WebElement viewPermissionOfInactiveSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_INACTIVE_SUMMARY_REPORT));
        viewPermissionOfInactiveSummaryReport.click();
    }

    public void clickOnSettingsOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();

        // Setting main module
        WebElement settingsModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SETTINGS_SUB_MODULE));
        settingsModule.click();

        // general submodule of Setting
        WebElement generalModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.GENERAL_CHILD_MODULE));
        generalModule.click();

        // Setting submodule general list
        WebElement addDeletePermissionOfAdminUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MODIFY_PERMISSION_OF_ADMIN_USER));
        addDeletePermissionOfAdminUser.click();
        WebElement addDeletePermissionOfResellerUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_RESELLER_USER));
        addDeletePermissionOfResellerUser.click();
        WebElement addDeletePermissionOfCompanyUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_COMPANY_USER));
        addDeletePermissionOfCompanyUser.click();
        WebElement addDeletePermissionOfCompanySubUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_COMPANY_SUB_USER));
        addDeletePermissionOfCompanySubUser.click();
        WebElement addDeletePermissionOfEmployee = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_EMPLOYEE));
        addDeletePermissionOfEmployee.click();
        WebElement addDeletePermissionOfAlert = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_ALERT));
        addDeletePermissionOfAlert.click();

        // master submodule of Setting
        WebElement masterModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MASTER_CHILD_MODULE));
        masterModule.click();

        // Setting submodule master list
        WebElement addDeletePermissionOfAddTask = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_ADD_TASK));
        addDeletePermissionOfAddTask.click();
        WebElement addDeletePermissionOfTaskForm = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_TASK_FORM));
        addDeletePermissionOfTaskForm.click();
        WebElement addDeletePermissionOfLeave = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_LEAVE));
        addDeletePermissionOfLeave.click();
        WebElement addDeletePermissionOfHoliday = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_HOLIDAY));
        addDeletePermissionOfHoliday.click();
        WebElement addDeletePermissionOfRenameDeviceBrandOrModel = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_RENAME_DEVICE_BRAND_OR_MODEL));
        addDeletePermissionOfRenameDeviceBrandOrModel.click();
        WebElement addDeletePermissionOfAnnouncementOverview = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_ANNOUNCEMENT_OVERVIEW));
        addDeletePermissionOfAnnouncementOverview.click();
        WebElement addDeletePermissionOfCustomizeApis = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_CUSTOMIZE_APIS));
        addDeletePermissionOfCustomizeApis.click();
        WebElement addDeletePermissionOfExpense = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_EXPENSE));
        addDeletePermissionOfExpense.click();
        WebElement addDeletePermissionOfEasyRemove = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ADD_DELETE_PERMISSION_OF_EASY_REMOVE));
        addDeletePermissionOfEasyRemove.click();
    }

    public void enterServerIpForAdmin() {
        String serverIP = "192.168.1.10";
        iframe.switchToContentFrame();
        WebElement serverIPField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SERVER_IP_FIELD));
        serverIPField.sendKeys(serverIP);
    }

    public void enterSOSAuthorizedIpForAdmin() {
        String sosAuthorizedIP = "192.168.1.10";
        iframe.switchToContentFrame();
        WebElement sosAuthorizedIPField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SOS_AUTHORIZED_IP_FIELD));
        sosAuthorizedIPField.sendKeys(sosAuthorizedIP);
    }

    public void enterDomainForAdmin() {
        String domain = "example.com";
        iframe.switchToContentFrame();
        WebElement domainField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DOMAIN_FIELD));
        domainField.sendKeys(domain);
    }

    public void enterAndroidAppLinkForAdmin() {
        String androidAppLink = "https://play.google.com/store/apps/details?id=com.example.androidapp";
        iframe.switchToContentFrame();
        WebElement androidAppLinkField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.ANDROID_APP_LINK_FIELD));
        androidAppLinkField.sendKeys(androidAppLink);
    }

    public void enterIosAppLinkForAdmin() {
        String iosAppLink = "https://apps.apple.com/app/id1234567890";
        iframe.switchToContentFrame();
        WebElement iosAppLinkField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.IOS_APP_LINK_FIELD));
        iosAppLinkField.sendKeys(iosAppLink);
    }

    public void saveButtonOfAdminPage() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SAVE_BUTTON));
        saveButton.click();
        Thread.sleep(1000);
    }

    public void clickOnCreatedAdmin() throws InterruptedException {
        Thread.sleep(3000);
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.shortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void deleteButtonAdmin() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DELETE_BUTTON));
        deleteButton.click();
        Thread.sleep(1000);
        commonMethods.acceptAlert();
        Thread.sleep(1000);
        commonMethods.acceptAlert();
        Thread.sleep(1000);
    }

    public void verifyThatUserDeleted() {
        iframe.switchToContentFrame();
        boolean isDeleted = commonMethods.isUserDeleted(this.shortName);
        Assert.assertTrue(isDeleted, "User with Short Name '" + this.shortName + "' was not deleted successfully.");
    }

    public void searchTheEditedAdminUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.editShortName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void editAdminShortName() {
        this.editShortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterShortNameField);
        enterShortNameField.sendKeys(this.editShortName);
    }

    public void editAdminUserName() {
        this.editUserName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterUserNameField);
        enterUserNameField.sendKeys(this.editUserName);
    }

    public void editAdminMobileNumber() {
        this.editMobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumber);
        enterMobileNumber.sendKeys(this.editMobileNumber);
    }

    public void editAdminTelephoneNumber() {
        String editTelephoneNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterTelephoneNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TELEPHONE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterTelephoneNumber);
        enterTelephoneNumber.sendKeys(editTelephoneNumber);
    }

    public void clickOnEditRuleOfAdmin() {
        iframe.switchToAdminRuleFrame();
        WebElement editRule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.EDIT_RULE_BUTTON));
        editRule.click();
    }

    public void editRuleName() {
        String editRuleName = "EditRuleTesting1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterRuleName = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.RULE_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterRuleName);
        enterRuleName.sendKeys(editRuleName);
    }

    public void editDashboardOfTaskEyeProjectOfScreenAccessOfAdmin() {
        iframe.switchToContentFrame();
        WebElement dashBoardModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DASHBOARD_SUB_MODULE));
        dashBoardModule.click();
        WebElement viewPermissionOfDashboard = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.NO_ACCESS_PERMISSION_OF_DASHBOARD));
        viewPermissionOfDashboard.click();
    }

    public void verifyThatMobileNumberEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editMobileNumber);
        Assert.assertTrue(isEdited, "Short Name '" + this.editMobileNumber + "' was not edited successfully.");
    }

    public void verifyThatUserNameEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editUserName);
        Assert.assertTrue(isEdited, "Short Name '" + this.editUserName + "' was not edited successfully.");
    }

    public void clickOnEditedAdmin() {
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.editShortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void addButtonInsideAdminSubUserOfAdmin(String name) {
        iframe.switchToSubAdminUserFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
    }

    public void enterUserNameForAdminSubUser() {
        this.userNameOfAdminSubUser = CommonMethods.generateRandomEmail();
        iframe.switchToPopUpDialogFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.USERNAME_FIELD_SUB_ADMIN_USER));
        enterUserNameField.sendKeys(this.userNameOfAdminSubUser);
    }

    public void enterConfirmUserNameForAdminSubUser() {
        iframe.switchToPopUpDialogFrame();
        WebElement enterConfirmUserNameField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.CONFIRM_USERNAME_FIELD_SUB_ADMIN_USER));
        enterConfirmUserNameField.sendKeys(this.userNameOfAdminSubUser);
    }

    public void enterPasswordForAdminSubUser() {
        iframe.switchToPopUpDialogFrame();
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.PASSWORD_FIELD_SUB_ADMIN_USER));
        passwordField.sendKeys(password);
    }

    public void enterRetypePasswordForAdminSubUser() {
        iframe.switchToPopUpDialogFrame();
        WebElement retypePasswordField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.RETYPE_PASSWORD_FIELD_SUB_ADMIN_USER));
        retypePasswordField.sendKeys(password);
    }

    public void enterMobileNumberForAdminSubUser() {
        String mobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToPopUpDialogFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MOBILE_NUMBER_FIELD_SUB_ADMIN_USER));
        enterMobileNumber.sendKeys(mobileNumber);
    }

    public void enterPasswordRecoveryEmailForAdminSubUser() {
        String passwordRecoveryEmail = CommonMethods.generateRandomEmail();
        iframe.switchToPopUpDialogFrame();
        WebElement enterPasswordRecoveryEmailField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.PASSWORD_RECOVERY_EMAIL_FIELD_SUB_ADMIN_USER));
        enterPasswordRecoveryEmailField.sendKeys(passwordRecoveryEmail);
    }

    public void verifyUserNameForAdminSubUser() {
        iframe.switchToSubAdminUserFrame();
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AdminPageLocators.USERNAME_FIELD_SUB_ADMIN_USER));
        String enteredUserName = userNameField.getAttribute("value");

        if (!Objects.equals(enteredUserName, this.userNameOfAdminSubUser)) {
            throw new AssertionError("Username mismatch. Expected: " + this.userNameOfAdminSubUser + ", Found: " + enteredUserName);
        }
    }

    public void clickOnEditSubAdminUserOfAdmin() {
        iframe.switchToSubAdminUserFrame();
        WebElement editSubAdminUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.EDIT_RULE_BUTTON));
        editSubAdminUser.click();
    }

    public void deleteSubAdminUserOfAdmin() throws InterruptedException {
        iframe.switchToSubAdminUserFrame();
        WebElement deleteSubAdminUser = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DELETE_SUB_ADMIN_USER_BUTTON));
        deleteSubAdminUser.click();
        Thread.sleep(1000);
        commonMethods.acceptAlert();
    }
}
