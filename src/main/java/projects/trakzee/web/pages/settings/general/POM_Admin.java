package projects.trakzee.web.pages.settings.general;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.settings.general.PL_Admin;
import projects.trakzee.web.projectUtility.CheckboxHandler;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.DropdownHandler;
import projects.trakzee.web.projectUtility.MethodGeneratorFromTODOList;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.StringHandling;

public class POM_Admin implements InitializePages {
	private static WebDriver driver = TestBase.getWebDriver();
	WebDriverWait wait = new WebDriverWait(driver, 5);
	private ProjectUtility pu = new ProjectUtility();

	MyAccountTab myAcountTab = new MyAccountTab();

	public POM_Admin(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}

	public static void main(String[] args) {
		String[] testSteps = { "click on plus new icon or insert button at footer", "click on reset button at footer",
				"click on download xls icon button at footer", "select the tabs", "select country dropdown",
				"select state dropdown", "set short name field", "set user name field", "confirm user name field",
				"check email login details checkbox", "set password field", "set retype password field",
				"check enable security pin checkbox", "set password recovery email field", "set city field",
				"set zip code field", "set street 1 field", "set street 2 field", "set internet address field",
				"set contact person field", "set helpdesk telephone number field", "set help deskemail field",
				"set mobile number field",
				"set whatapp contact number by selecting the country code dropdown and field", "set fax number field",
				"check disable all object checkbox", "check inventory checkbox", "check auto suggest imei checkbox",
				"check kyc checkbox", "set welcome message field", "select license type radio button",
				"set data storage field", "set admin code field" };

		MethodGeneratorFromTODOList.generateMethodsFromSteps(testSteps, "STEP");
	}

