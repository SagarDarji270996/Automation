package projects.trackzee.tests.gmail;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import listeners.CustomTestListener;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.StartChromeWithRemoteDebugging;

@Listeners({ CustomTestListener.class })
public class TC_Gmail implements Runnable {
	private SoftAssert sharedSoftAssert;

	public TC_Gmail(SoftAssert softAssert) {
		this.sharedSoftAssert = softAssert;
	}

	private static WebDriver driver;

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

	//Home Page
	public static final String addressButtonCompose = "(//div[contains(text(),'Compose')])[1]";
	public static final String addressButtonInbox = "//div[contains(@class,'aim')]//*[text()='Inbox']";
	public static final String addressButtonStarred = "//div[contains(@class,'aim')]//*[text()='Starred']";
	public static final String addressButtonSnoozed = "//div[contains(@class,'aim')]//*[text()='Snoozed']";
	public static final String addressButtonSent = "//div[contains(@class,'aim')]//*[text()='Sent']";
	public static final String addressButtonDrafts = "//div[contains(@class,'aim')]//*[text()='Drafts']";
	public static final String addressButtonMore = "(//span[@class='CJ'][normalize-space()='More'])[1]";

	public static final String addressUserLogo = "//a[starts-with(@aria-label, 'Google Account')]";
	public static final String addressUserAccountFrame = "(//iframe[contains(@name,'account')])[1]";
	public static final String addressButtonLogout = "//*[contains(normalize-space(),'Sign out')]";

	//Inbox page
	public static final String addressCheckboxSelect = "(//span[@role='checkbox'])[2]";
	public static final String addressButtonDeleteEmail = "(//div[@aria-label='Delete'])[2]";
	public static final By btnDeleteIcon = By.xpath("//div[@aria-label='Delete']");
	public static final String addressButton1_50_OfMany = "(//div[@aria-label='Show more messages'])[2]";
	public static final String addressButton1_50_OfMany_NextArrow = "(//div[@aria-label='Older'])[2]";
	public static final String addressButton1_50_OfMany_BackArrow = "(//div[@aria-label='Newer'])[2]";
	public static final String addressAlertConversationMovedToTrace = "conversations moved to Trash";
	public static final String addressMessageNoEmailFound = "//span[contains(text(),'search options')]";
	public static final String addressEmailList = "//tr[contains(@class,'zA')]";
	public static final String addressCheckboxList = "//div[@role='checkbox']";

	//Login page
	public static final String addressButtonSingIn = "(//a[normalize-space()='Sign in'])[1]";
	public static final String addressAlreadyLoginEmail = "(//span[@role='checkbox'])[3]";
	public static final String addressFieldEmail = "(//input[@id='identifierId'])[1]";
	public static final String addressFieldPassword = "(//input[@name='Passwd'])[1]";
	public static final String addressButtonNext = "//span[contains(text(),'Next')]";
	public static final String addressTextYouAreSignedIn = "//div[normalize-space()='Complete a few suggestions to get the most out of your Google account']";
	public static final String addressButtonNotNow = "(//span[normalize-space()='Not now'])[1]";
	public static final String addressButtonUseAnotherAccount = "(//div[contains(text(),'Use another account')])[1]";
	public String searchBox_1_RU_address = "//input[contains(@placeholder,'Search')]";
	public String alertAddress_RU = "//div[@role='alert']";
	public static final String checkboxBasedOnGivenEmailSubjectStringFormat = "//td[@role='gridcell']//div[@role='link']//div//div//span[text()='%s']/ancestor::td/preceding-sibling::td[@data-tooltip='Select']";

	public static final By emailSubjectList = By
			.xpath("//td[@role='gridcell']//div[@role='link']//div//div//span/span");
	public static final String emailSubjectStringFormat = "//td[@role='gridcell']//div[@role='link']//div//div//span[text()='%s']";

	public static void main(String[] args) {
		System.out.println(getAttachmentTilteGivenScheduleReportSubjectByLocator("This is Subject"));
	}

