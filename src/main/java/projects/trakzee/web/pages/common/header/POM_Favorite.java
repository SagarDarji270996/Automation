package projects.trakzee.web.pages.common.header;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_FavoriteButton;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.ScreenshotHandler;

public class POM_Favorite implements InitializePages {

	private WebDriver driver = TestBase.getWebDriver();
	WebDriverWait wait;
	public POM_Favorite(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}

	SoftAssert softAssert = new SoftAssert();
	//CommonData commonMethods;
	ProjectUtility pu = new ProjectUtility();


	public void checkUIElementOnFavoritePage(String existingScreenShortsPath, String existingImageName,
			String checkUIElementOnFavoritePage) throws IOException, InterruptedException {
		// STEP_DONE: check the UI element present on the favorite page on the left-hand side
		getIFramePage().switchToDivFrame();
		ScreenshotHandler.compareElementScreenshot(driver, PL_FavoriteButton.screenshotUILelement,
				existingScreenShortsPath, existingImageName, checkUIElementOnFavoritePage);
	}

	public void clickCrossIconButton() {
		// STEP_DONE: click on the cross icon button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_FavoriteButton.crossIconOnMiniWindow);

	}

	public void selectOptionFromDropdown(String folderName) {
		// STEP_DONE: select the desired option from the dropdown
		getIFramePage().switchToDivFrame();
		getCommonMethodPage().selectFromSelectDropdown(PL_FavoriteButton.listFavouriteFolder, folderName);
	}

	public void setFolderName(String folderName) {
		// STEP_DONE: set the filter name
		getIFramePage().switchToDivFrame();
		pu.setDataInField(driver, PL_FavoriteButton.fieldFavoriteFolderName, folderName, 5);

	}

	public void clickSaveButton() {
		// STEP_DONE: click on the save button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_FavoriteButton.btnSave, 1);

	}

	public void clickRemoveButton() {
		// STEP_DONE: click on the remove button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_FavoriteButton.btnCancel, 1);

	}

	public void clickDeleteButtonFavoriteReport(String favoriteReportName) {
		// STEP_DONE: click on the remove button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver,
				String.format(PL_FavoriteButton.btnDeleteFavoriteReportStringFormat, favoriteReportName));

	}
}
