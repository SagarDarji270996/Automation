package projects.taskeye.web.pages;

import projects.commonMethods.CompanyPageCommonMethods;
import org.openqa.selenium.*;


public class CompanyPage {

    private final WebDriver driver;
    private final CompanyPageCommonMethods companyPageCommonMethods;

    public CompanyPage(WebDriver driver) {
        this.driver = driver;
        this.companyPageCommonMethods = new CompanyPageCommonMethods(driver);
    }

    public CompanyPage createCompanyUser() {
        companyPageCommonMethods.addCompany();
        return this;
    }

    public CompanyPage addCompanyDetails() throws InterruptedException {
//        companyPageCommonMethods.selectReseller("Test Reseller");
//        companyPageCommonMethods.selectReseller("Sagar Reseller");
        companyPageCommonMethods.enterShortName();
        companyPageCommonMethods.enterUserName();
        companyPageCommonMethods.enterConfirmUserName();
        companyPageCommonMethods.enterPassword();
        companyPageCommonMethods.enterRetypePassword();
        companyPageCommonMethods.enterTelephoneNumber();
        companyPageCommonMethods.enterMobileNumber();
        companyPageCommonMethods.selectCountry("India");
        companyPageCommonMethods.selectState("Gujarat");
        companyPageCommonMethods.enterCity();
        companyPageCommonMethods.enterZipcode();
        companyPageCommonMethods.enterStreet1();
        companyPageCommonMethods.enterStreet2();
        companyPageCommonMethods.enterContactPerson();
        companyPageCommonMethods.enterFaxNumber();
        companyPageCommonMethods.enterVATINNumber();
        return this;
    }