	@Description("This is used to fill the data in the all the admin tabs based on the different tabs")
	@Feature("Hanlde end to end filter functionality for all the pages throught out the project")
	public boolean adminFunctionalityForAllTabsAndAllPagesBasedOnField(Map<String, Object> args)
			throws InterruptedException {
		args = StringHandling.convertKeysToCamelCase(args);
		Thread.sleep(1000);
		getFooterPage().clickOnPlusNewIconOrInsertButtonAtFooter();
		getCommonPage().checkPageTitlePresence(System.getProperty("pageName"), 30);

		Set<String> filedNames = args.keySet();
		boolean isClickedOnActionButton = false;
		boolean isErrorMessagePresent = false;
		System.out.println("List of filed present as per given data: " + filedNames);
		for (String field : filedNames) {
			if (!"na".equalsIgnoreCase((String) args.get(field))) {
				switch (field) {
				case "country": {
					myAcountTab.selectCountryDropdown(args.get("country").toString());
					break;
				}
				case "state": {
					myAcountTab.selectStateDropdown(args.get("state").toString());
					break;
				}
				case "shortName": {
					myAcountTab.setShortNameField(args.get("shortName").toString());
					break;
				}
				case "userName": {
					myAcountTab.setUserNameField(args.get("userName").toString());
					break;
				}
				case "confirmUsername": {
					myAcountTab.setConfirmUserNameField(args.get("confirmUsername").toString());
					break;
				}
				case "emailLoginDetails": {
					myAcountTab.checkEmailLoginDetailsCheckbox(
							Boolean.parseBoolean(args.get("emailLoginDetails").toString()));
					break;
				}
				case "password": {
					myAcountTab.setPasswordField(args.get("password").toString());
					break;
				}
				case "retypePassword": {
					myAcountTab.setRetypePasswordField(args.get("retypePassword").toString());
					break;
				}
				case "enableSecurityPin": {
					if (Boolean.parseBoolean(args.get("enableSecurityPin").toString())) {
						boolean isCheckedSecurityPin = false;
						isCheckedSecurityPin = myAcountTab.checkEnableSecurityPinCheckbox(
								Boolean.parseBoolean(args.get("enableSecurityPin").toString()));
						if (isCheckedSecurityPin) {
							myAcountTab.setSecurityPinField(args.get("securityPin").toString());
							myAcountTab.setRetypeSecurityPinField(args.get("retypeSecurityPin").toString());
						}
					}

					break;
				}
				case "passwordRecoveryEmail": {
					myAcountTab.setPasswordRecoveryEmailField(args.get("passwordRecoveryEmail").toString());
					break;
				}
				case "city": {
					myAcountTab.setCityField(args.get("city").toString());
					break;
				}
				case "zipCode": {
					myAcountTab.setZipCodeField(args.get("zipCode").toString());
					break;
				}
				case "street1": {
					myAcountTab.setStreet1Field(args.get("street1").toString());
					break;
				}
				case "street2": {
					myAcountTab.setStreet2Field(args.get("street2").toString());
					break;
				}

				case "internetAddress": {
					myAcountTab.setInternetAddressField(args.get("internetAddress").toString());
					break;
				}

				case "contactPerson": {
					myAcountTab.setContactPersonField(args.get("contactPerson").toString());
					break;
				}
				case "helpDeskTelephoneNumber": {
					myAcountTab.setHelpdeskTelephoneNumberField(args.get("helpDeskTelephoneNumber").toString());
					break;
				}
				case "helpDeskEmail": {
					myAcountTab.setHelpDeskemailField(args.get("helpDeskEmail").toString());
					break;
				}
				case "mobileNumber": {
					myAcountTab.setMobileNumberField(args.get("mobileNumber").toString());
					break;
				}

				case "whatsappContactNumber": {
					myAcountTab.setWhatappContactNumberBySelectingTheCountryCodeDropdownAndField(
							args.get("whatsappContactNumber").toString());
					break;
				}

				case "faxNumber": {
					myAcountTab.setFaxNumberField(args.get("faxNumber").toString());
					break;
				}
				case "disableAllObject": {
					if (Boolean.parseBoolean(args.get("disableAllObject").toString())) {
						boolean isCheckedDisableAllObject = false;
						isCheckedDisableAllObject = myAcountTab.checkDisableAllObjectCheckbox(
								Boolean.parseBoolean(args.get("disableAllObject").toString()));
						if (isCheckedDisableAllObject) {
							myAcountTab.setReasonField(args.get("reason").toString());
						}
					}

					break;
				}
				case "inventory": {
					if (Boolean.parseBoolean(args.get("inventory").toString())) {
						boolean isCheckedDisableAllObject = false;
						isCheckedDisableAllObject = myAcountTab
								.checkInventoryCheckbox(Boolean.parseBoolean(args.get("inventory").toString()));
						if (isCheckedDisableAllObject) {

							myAcountTab
									.checkSubscriptionCheckbox(
											Boolean.parseBoolean(args.get("subscription").toString()));
						}
					}

					break;
				}

				case "autoSuggestImei": {
					myAcountTab
							.checkAutoSuggestImeiCheckbox(Boolean.parseBoolean(args.get("autoSuggestImei").toString()));
				}
				case "kyc": {
					myAcountTab.checkKycCheckbox(Boolean.parseBoolean(args.get("kyc").toString()));
					break;
				}
				case "welcomeMessage": {
					myAcountTab.setWelcomeMessageField(args.get("welcomeMessage").toString());
					break;
				}
				case "gstIn": {
					if (args.get("country").toString().trim().equalsIgnoreCase("India")) {
						myAcountTab.setGSTINField(args.get("gstIn").toString());
					}
					break;
				}
				case "panNo": {
					if (args.get("country").toString().trim().equalsIgnoreCase("India")) {
						myAcountTab.setPANNoField(args.get("panNo").toString());
					}
					break;
				}
				case "licenseType": {
					myAcountTab.selectLicenseTypeRadioButton(args.get("licenseType").toString());
					break;
				}
				case "dataStorage": {
					myAcountTab.setDataStorageField(args.get("dataStorage").toString());
					break;
				}
				case "adminCode": {
					myAcountTab.setAdminCodeField(args.get("adminCode").toString());
					break;
				}
				case "actionButton": {
					if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("save")) {
						isClickedOnActionButton = getFooterPage().clickOnSaveIconButtonAtFooter();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("reset")) {
						isClickedOnActionButton = getFooterPage()
								.clickOnResetButtonAtFooter();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("delete")) {
						isClickedOnActionButton = getFooterPage().clickDeleteIconButtonAtFooter();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("back")) {
						isClickedOnActionButton = getFooterPage().clickBackIconButtonAtFooter();
					}
				}
				}
			}
		}

