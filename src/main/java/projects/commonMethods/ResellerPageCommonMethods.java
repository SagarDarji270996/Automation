package projects.commonMethods;

import projects.taskeye.configurations.common.CommonMethods;
import projects.taskeye.configurations.common.IframesOfApplication;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.taskeye.web.locators.AdminPageLocators;
import projects.taskeye.web.locators.CompanyPageLocators;
import projects.taskeye.web.locators.ResellerPageLocators;

public class ResellerPageCommonMethods {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private final CommonMethods commonMethods;
    private final IframesOfApplication iframe;
    private final String password = "Test@123";
    private String userName;
    private String shortName;
    private String editShortName;
    private String editMobileNumber;
    private String editUserName;

    public ResellerPageCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        this.wait = new WebDriverWait(driver, 60);
        this.iframe = new IframesOfApplication(driver);
    }


    public void addReseller() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ADD_BUTTON));
        addButton.click();
        Thread.sleep(5000);
    }

    public void selectCountryForReseller(String countryName) throws InterruptedException {
        iframe.switchToContentFrame();
        By countryDropdownLocator = ResellerPageLocators.RESELLER_DETAILS_COUNTRY_DROPDOWN;
        By countryOptionLocator = By.xpath(String.format(ResellerPageLocators.RESELLER_DETAILS_COUNTRY_DROPDOWN_OPTION, countryName));
        commonMethods.selectFromDropdown(countryDropdownLocator, countryOptionLocator);
    }

    public void selectStateForReseller(String stateName) throws InterruptedException {
        Thread.sleep(500);
        iframe.switchToContentFrame();
        By stateDropdownLocator = ResellerPageLocators.RESELLER_DETAILS_STATE_DROPDOWN;
        By stateOptionLocator = By.xpath(String.format(ResellerPageLocators.RESELLER_DETAILS_STATE_DROPDOWN_OPTION, stateName));
        commonMethods.selectFromDropdown(stateDropdownLocator, stateOptionLocator);
    }

    public void enterShortNameForReseller() {
        this.shortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        enterShortNameField.sendKeys(this.shortName);
    }

    public void enterUserNameForReseller() {
        this.userName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        enterUserNameField.sendKeys(this.userName);
    }

    public void enterConfirmUserNameForReseller() {
        iframe.switchToContentFrame();
        WebElement enterConfirmUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONFIRM_USERNAME_FIELD));
        enterConfirmUserNameField.sendKeys(this.userName);
    }

    public void enterPasswordForReseller() {
        iframe.switchToContentFrame();
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.PASSWORD_FIELD));
        passwordField.sendKeys(password);
    }

    public void enterRetypePasswordForReseller() {
        iframe.switchToContentFrame();
        WebElement retypePasswordField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.RETYPE_PASSWORD_FIELD));
        retypePasswordField.sendKeys(password);
    }

    public void enterHelpDeskTelephoneNumberForReseller() {
        String helpDeskTelephoneNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterTelephoneNumber = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.HELP_DESK_TELEPHONE_NUMBER_FIELD));
        enterTelephoneNumber.sendKeys(helpDeskTelephoneNumber);
    }

    public void enterMobileNumberForReseller() {
        String mobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD));
        enterMobileNumber.sendKeys(mobileNumber);
    }

    public void enterCityForReseller() {
        String city = "Gandhinagar";
        iframe.switchToContentFrame();
        WebElement enterCityField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CITY_FIELD));
        enterCityField.sendKeys(city);
    }

    public void enterZipcodeForReseller() {
        String zipCode = "382016";
        iframe.switchToContentFrame();
        WebElement enterZipCodeField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.ZIP_CODE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterZipCodeField);
        enterZipCodeField.sendKeys(zipCode);
    }

    public void enterStreet1ForReseller() {
        String street1 = "Prem Bang-low";
        iframe.switchToContentFrame();
        WebElement enterStreet1Field = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.STREET1_FIELD));
        enterStreet1Field.sendKeys(street1);
    }

    public void enterStreet2ForReseller() {
        String street2 = "Street Road";
        iframe.switchToContentFrame();
        WebElement enterStreet2Field = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.STREET2_FIELD));
        enterStreet2Field.sendKeys(street2);
    }

    public void enterContactPersonForReseller() {
        String contactPerson = "Vimal Khatri";
        iframe.switchToContentFrame();
        WebElement contactPersonField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.CONTACT_PERSON_FIELD));
        contactPersonField.sendKeys(contactPerson);
    }

    public void enterHelpDeskEmailForReseller() {
        String helpDeskEmail = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.HELP_DESK_EMAIL_FIELD));
        enterUserNameField.sendKeys(helpDeskEmail);
    }

    public void enterWhatsappContactNumberForReseller() {
        String whatsappMobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.WHATSAPP_CONTACT_NUMBER_FIELD));
        enterMobileNumber.sendKeys(whatsappMobileNumber);
    }

    public void enterFaxNumberForReseller() {
        String faxNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterFaxNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.FAX_NUMBER_FIELD));
        enterFaxNumber.sendKeys(faxNumber);
    }

    public void enterWelcomeMessageForReseller() {
        String welcomeMessage = "Welcome to the Testing Team !!!";
        iframe.switchToContentFrame();
        WebElement welcomeMessageField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.WELCOME_MESSAGE_FIELD));
        welcomeMessageField.sendKeys(welcomeMessage);
    }

    public void enterDataStorageForReseller() {
        String dataStorageValue = "90";
        iframe.switchToContentFrame();
        WebElement dataStorageField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.DATA_STORAGE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", dataStorageField);
        dataStorageField.sendKeys(dataStorageValue);
    }

    public void enterPasswordRecoveryEmailForReseller() {
        String passwordRecoveryEmail = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterPasswordRecoveryEmail = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.PASSWORD_RECOVERY_EMAIL_FIELD));
        enterPasswordRecoveryEmail.sendKeys(passwordRecoveryEmail);
    }

    public void addButtonInsideRuleOfReseller(String name) {
        iframe.switchToRuleContentFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
    }

    public void enterDescriptionOfRuleTab() {
        String city = "TestRule1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.DESCRIPTION_FIELD));
        enterDescriptionField.sendKeys(city);
    }

    public void selectStartUpScreen(String startUpScreen) {
        iframe.switchToPopUpDialogFrame();
        By startUpScreenDropdownLocator = ResellerPageLocators.STARTUP_SCREEN_DROPDOWN;
        By startUpScreenOptionLocator = By.xpath(String.format(ResellerPageLocators.STARTUP_SCREEN_DROPDOWN_OPTION, startUpScreen));
        commonMethods.selectFromDropdown(startUpScreenDropdownLocator, startUpScreenOptionLocator);
    }

    public void selectEventDateFormatScreen(String eventDateFormat) {
        iframe.switchToPopUpDialogFrame();
        By eventDateFormatDropdownLocator = ResellerPageLocators.EVENT_DATE_FORMAT_DROPDOWN;
        By eventDateFormatOptionLocator = By.xpath(String.format(ResellerPageLocators.EVENT_DATE_FORMAT_DROPDOWN_OPTION, eventDateFormat));
        commonMethods.selectFromDropdown(eventDateFormatDropdownLocator, eventDateFormatOptionLocator);
    }

    public void enterSpeedConsiderationMomentOfRuleTab() {
        String speedConsiderationMoment = "5";
        iframe.switchToPopUpDialogFrame();
        WebElement speedConsiderationMomentField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SPEED_CONSIDERATION_MOMENT_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", speedConsiderationMomentField);
        speedConsiderationMomentField.sendKeys(speedConsiderationMoment);
    }

    public void enterMaxSpeedValidatorOfRuleTab() {
        String maxSpeedValidator = "200";
        iframe.switchToPopUpDialogFrame();
        WebElement maxSpeedValidatorField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.MAX_SPEED_VALIDATOR_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", maxSpeedValidatorField);
        maxSpeedValidatorField.sendKeys(maxSpeedValidator);
    }

    public void selectTimeZone(String timezoneName) {
        iframe.switchToContentFrame();
        By timezoneDropdownLocator = ResellerPageLocators.TIMEZONE_DROPDOWN;
        By timezoneOptionLocator = By.xpath(String.format(ResellerPageLocators.TIMEZONE_DROPDOWN_OPTION, timezoneName));
        commonMethods.selectFromDropdown(timezoneDropdownLocator, timezoneOptionLocator);
    }

    public void selectAlertOnDataAccessTabOfReseller(String Alert) {
        iframe.switchToContentFrame();
        By selectAlertField = ResellerPageLocators.ALERT_FIELD;
        By selectAllAlert = By.xpath(String.format(ResellerPageLocators.ALL_ALERT_OPTION, Alert));
        commonMethods.selectFromDropdown(selectAlertField, selectAllAlert);
        WebElement screenElement = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT));
        screenElement.click();
    }

    public void selectTimeZoneRightsOnDataAccessTabOfReseller(String timeZone) {
        iframe.switchToContentFrame();
        By selectTimeZoneRightsField = ResellerPageLocators.TIMEZONE_RIGHTS_FIELD;
        By selectTimeZoneRightsOption = By.xpath(String.format(ResellerPageLocators.TIMEZON_RIGHTS_OPTION, timeZone));
        commonMethods.selectFromDropdown(selectTimeZoneRightsField, selectTimeZoneRightsOption);
        WebElement screenElement = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT));
        screenElement.click();
    }

    public void selectSimProviderOnDataAccessTabOfReseller(String providerName) {
        iframe.switchToContentFrame();
        By selectSimProviderField = ResellerPageLocators.SIM_PROVIDER_FIELD;
        By selectSimProviderOption = By.xpath(String.format(ResellerPageLocators.SIM_PROVIDER_OPTION, providerName));
        commonMethods.selectFromDropdown(selectSimProviderField, selectSimProviderOption);
        WebElement screenElement = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT));
        screenElement.click();
    }

    public void enterMapProjectIdOnMapOfReseller(String mapProjectId) {
        iframe.switchToCompanyMapContentFrame();
        WebElement enterMapProjectId = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.MAP_PROJECT_ID_FIELD));
        enterMapProjectId.sendKeys(mapProjectId);
    }

    public void addButtonInsideTemplateOfReseller(String name) {
        iframe.switchToTemplateFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRuleOfCompany = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRuleOfCompany.click();
    }

    public void selectNameOnTemplatesTabOfReseller(String Name) {
        iframe.switchToPopUpDialogFrame();
        By nameField = ResellerPageLocators.NAME_FIELD;
        By nameFieldOptions = By.xpath(String.format(ResellerPageLocators.NAME_FIELD_DROPDOWN, Name));
        commonMethods.selectFromDropdown(nameField, nameFieldOptions);
    }

    public void enterTemplateDescriptionForReseller() {
        iframe.switchToPopUpDialogFrame();
        String description = "Testing Template Description";
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.TEMPLATE_DESCRIPTION_FIELD));
        descriptionField.sendKeys(description);
    }

    public void enterTemplateSmsMessageForReseller() {
        iframe.switchToPopUpDialogFrame();
        String smsMessage = "%link%";
        WebElement smsMessageFiekd = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SMS_MESSAGE_FIELD));
        smsMessageFiekd.sendKeys(smsMessage);
    }

    public void enterTemplateSmsTemplateIdForReseller() {
        iframe.switchToPopUpDialogFrame();
        String smsTemplateId = "A0101A";
        WebElement smsTemplateIdField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SMS_TEMPLATE_ID_FIELD));
        smsTemplateIdField.sendKeys(smsTemplateId);
    }

    public void enterTemplateEmailSubjectForReseller() {
        iframe.switchToPopUpDialogFrame();
        String emailSubject = "Follow-Up on Template Description for Reseller";
        WebElement emailSubjectField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EMAIL_SUBJECT_FIELD));
        emailSubjectField.sendKeys(emailSubject);
    }

    public void enterTemplateEmailMessageForReseller() {
        iframe.switchToPopUpDialogFrame();
        String emailMessage = "Dear [Recipient's Name],\n" +
                "\n" +
                "I hope this email finds you well. %link%\n" +
                "\n" +
                "I wanted to follow up regarding the template description for the reseller section. As discussed, we need to update the description on the templates tab to ensure accurate and clear information for our resellers.\n" +
                "\n" +
                "Please let me know if you need any assistance or if there's anything specific you'd like included in the description. I’m happy to help with the update as soon as possible.\n" +
                "\n" +
                "Looking forward to your response.\n" +
                "\n" +
                "Best regards,\n" +
                "[Your Full Name]";
        WebElement emailMessageField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EMAIL_MESSAGE_FIELD));
        emailMessageField.sendKeys(emailMessage);
    }

    public void enterTemplateDefaultEmailMessageForReseller() {
        iframe.switchToPopUpDialogFrame();
        String defaultEmailMessage = "Dear [Recipient's Name],\n" +
                "\n" +
                "I hope this email finds you well.\n" +
                "\n" +
                "I wanted to follow up regarding the template description for the reseller section. As discussed, we need to update the description on the templates tab to ensure accurate and clear information for our resellers.\n" +
                "\n" +
                "Please let me know if you need any assistance or if there's anything specific you'd like included in the description. I’m happy to help with the update as soon as possible.\n" +
                "\n" +
                "Looking forward to your response.\n" +
                "\n" +
                "Best regards,\n" +
                "[Your Full Name]";
        WebElement defaultEmailMessageField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.DEFAULT_MESSAGE_FIELD));
        defaultEmailMessageField.sendKeys(defaultEmailMessage);
    }

    public void addButtonInsidePaymentGatewayOfReseller(String name) {
        iframe.switchToPaymentGatewayFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsidePaymentGatewayOfReseller = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsidePaymentGatewayOfReseller.click();
    }

    public void selectGatewayOnPaymentGatewayTabOfReseller(String gatewayName) {
        iframe.switchToPopUpDialogFrame();
        By gatewayField = ResellerPageLocators.GATEWAY_NAME_DROPDOWN;
        By datewayFieldOptions = By.xpath(String.format(ResellerPageLocators.GATEWAY_NAME_FIELD_DROPDOWN_OPTIONS, gatewayName));
        commonMethods.selectFromDropdown(gatewayField, datewayFieldOptions);
    }

    public void enterPayementGatewayNameForReseller() {
        iframe.switchToPopUpDialogFrame();
        String gatewayName = "PayPal REST API";
        WebElement gatewayNameField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.GATEWAY_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", gatewayNameField);
        gatewayNameField.sendKeys(gatewayName);
    }

    public void enterPayementGatewayClientIdForReseller() {
        iframe.switchToPopUpDialogFrame();
        String clientId = "AXL1rSx5OZkfj2vTfLdc5R9n5DQXhd7rZnQz0P-KYs0Oly7y2kmQy5q5z6fMc0wVg6tBzGoOxUpzEjK3l7kYnVVVVyL_FGMeQ";
        WebElement clientIdField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.CLIENT_ID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", clientIdField);
        clientIdField.sendKeys(clientId);
    }

    public void enterPayementGatewaySecretKeyForReseller() {
        iframe.switchToPopUpDialogFrame();
        String secretKey = "EECrNxWoTbQXYmFe1oZPjcivZP2gJDe-K4g8o8CPOzA4WlnF1pWTm-V9ehF32uEFh5IW5I3aT8W3gUwq4Rpa4tZOrF8V1a0Zow";
        WebElement secretKeyField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SECRET_KEY_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", secretKeyField);
        secretKeyField.sendKeys(secretKey);
    }

    public void addButtonInsideRenameLabelOfReseller(String name) {
        iframe.switchToRenameLabelFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRenameLabelOfReseller = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRenameLabelOfReseller.click();
    }

    public void enterRenameLabelActualNameForReseller() {
        iframe.switchToRenameLabelFrame();
        String actualName = "Testing Purpose";
        WebElement actualNameField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.ACTUAL_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", actualNameField);
        actualNameField.sendKeys(actualName);
    }

    public void enterRenameLabelNewNameForReseller() {
        iframe.switchToRenameLabelFrame();
        String newName = "Testing Purpose";
        WebElement newNameField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.NEW_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", newNameField);
        newNameField.sendKeys(newName);
    }

    public void selectIvrGatewayOnIvrTabOfReseller(String ivrGatewayName) {
        iframe.switchToContentFrame();
        By ivrGatewayField = ResellerPageLocators.IVR_GATEWAY_DROPDOWN;
        By ivrGatewayDropdownOptions = By.xpath(String.format(ResellerPageLocators.IVR_GATEWAY_DROPDOWN_OPTIONS, ivrGatewayName));
        commonMethods.selectFromDropdown(ivrGatewayField, ivrGatewayDropdownOptions);
        WebElement screenElementOfIvr = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT_IVR));
        screenElementOfIvr.click();
    }

    public void enterIvrAccountSidForReseller() {
        iframe.switchToContentFrame();
        String accountSId = "Testing Purpose";
        WebElement accountSIdField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.ACCOUNT_SID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", accountSIdField);
        accountSIdField.sendKeys(accountSId);
    }

    public void enterIvrAccountTokenForReseller() {
        iframe.switchToContentFrame();
        String accountToken = "EECrNxWoTbQXYmFe1oZPjcivZP2gJDe-K4g8o8CPOzA4WlnF1pWTm";
        WebElement accountTokenField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.ACCOUNT_TOKEN_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", accountTokenField);
        accountTokenField.sendKeys(accountToken);
    }

    public void enterIvrFromNumberForReseller() {
        iframe.switchToContentFrame();
        String fromNumber = "9181937981821";
        WebElement fromNumberField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.FROM_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", fromNumberField);
        fromNumberField.sendKeys(fromNumber);
    }

    public void addButtonInsideScoialMediaApiOfReseller(String name) {
        iframe.switchToSocialMediaApiFrame();
        String dynamicXpath = String.format(CompanyPageLocators.ADD_BUTTON_INSIDE_COMPANY, name);
        WebElement addButtonInsideRenameLabelOfReseller = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
        addButtonInsideRenameLabelOfReseller.click();
    }

    public void selectGatewayOnSocialMediaApiTabOfReseller(String gatewayName) {
        iframe.switchToPopUpDialogFrame();
        By gatewayDropdown = ResellerPageLocators.GATEWAY_DROPDOWN;
        By GatewayDropdownOptions = By.xpath(String.format(ResellerPageLocators.GATEWAY_DROPDOWN_OPTIONS, gatewayName));
        commonMethods.selectFromDropdown(gatewayDropdown, GatewayDropdownOptions);
        WebElement screenElementOfSocialMedia = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT_SOCIAL_MEDIA));
        screenElementOfSocialMedia.click();
    }

    public void selectTypeOnSocialMediaApiTabOfReseller(String typeName) {
        iframe.switchToPopUpDialogFrame();
        By gatewayTypeDropdown = ResellerPageLocators.GATEWAY_TYPE_DROPDOWN;
        By GatewayTypeDropdownOptions = By.xpath(String.format(ResellerPageLocators.GATEWAY_TYPE_DROPDOWN_OPTIONS, typeName));
        commonMethods.selectFromDropdown(gatewayTypeDropdown, GatewayTypeDropdownOptions);
        WebElement screenElementOfSocialMedia = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SCREEN_ELEMENT_SOCIAL_MEDIA));
        screenElementOfSocialMedia.click();
    }

    public void enterSocialMediaApiBotHttpTokenForReseller() {
        iframe.switchToPopUpDialogFrame();
        String botHttpToken = "EECrNxWoTbQXYmFe1oZPjcivZP2gJDe-EECrNxWoTbQXYmFe1oZPjcivZP2gJDe";
        WebElement botHttpTokenField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.BOT_HTTP_TOKEN_FIELD));
        botHttpTokenField.sendKeys(botHttpToken);
    }

    public void isConsiderAnnouncementCheck() {
        iframe.switchToPopUpDialogFrame();
        WebElement considerAnnouncementCheck = driver.findElement(By.xpath(ResellerPageLocators.CONSIDER_ANNOUNCEMENT_CHECKBOX));
        CommonMethods.checkCheckbox(considerAnnouncementCheck);
    }

    public void saveButtonOfResellerPage() throws InterruptedException {
        iframe.switchToBottomFrame();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SAVE_BUTTON));
        saveButton.click();
        Thread.sleep(1000);
    }

    public void clickOnCreatedReseller() throws InterruptedException {
        Thread.sleep(3000);
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.shortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void deleteButtonReseller() {
        iframe.switchToBottomFrame();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.DELETE_BUTTON));
        deleteButton.click();
        iframe.switchToDivFrame();
        WebElement deleteOkButton = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.DELETE_OK_BUTTON));
        deleteOkButton.click();
    }

    public void verifyThatUserDeleted() {
        iframe.switchToContentFrame();
        boolean isDeleted = commonMethods.isUserDeleted(this.shortName);
        Assert.assertTrue(isDeleted, "User with Short Name '" + this.shortName + "' was not deleted successfully.");
    }

    public void editResellerShortName() {
        this.editShortName = CommonMethods.generateRandomFirstName();
        iframe.switchToContentFrame();
        WebElement enterShortNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.SHORT_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterShortNameField);
        enterShortNameField.sendKeys(this.editShortName);
    }

    public void editResellerUserName() {
        this.editUserName = CommonMethods.generateRandomEmail();
        iframe.switchToContentFrame();
        WebElement enterUserNameField = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.USERNAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterUserNameField);
        enterUserNameField.sendKeys(this.editUserName);
    }

    public void editResellerMobileNumber() {
        this.editMobileNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.MOBILE_NUMBER_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterMobileNumber);
        enterMobileNumber.sendKeys(this.editMobileNumber);
    }

    public void editResellerTelephoneNumber() {
        String editTelephoneNumber = CommonMethods.generateRandomMobileNumber();
        iframe.switchToContentFrame();
        WebElement enterTelephoneNumber = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.TELEPHONE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterTelephoneNumber);
        enterTelephoneNumber.sendKeys(editTelephoneNumber);
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

    public void clickOnEditedReseller() {
        iframe.switchToContentFrame();
        String formattedXpath = String.format(CompanyPageLocators.TABLE_OF_SHORT_NAME, this.editShortName);
        commonMethods.doubleClick(By.xpath(formattedXpath));
    }

    public void clickOnEditRuleOfReseller() {
        iframe.switchToRuleContentFrame();
        WebElement editRule = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EDIT_RULE_BUTTON));
        editRule.click();
    }

    public void editRuleName() {
        String editRuleName = "EditRuleTesting1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterRuleName = wait.until(ExpectedConditions.elementToBeClickable(CompanyPageLocators.RULE_NAME_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterRuleName);
        enterRuleName.sendKeys(editRuleName);
    }

    public void editDescriptionOfRuleTab() {
        String editRuleDescription = "EditDescriptionTestRule1";
        iframe.switchToPopUpDialogFrame();
        WebElement enterDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.DESCRIPTION_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", enterDescriptionField);
        enterDescriptionField.sendKeys(editRuleDescription);
    }

    public void editDashboardOfTaskEyeProjectOfScreenAccessOfReseller() {
        iframe.switchToContentFrame();
        WebElement dashBoardModule = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.DASHBOARD_SUB_MODULE));
        dashBoardModule.click();
        WebElement viewPermissionOfDashboard = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.NO_ACCESS_PERMISSION_OF_DASHBOARD));
        viewPermissionOfDashboard.click();
    }

    public void editMapProjectIdOnMapOfReseller(String editMapProjectId) {
        iframe.switchToCompanyMapContentFrame();
        WebElement editedMapProjectId = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.MAP_PROJECT_ID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", editedMapProjectId);
        editedMapProjectId.sendKeys(editMapProjectId);
    }

    public void clickOnEditTemplatePropertiesOfReseller() {
        iframe.switchToTemplateFrame();
        WebElement templateProperties = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EDIT_RULE_BUTTON));
        templateProperties.click();
    }

    public void clickOnEditPaymentGatewayOfReseller() {
        iframe.switchToPaymentGatewayFrame();
        WebElement templateProperties = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EDIT_PAYMENT_GATEWAY_BUTTON));
        templateProperties.click();
    }


    public void editTemplateDescriptionForReseller() {
        iframe.switchToPopUpDialogFrame();
        String editDescription = "Edit Testing Template Description";
        WebElement editDescriptionField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.TEMPLATE_DESCRIPTION_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", editDescriptionField);
        editDescriptionField.sendKeys(editDescription);
    }

    public void editTemplateSmsMessageForReseller() {
        iframe.switchToPopUpDialogFrame();
        String smsMessage = "%link%";
        WebElement smsMessageFiekd = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SMS_MESSAGE_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", smsMessageFiekd);
        smsMessageFiekd.sendKeys(smsMessage);
    }

    public void editTemplateSmsTemplateIdForReseller() {
        iframe.switchToPopUpDialogFrame();
        String smsTemplateId = "B0101B";
        WebElement smsTemplateIdField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.SMS_TEMPLATE_ID_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", smsTemplateIdField);
        smsTemplateIdField.sendKeys(smsTemplateId);
    }

    public void editTemplateEmailSubjectForReseller() {
        iframe.switchToPopUpDialogFrame();
        String emailSubject = "Editing Follow-Up on Template Description for Reseller";
        WebElement emailSubjectField = wait.until(ExpectedConditions.elementToBeClickable(ResellerPageLocators.EMAIL_SUBJECT_FIELD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", emailSubjectField);
        emailSubjectField.sendKeys(emailSubject);
    }

    public void searchTheResellerUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.shortName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void searchTheEditedResellerUser() {
        iframe.switchToBottomFrame();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(AdminPageLocators.SEARCH_FIELD));
        searchField.sendKeys(this.editUserName);
        searchField.sendKeys(Keys.ENTER);
    }
}
