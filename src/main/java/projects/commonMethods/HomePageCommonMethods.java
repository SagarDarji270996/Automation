package projects.commonMethods;

import projects.taskeye.configurations.common.CommonMethods;
import projects.taskeye.configurations.common.IframesOfApplication;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import projects.taskeye.web.locators.HomePageLocators;

public class HomePageCommonMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final CommonMethods commonMethods;
    private final IframesOfApplication iframe;

    public HomePageCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        this.iframe = new IframesOfApplication(driver);
        this.wait = new WebDriverWait(driver, 60);
    }

    public void navigateToScreen(String mainModule, String subModule, String screen) throws InterruptedException {
        Thread.sleep(500);
        iframe.switchToDivFrame();
        commonMethods.clickOnAnyButtonWithNameAndXpath(mainModule, String.format(HomePageLocators.MAIN_MODULE_BUTTON_XPATH, mainModule));
        commonMethods.clickOnAnyButtonWithNameAndXpath(subModule, String.format(HomePageLocators.SUB_MODULE_BUTTON_XPATH, subModule, subModule));
        commonMethods.clickOnAnyButtonWithNameAndXpath(screen, String.format(HomePageLocators.SCREEN_BUTTON_XPATH, screen, screen));
    }

    public void clickOnUserInfoButton() {
        iframe.switchToDivFrame();
        WebElement userInfoButton = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.USER_BUTTON));
        userInfoButton.click();
    }

    public void selectProject(String projectName) {
        WebElement applicationsElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.APPLICATIONS_MENU));
        Actions actions = new Actions(driver);
        actions.moveToElement(applicationsElement).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.SIDE_DRAWER));
        By projectLocator = HomePageLocators.getProjectByName(projectName);
        WebElement projectElement = wait.until(ExpectedConditions.presenceOfElementLocated(projectLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", projectElement);
        wait.until(ExpectedConditions.elementToBeClickable(projectElement));
        try {
            projectElement.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", projectElement);
        }
        System.out.println("Selected project: " + projectName);
    }
}
