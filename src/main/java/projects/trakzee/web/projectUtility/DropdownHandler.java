package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class DropdownHandler {
	private static SoftAssert softAssert = TestBase.getSoftAssert();
	public static final Logger logger = LogManager.getLogger("logger");
	public static final Logger reportLogger = LogManager.getLogger("reportLogger");
	public static Actions action;
	public static WebDriverWait wait;
	public static JavascriptExecutor jsExecutor;

	public static boolean selectElementFromList(WebDriver driver, By listElementAddress, String elementName) {

		List<WebElement> listElement = driver.findElements(listElementAddress);

		for (WebElement item : listElement) {

			String actualName = item.getText().trim();
			if (actualName.equals(elementName)) {
				try {
					item.click();
					System.out.println("Selected element: " + actualName);
					return true;
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}
		return false;
	}

	public static String selectElementFromListWithStringReturn(WebDriver driver, By listElementAddress,
			String elementName) {

		List<WebElement> listElement = driver.findElements(listElementAddress);

		for (WebElement item : listElement) {

			String actualName = item.getText().trim();
			if (actualName.equalsIgnoreCase(elementName.trim())) {
				try {
					item.click();
					System.out.println("Selected element: " + actualName);
					return actualName;
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}
		return null;
	}

	public static void veriryThenClickAndSearchAndSelectIfNotAlreadySelected(WebDriver driver, String elementLableName,
			By xpathDropdown, By xpathSearchableField, By xpathListOfElements, String expectedSearchableValue,
			By alreadySelectedElementLocator) throws InterruptedException {
		boolean isOptionSelected = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement alreadySelectedElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(alreadySelectedElementLocator));
			String selectedText = alreadySelectedElement.getText();
			if (selectedText.trim().equalsIgnoreCase(expectedSearchableValue.trim())) {
				System.out.println("Element " + elementLableName + " is already selected: " + expectedSearchableValue);
				isOptionSelected = true;
				return;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLableName);
			return;
		}

		try {
			// Step 1: Click on the dropdown element
			WebElement dropdownElement = driver.findElement(xpathDropdown);
			dropdownElement.click();
			System.out.println("Dropdown clicked successfully:" + elementLableName);

			// Step 2: Enter the value in the search input field
			WebElement searchField = driver.findElement(xpathSearchableField);
			searchField.sendKeys(Keys.CONTROL, "a");
			searchField.sendKeys(Keys.DELETE);
			searchField.sendKeys(expectedSearchableValue);
			System.out.println("Entered value in the search field: " + expectedSearchableValue);

			// Step 3: Find all options in the dropdown list
			List<WebElement> options = driver.findElements(xpathListOfElements);

			//to click on the dropdown button if the return not matched after search
			if (options.size() == 0) {
				softAssert.assertTrue(false,
						"Expected value not present in the list after searched: " + expectedSearchableValue);
				dropdownElement.click();
				return;
			}

			for (WebElement option : options) {
				String optionText = option.getText().trim();
				// Match the desired value and click
				if (optionText.equalsIgnoreCase(expectedSearchableValue)) {
					option.click();
					System.out.println("Selected option for " + elementLableName + " is : " + expectedSearchableValue);
					isOptionSelected = true;
					break;
				}
			}
			


			if (!isOptionSelected) {
				System.out
						.println("No matching option found for " + elementLableName + " : " + expectedSearchableValue);
			}

		} catch (

		NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
		softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLableName);

	}

	public static boolean veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(WebDriver driver,
			String elementLableName, By xpathDropdown, By xpathSearchableField, By xpathListOfElements,
			String expectedSearchableValue, By alreadySelectedElementLocator)
			throws InterruptedException {
		boolean isOptionSelected = false;
		WebDriverWait wait = new WebDriverWait(driver, 5);

		//to check is it already selected
		try {
			WebElement alreadySelectedElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(alreadySelectedElementLocator));
			String selectedText = alreadySelectedElement.getText();
			if (selectedText.trim().equalsIgnoreCase(expectedSearchableValue.trim())) {
				System.out.println("Element " + elementLableName + " is already selected: " + selectedText);
				isOptionSelected = true;
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception e: " + e.getMessage());
		}

		try {
			// Step 1: Click on the dropdown element
			WebElement dropdownElement = driver.findElement(xpathDropdown);
			dropdownElement.click();
			System.out.println("Dropdown clicked successfully:" + elementLableName);

			// Step 2: Enter the value in the search input field
			WebElement searchField = driver.findElement(xpathSearchableField);
			searchField.sendKeys(Keys.CONTROL, "a");
			searchField.sendKeys(Keys.DELETE);
			searchField.sendKeys(expectedSearchableValue);
			System.out.println("Entered value in the search field: " + expectedSearchableValue);

			//to check it is present in the list or not
			List<WebElement> options = driver.findElements(xpathListOfElements);
			boolean isNoResultMatched = true;

			if (options.size() != 0) {
				isNoResultMatched = false;
				for (WebElement option : options) {
					String optionText = option.getText().trim();
					System.out.println("Checking option: " + optionText);

					// Match the desired value and click
					if (optionText.equalsIgnoreCase(expectedSearchableValue)) {
						option.click();
						System.out.println("Selected option: " + expectedSearchableValue);
						isOptionSelected = true;
						break;
					}

				}
			}
			if (isNoResultMatched) {
				System.out.println("After search in the search box get No result matched");
				dropdownElement.click();
				softAssert.assertTrue(false, "Option not present in the list: " + expectedSearchableValue);
				return false;
			}

			if (!isOptionSelected) {
				System.out.println("No matching option found for: " + expectedSearchableValue);
			}

		} catch (

		NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
		softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLableName);
		return isOptionSelected;
	}

	/*This is used when the dropdown have this feature: Click | search | Select | Present option selected with checkbox with multi selection, 
	 * When the on click enter all selected items un selected and on click enter again it select all checkboxes,
	 * And if more then on selected than on twice click enter button it un select all*/
	public static boolean veriryThenClickAndSearchAndCheckPresenceAndSelectIfNotAlreadySelected(WebDriver driver,
			String elementLableName, By xpathDropdown, By xpathSearchableField, By xpathListOfElements,
			String expectedSearchableValue, By alreadySelectedElementLocator,
			By AllOptionCheckBoxLocator) throws InterruptedException {
		boolean isOptionSelected = false;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		//to check is it already selected
		String selectedText= null;
		WebElement alreadySelectedElement = null;
		try {
			alreadySelectedElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(alreadySelectedElementLocator));
			selectedText = alreadySelectedElement.getText();
			if (selectedText.trim().equalsIgnoreCase(expectedSearchableValue.trim())) {
				System.out.println("Element " + elementLableName + " is already selected: " + selectedText);
				isOptionSelected = true;
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception e: " + e.getMessage());
		}

		

		try {
			// Step 1: Click on the dropdown element
			WebElement dropdownElement = driver.findElement(xpathDropdown);
			System.out.println("Element: dropdownElement: " + dropdownElement);
			dropdownElement.click();

			System.out.println("Dropdown clicked successfully:" + elementLableName);

			//to check #All check box already selected
			boolean isSelected = driver.findElements(AllOptionCheckBoxLocator)
					.size() > 0;
			if (isSelected) {
				//if selected then click on it to clear all selection
				driver.findElement(AllOptionCheckBoxLocator).click();
			} else {
				driver.findElement(AllOptionCheckBoxLocator).click();
				Thread.sleep(200);
				driver.findElement(AllOptionCheckBoxLocator).click();
				Thread.sleep(200);
			}

			// Step 2: Enter the value in the search input field
			WebElement searchField = driver.findElement(xpathSearchableField);
			searchField.sendKeys(Keys.ENTER);
			Thread.sleep(300);
			searchField.sendKeys(expectedSearchableValue);
			System.out.println("Entered value in the search field: " + expectedSearchableValue);

			//to check it is present in the list or not
			List<WebElement> options = driver.findElements(xpathListOfElements);
			boolean isNoResultMatched = true;

			if (options.size() != 0) {
				isNoResultMatched = false;
				for (WebElement option : options) {
					String optionText = option.getText().trim();
					System.out.println("Checking option: " + optionText);

					// Match the desired value and click
					if (optionText.equalsIgnoreCase(expectedSearchableValue)) {
						option.click();
						System.out.println("Selected option: " + expectedSearchableValue);
						isOptionSelected = true;
						break;
					}

				}
			}
			if (isNoResultMatched) {
				System.out.println("After search in the search box get No result matched");
				dropdownElement.click();
				softAssert.assertTrue(false, "Option not present in the list: " + expectedSearchableValue);
				return false;
			}

			if (!isOptionSelected) {
				System.out.println("No matching option found for: " + expectedSearchableValue);
			}

		} catch (

		NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
		softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLableName);
		return isOptionSelected;
	}

	public static void clickAndSearchAndSelect(WebDriver driver, String elementLableName, By xpathDropdown,
			By xpathSearchableField, By xpathListOfElements, String expectedSearchableValue)
			throws InterruptedException {
		boolean isOptionSelected = false;
		WebDriverWait wait = new WebDriverWait(driver, 5);

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpathDropdown));
			// Step 1: Click on the dropdown element
			WebElement dropdownElement = driver.findElement(xpathDropdown);
			dropdownElement.click();
			System.out.println("Dropdown clicked successfully:" + elementLableName);

			// Step 2: Enter the value in the search input field
			WebElement searchField = driver.findElement(xpathSearchableField);
			searchField.sendKeys(Keys.CONTROL, "a");
			searchField.sendKeys(Keys.DELETE);
			searchField.sendKeys(expectedSearchableValue);
			System.out.println("Entered value in the search field: " + expectedSearchableValue);

			// Step 3: Find all options in the dropdown list
			List<WebElement> options = driver.findElements(xpathListOfElements);

			for (WebElement option : options) {
				String optionText = option.getText().trim();
				System.out.println("Checking option: " + optionText);

				// Match the desired value and click
				if (optionText.equalsIgnoreCase(expectedSearchableValue)) {
					option.click();
					System.out.println("Selected option: " + expectedSearchableValue);
					isOptionSelected = true;
					break;
				}
			}

			if (!isOptionSelected) {
				System.out.println("No matching option found for: " + expectedSearchableValue);
			}

		} catch (

		NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
		softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLableName);

	}

	public static void clickAndSearchAndSelectRandomlyWithinList(WebDriver driver, String elementLabelName,
			By xpathDropdown, By xpathSearchableField, By xpathListOfElements, String expectedSearchableValue) {
		boolean isOptionSelected = false;

		try {
			// Step 1: Click on the dropdown element
			WebElement dropdownElement = driver.findElement(xpathDropdown);
			dropdownElement.click();
			System.out.println("Dropdown clicked successfully: " + elementLabelName);

			// Step 2: Enter the value in the search input field
			WebElement searchField = driver.findElement(xpathSearchableField);
			searchField.sendKeys(Keys.CONTROL, "a");
			searchField.sendKeys(Keys.DELETE);
			searchField.sendKeys(expectedSearchableValue);
			System.out.println("Entered value in the search field: " + expectedSearchableValue);

			// Step 3: Find all options in the dropdown list
			List<WebElement> options = driver.findElements(xpathListOfElements);

			// Step 4: If no matching option is found, select an option randomly
			if (!isOptionSelected && !options.isEmpty()) {
				Random random = new Random();
				int randomIndex = random.nextInt(options.size());
				WebElement randomOption = options.get(randomIndex);
				String randomOptionText = randomOption.getText().trim();
				randomOption.click();
				System.out.println("No matching option found. Randomly selected option: " + randomOptionText);
				isOptionSelected = true;
			}

			if (!isOptionSelected) {
				System.out.println("No options available to select from: " + elementLabelName);
			}

		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}

		softAssert.assertTrue(isOptionSelected, "Option not selected from: " + elementLabelName);

	}

	public static String selectDropdownValueBySelectClass(WebDriver driver, String selectTagXPath,
			String expectedValue) {

		String selectedOption = null;
		SoftAssert softAssert = new SoftAssert();
		String selectedValue = null;
		List<String> optionAvailable = new ArrayList<>();
		try {
			// Wait for the <select> element to be visible
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectTagXPath)));

			// Locate the <select> element
			WebElement selectElement = driver.findElement(By.xpath(selectTagXPath));
			System.out.println("Dropdown element located with XPath: " + selectTagXPath);

			// Create a Select instance
			Select dropdown = new Select(selectElement);

			// Log available options for debugging
			List<WebElement> options = dropdown.getOptions();
			for (WebElement ele : options) {
				optionAvailable.add(ele.getText());
			}
			System.out.println("Available options: " + optionAvailable);
			if (!optionAvailable.contains(expectedValue)) {
				softAssert.assertTrue(false, "Expected value not present in the list");
				return null;
			}
			dropdown.selectByVisibleText(expectedValue.trim());

			WebElement selectedElement = driver.findElement(By.xpath(selectTagXPath));
			Select selecteddropdown = new Select(selectedElement);
			selectedValue = selecteddropdown.getFirstSelectedOption().getText();

		} catch (NoSuchElementException e) {
			logger.error("Dropdown value not found: " + expectedValue);
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
		}
		softAssert.assertEquals(selectedValue.trim(), expectedValue.trim(), "Expected and actual value not matched");

		return selectedValue;
	}

	public static void clickAndSelectDropdownValueBySelectClass(WebDriver driver, String elementLocatorForClick,
			String selectTagXPath, String expectedValue) {
		SoftAssert softAssert = new SoftAssert();
		Select dropdown = null;
		try {
			// Click on the dropdown to open it
			WebElement dropdownClickElement = driver.findElement(By.xpath(elementLocatorForClick));
			dropdownClickElement.click();

			// Locate the <select> element
			WebElement selectElement = driver.findElement(By.xpath(selectTagXPath));
			System.out.println("Dropdown element located with XPath: " + selectTagXPath);

			// Create a Select instance
			dropdown = new Select(selectElement);

			dropdown.selectByVisibleText(expectedValue);

			System.out.println("Value selected from dropdown: " + expectedValue);
		} catch (NoSuchElementException e) {
			softAssert.fail("Expected value not found: " + expectedValue);

		} catch (Exception e) {
			softAssert.fail("An unexpected error occurred: " + e.getMessage());
		}

		// Assert all to capture any failures

	}

	public static void clickAndSelectDropdownValueBySelectClass(WebDriver driver, By elementLocatorForClick,
			By selectTagXPath, String expectedValue) {
		SoftAssert softAssert = new SoftAssert();

		try {
			// Click on the dropdown to open it (only if necessary)
			WebElement dropdownClickElement = driver.findElement(elementLocatorForClick);
			if (dropdownClickElement.isDisplayed() && dropdownClickElement.isEnabled()) {
				dropdownClickElement.click();
			}

			// Locate the <select> element
			WebElement selectElement = driver.findElement(selectTagXPath);
			System.out.println("Dropdown element located with XPath: " + selectTagXPath);

			// Create a Select instance and select value
			Select dropdown = new Select(selectElement);
			dropdown.selectByVisibleText(expectedValue);
			System.out.println("Value selected from dropdown: " + expectedValue);

		} catch (NoSuchElementException e) {
			softAssert.fail("Dropdown element or expected value not found: " + expectedValue);

		} catch (ElementNotInteractableException e) {
			softAssert.fail("Dropdown is not interactable: " + e.getMessage());

		} catch (Exception e) {
			softAssert.fail("An unexpected error occurred: " + e.getMessage());
		}

		// Assert all to capture any failures
		softAssert.assertAll();
	}

	public static void selectDropdownValueBySelectClass(WebDriver driver, By selectTagXPath, String expectedValue) {
		SoftAssert softAssert = new SoftAssert();

		try {
			// Locate the <select> element directly
			WebElement selectElement = driver.findElement(selectTagXPath);
			System.out.println("Dropdown element located with XPath: " + selectTagXPath);

			// Create a Select instance and select value
			Select dropdown = new Select(selectElement);
			dropdown.selectByVisibleText(expectedValue);
			System.out.println("Value selected from dropdown: " + expectedValue);

		} catch (NoSuchElementException e) {
			softAssert.fail("Dropdown element or expected value not found: " + expectedValue);

		} catch (ElementNotInteractableException e) {
			softAssert.fail("Dropdown is not interactable: " + e.getMessage());

		} catch (Exception e) {
			softAssert.fail("An unexpected error occurred: " + e.getMessage());
		}

		// Assert all to capture any failures
		softAssert.assertAll();
	}

	public static String clickAndSearchAndSelectDropdownValueBySelectClass(WebDriver driver, By dropdownLocatorPath,
			By searchboxLocatorPath, By selectTagLocatorPath, String expectedValue) {

		String selectedOption = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			// Click the dropdown to open options
			wait.until(ExpectedConditions.elementToBeClickable(dropdownLocatorPath)).click();

			// Enter the search text
			WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchboxLocatorPath));
			searchBox.clear();
			searchBox.sendKeys(expectedValue);

			// Locate the <select> element and wait for visibility
			WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selectTagLocatorPath));
			Select dropdown = new Select(selectElement);

			// Select the option by visible text
			dropdown.selectByVisibleText(expectedValue.trim());

		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			throw new RuntimeException("Failed to select the dropdown value", e);
		}
		return selectedOption;
	}

}
