package projects.trakzee.web.projectUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class WaitUtils {

	private SoftAssert softAssert = TestBase.getSoftAssert();
    private final WebDriver driver;  // Declare driver as final
	private static int DEFAULT_TIMEOUT = 60; // Timeout in seconds

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(By locator) {
		new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisible(By locator) {
		new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBePresent(By locator) {
		new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
