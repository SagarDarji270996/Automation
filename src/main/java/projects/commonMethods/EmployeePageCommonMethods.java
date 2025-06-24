package projects.commonMethods;

import projects.taskeye.configurations.common.CommonMethods;
import projects.taskeye.configurations.common.DatePickerUtils;
import projects.taskeye.configurations.common.IframesOfApplication;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.taskeye.web.locators.EmployeePageLocators;

public class EmployeePageCommonMethods {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private final CommonMethods commonMethods;
    private final IframesOfApplication iframe;
    private final String password = "Test@123";
    private String employeeNumber;
    private String firstName;
    private String editedFirstName;
    private String lastName;
    private String editedLastName;
    private String emailId;
    private String editedEmailId;
    private String mobileNumber;

    public EmployeePageCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        this.wait = new WebDriverWait(driver, 60);
        this.iframe = new IframesOfApplication(driver);
    }

    public void addEmployee() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ADD_BUTTON));
        addButton.click();
        Thread.sleep(5000);
    }

    public void selectCompany(String companyName) {
        iframe.switchToContentFrame();
        By companyDropdownLocator = EmployeePageLocators.COMPANY_DROPDOWN;
        By companyOptionLocator = By.xpath(String.format(EmployeePageLocators.COMPANY_DROPDOWN_OPTION, companyName));
        commonMethods.selectFromDropdown(companyDropdownLocator, companyOptionLocator);
    }

    public void selectDepartment(String departmentName) {
        iframe.switchToContentFrame();
        By departmentDropdownLocator = EmployeePageLocators.DEPARTMENT_DROPDOWN;
        By departmentOptionLocator = By.xpath(String.format(EmployeePageLocators.DEPARTMENT_DROPDOWN_OPTION, departmentName));
        commonMethods.selectFromDropdown(departmentDropdownLocator, departmentOptionLocator);
    }

    public void enterDepartmentName() {
        String departmentName = "DepartmentName-Test";
        iframe.switchToContentFrame();
        WebElement departmentNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.DEPARTMENT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", departmentNameField);
        departmentNameField.sendKeys(departmentName);
    }

    public void enterFirstName() {
        this.firstName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.FIRST_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", firstNameField);
        firstNameField.sendKeys(this.firstName);
    }

    public void enterMiddleName() {
        String middleName = "Middle Name";
        iframe.switchToContentFrame();
        WebElement middleNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.MIDDLE_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", middleNameField);
        middleNameField.sendKeys(middleName);
    }

    public void enterLastName() {
        this.lastName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.LAST_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", lastNameField);
        lastNameField.sendKeys(this.lastName);
    }

    public void enterEmployeeNumber() {
        this.employeeNumber = CommonMethods.generateRandomNumber();
        iframe.switchToContentFrame();
        WebElement employeeNumberField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.EMPLOYEE_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", employeeNumberField);
        employeeNumberField.sendKeys(this.employeeNumber);
    }

    public void selectEmployeeType(String employeeType) {
        iframe.switchToContentFrame();
        By employeeTypeDropdownLocator = EmployeePageLocators.EMPLOYEE_TYPE_DROPDOWN;
        By employeeTypeOptionLocator = By.xpath(String.format(EmployeePageLocators.EMPLOYEE_TYPE_DROPDOWN_OPTION, employeeType));
        commonMethods.selectFromDropdown(employeeTypeDropdownLocator, employeeTypeOptionLocator);
    }

    public void selectLanguage(String language) {
        iframe.switchToContentFrame();
        By languageDropdownLocator = EmployeePageLocators.LANGUAGE_DROPDOWN;
        By languageOptionLocator = By.xpath(String.format(EmployeePageLocators.LANGUAGE_DROPDOWN_OPTION, language));
        commonMethods.selectFromDropdown(languageDropdownLocator, languageOptionLocator);
    }

    public void selectGender() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(EmployeePageLocators.FEMALE_RADIO_BUTTON);
        commonMethods.selectRadioButton(EmployeePageLocators.MALE_RADIO_BUTTON);
    }

    public void selectTrackForm() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(EmployeePageLocators.TASK_FORM_DEVICE_RADIO_BUTTON);
        commonMethods.selectRadioButton(EmployeePageLocators.TASK_FORM_MOBILE_APP_RADIO_BUTTON);
    }

    public void selectVerificationVia() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(EmployeePageLocators.VERIFICATION_VIA_OTP_RADIO_BUTTON);
        commonMethods.selectRadioButton(EmployeePageLocators.VERIFICATION_VIA_PASSWORD_RADIO_BUTTON);
    }

    public void selectOtpVia() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(EmployeePageLocators.OTP_VIA_EMAIL_RADIO_BUTTON);
        commonMethods.selectRadioButton(EmployeePageLocators.OTP_VIA_SMS_RADIO_BUTTON);
    }

    public void enterPassword() {
        iframe.switchToContentFrame();
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.PASSWORD_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
        passwordField.sendKeys(password);
    }

    public void enterRetypePassword() {
        iframe.switchToContentFrame();
        WebElement retypePasswordField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.RETYPE_PASSWORD_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", retypePasswordField);
        retypePasswordField.sendKeys(password);
    }

    public void enterEmailId() {
        this.emailId = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement emailIdField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.EMAIL_ID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", emailIdField);
        emailIdField.sendKeys(this.emailId);
    }

    public void enterMobileNumber() {
        this.mobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumberField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.MOBILE_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumberField);
        enterMobileNumberField.sendKeys(this.mobileNumber);
    }

    public void enterEmergencyContactNumber() {
        String emergencyContactNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumberField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.EMERGENCY_CONTACT_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumberField);
        enterMobileNumberField.sendKeys(emergencyContactNumber);
    }

    public void selectTimeZone(String timeZoneName) {
        iframe.switchToContentFrame();
        By timeZoneDropdownLocator = EmployeePageLocators.TIMEZONE_DROPDOWN;
        By timeZoneOptionLocator = By.xpath(String.format(EmployeePageLocators.TIMEZONE_DROPDOWN_OPTION, timeZoneName));
        commonMethods.selectFromDropdown(timeZoneDropdownLocator, timeZoneOptionLocator);
    }

    public void selectSpeedDetection(String speedDetection) {
        iframe.switchToContentFrame();
        By speedDetectionDropdownLocator = EmployeePageLocators.SPEED_DETECTION_DROPDOWN;
        By speedDetectionOptionLocator = By.xpath(String.format(EmployeePageLocators.SPEED_DETECTION_DROPDOWN_OPTION, speedDetection));
        commonMethods.selectFromDropdown(speedDetectionDropdownLocator, speedDetectionOptionLocator);
    }

    public void selectShift(String shiftName) {
        iframe.switchToContentFrame();
        By shiftDropdownLocator = EmployeePageLocators.SHIFT_DROPDOWN;
        By shiftOptionLocator = By.xpath(String.format(EmployeePageLocators.SHIFT_DROPDOWN_OPTION, shiftName));
        commonMethods.selectFromDropdown(shiftDropdownLocator, shiftOptionLocator);
    }

    public void selectTaskConsideration() {
        iframe.switchToContentFrame();
        commonMethods.selectRadioButton(EmployeePageLocators.AUTO_TASK_RADIO_BUTTON);
        commonMethods.selectRadioButton(EmployeePageLocators.MANUAL_TASK_RADIO_BUTTON);
    }

    public void selectWorkingDays(String... daysToSelect) throws InterruptedException {
        Thread.sleep(2000);
        iframe.switchToContentFrame();
        commonMethods.selectWorkingDays(daysToSelect);
    }

    public void enterStreet1Address() {
        String street1 = "D-203, Sarthak Safal Appartment";
        iframe.switchToContentFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.STREET1_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet1Field);
        enterStreet1Field.sendKeys(street1);
    }

    public void editStreet1Address() {
        String street1 = "Applo Hospital";
        iframe.switchToContentFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.STREET1_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet1Field);
        enterStreet1Field.sendKeys(street1);
    }

    public void enterStreet2Address() {
        String street2 = "Near Vaidehi Elegance";
        iframe.switchToContentFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.STREET2_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet2Field);
        enterStreet2Field.sendKeys(street2);
    }

    public void editStreet2Address() {
        String street2 = "Near Genpact Elegance";
        iframe.switchToContentFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.STREET2_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterStreet2Field);
        enterStreet2Field.sendKeys(street2);
    }

    public void enterCityAddress() {
        String city = "Gandhinagar";
        iframe.switchToContentFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.CITY_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterCityField);
        enterCityField.sendKeys(city);
    }

    public void editCityAddress() {
        String city = "Ahmedabad";
        iframe.switchToContentFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.CITY_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterCityField);
        enterCityField.sendKeys(city);
    }

    public void enterZipCodeAddress() {
        String zipCode = "382016";
        iframe.switchToContentFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.ZIPCODE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterZipCodeField);
        enterZipCodeField.sendKeys(zipCode);
    }

    public void editZipCodeAddress() {
        String zipCode = "380051";
        iframe.switchToContentFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.ZIPCODE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterZipCodeField);
        enterZipCodeField.sendKeys(zipCode);
    }

    public void selectCountry(String countryName) {
        iframe.switchToContentFrame();
        By countryDropdownLocator = EmployeePageLocators.COUNTRY_DROPDOWN;
        By countryOptionLocator = By.xpath(String.format(EmployeePageLocators.COUNTRY_DROPDOWN_OPTION, countryName));
        commonMethods.selectFromDropdown(countryDropdownLocator, countryOptionLocator);
    }

    public void addButtonInsideLeaveOfEmployee(String name) throws InterruptedException {
        iframe.switchToEmployeeLeaveFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
        Thread.sleep(2000);
    }

    public void selectLeaveTypeOnLeaveTabOfEmployee(String leaveType) {
        iframe.switchToEmployeeLeaveFrame();
        By leaveTypeDropdownLocator = EmployeePageLocators.LEAVE_TYPE_DROPDOWN;
        By leaveTypeOptionLocator = By.xpath(String.format(EmployeePageLocators.LEAVE_TYPE_DROPDOWN_OPTION, leaveType));
        commonMethods.selectFromDropdown(leaveTypeDropdownLocator, leaveTypeOptionLocator);
    }

    public void enterNumberOfDays(String numberOfDays) {
        iframe.switchToEmployeeLeaveFrame();
        WebElement numberOfDaysField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.NO_OF_DAYS_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", numberOfDaysField);
        numberOfDaysField.sendKeys(numberOfDays);
    }

    public void validityFromDate() {
        iframe.switchToEmployeeLeaveFrame();
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        datePickerUtils.selectDate(By.id("leave_start_date00"), 0);
    }

    public void checkAutoRenewal() {
        iframe.switchToEmployeeLeaveFrame();
        WebElement autoRenewalCheckbox = driver.findElement(By.xpath(EmployeePageLocators.AUTO_RENEWAL_CHECKBOX));
        CommonMethods.checkCheckbox(autoRenewalCheckbox);
    }

    public void selectRenewalFrequencyOnLeaveTabOfEmployee(String renewalFrequency) {
        iframe.switchToEmployeeLeaveFrame();
        By renewalFrequencyDropdownLocator = EmployeePageLocators.RENEWAL_FREQUENCY_DROPDOWN;
        By renewalFrequencyOptionLocator = By.xpath(String.format(EmployeePageLocators.RENEWAL_FREQUENCY_DROPDOWN_OPTION, renewalFrequency));
        commonMethods.selectFromDropdown(renewalFrequencyDropdownLocator, renewalFrequencyOptionLocator);
    }

    public void checkCarryOver() {
        iframe.switchToEmployeeLeaveFrame();
        WebElement carryOverCheckbox = driver.findElement(By.xpath(EmployeePageLocators.CARRY_OVER_CHECKBOX));
        CommonMethods.checkCheckbox(carryOverCheckbox);
    }

    public void searchTheEmployeeUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.firstName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void searchTheEditedEmployeeUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.editedFirstName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void clickOnCreatedEmployee() throws InterruptedException {
        Thread.sleep(3000);
        iframe.switchToContentFrame();
        String formattedXpath = String.format(EmployeePageLocators.TABLE_OF_FIRST_NAME, this.firstName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void clickOnEditedEmployee() throws InterruptedException {
        Thread.sleep(3000);
        iframe.switchToContentFrame();
        String formattedXpath = String.format(EmployeePageLocators.TABLE_OF_FIRST_NAME, this.editedFirstName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void deleteButton() {
        iframe.switchToBottomFrame();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DELETE_BUTTON));
        deleteButton.click();
        commonMethods.acceptAlert();
    }

    public void verifyThatUserDeleted() {
        iframe.switchToContentFrame();
        boolean isDeleted = commonMethods.isUserDeleted(this.firstName);
        Assert.assertTrue(isDeleted, "User with First Name '" + this.firstName + "' was not deleted successfully.");
    }

    public void saveButtonOfEmployeePage() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SAVE_BUTTON));
        saveButton.click();
        Thread.sleep(1000);
    }

    public void selectDateOfBirth18YearsAgo() {
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        iframe.switchToContentFrame();
        By dobInputLocator = By.xpath(EmployeePageLocators.DOB_INPUT);
        datePickerUtils.selectDateOfBirth18YearsAgo(dobInputLocator);
    }

    public void enterJoiningDate() {
        iframe.switchToContentFrame();
        DatePickerUtils datePickerUtils = new DatePickerUtils(driver, wait);
        datePickerUtils.selectDate(By.id("-date_of_joining"), 0);
    }

    public void editFirstName() {
        this.editedFirstName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.FIRST_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", firstNameField);
        firstNameField.sendKeys(this.editedFirstName);
    }

    public void editLastName() {
        this.editedLastName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.LAST_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", lastNameField);
        lastNameField.sendKeys(this.editedLastName);
    }

    public void editEmailId() {
        this.editedEmailId = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement emailIdField = wait.until(ExpectedConditions.elementToBeClickable(EmployeePageLocators.EMAIL_ID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", emailIdField);
        emailIdField.sendKeys(this.editedEmailId);
    }

    public void verifyThatFirstNameEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editedFirstName);
        Assert.assertTrue(isEdited, "Short Name '" + this.editedFirstName + "' was not edited successfully.");
    }

    public void verifyThatLastNameEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editedLastName);
        Assert.assertTrue(isEdited, "Short Name '" + this.editedLastName + "' was not edited successfully.");
    }

    public void verifyThatEmailIdEdited() {
        iframe.switchToContentFrame();
        boolean isEdited = commonMethods.isTextPresentInTable(this.editedEmailId);
        Assert.assertTrue(isEdited, "Short Name '" + this.editedEmailId + "' was not edited successfully.");
    }
}
