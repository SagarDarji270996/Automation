package projects.trakzee.web.pages.reports.activity;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.common.PL_Filter;
import projects.trakzee.web.projectUtility.ProjectUtility;
import projects.trakzee.web.projectUtility.VisibleElement;
import projects.trakzee.web.projectUtility.XpathExtractor;

public class POM_SpeedVsDistance {
	WebDriver driver = TestBase.getWebDriver();;

	public POM_SpeedVsDistance(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriverWait wait = new WebDriverWait(driver, 5);

	private SoftAssert softAssert = new SoftAssert();
	private ProjectUtility pu = new ProjectUtility();

	public static void verifyTheSpeedRangeColumnAsPerSelectedInFilter(WebDriver driver, String speedRange) {
		String[] ranges = speedRange.split(",");
		for (String range : ranges) {
			if(range == null) {
				return;
			}
			String xpath = XpathExtractor.getXpathValueFromByDataType(PL_Filter.speedRangeColumnNameStringFormat);
			boolean isSpeedRangeColumnPresent = VisibleElement.isElementVisible(driver,
					String.format(xpath, range));
			TestBase.getSoftAssert().assertTrue(isSpeedRangeColumnPresent,
					"The selected speedrange column not present on the overview screen:" + range);
		}
	}

}
