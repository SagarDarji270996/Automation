package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class WebElementList {

	private static SoftAssert softAssert = TestBase.getSoftAssert();

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
					softAssert.assertFalse(false, "Failed to select the given element: " + elementName);
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}

		return false;
	}

	public static List<WebElement> getWebElementList(WebDriver driver, By listElementAddress) {
		List<WebElement> listElement = driver.findElements(listElementAddress);
		return listElement;
	}

	public static int getWebElementListSize(WebDriver driver, By listElementAddress) {
		List<WebElement> listElement = driver.findElements(listElementAddress);
		return listElement.size();
	}

	public static List<WebElement> getWebElementList(WebDriver driver, String listElementAddress) {
		List<WebElement> listElement = driver.findElements(By.xpath(listElementAddress));
		return listElement;
	}

	public static List<String> getTextFromList(WebDriver driver, By listElementAddress) {
		List<WebElement> listElement = driver.findElements(listElementAddress);
		List<String> actualList = new ArrayList<>();
		for (WebElement ele : listElement) {
			String actualName = ele.getText().trim();
			actualList.add(actualName);
		}
		return actualList;
	}

	public static List<String> getTextFromList(WebDriver driver, String listElementAddress, String attributeName,
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
		System.out.println("Actual list: " + actualList);
		softAssert.assertEquals(actualList, expectedList, "❌❌❌ List name matched");

		return actualList;
	}

	public static List<String> getTextFromList(WebDriver driver, By listElementAddress, String attributeName,
			List<String> expectedList) {
		List<WebElement> listElement = driver.findElements(listElementAddress);
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
		System.out.println("Actual list: " + actualList);
		softAssert.assertEquals(actualList, expectedList, "❌❌❌ List name matched");

		return actualList;
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
		System.out.println("List of visible element: " + actualList);
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
		System.out.println("List of visible element: " + actualList);
		return actualList;
	}

	public static List<String> getVisibleTextFromWebElementList(WebDriver driver, By listElementAddress,
			String attributeName) {
		List<WebElement> listElements = driver.findElements(listElementAddress);
		List<String> visibleTextList = new ArrayList<>();

		visibleTextList = listElements.stream().filter(ele -> ele.isDisplayed() && ele.isEnabled()) // Only consider displayed & enabled elements
				.map(ele -> ele.getAttribute(attributeName).trim()) // Extract text and trim spaces
				.filter(text -> !text.isEmpty()) // Exclude empty values
				.collect(Collectors.toList()); // Convert to List

		System.out.println("List of visible elements: " + visibleTextList);

		return visibleTextList;
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

	public static void getTextFromRowList(WebDriver driver, String listXpath) {
		List<WebElement> listItems = driver.findElements(By.xpath(listXpath));
		List<String> acutalList = new ArrayList<String>();
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			String[] values = itemName.split("\\n");
			acutalList.add(values[0]);
		}
	}

	public static boolean compareWebElmentListWithExpectedList(WebDriver driver, By listLocatorWithText,
			String[] expectedList) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		List<String> webElementList = new ArrayList<>();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(listLocatorWithText));
			List<WebElement> element = driver.findElements(listLocatorWithText);
			for (WebElement ele : element) {
				webElementList.add(ele.getText());
			}

		} catch (Exception e) {
			System.out.println("Exception from getElementTextAndCompare: " + e.getMessage());
		}

		softAssert.assertEqualsNoOrder(webElementList.toArray(), expectedList,
				"Actual text:" + Arrays.toString(webElementList.toArray()) + " not matched with the expected text: "
						+ Arrays.toString(expectedList));

		return false;
	}

}
