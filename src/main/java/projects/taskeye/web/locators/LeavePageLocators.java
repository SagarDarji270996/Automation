package projects.taskeye.web.locators;

import org.openqa.selenium.By;

public class LeavePageLocators {
    public static final String TEXT_HEADER_NAME = "//label[text()='headerName']";
    public static final String SANCTION_RADIO_BUTTON = "(//label[contains(text(),'Sanction')]/preceding-sibling::input[@type='radio'])[2]";
    public static final String REJECT_RADIO_BUTTON = "//label[contains(text(),'Reject')]/preceding-sibling::input[@type='radio']";
    public static By ADD_BUTTON = By.xpath("//a[@title='New']");
    public static By SAVE_BUTTON = By.xpath("//a[@title='Save']");
    public static By FILTER_BUTTON = By.xpath("//a[@title='Filter']");
    public static By REASON_FIELD = By.xpath("//textarea[@id='-reason']");
}
