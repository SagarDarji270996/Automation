package projects.trackzee.tests.multiobjectrunning;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;
import projects.trakzee.web.projectUtility.StartChromeWithRemoteDebugging;
import projects.trakzee.web.projectUtility.StringHandling;

public class MultiObjectRunning {

	private static WebDriver driver;

	ProjectUtility pu = new ProjectUtility();
	//xpath
	public static final By alreadyPresentIndexCount = By.xpath("//tbody[@id='configurations']//tr//td[1]");
	public static final String imeiNoStringFormat = "(//td//input[@name='imei_no'])[%s]";
	public static final String serverIPStringFormat = "(//td//input[@name='server_ip'])[%s]";
	public static final String CSVFileStringFormat = "//td//input[@name='csv_file']";
	public static final String timeIntervalStringFormat = "(//td//input[@name='update_interval'])[%s]";
	public static final String statusStringFormat = "(//td//div[@class='custom-control custom-switch'])[%s]";
	public static final String submitStringFormat = "(//td//button[text()='Submit'])[%s]";
	public static final String removeStringFormat = "(//td//button[text()='Remove'])[%s]";
	public static final By addConfiguration = By.xpath("//button[@id='add_configuration']");

	public String lastIndex() {
		int totalIndex = getWebDriver().findElements(alreadyPresentIndexCount).size();
		return totalIndex + "";
	}

	public void setIMEINo(String imieNo) {
		pu.setDataInFieldWithClear(getWebDriver(), String.format(imeiNoStringFormat, lastIndex()), imieNo);
	}

	public void setServerIP(String serverIP) {
		pu.setDataInFieldWithClear(getWebDriver(), String.format(serverIPStringFormat, lastIndex()), serverIP);
	}

	public void setTimeInterval(String timeInterval) {
		pu.setDataInFieldWithClear(getWebDriver(), String.format(timeIntervalStringFormat, lastIndex()), timeInterval);
	}

	public void setCSVFile(String csvFile) {
		WebElement ele = getWebDriver().findElement(By.xpath(CSVFileStringFormat));
		ele.sendKeys(csvFile);
	}

	public void clickOnSubmit() throws InterruptedException {
		pu.clickOnButton(getWebDriver(), String.format(submitStringFormat, lastIndex()));
		acceptAlert();
	}