    public CompanyPage addRuleOfCompany() throws InterruptedException {
        companyPageCommonMethods.selectTab("Rule");
        companyPageCommonMethods.addButtonInsideRuleOfCompany("__rule_name");
        companyPageCommonMethods.enterRuleName();
        companyPageCommonMethods.validFromDate();
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage addUserSettingOfCompany() {
        companyPageCommonMethods.selectTab("Setting");
        companyPageCommonMethods.selectTimeZone("UTC+01:00 - Africa/Algiers");
        companyPageCommonMethods.selectTimeZone("UTC+05:30 - Asia/Kolkata");
        companyPageCommonMethods.selectDateFormat("MM/dd/yyyy");
        companyPageCommonMethods.selectDateFormat("dd-MM-yyyy");
        companyPageCommonMethods.selectTimeFormat("24 - Hour");
        companyPageCommonMethods.selectTimeFormat("12 - Hour");
        companyPageCommonMethods.selectUnitsOfDistance("Nautical mile");
        companyPageCommonMethods.selectUnitsOfDistance("Kilometer");
        companyPageCommonMethods.selectUserStatus();
        companyPageCommonMethods.selectShowDefaultFilterOption();
        companyPageCommonMethods.checkWebNotification();
        companyPageCommonMethods.selectWebAccess();
        companyPageCommonMethods.selectNMobileAccess();
        return this;
    }

    public CompanyPage addScreenAccessOfCompany() {
        companyPageCommonMethods.selectTab("ScreenRights");
        companyPageCommonMethods.clickOnTaskEyeProjectOfScreenAccess();
//        companyPageCommonMethods.clickOnDashboardOfTaskEyeProjectOfScreenAccessOfCompany();
//        companyPageCommonMethods.clickOnTrackingOfTaskEyeProjectOfScreenAccessOfCompany();
//        companyPageCommonMethods.clickOnChartOfTaskEyeProjectOfScreenAccessOfCompany();
//        companyPageCommonMethods.clickOnReportsOfTaskEyeProjectOfScreenAccessOfCompany();
//        companyPageCommonMethods.clickOnSettingsOfTaskEyeProjectOfScreenAccessOfCompany();
        return this;
    }

    public CompanyPage addDataAccessOfCompany() {
        companyPageCommonMethods.selectTab("ContentRights");
        companyPageCommonMethods.selectAlertOnDataAccessTabOfCompany();
        companyPageCommonMethods.selectLanguageOnDataAccessTabOfCompany("Hindi");
        companyPageCommonMethods.selectLanguageOnDataAccessTabOfCompany("English");
        return this;
    }

    public CompanyPage addMapOfCompany() {
        companyPageCommonMethods.selectTab("Map");
        companyPageCommonMethods.addButtonInsideMapOfCompany("__geomap_id");
        companyPageCommonMethods.selectMapOnMapTabOfCompany("GOOGLE");
        companyPageCommonMethods.enterWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPageCommonMethods.enterMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        companyPageCommonMethods.checkAddressFromMapProvider();
        companyPageCommonMethods.checkDefaultMap();
        return this;
    }

    public CompanyPage addEmailOfCompany() {
        companyPageCommonMethods.selectTab("Email");
        return this;
    }

    public CompanyPage addSmsOfCompany() {
        companyPageCommonMethods.selectTab("SMS");
        return this;
    }

    public CompanyPage addBranchOfCompany() throws InterruptedException {
        companyPageCommonMethods.selectTab("Branch");
        companyPageCommonMethods.addButtonInsideBranch("__short_name_i");
        companyPageCommonMethods.enterBranchName();
        companyPageCommonMethods.enterCityInAddBranch();
        companyPageCommonMethods.enterZipcodeInAddBranch();
        companyPageCommonMethods.enterStreet1InAddBranch();
        companyPageCommonMethods.enterStreet2InAddBranch();
        companyPageCommonMethods.enterContactPersonInAddBranch();
        companyPageCommonMethods.enterMobileNumberInAddBranch();
        companyPageCommonMethods.enterEmailIdInAddBranch();
        companyPageCommonMethods.enterFaxNumberInAddBranch();
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage addDepartmentOfCompany() {
        companyPageCommonMethods.selectTab("Department");
        companyPageCommonMethods.addButtonInsideDepartment("__department_name_i");
        companyPageCommonMethods.enterDepartmentNameInAddDepartment();
        companyPageCommonMethods.enterDescriptionInAddDepartment();
        return this;
    }

    public CompanyPage addTaskCategoryOfCompany() {
        companyPageCommonMethods.selectTab("TaskCategory");
        companyPageCommonMethods.addButtonInsideTaskCategory("__task_category_name_i");
        companyPageCommonMethods.enterTaskCategoryNameInTaskCategory();
        companyPageCommonMethods.enterDescriptionInTaskCategory();
        return this;
    }

    public CompanyPage addExpenseCategoryOfCompany() {
        companyPageCommonMethods.selectTab("ExpenseCategory");
        companyPageCommonMethods.addButtonInsideExpenseCategory("__task_expense_category_name_i");
        companyPageCommonMethods.enterExpenseCategoryNameInExpenseCategory();
        companyPageCommonMethods.enterDescriptionInExpenseCategory();
        companyPageCommonMethods.isClaimableInExpenseCategory();
        return this;
    }

    public CompanyPage addShiftOfCompany() throws InterruptedException {
        companyPageCommonMethods.selectTab("Shift");
        companyPageCommonMethods.addButtonInsideShift("__shift_name");
        companyPageCommonMethods.enterShiftNameInShift();
        companyPageCommonMethods.selectValidFromDateForShift();
        companyPageCommonMethods.selectValidToDateForShift();
        companyPageCommonMethods.selectWorkingDays("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        companyPageCommonMethods.selectShiftType();
        companyPageCommonMethods.selectDailyWorkingHourWithMinutes("08", "30");
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage editScreenAccessOfCompany() {
        companyPageCommonMethods.selectTab("ScreenRights");
        companyPageCommonMethods.clickOnTaskEyeProjectOfScreenAccess();
//        companyPageCommonMethods.editDashboardOfTaskEyeProjectOfScreenAccessOfCompany();
        return this;
    }

    public CompanyPage editUserSettingOfCompany() {
        companyPageCommonMethods.selectTab("Setting");
        companyPageCommonMethods.selectTimeZone("UTC+01:00 - Africa/Algiers");
        companyPageCommonMethods.selectDateFormat("MM/dd/yyyy");
        companyPageCommonMethods.selectTimeFormat("24 - Hour");
        companyPageCommonMethods.selectUnitsOfDistance("Nautical mile");
        companyPageCommonMethods.selectUserStatus();
        companyPageCommonMethods.selectShowDefaultFilterOption();
        companyPageCommonMethods.checkWebNotification();
        companyPageCommonMethods.selectWebAccess();
        companyPageCommonMethods.selectNMobileAccess();
        return this;
    }

    public CompanyPage editDataAccessOfCompany() {
        companyPageCommonMethods.selectTab("ContentRights");
        companyPageCommonMethods.selectAlertOnDataAccessTabOfCompany();
        companyPageCommonMethods.selectLanguageOnDataAccessTabOfCompany("Hindi");
        return this;
    }

    public CompanyPage editMapOfCompany() {
        companyPageCommonMethods.selectTab("Map");
        companyPageCommonMethods.editWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPageCommonMethods.editMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        companyPageCommonMethods.checkAddressFromMapProvider();
        companyPageCommonMethods.checkDefaultMap();
        return this;
    }

    public CompanyPage editCompanyDetails() throws InterruptedException {
        companyPageCommonMethods.editShortName();
        companyPageCommonMethods.editUserName();
        companyPageCommonMethods.editTelephoneNumber();
        companyPageCommonMethods.editMobileNumber();
        companyPageCommonMethods.selectCountry("India");
        companyPageCommonMethods.selectState("Delhi");
        companyPageCommonMethods.saveButton();
        return this;
    }

    public CompanyPage editBranchDetails() throws InterruptedException {
        companyPageCommonMethods.selectTab("Branch");
        companyPageCommonMethods.clickOnEditBranch();
        companyPageCommonMethods.editBranchNameEditBranch();
        companyPageCommonMethods.editCityInEditBranch();
        companyPageCommonMethods.editZipcodeInEditBranch();
        companyPageCommonMethods.editStreet1InEditBranch();
        companyPageCommonMethods.editStreet2InEditBranch();
        companyPageCommonMethods.editContactPersonInEditBranch();
        companyPageCommonMethods.editFaxNumberInEditBranch();
        companyPageCommonMethods.editMobileNumberInEditBranch();
        companyPageCommonMethods.editEmailIdInEditBranch();
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage editDepartmentDetails() {
        companyPageCommonMethods.selectTab("Department");
        companyPageCommonMethods.editDepartmentNameInEditDepartment();
        companyPageCommonMethods.editDescriptionInEditDepartment();
        return this;
    }

    public CompanyPage editTaskCategoryDetails() {
        companyPageCommonMethods.selectTab("TaskCategory");
        companyPageCommonMethods.editTaskCategoryNameInEditTaskCategory();
        companyPageCommonMethods.editTaskCategoryDescriptionInEditTaskCategory();
        return this;
    }

    public CompanyPage editExpenseCategoryDetails() {
        companyPageCommonMethods.selectTab("ExpenseCategory");
        companyPageCommonMethods.editExpenseCategoryNameInEditExpenseCategory();
        companyPageCommonMethods.editDescriptionInEditExpenseCategory();
        companyPageCommonMethods.editIsClaimableInEditExpenseCategory();
        return this;
    }

    public CompanyPage editShiftDetails() throws InterruptedException {
        companyPageCommonMethods.selectTab("Shift");
        companyPageCommonMethods.clickOnEditShift();
        companyPageCommonMethods.editShiftNameInShift();
        companyPageCommonMethods.selectWorkingDays("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        companyPageCommonMethods.selectShiftType();
        companyPageCommonMethods.selectDailyWorkingHourWithMinutes("09", "00");
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage fillCompanyDetailsWithValidation() throws InterruptedException {
//        companyPageCommonMethods.selectReseller("Test Reseller");
//        companyPageCommonMethods.selectReseller("Sagar Reseller");
        companyPageCommonMethods.saveButton();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Short Name", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterShortName();
        companyPageCommonMethods.saveButton();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("User Name", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterUserName();
        companyPageCommonMethods.enterConfirmUserName();
        companyPageCommonMethods.saveButton();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Password", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterPassword();
        companyPageCommonMethods.saveButton();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("RetypePassword", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterRetypePassword();
        companyPageCommonMethods.enterTelephoneNumber();
        companyPageCommonMethods.enterMobileNumber();
        companyPageCommonMethods.selectCountry("India");
        companyPageCommonMethods.selectState("Gujarat");
        companyPageCommonMethods.enterCity();
        companyPageCommonMethods.enterZipcode();
        companyPageCommonMethods.enterStreet1();
        companyPageCommonMethods.enterStreet2();
        companyPageCommonMethods.enterContactPerson();
        companyPageCommonMethods.enterFaxNumber();
        companyPageCommonMethods.enterVATINNumber();
        return this;
    }

    public CompanyPage fillCompanyRuleDetailsWithValidation() throws InterruptedException {
        companyPageCommonMethods.selectTab("Rule");
        companyPageCommonMethods.addButtonInsideRuleOfCompany("__rule_name");
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Rule Name", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field Can Not Be Blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterRuleName();
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Valid", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field Can Not Be Blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.validFromDate();
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage fillCompanyShiftDetailsWithValidation() throws InterruptedException {
        companyPageCommonMethods.selectTab("Shift");
        companyPageCommonMethods.addButtonInsideShift("__shift_name");
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Shift Name ", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field cannot be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterShiftNameInShift();
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Valid From ", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field cannot be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.selectValidFromDateForShift();
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Valid To ", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Field cannot be blank", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.selectValidToDateForShift();
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Working Day", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Select Working Days", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.selectWorkingDays("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        companyPageCommonMethods.selectShiftType();
        companyPageCommonMethods.saveButtonOfDialogBox();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("Daily Working Hour", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Select a value dropdown", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.selectDailyWorkingHourWithMinutes("08", "30");
        companyPageCommonMethods.saveButtonOfDialogBox();
        return this;
    }

    public CompanyPage fillCompanyDetailsWithDuplicateValidation() throws InterruptedException {
//        companyPageCommonMethods.selectReseller("TestingPurpose");
//        companyPageCommonMethods.selectReseller("Sagar");
        companyPageCommonMethods.enterShortName();
        companyPageCommonMethods.enterStaticUserName();
        companyPageCommonMethods.enterStaticConfirmUserName();
        companyPageCommonMethods.enterPassword();
        companyPageCommonMethods.enterRetypePassword();
        companyPageCommonMethods.enterTelephoneNumber();
        companyPageCommonMethods.enterMobileNumber();
        companyPageCommonMethods.selectCountry("India");
        companyPageCommonMethods.selectState("Gujarat");
        companyPageCommonMethods.enterCity();
        companyPageCommonMethods.enterZipcode();
        companyPageCommonMethods.enterStreet1();
        companyPageCommonMethods.enterStreet2();
        companyPageCommonMethods.enterContactPerson();
        companyPageCommonMethods.enterFaxNumber();
        companyPageCommonMethods.enterVATINNumber();
        companyPageCommonMethods.saveButton();
        companyPageCommonMethods.verifyHeaderOfValidationPopup("User Name", "Header text does not match!");
        companyPageCommonMethods.verifyContentOfValidationPopUp("Duplicate Username", "Content text does not match!");
        companyPageCommonMethods.closeButtonOfPopUp();
        companyPageCommonMethods.enterUserName();
        companyPageCommonMethods.enterConfirmUserName();
        return this;
    }

    public CompanyPage verifyThatCompanyDetailsAreEdited() {
        companyPageCommonMethods.verifyThatShortNameEdited();
        companyPageCommonMethods.verifyThatUserNameEdited();
        companyPageCommonMethods.verifyThatMobileNumberEdited();
        return this;
    }

    public CompanyPage verifyThatBranchDetailsOfCompanyAreEdited() {
        companyPageCommonMethods.verifyThatBranchNameEditedBranchTable();
        companyPageCommonMethods.verifyThatCityEditedBranchTable();
        companyPageCommonMethods.verifyThatStreet1EditedBranchTable();
        companyPageCommonMethods.verifyThatContactPersonEditedBranchTable();
        companyPageCommonMethods.verifyThatMobileNumberEditedBranchTable();
        return this;
    }

    public CompanyPage verifyTheCreatedAndDeleteCompany() throws InterruptedException {
        companyPageCommonMethods.searchTheCompanyUser();
        companyPageCommonMethods.clickOnCreatedCompany();
        companyPageCommonMethods.deleteCompanyButton();
        companyPageCommonMethods.verifyThatUserDeleted();
        return this;
    }

    public CompanyPage saveButtonOfCompany() throws InterruptedException {
        companyPageCommonMethods.saveButton();
        return this;
    }

    public CompanyPage searchCompanyUser() {
        companyPageCommonMethods.searchTheCompanyUser();
        return this;
    }

    public CompanyPage clickOnCreatedCompanyUser() throws InterruptedException {
        companyPageCommonMethods.clickOnCreatedCompany();
        return this;
    }

    public CompanyPage clickOnEditedCompanyUser() {
        companyPageCommonMethods.clickOnEditedCompany();
        return this;
    }

    public CompanyPage deleteCompanyUser() throws InterruptedException {
        companyPageCommonMethods.deleteCompanyButton();
        return this;
    }

    public CompanyPage verifyCompanyUserIsDeleted() throws InterruptedException {
        companyPageCommonMethods.verifyThatUserDeleted();
        return this;
    }


}


