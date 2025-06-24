package projects.trakzee.web.locators.loginAndHome;

import org.openqa.selenium.By;

public class PL_HomePage {
	public static final String MAIN_MODULE_BUTTON_XPATH = "//div[@id='tree-module']//button[@title='%s']";
	public static final String SUB_MODULE_BUTTON_XPATH = "//ul[@class='subMenuUl']//span[@title='%s'][normalize-space()='%s']";
	public static final String SCREEN_BUTTON_XPATH = "//ul[@class='subMenuUl']//li//div[@id='deepMenu']//ul//li[@title='%s']//a[@href='#']//span[@class='nav-text ng-binding'][normalize-space()='%s']";
	public static By USER_BUTTON = By.xpath("//span[@id='user_info_btn']");
	public static final By APPLICATIONS_MENU = By.cssSelector("li.menu-li.projects > a#aprojects");
	public static final By SIDE_DRAWER = By.cssSelector("ul.project-list.scrollable-content");

	public static final By btnNavigationDownlaods = By
			.xpath("//div[@id='tree-download']//span[@title='Cloud Download']");

	public static By getProjectByName(String projectName) {
		By address = By
				.xpath("//ul[@class='project-list scrollable-content']//span[contains(text(), '" + projectName + "')]");
		System.out.println(address);
		return address;
	}

	public static final By textAlreadySelectedProject = By
			.xpath("//li[@class='menu-li projects']//li//a[contains(@class,'not-active active')]//span");

	public static final By listMainMenus = By
			.xpath("//div[@id='tree-module']//span[not(ancestor::div[@id='subMenu'])]/following-sibling::span");

	// Top Level Sub Menus List [Level-1]
	public static final By ListSubMenus = By.xpath("//div[@id='subMenu']//li[not(ancestor::div[@id='deepMenu'])]");

	// Inner Level Deep Menus List [Level-2]
	public static final By ListDeepMenus = By.xpath("//div[@id='deepMenu']//li");

	public static final By btnLogo = By.xpath("(//img[@id='logo-img'])[1]");
	
	public static final By btnCrossIconServerChargeInteruption = By
			.xpath("//div[@role='dialog' and not(contains(@style, 'display: none'))]//button[@title='Close']");
	public static final By textAvoidServerChargeInteruptionPage = By
			.xpath("//div[@role='dialog' and not(contains(@style, 'display: none'))]//span[@class='ui-dialog-title']");

}