package projects.taskeye.web.pages;

import org.openqa.selenium.*;
import projects.commonMethods.AdminPageCommonMethods;
import projects.commonMethods.CompanyPageCommonMethods;
import projects.commonMethods.ResellerPageCommonMethods;

public class AdminPage {

    private final WebDriver driver;
    private final AdminPageCommonMethods adminPageCommonMethods;
    private final ResellerPageCommonMethods resellerPage;
    private final CompanyPageCommonMethods companyPage;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.adminPageCommonMethods = new AdminPageCommonMethods(driver);
        this.resellerPage = new ResellerPageCommonMethods(driver);
        this.companyPage = new CompanyPageCommonMethods(driver);
    }

    public AdminPage createAdminUser() throws InterruptedException {
        adminPageCommonMethods.addAdmin();
        return this;
    }

    public AdminPage addAdminDetails() throws InterruptedException {
        adminPageCommonMethods.selectCountryForAdmin("India");
        adminPageCommonMethods.selectStateForAdmin("Gujarat");
        adminPageCommonMethods.enterShortNameForAdmin();
        adminPageCommonMethods.enterUserNameForAdmin();
        adminPageCommonMethods.enterConfirmUserNameForAdmin();
        adminPageCommonMethods.enterPasswordForAdmin();
        resellerPage.enterRetypePasswordForReseller();
        resellerPage.enterPasswordRecoveryEmailForReseller();
        resellerPage.enterCityForReseller();
        resellerPage.enterZipcodeForReseller();
        resellerPage.enterStreet1ForReseller();
        resellerPage.enterStreet2ForReseller();
        resellerPage.enterContactPersonForReseller();
        resellerPage.enterHelpDeskTelephoneNumberForReseller();
        resellerPage.enterHelpDeskEmailForReseller();
        resellerPage.enterMobileNumberForReseller();
        resellerPage.enterWhatsappContactNumberForReseller();
        resellerPage.enterFaxNumberForReseller();
        resellerPage.enterWelcomeMessageForReseller();
        resellerPage.enterDataStorageForReseller();
        return this;
    }

    public AdminPage addRuleOfAdmin() throws InterruptedException {
        companyPage.selectTab("Rule");
        adminPageCommonMethods.addButtonInsideRuleOfAdmin("__rule_name");
        companyPage.enterRuleName();
        companyPage.validFromDate();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage addUserSettingOfCompany() {
        companyPage.selectTab("Setting");
        adminPageCommonMethods.selectTimeZoneInUserSettingOfAdmin("UTC+01:00 - Africa/Algiers");
        adminPageCommonMethods.selectTimeZoneInUserSettingOfAdmin("UTC+05:30 - Asia/Kolkata");
        companyPage.selectDateFormat("MM/dd/yyyy");
        companyPage.selectDateFormat("dd-MM-yyyy");
        companyPage.selectTimeFormat("24 - Hour");
        companyPage.selectTimeFormat("12 - Hour");
        adminPageCommonMethods.selectWeekStartDay("Monday");
        companyPage.selectUserStatus();
        companyPage.selectShowDefaultFilterOption();
        companyPage.selectWebAccess();
        companyPage.selectNMobileAccess();
        return this;
    }

    public AdminPage addScreenAccessOfAdmin() {
        companyPage.selectTab("ScreenRights");
        companyPage.clickOnTaskEyeProjectOfScreenAccess();
//        adminPageCommonMethods.clickOnDashboardOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnTrackingOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnChartOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnReportsOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnSettingsOfTaskEyeProjectOfScreenAccessOfAdmin();
        return this;
    }

    public AdminPage addDataAccessOfAdmin() {
        companyPage.selectTab("ContentRights");
        resellerPage.selectAlertOnDataAccessTabOfReseller("InActive");
        resellerPage.selectTimeZoneRightsOnDataAccessTabOfReseller("UTC+10:00 - Australia/Hobart");
        adminPageCommonMethods.enterServerIpForAdmin();
        adminPageCommonMethods.enterSOSAuthorizedIpForAdmin();
        adminPageCommonMethods.enterDomainForAdmin();
        adminPageCommonMethods.enterAndroidAppLinkForAdmin();
        adminPageCommonMethods.enterIosAppLinkForAdmin();
        return this;
    }

    public AdminPage addMapOfAdmin() {
        companyPage.selectTab("Map");
        companyPage.addButtonInsideMapOfCompany("__geomap_id");
        companyPage.selectMapOnMapTabOfCompany("GOOGLE");
        companyPage.enterWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPage.enterMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        resellerPage.enterMapProjectIdOnMapOfReseller("0987654321cd-98765432-ijkl");
        companyPage.checkDefaultMap();
        return this;
    }

    public AdminPage addEmailOfAdmin() {
        companyPage.selectTab("Email");
        return this;
    }

    public AdminPage addSmsOfAdmin() {
        companyPage.selectTab("SMS");
        return this;
    }

    public AdminPage addTemplatesOfAdmin() throws InterruptedException {
        companyPage.selectTab("Template");
        resellerPage.addButtonInsideTemplateOfReseller("__template_name");
        resellerPage.selectNameOnTemplatesTabOfReseller("Welcome Message");
        resellerPage.enterTemplateDescriptionForReseller();
        resellerPage.enterTemplateSmsMessageForReseller();
        resellerPage.enterTemplateSmsTemplateIdForReseller();
        resellerPage.enterTemplateEmailSubjectForReseller();
        resellerPage.enterTemplateEmailMessageForReseller();
        resellerPage.enterTemplateDefaultEmailMessageForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage addPaymentGatewayOfAdmin() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPage.addButtonInsidePaymentGatewayOfReseller("__gateway_name");
        resellerPage.selectGatewayOnPaymentGatewayTabOfReseller("PayPal");
        resellerPage.enterPayementGatewayNameForReseller();
        resellerPage.enterPayementGatewayClientIdForReseller();
        resellerPage.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage addRenameLabelOfAdmin() {
        companyPage.selectTab("RenameLabel");
        resellerPage.addButtonInsideRenameLabelOfReseller("__actualproperty_name");
        resellerPage.enterRenameLabelActualNameForReseller();
        resellerPage.enterRenameLabelNewNameForReseller();
        return this;
    }

    public AdminPage addIVROfAdmin() {
        companyPage.selectTab("ivrcall");
        resellerPage.selectIvrGatewayOnIvrTabOfReseller("Custom");
        resellerPage.enterIvrAccountSidForReseller();
        resellerPage.enterIvrAccountTokenForReseller();
        resellerPage.enterIvrFromNumberForReseller();
        return this;
    }

    public AdminPage addWhiteLabelOfAdmin() {
        companyPage.selectTab("WhiteLabel");
        return this;
    }

    public AdminPage addChatbotOfAdmin() {
        companyPage.selectTab("ChatBot");
        return this;
    }

    public AdminPage addSocialMediaApiOfAdmin() throws InterruptedException {
        companyPage.selectTab("SocialMediaAPI");
        resellerPage.addButtonInsideScoialMediaApiOfReseller("__social_gateway");
        resellerPage.enterSocialMediaApiBotHttpTokenForReseller();
        resellerPage.isConsiderAnnouncementCheck();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage addDocumentOfAdmin() {
        companyPage.selectTab("Document");
        return this;
    }

    public AdminPage addAuthenticationOfAdmin() {
        companyPage.selectTab("Authentication");
        return this;
    }

    public AdminPage addSingleSignOnOfAdmin() {
        companyPage.selectTab("SSO");
        return this;
    }

    public AdminPage addAdminSubUserOfAdmin() throws InterruptedException {
        companyPage.selectTab("SubAccount");
        adminPageCommonMethods.addButtonInsideAdminSubUserOfAdmin("__username_i");
        adminPageCommonMethods.enterUserNameForAdminSubUser();
        adminPageCommonMethods.enterConfirmUserNameForAdminSubUser();
        adminPageCommonMethods.enterPasswordForAdminSubUser();
        adminPageCommonMethods.enterRetypePasswordForAdminSubUser();
        adminPageCommonMethods.enterMobileNumberForAdminSubUser();
        adminPageCommonMethods.enterPasswordRecoveryEmailForAdminSubUser();
        companyPage.saveButtonOfDialogBox();
        adminPageCommonMethods.verifyUserNameForAdminSubUser();
        return this;
    }

    public AdminPage verifyTheCreatedAndDeleteAdmin() throws InterruptedException {
        adminPageCommonMethods.searchTheAdminUser();
        adminPageCommonMethods.clickOnCreatedAdmin();
        adminPageCommonMethods.deleteButtonAdmin();
        adminPageCommonMethods.verifyThatUserDeleted();
        return this;
    }

    public AdminPage editAdminDetails() throws InterruptedException {
        adminPageCommonMethods.editAdminShortName();
        adminPageCommonMethods.editAdminUserName();
        adminPageCommonMethods.editAdminTelephoneNumber();
        adminPageCommonMethods.editAdminMobileNumber();
        adminPageCommonMethods.selectCountryForAdmin("India");
        adminPageCommonMethods.selectStateForAdmin("Gujarat");
        return this;
    }

    public AdminPage editRuleDetailsOfAdmin() throws InterruptedException {
        companyPage.selectTab("Rule");
        adminPageCommonMethods.clickOnEditRuleOfAdmin();
        adminPageCommonMethods.editRuleName();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage editUserSettingOfAdmin() {
        companyPage.selectTab("Setting");
        adminPageCommonMethods.selectTimeZoneInUserSettingOfAdmin("UTC+01:00 - Africa/Algiers");
        companyPage.selectDateFormat("MM/dd/yyyy");
        companyPage.selectTimeFormat("24 - Hour");
        adminPageCommonMethods.selectWeekStartDay("Sunday");
        companyPage.selectUserStatus();
        companyPage.selectShowDefaultFilterOption();
        return this;
    }

    public AdminPage editScreenAccessOfAdmin() {
        companyPage.selectTab("ScreenRights");
        companyPage.clickOnTaskEyeProjectOfScreenAccess();
//        adminPageCommonMethods.editDashboardOfTaskEyeProjectOfScreenAccessOfAdmin();
        return this;
    }

    public AdminPage editDataAccessOfAdmin() {
        companyPage.selectTab("ContentRights");
        resellerPage.selectAlertOnDataAccessTabOfReseller("Battery");
        resellerPage.selectTimeZoneRightsOnDataAccessTabOfReseller("UTC+00:00 - Africa/Bissau");
        return this;

    }

    public AdminPage editMapOfAdmin() {
        companyPage.selectTab("Map");
        companyPage.editWebMapKey("efgh-abcd-efgh-efgh-0987654321cd");
        companyPage.editMobileMapKey("qrst-zyxw-vuts-0987654321cd-0987654321cd");
        resellerPage.editMapProjectIdOnMapOfReseller("0987654321cd-98765432-abcd");
        return this;
    }

    public AdminPage editTemplatesOfAdmin() throws InterruptedException {
        companyPage.selectTab("Template");
        resellerPage.clickOnEditTemplatePropertiesOfReseller();
        resellerPage.editTemplateDescriptionForReseller();
        resellerPage.editTemplateSmsMessageForReseller();
        resellerPage.editTemplateSmsTemplateIdForReseller();
        resellerPage.editTemplateEmailSubjectForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage editPaymentGatewayOfAdmin() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPage.clickOnEditPaymentGatewayOfReseller();
        resellerPage.enterPayementGatewayNameForReseller();
        resellerPage.enterPayementGatewayClientIdForReseller();
        resellerPage.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage editRenameLabelOfAdmin() {
        companyPage.selectTab("RenameLabel");
        resellerPage.enterRenameLabelActualNameForReseller();
        resellerPage.enterRenameLabelNewNameForReseller();
        return this;
    }

    public AdminPage editIVROfAdmin() {
        companyPage.selectTab("ivrcall");
        resellerPage.enterIvrAccountSidForReseller();
        resellerPage.enterIvrAccountTokenForReseller();
        resellerPage.enterIvrFromNumberForReseller();
        return this;
    }

    public AdminPage verifyThatAdminDetailsAreEdited() {
        adminPageCommonMethods.verifyThatUserNameEdited();
        adminPageCommonMethods.verifyThatMobileNumberEdited();
        return this;
    }

    public AdminPage fillAdminDetailsWithValidation() throws InterruptedException {
        adminPageCommonMethods.selectCountryForAdmin("India");
        adminPageCommonMethods.selectStateForAdmin("Gujarat");
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Short Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        adminPageCommonMethods.enterShortNameForAdmin();
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("User Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        adminPageCommonMethods.enterUserNameForAdmin();
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Confirm User Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        adminPageCommonMethods.enterConfirmUserNameForAdmin();
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Password", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        adminPageCommonMethods.enterPasswordForAdmin();
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("RetypePassword", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterRetypePasswordForReseller();
        resellerPage.enterPasswordRecoveryEmailForReseller();
        resellerPage.enterCityForReseller();
        resellerPage.enterZipcodeForReseller();
        resellerPage.enterStreet1ForReseller();
        resellerPage.enterStreet2ForReseller();
        resellerPage.enterContactPersonForReseller();
        resellerPage.enterHelpDeskTelephoneNumberForReseller();
        resellerPage.enterHelpDeskEmailForReseller();
        resellerPage.enterMobileNumberForReseller();
        resellerPage.enterWhatsappContactNumberForReseller();
        resellerPage.enterFaxNumberForReseller();
        resellerPage.enterWelcomeMessageForReseller();
        resellerPage.enterDataStorageForReseller();
        return this;
    }

    public AdminPage fillAdminRuleDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("Rule");
        adminPageCommonMethods.addButtonInsideRuleOfAdmin("__rule_name");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Rule Name ", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.enterRuleName();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Valid From", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.validFromDate();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage fillAdminMapDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("Map");
        companyPage.addButtonInsideMapOfCompany("__geomap_id");
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Map", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please select one Map", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.selectMapOnMapTabOfCompany("GOOGLE");
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Web Map Key", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.enterWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPage.enterMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        resellerPage.enterMapProjectIdOnMapOfReseller("0987654321cd-98765432-ijkl");
        companyPage.checkDefaultMap();
        return this;
    }

    public AdminPage fillAdminPaymentGatewayDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPage.addButtonInsidePaymentGatewayOfReseller("__gateway_name");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Gateway", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please select value from dropdown", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.selectGatewayOnPaymentGatewayTabOfReseller("PayPal");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Gateway Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterPayementGatewayNameForReseller();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Client ID", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterPayementGatewayClientIdForReseller();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Secret key", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public AdminPage fillAdminRenameLabelDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("RenameLabel");
        resellerPage.addButtonInsideRenameLabelOfReseller("__actualproperty_name");
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("Actual Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("field cannot be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterRenameLabelActualNameForReseller();
        adminPageCommonMethods.saveButtonOfAdminPage();
        companyPage.verifyHeaderOfValidationPopup("New Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("field cannot be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPage.enterRenameLabelNewNameForReseller();
        return this;
    }


    public AdminPage searchAdminUser() {
        adminPageCommonMethods.searchTheAdminUser();
        return this;
    }

    public AdminPage searchEditedAdminUser() {
        adminPageCommonMethods.searchTheEditedAdminUser();
        return this;
    }

    public AdminPage clickOnCreatedAdminUser() throws InterruptedException {
        adminPageCommonMethods.clickOnCreatedAdmin();
        return this;
    }

    public AdminPage clickOnEditedAdminUser() {
        adminPageCommonMethods.clickOnEditedAdmin();
        return this;
    }

    public AdminPage deleteAdminUser() throws InterruptedException {
        adminPageCommonMethods.deleteButtonAdmin();
        return this;
    }


    public AdminPage deleteSubAdminUser() throws InterruptedException {
        adminPageCommonMethods.deleteSubAdminUserOfAdmin();
        return this;
    }

    public AdminPage verifyAdminUserIsDeleted() throws InterruptedException {
        adminPageCommonMethods.verifyThatUserDeleted();
        return this;
    }

    public AdminPage saveButtonOfAdmin() throws InterruptedException {
        adminPageCommonMethods.saveButtonOfAdminPage();
        return this;
    }
}