		if (isClickedOnActionButton) {
			if (!(args.get("errorMessage") == null
					|| ((String) args.get("errorMessage")).trim().matches("(?i)^\\s*(|NA|NULL)\\s*$"))) {
				// The (?i) makes it case-insensitive.
				// The regex ^\\s*(|NA|NULL)\\s*$ ensures only "NA", "NULL", empty, or
				// whitespace strings are matched.
				getCommonPage().getJAlertMessageAndVerify((String) args.get("errorMessage"));
				getCommonPage().clickOnJAlertCloseButton();
				isErrorMessagePresent = true;
				TestBase.getSoftAssert().assertAll();
			} else {
				boolean jalertMessage = false;
				try {
					getIFramePage().switchToDivFrame();
					WebDriverWait wait = new WebDriverWait(driver, 1);
					jalertMessage = driver.findElement(PL_Commons.textJAlertMessage).isDisplayed();
					isErrorMessagePresent = true;
				} catch (Exception e) {
				}
				if (jalertMessage) {
					getCommonPage().getJAlertMessageAndVerify((String) args.get(""));
					getCommonPage().clickOnJAlertCloseButton();
					TestBase.getSoftAssert().fail("Gets message on click apply button: " + jalertMessage);
					TestBase.getSoftAssert().assertAll();
				}

			}
		} else {
			System.out.println("Not clicked on the apply button");
		}
		System.out.println("End filter selection");

