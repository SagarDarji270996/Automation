package projects.taskeye.tests;

import projects.taskeye.configurations.base.TestBase;
import projects.taskeye.configurations.config.ConfigLoader;
import listeners.CustomTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.commonMethods.LoginPageCommonMethods;
import projects.taskeye.web.pages.AdminPage;
import projects.taskeye.web.pages.HomePage;

@Listeners({CustomTestListener.class})
public class AdminTest extends TestBase {

    private final ConfigLoader configLoader = new ConfigLoader();
    private LoginPageCommonMethods loginPageCommonMethods;
    private HomePage homepage;
    private AdminPage adminPage;

    private void initializePages() {
        loginPageCommonMethods = new LoginPageCommonMethods(driver);
        homepage = new HomePage(driver);
        adminPage = new AdminPage(driver);
    }

    private void loginToApplication() {
        String userType = System.getProperty("userType", "superadmin");
        String project = System.getProperty("project", "taskeye");
        String credentials = configLoader.getUserCredentials(project, userType);
        String[] credentialParts = credentials.split(":");
        String username = credentialParts[0];
        String password = credentialParts[1];
        loginPageCommonMethods.enterUsername(username);
        loginPageCommonMethods.enterPassword(password);
        loginPageCommonMethods.clickLoginButton();
    }

    private void navigateToAdminSettings() throws InterruptedException {
        homepage.selectProject("TaskEye");
        homepage.navigateToMenu("Settings", "General", "Admin");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        setUpWeb();
    }

    @Test
    public void testCreateAdminSuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToAdminSettings();
        adminPage.createAdminUser()
                .addAdminDetails()
                .addRuleOfAdmin()
                .addUserSettingOfCompany()
                .addScreenAccessOfAdmin()
                .addDataAccessOfAdmin()
                .addMapOfAdmin()
                .addEmailOfAdmin()
                .addSmsOfAdmin()
                .addTemplatesOfAdmin()
                .addPaymentGatewayOfAdmin()
                .addRenameLabelOfAdmin()
                .addIVROfAdmin()
                .addWhiteLabelOfAdmin()
                .addChatbotOfAdmin()
                .addSocialMediaApiOfAdmin()
                .addDocumentOfAdmin()
                .addAuthenticationOfAdmin()
                .addSingleSignOnOfAdmin()
                .saveButtonOfAdmin()
                .verifyTheCreatedAndDeleteAdmin();
    }

    @Test
    public void testCreateEditDeleteAdminSuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToAdminSettings();
        adminPage.createAdminUser()
                .addAdminDetails()
                .addRuleOfAdmin()
                .addUserSettingOfCompany()
                .addScreenAccessOfAdmin()
                .addDataAccessOfAdmin()
                .addMapOfAdmin()
                .addEmailOfAdmin()
                .addSmsOfAdmin()
                .addTemplatesOfAdmin()
                .addPaymentGatewayOfAdmin()
                .addRenameLabelOfAdmin()
                .addIVROfAdmin()
                .addWhiteLabelOfAdmin()
                .addChatbotOfAdmin()
                .addSocialMediaApiOfAdmin()
                .addDocumentOfAdmin()
                .addAuthenticationOfAdmin()
                .addSingleSignOnOfAdmin()
                .saveButtonOfAdmin()
                .searchAdminUser()
                .clickOnCreatedAdminUser()
                .editAdminDetails()
                .editRuleDetailsOfAdmin()
                .editUserSettingOfAdmin()
                .editScreenAccessOfAdmin()
                .editDataAccessOfAdmin()
                .editMapOfAdmin()
                .editPaymentGatewayOfAdmin()
                .editRenameLabelOfAdmin()
                .editIVROfAdmin()
                .saveButtonOfAdmin()
                .searchEditedAdminUser()
                .verifyThatAdminDetailsAreEdited()
                .clickOnEditedAdminUser()
                .deleteAdminUser()
                .verifyAdminUserIsDeleted();
    }

    @Test
    public void testCreateAdminWithValidationMessages() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToAdminSettings();
        adminPage.createAdminUser()
                .fillAdminDetailsWithValidation()
                .fillAdminRuleDetailsWithValidation()
                .fillAdminMapDetailsWithValidation()
                .fillAdminPaymentGatewayDetailsWithValidation()
                .fillAdminRenameLabelDetailsWithValidation()
                .saveButtonOfAdmin()
                .verifyTheCreatedAndDeleteAdmin();
    }


    @Test
    public void testCreateSubAdminUserSuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToAdminSettings();
        adminPage.createAdminUser()
                .addAdminDetails()
                .addRuleOfAdmin()
                .addUserSettingOfCompany()
                .addScreenAccessOfAdmin()
                .addDataAccessOfAdmin()
                .addMapOfAdmin()
                .addEmailOfAdmin()
                .addSmsOfAdmin()
                .addTemplatesOfAdmin()
                .addPaymentGatewayOfAdmin()
                .addRenameLabelOfAdmin()
                .addIVROfAdmin()
                .addWhiteLabelOfAdmin()
                .addChatbotOfAdmin()
                .addSocialMediaApiOfAdmin()
                .addDocumentOfAdmin()
                .addAuthenticationOfAdmin()
                .addSingleSignOnOfAdmin()
                .saveButtonOfAdmin()
                .searchAdminUser()
                .clickOnCreatedAdminUser()
                .addAdminSubUserOfAdmin()
                .deleteSubAdminUser()
                .saveButtonOfAdmin()
                .verifyTheCreatedAndDeleteAdmin();
    }

    @AfterMethod
    public void tearDownAfterTest() {
        tearDown();
    }
}