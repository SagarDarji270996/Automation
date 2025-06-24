package projects.taskeye.web.pages;

import projects.commonMethods.AdminPageCommonMethods;
import projects.commonMethods.CompanyPageCommonMethods;
import projects.commonMethods.ResellerPageCommonMethods;
import org.openqa.selenium.*;

public class ResellerPage {

    private final WebDriver driver;
    private final ResellerPageCommonMethods resellerPageCommonMethods;
    private final CompanyPageCommonMethods companyPageCommonMethods;
    private final AdminPageCommonMethods adminPageCommonMethods;
    private final CompanyPageCommonMethods companyPage;


    public ResellerPage(WebDriver driver) {
        this.driver = driver;
        this.resellerPageCommonMethods = new ResellerPageCommonMethods(driver);
        this.companyPageCommonMethods = new CompanyPageCommonMethods(driver);
        this.adminPageCommonMethods = new AdminPageCommonMethods(driver);
        this.companyPage = new CompanyPageCommonMethods(driver);
    }

    public ResellerPage createResellerUser() throws InterruptedException {
        resellerPageCommonMethods.addReseller();
        return this;
    }


    public ResellerPage addResellerDetails() throws InterruptedException {
        resellerPageCommonMethods.selectCountryForReseller("India");
        resellerPageCommonMethods.selectStateForReseller("Gujarat");
        resellerPageCommonMethods.enterShortNameForReseller();
        resellerPageCommonMethods.enterUserNameForReseller();
        resellerPageCommonMethods.enterConfirmUserNameForReseller();
        resellerPageCommonMethods.enterPasswordForReseller();
        resellerPageCommonMethods.enterRetypePasswordForReseller();
        resellerPageCommonMethods.enterPasswordRecoveryEmailForReseller();
        resellerPageCommonMethods.enterCityForReseller();
        resellerPageCommonMethods.enterZipcodeForReseller();
        resellerPageCommonMethods.enterStreet1ForReseller();
        resellerPageCommonMethods.enterStreet2ForReseller();
        resellerPageCommonMethods.enterContactPersonForReseller();
        resellerPageCommonMethods.enterHelpDeskTelephoneNumberForReseller();
        resellerPageCommonMethods.enterHelpDeskEmailForReseller();
        resellerPageCommonMethods.enterMobileNumberForReseller();
        resellerPageCommonMethods.enterWhatsappContactNumberForReseller();
        resellerPageCommonMethods.enterFaxNumberForReseller();
        resellerPageCommonMethods.enterWelcomeMessageForReseller();
        resellerPageCommonMethods.enterDataStorageForReseller();
        return this;
    }