	public void acceptAlert() {
		try {
			// WebDriverWait wait = new WebDriverWait(getWebDriver(),
			// Duration.ofSeconds(10));// selenium [4.26]
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent()); // Wait for alert

			Alert alert = getWebDriver().switchTo().alert(); // Switch to the alert
			System.out.println("Alert Text: " + alert.getText()); // Print alert text
			alert.accept(); // Accept (OK button)
			System.out.println("Alert accepted.");
		} catch (NoAlertPresentException e) {
			System.out.println("No alert found.");
		}

	}

	public boolean clickOnAddConfiguration() {
		return pu.clickOnButtonWithBooleanReturn(getWebDriver(), addConfiguration);
	}

	public void clickOnRemove() {
		pu.clickOnButton(getWebDriver(), String.format(removeStringFormat, lastIndex()));
		acceptAlert();
	}

	public void clickOnRemoveBulkUpdate(int indexes) {
		// WebDriverWait wait = new WebDriverWait(getWebDriver(),
		// Duration.ofSeconds(10));[v4.26]

		WebDriverWait wait = new WebDriverWait(driver, 5);

		for (int index = indexes; index <= indexes; index--) {
			if (index == 0) {
				return;
			}
			pu.clickOnButton(getWebDriver(), String.format(removeStringFormat, index));
			System.out.println("Clicked on status successfully!");
			acceptAlert();

		}
	}

	public void clickOnStatus() throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(getWebDriver(),
		// Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String xpath = String.format(statusStringFormat, lastIndex());
		System.out.println("Status: " + xpath);

		try {
			WebElement status = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			// Scroll into view before clicking
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", status);
			Thread.sleep(500); // Small wait after scrolling

			status.click();
			System.out.println("Clicked on status successfully!");
		} catch (Exception e) {
			System.out.println("Not clicked on the status: " + xpath);
			e.printStackTrace();
		}
	}

	public void clickOnStatus(int indexes) throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(getWebDriver(),
		// Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		for (int index = 1; index <= indexes; index++) {
			String xpath = String.format(statusStringFormat, index);
			System.out.println("Status: " + xpath);

			try {
				WebElement status = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

				// Scroll into view before clicking
				((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", status);
				Thread.sleep(500); // Small wait after scrolling

				status.click();
				System.out.println("Clicked on status successfully!");
			} catch (Exception e) {
				System.out.println("Not clicked on the status: " + xpath);
				e.printStackTrace();
			}
		}
	}

	@Test(priority = 1, dataProvider = "scheduleReportDataProviderForActivitySubModule")
	public void testSchedulreReportFunctionalityForActivitySubModuleUsingExcelSheet(Map<String, Object> filterData)
			throws Exception {
		//TESTCASE_DONE: Test filter functionality
		try {
			filterData = StringHandling.convertKeysToCamelCase(filterData);

			clickOnAddConfiguration();
			setIMEINo((String) filterData.get("imeiNo"));
			setServerIP((String) filterData.get("serverIp"));
			setTimeInterval((String) filterData.get("timeInterval"));
			String fileName = "valsad_to_gandhinagar";

			final String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "java" + File.separator + "projects" + File.separator + "multiobjectrunning"
					+ File.separator + fileName + ".csv";
			System.out.println("XLS File path: " + filePath);

			setCSVFile(filePath);
			clickOnSubmit();
			getWebDriver().navigate().refresh();
			Thread.sleep(200);
			clickOnStatus(); //active
		} catch (Exception e) {

		}
	}

	@Test(priority = 2)
	public void refreshPage() throws InterruptedException {
		try {
			getWebDriver().navigate().refresh();
			Thread.sleep(2000);
		} catch (Exception e) {

		}
	}

	@Description("It will inactive all the running vehicle")
	@Test(priority = 3)
	public void changeStatusBulkUpdate() throws InterruptedException {
		try {
			Thread.sleep(1000); //inactive
			clickOnStatus(Integer.parseInt(lastIndex()));
		} catch (Exception e) {

		}
	}

	@Description("It will change the status inactive to active all the vehicle")
	@Test(priority = 4)
	public void changeStatusBulkUpdate2() throws InterruptedException {
		try {
			Thread.sleep(300000); //active
			clickOnStatus(Integer.parseInt(lastIndex()));
		} catch (Exception e) {

		}
	}

	@Description("It will change the status active to inactive all the vehicle")
	@Test(priority = 5)
	public void changeStatusBulkUpdate3() throws InterruptedException {
		try {
			Thread.sleep(150000); //active
			clickOnStatus(Integer.parseInt(lastIndex()));
		} catch (Exception e) {

		}
	}

	@Description("It will change the status inactive to active all the vehicle")
	@Test(priority = 6)
	public void changeStatusBulkUpdate4() throws InterruptedException {
		try {
			Thread.sleep(150000); //active
			clickOnStatus(Integer.parseInt(lastIndex()));
		} catch (Exception e) {

		}
	}

	@Test(priority = 7)
	public void removeConfigurations() throws InterruptedException {
		try {
			Thread.sleep(150000);
			clickOnRemoveBulkUpdate(Integer.parseInt(lastIndex()));
		} catch (Exception e) {

		}
	}

	@DataProvider(name = "scheduleReportDataProviderForActivitySubModule")
	public Object[][] scheduleReportDataProviderForActivitySubModuleNew(Method method) throws IOException {
		// Fetch data from Excel file using the DataProviders class
		System.setProperty("sheetName", "Object_3");
		Test testAnnotation = method.getAnnotation(Test.class);
		String sheetName = testAnnotation.description();

		//this line is run use-able only if the page name and sheet name is not present in the test case description and testname
		sheetName = sheetName.isEmpty() || sheetName.equals("") ? System.getProperty("sheetName") : sheetName;

		System.out.println("Found description as sheet name: " + sheetName);

		String fileName = "Object";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "projects" + File.separator + "multiobjectrunning"
				+ File.separator + fileName + ".xlsx";
		System.out.println("XLS File path: " + filePath);
		List<Map<String, Object>> rawData = ReadDataFromExcelFile
				.readExcelFileAndCatchDataUsingColumnNameWithSkipDataSet(filePath, sheetName);

		// Convert List<Map<String, Object>> to Object[][]
		Object[][] data = new Object[rawData.size()][1];
		for (int i = 0; i < rawData.size(); i++) {
			data[i][0] = rawData.get(i); // Each test method receives one Map<String, Object>
		}
		return data;
	}

	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	public static void setDriver(WebDriver driverInstance) {
		tldriver.set(driverInstance);
	}

	public static void quitDriver() {
		if (tldriver.get() != null) {
			tldriver.get().quit();
			tldriver.remove();
		}
	}

	public static WebDriver getWebDriver() {
		return getDriver();
	}

	public void setWebDriver(WebDriver driverInstance) {
		setDriver(driverInstance);
	}

	@BeforeClass()
	public void setUpWebDriverInDebuggerMode() {
		System.out.println("Setting up WebDriver in debugger mode...");
		// Ensure the correct version of ChromeDriver is used
		WebDriverManager.chromedriver().driverVersion("latest").setup();

		if (!Boolean.parseBoolean(System.getProperty("headless", "true"))) {
			StartChromeWithRemoteDebugging.startChromeIfNotRunning(9222, "/home/uffizio/ChromeDebuggerDataGMail",
					"Profile 1");
		}
		try {
			// Initialize WebDriver with the given Chrome options
			driver = new ChromeDriver(customizedChromeOptions(true, true, true, false, 9222));
			setWebDriver(driver);
		} catch (Exception e) {
			// Capture any errors during initialization and print the stack trace
			System.err.println("Failed to initialize WebDriver: " + e.getMessage());
		}
		getWebDriver().get("http://192.168.3.177:5000");

	}

	private ChromeOptions customizedChromeOptions(boolean blockAdsAndNotifications, boolean headlessBrowsing,
			boolean incognitoMode, boolean debuggerMode, int debuggerPort) {

		// TO INITIALIZE CHROME OPTIONS
		ChromeOptions options = new ChromeOptions(); // Use the correct class name and variable

		if (blockAdsAndNotifications) {
			// Disable pop-ups and intrusive ads
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-ads");
			System.out.println("Disabled Ads and Notifications");
		}
		if (headlessBrowsing) {
			// FOR HEADER LESS BROWSING
			options.addArguments("--headless");
			System.out.println("Entered into headless browsing");
		}

		if (incognitoMode) {
			// TO OPEN CHROME DRIVER INTO INCOGNITO MODE
			options.addArguments("--incognito");
			System.out.println("Entered into incognito mode");

		}

		if (debuggerMode) {
			// TO USE CHROME DRIVER IN DEBUGGER MODE
			options.setExperimentalOption("debuggerAddress", "localhost:" + debuggerPort);
			System.out.println("Debugging mode with port:" + debuggerPort);
		}

		// Set the path to Chrome binary if it’s not in the default location
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); // Path for Windows (Update as needed)
		} else if (System.getProperty("os.name").toLowerCase().contains("nix")
				|| System.getProperty("os.name").toLowerCase().contains("nux")) {
			options.setBinary("/usr/bin/google-chrome"); // Path for Linux

		}

		return options;
	}

}
