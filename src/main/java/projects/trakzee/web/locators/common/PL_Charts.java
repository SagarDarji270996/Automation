package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_Charts {

	// Header Field
	public static final By textHeader = By.xpath("//input[@id='header']");
	public static final By pageTitle = By.xpath("(//div[@id='trakzeeAdvancedChart']/parent::div//span[1])[1]");

	// X-Axis Options List
	public static final By listXAxis = By.xpath("//select[@id='xaxis']");

	// Y-Axis Options List
	public static final By listYAxis = By.xpath("//select[@id='yaxis']");

	// Sortable List
	public static final By listSortAble = By
			.xpath("//tr[@style = '']//input[@class='sortable-checkbox'][@type='checkbox']");

	public static final By btnSaveOnCharts = By.xpath("//input[@id='btn_save']");
	public static final By btnCancelOnCharts = By.xpath("//input[@id='btn_cancel']");
	public static final By screenshortUIElement = By.xpath("(//div[@id='trakzeeAdvancedChart'])[1]");

}