	public static By getAttachmentTilteGivenScheduleReportSubjectByLocator(String ScheduleReportSubject) {
		final String emailAttachementTitleWithBasedOnGivenSubject = "//td[@role='gridcell']//div[@role='link']//div//div//span[text()='"
				+ ScheduleReportSubject + "']/ancestor::td[@role='gridcell']//div[2]//span[2]";
		System.out.println("Created path: " + emailAttachementTitleWithBasedOnGivenSubject);
		return By.xpath(emailAttachementTitleWithBasedOnGivenSubject);
	}

	String searchKey = "alert";
	String EmailSenderName = searchKey;
	int searchKeyColumnIndex = 1;
	boolean wantToGetMatchedRecords = true;

	// TO DELETE EMAILS
	@Test(enabled = false, priority = 1)
	public void test_DelteEmails() throws Throwable {
		getWebDriver().get("https://mail.google.com");
		callMeToClickOnAnyButtonWithNameAndXpath(getWebDriver(), "Inbox Tab", addressButtonInbox);
		deleleEmails(EmailSenderName, searchKey, searchKeyColumnIndex, wantToGetMatchedRecords);
	}

	boolean isNewThread = false;
	boolean isEmailVerificationDone = false;

	@Override
	public void run() {
		System.out.println("New thread started");
		isNewThread = true;
		LocalTime localTime = LocalTime.now();

		String reportTime = System.getProperty("reportGenerationTime");

		LocalTime timeSettedWhileReportSchedule = LocalTime.parse(reportTime);

		Duration duration = Duration.between(localTime, timeSettedWhileReportSchedule);
		int minutes = (int) (duration.toMinutes());
		long seconds = (int) (duration.toSeconds());
		if (seconds < 0) {
			seconds = Math.abs(seconds); // Convert to positive value
		}
		System.out.println("Duration in minutes: " + minutes + " and total seconds: " + seconds);

		try {
			System.out
					.println("Thread going to sleep:" + Thread.currentThread().getName() + " time: " + LocalTime.now());
			Thread.sleep(seconds * 1000);
			System.out
					.println("Thread to sleep till:" + Thread.currentThread().getName() + " time: " + LocalTime.now());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (reportTime != null) {
			try {
				// To set the getWebDriver() and start the new Chrome instance in debugger mode
				setUpWebDriverInDebuggerMode();

				// To run the test case
				test_VerifyScheduleReportSubjectAndAttachmentTitleThanDeleteIt();
				isEmailVerificationDone = true;
				System.setProperty("isEmailVerificationDone", isEmailVerificationDone + "");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("reportGenerationTime is not set in system properties.");
		}

	}

	// TO DELETE EMAILS
	@Description("It is used to cross verify the schedule Report subject and attachment title on the email")
	@Test
	public void test_VerifyScheduleReportSubjectAndAttachmentTitleThanDeleteIt() throws Throwable {
		getWebDriver().get("https://mail.google.com");

		String expectedScheduleReportSubject = System.getProperty("scheduleReportSubject");
		String expectedAttachmentTitle = System.getProperty("attachmentTitle");
		System.out.println("expectedScheduleReportSubject: " + expectedScheduleReportSubject
				+ " and expectedAttachmentTitle: " + expectedAttachmentTitle);
		if (expectedScheduleReportSubject == null || expectedScheduleReportSubject == "") {
			throw new SkipException("Expected scheduleReportSubject found null");
		}
		if (expectedAttachmentTitle == null || expectedAttachmentTitle == "") {
			throw new SkipException("Expected AttachmentTitle found null");
		}

		String attachmentTitle = null;
		String updated = null;
		String expected = null;
		String subject = null;
		callMeToClickOnAnyButtonWithNameAndXpath(getWebDriver(), "Inbox Tab", addressButtonInbox);
		try {
			subject = getWebDriver()
					.findElement(By.xpath(String.format(emailSubjectStringFormat, expectedScheduleReportSubject)))
					.getText();
			if (isNewThread) {
				sharedSoftAssert.assertEquals(subject, expectedScheduleReportSubject, "❌ Both value not matched");
			}else {
				TestBase.getSoftAssert().assertEquals(subject, expectedScheduleReportSubject, "❌ Both value not matched");
			}
			
		} catch (Exception e) {
			if (isNewThread) {
				sharedSoftAssert.fail(
						"❌ The mail not found for the given schedule report subject: " + expectedScheduleReportSubject);

			} else {
				TestBase.getSoftAssert().fail(
						"❌ The mail not found for the given schedule report subject: " + expectedScheduleReportSubject);
			}
			return;
		}
		try {
			attachmentTitle = getWebDriver()
					.findElement(getAttachmentTilteGivenScheduleReportSubjectByLocator(expectedScheduleReportSubject))
					.getText();
			if (attachmentTitle == null || attachmentTitle.isEmpty()) {
				if (isNewThread) {
					sharedSoftAssert
					.fail("The attachemnt not found for the given schedule reports: " + expectedAttachmentTitle);
				}else {
					TestBase.getSoftAssert()
					.fail("The attachemnt not found for the given schedule reports: " + expectedAttachmentTitle);
				}
				
				return;
			}

			int size = attachmentTitle.length();
			updated = attachmentTitle.substring(0, size - 27).replaceAll("_", "").toLowerCase();
			expected = expectedAttachmentTitle.replaceAll("_", "").replaceAll(" ", "").toLowerCase();
			if (isNewThread) {
				sharedSoftAssert.assertEquals(updated, expected, "❌ Both value not matched");
			}else {
				TestBase.getSoftAssert().assertEquals(updated, expected, "❌ Both value not matched");
			}
			
			if (updated.equalsIgnoreCase(expected)) {
				System.out.println("✅ Email verification done successfully.. Actual attachment title in lower case: "
						+ updated
						+ " and expected in lower case: " + expected);
			} else {
				System.out.println("❌ Email verification failed.. Actual attachment title in lower case: "
						+ updated + " and expected in lower case: " + expected);
			}
		} catch (Exception e) {
			if (isNewThread) {
				sharedSoftAssert
				.fail("❌ The mail is present for the given schedule report subject: "
						+ expectedScheduleReportSubject
						+ ", but the given attachement not found: " + expectedAttachmentTitle);
			}else {
				TestBase.getSoftAssert()
				.fail("❌ The mail is present for the given schedule report subject: "
						+ expectedScheduleReportSubject
						+ ", but the given attachement not found: " + expectedAttachmentTitle);
			}
		}

		if (updated != null && expected != null && updated.equals(expected)) {
			try {
				int count = 1;
				do {
					try {
						System.out.println("Checking the email presence in the iteration: " + count);
						WebDriverWait wait = null;
						if (count == 1) {
							// this is valid for the selenium version: 4.26
							//wait = new WebDriverWait(driver, Duration.ofSeconds(5));
							wait = new WebDriverWait(driver, 5); // [selenium 3.14]
						} else {
							System.out.println("Thread going to wait dynamically next 10 seconds");
							// this is valid for the selenium version: 4.26
							// wait = new WebDriverWait(driver, Duration.ofSeconds(10));
							wait = new WebDriverWait(driver, 5);// [selenium 3.14]
						}
						WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String
								.format(checkboxBasedOnGivenEmailSubjectStringFormat, expectedScheduleReportSubject))));
						checkbox.click();
						getWebDriver().findElement(btnDeleteIcon).click();
						break;
					} catch (Exception e) {
						System.out.println("Email not found in the iteration: " + count);
					}
				} while (count++ <= 2);

			} catch (Exception e) {
				if (isNewThread) {
					sharedSoftAssert.fail("❌ Email not deleted from the gmail");
			}else {
				TestBase.getSoftAssert().fail("❌ Email not deleted from the gmail");
			}
			}

			System.out.println("✅ Email deleted successfully: " + expectedScheduleReportSubject);
		}
		if (!isNewThread) {
				TestBase.getSoftAssert().assertAll();
			}
	}

	// EMAIL LIST
	@FindBy(xpath = addressEmailList)
	@CacheLookup
	List<WebElement> listEmails;

	public ArrayList<Integer> findEmailFromRowList(String EmailSenderName, String searchKey, int searchKeyColumnIndex,
			boolean wantToGetMatchedRecords) throws InterruptedException {

		ArrayList<Integer> rowNumber = new ArrayList<>();

		try {
			Thread.sleep(1000);
			rowNumber = findMatchedUnMatchedRowListCountNumber(addressEmailList, listEmails, getWebDriver(), searchKey,
					searchKeyColumnIndex, wantToGetMatchedRecords);

		} catch (Exception e) {
			System.out.println("Exception from findTenantsFromRowListAndClickOnThreeDot: " + e.getMessage());
		}
		if (wantToGetMatchedRecords) {
			System.out.println("MatchRowNumber: " + rowNumber);
		} else {
			System.out.println("UnmatchRowNumber: " + rowNumber);
		}

		return rowNumber;

	}

	// TO DELETE EMAILS
	public void deleleEmails(String EmailSenderName, String searchKey, int searchKeyColumnIndex,
			boolean wantToGetMatchedRecords) throws InterruptedException {
		Actions action = new Actions(getWebDriver());
		boolean isEmailFound = true;
		searchBox_1_RU(getWebDriver(), searchKey);
		Thread.sleep(1000);
		int breakLoopCounter = 0;

		while (isEmailFound) {
			ArrayList<Integer> rowNumber = findEmailFromRowList(EmailSenderName, searchKey, searchKeyColumnIndex,
					wantToGetMatchedRecords);

			try {
				WebElement emailFound = getWebDriver().findElement(By.xpath(addressMessageNoEmailFound));
				isEmailFound = !(emailFound.isEnabled() && emailFound.isDisplayed());
			} catch (Exception e) {
				if (!rowNumber.isEmpty()) {
					for (int rowNumberCounter : rowNumber) {
						String checkBoxFormattedAddress = "(" + addressCheckboxList + ")[" + rowNumberCounter + "]";
						WebElement checkboxCount = getWebDriver().findElement(By.xpath(checkBoxFormattedAddress));
						if (checkboxCount.isDisplayed() && checkboxCount.isEnabled()) {
							Thread.sleep(100);
							action.moveToElement(checkboxCount).click().build().perform();
							System.out.println("checkbox address: " + checkBoxFormattedAddress);
						}
					}

					boolean flag = callMeToClickOnAnyButtonWithNameAndXpath(getWebDriver(), "Delete",
							addressButtonDeleteEmail);

					if (flag) {
						snakeAlertMessagesDisplayedContent_RU();
					}
				}

			} finally {
				try {
					boolean isNextButtonEnabled = isElementEnabled(getWebDriver(), addressButton1_50_OfMany_NextArrow,
							"aria-disabled");
					if (isNextButtonEnabled) {
						callMeToClickOnAnyButtonWithNameAndXpath(getWebDriver(), "Older button",
								addressButton1_50_OfMany_NextArrow);
					} else {
						breakLoopCounter++;
						if (breakLoopCounter == 2) {
							System.out.println("No more mail present and isNextButtonEnabled: " + isNextButtonEnabled);
							break;
						}
					}

				} catch (Exception e) {
					isEmailFound = false;
					System.out.println("Exception while chacking '1-50 of Many' >> " + e.getMessage());
					break;
				}
			}

		}
		if (!isEmailFound) {
			System.out.println("All the email deleted or No Email presence now...");
		}
	}

	@BeforeTest()
	public void setUpWebDriverInDebuggerMode() {
		System.out.println("Setting up WebDriver in debugger mode...");

		// Ensure the correct version of ChromeDriver is used
		WebDriverManager.chromedriver().driverVersion("latest").setup();
		StartChromeWithRemoteDebugging.startChromeIfNotRunning(9222, "/home/uffizio/ChromeDebuggerDataGMail",
				"Profile 1");
		try {
			// Initialize WebDriver with the given Chrome options
			driver = new ChromeDriver(customizedChromeOptions(true, false, false, true, 9222));
			setWebDriver(driver);
		} catch (Exception e) {
			// Capture any errors during initialization and print the stack trace
			System.err.println("Failed to initialize WebDriver: " + e.getMessage());
		}

	}

	public boolean isElementEnabled(WebDriver driver, String elementAddress, String enableDisableIdentifier) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(elementAddress));
		Boolean isEnabled = (Boolean) jsExecutor
				.executeScript("return !arguments[0].hasAttribute('" + enableDisableIdentifier + "');", element);
		return isEnabled;
	}

	public boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String buttonName, String xpathAddress)
			throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); //this is valid for selenium version 4.26 
		WebDriverWait wait = new WebDriverWait(driver, 30);

		Actions action = new Actions(driver);

		boolean isClickedOnButton = false;
		try {
			System.out.println("Element name and xpath >> " + buttonName + " and >> " + xpathAddress);
			WebElement button = driver.findElement(By.xpath(xpathAddress));
			if (button.isEnabled() && button.isDisplayed()) {

				wait.until(ExpectedConditions.elementToBeClickable(button));

				action.moveToElement(button).click().build().perform();
				isClickedOnButton = true;
				System.out.println("clicke on button >> " + buttonName);
				Thread.sleep(1000);

			}

		} catch (Exception e) {
			System.out.println("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
		}
		return isClickedOnButton;
	}

	public String snakeAlertMessagesDisplayedContent_RU() throws InterruptedException {
		// logger.info("Alert - Try");
		String alertMessageContent_RU;
		WebElement alertSnakeMessage = null;
		int alertLoopCount = 0;
		String exception = null;
		boolean flag = false;
		while (alertLoopCount <= 5000) {
			alertLoopCount++;
			try {
				alertSnakeMessage = driver.findElement(By.xpath(alertAddress_RU));
				flag = alertSnakeMessage.isDisplayed();
				if (flag) {
					System.out.println(
							"Checking alert on intervel of 150 milliSeconds and loop count is: " + alertLoopCount);
					System.out.println("Alert message is displayed: " + flag);
					alertMessageContent_RU = alertSnakeMessage.getText();
					System.out.println("===>>> Alert Message Content: " + alertMessageContent_RU);
					return alertMessageContent_RU;
				} else {
					Thread.sleep(100);
				}
			} catch (Exception e) {
				exception = e.getMessage();
			}

			if (flag) {
				Thread.sleep(500);
				break;
			}
			if (alertLoopCount == 5000) {
				System.out.println(
						"Alert message check frequency is 150 milli seconds and loop count is: " + alertLoopCount);
			}
		}

		System.out.println("Alert not cought exception: " + exception);
		Thread.sleep(2000);
		return "Alert Not Found";
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

	public void searchBox_1_RU(WebDriver driver, String SearchKey) throws InterruptedException {
		Thread.sleep(200);
		WebElement searchBox_1_RU = driver.findElement(By.xpath(searchBox_1_RU_address));
		searchBox_1_RU.sendKeys(Keys.CONTROL, "a");
		searchBox_1_RU.sendKeys(Keys.DELETE);
		searchBox_1_RU.sendKeys(SearchKey, Keys.ENTER);
		System.out.println("Searched the search keys in the search box: " + SearchKey);
	}

	public static ArrayList<Integer> findMatchedUnMatchedRowListCountNumber(String list_Address,
			List<WebElement> listElement, WebDriver driver, String searchKey, int searchKeyColumnIndex,
			boolean wantToGetMatchedRecords) throws InterruptedException {

		Actions action = new Actions(driver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();
		System.out.println("findThreedActionButtonAndClick method called and Caller method name: " + callerMethodName);
		System.out.println("wantToGetMatchedRecords: " + wantToGetMatchedRecords);

		List<WebElement> listElement_Inner = driver.findElements(By.xpath(list_Address));

		ArrayList<Integer> matchedRowCount = new ArrayList<>();
		ArrayList<Integer> unmatchedRowCount = new ArrayList<>();
		int listRowCount = 0;
		boolean isSearchKeyMatched = false;

		for (WebElement element : listElement_Inner) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String firstColumnText = text[0];
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					//logger.info("formatText: " + formatText);
					//logger.info("searchKey: " + searchKey);

					if (formatText.equalsIgnoreCase(searchKey)) {
						isSearchKeyMatched = true;
						System.out.println("isSearchKeyMatched: " + isSearchKeyMatched);
						matchedRowCount.add(listRowCount);
					} else {
						unmatchedRowCount.add(listRowCount);
					}

				}

			}
		}

		if (wantToGetMatchedRecords) {
			return matchedRowCount;
		} else {
			return unmatchedRowCount;
		}
	}

	@AfterTest()
	public void Teardown() {
		driver.quit();
		System.out.println("Driver shutdown");
	}

}
