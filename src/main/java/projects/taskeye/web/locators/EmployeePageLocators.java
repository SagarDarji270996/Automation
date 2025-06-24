package projects.taskeye.web.locators;

import org.openqa.selenium.By;

public class EmployeePageLocators {

    public static final By COMPANY_DROPDOWN = By.xpath("//div[@id='company_id_chosen']");
    public static final String COMPANY_DROPDOWN_OPTION = "//div[@id='company_id_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By DEPARTMENT_DROPDOWN = By.xpath("//div[@id='task_department_id_chosen']");
    public static final String DEPARTMENT_DROPDOWN_OPTION = "//div[@id='task_department_id_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By EMPLOYEE_TYPE_DROPDOWN = By.xpath("//div[@id='employee_type_chosen']");
    public static final String EMPLOYEE_TYPE_DROPDOWN_OPTION = "//div[@id='employee_type_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By LANGUAGE_DROPDOWN = By.xpath("//div[@id='languagecode_chosen']");
    public static final String LANGUAGE_DROPDOWN_OPTION = "//div[@id='languagecode_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final String FEMALE_RADIO_BUTTON = "//input[@id='gender_2']";
    public static final String MALE_RADIO_BUTTON = "//input[@id='gender_1']";
    public static final String TASK_FORM_DEVICE_RADIO_BUTTON = "//input[@id='track_from_2']";
    public static final String TASK_FORM_MOBILE_APP_RADIO_BUTTON = "//input[@id='track_from_1']";
    public static final String VERIFICATION_VIA_PASSWORD_RADIO_BUTTON = "//input[@id='verification_via_2']";
    public static final String VERIFICATION_VIA_OTP_RADIO_BUTTON = "//input[@id='verification_via_1']";
    public static final String OTP_VIA_EMAIL_RADIO_BUTTON = "//input[@id='otp_via_2']";
    public static final String OTP_VIA_SMS_RADIO_BUTTON = "//input[@id='otp_via_1']";
    public static final By TIMEZONE_DROPDOWN = By.xpath("//div[@id='input_timezone_chosen']");
    public static final String TIMEZONE_DROPDOWN_OPTION = "//div[@id='input_timezone_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By SPEED_DETECTION_DROPDOWN = By.xpath("//div[@id='accuracy_speed_chosen']");
    public static final String SPEED_DETECTION_DROPDOWN_OPTION = "//div[@id='accuracy_speed_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By SHIFT_DROPDOWN = By.xpath("//div[@id='search_device_chosen']");
    public static final String SHIFT_DROPDOWN_OPTION = "//div[@id='search_device_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final String AUTO_TASK_RADIO_BUTTON = "//input[@id='task_start_1']";
    public static final String MANUAL_TASK_RADIO_BUTTON = "//input[@id='task_start_2']";
    public static final By COUNTRY_DROPDOWN = By.xpath("//div[@id='country_chosen']");
    public static final String COUNTRY_DROPDOWN_OPTION = "//div[@id='country_chosen']//div[@class='chosen-drop']//ul//li[@title='%s']";
    public static final By LEAVE_TYPE_DROPDOWN = By.xpath("//select[@name='leave_type0']");
    public static final String LEAVE_TYPE_DROPDOWN_OPTION = "//td[@id='leave_type00']//select[@name='leave_type0']//*[contains(text(),'%s')]";
    public static final String AUTO_RENEWAL_CHECKBOX = "//input[@id='auto_renewal0_1']";
    public static final By RENEWAL_FREQUENCY_DROPDOWN = By.xpath("//select[@name='renewal_frequency0']");
    public static final String RENEWAL_FREQUENCY_DROPDOWN_OPTION = "//td[@id='renewal_frequency00']//select[@name='renewal_frequency0']//*[contains(text(),'%s')]";
    public static final String CARRY_OVER_CHECKBOX = "//input[@id='carry_over0_1']";
    public static final String TABLE_OF_FIRST_NAME = "//td[@name='first_name' and @title='%s']";
    public static final String DOB_INPUT = "//input[@id='-date_of_birth']";
    public static By DEPARTMENT_NAME_FIELD = By.xpath("//input[@id='-department_name']");
    public static By FIRST_NAME_FIELD = By.xpath("//input[@id='-first_name']");
    public static By MIDDLE_NAME_FIELD = By.xpath("//input[@id='-middle_name']");
    public static By LAST_NAME_FIELD = By.xpath("//input[@id='-last_name']");
    public static By EMPLOYEE_NUMBER_FIELD = By.xpath("//input[@id='-employee_no']");
    public static By PASSWORD_FIELD = By.xpath("//input[@id='-password_val']");
    public static By RETYPE_PASSWORD_FIELD = By.xpath("//input[@id='-retype_password']");
    public static By EMAIL_ID_FIELD = By.xpath("//input[@id='-email']");
    public static By MOBILE_NUMBER_FIELD = By.xpath("//input[@id='-mobile_no']");
    public static By EMERGENCY_CONTACT_NUMBER_FIELD = By.xpath("//input[@id='-emergency_contact']");
    public static By STREET1_FIELD = By.xpath("//input[@id='-street1']");
    public static By STREET2_FIELD = By.xpath("//input[@id='-street2']");
    public static By CITY_FIELD = By.xpath("//input[@id='-city']");
    public static By ZIPCODE_FIELD = By.xpath("//input[@id='-zip_code']");
    public static By NO_OF_DAYS_FIELD = By.xpath("//input[@id='-no_of_days']");
}
