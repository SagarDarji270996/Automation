package projects.trakzee.web.locators.settings.general;

import org.openqa.selenium.By;

public class PL_Admin {
	public static final By listTab = By.cssSelector("ul[id='tabs'] li");
	public static final String alreadySelectedTab = "//ul[@id='tabs']//li[@data_id and @class='active']";

	public static void main(String[] args) {
		System.out.println(getTabXpath("My Account"));
	}

	public static String getTabXpath(String tabname) {
		String tab = tabname.replaceAll(" ", "");
		String xpath = String.format("//ul[@id='tabs']//li[@data_id='%s']", tab);
		return xpath;
	}

	public class MyAccountTab {
		// Country
		public static final By btnCountry = By.xpath("//div[starts-with(@id,'country')]");
		public static final By textAlreadySelectedDropdownCountry = By.xpath(
				"//div[starts-with(@id,'country')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");
		public static final By fieldSearchboxCountry = By
				.xpath("//div[starts-with(@id,'country')]//div[@class='chosen-search']//input");
		public static final By listCountry = By
				.xpath("//div[starts-with(@id,'country')]//ul//li[contains(@class,'active-result')]");


		// State
		public static final By btnState = By.xpath("//div[starts-with(@id,'state')]");
		public static final By textAlreadySelectedDropdownState = By.xpath(
				"//div[starts-with(@id,'state')]//a[@class='chosen-single']//input[@type='text']/following-sibling::span");
		public static final By fieldSearchboxState = By
				.xpath("//div[starts-with(@id,'state')]//div[@class='chosen-search']//input");
		public static final By listState = By
				.xpath("//div[starts-with(@id,'state')]//ul//li[contains(@class,'active-result')]");

		public static final By fieldShortName = By.xpath("//input[@id='-short_name']");
		public static final By fieldUsername = By.xpath("//input[@id='-username']");
		public static final By fieldConfirmUsername = By.xpath("//input[@id='-confirm_username']");
		public static final By checkboxEmailLoginDetails = By.xpath("//input[@id='email_login_details_1']");

		public static final By fieldPassword = By.xpath("//input[@id='-password_value']");
		public static final By fieldRetypePassword = By.xpath("//input[@id='-retype_password']");
		public static final By fieldPasswordRecoveryEmail = By.xpath("//input[@id='-password_recovery_email']");
		public static final By checkboxEnableSecurityPin = By.xpath("//input[@id='enable_security_pin_1']");
		public static final By fieldSecurityPin = By.xpath("//input[@id='-security_pin']");
		public static final By fieldRetypeSecurityPin = By.xpath("//input[@id='-retype_security_pin']");


		public static final By fieldCity = By.xpath("//input[@id='-city']");
		public static final By fieldZipCode = By.xpath("//input[@id='-zipcode']");
		public static final By fieldStreet1 = By.xpath("//input[@id='-street1']");

		public static final By fieldStreet2 = By.xpath("//input[@id='-street2']");
		public static final By fieldInternetAddress = By.xpath("//input[@id='-internet_address']");
		public static final By fieldContactPerson = By.xpath("//input[@id='-contact_person']");

		public static final By fieldHelpDeskTelephoneNumber = By.xpath("//input[@id='-tel_no']");
		public static final By fieldHelpDeskEmail = By.xpath("//input[@id='-help_desk_email']");
		public static final By fieldMobileNumber = By.xpath("//input[@id='-mob_no']");

		public static final By fieldWhatsAppContactNumber = By.xpath("//input[@id='-whatsapp_contact_number']");
		public static final By fieldFaxNumber = By.xpath("//input[@id='-fax_no']");
		public static final By checkboxDisableAllObject = By.xpath("//input[@id='is_disable_1']");
		public static final By fieldReason = By.xpath("//input[@id='-disable_reason']");
		public static final By checkboxAutoSuggestIMEI = By.xpath("//input[@id='auto_suggest_imei_1']");
		public static final By checkboxInventory = By.xpath("//input[@id='inventory_1']");
		public static final By checkboxSubscription = By.xpath("//input[@id='subscription_1']");

		public static final By checkboxKYC = By.xpath("//input[@id='kyc_1']");

		public static final By fieldWelcomMessage = By.xpath("//input[@id='-welcome_msg']");

		public static final By radioButtonTariff = By.xpath("//input[@id='license_type_1']");
		public static final By radioButtonNonTariff = By.xpath("//input[@id='license_type_2']");

		public static final By fieldDataStorage = By.xpath("//input[@id='-data_storage']");
		public static final By fieldAdminCode = By.xpath("//input[@id='-admin_code']");

		public static final By fieldGSTIN = By.xpath("//input[@id='-gstin_no']");
		public static final By fieldPANNo = By.xpath("//input[@id='-pan_no']");

	}

}
