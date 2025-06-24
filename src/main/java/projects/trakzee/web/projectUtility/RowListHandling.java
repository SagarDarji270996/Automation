package projects.trakzee.web.projectUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class RowListHandling {

	private static SoftAssert softAssert = TestBase.getSoftAssert();
	private static WebDriverWait wait = null;

	public static Actions action = null;
	public static JavascriptExecutor jsExecutor;

	private static List<WebElement> getWebElementList(WebDriver driver, String listElementAddress) {
		List<WebElement> listElement = driver.findElements(By.xpath(listElementAddress));
		return listElement;
	}

	private static List<WebElement> getWebElementList(WebDriver driver, By listElementAddress) {
		wait = new WebDriverWait(driver, 10);
		List<WebElement> listElement = driver.findElements(listElementAddress);
		return listElement;
	}

	private static List<WebElement> getWebElementList(WebDriver driver, By listElementAddress, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listElementAddress));
		List<WebElement> listElement = driver.findElements(listElementAddress);
		return listElement;
	}

	public static int findElementFromRowListAndClickOnAnyColumnElement(WebDriver driver, String xpathRowListElement,
			String commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex,
			boolean wantToClickOnElement, boolean wantToclickOnFindSearckKey) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();

		System.out.println("findElementFromRowListAndTakeActionOnColumnElement method called and Caller method name: "
				+ callerMethodName);
		System.out.println("wantToClickOnElement: " + wantToClickOnElement);
		System.out.println("wantToclickOnFindSearckKey: " + wantToclickOnFindSearckKey);

		List<WebElement> listElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + xpathRowListElement + commonXpathColumnListElement + ")";

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean confirmationOnGivenValue = false;
		boolean isTargetElementPresent = false;
		boolean isSearchKeyPresent = false;
		boolean isClickedOnActionElement = false;
		String targetElementName = null;

		for (WebElement element : listElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String firstColumnText = text[0];
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);
					String btnActionAddress = null;

					if (formatText.equalsIgnoreCase(searchKey)) {
						if (wantToClickOnElement == false && wantToclickOnFindSearckKey == false) {
							return listRowCount;
						}
						softAssert.assertEquals(formatText, searchKey, "Search key not matched");

						try {
							if (wantToClickOnElement) {
								btnActionAddress = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
								System.out.println("Xpath of action elelment: " + btnActionAddress);

								WebElement targetElement = element.findElement(By.xpath(btnActionAddress));

								if (targetElement.isDisplayed() && targetElement.isEnabled()) {
									isActionElementPresent = true;
									System.out.println("Given value matched with the list value: " + formatText);
									confirmationOnGivenValue = true;
									// ((JavascriptExecutor)
									// driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
									targetElement.click();
									isTargetElementPresent = true;
									System.out.println("Clicked on the action element");
									isClickedOnActionElement = true;
									softAssert.assertTrue(isClickedOnActionElement, "Not click on action element");
									break;
								}
							} else if (wantToclickOnFindSearckKey) {
								System.out.println("Text present at given column index: " + formatText);
								confirmationOnGivenValue = true;
								String yourUniqueValue = null;
								WebElement elementFindKey = null;

								yourUniqueValue = formatText.toLowerCase().trim();
								elementFindKey = driver.findElement(By.xpath(
										"(//*[translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
												+ yourUniqueValue + "'])[1]"));
								// ((JavascriptExecutor)
								// driver).executeScript("arguments[0].scrollIntoView(true);", elementFindKey);
								action.moveToElement(elementFindKey).build().perform();
								action.moveToElement(elementFindKey).click().build().perform();
								isSearchKeyPresent = true;
								softAssert.assertTrue(isSearchKeyPresent, "Search key not found");
								System.out.println("Clicked on the findSearchKey: " + elementFindKey.getText());
								break;

							}

						} catch (Exception e) {
							System.out.println("Exception: " + e.getMessage());
							if (isTargetElementPresent == false) {
								softAssert.assertTrue(false,
										"Targeted action element: " + targetElementName + " not present");
							}
							if (isSearchKeyPresent == false) {
								softAssert.assertTrue(false, "Search key: " + searchKey + " not present");
							}

						}
					}

				}
				if (confirmationOnGivenValue) {
					break;// because list item matched
				}
			}
			if (isClickedOnActionElement) {
				break;
			}
		}



		if (!isActionElementPresent) {
			return -1;
		} else {
			return listRowCount;
		}

	}

	public static String findElementFromRowListAndVerifyByAttribute(WebDriver driver, By listLocatorColumnSpecific,
			String attributeName, String searchKey, int waitTime) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		List<WebElement> listElement = getWebElementList(driver, listLocatorColumnSpecific, waitTime);
		System.out.println("Size: " + listElement.size());
		if (listElement.size() == 0) {
			softAssert.assertFalse(true, "data not found");

			return "0";
		}
		String findValue = null;
		boolean isFindValue = false;
		int rowCount = 0;
		System.out.println("searchKey: " + searchKey);
		for (WebElement element : listElement) {
			String text = element.getAttribute(attributeName).trim().toString();
			if (text.equals(searchKey.trim())) {
				System.out.println("Text: " + text);
				findValue = text.trim();
				isFindValue = true;
				break;
			} else {
				System.out.println("1");
			}
		}
		softAssert.assertEquals(findValue, searchKey, "Search key not matched");

		return findValue;

	}

	public static int findElementFromRowListAndClickOnAnyColumnElement(WebDriver driver, By xpathRowListElement,
			By commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex, boolean wantToClickOnElement,
			boolean wantToclickOnFindSearckKey) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		List<WebElement> listElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + getXpathValueFromByDataType(xpathRowListElement)
				+ getXpathValueFromByDataType(commonXpathColumnListElement) + ")";
		System.out.println("Combined xpath: " + commonXpathBasedOnColumnIndex);

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean confirmationOnGivenValue = false;
		boolean isTargetElementPresent = false;
		boolean isSearchKeyPresent = false;
		boolean isClickedOnActionElement = false;
		String targetElementName = null;

		for (WebElement element : listElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String firstColumnText = text[0];
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					//System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);
					String btnActionAddress = null;

					if (formatText.equalsIgnoreCase(searchKey)) {
						if (wantToClickOnElement == false && wantToclickOnFindSearckKey == false) {
							return listRowCount;
						}
						softAssert.assertEquals(formatText, searchKey, "Search key not matched");

						try {
							if (wantToClickOnElement) {
								btnActionAddress = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
								System.out.println("Xpath of action elelment: " + btnActionAddress);

								WebElement targetElement = element.findElement(By.xpath(btnActionAddress));

								if (targetElement.isDisplayed() && targetElement.isEnabled()) {
									isActionElementPresent = true;
									System.out.println("Given value matched with the list value: " + formatText);
									confirmationOnGivenValue = true;
									// ((JavascriptExecutor)
									// driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
									targetElement.click();
									isTargetElementPresent = true;
									System.out.println("Clicked on the action element");
									isClickedOnActionElement = true;
									softAssert.assertTrue(isClickedOnActionElement, "Not click on action element");
									break;
								}
							} else if (wantToclickOnFindSearckKey) {
								System.out.println("Text present at given column index: " + formatText);
								confirmationOnGivenValue = true;
								String yourUniqueValue = null;
								WebElement elementFindKey = null;

								yourUniqueValue = formatText.toLowerCase().trim();
								elementFindKey = driver.findElement(By.xpath(
										"(//*[translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
												+ yourUniqueValue + "'])[1]"));
								// ((JavascriptExecutor)
								// driver).executeScript("arguments[0].scrollIntoView(true);", elementFindKey);
								action.moveToElement(elementFindKey).build().perform();
								action.moveToElement(elementFindKey).click().build().perform();
								isSearchKeyPresent = true;
								softAssert.assertTrue(isSearchKeyPresent, "Search key not found");
								System.out.println("Clicked on the findSearchKey: " + elementFindKey.getText());
								break;

							}

						} catch (Exception e) {
							System.out.println("Exception: " + e.getMessage());
							if (isTargetElementPresent == false) {
								softAssert.assertTrue(false,
										"Targeted action element: " + targetElementName + " not present");
							}
							if (isSearchKeyPresent == false) {
								softAssert.assertTrue(false, "Search key: " + searchKey + " not present");
							}

						}
					}

				}
				if (confirmationOnGivenValue) {
					break;// because list item matched
				}
			}
			if (isClickedOnActionElement) {
				break;
			}
		}



		if (!isActionElementPresent) {
			return -1;
		} else {
			return listRowCount;
		}

	}

	public static int getMatchedRowListCount(WebDriver driver, By xpathRowListElement, String searchKey,
			int searchKeyColumnIndex) throws InterruptedException {
		List<WebElement> listElement = getWebElementList(driver, xpathRowListElement);
		int listRowCount = 0;

		for (WebElement element : listElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;
				if (columnIndexCount == searchKeyColumnIndex) {
					if (st.equalsIgnoreCase(searchKey)) {
						System.out.println("Formatted key" + formatText + " and searched key is: " + searchKey);
						return listRowCount;
					}
				}
			}
		}
		return -1;

	}

	public static int findElementFromRowListAndCheckIsToggleable(WebDriver driver, String xpathRowListElement,
			String commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex)
			throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();

		System.out.println("findElementFromRowListAndToggleCheckbox method called by: " + callerMethodName);

		List<WebElement> listElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + xpathRowListElement + commonXpathColumnListElement + ")";

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean isCheckboxToggled = false;

		for (WebElement element : listElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();
					System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);

					if (formatText.equalsIgnoreCase(searchKey)) {

						try {
							String checkboxXpath = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
							System.out.println("Xpath of target checkbox element: " + checkboxXpath);

							WebElement targetCheckbox = element.findElement(By.xpath(checkboxXpath));

							if (targetCheckbox.isDisplayed() && targetCheckbox.isEnabled()) {
								isActionElementPresent = true;

								// Check initial state of the checkbox
								boolean initialState = targetCheckbox.isSelected();
								System.out.println("Initial checkbox state (selected): " + initialState);

								// Toggle the checkbox
								targetCheckbox.click();
								boolean newState = targetCheckbox.isSelected();

								// Check if the checkbox state has toggled
								if (newState != initialState) {
									System.out.println(
											"Checkbox toggled successfully. New state (selected): " + newState);
									isCheckboxToggled = true;
								} else {
									System.out.println("Checkbox did not toggle.");
								}

								softAssert.assertTrue(isCheckboxToggled, "Checkbox was not toggleable as expected.");
								break;

							} else {
								System.out.println("Target checkbox is not displayed or enabled.");
							}

						} catch (Exception e) {
							System.out.println("Exception while attempting to toggle checkbox: " + e.getMessage());
							softAssert.fail("Exception encountered for search key: " + searchKey);
						}
					}
				}

				if (isCheckboxToggled) {
					break; // Exit if the checkbox was toggled
				}
			}
			if (isCheckboxToggled) {
				break;
			}
		}



		return isCheckboxToggled ? listRowCount : -1; // Return row index if toggled, otherwise -1
	}

	public static int findElementFromRowListAndCheckIsToggleable(WebDriver driver, By xpathRowListElement,
			By commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();

		System.out.println("findElementFromRowListAndToggleCheckbox method called by: " + callerMethodName);

		List<WebElement> listElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + xpathRowListElement.toString() + commonXpathColumnListElement
				+ ")";

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean isCheckboxToggled = false;

		for (WebElement element : listElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();
					System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);

					if (formatText.equalsIgnoreCase(searchKey)) {

						try {
							String checkboxXpath = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
							System.out.println("Xpath of target checkbox element: " + checkboxXpath);

							WebElement targetCheckbox = element.findElement(By.xpath(checkboxXpath));

							if (targetCheckbox.isDisplayed() && targetCheckbox.isEnabled()) {
								isActionElementPresent = true;

								// Check initial state of the checkbox
								boolean initialState = targetCheckbox.isSelected();
								System.out.println("Initial checkbox state (selected): " + initialState);

								// Toggle the checkbox
								targetCheckbox.click();
								boolean newState = targetCheckbox.isSelected();

								// Check if the checkbox state has toggled
								if (newState != initialState) {
									System.out.println(
											"Checkbox toggled successfully. New state (selected): " + newState);
									isCheckboxToggled = true;
								} else {
									System.out.println("Checkbox did not toggle.");
								}

								softAssert.assertTrue(isCheckboxToggled, "Checkbox was not toggleable as expected.");
								break;

							} else {
								System.out.println("Target checkbox is not displayed or enabled.");
							}

						} catch (Exception e) {
							System.out.println("Exception while attempting to toggle checkbox: " + e.getMessage());
							softAssert.fail("Exception encountered for search key: " + searchKey);
						}
					}
				}

				if (isCheckboxToggled) {
					break; // Exit if the checkbox was toggled
				}
			}
			if (isCheckboxToggled) {
				break;
			}
		}



		return isCheckboxToggled ? listRowCount : -1; // Return row index if toggled, otherwise -1
	}

	public static int findElementFromRowListAndSelectCheckboxIfNotSelected(WebDriver driver, By xpathRowListElement,
			By commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		String rowList = XpathExtractor.getXpathValueFromByDataType(xpathRowListElement);
		String coloumnList = XpathExtractor.getXpathValueFromByDataType(commonXpathColumnListElement);

		List<WebElement> rowListElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + rowList + coloumnList + ")";

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean isCheckboxSelected = false;

		for (WebElement element : rowListElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					if (formatText.equalsIgnoreCase(searchKey)) {
						System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);

						try {
							String checkboxXpath = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
							System.out.println("Xpath of target checkbox element: " + checkboxXpath);

							WebElement targetCheckbox = element.findElement(By.xpath(checkboxXpath));

							if (targetCheckbox.isDisplayed() && targetCheckbox.isEnabled()) {
								isActionElementPresent = true;

								// Check if the checkbox is already selected
								if (!targetCheckbox.isSelected()) {
									targetCheckbox.click();
									System.out.println("Checkbox was not selected, now selected.");
									Thread.sleep(100);
									// Verify if checkbox is now selected
									targetCheckbox = element.findElement(By.xpath(checkboxXpath));
									isCheckboxSelected = targetCheckbox.isSelected();
								} else {
									System.out.println("Checkbox is already selected.");
									isCheckboxSelected = true;
								}
								break;
							} else {
								System.out.println("Target checkbox is not displayed or enabled.");
							}

						} catch (Exception e) {
							System.out.println("Exception while attempting to select checkbox: " + e.getMessage());
							softAssert.fail("Exception encountered for search key: " + searchKey);
						}
					}
				}

				if (isCheckboxSelected) {
					break; // Exit if checkbox was selected
				}
			}
			if (isCheckboxSelected) {
				break;
			}
		}



		return isCheckboxSelected ? listRowCount : -1; // Return row index if selected, otherwise -1
	}

	public static int findElementFromRowListAndSelectCheckboxIfNotSelected(WebDriver driver, String xpathRowListElement,
			String commonXpathColumnListElement, String searchKey, int searchKeyColumnIndex)
			throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		List<WebElement> rowListElement = getWebElementList(driver, xpathRowListElement);

		String commonXpathBasedOnColumnIndex = "(" + xpathRowListElement + commonXpathColumnListElement + ")";

		int listRowCount = 0;
		boolean isActionElementPresent = false;
		boolean isCheckboxSelected = false;

		for (WebElement element : rowListElement) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					if (formatText.equalsIgnoreCase(searchKey)) {
						System.out.println("formatText: " + formatText + " and searchKey: " + searchKey);

						try {
							String checkboxXpath = commonXpathBasedOnColumnIndex + "[" + listRowCount + "]";
							System.out.println("Xpath of target checkbox element: " + checkboxXpath);

							WebElement targetCheckbox = element.findElement(By.xpath(checkboxXpath));

							if (targetCheckbox.isDisplayed() && targetCheckbox.isEnabled()) {
								isActionElementPresent = true;

								// Check if the checkbox is already selected
								if (!targetCheckbox.isSelected()) {
									targetCheckbox.click();
									System.out.println("Checkbox was not selected, now selected.");
									Thread.sleep(100);
									// Verify if checkbox is now selected
									targetCheckbox = element.findElement(By.xpath(checkboxXpath));
									isCheckboxSelected = targetCheckbox.isSelected();
								} else {
									System.out.println("Checkbox is already selected.");
									isCheckboxSelected = true;
								}
								break;
							} else {
								System.out.println("Target checkbox is not displayed or enabled.");
							}

						} catch (Exception e) {
							System.out.println("Exception while attempting to select checkbox: " + e.getMessage());
							softAssert.fail("Exception encountered for search key: " + searchKey);
						}
					}
				}

				if (isCheckboxSelected) {
					break; // Exit if checkbox was selected
				}
			}
			if (isCheckboxSelected) {
				break;
			}
		}



		return isCheckboxSelected ? listRowCount : -1; // Return row index if selected, otherwise -1
	}

	private static String getXpathValueFromByDataType(By byLocators) {
		String xpathValue = byLocators.toString();
		if (xpathValue.startsWith("By.xpath: ")) {
			xpathValue = xpathValue.replace("By.xpath: ", "");
		}
		return xpathValue;
	}

	public static void clickOnMultipleColumnIndexesBasedOnMatchedRowListEntries(WebDriver driver, By rowLocator,
			int searchKeyColumnIndex, String searchKey, int[] actionColumnIndexes,
			String[] actionColumnIndexesXpathVerticalFlow) {
		// Get the list of rows
		List<WebElement> rows = driver.findElements(rowLocator);
		int rowEntryCounter = 0;
		for (WebElement row : rows) {
			rowEntryCounter++;
			// Split the row text into column values
			String text = row.getText();
			System.out.println("Find text is: " + text);

			String[] columnValues = text.split("\\n");

			// Check if the searchKey exists in the specified column index
			if (columnValues[searchKeyColumnIndex - 1].trim().equalsIgnoreCase(searchKey)) {

				// Perform click action on the provided column indexes
				int columnIndexCounter = 0;
				for (int columnIndex : actionColumnIndexes) {

					if (columnIndex <= columnValues.length) {
						String xpathForTargetElement = "(" + getXpathValueFromByDataType(rowLocator) + ")["
								+ rowEntryCounter + "]" + actionColumnIndexesXpathVerticalFlow[columnIndexCounter++];
						System.out.println("XpathForTargetElement: " + xpathForTargetElement);
						WebElement targetElement = row.findElement(By.xpath(xpathForTargetElement));

						if (targetElement.getAttribute("type") != null
								&& targetElement.getAttribute("type").equalsIgnoreCase("checkbox")) {
							// Check if the checkbox is already checked
							System.out.println("Cathced attribute type: checkbox");
							if (!targetElement.isSelected()) {
								targetElement.click(); // Click only if not already checked
							}
						} else if (targetElement.getAttribute("type") != null
								&& targetElement.getAttribute("type").equalsIgnoreCase("radio")
								|| targetElement.getAttribute("type").equalsIgnoreCase("radioButton")) {
							// Check if the checkbox is already checked
							System.out.println("Cathced attribute type: radio button");
							if (!targetElement.isSelected()) {
								targetElement.click(); // Click only if not already checked
							}
						} else {
							// For non-checkbox elements
							if (targetElement.isDisplayed() && targetElement.isEnabled()) {
								targetElement.click();
								System.out.println("Cathced attribute type: not found");
							}
						}
					} else {
						throw new NoSuchElementException(
								"Column index " + columnIndex + " does not exist in the current row.");
					}
				}
				return; // Exit after processing the matching row
			}
		}

		throw new NoSuchElementException(
				"No matching row found for search key: " + searchKey + " in column index: " + searchKeyColumnIndex);
	}

}
