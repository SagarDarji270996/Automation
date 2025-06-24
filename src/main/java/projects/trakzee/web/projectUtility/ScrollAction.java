package projects.trakzee.web.projectUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollAction {
	private static Actions action;
	private static JavascriptExecutor jsExecutor;

	public static void scrollElementTillNotVisible(WebDriver driver, By locator) throws InterruptedException {
		WebElement element = driver.findElement(locator);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollElementTillNotVisible(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollList(WebDriver driver, By list_address) throws InterruptedException {

		action = new Actions(driver);

		// TO TRACK THE CALLER METHOD NAME
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		System.out.println("Method called ScrollListElement and caller method name: " + callerMethodName);

		// TO INITIALIAZS ANCTION CLASS AND JAVA SCRIPT EXECUTOR
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		int scrollCount = 5;
		WebElement element = driver.findElement(list_address);
		List<WebElement> listELement = driver.findElements(list_address);
		// action.click(element).perform();
		for (int listCount = 0; listCount < listELement.size(); listCount++) {
			scrollCount++;
			if (listCount == 0) {
				System.out.println("Start scrolling the page");

			}

			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(20);
			if (scrollCount == 5) {
				break;
			}
			if (listCount == listELement.size() - 1) {
				System.out.println("Page Scrolling end");
			}
		}

	}

}
