package projects.trakzee.web.projectUtility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class DatePickerUtils {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	private WebDriver driver;
	private WebDriverWait wait;

	public DatePickerUtils(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void selectFromAndToDate(By dateField, int daysOffset) {
		LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
		int year = targetDate.getYear();
		int month = targetDate.getMonthValue();
		int day = targetDate.getDayOfMonth();
		WebElement dateInputField = driver.findElement(dateField);
		dateInputField.click();
		WebElement yearDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		List<WebElement> years = driver.findElements(By.xpath("//select[@class='ui-datepicker-year']/option"));
		for (WebElement yearOption : years) {
			if (yearOption.getText().equals(String.valueOf(year))) {
				yearOption.click();
				break;
			}
		}

		WebElement monthDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		List<WebElement> months = driver.findElements(By.xpath("//select[@class='ui-datepicker-month']/option"));
		for (WebElement monthOption : months) {
			if (monthOption.getText().equals(getMonthName(month))) {
				monthOption.click();
				break;
			}
		}

		String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day
				+ "']";
		List<WebElement> dayElements = driver.findElements(By.xpath(dayXPath));
		if (dayElements.isEmpty()) {
			throw new NoSuchElementException("The specified day is not selectable in the calendar.");
		}
		dayElements.get(0).click();
	}

	private static String getMonthName(int month) {
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return monthNames[month - 1];
	}

	public void selectDate(By dateField, int daysOffset) {
		LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
		int year = targetDate.getYear();
		int month = targetDate.getMonthValue();
		int day = targetDate.getDayOfMonth();
		WebElement dateInputField = driver.findElement(dateField);
		dateInputField.click();
		WebElement yearDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		List<WebElement> years = driver.findElements(By.xpath("//select[@class='ui-datepicker-year']/option"));
		for (WebElement yearOption : years) {
			if (yearOption.getText().equals(String.valueOf(year))) {
				yearOption.click();
				break;
			}
		}
		WebElement monthDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		List<WebElement> months = driver.findElements(By.xpath("//select[@class='ui-datepicker-month']/option"));
		for (WebElement monthOption : months) {
			if (monthOption.getText().equals(getMonthName(month))) {
				monthOption.click();
				break;
			}
		}
		String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day
				+ "']";
		List<WebElement> dayElements = driver.findElements(By.xpath(dayXPath));
		if (dayElements.isEmpty()) {
			throw new NoSuchElementException("The specified day is not selectable in the calendar.");
		}
		dayElements.get(0).click();
	}

	public void selectDateOfBirth18YearsAgo(By dateField) {
		// Calculate the date 18 years ago from today
		LocalDate targetDate = LocalDate.now().minusYears(18);
		int year = targetDate.getYear();
		int month = targetDate.getMonthValue();
		int day = targetDate.getDayOfMonth();

		// Open the date picker
		WebElement dateInputField = driver.findElement(dateField);
		dateInputField.click();

		// Select the year
		WebElement yearDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		List<WebElement> years = driver.findElements(By.xpath("//select[@class='ui-datepicker-year']/option"));
		for (WebElement yearOption : years) {
			if (yearOption.getText().equals(String.valueOf(year))) {
				yearOption.click();
				break;
			}
		}

		// Select the month
		WebElement monthDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		List<WebElement> months = driver.findElements(By.xpath("//select[@class='ui-datepicker-month']/option"));
		for (WebElement monthOption : months) {
			if (monthOption.getText().equals(getMonthName(month))) {
				monthOption.click();
				break;
			}
		}

		// Select the day
		String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day
				+ "']";
		List<WebElement> dayElements = driver.findElements(By.xpath(dayXPath));
		if (dayElements.isEmpty()) {
			throw new NoSuchElementException("The specified day is not selectable in the calendar.");
		}
		dayElements.get(0).click();
	}

	public static void pickDateWhenStartAndEndDateAreTogether(WebDriver driver, String startDate, String endDate,
			String dateSeparator, By listDateGridLocatorForStart, By listDateGridLocatorForEnd, By nextButtonLocator,
			By previousButtonLocator, By monthYearLocatorForStart, By monthYearLocatorForEnd,
			By finalOKOrSubmitOrApplyButtonLocator, By cancelButtonLocator) {
		// METHOD: To pick date when start and date are together with month and year are together[not in dropdown] and date are in grid format
		//Note: date pick picker is already open

		LocalDate start;
		LocalDate end;
		try {
			String normalizedStartDate = startDate.replace(dateSeparator, "/");
			String normalizedEndDate = endDate.replace(dateSeparator, "/");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			start = LocalDate.parse(normalizedStartDate, formatter);
			end = LocalDate.parse(normalizedEndDate, formatter);
		} catch (DateTimeParseException e) {
			// Date formatter to parse the input dates
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd" + dateSeparator + "MM" + dateSeparator + "yyyy");
			start = LocalDate.parse(startDate, formatter);
			end = LocalDate.parse(endDate, formatter);
		}

		System.out.println("Start date: " + start);
		System.out.println("End date: " + end);

		// Select the start date
		boolean isStartDateSelected = selectDate(driver, start, listDateGridLocatorForStart, nextButtonLocator,
				previousButtonLocator, monthYearLocatorForStart);
		if (isStartDateSelected) {
			System.out.println("Start date selected successfully");
		}

		// Select the end date
		boolean isEndDateSelected = selectDate(driver, end, listDateGridLocatorForEnd, nextButtonLocator,
				previousButtonLocator, monthYearLocatorForEnd);
		if (isEndDateSelected) {
			System.out.println("End date selected successfully");
		}
		if (isStartDateSelected && isEndDateSelected) {
			driver.findElement(finalOKOrSubmitOrApplyButtonLocator).click();
			System.out.println("Clicked on the finaly OK or Submit or Apply button");
		} else {
			driver.findElement(cancelButtonLocator).click();
			System.out.println("Clicked on cancel button");
		}

	}

	private static boolean selectDate(WebDriver driver, LocalDate date, By dateGridLocator, By nextButtonLocator,
			By previousButtonLocator, By monthYearLocator) {
		// Extract the target year and month
		boolean isDatePicked = false;

		int targetYear = Integer.parseInt("" + date.getYear());
		int targetMonth = Integer.parseInt("" + date.getMonthValue());
		String targetDay = "" + date.getDayOfMonth();
		System.out.println("TargetYear: " + targetYear);
		System.out.println("TargetMonth: " + targetMonth);
		System.out.println("TargetDay: " + targetDay);

		while (true) {
			// Get the currently displayed month and year
			WebElement monthYearElement = driver.findElement(monthYearLocator);
			String displayedMonthYear = monthYearElement.getText();
			System.out.println("Displayed month year: " + displayedMonthYear);

			// Convert the displayed month and year into a LocalDate for comparison
			//			DateTimeFormatter displayedFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
			//			LocalDate displayedDate = LocalDate.parse("01 " + displayedMonthYear, displayedFormatter);

			int displayedYear = Integer.parseInt(displayedMonthYear.split(" ")[1]);
			String displayedMonth = displayedMonthYear.split(" ")[0];
			int displayedMonthNumber = getMonthNumber(displayedMonth);

			//			int displayedYear = displayedDate.getYear();
			//			int displayedMonth = displayedDate.getMonthValue();
			System.out.println("Displayed year: " + displayedYear);
			System.out.println("Displayed month: " + displayedMonth);
			if (displayedYear == targetYear && displayedMonthNumber == targetMonth) {
				// Select the target day
				List<WebElement> dateElements = driver.findElements(dateGridLocator);
				System.out.println("DateElements list size: " + dateElements.size());
				for (WebElement dateElement : dateElements) {
					String dateValue = dateElement.getText();
					if (dateValue.equals(String.valueOf(targetDay))) {
						dateElement.click();
						System.out.println("Date selected: " + dateValue);
						return true;
					}
				}
			} else if (displayedYear < targetYear
					|| displayedYear == targetYear && displayedMonthNumber < targetMonth) {
				// Move to the next month
				driver.findElement(nextButtonLocator).click();
			} else {
				// Move to the previous month
				driver.findElement(previousButtonLocator).click();
			}
		}

	}

	private static int getMonthNumber(String monthName) {
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (int i = 0; i < monthNames.length; i++) {
			if (monthNames[i].equalsIgnoreCase(monthName)
					|| monthNames[i].substring(0, 3).equalsIgnoreCase(monthName)) {
				return i + 1; // Return the month number (1-based index)
			}
		}
		throw new IllegalArgumentException("Invalid month name: " + monthName);
	}

	public static boolean selectDate(WebDriver driver, String date, By dateGridLocator, By nextButtonLocator,
			By previousButtonLocator, By monthYearLocator, String seperator) {

		System.out.println("Given date: " + date + " and seperator is: " + seperator);

		// Convert String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d" + seperator + "M" + seperator + "yyyy");
		LocalDate targetDate = LocalDate.parse(date, formatter);

		boolean isDatePicked = false;

		int targetYear = Integer.parseInt("" + targetDate.getYear());
		int targetMonth = Integer.parseInt("" + targetDate.getMonthValue());
		String targetDay = "" + targetDate.getDayOfMonth();
		System.out.println("TargetYear: " + targetYear);
		System.out.println("TargetMonth: " + targetMonth);
		System.out.println("TargetDay: " + targetDay);

		while (true) {
			// Get the currently displayed month and year
			WebElement monthYearElement = driver.findElement(monthYearLocator);
			String displayedMonthYear = monthYearElement.getText();
			System.out.println("Displayed month year: " + displayedMonthYear);

			// Convert the displayed month and year into a LocalDate for comparison
			//			DateTimeFormatter displayedFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
			//			LocalDate displayedDate = LocalDate.parse("01 " + displayedMonthYear, displayedFormatter);

			int displayedYear = Integer.parseInt(displayedMonthYear.split(" ")[1]);
			String displayedMonth = displayedMonthYear.split(" ")[0];
			int displayedMonthNumber = getMonthNumber(displayedMonth);

			//			int displayedYear = displayedDate.getYear();
			//			int displayedMonth = displayedDate.getMonthValue();
			System.out.println("Displayed year: " + displayedYear);
			System.out.println("Displayed month: " + displayedMonth);
			if (displayedYear == targetYear && displayedMonthNumber == targetMonth) {
				// Select the target day
				List<WebElement> dateElements = driver.findElements(dateGridLocator);
				System.out.println("DateElements list size: " + dateElements.size());
				for (WebElement dateElement : dateElements) {
					String dateValue = dateElement.getText();
					if (dateValue.equals(String.valueOf(targetDay))) {
						dateElement.click();
						System.out.println("Date selected: " + dateValue);
						return true;
					}
				}
			} else if (displayedYear < targetYear
					|| displayedYear == targetYear && displayedMonthNumber < targetMonth) {
				// Move to the next month
				driver.findElement(nextButtonLocator).click();
			} else {
				// Move to the previous month
				driver.findElement(previousButtonLocator).click();
			}
		}
	}

	/*This is used when, in the calendar picker month and year present inside the dropdown and date are in the greed format*/
	public static boolean selectDate(WebDriver driver, String date, By dateGridLocatorOrDateList, By nextButtonLocator,
			By previousButtonLocator, By monthLocatorDropdownListOrSelectTagAddress,
			By yearLocatorDropdownListOrSelectTagAddress, String separator, boolean isSelectTagYearAndMonth) {
		try {
			// Split the input date into day, month, and year
			String[] dateParts = date.split(separator);
			int day = Integer.parseInt(dateParts[0]);
			String month = dateParts[1];
			String year = dateParts[2];

			if (isSelectTagYearAndMonth) {
				// Select the year
				WebElement yearDropdown = driver.findElement(yearLocatorDropdownListOrSelectTagAddress);
				Select yearSelect = new Select(yearDropdown);
				yearSelect.selectByVisibleText(year);
				System.out.println("Selected Year: " + year);

				// Select the month
				WebElement monthDropdown = driver.findElement(monthLocatorDropdownListOrSelectTagAddress);
				Select monthSelect = new Select(monthDropdown);
				try {
					monthSelect.selectByVisibleText(month);
				} catch (NoSuchElementException e) {
					monthSelect.selectByVisibleText(getMonthName(Integer.parseInt(month)));
				}
				System.out.println("Selected month: " + month);
			} else {
				//to select year
				List<WebElement> yearList = driver.findElements(yearLocatorDropdownListOrSelectTagAddress);
				for (WebElement yearElement : yearList) {
					if (yearElement.getText().equals(String.valueOf(year))) {
						yearElement.click();
						System.out.println("Selected Year: " + year);
						return true; // Date selected successfully
					}
				}

				// to select month
				List<WebElement> monthList = driver.findElements(monthLocatorDropdownListOrSelectTagAddress);
				for (WebElement monthElement : monthList) {
					if (monthElement.getText().equals(String.valueOf(month))) {
						monthElement.click();
						System.out.println("Selected month: " + month);
						return true; // Date selected successfully
					}
				}

			}


			// Find the correct day from the date grid
			List<WebElement> dateList = driver.findElements(dateGridLocatorOrDateList);
			for (WebElement dateElement : dateList) {
				if (dateElement.getText().equals(String.valueOf(day))) {
					dateElement.click();
					System.out.println("Selected date: " + day);
					return true; // Date selected successfully
				}
			}

			return false; // If the date is not found
		} catch (Exception e) {
			e.printStackTrace();
			return false; // In case of failure
		}
	}

	public static boolean selectDateWithHourAndMinute(WebDriver driver, String date, By dateGridLocator,
			By nextButtonLocator, By previousButtonLocator, By monthYearLocator, String seperator) {
		// Your date in string format (Example: "23-02-2025")

		// Convert String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d" + seperator + "M" + seperator + "yyyy"); // Adjust format as per your string
		LocalDate targetDate = LocalDate.parse(date, formatter);

		boolean isDatePicked = false;

		int targetYear = Integer.parseInt("" + targetDate.getYear());
		int targetMonth = Integer.parseInt("" + targetDate.getMonthValue());
		String targetDay = "" + targetDate.getDayOfMonth();
		System.out.println("TargetYear: " + targetYear);
		System.out.println("TargetMonth: " + targetMonth);
		System.out.println("TargetDay: " + targetDay);

		while (true) {
			// Get the currently displayed month and year
			WebElement monthYearElement = driver.findElement(monthYearLocator);
			String displayedMonthYear = monthYearElement.getText();
			System.out.println("Displayed month year: " + displayedMonthYear);

			// Convert the displayed month and year into a LocalDate for comparison
			//			DateTimeFormatter displayedFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
			//			LocalDate displayedDate = LocalDate.parse("01 " + displayedMonthYear, displayedFormatter);

			int displayedYear = Integer.parseInt(displayedMonthYear.split(" ")[1]);
			String displayedMonth = displayedMonthYear.split(" ")[0];
			int displayedMonthNumber = getMonthNumber(displayedMonth);

			//			int displayedYear = displayedDate.getYear();
			//			int displayedMonth = displayedDate.getMonthValue();
			System.out.println("Displayed year: " + displayedYear);
			System.out.println("Displayed month: " + displayedMonth);
			if (displayedYear == targetYear && displayedMonthNumber == targetMonth) {
				// Select the target day
				List<WebElement> dateElements = driver.findElements(dateGridLocator);
				System.out.println("DateElements list size: " + dateElements.size());
				for (WebElement dateElement : dateElements) {
					String dateValue = dateElement.getText();
					if (dateValue.equals(String.valueOf(targetDay))) {
						dateElement.click();
						System.out.println("Date selected: " + dateValue);
						return true;
					}
				}
			} else if (displayedYear < targetYear
					|| displayedYear == targetYear && displayedMonthNumber < targetMonth) {
				// Move to the next month
				driver.findElement(nextButtonLocator).click();
			} else {
				// Move to the previous month
				driver.findElement(previousButtonLocator).click();
			}
		}

	}

}