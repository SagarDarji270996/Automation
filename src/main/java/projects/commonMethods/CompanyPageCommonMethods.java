package projects.commonMethods;

import projects.taskeye.configurations.common.CommonMethods;
import projects.taskeye.configurations.common.DatePickerUtils;
import projects.taskeye.configurations.common.IframesOfApplication;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.taskeye.web.locators.AdminPageLocators;
import projects.taskeye.web.locators.CompanyPageLocators;

public class CompanyPageCommonMethods {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private final CommonMethods commonMethods;
    private final IframesOfApplication iframe;
    private final String password = "Test@123";
    private String userName;
    private String shortName;
    private String editShortName;
    private String editBranchNameOfBranch;
    private String editMobileNumber;
    private String editCityOfBranch;
    private String editStreet1OfBranch;
    private String editContactPersonOfBranch;
    private String editMobileNumberOfBranch;
    private String editUserName;

    public CompanyPageCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        this.wait = new WebDriverWait(driver, 60);
        this.iframe = new IframesOfApplication(driver);
    }

    public void addCompany() {
        iframe.switchToBottomFrame();
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ADD_BUTTON));
        loginButton.click();
    }

    public void selectReseller(String resellerName) {
        iframe.switchToContentFrame();
        By resellerDropdownLocator = CompanyPageLocators.RESELLER_DROPDOWN;
        By optionLocator = By.xpath(String.format(CompanyPageLocators.RESELLER_DROPDOWN_OPTION, resellerName));
        commonMethods.selectFromDropdown(resellerDropdownLocator, optionLocator);
    }

    public void enterShortName() {
        this.shortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        enterShortNameField.sendKeys(this.shortName);
    }

    public void enterUserName() {
        this.userName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterUserNameField);
        enterUserNameField.sendKeys(this.userName);
    }

    public void enterConfirmUserName() {
        iframe.switchToContentFrame();
        WebElement enterConfirmUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONFIRM_USERNAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterConfirmUserNameField);
        enterConfirmUserNameField.sendKeys(this.userName);
    }

    public void enterPassword() {
        iframe.switchToContentFrame();
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.PASSWORD_FIELD));
        passwordField.sendKeys(password);
    }

    public void enterRetypePassword() {
        iframe.switchToContentFrame();
        WebElement retypePasswordField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.RETYPE_PASSWORD_FIELD));
        retypePasswordField.sendKeys(password);
    }

    public void enterTelephoneNumber() {
        String telephoneNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterTelephoneNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TELEPHONE_FIELD));
        enterTelephoneNumber.sendKeys(telephoneNumber);
    }

    public void enterMobileNumber() {
        String mobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD));
        enterMobileNumber.sendKeys(mobileNumber);
    }

    public void selectCountry(String countryName) {
        iframe.switchToContentFrame();
        By countryDropdownLocator = CompanyPageLocators.COMPANY_DETAILS_COUNTRY_DROPDOWN;
        By countryOptionLocator = By.xpath(String.format(CompanyPageLocators.COMPANY_DETAILS_COUNTRY_DROPDOWN_OPTION, countryName));
        commonMethods.selectFromDropdown(countryDropdownLocator, countryOptionLocator);
    }

    public void selectState(String stateName) throws InterruptedException {
        Thread.sleep(500);
        iframe.switchToContentFrame();
        By stateDropdownLocator = CompanyPageLocators.COMPANY_DETAILS_STATE_DROPDOWN;
        By stateOptionLocator = By.xpath(String.format(CompanyPageLocators.COMPANY_DETAILS_STATE_DROPDOWN_OPTION, stateName));
        commonMethods.selectFromDropdown(stateDropdownLocator, stateOptionLocator);
    }

    public void enterCity() {
        String city = "Gandhinagar";
        iframe.switchToContentFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CITY_FIELD));
        enterCityField.sendKeys(city);
    }

    public void enterZipcode() {
        String zipCode = "382016";
        iframe.switchToContentFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ZIP_CODE_FIELD));
        enterZipCodeField.sendKeys(zipCode);
    }

    public void enterStreet1() {
        String street1 = "Prem Bang-low";
        iframe.switchToContentFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET1_FIELD));
        enterStreet1Field.sendKeys(street1);
    }

    public void enterStreet2() {
        String street2 = "Street Road";
        iframe.switchToContentFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET2_FIELD));
        enterStreet2Field.sendKeys(street2);
    }

    public void enterContactPerson() {
        String contactPerson = "Vimal Khatri";
        iframe.switchToContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONTACT_PERSON_FIELD));
        contactPersonField.sendKeys(contactPerson);
    }

    public void enterFaxNumber() {
        String faxNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterFaxNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.FAX_NUMBER_FIELD));
        enterFaxNumber.sendKeys(faxNumber);
    }

    public void enterVATINNumber() {
        String vatinNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterVatinNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.VATIN_NUMBER_FIELD));
        enterVatinNumber.sendKeys(vatinNumber);
    }

    public void saveButton() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SAVE_BUTTON));
        saveButton.click();
        Thread.sleep(1000);
    }

    public void selectTab(String tabName) {
        String xpathTemplate = CompanyPageLocators.TABS;
        commonMethods.selectTab(tabName, xpathTemplate);
    }

    public void verifyHeaderOfValidationPopup(String expectedText, String message) {
        iframe.switchToDivFrame();
        commonMethods.assertText(CompanyPageLocators.VALIDATION_POPUP_HEADER, expectedText, message);
    }

    public void verifyContentOfValidationPopUp(String expectedText, String message) {
        iframe.switchToDivFrame();
        commonMethods.assertText(CompanyPageLocators.VALIDATION_MESSAGE, expectedText, message);
    }

    public void verifyContentOfValidationContent(String expectedText, String message) {
        iframe.switchToDivFrame();
        commonMethods.assertText(CompanyPageLocators.VALIDATION_MESSAGE2, expectedText, message);
    }

    public void closeButtonOfPopUp() {
        iframe.switchToDivFrame();
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CLOSE_BUTTON_OF_POPUP));
        closeButton.click();
    }

    public void clickOnCreatedCompany() throws InterruptedException {
        Thread.sleep(3000);
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.shortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void deleteCompanyButton() {
        iframe.switchToBottomFrame();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DELETE_BUTTON));
        deleteButton.click();
        commonMethods.acceptAlert();
    }

    public void verifyThatUserDeleted() {
        iframe.switchToContentFrame();
        boolean isDeleted = commonMethods.isUserDeleted(this.shortName);
        Assert.assertTrue(isDeleted, "User with Short Name '" + this.shortName + "' was not deleted successfully.");
    }

    public void clickOnEditedCompany() {
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.editShortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void addButtonInsideRuleOfCompany(String name) {
        iframe.switchToCompanyRuleContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
    }

    public void enterRuleName() {
        String city = "Test1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.RULE_NAME_FIELD));
        enterCityField.sendKeys(city);
    }

    public void validFromDate() {
        iframe.switchToPopUpDialogFrame();
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        datePickerUtils.selectDate(By.id("-valid_from"), 0);
    }

    public void saveButtonOfDialogBox() throws InterruptedException {
        Thread.sleep(4000);
        iframe.switchToContentFrame();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SAVE_BUTTON_OF_DIALOG_BOX));
        saveButton.click();
        Thread.sleep(4000);
    }

    public void enterBranchName() {
        String branchName = "Branch1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.BRANCH_NAME_FIELD));
        enterCityField.sendKeys(branchName);
    }

    public void enterCityInAddBranch() {
        String city = "Edmonton";
        iframe.switchToPopUpDialogFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CITY_FIELD_IN_BRANCH));
        enterCityField.sendKeys(city);
    }

    public void enterZipcodeInAddBranch() {
        String zipCode = "T6C0Y7";
        iframe.switchToPopUpDialogFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ZIPCODE_FIELD_IN_BRANCH));
        enterZipCodeField.sendKeys(zipCode);
    }

    public void enterStreet1InAddBranch() {
        String street1 = "82 Ave - 87 St";
        iframe.switchToPopUpDialogFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET1_FIELD_IN_BRANCH));
        enterStreet1Field.sendKeys(street1);
    }

    public void enterStreet2InAddBranch() {
        String street2 = "Street Road";
        iframe.switchToPopUpDialogFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET2_FIELD_IN_BRANCH));
        enterStreet2Field.sendKeys(street2);
    }

    public void enterContactPersonInAddBranch() {
        String contactPerson = "Andrew Portal";
        iframe.switchToPopUpDialogFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONTACT_PERSON_FIELD_IN_BRANCH));
        contactPersonField.sendKeys(contactPerson);
    }

    public void enterFaxNumberInAddBranch() {
        String faxNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToPopUpDialogFrame();
        WebElement enterFaxNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.FAX_FIELD_IN_BRANCH));
        enterFaxNumber.sendKeys(faxNumber);
    }

    public void enterMobileNumberInAddBranch() {
        String mobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToPopUpDialogFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD_IN_BRANCH));
        enterMobileNumber.sendKeys(mobileNumber);
    }

    public void enterEmailIdInAddBranch() {
        String emailId = CommonMethods.generateRandomEmail();
        iframe.switchToPopUpDialogFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EMAIL_FIELD_IN_BRANCH));
        enterUserNameField.sendKeys(emailId);
    }

    public void addButtonInsideBranch(String name) {
        iframe.switchToBranchContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void addButtonInsideDepartment(String name) {
        iframe.switchToDepartmentContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void enterDepartmentNameInAddDepartment() {
        String departmentName = "Quality Department";
        iframe.switchToDepartmentContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DEPARTMENT_NAME_FIELD));
        contactPersonField.sendKeys(departmentName);
    }

    public void enterDescriptionInAddDepartment() {
        String departmentDescription = "Testing Process";
        iframe.switchToDepartmentContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DEPARTMENT_DESCRIPTION_FIELD));
        contactPersonField.sendKeys(departmentDescription);
    }

    public void addButtonInsideTaskCategory(String name) {
        iframe.switchTaskCategoryContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void enterTaskCategoryNameInTaskCategory() {
        String taskCategoryName = "QA Automation";
        iframe.switchTaskCategoryContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TASK_CATEGORY_NAME_FIELD));
        contactPersonField.sendKeys(taskCategoryName);
    }

    public void enterDescriptionInTaskCategory() {
        String categoryDescription = "Testing Process";
        iframe.switchTaskCategoryContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TASK_DESCRIPTION_FIELD));
        contactPersonField.sendKeys(categoryDescription);
    }

    public void addButtonInsideExpenseCategory(String name) {
        iframe.switchExpenseCategoryContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void enterExpenseCategoryNameInExpenseCategory() {
        String expenseCategoryName = "Petrol Expense";
        iframe.switchExpenseCategoryContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EXPENSE_CATEGORY_NAME_FIELD));
        contactPersonField.sendKeys(expenseCategoryName);
    }

    public void enterDescriptionInExpenseCategory() {
        String expenseDescription = "This is only for Petrol Expense";
        iframe.switchExpenseCategoryContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EXPENSE_DESCRIPTION_FIELD));
        contactPersonField.sendKeys(expenseDescription);
    }

    public void isClaimableInExpenseCategory() {
        iframe.switchExpenseCategoryContentFrame();
        WebElement expenseClaimableCheckbox = driver.findElement(By.xpath(CompanyPageLocators.EXPENSE_CLAIMABLE_CHECKBOX));
        CommonMethods.checkCheckbox(expenseClaimableCheckbox);
    }

    public void addButtonInsideShift(String name) {
        iframe.switchShiftContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void enterShiftNameInShift() {
        String shiftName = "Morning Shift";
        iframe.switchToPopUpDialogFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHIFT_NAME_FIELD));
        contactPersonField.sendKeys(shiftName);
    }

    public void selectValidFromDateForShift() {
        iframe.switchToPopUpDialogFrame();
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        datePickerUtils.selectFromAndToDate(CompanyPageLocators.SHIFT_VALID_FROM, 1);
    }

    public void selectValidToDateForShift() {
        iframe.switchToPopUpDialogFrame();
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        datePickerUtils.selectFromAndToDate(CompanyPageLocators.SHIFT_VALID_TO, 365);
    }

    public void selectWorkingDays(String... daysToSelect) {
        iframe.switchToPopUpDialogFrame();
        commonMethods.selectWorkingDays(daysToSelect);
    }

    public void selectShiftType() {
        iframe.switchToPopUpDialogFrame();
        commonMethods.selectRadioButton(CompanyPageLocators.FLEXIBLE_SHIFT);
    }

    public void selectDailyWorkingHourWithMinutes(String hourValue, String minuteValue) {
        iframe.switchToPopUpDialogFrame();
        By dailyWorkingHourDropdown = By.name("daily_working_hour_hr");
        commonMethods.selectFromSelectDropdown(dailyWorkingHourDropdown, hourValue);
        By dailyWorkingMinutesDropdown = By.name("daily_working_hour_hr_min");
        commonMethods.selectFromSelectDropdown(dailyWorkingMinutesDropdown, minuteValue);
    }

    public void editShortName() {
        this.editShortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterShortNameField);
        enterShortNameField.sendKeys(this.editShortName);
    }

    public void editUserName() {
        this.editUserName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterUserNameField);
        enterUserNameField.sendKeys(this.editUserName);
    }

    public void editMobileNumber() {
        this.editMobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumber);
        enterMobileNumber.sendKeys(this.editMobileNumber);
    }

    public void editTelephoneNumber() {
        String editTelephoneNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterTelephoneNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TELEPHONE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterTelephoneNumber);
        enterTelephoneNumber.sendKeys(editTelephoneNumber);
    }

    public void verifyThatShortNameEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editShortName);
        Assert.assertTrue(isEdited, "Short Name '" + this.editShortName + "' was not edited successfully.");
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

    public void clickOnEditBranch() {
        iframe.switchToBranchContentFrame();
        WebElement editBranch = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EDIT_BRANCH));
        editBranch.click();
    }

    public void editBranchNameEditBranch() {
        this.editBranchNameOfBranch = "Branch2";
        iframe.switchToPopUpDialogFrame();
        WebElement enterBranchNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.BRANCH_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterBranchNameField);
        enterBranchNameField.sendKeys(this.editBranchNameOfBranch);
    }

    public void editCityInEditBranch() {
        this.editCityOfBranch = "Gandhinagar";
        iframe.switchToPopUpDialogFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CITY_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterCityField);
        enterCityField.sendKeys(this.editCityOfBranch);
    }

    public void editZipcodeInEditBranch() {
        String zipCode = "382016";
        iframe.switchToPopUpDialogFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ZIPCODE_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterZipCodeField);
        enterZipCodeField.sendKeys(zipCode);
    }

    public void editStreet1InEditBranch() {
        this.editStreet1OfBranch = "D-103";
        iframe.switchToPopUpDialogFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET1_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet1Field);
        enterStreet1Field.sendKeys(this.editStreet1OfBranch);
    }

    public void editStreet2InEditBranch() {
        String street2 = "Vaidehi Residency";
        iframe.switchToPopUpDialogFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET2_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet2Field);
        enterStreet2Field.sendKeys(street2);
    }

    public void editContactPersonInEditBranch() {
        this.editContactPersonOfBranch = "Vishal Patel";
        iframe.switchToPopUpDialogFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONTACT_PERSON_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", contactPersonField);
        contactPersonField.sendKeys(this.editContactPersonOfBranch);
    }

    public void editFaxNumberInEditBranch() {
        String faxNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToPopUpDialogFrame();
        WebElement enterFaxNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.FAX_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterFaxNumber);
        enterFaxNumber.sendKeys(faxNumber);
    }

    public void editMobileNumberInEditBranch() {
        this.editMobileNumberOfBranch = CommonMethods.generateRandomMobileNumber();
        iframe.switchToPopUpDialogFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumber);
        enterMobileNumber.sendKeys(this.editMobileNumberOfBranch);
    }

    public void editEmailIdInEditBranch() {
        String emailId = CommonMethods.generateRandomEmail();
        iframe.switchToPopUpDialogFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EMAIL_FIELD_IN_BRANCH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterUserNameField);
        enterUserNameField.sendKeys(emailId);
    }

    public void verifyThatBranchNameEditedBranchTable() {
        iframe.switchToBranchContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editBranchNameOfBranch);
        Assert.assertTrue(isEdited, "Branch Name '" + this.editBranchNameOfBranch + "' was not edited successfully.");
    }

    public void verifyThatCityEditedBranchTable() {
        iframe.switchToBranchContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editCityOfBranch);
        Assert.assertTrue(isEdited, "City Name '" + this.editCityOfBranch + "' was not edited successfully.");
    }

    public void verifyThatStreet1EditedBranchTable() {
        iframe.switchToBranchContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editStreet1OfBranch);
        Assert.assertTrue(isEdited, "Street 1 '" + this.editStreet1OfBranch + "' was not edited successfully.");
    }

    public void verifyThatContactPersonEditedBranchTable() {
        iframe.switchToBranchContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editContactPersonOfBranch);
        Assert.assertTrue(isEdited, "Contact Person '" + this.editContactPersonOfBranch + "' was not edited successfully.");
    }

    public void verifyThatMobileNumberEditedBranchTable() {
        iframe.switchToBranchContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editMobileNumberOfBranch);
        Assert.assertTrue(isEdited, "MobileNumber '" + this.editMobileNumberOfBranch + "' was not edited successfully.");
    }

    public void editDepartmentNameInEditDepartment() {
        String editedDepartmentName = "SDET";
        iframe.switchToDepartmentContentFrame();
        WebElement departmentNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DEPARTMENT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", departmentNameField);
        departmentNameField.sendKeys(editedDepartmentName);
    }

    public void editDescriptionInEditDepartment() {
        String editedDepartmentDescription = "Testing Mobile";
        iframe.switchToDepartmentContentFrame();
        WebElement departmentDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DEPARTMENT_DESCRIPTION_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", departmentDescriptionField);
        departmentDescriptionField.sendKeys(editedDepartmentDescription);
    }

    public void editTaskCategoryNameInEditTaskCategory() {
        String taskCategoryName = "Web Automation";
        iframe.switchTaskCategoryContentFrame();
        WebElement taskCategoryField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TASK_CATEGORY_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", taskCategoryField);
        taskCategoryField.sendKeys(taskCategoryName);
    }

    public void editTaskCategoryDescriptionInEditTaskCategory() {
        String categoryDescription = "Manual testing";
        iframe.switchTaskCategoryContentFrame();
        WebElement taskCategoryDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TASK_DESCRIPTION_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", taskCategoryDescriptionField);
        taskCategoryDescriptionField.sendKeys(categoryDescription);
    }

    public void editExpenseCategoryNameInEditExpenseCategory() {
        String expenseCategoryName = "Crud Oil Expense";
        iframe.switchExpenseCategoryContentFrame();
        WebElement expenseCategoryNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EXPENSE_CATEGORY_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", expenseCategoryNameField);
        expenseCategoryNameField.sendKeys(expenseCategoryName);
    }

    public void editDescriptionInEditExpenseCategory() {
        String expenseDescription = "Recharge You Vehicle Expense";
        iframe.switchExpenseCategoryContentFrame();
        WebElement expenseDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EXPENSE_DESCRIPTION_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", expenseDescriptionField);
        expenseDescriptionField.sendKeys(expenseDescription);
    }

    public void editIsClaimableInEditExpenseCategory() {
        iframe.switchExpenseCategoryContentFrame();
        WebElement expenseClaimableUncheckCheckbox = driver.findElement(By.xpath(CompanyPageLocators.EXPENSE_CLAIMABLE_CHECKBOX));
        CommonMethods.uncheckCheckbox(expenseClaimableUncheckCheckbox);
        WebElement expenseClaimableCheckCheckbox = driver.findElement(By.xpath(CompanyPageLocators.EXPENSE_CLAIMABLE_CHECKBOX));
        CommonMethods.checkCheckbox(expenseClaimableCheckCheckbox);
    }

    public void selectTimeZone(String timezoneName) {
        iframe.switchToContentFrame();
        By timezoneDropdownLocator = CompanyPageLocators.TIMEZONE_DROPDOWN;
        By timezoneOptionLocator = By.xpath(String.format(CompanyPageLocators.TIMEZONE_DROPDOWN_OPTION, timezoneName));
        commonMethods.selectFromDropdown(timezoneDropdownLocator, timezoneOptionLocator);
    }

    public void selectDateFormat(String dateFormat) {
        iframe.switchToContentFrame();
        By dateFormatDropdownLocator = CompanyPageLocators.DATE_FORMAT_DROPDOWN;
        By dateFormatOptionLocator = By.xpath(String.format(CompanyPageLocators.DATE_FORMAT_DROPDOWN_OPTION, dateFormat));
        commonMethods.selectFromDropdown(dateFormatDropdownLocator, dateFormatOptionLocator);
    }

    public void selectTimeFormat(String timeFormat) {
        iframe.switchToContentFrame();
        By timeFormatDropdownLocator = CompanyPageLocators.TIME_FORMAT_DROPDOWN;
        By timeFormatOptionLocator = By.xpath(String.format(CompanyPageLocators.TIME_FORMAT_DROPDOWN_OPTION, timeFormat));
        commonMethods.selectFromDropdown(timeFormatDropdownLocator, timeFormatOptionLocator);
    }

    public void selectUnitsOfDistance(String unitOfDistance) {
        iframe.switchToContentFrame();
        By unitOfDistanceDropdownLocator = CompanyPageLocators.UNITS_OF_DISTANCE_DROPDOWN;
        By unitOfDistanceOptionLocator = By.xpath(String.format(CompanyPageLocators.UNITS_OF_DISTANCE_DROPDOWN_OPTION, unitOfDistance));
        commonMethods.selectFromDropdown(unitOfDistanceDropdownLocator, unitOfDistanceOptionLocator);
    }

    public void selectUserStatus() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(CompanyPageLocators.USER_STATUS_INACTIVE);
        commonMethods.selectRadioButton(CompanyPageLocators.USER_STATUS_ACTIVE);
    }

    public void selectShowDefaultFilterOption() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(CompanyPageLocators.SHOW_DEFAULT_FIlTER_OPTION_IS_OFF);
        commonMethods.selectRadioButton(CompanyPageLocators.SHOW_DEFAULT_FIlTER_OPTION_IS_ON);
    }

    public void checkWebNotification() {
        iframe.switchToContentFrame();
        WebElement webNotificationCheckbox = driver.findElement(By.xpath(CompanyPageLocators.WEB_NOTIFICATION_CHECKBOX));
        CommonMethods.checkCheckbox(webNotificationCheckbox);
    }

    public void selectWebAccess() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(CompanyPageLocators.WEB_ACCESS_NONE);
        commonMethods.selectRadioButton(CompanyPageLocators.WEB_ACCESS_ALL);
    }

    public void selectNMobileAccess() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(CompanyPageLocators.MOBILE_ACCESS_NONE);
        commonMethods.selectRadioButton(CompanyPageLocators.MOBILE_ACCESS_ALL);
    }

    public void clickOnTaskEyeProjectOfScreenAccess() {
        iframe.switchToContentFrame();
        WebElement taskEyeProject = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.TASKEYE_PROJECT));
        taskEyeProject.click();
    }

    public void clickOnDashboardOfTaskEyeProjectOfScreenAccessOfCompany() {
        iframe.switchToContentFrame();
        WebElement dashBoardModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DASHBOARD_SUB_MODULE));
        dashBoardModule.click();
        WebElement viewPermissionOfDashboard = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.MODIFY_PERMISSION_OF_DASHBOARD2));
        viewPermissionOfDashboard.click();
    }

    public void clickOnTrackingOfTaskEyeProjectOfScreenAccessOfCompany() {
        iframe.switchToContentFrame();
        WebElement trackingModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.TRACKING_SUB_MODULE));
        trackingModule.click();
        WebElement viewPermissionOfTracking = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_TRACKING));
        viewPermissionOfTracking.click();
    }

    public void clickOnChartOfTaskEyeProjectOfScreenAccessOfCompany() {
        iframe.switchToContentFrame();
        WebElement chartModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.CHART_SUB_MODULE));
        chartModule.click();
        WebElement viewPermissionOfDistance = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_DISTANCE));
        viewPermissionOfDistance.click();
        WebElement viewPermissionOfAlerts = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_ALERTS));
        viewPermissionOfAlerts.click();
    }

    public void clickOnReportsOfTaskEyeProjectOfScreenAccessOfCompany() {
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
        WebElement viewPermissionOfEmployeeTaskHistoryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMPLOYEE_TASK_HISTORY_REPORT));
        viewPermissionOfEmployeeTaskHistoryReport.click();
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
        WebElement viewPermissionOfEmailSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_EMAIL_SUMMARY_REPORT));
        viewPermissionOfEmailSummaryReport.click();
        WebElement viewPermissionOfSoSSummaryReport = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.VIEW_PERMISSION_OF_SOS_SUMMARY_REPORT));
        viewPermissionOfSoSSummaryReport.click();

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

    public void clickOnSettingsOfTaskEyeProjectOfScreenAccessOfCompany() {
        iframe.switchToContentFrame();

        // Setting main module
        WebElement settingsModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SETTINGS_SUB_MODULE));
        settingsModule.click();

        // general submodule of Setting
        WebElement generalModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.GENERAL_CHILD_MODULE));
        generalModule.click();

        // Setting submodule general list
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

    public void selectAlertOnDataAccessTabOfCompany() {
        iframe.switchToContentFrame();
        WebElement selectChargerAlert = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CHARGER_ALERT_OPTION));
        selectChargerAlert.click();
        WebElement selectAllAlert = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ALL_ALERT_OPTION));
        selectAllAlert.click();
    }

    public void selectLanguageOnDataAccessTabOfCompany(String languageName) {
        iframe.switchToContentFrame();
        By languageDropdownLocator = CompanyPageLocators.LANGUAGE_DROPDOWN;
        By languageOptionLocator = By.xpath(String.format(CompanyPageLocators.LANGUAGE_DROPDOWN_OPTION, languageName));
        commonMethods.selectFromDropdown(languageDropdownLocator, languageOptionLocator);
    }

    public void addButtonInsideMapOfCompany(String name) {
        iframe.switchToCompanyMapContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideCompany.click();
    }

    public void selectMapOnMapTabOfCompany(String mapName) {
        iframe.switchToCompanyMapContentFrame();
        By mapDropdownLocator = CompanyPageLocators.MAP_DROPDOWN;
        By mapOptionLocator = By.xpath(String.format(CompanyPageLocators.MAP_DROPDOWN_OPTION, mapName));
        commonMethods.selectFromDropdown(mapDropdownLocator, mapOptionLocator);
    }

    public void enterWebMapKey(String WebMapKey) {
        iframe.switchToCompanyMapContentFrame();
        WebElement enterWebMapKey = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.WEB_MAP_KEY_FIELD));
        enterWebMapKey.sendKeys(WebMapKey);
    }

    public void enterMobileMapKey(String MobileMapKey) {
        iframe.switchToCompanyMapContentFrame();
        WebElement enterMobileMapKey = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_MAP_KEY_FIELD));
        enterMobileMapKey.sendKeys(MobileMapKey);
    }

    public void checkAddressFromMapProvider() {
        iframe.switchToCompanyMapContentFrame();
        WebElement addressFromMapProviderCheckBox = driver.findElement(By.xpath(CompanyPageLocators.ADDRESS_FROM_MAP_PROVIDER_CHECKBOX));
        CommonMethods.checkCheckbox(addressFromMapProviderCheckBox);
    }

    public void checkDefaultMap() {
        iframe.switchToCompanyMapContentFrame();
        WebElement defaultMapCheckBox = driver.findElement(By.xpath(CompanyPageLocators.DEFAULT_MAP_CHECKBOX));
        CommonMethods.checkCheckbox(defaultMapCheckBox);
    }

    public void enterFromEmailAddressInEmailTabOfCompany(String FromEmailAddress) {
        iframe.switchToContentFrame();
        WebElement enterFromEmailAddress = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.FROM_EMAIL_ADDRESS_FIELD_IN_EMAIL_TAB));
        enterFromEmailAddress.sendKeys(FromEmailAddress);
    }

    public void enterUserNameInEmailTabOfCompany(String UserNameOfEmailTab) {
        iframe.switchToContentFrame();
        WebElement enterUserNameInEmailTabOfCompany = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD_IN_EMAIL_TAB));
        enterUserNameInEmailTabOfCompany.sendKeys(UserNameOfEmailTab);
    }

    public void enterPasswordInEmailTabOfCompany(String PasswordOfEmailTab) {
        iframe.switchToContentFrame();
        WebElement enterPasswordInEmailTabOfCompany = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.PASSWORD_FIELD_IN_EMAIL_TAB));
        enterPasswordInEmailTabOfCompany.sendKeys(PasswordOfEmailTab);
    }

    public void enterHostInEmailTabOfCompany(String HostOfEmailTab) {
        iframe.switchToContentFrame();
        WebElement enterHostInEmailTabOfCompany = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.HOST_FIELD_IN_EMAIL_TAB));
        enterHostInEmailTabOfCompany.sendKeys(HostOfEmailTab);
    }

    public void enterOutgoingPortInEmailTabOfCompany(String OutgoingPortOfEmailTab) {
        iframe.switchToContentFrame();
        WebElement enterOutgoingPortInEmailTabOfCompany = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.OUTGOING_PORT_FIELD_IN_EMAIL_TAB));
        enterOutgoingPortInEmailTabOfCompany.sendKeys(OutgoingPortOfEmailTab);
    }

    public void clickOnTestConfigurationButtonInEmailTabOfCompany() {
        iframe.switchToContentFrame();
        WebElement testConfigurationButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TEST_CONFIGURATION_BUTTON_IN_EMAIL_TAB));
        testConfigurationButton.click();
    }

    public void enterVerifyEmailToInEmailTabOfCompany(String VerifyEmailId) {
        iframe.switchToContentFrame();
        WebElement enterVerifyEmailToInEmailTabOfCompany = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EMAIL_VERIFICATION_FIELD_IN_EMAIL_TAB));
        enterVerifyEmailToInEmailTabOfCompany.sendKeys(VerifyEmailId);
    }

    public void clickOnEmailVerifyButtonInEmailTabOfCompany() {
        iframe.switchToContentFrame();
        WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EMAIL_VERIFICATION_BUTTON_IN_EMAIL_TAB));
        verifyButton.click();
    }

    public void editDashboardOfTaskEyeProjectOfScreenAccessOfCompany() {
        iframe.switchToContentFrame();
        WebElement dashBoardModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DASHBOARD_SUB_MODULE));
        dashBoardModule.click();
        WebElement viewPermissionOfDashboard = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.NO_ACCESS_PERMISSION_OF_DASHBOARD));
        viewPermissionOfDashboard.click();
    }

    public void clickOnEditShift() {
        iframe.switchShiftContentFrame();
        WebElement editShift = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.EDIT_SHIFT));
        editShift.click();
    }

    public void editShiftNameInShift() {
        String shiftName = "Edit Morning Shift";
        iframe.switchToPopUpDialogFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHIFT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", contactPersonField);
        contactPersonField.sendKeys(shiftName);
    }

    public void editWebMapKey(String WebMapKey) {
        iframe.switchToCompanyMapContentFrame();
        WebElement enterWebMapKey = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.WEB_MAP_KEY_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterWebMapKey);
        enterWebMapKey.sendKeys(WebMapKey);
    }

    public void editMobileMapKey(String MobileMapKey) {
        iframe.switchToCompanyMapContentFrame();
        WebElement enterMobileMapKey = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_MAP_KEY_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileMapKey);
        enterMobileMapKey.sendKeys(MobileMapKey);
    }

    public void enterStaticUserName() {
        String userName = "CompoanyTest2709@gmail.com";
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        enterUserNameField.sendKeys(userName);
    }

    public void enterStaticConfirmUserName() {
        String userName = "CompoanyTest2709@gmail.com";
        iframe.switchToContentFrame();
        WebElement enterConfirmUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONFIRM_USERNAME_FIELD));
        enterConfirmUserNameField.sendKeys(userName);
    }

    public void searchTheCompanyUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.shortName);
        searchField.sendKeys(Keys.ENTER);
    }
}
