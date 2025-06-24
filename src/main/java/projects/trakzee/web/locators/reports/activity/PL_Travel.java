package projects.trakzee.web.locators.reports.activity;

import org.openqa.selenium.By;

public class PL_Travel {

	// Column Titles List
	public static final By listColumnTitles = By.xpath("//thead//tr[1]//td[normalize-space() != '']");

	// Travel Summary Values List
	public static final By listTravelSummaryValues = By.xpath("//tr[@class='TableSummary']//td");

	// Travel Summary Rows List
	public static final By listTravelSummaryRows = By.xpath("//tbody//tr[@class='z-grid-odd']");

	// Message Text on Popup Window
	public static final By textMessageOnPopupWindow = By
			.xpath("//*[contains(@class,'jalert')]//*[contains(@class,'content')]");

	// Close Button on Popup Window
	public static final By btnCloseOnPopupWindow = By.xpath(
			"//*[contains(@class,'jalert')]//*[contains(@class,'jclosebtn')] | //*[contains(@class,'jalert')]//*[normalize-space(text())='Close']");

	// Details screen
	public static final By detailsScreenCommonXpathStringForamtRowIndex = By
			.xpath("tr[id='detail_%s'] td[class='TableBorderColor parent-expand-td']");
	public static final By listDetailsScreenTableColumnTitle = By
			.xpath("//tbody//tr//th[@class='TableRowHeader']//div");
	public static final By listDetailsScreenTableColumnValues = By.xpath("//tbody//tr//td[@class='record-td']//input");
	public static final By btnplusAndMinusIconStringForamtRowIndex = By.xpath("//img[@id='img_%s']");
	public static final By footerUIElemnet = By.xpath("(//div[@class='bottombox'])[1]");

	public static By getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(String columnNames) {
		By listVehicleNameOnOverviewscreen = By
				.xpath("//tbody//tr[@class='z-grid-odd']//td[@name='" + columnNames + "']");
		System.out.println("Created xpath: " + listVehicleNameOnOverviewscreen);
		return listVehicleNameOnOverviewscreen;
	}

	public static By getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(String columnNames,
			String isTextInsideTag) {
		By listVehicleNameOnOverviewscreen = By
				.xpath("//tbody//tr[@class='z-grid-odd']//td[@name='" + columnNames + "']//input");
		System.out.println("Created xpath: " + listVehicleNameOnOverviewscreen);
		return listVehicleNameOnOverviewscreen;
	}

	public static By getListOfAnyColumnValuesFromOverviewScreenBasedOnColumnName(String columnNames, boolean isIcon) {
		By listVehicleNameOnOverviewscreen = By
				.xpath("//tbody//tr[@class='z-grid-odd']//td[@name='" + columnNames + "']//img");
		System.out.println("Created xpath: " + listVehicleNameOnOverviewscreen);
		return listVehicleNameOnOverviewscreen;
	}

}