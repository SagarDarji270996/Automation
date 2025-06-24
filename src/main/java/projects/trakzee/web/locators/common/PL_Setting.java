package projects.trakzee.web.locators.common;

import org.openqa.selenium.By;

public class PL_Setting {

	// Columns
	public static final By advanceSettingsContainer = By.xpath("(//div[@class='sort-box-container'])[1]");
	public static final By listSettingColumns = By.xpath(
			"//tr[not(contains(@style, 'display:none'))]//div[@class='field-name'] | //tr[not(contains(@style, 'display:none'))]//td[@class='field-name']");
	public static final By listRowEntriesWithCheckboxOnly = By
			.xpath("//tbody//tr[@class='field-row' and not(contains(@style, 'display:none'))]");
	public static final By listShowAbleCheckbox = By
			.xpath("//tr[@style = '']//input[@class='showable-checkbox'][@type='checkbox']");
	public static final By listPrintAbleCheckbox = By
			.xpath("//tr[@style = '']//input[@class='printable-checkbox'][@type='checkbox']");
	public static final By listSortAbleCheckbox = By
			.xpath("//tr[@style = '']//input[@class='sortable-checkbox'][@type='checkbox']");
	public static final By listSortSequence = By
			.xpath("//tr[@style = '']//div[@class='sorting-btn-td']//*[@class='sort-seq']");
	public static final By listSorting = By
			.xpath("//tr[@style = '']//div[@class='sorting-btn-td']//*[@class='sorting_update']");
	public static final By listSortPriority = By.xpath(
			"//tr[@class='field-row' and not(ancestor::*[contains(@style, 'display:none')]) and @style = '']//select[@class='sort-seq']");
	public static final By listSortPriorityOption = By.xpath("//select[@class='sort-seq']//option");

	// Common xpath for row list
	public static final String listShowAbleCheckboxCommon = "//input[@class='showable-checkbox'][@type='checkbox'][not(@disabled)]";
	public static final By listShowableOnEnabledchecbox = By
			.xpath("//input[@class='showable-checkbox'][@type='checkbox'][not(@disabled)]");
	public static final String listPrintAbleCheckboxCommon = "//input[@class='printable-checkbox'][@type='checkbox'][not(@disabled)]";
	public static final By listPrintAbleCheckboxCommonEnabled = By
			.xpath("//input[@class='printable-checkbox'][@type='checkbox'][not(@disabled)]");
	public static final String listSortAbleCheckboxCommon = "//input[@class='sortable-checkbox'][@type='checkbox'][not(@disabled)]";
	public static final By listSortSequenceCommon = By
			.xpath("//div[@class='sorting-btn-td']//*[@class='sort-seq'][not(@disabled)]");
	public static final By listSortingCommon = By
			.xpath("//div[@class='sorting-btn-td']//*[@class='sorting_update'][not(@disabled)]");
	public static final String listSortPrioritySelectTag = "//select[@class='sort-seq']";
	public static final By listSortingAscendingCommon = By
			.xpath("//div[@class='sorting-btn-td']//*[@class='sorting_update']//span[text()='Asc']");
	public static final By listSortingDescendingCommon = By
			.xpath("//div[@class='sorting-btn-td']//*[@class='sorting_update']//span[text()='Des']");

	// For draggable mini window
	public static final By textTitleOfDragableMiniwindow = By
			.xpath("//div[@role='dialog' and contains(@style, 'opacity: 1')]//span[@class='ui-dialog-title']");
	public static final By iconCrossDragAbleMiniWindow = By.xpath(
			"//div[@role='dialog' and contains(@style, 'opacity: 1')]//button[contains(@class,'ui-dialog-titlebar-close')]");

	//To select the radio button
	public static By selectDetailReportDownloadRadioButtonLocatory(String buttonName) {
		switch (buttonName) {
		case "Single Sheet": {
			final By radioBtnSingleSheet = By.xpath("//input[@id='single_sheet']");
			return radioBtnSingleSheet;
		}
		case "Multiple Sheet": {
			final By radioBtnMultiSheet = By.xpath("//input[@id='multiple_sheet']");
			return radioBtnMultiSheet;
		}
		}
		return null;

	}

	public static final By handlerIconForDragAndDropRowEntriesStringFormat = By
			.xpath("//table[@id='screencustomisation']//tbody[%s]//td[@class='handler-icon']");
	public static final By iconBtnSave = By.xpath("//span[@title='Save']");
	public static final By iconBtnRestoreDefault = By.xpath("//span[@title='Restore Default']");
	public static final By iconBtnDelete = By.xpath("//span[@id='popup-delete-btn' and @title='Delete']");
	public static final By listSettingColumnHeadingTitle = By
			.xpath("//table[@id='screencustomisation']//thead//tr//th[normalize-space() !='']");
	public static final By listDateSelection = By
			.xpath("//div[contains(@class, 'daterangepicker ltr show-ranges opensleft datepicker-show-flex')]//li");

}