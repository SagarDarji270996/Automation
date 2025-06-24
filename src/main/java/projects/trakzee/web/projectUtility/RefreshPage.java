package projects.trakzee.web.projectUtility;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RefreshPage {

	public static void hardRefreshPage() {
		try {
			Robot robot = new Robot();
			// Press Control, Shift, and R
			String isHeadless = System.getProperty("headless");
			if (isHeadless.equals("false")) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				// Release keys in reverse order
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			System.out.println("Page refreshed successfully...");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void hardRefreshPage(WebDriver driver) throws InterruptedException {
		// Check if the environment is headless
		if (GraphicsEnvironment.isHeadless()) {
			// Use JavaScript to force a hard refresh in headless mode
			((JavascriptExecutor) driver).executeScript("window.location.reload(true);");
			((JavascriptExecutor) driver).executeScript("window.location.reload(true);");
			Thread.sleep(2000);
			System.out.println("Page refreshed using JavaScript in headless mode.");
		} else {
			try {
				Robot robot = new Robot();
				// Press Control, Shift, and R
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				// Release keys in reverse order
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				System.out.println("Page refreshed successfully using Robot.");
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	}

	public static void hardRefreshPageWithJavaScript(WebDriver driver) {
		// Cast driver to JavascriptExecutor and perform a hard refresh.
		((JavascriptExecutor) driver).executeScript("window.location.reload(true);");
		System.out.println("Page refreshed successfully...");
	}

}
