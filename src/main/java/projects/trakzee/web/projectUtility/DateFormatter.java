package projects.trakzee.web.projectUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.testng.asserts.SoftAssert;

public class DateFormatter {

	static SoftAssert softAssert = new SoftAssert();

	public static String DateToUTC(String startEndType, String inputDate) throws ParseException {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.withZone(ZoneId.of("UTC"));

		LocalDateTime localDateTime = LocalDateTime.parse(inputDate, inputFormatter);

		if ("start".equalsIgnoreCase(startEndType)) {
			localDateTime = localDateTime.withHour(18).withMinute(30).withSecond(0);
		} else if ("end".equalsIgnoreCase(startEndType)) {
			localDateTime = localDateTime.withHour(18).withMinute(29).withSecond(59);
		}

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("UTC"));

		return outputFormatter.format(zonedDateTime);
	}

	// TO FORMAT DATE INTO DATABASE FORMAT
	public static String formatDate(String startEndType, String date) {
		String inputDate = date;

		// DATETIME STAMP UTC FORMAT
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String utcTimestamp = null;
		try {
			// Parse the input date string
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // Set the time zone to UTC
			calendar.setTime(dateFormat.parse(inputDate));

			if ("start".equals(startEndType)) {
				// Check if the time is earlier than 18:30:00, and if so, subtract a day
				if (calendar.get(Calendar.HOUR_OF_DAY) < 18
						|| (calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) < 30)) {
					calendar.add(Calendar.DAY_OF_MONTH, -1);
				}
				// Set the time to 18:30:00
				calendar.set(Calendar.HOUR_OF_DAY, 18);
				calendar.set(Calendar.MINUTE, 30);
				calendar.set(Calendar.SECOND, 0);
			} else if ("end".equals(startEndType)) {
				// Set the time to 18:29:59
				calendar.set(Calendar.HOUR_OF_DAY, 18);
				calendar.set(Calendar.MINUTE, 29);
				calendar.set(Calendar.SECOND, 59);
			}

			// Convert to the desired output format
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			utcTimestamp = outputFormat.format(calendar.getTime());

		} catch (ParseException e) {
			// Handle parsing errors
			System.err.println("Error parsing date: " + e.getMessage());
		}

		return utcTimestamp;
	}

	public static SimpleDateFormat DateFormatter_MMDDYYYY(String seperator) {
		// Create an instance of the current date
		Date currentDate = new Date();

		// Define the format you want (MM-dd-yyyy)
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM" + seperator + "dd" + seperator + "yyyy");

		// Format the current date according to the pattern
		String formattedDate = dateFormat.format(currentDate);

		// Print the formatted date
		System.out.println("Formatted Date: " + formattedDate);
		return dateFormat;

	}

	public static SimpleDateFormat DateFormatter_DDMMYYYY(String seperator) {
		// Create an instance of the current date
		Date currentDate = new Date();

		// Define the format you want (MM-dd-yyyy)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd" + seperator + "MM" + seperator + "yyyy");

		// Format the current date according to the pattern
		String formattedDate = dateFormat.format(currentDate);

		// Print the formatted date
		System.out.println("Formatted Date: " + formattedDate);
		return dateFormat;

	}

	public static SimpleDateFormat DateFormatter_YYYYMMDD(String seperator) {
		// Create an instance of the current date
		Date currentDate = new Date();

		// Define the format you want (MM-dd-yyyy)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy" + seperator + "MM" + seperator + "dd");

		// Format the current date according to the pattern
		String formattedDate = dateFormat.format(currentDate);

		// Print the formatted date
		System.out.println("Formatted Date: " + formattedDate);
		return dateFormat;

	}

	public static SimpleDateFormat DateFormatter_YYYYDDMM(String seperator) {
		// Create an instance of the current date
		Date currentDate = new Date();

		// Define the format you want (MM-dd-yyyy)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy" + seperator + "dd" + seperator + "MM");

		// Format the current date according to the pattern
		String formattedDate = dateFormat.format(currentDate);

		// Print the formatted date
		System.out.println("Formatted Date: " + formattedDate);
		return dateFormat;

	}

//	public static void main(String[] args) {
//		String date = formatDate("start", "15 September 2023");
//		System.out.println(date);
//
//		DateFormatter_YYYYDDMM("-");
//	}

	// Method to compare two date-time strings with a tolerance
	public static boolean areDatesSameWithinTolerance(String dateRange1, String dateRange2,String startEndTimeSeperator, long toleranceMinutes) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		// Split the date range string into start and end times
		String[] date1Parts = dateRange1.split(startEndTimeSeperator);
		String[] date2Parts = dateRange2.split(startEndTimeSeperator);

		if (date1Parts.length != 2 || date2Parts.length != 2) {
			throw new IllegalArgumentException("Date range format is incorrect.");
		}

		// Parse the start and end times into LocalDateTime objects
		LocalDateTime startDate1 = LocalDateTime.parse(date1Parts[0], formatter);
		LocalDateTime endDate1 = LocalDateTime.parse(date1Parts[1], formatter);

		LocalDateTime startDate2 = LocalDateTime.parse(date2Parts[0], formatter);
		LocalDateTime endDate2 = LocalDateTime.parse(date2Parts[1], formatter);

		// Calculate the difference between the end times of the two date ranges
		Duration StartDuration = Duration.between(startDate1, startDate2);
		softAssert.assertTrue(Math.abs(StartDuration.toMinutes()) <= toleranceMinutes,
				"The Start time difference not within tolerance");
		
		// Calculate the difference between the end times of the two date ranges
		Duration EndDuration = Duration.between(endDate1, endDate2);
		softAssert.assertTrue(Math.abs(EndDuration.toMinutes()) <= toleranceMinutes,
				"The End time difference not within tolerance");
		
		// Check if the difference is within the tolerance
		return Math.abs(EndDuration.toMinutes()) <= toleranceMinutes;
	}

	public static void main(String[] args) {

		// Ask the user to input the two date-time strings
		String date1 = "10-12-2024 00:00:00 - 10-12-2024 18:50:12";
		String date2 = "10-12-2024 00:00:00 - 10-12-2024 18:51:12";
		long tolerance = 2;

		// Call the method to compare the dates
		boolean areSame = areDatesSameWithinTolerance(date1, date2," - " ,tolerance);

		// Display the result
		if (areSame) {
			System.out.println("The dates are the same within the tolerance.");
		} else {
			System.out.println("The dates are not the same within the tolerance.");
		}

	}

}
