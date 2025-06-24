package projects.taskeye.web.pages;//package pages.taskeye.pages;
//
//import common.CommonMethods;
//import common.IframesOfApplication;
//import locators.taskeye.LeavePageLocators;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class LeavePage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private final CommonMethods commonMethods;
//    private final IframesOfApplication iframe;
//    private static final Logger logger = LogManager.getLogger(LeavePage.class);
//
//    public LeavePage(WebDriver driver) {
//        this.driver = driver;
//        this.commonMethods = new CommonMethods(driver);
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        this.iframe = new IframesOfApplication(driver);
//    }
//
//    public void verifyLeaveHeaderText(String expectedHeaderText) {
//        String headerXPath = LeavePageLocators.TEXT_HEADER_NAME.replace("headerName", expectedHeaderText);
//        commonMethods.verifyElementText(headerXPath, expectedHeaderText, "Header text verification on Leave page");
//    }
//
//    public void addLeave(){
//        iframe.switchToBottomFrame();
//        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LeavePageLocators.ADD_BUTTON));
//        loginButton.click();
//    }
//
//    public void selectCompany(String companyId, String companyName) {
//        By companyDropdown = By.id(companyId);
//        commonMethods.selectFromDropdown(companyDropdown, companyName);
//    }
//
//    // Method to select Branch from the dropdown
//    public void selectBranch(String branchId, String branchName) {
//        By branchDropdown = By.id(branchId); // Convert branchId to By locator (e.g., By.id)
//        commonMethods.selectFromDropdown(branchDropdown, branchName);
//    }
//
//    // Method to select Department from the dropdown
//    public void selectDepartment(String departmentId, String departmentName) {
//        By departmentDropdown = By.id(departmentId); // Convert departmentId to By locator (e.g., By.id)
//        commonMethods.selectFromDropdown(departmentDropdown, departmentName);
//    }
//    public void selectEmployee(String employee_id,String companyName){
//        By employeeDropdown = By.id(employee_id);
//        commonMethods.selectFromDropdown(employeeDropdown,companyName);
//    }
//
//    public void selectLeaveType(String leave_type_id,String companyName){
//        By leaveTypeDropdown = By.id(leave_type_id);
//        commonMethods.selectFromDropdown(leaveTypeDropdown,companyName);
//    }
//
//    public void enterReason(String reason){
//        iframe.switchToContentFrame();
//        WebElement enterReasonField = wait.until(ExpectedConditions.elementToBeClickable(LeavePageLocators.REASON_FIELD));
//        enterReasonField.sendKeys(reason);
//    }
//
//    public void selectFromDate() {
//        iframe.switchToContentFrame();
//        commonMethods.selectDate("-from_date");
//    }
//
//    public void selectToDate() {
//        iframe.switchToContentFrame();
//        commonMethods.selectDate("-to_date");
//    }
//
//    public void selectSanction() {
//        iframe.switchToContentFrame();
//        commonMethods.selectRadioButton(LeavePageLocators.SANCTION_RADIO_BUTTON);
//    }
//
//    public void selectReject() {
//        iframe.switchToContentFrame();
//        commonMethods.selectRadioButton(LeavePageLocators.REJECT_RADIO_BUTTON);
//    }
//
//    public void saveButton(){
//        iframe.switchToBottomFrame();
//        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(LeavePageLocators.SAVE_BUTTON));
//        saveButton.click();
//    }
//
//    public void filterButton(){
//        iframe.switchToTitleFrame();
//        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(LeavePageLocators.FILTER_BUTTON));
//        filterButton.click();
//    }
//}
