package projects.taskeye.web.pages;

import projects.commonMethods.CompanyPageCommonMethods;
import projects.commonMethods.EmployeePageCommonMethods;
import org.openqa.selenium.*;

public class EmployeePage {

    private final WebDriver driver;
    private final EmployeePageCommonMethods employeePageCommonMethods;
    private final CompanyPageCommonMethods companyPage;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.employeePageCommonMethods = new EmployeePageCommonMethods(driver);
        this.companyPage = new CompanyPageCommonMethods(driver);
    }

    public EmployeePage createEmployeeUser() throws InterruptedException {
        employeePageCommonMethods.addEmployee();
        return this;
    }

    public EmployeePage addEmployeeDetails() throws InterruptedException {
        employeePageCommonMethods.selectCompany("CompoanyTest");
        employeePageCommonMethods.selectDepartment("Add New");
        employeePageCommonMethods.enterDepartmentName();
        employeePageCommonMethods.enterFirstName();
        employeePageCommonMethods.enterMiddleName();
        employeePageCommonMethods.enterLastName();
        employeePageCommonMethods.enterEmployeeNumber();
        employeePageCommonMethods.selectEmployeeType("Employee");
        employeePageCommonMethods.selectLanguage("English");
        employeePageCommonMethods.selectGender();
        employeePageCommonMethods.selectTrackForm();
        employeePageCommonMethods.selectVerificationVia();
        employeePageCommonMethods.enterPassword();
        employeePageCommonMethods.enterRetypePassword();
        employeePageCommonMethods.enterEmailId();
        employeePageCommonMethods.enterMobileNumber();
        employeePageCommonMethods.enterEmergencyContactNumber();
        employeePageCommonMethods.selectTimeZone("UTC+05:30");
        employeePageCommonMethods.selectSpeedDetection("From Latlng");
        employeePageCommonMethods.selectShift("Full Day Shift");
        employeePageCommonMethods.selectDateOfBirth18YearsAgo();
        employeePageCommonMethods.enterJoiningDate();
        employeePageCommonMethods.selectTaskConsideration();
        employeePageCommonMethods.selectWorkingDays("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        return this;
    }

    public EmployeePage addAddressDetails() {
        companyPage.selectTab("Address");
        employeePageCommonMethods.enterStreet1Address();
        employeePageCommonMethods.enterStreet2Address();
        employeePageCommonMethods.enterCityAddress();
        employeePageCommonMethods.enterZipCodeAddress();
        employeePageCommonMethods.selectCountry("India");
        return this;
    }

    public EmployeePage addLeaveDetails() throws InterruptedException {
        companyPage.selectTab("holiday_leave");
        employeePageCommonMethods.addButtonInsideLeaveOfEmployee("__leave_type");
        employeePageCommonMethods.selectLeaveTypeOnLeaveTabOfEmployee("PL");
        employeePageCommonMethods.enterNumberOfDays("10");
        employeePageCommonMethods.validityFromDate();
        employeePageCommonMethods.checkAutoRenewal();
        employeePageCommonMethods.selectRenewalFrequencyOnLeaveTabOfEmployee("Full-Yearly");
        employeePageCommonMethods.checkCarryOver();
        return this;
    }

    public EmployeePage verifyTheCreatedAndDeleteEmployee() throws InterruptedException {
        employeePageCommonMethods.searchTheEmployeeUser();
        employeePageCommonMethods.clickOnCreatedEmployee();
        employeePageCommonMethods.deleteButton();
        employeePageCommonMethods.verifyThatUserDeleted();
        return this;
    }

    public EmployeePage editEmployeeDetails() {
        employeePageCommonMethods.editFirstName();
        employeePageCommonMethods.enterMiddleName();
        employeePageCommonMethods.editLastName();
        employeePageCommonMethods.enterEmployeeNumber();
        employeePageCommonMethods.editEmailId();
        employeePageCommonMethods.enterMobileNumber();
        employeePageCommonMethods.enterEmergencyContactNumber();
        employeePageCommonMethods.selectSpeedDetection("From Device");
        return this;
    }

    public EmployeePage editAddressDetails() {
        companyPage.selectTab("Address");
        employeePageCommonMethods.editStreet1Address();
        employeePageCommonMethods.editStreet2Address();
        employeePageCommonMethods.editCityAddress();
        employeePageCommonMethods.editZipCodeAddress();
        employeePageCommonMethods.selectCountry("India");
        return this;
    }

    public EmployeePage editLeaveDetails() {
        companyPage.selectTab("holiday_leave");
        employeePageCommonMethods.enterNumberOfDays("7");
        return this;
    }

    public EmployeePage fillEmployeeDetailsWithValidation() throws InterruptedException {
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Branch", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("No Branch Selection Found For Saving Record.", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.selectCompany("CompoanyTest");
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Department", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("No Department Selection Found For Saving Record.", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.selectDepartment("Add New");
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("DepartmentName", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterDepartmentName();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyContentOfValidationContent("First Name :Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterFirstName();
        employeePageCommonMethods.enterMiddleName();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyContentOfValidationContent("Last Name :Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterLastName();
        employeePageCommonMethods.enterEmployeeNumber();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Employee Type", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please select value from dropdown", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.selectEmployeeType("Employee");
        employeePageCommonMethods.selectLanguage("English");
        employeePageCommonMethods.selectGender();
        employeePageCommonMethods.selectTrackForm();
        employeePageCommonMethods.selectVerificationVia();
        employeePageCommonMethods.enterEmailId();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Mobile Number", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterMobileNumber();
        employeePageCommonMethods.enterEmergencyContactNumber();
        employeePageCommonMethods.selectTimeZone("UTC+05:30");
        employeePageCommonMethods.selectSpeedDetection("From Latlng");
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Shift", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please Select Shift", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.selectShift("Full Day Shift");
        employeePageCommonMethods.selectDateOfBirth18YearsAgo();
        employeePageCommonMethods.enterJoiningDate();
        employeePageCommonMethods.selectTaskConsideration();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Working Day", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Select Working Days", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.selectWorkingDays("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Password", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterPassword();
        employeePageCommonMethods.saveButtonOfEmployeePage();
        companyPage.verifyHeaderOfValidationPopup("Password and Retype Password", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Not Match", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        employeePageCommonMethods.enterRetypePassword();
        return this;
    }

    public EmployeePage verifyThatCompanyDetailsAreEdited() {
        employeePageCommonMethods.verifyThatFirstNameEdited();
        employeePageCommonMethods.verifyThatLastNameEdited();
        employeePageCommonMethods.verifyThatEmailIdEdited();
        return this;
    }

    public EmployeePage saveButtonOfEmployee() throws InterruptedException {
        employeePageCommonMethods.saveButtonOfEmployeePage();
        return this;
    }

    public EmployeePage searchEmployeeUser() {
        employeePageCommonMethods.searchTheEmployeeUser();
        return this;
    }

    public EmployeePage searchEditedEmployeeUser() {
        employeePageCommonMethods.searchTheEditedEmployeeUser();
        return this;
    }

    public EmployeePage clickOnCreatedEmployeeUser() throws InterruptedException {
        employeePageCommonMethods.clickOnCreatedEmployee();
        return this;
    }

    public EmployeePage clickOnEditedEmployeeUser() throws InterruptedException {
        employeePageCommonMethods.clickOnEditedEmployee();
        return this;
    }

    public EmployeePage deleteEmployeeUser() throws InterruptedException {
        employeePageCommonMethods.deleteButton();
        return this;
    }

    public EmployeePage verifyEmployeeUserIsDeleted() throws InterruptedException {
        employeePageCommonMethods.verifyThatUserDeleted();
        return this;
    }
}
