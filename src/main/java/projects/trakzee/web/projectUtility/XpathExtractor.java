package projects.trakzee.web.projectUtility;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class XpathExtractor {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static String getXpathValueFromByDataType(By byLocators) {
		String xpathValue = byLocators.toString();
		if (xpathValue.startsWith("By.xpath: ")) {
			xpathValue = xpathValue.replace("By.xpath: ", "");
		}
		return xpathValue;
	}

	public static By convertXpathToBy(String xpathValue) {
		if (xpathValue == null || xpathValue.isEmpty()) {
			throw new IllegalArgumentException("The provided XPath value is null or empty.");
		}
		return By.xpath(xpathValue);
	}

}
