package projects.trakzee.web.locators.common.footer;

import org.openqa.selenium.By;

public class PL_Footer {

	// Footer
	public static final By listBottomIconElementAtFooter = By.xpath(
			"//td[@class='bottom-icon-box']//a[not(@style='display: none;') and not(@style='display:none;')] | //input[@name='searchterm'] | //select[@name='searchfield'] | //a[@class='navItem ft-icon icon bt-bottomsearch'] | //select[@id='recordsperpages'] | //select[@id='pagecounter'] | //span[@id='F2'] | //span[@id='F1'] | //div[@class='navItem2 totalrecord-box']");
	public static final By listBottomIconsRHSAtFooter = By
			.xpath("//td[@class='bottom-icon-box']//a[not(@style='display: none;') and not(@style='display:none;')]");
	public static final By btnPlusAtFooter = By.xpath("(//a[@title='New'])[1]");
	public static final By btnResetAtFooter = By.xpath("//a[@title='Reset']");
	public static final By btnExcelDownloadAtFooter = By.xpath("//a[@title='Excel Download']");
	public static final By btnPDFDownloadAtFooter = By.xpath("//a[@title='Download in PDF format']");
	public static final By btnCSVDownloadAtFooter = By.xpath("//a[@title='Download in CSV format']");
	public static final By searchBoxBottomAtFooter = By.xpath("//input[@name='searchterm']");
	public static final By searchAbleItemsAtFooter = By.xpath("//select[@name='searchfield']");
	public static final By searchfilterDropdownAtFooter = By
			.xpath("//select[@name='searchfield']//select[@name='searchfield']");
	public static final By searchIconBottomAtFooter = By.xpath("//a[@class='navItem ft-icon icon bt-bottomsearch']");
	public static final By iconRecordPerPageAtFooter = By.xpath("//select[@id='recordsperpages']");
	public static final By pageCounterAtFooter = By.xpath("//select[@id='pagecounter']");
	public static final By btnArrowNextPageAtFooter = By.xpath("//span[@id='F2']");
	public static final By btnArrowPreviousPageAtFooter = By.xpath("//span[@id='F1']");
	public static final By textTotalRecordsAtFooter = By.xpath("//div[@class='navItem2 totalrecord-box']");
	public static final By textTotalRecordsPresentAtFooter = By
			.xpath("//div[@class='navItem2 totalrecord-box']//span[@id='totrec']");
	public static final By btnBackAtFooter = By.xpath("(//a[@title='Back'])[1]");
	public static final By btnSaveAtFooter = By.xpath("(//a[@title='Save'])[1]");
	public static final By btnDeleteAtFooter = By.xpath("(//a[@title='Delete'])[1]");


}
