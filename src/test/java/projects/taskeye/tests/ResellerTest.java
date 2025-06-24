package projects.taskeye.tests;

import projects.taskeye.configurations.base.TestBase;
import projects.taskeye.configurations.config.ConfigLoader;
import listeners.CustomTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.commonMethods.LoginPageCommonMethods;
import projects.taskeye.web.pages.HomePage;
import projects.taskeye.web.pages.ResellerPage;

@Listeners({CustomTestListener.class})
public class ResellerTest extends TestBase {

    private final ConfigLoader configLoader = new ConfigLoader();
    private LoginPageCommonMethods loginPageCommonMethods;
    private HomePage homepage;
    private ResellerPage resellerPage;

    private void initializePages() {
        loginPageCommonMethods = new LoginPageCommonMethods(driver);
        homepage = new HomePage(driver);
        resellerPage = new ResellerPage(driver);
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

    private void navigateToResellerSettings() throws InterruptedException {
        homepage.selectProject("TaskEye");
        homepage.navigateToMenu("Settings", "General", "Reseller");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        setUpWeb();
    }

    @Test
    public void testCreateResellerSuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToResellerSettings();
        resellerPage.createResellerUser()
                .addResellerDetails()
                .addRuleOfReseller()
                .addUserSettingOfReseller()
                .addScreenAccessOfReseller()
                .addDataAccessOfReseller()
                .addMapOfReseller()
                .addEmailOfReseller()
                .addSmsOfReseller()
                .addTemplatesOfReseller()
                .addPaymentGatewayOfReseller()
                .addRenameLabelOfReseller()
                .addIVROfReseller()
                .addChatbotOfReseller()
                .addSocialMediaApiOfReseller()
                .addDocumentOfReseller()
                .addAuthenticationOfReseller()
                .addSingleSignOnOfReseller()
                .saveButtonOfReseller()
                .verifyTheCreatedAndDeleteReseller();
    }

    @Test
    public void testCreateEditDeleteResellerSuccessfully() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToResellerSettings();
        resellerPage.createResellerUser()
                .addResellerDetails()
                .addRuleOfReseller()
                .addUserSettingOfReseller()
                .addScreenAccessOfReseller()
                .addDataAccessOfReseller()
                .addMapOfReseller()
                .addEmailOfReseller()
                .addSmsOfReseller()
                .addTemplatesOfReseller()
                .addPaymentGatewayOfReseller()
                .addRenameLabelOfReseller()
                .addIVROfReseller()
                .addChatbotOfReseller()
                .addSocialMediaApiOfReseller()
                .addDocumentOfReseller()
                .addAuthenticationOfReseller()
                .addSingleSignOnOfReseller()
                .saveButtonOfReseller()
                .searchResellerUser()
                .clickOnCreatedResellerUser()
                .editResellerDetails()
                .editRuleDetailsOfReseller()
                .editUserSettingOfReseller()
                .editScreenAccessOfReseller()
                .editDataAccessOfReseller()
                .editMapOfReseller()
                .editPaymentGatewayOfReseller()
                .editRenameLabelOfReseller()
                .editIVROfReseller()
                .saveButtonOfReseller()
                .searchEditedResellerUser()
                .verifyThatResellerDetailsAreEdited()
                .clickOnEditedResellerUser()
                .deleteResellerUser()
                .verifyResellerUserIsDeleted();
    }

    @Test
    public void testCreateResellerWithValidationMessages() throws Exception {
        driver.get(getWebsiteUrl());
        initializePages();
        loginToApplication();
        navigateToResellerSettings();
        resellerPage.createResellerUser()
                .fillResellerDetailsWithValidation()
                .fillResellerRuleDetailsWithValidation()
                .fillResellerMapDetailsWithValidation()
                .fillResellerPaymentGatewayDetailsWithValidation()
                .fillResellerRenameLabelDetailsWithValidation()
                .saveButtonOfReseller()
                .verifyTheCreatedAndDeleteReseller();
    }

    @AfterMethod
    public void tearDownAfterTest() {
        tearDown();
    }
}
