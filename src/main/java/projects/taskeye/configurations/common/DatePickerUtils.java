package projects.taskeye.configurations.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatePickerUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

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

        String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day + "']";
        List<WebElement> dayElements = driver.findElements(By.xpath(dayXPath));
        if (dayElements.isEmpty()) {
            throw new NoSuchElementException("The specified day is not selectable in the calendar.");
        }
        dayElements.get(0).click();
    }

    private String getMonthName(int month) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
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
        String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day + "']";
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
        String dayXPath = "//td[not(contains(@class, 'ui-datepicker-unselectable'))]//a[normalize-space()='" + day + "']";
        List<WebElement> dayElements = driver.findElements(By.xpath(dayXPath));
        if (dayElements.isEmpty()) {
            throw new NoSuchElementException("The specified day is not selectable in the calendar.");
        }
        dayElements.get(0).click();
    }

}