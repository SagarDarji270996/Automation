package projects.trakzee.web.projectUtility;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class VisibleElement {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	// DECLARATIONS
	public static Logger logger = LogManager.getLogger("logger");
	public static Logger reportLogger = LogManager.getLogger("reportLogger");
	public static Actions action;
	public static JavascriptExecutor jsExecutor = null;
	private static WebDriverWait wait = null;

	// To check the element is viisble nor, if the xpath having more then one
	// element selection but only one is visible.
	public static WebElement findVisibleElement(WebDriver driver, String xElementLocator)
			throws InterruptedException {
		WebElement visibleElement = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return [...document.evaluate(\"" + xElementLocator
						+ "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)].find(el => el.offsetParent !== null);");
		return visibleElement;
	}

	public static WebElement findVisibleElement(WebDriver driver, By by) throws InterruptedException {
		WebElement visibleElement = (WebElement) ((JavascriptExecutor) driver).executeScript(
				"return [...document.evaluate(arguments[0], document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)].find(el => el.offsetParent !== null);",
				by.toString());
		return visibleElement;
	}

	public static boolean isElementVisible(WebDriver driver, By elementLocator) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementVisible(WebDriver driver, String xpathAddress) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathAddress)));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementVisible(WebDriver driver, By elementLocator, long waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
