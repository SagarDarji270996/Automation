package projects.trakzee.configurations.common;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

public class CommonData {
    private static final Logger logger = LogManager.getLogger(CommonData.class);
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alex", "Emily", "Michael", "Sarah", "David", "Sophia"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
    private static final Random RANDOM = new Random();
    private final WebDriver driver;
	private final WebDriverWait wait = null;
    private final IframesOfApplication iframe;

    public CommonData(WebDriver driver) {
        this.driver = driver;
        this.iframe = new IframesOfApplication(driver);
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
        String emailPrefix = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)].toLowerCase() +
                LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)].toLowerCase() +
                RANDOM.nextInt(1000);
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

    public void clickOnAnyButtonWithNameAndXpath(String buttonName, String xpath) {
        Logger logger = LogManager.getLogger(CommonData.class);
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

    public void verifyElementText(String xpath, String expectedText, String description) {
        try {
            iframe.switchToTitleFrame();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            String actualText = driver.findElement(By.xpath(xpath)).getText().trim();
            if (actualText.equals(expectedText)) {
                logger.info("Verification successful for: {}", description);
            } else {
                logger.warn("Verification failed for: {}. Expected: {}, but found: {}", description, expectedText, actualText);
            }
            SoftAssert soft = new SoftAssert();
            soft.assertEquals(actualText, expectedText, "Header text mismatch for: " + description);
            soft.assertAll(); // Assert all soft assertions
        } catch (Exception e) {
            logger.error("Error verifying element text for: {}", description, e);
        }
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
            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(radioButtonXPath)));
            radioButton.click();
            logger.info("Selected radio button: {}", radioButtonXPath);
        } catch (TimeoutException e) {
            logger.error("Error selecting radio button: {}", radioButtonXPath, e);
            System.out.println(driver.getPageSource());
        } catch (Exception e) {
            logger.error("Unexpected error selecting radio button.", e);
        }
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
            WebElement tabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicTabLocator)));
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
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        if (checkboxes.size() > 7) {
            checkboxes = checkboxes.subList(0, 7);  // Take only the first 7 checkboxes
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
}