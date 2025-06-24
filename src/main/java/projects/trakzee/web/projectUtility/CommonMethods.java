package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.configurations.common.IframesOfApplication;

public class CommonMethods {
	private static SoftAssert softAssert = TestBase.getSoftAssert();
	private final WebDriver driver;
	private static WebDriverWait wait = null;
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		this.iframe = new IframesOfApplication(driver);
	}


	private static final Logger logger = LogManager.getLogger(CommonMethods.class);
	private final IframesOfApplication iframe;
	private static final String[] FIRST_NAMES = { "John", "Jane", "Alex", "Emily", "Michael", "Sarah", "David",
			"Sophia" };
	private static final String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
			"Davis" };

	private static final Random RANDOM = new Random();


	public void clickOnAnyButtonWithNameAndXpath(String buttonName, String xpath) {
		Logger logger = LogManager.getLogger(CommonMethods.class);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			logger.info("Clicked on button: {}", buttonName);
		} catch (TimeoutException e) {
			logger.error("Button not clickable: {}", buttonName, e);
		} catch (NoSuchElementException e) {
			logger.error("Element not found for button: {}", buttonName, e);
		} catch (Exception e) {
			logger.error("Error clicking button: {}", buttonName, e);
		}
	}

	public static boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String elementLocator,
			String elementName, String replaceElementValue) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		boolean isClickedOnButton = false;
		try {
			String dynamicLocator;
			if (elementLocator.contains("YourText")) {
				dynamicLocator = elementLocator.toString().replace("YourText", replaceElementValue);
			} else {
				dynamicLocator = String.format(elementLocator, replaceElementValue);
			}

			WebElement element = driver.findElement(By.xpath(dynamicLocator));
			System.out.println("Element name and xpath >> " + elementName + " and >> " + dynamicLocator);
			ScrollAction.scrollElementTillNotVisible(driver, element);
			element.click();

			isClickedOnButton = true;
			System.out.println("clicke on button >> " + elementName);

		} catch (Exception e) {
			System.out.println("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
		}
		return isClickedOnButton;
	}

	public static void scrollToElementToView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
	}

	public void verifyElementText(String xpath, String expectedText, String description) {
		try {
			iframe.switchToTitleFrame();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String actualText = driver.findElement(By.xpath(xpath)).getText().trim();
			if (actualText.equals(expectedText)) {
				logger.info("Verification successful for: {}", description);
			} else {
				logger.warn("Verification failed for: {}. Expected: {}, but found: {}", description, expectedText,
						actualText);
			}
			SoftAssert soft = new SoftAssert();
			soft.assertEquals(actualText, expectedText, "Header text mismatch for: " + description);
			soft.assertAll(); // Assert all soft assertions
		} catch (Exception e) {
			logger.error("Error verifying element text for: {}", description, e);
		}
	}

	public boolean verifyElementText(By xpath, String expectedText) {
		try {
			iframe.switchToTitleFrame();
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			String actualText = driver.findElement(xpath).getText().trim();
			if (actualText.equalsIgnoreCase(expectedText)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Error verifying element text for: {}", e);
		}
		return false;
	}

	public void selectFromDropdown(By dropdownLocator, By optionLocator) {
		try {
			WebElement dropdown = new WebDriverWait(driver, 5)
					.until(ExpectedConditions.elementToBeClickable(dropdownLocator));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

			dropdown.click();
			logger.info("Opened dropdown: {}", dropdownLocator);

			WebElement option = new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);

			if (option.isDisplayed() && option.isEnabled()) {
				option.click();
				logger.info("Selected option: {} from dropdown: {}", optionLocator, dropdownLocator);
			} else {
				logger.error("Option '{}' is not clickable or not enabled: {}", optionLocator, dropdownLocator);
			}

		} catch (TimeoutException e) {
			logger.error("Timeout while selecting option: {} from {}", optionLocator, dropdownLocator, e);
			System.out.println(driver.getPageSource());
		} catch (Exception e) {
			logger.error("Unexpected error occurred while selecting option.", e);
		}
	}

	public void selectFromSelectDropdown(By dropdownLocator, String optionText) {
		try {
			WebElement dropdown = driver.findElement(dropdownLocator);
			Select select = new Select(dropdown);
			select.selectByVisibleText(optionText);
			logger.info("Selected option: {} from {}", optionText, dropdownLocator);
		} catch (NoSuchElementException e) {
			logger.error("Option '{}' not found in dropdown: {}", optionText, dropdownLocator, e);
		} catch (Exception e) {
			logger.error("Unexpected error occurred while selecting option.", e);
		}
	}

	public void selectRadioButton(String radioButtonXPath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(radioButtonXPath)));
			radioButton.click();
			logger.info("Selected radio button: {}", radioButtonXPath);
		} catch (TimeoutException e) {
			logger.error("Error selecting radio button: {}", radioButtonXPath, e);
			System.out.println(driver.getPageSource());
		} catch (Exception e) {
			logger.error("Unexpected error selecting radio button.", e);
		}
	}

	public static void selectRadioButton(WebDriver driver, String elementLabelName, By xpathLocator) {
		SoftAssert softAssert = new SoftAssert();
		try {
			WebElement radioButton = driver.findElement(xpathLocator);

			// Check if the radio button has "enabled" and "displayed" attributes
			if (radioButton.isDisplayed() && radioButton.isEnabled()) {
				// Check if the radio button is already selected
				if (!radioButton.isSelected()) {
					radioButton.click();
					System.out.println("Radio button selected successfully: " + elementLabelName);
				} else {
					System.out.println("Radio button is already selected: " + elementLabelName);
				}
			} else {
				// If the radio button is not displayed or enabled, click it directly
				try {
					radioButton.click();
					System.out.println(
							"Radio button clicked directly as it is not displayed/enabled: " + elementLabelName);
				} catch (Exception e) {
					softAssert.assertTrue(false, "Failed to click the radio button directly: " + elementLabelName);
					System.out
							.println("Exception occurred while clicking the radio button directly: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.info("Exception occurred while handling the radio button: " + e.getMessage());
			softAssert.assertTrue(false, "Exception occurred for radio button: " + elementLabelName);
		}

		// Assert all collected assertions

	}

	/**
	 * Generates a random 10-digit mobile number.
	 * 
	 * @return Random 10-digit mobile number as a String.
	 */
	public static String generateRandomMobileNumber() {
		StringBuilder mobileNumber = new StringBuilder("9");
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(RANDOM.nextInt(10));
		}
		return mobileNumber.toString();
	}

	/**
	 * Generates a random 4-digit number.
	 * 
	 * @return Random 4-digit number as a String.
	 */
	public static String generateRandomNumber() {
		StringBuilder mobileNumber = new StringBuilder("3");
		for (int i = 0; i < 3; i++) {
			mobileNumber.append(RANDOM.nextInt(9));
		}
		return mobileNumber.toString();
	}

	/**
	 * Generates a random email ID using @gmail.com domain.
	 * 
	 * @return Random email ID as a String.
	 */
	public static String generateRandomEmail() {
		String emailPrefix = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)].toLowerCase()
				+ LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)].toLowerCase() + RANDOM.nextInt(1000);
		return emailPrefix + "@gmail.com";
	}

	/**
	 * Generates a random first name.
	 * 
	 * @return Random first name as a String.
	 */
	public static String generateRandomFirstName() {
		return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
	}

	/**
	 * Generates a random last name.
	 * 
	 * @return Random last name as a String.
	 */
	public static String generateRandomLastName() {
		return LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
	}

	/**
	 * Verifies if the text of an element matches the expected text.
	 *
	 * @param xpathTemplate The XPath of the element with '%s' placeholder.
	 * @param expectedText  The expected text to match.
	 * @param message       Custom message for assertion failure.
	 */
	public void assertText(String xpathTemplate, String expectedText, String message) {
		String xpath = String.format(xpathTemplate, expectedText);
		WebElement element = driver.findElement(By.xpath(xpath));
		String actualText = element.getText();
		String normalizedActualText = actualText.replaceAll("\\s+", "").toLowerCase();
		String normalizedExpectedText = expectedText.replaceAll("\\s+", "").toLowerCase();
		Assert.assertEquals(normalizedActualText, normalizedExpectedText, message);
	}

	/**
	 * Double-clicks on an element located by the given locator.
	 *
	 * @param locator The locator of the element to double-click.
	 */
	public void doubleClick(By locator) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	/**
	 * Double-clicks on a specific WebElement.
	 *
	 * @param element The WebElement to double-click.
	 */
	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept(); // Clicks OK
	}

	public void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss(); // Clicks Cancel
	}

	public boolean isUserDeleted(String userName) {
		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(), '" + userName + "')]"));
		return elements.isEmpty();
	}

	public boolean isTextPresentInTable(String text) {
		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(), '" + text + "')]"));
		return !elements.isEmpty(); // Returns true if text is found, false otherwise
	}

	public void selectTab(String tabName, String xpathTemplate) {
		try {
			iframe.switchToContentFrame();
			String dynamicTabLocator = String.format(xpathTemplate, tabName);
			WebElement tabElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicTabLocator)));
			wait.until(ExpectedConditions.elementToBeClickable(tabElement));
			tabElement.click();
			logger.info("Successfully selected tab: {}", tabName);
		} catch (TimeoutException e) {
			logger.error("Tab not clickable within timeout: {}", tabName, e);
		} catch (NoSuchElementException e) {
			logger.error("Tab not found: {}", tabName, e);
		} catch (Exception e) {
			logger.error("Error occurred while selecting tab: {}", tabName, e);
		}
	}

	public void selectWorkingDays(String... daysToSelect) {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[contains(@id,'working_day')]"));
		String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		if (checkboxes.size() > 7) {
			checkboxes = checkboxes.subList(0, 7); // Take only the first 7 checkboxes
		}
		if (checkboxes.size() != daysOfWeek.length) {
			throw new IllegalStateException("Number of checkboxes does not match the number of days in the week.");
		}
		for (int i = 0; i < checkboxes.size(); i++) {
			for (String day : daysToSelect) {
				if (daysOfWeek[i].equalsIgnoreCase(day)) {
					WebElement checkbox = checkboxes.get(i);
					System.out.println("Selecting checkbox for " + daysOfWeek[i]);
					checkCheckbox(checkbox);
					break;
				}
			}
		}
	}

	public static void checkCheckbox(WebElement checkboxElement) {
		if (!checkboxElement.isSelected()) {
			checkboxElement.click();
		}
	}

	public static void uncheckCheckbox(WebElement checkboxElement) {
		if (checkboxElement.isSelected()) {
			checkboxElement.click();
		}
	}

	public static String pickRandomValueFromList(WebDriver driver, By listLocator) {
		try {
			// Find the root element of the list
			WebElement rootElement = driver.findElement(listLocator);

			// Check if the root element is a <select> tag
			if (rootElement.getTagName().equalsIgnoreCase("select")) {
				// Handle <select> tag using the Select class
				Select dropdown = new Select(rootElement);

				// Get all options from the dropdown
				List<WebElement> options = dropdown.getOptions();

				// Check if the dropdown has options

				if (options.isEmpty()) {
					throw new RuntimeException(
							"The dropdown is empty. No options found for the locator: " + listLocator);
				}

				// Generate a random index
				Random random = new Random();
				int randomIndex = random.nextInt(options.size());

				// Select the option at the random index
				WebElement selectedOption = options.get(randomIndex);
				String selectedValue = selectedOption.getText();

				// Select the option in the dropdown
				dropdown.selectByIndex(randomIndex);

				// Log the selected value (optional)
				System.out.println("Randomly selected value from select tag dropdown: " + selectedValue);

				return selectedValue;
			} else {
				// Handle regular lists (non-<select> elements)
				List<WebElement> elements = driver.findElements(listLocator);

				if (elements.isEmpty()) {
					throw new RuntimeException("The list is empty. No elements found for the locator: " + listLocator);
				}

				// Generate a random index
				Random random = new Random();
				int randomIndex = random.nextInt(elements.size());

				// Get the text of the randomly selected element
				// Click the randomly selected element
				String selectedValue = elements.get(randomIndex).getText();
				elements.get(randomIndex).click();

				System.out.println("Randomly selected value: " + selectedValue);

				return selectedValue;
			}
		} catch (Exception e) {
			// Handle any exceptions
			System.err.println("Error while picking a random value: " + e.getMessage());
			return null;
		}
	}

	public static String pickRandomValueFromList(WebDriver driver, By listLocator, String expectedValue) {
		try {
			if (expectedValue.isEmpty()) {
				System.out.println("expecetdValue: " + expectedValue);
				return "";
			}
			// Find the root element of the list
			WebElement rootElement = driver.findElement(listLocator);

			// Check if the root element is a <select> tag
			if (rootElement.getTagName().equalsIgnoreCase("select")) {
				System.out.println("Locator has select tag: " + listLocator);

				// Find the select element using the locator
				WebElement selectElement = driver.findElement(listLocator);

				// Handle <select> tag using the Select class
				Select dropdown = new Select(selectElement);

				// Get all options from the dropdown
				List<WebElement> options = dropdown.getOptions();

				// Check if the dropdown has options
				if (options.isEmpty()) {
					throw new RuntimeException(
							"The dropdown is empty. No options found for the locator: " + listLocator);
				}

				// Select the option in the dropdown
				dropdown.selectByVisibleText(expectedValue);
				return expectedValue;

			} else {
				// Handle regular lists (non-<select> elements)
				List<WebElement> elements = driver.findElements(listLocator);

				if (elements.isEmpty()) {
					throw new RuntimeException("The list is empty. No elements found for the locator: " + listLocator);
				}

				for (WebElement ele : elements) {
					String text = ele.getText().trim();
					if (text.equalsIgnoreCase(expectedValue.trim())) {
						ele.click();
						System.out.println("selected value: " + text);
						return text;
					}
				}
			}
		} catch (Exception e) {
			// Handle any exceptions
			TestBase.getSoftAssert().fail("Expected option not present in the list: " + expectedValue);
			System.err.println("Error while picking a random value: " + e.getMessage());
		}
		return null;
	}

	private static Actions actions = null;

	public void dragAndDropElementInDifferentPlane(WebDriver driver, By xpath_SOURCE_ELEMENT, By xpath_TARGET_ELEMENT) {
		actions = new Actions(driver);
		try {
			WebElement sourceElement = driver.findElement(xpath_SOURCE_ELEMENT);
			WebElement targetElement = driver.findElement(xpath_TARGET_ELEMENT);
			actions = new Actions(driver);
			actions.dragAndDrop(sourceElement, targetElement).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

	public static void dragAndDropInSamePlane(WebDriver driver, By xpath_ELEMENT, int xOffset, int yOffset) {
		try {
			Thread.sleep(1000);
			WebElement textElement = driver.findElement(xpath_ELEMENT);
			actions = new Actions(driver);
			actions.dragAndDropBy(textElement, xOffset, yOffset).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

	public static void dragAndDropInSamePlane(WebDriver driver, String xpath_ELEMENT, int xOffset, int yOffset) {
		try {
			Thread.sleep(1000);
			WebElement textElement = driver.findElement(By.xpath(xpath_ELEMENT));
			actions = new Actions(driver);
			actions.dragAndDropBy(textElement, xOffset, yOffset).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

	public static double getInvisibilityWaitTime(WebDriver driver, By locator, int maxWaitTimeInSeconds) {
		long startTime = System.currentTimeMillis(); // Record the start time
		WebDriverWait wait = null;
		try {
//			// First check loading icon presence
//			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait for invisibility

			// Then check for invisibility
			wait = new WebDriverWait(driver, maxWaitTimeInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); // Wait for invisibility
		} catch (Exception e) {
			System.out.println("Element did not disappear within " + maxWaitTimeInSeconds + " seconds.");
			e.printStackTrace(); // Log exception details
			return maxWaitTimeInSeconds; // Return max wait time
		}

		long endTime = System.currentTimeMillis(); // Record the end time
		long totalTimeMillis = endTime - startTime; // Calculate total time in milliseconds

		double seconds = totalTimeMillis / 1000.0; // Convert to seconds
		System.out.println("Max loading time in seconds is: " + seconds);

		return seconds; // Return the wait time
	}


	public static boolean isElementDisplayed(WebDriver driver, By elementLocators) {
		boolean isDisplayed = false;
		SoftAssert softAssert = new SoftAssert(); // Ensure SoftAssert is initialized
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5); // Using driver directly
			// Wait for the element to be present and visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocators));

			// Check if the element is displayed
			isDisplayed = element.isDisplayed();
			System.out.println("Element is displayed: " + isDisplayed);
		} catch (Exception e) {
			System.out.println("Is Element displayed: " + isDisplayed + " Locator: " + elementLocators
					+ " - Exception: " + e.getMessage());
		}
		softAssert.assertTrue(isDisplayed, "Element not displayed, ");
		// Ensure all soft assertions are checked
		return isDisplayed;
	}

	public static String getElementText(WebDriver driver, By toolTip_TitleAddress) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		String tooltips_TitleValues = "";
		try {
			System.out.println("Xpath is: " + toolTip_TitleAddress);
			wait.until(ExpectedConditions.presenceOfElementLocated(toolTip_TitleAddress));
			WebElement tooltips_Title = wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip_TitleAddress));
			tooltips_TitleValues = tooltips_Title.getText();
			System.out.println("TextValue: " + tooltips_TitleValues);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return tooltips_TitleValues;
	}

	public static String getElementText(WebDriver driver, By toolTip_TitleAddress, int waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		String tooltips_TitleValues = "";
		try {
			System.out.println("Xpath is: " + toolTip_TitleAddress);
			wait.until(ExpectedConditions.presenceOfElementLocated(toolTip_TitleAddress));
			WebElement tooltips_Title = wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip_TitleAddress));
			tooltips_TitleValues = tooltips_Title.getText();
			System.out.println("TextValue: " + tooltips_TitleValues);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return tooltips_TitleValues;
	}

	public static boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String buttonName, By xpathAddress)
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		// action = new Actions(driver);

		boolean isClickedOnButton = false;
		try {
			System.out.println("Element name and xpath >> " + buttonName + " and >> " + xpathAddress);
			WebElement button = driver.findElement(xpathAddress);
			wait.until(ExpectedConditions.elementToBeClickable(button));
			button.click();
			isClickedOnButton = true;
			System.out.println("clicke on button >> " + buttonName);
		} catch (Exception e) {
			System.out.println("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
		}
		//		softAssert.assertTrue(isClickedOnButton, "User not clicked on the button: " + buttonName);
		//		
		return isClickedOnButton;
	}

	public static void getElementTextAndCompare(WebDriver driver, String xpathLocator, String expectedValues,
			int waitTimeInSeconds) throws InterruptedException {

		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(xpathLocator))));
			WebElement tooltips_Title = driver.findElement(By.xpath(TitleValues));
			TitleValues = tooltips_Title.getText().trim();
			System.out.println("Title: " + TitleValues);
			softAssert.assertEquals(TitleValues, expectedValues, "Element text not matched with the expected text");
		} catch (Exception e) {
			System.out.println("Exception from getElementTextAndCompare: " + e.getMessage());
		}

	}

	public static boolean getElementTextAndCompare(WebDriver driver, By xpathLocator, String expectedValues)
			throws InterruptedException {

		String TitleValues = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement tooltips_Title = null;
		try {
			try {
				tooltips_Title = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
			}catch(Exception e) {
				tooltips_Title = wait.until(ExpectedConditions.presenceOfElementLocated(xpathLocator));
			}

			TitleValues = tooltips_Title.getText().trim();
			System.out.println("Title: " + TitleValues);
			if (TitleValues.equals(expectedValues.trim())) {
				System.out.println("Both value matched expected: " + expectedValues + " and actual: " + TitleValues);
				return true;
			}

		} catch (Exception e) {
			System.out.println("Exception from getElementTextAndCompare: " + e.getMessage());
		}
		TestBase.getSoftAssert().assertEquals(TitleValues, expectedValues,
				"Element text not matched with the expected text");

		return false;
	}

	public static void compareListOfLabelsPresnceOnUIWithExpectedList(WebDriver driver, String expectedLabels,
			By xpathActualLabelOfList) {
		JavascriptExecutor jsExecutor;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpathActualLabelOfList));
		List<WebElement> actualElements = driver.findElements(xpathActualLabelOfList);
		List<String> enabledAndDisplayedList = new ArrayList<>();
		String[] expectedValues = expectedLabels.split(",");
		List<String> expectedValuesList = new ArrayList<String>();
		expectedValuesList = Arrays.asList(expectedValues);

		System.out.println("expectedLabels: " + expectedLabels);
		for (WebElement ele : actualElements) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", ele);
			String[] values = null;
			if (ele.isDisplayed() && ele.isEnabled()) {
				try {
					values = ele.getText().split("\\n");
					System.out.println("Actual Field: " + values[0]);
					enabledAndDisplayedList.add(values[0]);
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}

		softAssert.assertEqualsNoOrder(expectedValuesList.toArray(), enabledAndDisplayedList.toArray(),
				"List not matched while checking without orders");
		softAssert.assertEquals(enabledAndDisplayedList, expectedValuesList,
				"Actaul and expected values Or/And size not matched");


	}

	public static List<String> getVisibleTextFromWebElementList(WebDriver driver, String listElementAddress) {
		List<WebElement> listElement = driver.findElements(By.xpath(listElementAddress));
		List<String> actualList = new ArrayList<>();
		for (WebElement ele : listElement) {
			String actualName = ele.getText().trim();
			if (!actualName.isEmpty() && ele.isDisplayed() && ele.isEnabled()) {
				actualList.add(actualName);
			}
		}
		return actualList;
	}

	public static List<String> getVisibleTextFromWebElementList(WebDriver driver, By listElementAddress) {
		List<WebElement> listElement = driver.findElements(listElementAddress);
		List<String> actualList = new ArrayList<>();
		for (WebElement ele : listElement) {
			String actualName = ele.getText().trim();
			if (!actualName.isEmpty() && ele.isDisplayed() && ele.isEnabled()) {
				actualList.add(actualName);
			}
		}
		return actualList;
	}

	public static List<String> getTextFromRowList(WebDriver driver, String listXpath, int actualValuesColumnIndex) {

		List<WebElement> listItems = driver.findElements(By.xpath(listXpath));
		List<String> acutalList = new ArrayList<String>();
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			String[] values = itemName.split("\\n");
			acutalList.add(values[actualValuesColumnIndex]);
		}

		return acutalList;
	}

	public static boolean verifyValuePresenceInWebElementList(WebDriver driver, By listXpath, String expectedValue) {
		List<WebElement> listItems = driver.findElements(listXpath);
		List<String> acutalList = new ArrayList<String>();
		boolean isPresent = false;
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			acutalList.add(itemName);
			if (itemName.equalsIgnoreCase(expectedValue)) {
				System.out.println("✅ Expeced matched with the actual");
				isPresent = true;
			}
		}
		TestBase.getSoftAssert().assertTrue(isPresent,
				"The value" + expectedValue + " not present in the list: " + acutalList);

		return isPresent ? true : false;
	}

	public static boolean verifyValueAbsentInWebElementList(WebDriver driver, By listXpath, String expectedValue) {
		List<WebElement> listItems = driver.findElements(listXpath);
		List<String> acutalList = new ArrayList<String>();
		boolean isPresent = true;
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			acutalList.add(itemName);
			if (itemName.equalsIgnoreCase(expectedValue)) {
				System.out.println("✅ Expeced absent in list");
				isPresent = false;
			}
		}
		TestBase.getSoftAssert().assertTrue(isPresent,
				"The value: " + expectedValue + " not present in the list: " + acutalList);

		return isPresent ? false : true;
	}

	public static List<String> getTextFromRowList(WebDriver driver, By listXpath, int actualValuesColumnIndex) {

		List<WebElement> listItems = driver.findElements(listXpath);
		List<String> acutalList = new ArrayList<String>();
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			String[] values = itemName.split("\\n");
			acutalList.add(values[actualValuesColumnIndex]);
		}

		return acutalList;
	}

	public static void compareListByText(WebDriver driver, By listXpath, String expectedCommaSeperatedValues) {
		System.out.println("Expected list comma seperated: " + expectedCommaSeperatedValues);
		String[] expectedList = expectedCommaSeperatedValues.split(",");

		String[] updatedList = new String[expectedList.length];
		for (int i = 0; i < expectedList.length; i++) {
			updatedList[i] = expectedList[i].trim();
		}

		List<String> expectedListNew = Arrays.asList(updatedList);
		List<String> actualList = new ArrayList<>();

		wait = new WebDriverWait(driver, 5);
		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			actualList = new ArrayList<String>();
			for (WebElement items : listItems) {
				String itemName = items.getText().toString().trim();
				actualList.add(itemName);
			}
		} catch (Exception e) {
			
		}
		TestBase.getSoftAssert().assertEquals(actualList, expectedListNew, "List not matched and actual list is:\n "
				+ actualList
				+ " \n And expected list is: \n" + expectedListNew);

	}

	public static void compareListByTextByIgnorOrder(WebDriver driver, By listXpath,
			String expectedCommaSeperatedValues) {
		String[] expectedList = expectedCommaSeperatedValues.split(",");
		List<String> expectedListNew = Arrays.asList(expectedList);
		List<String> actualList = new ArrayList<>();

		wait = new WebDriverWait(driver, 5);
		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			actualList = new ArrayList<String>();
			for (WebElement items : listItems) {
				String itemName = items.getText().toString();
				actualList.add(itemName);
			}
		} catch (Exception e) {

		}
		TestBase.getSoftAssert().assertEqualsNoOrder(actualList.toArray(), expectedListNew.toArray(),
				"List not matched. \nActual list: " + actualList + "\nExpected list: " + expectedListNew);
	}

	public static void compareListByTextByRemovingDuplicateFromActualList(WebDriver driver, By listXpath,
			String expectedCommaSeparatedValues) {
		String[] expectedList = expectedCommaSeparatedValues.split(",");
		List<String> expectedListNew = Arrays.asList(expectedList);
		Set<String> actualSet = new LinkedHashSet<>(); // Using LinkedHashSet to remove duplicates while maintaining order

		wait = new WebDriverWait(driver, 5);
		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			for (WebElement item : listItems) {
				String itemName = item.getText().trim(); // Trim to avoid leading/trailing spaces
				actualSet.add(itemName);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching list items: " + e.getMessage());
		}

		List<String> actualList = new ArrayList<>(actualSet); // Convert set back to list for comparison

		TestBase.getSoftAssert().assertEquals(actualList, expectedListNew,
				"List not matched. \nActual list: " + actualList + "\nExpected list: " + expectedListNew);
	}

	public static void compareListByTextByRemovingDuplicateFromActualListIgnorOrder(WebDriver driver, By listXpath,
			String expectedCommaSeparatedValues) {
		String[] expectedList = expectedCommaSeparatedValues.split(",");
		List<String> expectedListNew = Arrays.asList(expectedList);
		Set<String> actualSet = new LinkedHashSet<>(); // Using LinkedHashSet to remove duplicates while maintaining order

		wait = new WebDriverWait(driver, 5);
		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			for (WebElement item : listItems) {
				String itemName = item.getText();
				if (itemName == null || itemName == "") {
					itemName = item.getAttribute("title");
				}
				actualSet.add(itemName);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching list items: " + e.getMessage());
		}

		List<String> actualList = new ArrayList<>(actualSet); // Convert set back to list for comparison

		TestBase.getSoftAssert().assertEqualsNoOrder(actualList.toArray(), expectedListNew.toArray(),
				"List not matched. \nActual list: " + actualList + "\nExpected list: " + expectedListNew);
	}

	public static void compareListByTextByRemovingDuplicateFromActualListIgnorOrderByAttribute(WebDriver driver,
			By listXpath, String expectedCommaSeparatedValues, String attributeName) {
		String[] expectedList = expectedCommaSeparatedValues.split(",");
		List<String> expectedListNew = Arrays.asList(expectedList);
		Set<String> actualSet = new LinkedHashSet<>(); // Using LinkedHashSet to remove duplicates while maintaining order

		wait = new WebDriverWait(driver, 5);
		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			for (WebElement item : listItems) {
				String itemName = item.getAttribute(attributeName).trim(); // Trim to avoid leading/trailing spaces
				actualSet.add(itemName);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching list items: " + e.getMessage());
		}

		List<String> actualList = new ArrayList<>(actualSet); // Convert set back to list for comparison

		TestBase.getSoftAssert().assertEqualsNoOrder(actualList.toArray(), expectedListNew.toArray(),
				"List not matched. \nActual list: " + actualList + "\nExpected list: " + expectedListNew);
	}

	public static void compareListByTextCheckAtualListWithExpectedByRemovingDuplicateFromActualListIgnoreOrder(
			WebDriver driver, By listXpath,
			String expectedCommaSeparatedValues) {

		// Convert expected values into a Set (removes duplicates)
		Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedCommaSeparatedValues.split(",")));

		// Using LinkedHashSet to remove duplicates while maintaining order in actual list
		Set<String> actualSet = new LinkedHashSet<>();

		wait = new WebDriverWait(driver, 5);

		try {
			List<WebElement> listItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listXpath));
			for (WebElement item : listItems) {
				String itemName = item.getText().trim(); // Trim spaces
				if (!itemName.isEmpty()) {
					actualSet.add(itemName); // Add to set (removes duplicates automatically)
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while fetching list items: " + e.getMessage());
		}

		// Convert actualSet back to a List for comparison
		List<String> actualList = new ArrayList<>(actualSet);

		// Check if actualList is a subset of expectedSet (ignoring order & duplicates)
		boolean isSubset = expectedSet.containsAll(actualList);

		TestBase.getSoftAssert().assertTrue(isSubset,
				"Actual list contains unexpected values! \nActual: " + actualList + "\nExpected: " + expectedSet);

		System.out.println("✅ Actual List (after removing duplicates): " + actualList);
		System.out.println("✅ Expected Set: " + expectedSet);
	}


	public boolean getErrorMessage(String errorMessageTitle, By errorMessageLocatorPatt) {
		try {
			// Switch to the iframe where the element resides
			iframe.switchToScheduleScreen();

			// Use WebDriverWait to wait for the element to be present
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocatorPatt));

			// If the element is found, return true
			System.out.println(errorMessageTitle + "present and value is: " + element.getText());
			return element.isDisplayed();
		} catch (TimeoutException e) {
			// If the element is not found within 5 seconds, return false
			return false;
		} catch (Exception e) {
			// Log any unexpected exceptions and return false
			System.err.println("Error while checking error message: " + e.getMessage());
			return false;
		}
	}

	public boolean getErrorMessage(String errorMessageTitle, By errorMessageLocatorPatt, int waitTimeInSeconds) {
		try {
			// Switch to the iframe where the element resides
			iframe.switchToScheduleScreen();

			// Use WebDriverWait to wait for the element to be present
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocatorPatt));

			// If the element is found, return true
			System.out.println(errorMessageTitle + "present and value is: " + element.getText());
			return element.isDisplayed();
		} catch (TimeoutException e) {
			// If the element is not found within 5 seconds, return false
			return false;
		} catch (Exception e) {
			// Log any unexpected exceptions and return false
			System.err.println("Error while checking error message: " + e.getMessage());
			return false;
		}
	}

}