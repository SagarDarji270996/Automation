package projects.trakzee.web.projectUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class MobileActions extends MobileBrowserWait {
	private SoftAssert softAssert = TestBase.getSoftAssert();
    // Modify static methods to accept WebDriver as a parameter
    public static void clickElementBy(WebDriver driver, By by) {
        tryFindElement(driver, by).click();
    }

    public static void clearAndfillInFieldWith(WebDriver driver, By by, String text) {
        tryFindElement(driver, by).clear();
        tryFindElement(driver, by).sendKeys(text);
    }

    public static WebElement tryFindElement(WebDriver driver, By by) {
        WebElement element = (WebElement) getFluentWait(driver).until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public static String getTextFromElement(WebDriver driver, By by) {
        return tryFindElement(driver, by).getText().trim();
    }

    public static int getNumberOfResultsForElement(WebDriver driver, By by) {
        return driver.findElements(by).size();
    }
}
