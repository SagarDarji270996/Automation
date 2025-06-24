package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_FavoriteButton {

	public static final By textFavouriteMiniWindowTitle = By
			.xpath("//div[@id='favourite_report_setting']//div[@class='favourite_report_header']//span");
	public static final By textFavouriteTitleRHS = By
			.xpath("//div[@id='favourite_report']//div[@class='favourite_report_header']//span");
	public static final By btnAddFavouriteReports = By.xpath("//a[@id='show_Favourite_report']");
	public static final By listFavouriteFolder = By.xpath("//select[@id='favourite_folder_id']");
	public static final By btnSave = By.xpath("(//a[normalize-space()='Save'])[1]");
	public static final By btnCancel = By.xpath("(//a[normalize-space()='Remove'])[1]");
	public static final By crossIconOnMiniWindow = By.xpath("//img[@src='../images/new/favourite_cancel_icon.svg']");
	public static final By fieldFavoriteFolderName = By.xpath("//input[@id='favourite_name']");

	public static final By listOfFieldOrLabelPresentOnFavoriteMiniWindow = By
			.xpath("//div[@id='favourite_report_setting']/following-sibling::div");

	public static final By screenshotUILelement = By.xpath("(//div[@id='favourite_report_setting'])[1]");

	public static final By listFavoriteFolderRHS = By.xpath("//ul[@class='main_menu scrollable-content']/li");
	public static final String listFavoriteFolderRHSStringFormat = "//ul[@class='main_menu scrollable-content']//li[not(ancestor::div[@id='favourite_deepMenu'])]//span[text()='%s']";
	public static final String listDeepFavoriteReportsInsideFolderStringFormat = "//ul[@class='main_menu scrollable-content']/li[@title='%s']//div[@id='favourite_deepMenu']//ul[@class='favouriteSubMenuUl']//li";
	public static final String favoriteMarkedReportStringFormat = "//li[@title='%s']//a//span";
	public static final String favoriteMarkedReportRHSStringFormat = "//ul[@class='favouriteSubMenuUl']//li[@title='%s']//a//span";
	public static final String btnDeleteFavoriteReportStringFormat = "//li[@title='%s']//img[@class='favourite-delete-icon']";

	public static final By btnfavoriteWhite = By.xpath("img[src='../images/new/favourite_white.svg']");
	public static final By btnfavoriteYellow = By.xpath("img[src='../images/new/favourite_yellow.svg']");

}
