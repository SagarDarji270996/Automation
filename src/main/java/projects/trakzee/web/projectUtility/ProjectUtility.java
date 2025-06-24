package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class ProjectUtility {
	private static WebDriverWait wait;

	public boolean clickOnButton(WebDriver driver, By locatorAddress, int waitTimeInSeconds) {
		wait = new WebDriverWait(driver, waitTimeInSeconds);
		try {
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locatorAddress));
			loginButton.click();
			System.out.println("Clicked on the given element: " + locatorAddress);
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not click on the button: " + locatorAddress);
		}
		return false;

	}

	public boolean clickOnButtonIfLocatorIsMultiple(WebDriver driver, By locatorAddress, long waitTimeInSeconds) {
		wait = new WebDriverWait(driver, waitTimeInSeconds);
		boolean isClicked = false;
		try {
			List<WebElement> button = driver.findElements(locatorAddress);
			int count = 0;
			for (WebElement ele : button) {
				count++;
				System.out.println("Total button presence: " + button.size() + " and current iteration: " + count);
				try {
					wait.until(ExpectedConditions.visibilityOf(ele));
					ele.click();
					System.out.println("Clicked on the given element: " + locatorAddress);
					return true;
				} catch (Exception e) {
					wait = new WebDriverWait(driver, 1);
					System.out.println("Exceptino from: clickOnButtonIfLocatorIsMultiple: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not click on the button: " + locatorAddress);

		}
		return false;

	}

	public void clickOnButtonNew(WebDriver driver, By locatorAddress, long waitTimeInSeconds) throws Exception {
		wait = new WebDriverWait(driver, waitTimeInSeconds);
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locatorAddress));
		loginButton.click();
		System.out.println("Clicked on the given element: " + locatorAddress);
	}

	public void clickOnButton(WebDriver driver, By locatorAddress) {
		wait = new WebDriverWait(driver, 3);
		try {
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locatorAddress));
			loginButton.click();
			System.out.println("Clicked on the given element: " + locatorAddress);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getStackTrace());
			TestBase.getSoftAssert().assertTrue(false, "Not click on the button: " + locatorAddress);
		}
	}


	public boolean clickOnButtonWithBooleanReturn(WebDriver driver, By locatorAddress) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locatorAddress));
			loginButton.click();
			System.out.println("Clicked on the given element: " + locatorAddress);
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getStackTrace());
			TestBase.getSoftAssert().assertTrue(false, "Not click on the button: " + locatorAddress);
		}

		return false;
	}

	public void clickOnButton(WebDriver driver, String locatorAddress) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorAddress)));
			loginButton.click();
			System.out.println("Clicked on the given element: " + locatorAddress);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not click on the button: " + locatorAddress);
		}

	}

	public void setDataInField(WebDriver driver, By locatorAddress, String value, long waitTimeInSeconds) {
		wait = new WebDriverWait(driver, waitTimeInSeconds);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorAddress));
			ScrollAction.scrollElementTillNotVisible(driver, element);
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not set data in field: " + locatorAddress);
		}

	}

	public void setDataInFieldWithClear(WebDriver driver, By locatorAddress, String value) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorAddress));

			ScrollAction.scrollElementTillNotVisible(driver, element);
			element.click();
			element.sendKeys(Keys.CONTROL, "a");
			element.sendKeys(Keys.DELETE);
			element.sendKeys(value);
			System.out.println("Data set successfully in field: " + value);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not set " + value + " data in field: " + locatorAddress);
		}

	}

	public void setDataInFieldWithClear(WebDriver driver, String locatorAddress, String value) {
		wait = new WebDriverWait(driver, 5);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorAddress)));

			ScrollAction.scrollElementTillNotVisible(driver, element);
			element.click();
			element.sendKeys(Keys.CONTROL, "a");
			element.sendKeys(Keys.DELETE);
			element.sendKeys(value);
			System.out.println("Data set successfully in field: " + value);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			TestBase.getSoftAssert().assertTrue(false, "Not set " + value + " data in field: " + locatorAddress);
		}

	}

	public void setDataInField(WebDriver driver, By locatorAddress, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			// Wait for the element to be visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorAddress));
			// Scroll into view to ensure it is interactable
			ScrollAction.scrollElementTillNotVisible(driver, element);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(value);
			// Optional: Log success (if needed)
			System.out.println("Data set " + value + " successfully in field");
		} catch (Exception e) {
			// Log the exception and assert failure
			System.err.println("Exception: " + e.getLocalizedMessage());
			TestBase.getSoftAssert().fail("Failed to set data in field: " + locatorAddress);
		}

	}

	public boolean getTextByAttributeAndVerify(WebDriver driver, String attributeName, String expectedValues,
			By elementAddress) {
		wait = new WebDriverWait(driver, 5);
		try {
			// Locate the element using the attribute
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementAddress));
			String actualText = element.getAttribute(attributeName).trim();
			System.out.println("ActualText: " + actualText + " and expected text: " + expectedValues);
			TestBase.getSoftAssert().assertEquals(actualText, expectedValues.trim(), "Text verification failed!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean getTextByAttributeAndVerify(WebDriver driver, String attributeName, String expectedValues,
			By elementAddress, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		try {
			// Locate the element using the attribute
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementAddress));
			String actualText = element.getAttribute(attributeName).trim();
			System.out.println("ActualText: " + actualText + " and expected text: " + expectedValues);
			TestBase.getSoftAssert().assertEquals(actualText, expectedValues.trim(), "Text verification failed!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean getTextByAttributeAndVerify(WebDriver driver, String attributeName, String expectedValues,
			String elementAddress, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		try {
			// Locate the element using the attribute
			System.out.println("Locator: " + elementAddress);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementAddress)));
			String actualText = element.getAttribute(attributeName).trim();
			System.out.println("ActualText: " + actualText + " and expected text: " + expectedValues);
			TestBase.getSoftAssert().assertEquals(actualText, expectedValues.trim(), "Text verification failed!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean getTextByAttributeAndVerify(WebDriver driver, String attributeName, String expectedValues,
			String elementAddress) {
		wait = new WebDriverWait(driver, 5);
		String actualText = null;
		try {
			// Locate the element using the attribute
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementAddress)));
			actualText = element.getAttribute(attributeName);
			TestBase.getSoftAssert().assertEquals(actualText, expectedValues, "❌ Text verification failed!");
			System.out.println("✅ Expected:" + expectedValues + " matched with actual text:" + actualText);
			return true;
		} catch (Exception e) {
			TestBase.getSoftAssert()
					.fail("❌ Text verification failed! expected: " + expectedValues + " and found: " + actualText);
			e.printStackTrace();
			return false;
		}
	}

	public List<String> getTextFromListAndVerify(WebDriver driver, String listElementAddress, String attributeName,
			List<String> expectedList) {
		List<WebElement> listElement = driver.findElements(By.xpath(attributeName));
		List<String> actualList = new ArrayList<>();
		for (WebElement ele : listElement) {
			String actualName = ele.getAttribute(attributeName);
			// System.out.println("actualName with Attribute: "+actualName);
			if (actualName.isEmpty() || actualName.isBlank() || actualName.equals("") || actualName == null) {
				actualName = ele.getText().trim();
				// System.out.println("actualName without Attribute: "+actualName);
			}

			actualList.add(actualName);
		}
		TestBase.getSoftAssert().assertEquals(actualList, expectedList, "❌❌❌ List name matched");

		return actualList;
	}

	public String getElementText(WebDriver driver, By xpathLocator) throws InterruptedException {
		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			WebElement tooltips_Title = wait.until(ExpectedConditions.visibilityOfElementLocated((xpathLocator)));
			TitleValues = tooltips_Title.getText().trim();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return TitleValues;
	}

	public String getElementText(WebDriver driver, String xpathLocator) throws InterruptedException {
		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			WebElement tooltips_Title = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
			TitleValues = tooltips_Title.getText().trim();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return TitleValues;
	}

	public boolean getElementTextAndCompare(WebDriver driver, By xpathLocator, String expectedValues)
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 4);

		String TitleValues = null;
		boolean isMatched = false;
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathLocator));
			List<WebElement> tooltips_Title = driver.findElements(xpathLocator);
			for (WebElement ele : tooltips_Title) {
				if (ele.isDisplayed() || ele.isEnabled()) {
					TitleValues = ele.getText();
					if (TitleValues != null && TitleValues.equalsIgnoreCase(expectedValues.trim())) {
						isMatched = true;
						System.out.println("Actual value: " + TitleValues + " and expected value: " + expectedValues);
						break;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
				"Element text not matched with the expected text");

		return isMatched;
	}

	public boolean getElementTextAndCompareIfLocatorHaveMulitpleOption(WebDriver driver, By xpathLocator,
			String expectedValues, int waitTime) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		String TitleValues = null;
		boolean isMatched = false;
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathLocator));
			List<WebElement> tooltips_Title = driver.findElements(xpathLocator);
			for (WebElement ele : tooltips_Title) {
				if (ele.isDisplayed() || ele.isEnabled()) {
					TitleValues = ele.getText();
					if (TitleValues != null && TitleValues.equalsIgnoreCase(expectedValues.trim())) {
						isMatched = true;
						System.out.println("Actual value: " + TitleValues + " and expected value: " + expectedValues);
						break;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
				"Element text not matched with the expected text");

		return isMatched;
	}

	public boolean getElementTextAndCompareIfLocatorHaveMulitpleOption(WebDriver driver, By xpathLocator,
			String expectedValues, int waitTime, boolean wantAddAssertion) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		String TitleValues = null;
		boolean isMatched = false;
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathLocator));
			List<WebElement> tooltips_Title = driver.findElements(xpathLocator);
			for (WebElement ele : tooltips_Title) {
				if (ele.isDisplayed() || ele.isEnabled()) {
					TitleValues = ele.getText();
					if (TitleValues != null && TitleValues.equalsIgnoreCase(expectedValues.trim())) {
						isMatched = true;
						System.out.println("Actual value: " + TitleValues + " and expected value: " + expectedValues);
						break;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		if (wantAddAssertion) {
			TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
					"Element text not matched with the expected text");
		}

		return isMatched;
	}

	public boolean getElementTextAndCheckPresenceListExpectedValues(WebDriver driver, By xpathLocator,
			List<String> expectedValues) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		boolean isMatched = false;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
			WebElement tooltips_Title = driver.findElement(xpathLocator);
			String TitleValues = tooltips_Title.getText().trim();
			expectedValues.contains(TitleValues);
			TestBase.getSoftAssert().assertTrue(expectedValues.contains(TitleValues),
					"Element text is not present in the expected list");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return isMatched;
	}

	public String getElementTextAndCompare(WebDriver driver, By locator, String expectedValues, int waitTimeInSeconds)
			throws InterruptedException {

		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		try {
			WebElement tooltips_Title = wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
			TitleValues = tooltips_Title.getText().trim();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		System.out.println("TitleValues: " + TitleValues);
		TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
				"Element text not matched with the expected text");
		return TitleValues;
	}

	public String getElementTextAndCompare(WebDriver driver, String xpathLocator, String expectedValues)
			throws InterruptedException {

		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			WebElement tooltips_Title = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
			TitleValues = tooltips_Title.getText().trim();
			TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
					"Element text not matched with the expected text");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		//
		return TitleValues;
	}

	public Boolean getElementTextAndCompare(WebDriver driver, String xpathLocator, int waitTimeInSeconds)
			throws InterruptedException {

		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		try {
			return wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator))).isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getStackTrace());
		}
		return false;
	}

	public boolean selectElementFromList(WebDriver driver, By listElementAddress, String elementValue)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(listElementAddress));

		List<WebElement> listElement = driver.findElements(listElementAddress);

		boolean isElementSelected = false;
		boolean isElementPresentInList = false;

		for (WebElement item : listElement) {

			String actualName = item.getText().trim();

			if (actualName.equals(elementValue.trim())) {
				System.out.println("Matched element in list with user data: " + actualName);
				//To scroll the element till not visible
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				isElementPresentInList = true;
				try {
					item.click();
					isElementSelected = true;
					return true;
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}

		}
		TestBase.getSoftAssert().assertEquals(isElementPresentInList, isElementSelected,
				"Given Element: " + elementValue + " is not present in the list or not selected");

		return false;
	}

}