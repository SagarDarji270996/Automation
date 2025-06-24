package projects.taskeye.tests;

import projects.taskeye.configurations.base.TestBase;
import projects.taskeye.configurations.config.ConfigLoader;
import listeners.CustomTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.commonMethods.LoginPageCommonMethods;
import projects.taskeye.web.pages.CompanyPage;
import projects.taskeye.web.pages.HomePage;

@Listeners({CustomTestListener.class})
public class CompanyTest extends TestBase {

    private final ConfigLoader configLoader = new ConfigLoader();
    private LoginPageCommonMethods loginPageCommonMethods;
    private HomePage homepage;
    private CompanyPage companyPage;

    private void initializePages() {
        loginPageCommonMethods = new LoginPageCommonMethods(driver);
        homepage = new HomePage(driver);
        companyPage = new CompanyPage(driver);
    }

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

    private void navigateToCompanySettings() throws InterruptedException {
        homepage.selectProject("TaskEye");
        homepage.navigateToMenu("Settings", "General", "Company");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        setUpWeb();
    }

    @Test
    public void testCreateCompanySuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToCompanySettings();
        companyPage.createCompanyUser()
                .addCompanyDetails()
                .addRuleOfCompany()
                .addUserSettingOfCompany()
                .addScreenAccessOfCompany()
                .addDataAccessOfCompany()
                .addMapOfCompany()
                .addEmailOfCompany()
                .addSmsOfCompany()
                .addBranchOfCompany()
                .addDepartmentOfCompany()
                .addTaskCategoryOfCompany()
                .addExpenseCategoryOfCompany()
                .addShiftOfCompany()
                .saveButtonOfCompany()
                .verifyTheCreatedAndDeleteCompany();
    }

    @Test
    public void testCreateAndEditCompanySuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToCompanySettings();
        companyPage.createCompanyUser()
                .addCompanyDetails()
                .addRuleOfCompany()
                .addUserSettingOfCompany()
                .addScreenAccessOfCompany()
                .addDataAccessOfCompany()
                .addMapOfCompany()
                .addEmailOfCompany()
                .addSmsOfCompany()
                .addBranchOfCompany()
                .addDepartmentOfCompany()
                .addTaskCategoryOfCompany()
                .addExpenseCategoryOfCompany()
                .addShiftOfCompany()
                .saveButtonOfCompany()
                .searchCompanyUser()
                .clickOnCreatedCompanyUser()
                .editCompanyDetails()
                .verifyThatCompanyDetailsAreEdited()
                .searchCompanyUser()
                .clickOnEditedCompanyUser()
                .editUserSettingOfCompany()
                .editScreenAccessOfCompany()
                .editDataAccessOfCompany()
                .editMapOfCompany()
                .editBranchDetails()
                .verifyThatBranchDetailsOfCompanyAreEdited()
                .editDepartmentDetails()
                .editTaskCategoryDetails()
                .editExpenseCategoryDetails()
                .editShiftDetails()
                .saveButtonOfCompany()
                .searchCompanyUser()
                .clickOnEditedCompanyUser()
                .deleteCompanyUser()
                .verifyCompanyUserIsDeleted();
    }

    @Test
    public void testCreateCompanyWithValidationMessages() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToCompanySettings();
        companyPage.createCompanyUser()
                .fillCompanyDetailsWithValidation()
                .fillCompanyRuleDetailsWithValidation()
                .fillCompanyShiftDetailsWithValidation()
                .saveButtonOfCompany()
                .searchCompanyUser()
                .clickOnCreatedCompanyUser()
                .deleteCompanyUser()
                .verifyCompanyUserIsDeleted();
    }

    @Test
    public void testCreateAndDeleteCompanyWithDuplicateUserValidation() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToCompanySettings();
        companyPage.createCompanyUser()
                .fillCompanyDetailsWithDuplicateValidation()
                .saveButtonOfCompany()
                .searchCompanyUser()
                .clickOnCreatedCompanyUser()
                .deleteCompanyUser()
                .verifyCompanyUserIsDeleted();
    }

    @AfterMethod
    public void tearDownAfterTest() {
        tearDown();
    }
}
