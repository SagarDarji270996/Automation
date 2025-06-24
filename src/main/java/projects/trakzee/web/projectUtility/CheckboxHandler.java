package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class CheckboxHandler {

	private static SoftAssert softAssert = TestBase.getSoftAssert();
	private static WebDriverWait wait;


	public static boolean IscheckboxSelected(WebDriver driver, String xpathCheckbox) {
		wait = new WebDriverWait(driver, 5);
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCheckbox)));
		try {
			// Check if the checkbox is not selected
			if (!checkbox.isSelected()) {
				System.out.println("Checkbox is already selected");
				return true;

			} else {
				softAssert.assertTrue(false, "Checkbox was not selected");
				System.out.println("Checkbox was not selected");
				return false;
			}
		} catch (Exception e) {
			softAssert.assertTrue(false, "Error occurred while interacting with the checkbox: " + e.getMessage());
			// Log the exception if an error occurs while interacting with the checkbox
			System.out.println("Error occurred while interacting with the checkbox: " + e.getMessage());

		}


		return false;
	}

	public static boolean checkCheckboxIfNotSelected(WebDriver driver, String xpathCheckbox) {
		wait = new WebDriverWait(driver, 5);
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCheckbox)));
		boolean isCheckboxIsChecked = false;
		try {
			// Check if the checkbox is not selected
			if (!checkbox.isSelected()) {
				// If not selected, click to check it
				checkbox.click();
				isCheckboxIsChecked = true;
				System.out.println("Checkbox was not selected, now checked.");
			} else {
				System.out.println("Checkbox is already selected");
			}
		} catch (Exception e) {
			// Log the exception if an error occurs while interacting with the checkbox
			System.out.println("Error occurred while interacting with the checkbox: " + e.getMessage());
		}

		softAssert.assertTrue(isCheckboxIsChecked, "Check box not found");
		return isCheckboxIsChecked;
	}

	public static boolean checkCheckboxIfNotSelected(WebDriver driver, String checkBoxName, By xpathCheckbox) {
		wait = new WebDriverWait(driver, 5);
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathCheckbox));
		boolean isCheckboxIsChecked = false;
		try {
			// Check if the checkbox is not selected
			if (!checkbox.isSelected()) {
				// If not selected, click to check it
				checkbox.click();
				isCheckboxIsChecked = true;
				System.out.println("Checkbox was not selected, now checked: " + checkBoxName);
			} else {
				System.out.println("Checkbox is already selected: " + checkBoxName);
			}
		} catch (Exception e) {
			// Log the exception if an error occurs while interacting with the checkbox
			System.out.println("Error occurred while interacting with the checkbox: " + e.getMessage());
			softAssert.assertTrue(isCheckboxIsChecked, "Check box not found: " + checkBoxName);
		}


		return isCheckboxIsChecked;
	}

	public static boolean checkUncheckCheckbox(WebDriver driver, String xpathCheckbox) {
		wait = new WebDriverWait(driver, 5);
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCheckbox)));
		boolean isCheckboxStateChanged = false;

		try {
			// Check if the checkbox is already selected
			if (!checkbox.isSelected()) {
				// If not selected, click to check it
				checkbox.click();
				isCheckboxStateChanged = true;
				System.out.println("Checkbox was not selected, now checked.");
			} else {
				// If already selected, click to uncheck it
				checkbox.click();
				isCheckboxStateChanged = true;
				System.out.println("Checkbox was already selected, now unchecked.");
			}
		} catch (Exception e) {
			// Log the exception if an error occurs while interacting with the checkbox
			System.out.println("Error occurred while interacting with the checkbox: " + e.getMessage());
			softAssert.assertTrue(isCheckboxStateChanged, "Checkbox not found or not clickable.");
		}

		// Assert that the checkbox state has changed (either checked or unchecked)

		return isCheckboxStateChanged;
	}

	public static boolean verifyAndUncheckCheckboxes(WebDriver driver, By listLocator) {
		wait = new WebDriverWait(driver, 5);
		List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listLocator));
		boolean isCheckboxStateChanged = false;

		try {
			// Check if the checkbox is already selected
			for (WebElement checkbox : checkboxes) {
				if (checkbox.isSelected()) {
					checkbox.click();
					isCheckboxStateChanged = true;
				}
			}
		} catch (Exception e) {
			// Log the exception if an error occurs while interacting with the checkbox
			System.out.println("Error occurred while interacting with the checkbox: " + e.getMessage());

		}

		// Assert that the checkbox state has changed (either checked or unchecked)
		softAssert.assertTrue(isCheckboxStateChanged, "Checkbox not found or not clickable.");
		return isCheckboxStateChanged;
	}

	public static boolean verifyAllCheckboxesToggleableInList(WebDriver driver, String xpathCheckBoxList) {
		// Find the checkbox elements using the provided XPath
		List<WebElement> checkboxElements = driver.findElements(By.xpath(xpathCheckBoxList));

		// If no checkboxes are found, return false early
		if (checkboxElements.isEmpty()) {
			System.out.println("No checkboxes found with the provided XPath: " + xpathCheckBoxList);
			softAssert.assertTrue(false, "Checkbox is not enabled or displayed");
			return false;
		}

		for (WebElement checkboxElement : checkboxElements) {
			try {
				// Check if the checkbox is both enabled and displayed
				if (!checkboxElement.isEnabled() || !checkboxElement.isDisplayed()) {
					softAssert.assertTrue(false, "Checkbox is not enabled or displayed");
					return false;
				}

				// Capture the initial checked state of the checkbox
				boolean initialCheckedState = checkboxElement.isSelected();

				// Toggle the checkbox
				checkboxElement.click();

				// Verify that the checkbox state has changed
				if (checkboxElement.isSelected() == initialCheckedState) {
					softAssert.assertTrue(false, "Checkbox state did not change after clicking.");
					return false;
				}

				// Toggle back to the original state
				checkboxElement.click();

				// Confirm that the checkbox has returned to its original state
				if (checkboxElement.isSelected() != initialCheckedState) {
					softAssert.assertTrue(false, "Checkbox state did not revert to original state.");
					return false;
				}

			} catch (Exception e) {
				// Log any exceptions that occur during checkbox interaction
				System.out.println("Exception while interacting with checkbox: " + e.getMessage());
				softAssert.assertTrue(false, "Exception occurred while toggling checkbox.");
				return false;
			}
		}

		// After checking all checkboxes, assert all the conditions in the soft assert

		System.out.println("All checkboxes are toggleable.");
		return true;
	}

	public static boolean verifyCheckboxeToggleable(WebDriver driver, String xpathCheckBox) {
		wait = new WebDriverWait(driver, 5);
		WebElement checkboxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCheckBox)));

		if (!checkboxElement.isEnabled() && !checkboxElement.isDisplayed()) {
			return false;
		}

		// Toggle the checkbox
		boolean initialCheckedState = checkboxElement.isSelected();
		checkboxElement.click();

		// Verify that the checkbox state has changed
		if (checkboxElement.isSelected() == initialCheckedState) {
			return false;
		}

		// Toggle back to the original state
		checkboxElement.click();

		// Confirm it has returned to the original state
		if (checkboxElement.isSelected() != initialCheckedState) {
			return false;
		}

		return true;
	}

	public static boolean checkTwoLevelCheckboxIfNotSelectedSinleSelection(WebDriver driver, String levelOneLabel,
			String xpathLevelOneList, String levelTwoLabel, String xpathLevelTwoList, String checkboxTagName) {

		// Find all the level-one checkboxes (they correspond to groups or categories)
		List<WebElement> levelOneCheckboxes = driver.findElements(By.xpath(xpathLevelOneList));
		boolean isCheckboxChecked = false; // To track if the checkbox was checked

		// Iterate over the level-one checkboxes
		for (WebElement levelOneCheckbox : levelOneCheckboxes) {
			// Find the label associated with the level-one checkbox
			WebElement levelOneLabelElement = null;
			try {
				levelOneLabelElement = levelOneCheckbox
						.findElement(By.xpath(".//following-sibling::" + checkboxTagName));
			} catch (Exception e) {
				levelOneLabelElement = levelOneCheckbox;
			}

			// Compare the extracted label text with the user-provided label
			if (levelOneLabelElement != null && levelOneLabelElement.getText().equalsIgnoreCase(levelOneLabel)) {
				// Once the level-one checkbox is found, locate the level-two checkboxes within
				// this group
				List<WebElement> levelTwoCheckboxes = levelOneCheckbox.findElements(By.xpath(xpathLevelTwoList));

				// Iterate over the level-two checkboxes within the level-one group
				for (WebElement levelTwoCheckbox : levelTwoCheckboxes) {
					// Find the label associated with the level-two checkbox
					WebElement levelTwoLabelElement = null;
					try {
						levelTwoLabelElement = levelTwoCheckbox
								.findElement(By.xpath(".//following-sibling::" + checkboxTagName));
					} catch (Exception e) {
						levelTwoLabelElement = levelTwoCheckbox;
					}

					// Compare the extracted label text with the user-provided level-two label
					if (levelTwoLabelElement != null
							&& levelTwoLabelElement.getText().equalsIgnoreCase(levelTwoLabel)) {
						// If the level-two checkbox is not selected, check it
						if (!levelTwoCheckbox.isSelected()) {
							levelTwoCheckbox.click();
							System.out.println("Checked the level-two checkbox for: " + levelTwoLabel);
							isCheckboxChecked = true;
							break; // No need to continue looping once the checkbox is checked
						} else {
							System.out.println("Level-two checkbox for " + levelTwoLabel + " is already selected.");
						}
					}
				}
				if (isCheckboxChecked) {
					break; // Exit the outer loop once the level-two checkbox is checked
				}
			}
		}

		// Assert that the checkbox state has changed (either checked or unchecked)
		if (!isCheckboxChecked) {
			// If no checkbox was checked, assert the failure
			System.out.println("No matching checkbox found for the labels provided or checkbox already checked.");
			softAssert.assertTrue(isCheckboxChecked, "Checkbox for level-one label '" + levelOneLabel
					+ "' and level-two label '" + levelTwoLabel + "' was not found or not selected.");
		}

		// Perform soft assertions at the end


		// Return whether the checkbox was checked or not
		return isCheckboxChecked;
	}

	public static boolean checkTwoLevelCheckboxesIfNotSelectedMultipleSelection(WebDriver driver,
			List<String> levelOneLabels, String xpathLevelOneList, List<String> levelTwoLabels,
			String xpathLevelTwoList, String checkboxTagName) {

		// Find all the level-one checkboxes (they correspond to groups or categories)
		List<WebElement> levelOneCheckboxes = driver.findElements(By.xpath(xpathLevelOneList));
		boolean allCheckboxesChecked = true; // To track if all expected checkboxes were checked

		// Iterate over the level-one checkboxes
		for (WebElement levelOneCheckbox : levelOneCheckboxes) {
			// Find the label associated with the level-one checkbox
			WebElement levelOneLabelElement = null;
			try {
				levelOneLabelElement = levelOneCheckbox
						.findElement(By.xpath(".//following-sibling::" + checkboxTagName));
			} catch (Exception e) {
				levelOneLabelElement = levelOneCheckbox;
			}

			// Compare the extracted label text with each user-provided level-one label
			if (levelOneLabelElement != null && levelOneLabels.contains(levelOneLabelElement.getText())) {
				// Once a matching level-one checkbox is found, locate level-two checkboxes
				// within this group
				List<WebElement> levelTwoCheckboxes = levelOneCheckbox.findElements(By.xpath(xpathLevelTwoList));

				// Iterate over the level-two checkboxes within the level-one group
				for (WebElement levelTwoCheckbox : levelTwoCheckboxes) {
					// Find the label associated with the level-two checkbox
					WebElement levelTwoLabelElement = null;
					try {
						levelTwoLabelElement = levelTwoCheckbox
								.findElement(By.xpath(".//following-sibling::" + checkboxTagName));
					} catch (Exception e) {
						levelTwoLabelElement = levelTwoCheckbox;
					}

					// Compare the extracted label text with each user-provided level-two label
					if (levelTwoLabelElement != null && levelTwoLabels.contains(levelTwoLabelElement.getText())) {
						// If the level-two checkbox is not selected, check it
						if (!levelTwoCheckbox.isSelected()) {
							levelTwoCheckbox.click();
							System.out.println("Checked the level-two checkbox for: " + levelTwoLabelElement.getText());
						} else {
							System.out.println("Level-two checkbox for " + levelTwoLabelElement.getText()
									+ " is already selected.");
						}
					}
				}
			}
		}

		// Verify that all expected level-one and level-two labels were checked
		for (String label : levelOneLabels) {
			if (!driver.findElements(By.xpath(xpathLevelOneList + "[contains(.,'" + label + "')]")).stream()
					.allMatch(WebElement::isSelected)) {
				System.out.println("Checkbox for level-one label '" + label + "' was not found or not selected.");
				allCheckboxesChecked = false;
			}
		}
		for (String label : levelTwoLabels) {
			if (!driver.findElements(By.xpath(xpathLevelTwoList + "[contains(.,'" + label + "')]")).stream()
					.allMatch(WebElement::isSelected)) {
				System.out.println("Checkbox for level-two label '" + label + "' was not found or not selected.");
				allCheckboxesChecked = false;
			}
		}

		// Perform a soft assertion for all checkboxes
		softAssert.assertTrue(allCheckboxesChecked, "Not all expected checkboxes were checked.");


		// Return whether all expected checkboxes were successfully checked
		return allCheckboxesChecked;
	}

	public static void selectThreeLevelCheckboxes(WebDriver driver,
			Map<String, Map<String, List<String>>> selectionCriteria, By companyLocator, By branchLocator,
			By vehicleLocator) {
		// Iterate through the companies provided in the criteria
		for (String company : selectionCriteria.keySet()) {
			// Locate and select the company checkbox
			List<WebElement> companies = driver.findElements(companyLocator);
			boolean companyFound = false;

			for (WebElement companyElement : companies) {
				if (companyElement.getText().trim().equalsIgnoreCase(company)) {
					companyFound = true;
					if (!companyElement.isSelected()) {
						companyElement.click(); // Select the company
					}
					break;
				}
			}
			if (!companyFound) {
				System.out.println("Company not found: " + company);
				continue;
			}

			// Get the branches for this company
			Map<String, List<String>> branches = selectionCriteria.get(company);

			for (String branch : branches.keySet()) {
				// Locate and select the branch checkbox
				List<WebElement> branchElements = driver.findElements(branchLocator);
				boolean branchFound = false;

				for (WebElement branchElement : branchElements) {
					if (branchElement.getText().trim().equalsIgnoreCase(branch)) {
						branchFound = true;
						if (!branchElement.isSelected()) {
							branchElement.click(); // Select the branch
						}
						break;
					}
				}
				if (!branchFound) {
					System.out.println("Branch not found: " + branch);
					continue;
				}

				// Get the vehicles for this branch
				List<String> vehicles = branches.get(branch);

				for (String vehicle : vehicles) {
					// Locate and select the vehicle checkbox
					List<WebElement> vehicleElements = driver.findElements(vehicleLocator);
					boolean vehicleFound = false;

					for (WebElement vehicleElement : vehicleElements) {
						if (vehicleElement.getText().trim().equalsIgnoreCase(vehicle)) {
							vehicleFound = true;
							if (!vehicleElement.isSelected()) {
								vehicleElement.click(); // Select the vehicle
							}
							break;
						}
					}
					if (!vehicleFound) {
						System.out.println("Vehicle not found: " + vehicle);
					}
				}
			}
		}
	}

}
