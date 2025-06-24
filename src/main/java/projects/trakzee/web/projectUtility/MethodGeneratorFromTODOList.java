package projects.trakzee.web.projectUtility;

import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class MethodGeneratorFromTODOList {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static void generateMethodsFromSteps(String[] testSteps, String methodType) {
		for (String step : testSteps) {
			// Extract the method name from the test step
			String methodName = createMethodName(step);
			methodType = methodType.toUpperCase();
			// Generate the method template
			String methodTemplate = String.format(
					"public void %s() {\n" + "\t\t\t" + "// TODO " + methodType + ": " + "%s\n" + "\n\t\t}\n",
					methodName, step.trim());

			// Print the generated method
			System.out.println(methodTemplate);
		}
	}

	// Helper method to create a method name from the test step
	private static String createMethodName(String step) {

		// Split the step into words
		String[] words = step.split("\\s+");

		// Convert the first word to lowercase and capitalize the others
		StringBuilder methodName = new StringBuilder(words[0].toLowerCase());
		for (int i = 1; i < words.length; i++) {
			methodName.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
		}

		return methodName.toString();
	}

}
