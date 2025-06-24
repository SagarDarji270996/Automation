package projects.trakzee.web.pages.common.header;

import java.util.Arrays;
import java.util.ListIterator;
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
import projects.trakzee.web.locators.common.PL_Setting;
import projects.trakzee.web.projectUtility.CheckboxHandler;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.RowListHandling;
import projects.trakzee.web.projectUtility.StringHandling;
import projects.trakzee.web.projectUtility.XpathExtractor;

public class POM_Setting implements InitializePages {

	private WebDriver driver = TestBase.getWebDriver();
	ProjectUtility pu = new ProjectUtility();

	public POM_Setting(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriverWait wait = new WebDriverWait(driver, 5);

	@Description("This is used to hanlde the settings functionality irrespective of pages by passing the page name and possible arguments present on pages.")
	@Feature("Hanlde end to end setings functionality for all the pages throught out the project")
	public boolean settingFunctionalityForAllPagesBasedOnField(Map<String, Object> args) throws InterruptedException {
		args = StringHandling.convertKeysToCamelCase(args);
		Thread.sleep(2000);
		clickOnSettingIconButtonAndVerifyTooltip();
		verifySettingsPageTitle("Settings");

		String listOfColumnTitleCommaSeperated = "Column,Showable,Printable,Sortable,Sort Priority,Sorting";
		verifyColumnNamePresent(listOfColumnTitleCommaSeperated);

		Set<String> filedNames = args.keySet();
		boolean isClickedOnActionButton = false;
		System.out.println("List of filed present as per given data: " + filedNames);
		for (String field : filedNames) {
			Object value = args.get(field);

			if (!(value instanceof String || value instanceof Number) || !"na".equalsIgnoreCase(value.toString())) {
				// Proceed if value is NOT "na"
				switch (field) {
				case "rowTitleList": {
					verifyRowEntriesTitleUnderTheColumn((String) args.get("rowTitleList"));
					break;
				}
				case "dragAndDropRow": {
					verifyDragAndDropRowEntries((Double) args.get("dragAndDropRow"));
					break;
				}
				case "actionRowTitle": {
					int[] actionColumnIndexes = new int[3];
					boolean isShowable = Boolean.parseBoolean((String) args.get("showable"));
					if (isShowable) {
						actionColumnIndexes[0] = 2;
					}

					boolean isPrintable = Boolean.parseBoolean((String) args.get("printable"));
					if (isPrintable) {
						actionColumnIndexes[1] = 3;
					}
					boolean isSortable = Boolean.parseBoolean((String) args.get("sortable"));
					if (isSortable) {
						actionColumnIndexes[2] = 4;
					}

					String[] ationRowTitles = ((String) args.get("actionRowTitle")).split(",");
					for (String title : ationRowTitles) {
						verifyCheckBoxesForTheShowablePrintableSortableSortpriorityAndSorting(title, 1,
								actionColumnIndexes);
					}
					break;
				}
				case "actionButton": {
					if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("save")) {
						isClickedOnActionButton = clickOnSaveButtonAndVerify();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("delete")) {
						isClickedOnActionButton = clickOnDeleteButtonAndVerify();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("crossIcon")) {
						isClickedOnActionButton = clickOnCrossIconButtonAndVerify();
					} else if ((args.get("actionButton").toString()).trim().replaceAll(" ", "").toLowerCase()
							.contains("reset")) {
						isClickedOnActionButton = clickOnRestoreDefaultButtonAndVerify();
					}
				}

				}
			}
		}

		if (isClickedOnActionButton) {
			if (!(args.get("errorMessage") == null
					|| ((String) args.get("errorMessage")).trim().matches("(?i)^\\s*(|NA|NULL)\\s*$"))) {
				// The (?i) makes it case-insensitive.
				// The regex ^\\s*(|NA|NULL)\\s*$ ensures only "NA", "NULL", empty, or whitespace strings are matched.
				getCommonPage().getJAlertMessageAndVerify((String) args.get("errorMessage"));
				getCommonPage().clickOnJAlertCloseButton();
			} else {
				boolean jalertMessage = false;
				try {
					getIFramePage().switchToDivFrame();
					WebDriverWait wait = new WebDriverWait(driver, 1);
					jalertMessage = driver.findElement(PL_Commons.textJAlertMessage).isDisplayed();
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

		return isClickedOnActionButton;

	}

	public void verifyThatUserAbleHandleTheSettingFunctionality(String settingPageTitle, Double indexForDragAndDrop,
			String listOfColumnTitleCommaSeperated, String listOfRowEntriesTitleCommaSeperated, String searchKey,
			int searchKeyColumnIndex, int[] actionColumnIndexes) throws InterruptedException {
		// SCENARIOS_DONE: verify that user able handle the setting functionality
		clickOnSettingIconButtonAndVerifyTooltip();
		verifySettingsPageTitle(settingPageTitle);
		clickOnCrossIconButtonAndVerify();
		clickOnSettingIconButtonAndVerifyTooltip();
		clickOnDeleteButtonAndVerify();
		clickOnSettingIconButtonAndVerifyTooltip();
		clickOnSaveButtonAndVerify();
		clickOnSettingIconButtonAndVerifyTooltip();
		clickOnRestoreDefaultButtonAndVerify();

		verifyDragAndDropRowEntries(indexForDragAndDrop);
		verifyColumnNamePresent(listOfColumnTitleCommaSeperated);
		verifyRowEntriesTitleUnderTheColumn(listOfRowEntriesTitleCommaSeperated);
		verifyCheckBoxesForTheShowablePrintableSortableSortpriorityAndSorting(searchKey, searchKeyColumnIndex,
				actionColumnIndexes);
		clickOnSaveButtonAndVerify();

	}

	public void verifyTheListOfColumnNamePresentOnOverviewScreenBasedOnTheAppliedSettingsForShowable(
			String settingPageTitle, String[] expectedShowAbleListCommaSeparated) throws InterruptedException {
		// SCENARIOS_DONE: Verify the list of column name present on overview screen based on the applied settings for showable
		clickOnSettingIconButtonAndVerifyTooltip();
		verifySettingsPageTitle(settingPageTitle);
		uncheckAllTheShowableCheckboxes();
		selectTheCheckboxesForShowableColumnIfNotSelected(expectedShowAbleListCommaSeparated);
		clickOnSaveButtonAndVerify();
		getIFramePage().switchToTitleFrame();
		pu.clickOnButton(driver, PL_Commons.btnFilterAtHeader);
		getIFramePage().switchToContentFrame();
	}

	@Step("Settings icon")
	public void clickOnSettingIconButtonAndVerifyTooltip() throws InterruptedException {
		// STEP_DONE: click on the setting icon button and verify tooltip
		getIFramePage().switchToTitleFrame();
		pu.getTextByAttributeAndVerify(driver, "title", "Settings", PL_Commons.btnSettingAtHeader);

		pu.clickOnButton(driver, PL_Commons.btnSettingAtHeader, 15);

		getIFramePage().switchToDivFrame();
		pu.getElementTextAndCompare(driver, PL_Setting.textTitleOfDragableMiniwindow, "Settings", 5);
	}

	public void verifySettingsPageTitle(String settingPageTitle) throws InterruptedException {
		// STEP_DONE: verify settings page title
		getIFramePage().switchToDivFrame();
		CommonMethods.getElementTextAndCompare(driver, PL_Setting.textTitleOfDragableMiniwindow, settingPageTitle);
	}

	public void verifyColumnNamePresent(String listOfColumnTitleCommaSeperated) {
		// STEP_DONE: verify column name present
		getIFramePage().switchToSettingPopup();
		String expectedColumnNamesList = listOfColumnTitleCommaSeperated;
		CommonMethods.compareListByText(driver, PL_Setting.listSettingColumnHeadingTitle, expectedColumnNamesList);

	}

	public void verifyRowEntriesTitleUnderTheColumn(String listOfRowEntriesTitleCommaSeperated) {
		// STEP_DONE: verify row entries title under the column
		getIFramePage().switchToSettingPopup();
		String expectedColumnSettingTitlte = listOfRowEntriesTitleCommaSeperated;
		CommonMethods.compareListByText(driver, PL_Setting.listSettingColumns, expectedColumnSettingTitlte);

	}

	public void verifyCheckBoxesForTheShowablePrintableSortableSortpriorityAndSorting(String searchKey,
			int searchKeyColumnIndex, int[] actionColumnIndex) {
		// STEP_DONE: verify check boxes for the showable printable sortable sortpriority and sorting
		getIFramePage().switchToSettingPopup();
		String[] actionColumnIndexesXpathVerticalFlow = { PL_Setting.listShowAbleCheckboxCommon,
				PL_Setting.listPrintAbleCheckboxCommon, PL_Setting.listSortAbleCheckboxCommon };

		RowListHandling.clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(driver,
				PL_Setting.listRowEntriesWithCheckboxOnly, searchKeyColumnIndex, searchKey, actionColumnIndex,
				actionColumnIndexesXpathVerticalFlow);
	}

	public void verifyScrollProperty() throws InterruptedException {
		// STEP_DONE: verify scroll property
		getIFramePage().switchToSettingPopup();
		getSchedulePage().scrollAdvanceConfigurationRowList();

	}

	public void verifyDragAndDropRowEntries(Double index) {
		// STEP_DONE: verify drag and drop row entries
		getIFramePage().switchToSettingPopup();
		String xpath = XpathExtractor
				.getXpathValueFromByDataType(PL_Setting.handlerIconForDragAndDropRowEntriesStringFormat);
		CommonMethods.dragAndDropInSamePlane(driver, String.format(xpath, index), 0, 20);
	}

	public boolean clickOnCrossIconButtonAndVerify() {
		// STEP_DONE: click on cross icon button and verify
		getIFramePage().switchToDivFrame();
		return pu.clickOnButton(driver, PL_Setting.iconCrossDragAbleMiniWindow, 4);

	}

	public boolean clickOnSaveButtonAndVerify() {
		// STEP_DONE: click on save button and verify
		getIFramePage().switchToSettingPopup();
		return pu.clickOnButton(driver, PL_Setting.iconBtnSave, 3);

	}

	public boolean clickOnRestoreDefaultButtonAndVerify() {
		// STEP_DONE: click on restore default button and verify
		getIFramePage().switchToSettingPopup();
		return pu.clickOnButton(driver, PL_Setting.iconBtnRestoreDefault, 3);

	}

	@Step("Delete icon")
	public boolean clickOnDeleteButtonAndVerify() {
		// STEP_DONE: click on delete button and verify
		getIFramePage().switchToSettingPopup();
		return pu.clickOnButton(driver, PL_Setting.iconBtnDelete, 3);

	}

	public void selectTheCheckboxesForShowableColumnIfNotSelected(String[] expectedListCommaSeparated) {
		// STEP: Select the checkboxes for the showable column
		ListIterator<String> list = Arrays.asList(expectedListCommaSeparated).listIterator();
		getIFramePage().switchToSettingPopup();
		while (list.hasNext()) {
			String actionColumnXpath = PL_Setting.listShowAbleCheckboxCommon; // Reuse the variable instead of hardcoding
			RowListHandling.clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(driver,
					PL_Setting.listRowEntriesWithCheckboxOnly, 1, list.next().toString(), new int[] { 2 },
					new String[] { actionColumnXpath });
		}
	}

	public void selectTheCheckboxesForPrintableColumnIfNotSelected(String[] expectedListCommaSeperacted) {
		//STEP: Select the check boxes for the showable column

	}

	public void selectTheCheckboxesForSortableColumnIfNotSelected(String[] expectedListCommaSeperacted) {
		//STEP: Select the check boxes for the showable column

	}

	public void uncheckAllTheShowableCheckboxes() {
		// STEP_DONE: uncheck all the showable checkboxes
		getIFramePage().switchToSettingPopup();
		CheckboxHandler.verifyAndUncheckCheckboxes(driver, PL_Setting.listShowableOnEnabledchecbox);
	}

	public void uncheckAllThePrintableCheckboxes() {
		// STEP_DONE: uncheck all the printable checkboxes
		getIFramePage().switchToSettingPopup();
		CheckboxHandler.verifyAndUncheckCheckboxes(driver, PL_Setting.listPrintAbleCheckboxCommonEnabled);

	}

}