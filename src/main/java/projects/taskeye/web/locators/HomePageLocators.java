package projects.taskeye.web.locators;

import org.openqa.selenium.By;

public class HomePageLocators {
    public static final String MAIN_MODULE_BUTTON_XPATH = "//div[@id='tree-module']//button[@title='%s']";
    public static final String SUB_MODULE_BUTTON_XPATH = "//ul[@class='subMenuUl']//span[@title='%s'][normalize-space()='%s']";
    public static final String SCREEN_BUTTON_XPATH = "//ul[@class='subMenuUl']//li//div[@id='deepMenu']//ul//li[@title='%s']//a[@href='#']//span[@class='nav-text ng-binding'][normalize-space()='%s']";
    public static final By APPLICATIONS_MENU = By.cssSelector("li.menu-li.projects > a#aprojects");
    public static final By SIDE_DRAWER = By.cssSelector("ul.project-list.scrollable-content");
    public static By USER_BUTTON = By.xpath("//span[@id='user_info_btn']");

    public static By getProjectByName(String projectName) {
        return By.xpath("//ul[@class='project-list scrollable-content']//span[contains(normalize-space(text()), '" + projectName + "')]");
    }
}