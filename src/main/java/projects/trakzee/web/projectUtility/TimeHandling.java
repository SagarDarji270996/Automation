package projects.trakzee.web.projectUtility;

import java.time.Duration;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeHandling {

	public static void localTimeDifference(LocalTime startTime, LocalTime endTime) {
		Duration duration = Duration.between(startTime, endTime);
		long hours = duration.toHours();
		int minutes = (int) (duration.toMinutes() % 60);
		int seconds = (int) (duration.getSeconds() % 60);
		System.out.println(
				"Time Difference: " + hours + " hours in long, " + minutes + " minutes in int, " + seconds
						+ " seconds in int");
	}

	public static double getPageLoadingIconVisibilityAndInvisibilityTimeDifference(WebDriver driver, By locator,
			int maxWaitTimeInSeconds) {
		long startTime = System.currentTimeMillis(); // Record the start time
		WebDriverWait wait = null;
		try {
			// First check loading icon presence
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait for invisibility

			// Then check for invisibility
			wait = new WebDriverWait(driver, maxWaitTimeInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); // Wait for invisibility
		} catch (Exception e) {
			System.out.println("Element did not disappear within " + maxWaitTimeInSeconds + " seconds.");
			e.printStackTrace(); // Log exception details
			return maxWaitTimeInSeconds; // Return max wait time
		}

		long endTime = System.currentTimeMillis(); // Record the end time
		long totalTimeMillis = endTime - startTime; // Calculate total time in milliseconds

		double seconds = totalTimeMillis / 1000.0; // Convert to seconds
		System.out.println("Max loading time in seconds is: " + seconds);

		return seconds; // Return the wait time
	}

}
