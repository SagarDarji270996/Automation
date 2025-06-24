package projects.taskeye.configurations.common;

import projects.taskeye.configurations.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class MobileBrowserWait extends TestBase {

    private static final Integer fluentWaitInterval = 60;

    // Modify static methods to accept WebDriver as a parameter
    public static Wait getFluentWait(WebDriver driver) {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(fluentWaitInterval))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return fluentWait;
    }

    public static boolean waitUntilElementIsPresent(WebDriver driver, By by) {
        try {
            getFluentWait(driver).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static boolean waitUntilElementClickable(WebDriver driver, By by) {
        try {
            getFluentWait(driver).until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
