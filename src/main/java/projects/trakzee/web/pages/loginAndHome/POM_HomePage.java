package projects.trakzee.web.pages.loginAndHome;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Commons;
import projects.trakzee.web.locators.common.PL_FavoriteButton;
import projects.trakzee.web.locators.loginAndHome.PL_HomePage;
import projects.trakzee.web.projectUtility.CommonMethods;
import projects.trakzee.web.projectUtility.HoverOnElement;
import projects.trakzee.web.projectUtility.ProjectUtility;

public class POM_HomePage implements InitializePages {

	WebDriver driver = TestBase.getWebDriver();;
	public POM_HomePage(WebDriver driver) {
		this.driver = driver;
	}

	WebDriverWait wait = new WebDriverWait(driver, 5);
	ProjectUtility pu = new ProjectUtility();


	public void navigateToScreen(String mainModule, String subModule, String screen) throws InterruptedException {
		Thread.sleep(500);
		getIFramePage().switchToDivFrame();
		CommonMethods.callMeToClickOnAnyButtonWithNameAndXpath(driver, PL_Commons.hoverMainModule, mainModule,
				mainModule);
		CommonMethods.callMeToClickOnAnyButtonWithNameAndXpath(driver, PL_Commons.hoverSubModule2, subModule,
				subModule);
		CommonMethods.callMeToClickOnAnyButtonWithNameAndXpath(driver, PL_Commons.hoverScreen2, screen, screen);

	}

	public void navigateToScreen(List<String> listExpectedValue) throws InterruptedException {
		Thread.sleep(500);
		getIFramePage().switchToDivFrame();
		List<By> listLocators = new ArrayList<>();
		listLocators.add(PL_HomePage.listMainMenus);
		listLocators.add(PL_HomePage.ListSubMenus);
		listLocators.add(PL_HomePage.ListDeepMenus);
		HoverOnElement.nestedHoverAndClickOnListElements(driver, listExpectedValue, listLocators);
		getTravelPage().checkTravelSummaryPageLoadingOnDefault(30);
	}

	public void navigateToFavoriteReports(String folderName, String reportName) throws InterruptedException {
		getIFramePage().switchToDivFrame();
		CommonMethods.callMeToClickOnAnyButtonWithNameAndXpath(driver,
				PL_FavoriteButton.listFavoriteFolderRHSStringFormat, folderName, folderName);

		HoverOnElement.hoverAndClickOnElements(driver,
				String.format(PL_FavoriteButton.favoriteMarkedReportRHSStringFormat, reportName));
	}

	public void clickOnUserInfoButton() {
		getIFramePage().switchToDivFrame();
		WebElement userInfoButton = wait.until(ExpectedConditions.elementToBeClickable(PL_HomePage.USER_BUTTON));
		userInfoButton.click();
	}

	public void clickOnCrossButtonAvoidServiceInteruption() {
		getIFramePage().switchToDivFrame();
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			WebElement userInfoButton = wait
					.until(ExpectedConditions.elementToBeClickable(PL_HomePage.btnCrossIconServerChargeInteruption));
			userInfoButton.click();
		} catch (Exception e) {
		}
	}

	public void selectProject(String projectName) {
		clickOnUserInfoButton();
		WebElement applicationsElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(PL_HomePage.APPLICATIONS_MENU));
		Actions actions = new Actions(driver);
		actions.moveToElement(applicationsElement).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PL_HomePage.SIDE_DRAWER));
		By projectLocator = PL_HomePage.getProjectByName(projectName);
		WebElement projectElement = wait.until(ExpectedConditions.presenceOfElementLocated(projectLocator));

		String targetProject = projectElement.getText();
		WebElement alreadySelectedProjectElement = wait
				.until(ExpectedConditions.presenceOfElementLocated(PL_HomePage.textAlreadySelectedProject));
		String alreadySelectedProject = alreadySelectedProjectElement.getText();
		if (!targetProject.trim().equals(alreadySelectedProject.trim())) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", projectElement);
			wait.until(ExpectedConditions.elementToBeClickable(projectElement));
			try {
				projectElement.click();
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", projectElement);
			}
			System.out.println("Selected project: " + projectName);
		} else {
			clickOnApplicationMenu();
			System.out.println("Given project already selected: " + projectName);
		}
		
	}

	public void clickOnCloudDownloadIcon() {
		//STEP_DONE: To click on the cloud download icon button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_HomePage.btnNavigationDownlaods, 10);

	}

	public void clickOnApplicationMenu() {
		//STEP_DONE: To click on the logo download icon button
		getIFramePage().switchToDivFrame();
		pu.clickOnButton(driver, PL_HomePage.APPLICATIONS_MENU, 10);

	}

}
