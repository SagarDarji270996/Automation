package projects.trackzee.tests.common.header;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_Chart extends TestBase implements InitializePages {


	@BeforeClass
	private void navigateToTravelSummaryScreen() throws InterruptedException {
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen("Reports", "Activity", "Travel");
	}

	String pageTitle = "Chart";
	String headerValue = "Running V/S Stop";
	String yaxis = "Stop";
	String xaxis = "Idle";

	@Test
	public void testChartFunctionaity() throws InterruptedException, IOException {
		//TESTCASE_DONE: Test Charts functionality
		getChartPage().verifyTheChartsFunctionality(pageTitle, headerValue, yaxis, xaxis);
	}

}
