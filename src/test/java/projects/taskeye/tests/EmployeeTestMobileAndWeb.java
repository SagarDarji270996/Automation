package projects.taskeye.tests;

import projects.taskeye.configurations.base.TestBase;
import projects.taskeye.configurations.config.ConfigLoader;
import listeners.CustomTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.commonMethods.LoginPageCommonMethods;
import projects.taskeye.mobile.pages.MobileLoginPage;
import projects.taskeye.web.pages.EmployeePage;
import projects.taskeye.web.pages.HomePage;

@Listeners({CustomTestListener.class})
public class EmployeeTestMobileAndWeb extends TestBase {
    private final ConfigLoader configLoader = new ConfigLoader();
    private LoginPageCommonMethods loginPageCommonMethods;
    private HomePage homepage;
    private EmployeePage employeePage;
    private MobileLoginPage mobileLoginPage;

    private void loginToApplication() {
        String userType = System.getProperty("userType", "admin");
        String project = System.getProperty("project", "taskeye");
        String credentials = configLoader.getUserCredentials(project, userType);
        String[] credentialParts = credentials.split(":");
        String username = credentialParts[0];
        String password = credentialParts[1];
        loginPageCommonMethods.enterUsername(username);
        loginPageCommonMethods.enterPassword(password);
        loginPageCommonMethods.clickLoginButton();
    }

    private void initializePages() {
        loginPageCommonMethods = new LoginPageCommonMethods(driver);
        homepage = new HomePage(driver);
        employeePage = new EmployeePage(driver);
        mobileLoginPage = new MobileLoginPage(mobileDriver, "android");
    }

    private void navigateToEmployeeSettings() throws InterruptedException {
        homepage.selectProject("TaskEye");
        homepage.navigateToMenu("Settings", "General", "Employee");
    }

    @Test
    public void testCreateEmployeeSuccessfully() throws Exception {
        setUpWeb();
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToEmployeeSettings();
        employeePage.createEmployeeUser()
                .addEmployeeDetails()
                .addAddressDetails()
                .addLeaveDetails()
                .saveButtonOfEmployee();
        setUpMobile();
        initializePages();
        mobileLoginPage.clickOkButtonInBottomSheet();
        employeePage.verifyTheCreatedAndDeleteEmployee();
    }
}