		return isClickedOnActionButton && isErrorMessagePresent == false;

	}

	@Step()
	public void selectTheTabs(String tabName) {
		// DONE STEP: select the tabs
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, PL_Admin.getTabXpath(tabName));
	}

	@Step()
	public void checkListOfTabPresent(String tabListCommaSeperated) throws InterruptedException {
		getIFramePage().switchToContentFrame();
		CommonMethods.compareListByText(driver, PL_Admin.listTab,
				tabListCommaSeperated);
	}

	protected class MyAccountTab {
		@Step()
		public void selectCountryDropdown(String country) throws InterruptedException {
			// DONE STEP: select country dropdown
			getIFramePage().switchToContentFrame();
			DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "Country",
					PL_Admin.MyAccountTab.btnCountry, PL_Admin.MyAccountTab.fieldSearchboxCountry,
					PL_Admin.MyAccountTab.listCountry,
					country, PL_Admin.MyAccountTab.textAlreadySelectedDropdownCountry);
		}

		@Step()
		public void selectStateDropdown(String state) throws InterruptedException {
			// DONE STEP: select state dropdown
			getIFramePage().switchToContentFrame();
			DropdownHandler.veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(driver, "State",
					PL_Admin.MyAccountTab.btnState, PL_Admin.MyAccountTab.fieldSearchboxState,
					PL_Admin.MyAccountTab.listState, state,
					PL_Admin.MyAccountTab.textAlreadySelectedDropdownState);

		}

		@Step()
		public void setShortNameField(String shortName) {
			// DONE STEP: set short name field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldShortName, shortName);
		}

		@Step()
		public void setUserNameField(String username) {
			// DONE STEP: set user name field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldUsername, username);

		}

		@Step()
		public void setConfirmUserNameField(String confirmUsername) {
			// DONE STEP: confirm user name field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldConfirmUsername, confirmUsername);

		}

		@Step()
		public void checkEmailLoginDetailsCheckbox(Boolean wantToCheck) {
			// DONE STEP: check email login details checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				CheckboxHandler.checkCheckboxIfNotSelected(driver, "Email login details",
						PL_Admin.MyAccountTab.checkboxEmailLoginDetails);
			}
		}

		@Step()
		public void setPasswordField(String password) {
			// DONE STEP: set password field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldPassword, password);
		}

		@Step()
		public void setRetypePasswordField(String retypePassword) {
			// DONE STEP: set retype password field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldRetypePassword, retypePassword);
		}

		@Step()
		public boolean checkEnableSecurityPinCheckbox(Boolean wantToCheck) {
			// DONE STEP: check enable security pin checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				return CheckboxHandler.checkCheckboxIfNotSelected(driver, "Enable security pin",
						PL_Admin.MyAccountTab.checkboxEnableSecurityPin);
			}
			return false;

		}

		@Step()
		public void setSecurityPinField(String securityPin) {
			// DONE STEP: set security pin
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldSecurityPin, securityPin);
		}

		@Step()
		public void setRetypeSecurityPinField(String retypeSecurityPin) {
			// DONE STEP: retype security pin
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldRetypeSecurityPin, retypeSecurityPin);

		}

		@Step()
		public void setPasswordRecoveryEmailField(String recoveryEmail) {
			// DONE STEP: set password recovery email field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldPasswordRecoveryEmail, recoveryEmail);
		}

		@Step()
		public void setCityField(String city) {
			// DONE STEP: set city field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldCity, city);
		}

		@Step()
		public void setZipCodeField(String zipcode) {
			// DONE STEP: set zip code field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldZipCode, zipcode);

		}

		@Step()
		public void setStreet1Field(String street1) {
			// DONE STEP: set street 1 field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldStreet1, street1);

		}

		@Step()
		public void setStreet2Field(String street2) {
			// DONE STEP: set street 2 field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldStreet2, street2);

		}

		@Step()
		public void setInternetAddressField(String internetAddress) {
			// DONE STEP: set internet address field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldInternetAddress, internetAddress);

		}

		@Step()
		public void setContactPersonField(String contactPerson) {
			// DONE STEP: set contact person field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldContactPerson, contactPerson);

		}

		@Step()
		public void setHelpdeskTelephoneNumberField(String telephoneNumber) {
			// DONE STEP: set helpdesk telephone number field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldHelpDeskTelephoneNumber, telephoneNumber);

		}

		@Step()
		public void setHelpDeskemailField(String helpdeskMail) {
			// DONE STEP: set help deskemail field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldHelpDeskEmail, helpdeskMail);

		}

		@Step()
		public void setMobileNumberField(String mobileNumber) {
			// DONE STEP: set mobile number field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldMobileNumber, mobileNumber);

		}

		@Step()
		public void setWhatappContactNumberBySelectingTheCountryCodeDropdownAndField(String whatsappNumber) {
			// DONE STEP: set whatapp contact number by selecting the country code dropdown
			// and field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldWhatsAppContactNumber, whatsappNumber);

		}

		@Step()
		public void setFaxNumberField(String faxNumber) {
			// DONE STEP: set fax number field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldFaxNumber, faxNumber);

		}

		@Step()
		public boolean checkDisableAllObjectCheckbox(Boolean wantToCheck) {
			// DONE STEP: check disable all object checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				return CheckboxHandler.checkCheckboxIfNotSelected(driver, "Disable all object",
						PL_Admin.MyAccountTab.checkboxDisableAllObject);
			}
			return false;

		}

		@Step()
		public void setReasonField(String reason) {
			// DONE STEP: set fax number field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldReason, reason);

		}


		@Step()
		public boolean checkInventoryCheckbox(Boolean wantToCheck) {
			// DONE STEP: check inventory checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				return CheckboxHandler.checkCheckboxIfNotSelected(driver, "Inventory",
						PL_Admin.MyAccountTab.checkboxInventory);
			}
			return false;
		}

		@Step()
		public void checkSubscriptionCheckbox(Boolean wantToCheck) {
			// DONE STEP: check subscription checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				CheckboxHandler.checkCheckboxIfNotSelected(driver, "Subscription",
						PL_Admin.MyAccountTab.checkboxSubscription);
			}
		}

		@Step()
		public void checkAutoSuggestImeiCheckbox(Boolean wantToCheck) {
			// DONE STEP: check auto suggest imei checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				CheckboxHandler.checkCheckboxIfNotSelected(driver, "Auto suggest imei",
						PL_Admin.MyAccountTab.checkboxAutoSuggestIMEI);
			}
		}

		@Step()
		public void checkKycCheckbox(Boolean wantToCheck) {
			// DONE STEP: check kyc checkbox
			if (wantToCheck) {
				getIFramePage().switchToContentFrame();
				CheckboxHandler.checkCheckboxIfNotSelected(driver, "KYC",
						PL_Admin.MyAccountTab.checkboxKYC);
			}
		}

		@Step()
		public void setWelcomeMessageField(String welcomeMessage) {
			// DONE STEP: set welcome message field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldWelcomMessage, welcomeMessage);

		}

		@Step()
		public void setGSTINField(String gstIn) {
			// DONE STEP: set GST In field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldGSTIN, gstIn);
		}

		@Step()
		public void setPANNoField(String panNo) {
			// DONE STEP: set PAN No field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldPANNo, panNo);
		}

		@Step()
		public void selectLicenseTypeRadioButton(String licenseType) {
			// DONE STEP: select license type radio button
			getIFramePage().switchToContentFrame();
			if (licenseType.trim().equalsIgnoreCase("Tariff")) {
				pu.clickOnButton(driver, PL_Admin.MyAccountTab.radioButtonTariff);
			} else {
				pu.clickOnButton(driver, PL_Admin.MyAccountTab.radioButtonNonTariff);
			}
		}

		@Step()
		public void setDataStorageField(String dataStorage) {
			// DONE STEP: set data storage field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldDataStorage, dataStorage);

		}

		@Step()
		public void setAdminCodeField(String adminCode) {
			// DONE STEP: set admin code field
			getIFramePage().switchToContentFrame();
			pu.setDataInFieldWithClear(driver, PL_Admin.MyAccountTab.fieldAdminCode, adminCode);

		}
	}

}