    public ResellerPage addRuleOfReseller() throws InterruptedException {
        companyPage.selectTab("Rule");
        resellerPageCommonMethods.addButtonInsideRuleOfReseller("__rule_name");
        companyPage.enterRuleName();
        resellerPageCommonMethods.enterDescriptionOfRuleTab();
        companyPage.validFromDate();
        resellerPageCommonMethods.enterSpeedConsiderationMomentOfRuleTab();
        resellerPageCommonMethods.enterMaxSpeedValidatorOfRuleTab();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage addUserSettingOfReseller() {
        companyPage.selectTab("Settings");
        resellerPageCommonMethods.selectTimeZone("UTC+01:00 - Africa/Algiers");
        resellerPageCommonMethods.selectTimeZone("UTC+05:30 - Asia/Kolkata");
        companyPage.selectDateFormat("MM/dd/yyyy");
        companyPage.selectDateFormat("dd-MM-yyyy");
        companyPage.selectTimeFormat("24 - Hour");
        companyPage.selectTimeFormat("12 - Hour");
        companyPage.selectUserStatus();
        companyPage.selectShowDefaultFilterOption();
        return this;
    }

    public ResellerPage addScreenAccessOfReseller() {
        companyPage.selectTab("ScreenRights");
        companyPage.clickOnTaskEyeProjectOfScreenAccess();
//        adminPageCommonMethods.clickOnDashboardOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnTrackingOfTaskEyeProjectOfScreenAccessOfAdmin();
//        adminPageCommonMethods.clickOnChartOfTaskEyeProjectOfScreenAccessOfAdmin();
//        companyPageCommonMethods.clickOnReportsOfTaskEyeProjectOfScreenAccessOfCompany();
//        companyPageCommonMethods.clickOnSettingsOfTaskEyeProjectOfScreenAccessOfCompany();
        return this;
    }

    public ResellerPage addDataAccessOfReseller() {
        companyPage.selectTab("ContentRights");
        resellerPageCommonMethods.selectAlertOnDataAccessTabOfReseller("InActive");
        resellerPageCommonMethods.selectTimeZoneRightsOnDataAccessTabOfReseller("UTC+10:00 - Australia/Hobart");
        resellerPageCommonMethods.selectSimProviderOnDataAccessTabOfReseller("All");
        resellerPageCommonMethods.selectSimProviderOnDataAccessTabOfReseller("Vodafone");
        return this;
    }

    public ResellerPage addMapOfReseller() {
        companyPage.selectTab("Map");
        companyPage.addButtonInsideMapOfCompany("__geomap_id");
        companyPage.selectMapOnMapTabOfCompany("GOOGLE");
        companyPage.enterWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPage.enterMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        resellerPageCommonMethods.enterMapProjectIdOnMapOfReseller("0987654321cd-98765432-ijkl");
        companyPage.checkDefaultMap();
        return this;
    }

    public ResellerPage addEmailOfReseller() {
        companyPage.selectTab("Email");
        return this;
    }

    public ResellerPage addSmsOfReseller() {
        companyPage.selectTab("SMS");
        return this;
    }

    public ResellerPage addTemplatesOfReseller() throws InterruptedException {
        companyPage.selectTab("Template");
        resellerPageCommonMethods.addButtonInsideTemplateOfReseller("__template_name");
        resellerPageCommonMethods.selectNameOnTemplatesTabOfReseller("Welcome Message");
        resellerPageCommonMethods.enterTemplateDescriptionForReseller();
        resellerPageCommonMethods.enterTemplateSmsMessageForReseller();
        resellerPageCommonMethods.enterTemplateSmsTemplateIdForReseller();
        resellerPageCommonMethods.enterTemplateEmailSubjectForReseller();
        resellerPageCommonMethods.enterTemplateEmailMessageForReseller();
        resellerPageCommonMethods.enterTemplateDefaultEmailMessageForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage addPaymentGatewayOfReseller() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPageCommonMethods.addButtonInsidePaymentGatewayOfReseller("__gateway_name");
        resellerPageCommonMethods.selectGatewayOnPaymentGatewayTabOfReseller("PayPal");
        resellerPageCommonMethods.enterPayementGatewayNameForReseller();
        resellerPageCommonMethods.enterPayementGatewayClientIdForReseller();
        resellerPageCommonMethods.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage addRenameLabelOfReseller() {
        companyPage.selectTab("RenameLabel");
        resellerPageCommonMethods.addButtonInsideRenameLabelOfReseller("__actualproperty_name");
        resellerPageCommonMethods.enterRenameLabelActualNameForReseller();
        resellerPageCommonMethods.enterRenameLabelNewNameForReseller();
        return this;
    }

    public ResellerPage addIVROfReseller() {
        companyPage.selectTab("ivrcall");
        resellerPageCommonMethods.selectIvrGatewayOnIvrTabOfReseller("Custom");
        resellerPageCommonMethods.enterIvrAccountSidForReseller();
        resellerPageCommonMethods.enterIvrAccountTokenForReseller();
        resellerPageCommonMethods.enterIvrFromNumberForReseller();
        return this;
    }

    public ResellerPage addChatbotOfReseller() {
        companyPage.selectTab("ChatBot");
        return this;
    }

    public ResellerPage addSocialMediaApiOfReseller() throws InterruptedException {
        companyPage.selectTab("SocialMediaAPI");
        resellerPageCommonMethods.addButtonInsideScoialMediaApiOfReseller("__social_gateway");
        resellerPageCommonMethods.enterSocialMediaApiBotHttpTokenForReseller();
        resellerPageCommonMethods.isConsiderAnnouncementCheck();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage addDocumentOfReseller() {
        companyPage.selectTab("Document");
        return this;
    }

    public ResellerPage addAuthenticationOfReseller() {
        companyPage.selectTab("Authentication");
        return this;
    }

    public ResellerPage addSingleSignOnOfReseller() {
        companyPage.selectTab("SSO");
        return this;
    }

    public ResellerPage verifyTheCreatedAndDeleteReseller() throws InterruptedException {
        resellerPageCommonMethods.searchTheResellerUser();
        resellerPageCommonMethods.clickOnCreatedReseller();
        resellerPageCommonMethods.deleteButtonReseller();
        resellerPageCommonMethods.verifyThatUserDeleted();
        return this;
    }

    public ResellerPage fillResellerDetailsWithValidation() throws InterruptedException {
        resellerPageCommonMethods.selectCountryForReseller("India");
        resellerPageCommonMethods.selectStateForReseller("Gujarat");
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Short Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterShortNameForReseller();
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("User Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterUserNameForReseller();
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Confirm Username", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterConfirmUserNameForReseller();
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Password", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterPasswordForReseller();
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("RetypePassword", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterRetypePasswordForReseller();
        resellerPageCommonMethods.enterPasswordRecoveryEmailForReseller();
        resellerPageCommonMethods.enterCityForReseller();
        resellerPageCommonMethods.enterZipcodeForReseller();
        resellerPageCommonMethods.enterStreet1ForReseller();
        resellerPageCommonMethods.enterStreet2ForReseller();
        resellerPageCommonMethods.enterContactPersonForReseller();
        resellerPageCommonMethods.enterHelpDeskTelephoneNumberForReseller();
        resellerPageCommonMethods.enterHelpDeskEmailForReseller();
        resellerPageCommonMethods.enterMobileNumberForReseller();
        resellerPageCommonMethods.enterWhatsappContactNumberForReseller();
        resellerPageCommonMethods.enterFaxNumberForReseller();
        resellerPageCommonMethods.enterWelcomeMessageForReseller();
        resellerPageCommonMethods.enterDataStorageForReseller();
        return this;
    }

    public ResellerPage fillResellerRuleDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("Rule");
        resellerPageCommonMethods.addButtonInsideRuleOfReseller("__rule_name");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Rule Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.enterRuleName();
        resellerPageCommonMethods.enterDescriptionOfRuleTab();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Valid From", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.validFromDate();
        resellerPageCommonMethods.enterSpeedConsiderationMomentOfRuleTab();
        resellerPageCommonMethods.enterMaxSpeedValidatorOfRuleTab();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage fillResellerMapDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("Map");
        companyPage.addButtonInsideMapOfCompany("__geomap_id");
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Map", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please select one Map", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.selectMapOnMapTabOfCompany("GOOGLE");
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Web Map Key", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        companyPage.enterWebMapKey("12345678-abcd-efgh-ijkl-1234567890ab");
        companyPage.enterMobileMapKey("98765432-zyxw-vuts-qrst-0987654321cd");
        resellerPageCommonMethods.enterMapProjectIdOnMapOfReseller("0987654321cd-98765432-ijkl");
        companyPage.checkDefaultMap();
        return this;
    }

    public ResellerPage fillResellerPaymentGatewayDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPageCommonMethods.addButtonInsidePaymentGatewayOfReseller("__gateway_name");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Gateway", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Please select value from dropdown", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.selectGatewayOnPaymentGatewayTabOfReseller("PayPal");
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Gateway Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterPayementGatewayNameForReseller();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Client ID", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterPayementGatewayClientIdForReseller();
        companyPage.saveButtonOfDialogBox();
        companyPage.verifyHeaderOfValidationPopup("Secret key", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("Field can not be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage fillResellerRenameLabelDetailsWithValidation() throws InterruptedException {
        companyPage.selectTab("RenameLabel");
        resellerPageCommonMethods.addButtonInsideRenameLabelOfReseller("__actualproperty_name");
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("Actual Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("field cannot be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterRenameLabelActualNameForReseller();
        resellerPageCommonMethods.saveButtonOfResellerPage();
        companyPage.verifyHeaderOfValidationPopup("New Name", "Header text does not match!");
        companyPage.verifyContentOfValidationPopUp("field cannot be blank", "Content text does not match!");
        companyPage.closeButtonOfPopUp();
        resellerPageCommonMethods.enterRenameLabelNewNameForReseller();
        return this;
    }

    public ResellerPage editResellerDetails() throws InterruptedException {
        resellerPageCommonMethods.editResellerShortName();
        resellerPageCommonMethods.editResellerUserName();
        resellerPageCommonMethods.editResellerTelephoneNumber();
        resellerPageCommonMethods.editResellerMobileNumber();
        resellerPageCommonMethods.selectCountryForReseller("India");
        resellerPageCommonMethods.selectStateForReseller("Gujarat");
        return this;
    }

    public ResellerPage verifyThatResellerDetailsAreEdited() {
        resellerPageCommonMethods.verifyThatUserNameEdited();
        resellerPageCommonMethods.verifyThatMobileNumberEdited();
        return this;
    }

    public ResellerPage editRuleDetailsOfReseller() throws InterruptedException {
        companyPage.selectTab("Rule");
        resellerPageCommonMethods.clickOnEditRuleOfReseller();
        resellerPageCommonMethods.editRuleName();
        resellerPageCommonMethods.editDescriptionOfRuleTab();
        resellerPageCommonMethods.enterSpeedConsiderationMomentOfRuleTab();
        resellerPageCommonMethods.enterMaxSpeedValidatorOfRuleTab();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage editUserSettingOfReseller() {
        companyPage.selectTab("Settings");
        resellerPageCommonMethods.selectTimeZone("UTC+01:00 - Africa/Algiers");
        companyPage.selectDateFormat("MM/dd/yyyy");
        companyPage.selectTimeFormat("24 - Hour");
        companyPage.selectUserStatus();
        companyPage.selectShowDefaultFilterOption();
        return this;
    }

    public ResellerPage editScreenAccessOfReseller() {
        companyPage.selectTab("ScreenRights");
        companyPage.clickOnTaskEyeProjectOfScreenAccess();
//        resellerPageCommonMethods.editDashboardOfTaskEyeProjectOfScreenAccessOfReseller();
        return this;
    }

    public ResellerPage editDataAccessOfReseller() {
        companyPage.selectTab("ContentRights");
        resellerPageCommonMethods.selectAlertOnDataAccessTabOfReseller("Battery");
        resellerPageCommonMethods.selectTimeZoneRightsOnDataAccessTabOfReseller("UTC+00:00 - Africa/Bissau");
        resellerPageCommonMethods.selectSimProviderOnDataAccessTabOfReseller("Vodafone");
        resellerPageCommonMethods.selectSimProviderOnDataAccessTabOfReseller("Reliance");
        return this;
    }

    public ResellerPage editMapOfReseller() {
        companyPage.selectTab("Map");
        companyPage.editWebMapKey("efgh-abcd-efgh-efgh-0987654321cd");
        companyPage.editMobileMapKey("qrst-zyxw-vuts-0987654321cd-0987654321cd");
        resellerPageCommonMethods.editMapProjectIdOnMapOfReseller("0987654321cd-98765432-abcd");
        return this;
    }

    public ResellerPage editTemplatesOfReseller() throws InterruptedException {
        companyPage.selectTab("Template");
        resellerPageCommonMethods.clickOnEditTemplatePropertiesOfReseller();
        resellerPageCommonMethods.editTemplateDescriptionForReseller();
        resellerPageCommonMethods.editTemplateSmsMessageForReseller();
        resellerPageCommonMethods.editTemplateSmsTemplateIdForReseller();
        resellerPageCommonMethods.editTemplateEmailSubjectForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage editPaymentGatewayOfReseller() throws InterruptedException {
        companyPage.selectTab("PaymentGateway");
        resellerPageCommonMethods.clickOnEditPaymentGatewayOfReseller();
        resellerPageCommonMethods.enterPayementGatewayNameForReseller();
        resellerPageCommonMethods.enterPayementGatewayClientIdForReseller();
        resellerPageCommonMethods.enterPayementGatewaySecretKeyForReseller();
        companyPage.saveButtonOfDialogBox();
        return this;
    }

    public ResellerPage editRenameLabelOfReseller() {
        companyPage.selectTab("RenameLabel");
        resellerPageCommonMethods.enterRenameLabelActualNameForReseller();
        resellerPageCommonMethods.enterRenameLabelNewNameForReseller();
        return this;
    }

    public ResellerPage editIVROfReseller() {
        companyPage.selectTab("ivrcall");
        resellerPageCommonMethods.enterIvrAccountSidForReseller();
        resellerPageCommonMethods.enterIvrAccountTokenForReseller();
        resellerPageCommonMethods.enterIvrFromNumberForReseller();
        return this;
    }

    public ResellerPage saveButtonOfReseller() throws InterruptedException {
        resellerPageCommonMethods.saveButtonOfResellerPage();
        return this;
    }

    public ResellerPage searchResellerUser() {
        resellerPageCommonMethods.searchTheResellerUser();
        return this;
    }

    public ResellerPage searchEditedResellerUser() {
        resellerPageCommonMethods.searchTheEditedResellerUser();
        return this;
    }

    public ResellerPage clickOnCreatedResellerUser() throws InterruptedException {
        resellerPageCommonMethods.clickOnCreatedReseller();
        return this;
    }

    public ResellerPage clickOnEditedResellerUser() {
        resellerPageCommonMethods.clickOnEditedReseller();
        return this;
    }

    public ResellerPage deleteResellerUser() throws InterruptedException {
        resellerPageCommonMethods.deleteButtonReseller();
        return this;
    }

    public ResellerPage verifyResellerUserIsDeleted() throws InterruptedException {
        resellerPageCommonMethods.verifyThatUserDeleted();
        return this;
    }

}
