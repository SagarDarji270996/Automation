package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class HoverOnElement {
	private static Actions action;
	private static SoftAssert softAssert = TestBase.getSoftAssert();
	private static JavascriptExecutor jsExecutor = null;
	private static WebDriverWait wait = null;

	public static void hoverOnElement(WebDriver driver, String ElementAddress) throws InterruptedException {
		wait = new WebDriverWait(driver, 5);
		action = new Actions(driver);
		WebElement hoverElement = driver.findElement(By.xpath(ElementAddress));
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
		} catch (Exception e) {
			softAssert.assertTrue(false, "Mover not hover on the element ");
			System.out.println("Exception from: hoverOnElement >> " + e.getMessage());
		}

	}

	public static void hoverOnElement(WebDriver driver, By ElementAddress) throws InterruptedException {
		wait = new WebDriverWait(driver, 5);
		action = new Actions(driver);
		WebElement hoverElement = driver.findElement(ElementAddress);
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
		} catch (Exception e) {
			softAssert.assertTrue(false, "Mover not hover on the element ");
			System.out.println("Exception from: hoverOnElement >> " + e.getMessage());
		}

	}

	public static void hoverAndClickOnElement(WebDriver driver, By ElementAddress) throws InterruptedException {
		wait = new WebDriverWait(driver, 5);
		action = new Actions(driver);
		WebElement hoverElement = driver.findElement(ElementAddress);
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
			hoverElement.click();
		} catch (Exception e) {
			softAssert.assertTrue(false, "Mover not hover and click on the element ");
			System.out.println("Exception from: hoverOnElement >> " + e.getMessage());
		}

	}

	public void hoverOnElement(WebDriver driver, String elementName, By ElementAddress) throws InterruptedException {

		wait = new WebDriverWait(driver, 15);
		action = new Actions(driver);
		boolean isHover = false;
		WebElement hoverElement = driver.findElement(ElementAddress);
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
			isHover = true;
			System.out.println("Mouse hover on the element: " + elementName);
		} catch (Exception e) {
			System.out.println("Exception from: hoverOnElement >> " + e.getMessage());
		}
		softAssert.assertTrue(isHover, "Mouse not hovered on the element: " + elementName);

	}

	public void hoverOnElementAndVerify(WebDriver driver, String hoverElementXPath, String verifyElementXPath,
			String verifyElementValues) throws InterruptedException {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		System.out.println("Method called hoverOnElementAndVerify, called by: " + callerMethodName);

		wait = new WebDriverWait(driver, 15);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		// Find the element to hover on
		WebElement hoverElement = driver.findElement(By.xpath(hoverElementXPath));

		try {
			// Wait for the hover element to be visible
			wait.until(ExpectedConditions.visibilityOf(hoverElement));

			// Perform hover action
			action.moveToElement(hoverElement).pause(300).build().perform();
			System.out.println("Mouse hover performed on the element with XPath: " + hoverElementXPath);

			// After hover, verify the appearance of another element
			WebElement verifyElement = driver.findElement(By.xpath(verifyElementXPath));
			String verifyElementActualValues = verifyElement.getText().toString().trim();
			softAssert.assertEquals(verifyElementActualValues, verifyElementValues.trim(),
					"To verify elment after hover");
			// Wait for the verification element to appear
			wait.until(ExpectedConditions.visibilityOf(verifyElement));

			System.out.println("Verified appearance of element with XPath: " + verifyElementXPath);
		} catch (Exception e) {
			System.out.println("Exception in hoverOnElementAndVerify >> " + e.getMessage());
		}

	}

	public static void hoverOnElementAndVerify(WebDriver driver, By hoverElementXPath, By verifyElementXPath,
			String verifyElementValues) throws InterruptedException {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		System.out.println("Method called hoverOnElementAndVerify, called by: " + callerMethodName);

		wait = new WebDriverWait(driver, 15);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		// Find the element to hover on
		WebElement hoverElement = driver.findElement(hoverElementXPath);

		try {
			// Wait for the hover element to be visible
			wait.until(ExpectedConditions.visibilityOf(hoverElement));

			// Perform hover action
			action.moveToElement(hoverElement).pause(300).build().perform();
			System.out.println("Mouse hover performed on the element with XPath: " + hoverElementXPath);

			// After hover, verify the appearance of another element
			WebElement verifyElement = driver.findElement(verifyElementXPath);
			String verifyElementActualValues = verifyElement.getText().toString().trim();
			softAssert.assertEquals(verifyElementActualValues, verifyElementValues.trim(),
					"To verify elment after hover");
			// Wait for the verification element to appear
			wait.until(ExpectedConditions.visibilityOf(verifyElement));

			System.out.println("Verified appearance of element with XPath: " + verifyElementXPath);
		} catch (Exception e) {
			System.out.println("Exception in hoverOnElementAndVerify >> " + e.getMessage());
		}

	}

	public static void hoverOnElementAndVerifyTooltip(WebDriver driver, By hoverElementXPath, String attributeName,
			String expectedTooltip) throws InterruptedException {

		wait = new WebDriverWait(driver, 15);
		action = new Actions(driver);

		// Find the element to hover on
		WebElement hoverElement = driver.findElement(hoverElementXPath);
		String verifyElement = null;
		try {
			// Wait for the hover element to be visible
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			// Perform hover action
			action.moveToElement(hoverElement).pause(300).build().perform();

			// After hover, verify the appearance of another element
			verifyElement = hoverElement.getAttribute(attributeName);

		} catch (Exception e) {
			System.out.println("Exception in hoverOnElementAndVerify >> " + e.getMessage());
		}
		softAssert.assertEquals(verifyElement.trim(), expectedTooltip.trim(), "Tooltip and expected value not matched");

	}

	public static void hoverAndVerifyAndClicks(WebDriver driver, By hoverElementXPath, String attributeName,
			String expectedTooltip) throws InterruptedException {

		wait = new WebDriverWait(driver, 15);
		action = new Actions(driver);

		// Find the element to hover on
		WebElement hoverElement = driver.findElement(hoverElementXPath);
		String verifyElement = null;
		try {
			// Wait for the hover element to be visible
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			// Perform hover action
			action.moveToElement(hoverElement).pause(300).build().perform();

			// After hover, verify the appearance of another element
			verifyElement = hoverElement.getAttribute(attributeName);
			hoverElement.click();
		} catch (Exception e) {
			System.out.println("Exception in hoverOnElementAndVerify >> " + e.getMessage());
		}
		softAssert.assertEquals(verifyElement.trim(), expectedTooltip.trim(), "Tooltip: " + verifyElement.trim()
				+ " and expected value:" + expectedTooltip.trim() + " not matched or not clicked");

	}

	public static void hoverOnElements(WebDriver driver, By... xpathLocators) {
		// Initialize Actions class
		Actions actions = new Actions(driver);

		// Iterate through the XPath locators array and perform hover action on each element
		for (By xpathLocator : xpathLocators) {
			WebElement element = driver.findElement(xpathLocator);
			actions.moveToElement(element).perform();
		}
	}

	public static void hoverAndClickOnElements(WebDriver driver, By... xpathLocators) {
		// Initialize Actions class
		Actions actions = new Actions(driver);

		// Iterate through the XPath locators array and perform hover action on each element
		for (By xpathLocator : xpathLocators) {
			WebElement element = driver.findElement(xpathLocator);
			actions.moveToElement(element).perform();
			actions.moveToElement(element).click();
		}
	}



	public static void hoverOnElements(WebDriver driver, String... xpathLocators) {
		// Initialize Actions class
		Actions actions = new Actions(driver);

		// Iterate through the XPath locators array and perform hover action on each element
		for (String xpathLocator : xpathLocators) {
			WebElement element = driver.findElement(By.xpath(xpathLocator));
			actions.moveToElement(element).perform();
		}
	}

	public static void hoverAndClickOnElements(WebDriver driver, String... xpathLocators) {
		// Initialize Actions class
		Actions actions = new Actions(driver);

		// Iterate through the XPath locators array and perform hover action on each element
		for (String xpathLocator : xpathLocators) {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
			WebElement element = driver.findElement(By.xpath(xpathLocator));
			actions.moveToElement(element).perform();
			actions.moveToElement(element).click();
		}
	}

	public static <T> void nestedHoverAndClickOnListElements(WebDriver driver, List<String> expectedValues,
			List<T> locators) {
		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement notHoveredElement = null;
		for (int index = 0; index < locators.size(); index++) {
			By locator;

			if (locators.get(index) instanceof By) {
				locator = (By) locators.get(index);
			} else if (locators.get(index) instanceof String) {
				locator = By.xpath((String) locators.get(index)); // Assuming XPath if it's a string
			} else {
				System.out.println("Unsupported locator type at index: " + index);
				continue;
			}

			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
				boolean isClicked = false;
				for (WebElement element : elements) {
					notHoveredElement = element;
					String elementText = element.getText().trim();
					if (elementText.equalsIgnoreCase(expectedValues.get(index).trim())) {
						actions.moveToElement(element).perform();
						Thread.sleep(100);
						actions.moveToElement(element).click().perform();
						isClicked = true;
						System.out.println("Moved to and clicked: " + elementText);
						break;
					}
				}
				softAssert.assertTrue(isClicked,
						"Not clicked on the element at nested hover index: " + index);
				if (!isClicked) {
					System.out.println("Not hovered locator is: " + locator + " and web element: " + notHoveredElement);
				}
			} catch (Exception e) {
				System.out.println("Exception at index " + index + ": " + e.getMessage());
			}

		}

	}



}
