package projects.trakzee.web.projectUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class StringHandling {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static void main(String[] args) {
		// Example map
		Map<String, String> stringMap = Map.of("\\d", "", "-", "", "__PM", "New");

		// Update the string
		String string = "Vehicle_Location_Status_23-01-2025_05-59-10_PM.pdf";
		String updatedString = updateString(string, stringMap);
		System.out.println("After update: " + updatedString);
	}

	public static String updateString(String baseString, Map<String, String> stringMap) {
		for (Map.Entry<String, String> entry : stringMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (baseString != null) {
				baseString = baseString.replaceAll(key, value); // Reassign the result
			}
			if (baseString.contains(key)) {
				baseString.replace(key, value);
			}
		}
		return baseString;
	}

	public static List<String> convertStringToArrayListIfValuePresentInArray(String input) {
		if (input == null || input.isEmpty()) {
			return new ArrayList<>(); // Return empty list for null/empty input
		}

		return Arrays.stream(input.replaceAll("[\\[\\]]", "").split(",")).map(String::trim)
				.collect(Collectors.toList());
	}

	public static String toCamelCase(String input) {
		input = input.toLowerCase().trim();
		StringBuilder camelCaseString = new StringBuilder();
		boolean capitalizeNext = false;

		for (char c : input.toCharArray()) {
			if (Character.isWhitespace(c) || c == '_' || c == '-') {
				capitalizeNext = true;
			} else if (capitalizeNext) {
				camelCaseString.append(Character.toUpperCase(c));
				capitalizeNext = false;
			} else {
				camelCaseString.append(c);
			}
		}
		return camelCaseString.toString();
	}

	public static Map<String, Object> convertKeysToCamelCase(Map<String, Object> inputMap) {
		Map<String, Object> modifiedMap = new LinkedHashMap<>(); // Preserve order

		for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
			String camelCaseKey = toCamelCase(entry.getKey()); // Convert to camel case
			modifiedMap.put(camelCaseKey, entry.getValue()); // Store with modified key
		}

		return modifiedMap; // Return updated map
	}

}
