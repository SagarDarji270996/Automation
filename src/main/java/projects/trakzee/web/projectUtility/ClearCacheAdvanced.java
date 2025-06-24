package projects.trakzee.web.projectUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class ClearCacheAdvanced {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static void clearChromeBrowserAdvanceCache(WebDriver driver) throws InterruptedException {
		// Set up ChromeDriver

		// Open Chrome's "Clear Browsing Data" settings
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(1000); // Wait for the settings page to load

		// Simulate user actions to clear cache using JavaScript
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Switch to "Advanced" tab
		js.executeScript("document.querySelector('settings-ui').shadowRoot"
				+ ".querySelector('settings-main').shadowRoot" + ".querySelector('settings-basic-page').shadowRoot"
				+ ".querySelector('settings-section settings-privacy-page').shadowRoot"
				+ ".querySelector('settings-clear-browsing-data-dialog').shadowRoot"
				+ ".querySelector('#tabs > div:nth-child(2)').click();");
		Thread.sleep(1000); // Wait for the Advanced tab to load

		// Select "All time" in the time range dropdown
		js.executeScript("document.querySelector('settings-ui').shadowRoot"
				+ ".querySelector('settings-main').shadowRoot" + ".querySelector('settings-basic-page').shadowRoot"
				+ ".querySelector('settings-section settings-privacy-page').shadowRoot"
				+ ".querySelector('settings-clear-browsing-data-dialog').shadowRoot"
				+ ".querySelector('#timePeriod').value = 0;");

		// Confirm the clearing of data
		js.executeScript("document.querySelector('settings-ui').shadowRoot"
				+ ".querySelector('settings-main').shadowRoot" + ".querySelector('settings-basic-page').shadowRoot"
				+ ".querySelector('settings-section settings-privacy-page').shadowRoot"
				+ ".querySelector('settings-clear-browsing-data-dialog').shadowRoot"
				+ ".querySelector('#clearBrowsingDataConfirm').click();");

		System.out.println("Cache cleared using the Advanced tab with 'All time' selection.");

	}
}
