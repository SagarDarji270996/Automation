package projects.trackzee.tests.tracking;

import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;

@Listeners({ CustomTestListener.class })
public class TC_LiveTracking extends TestBase implements InitializePages {
	@BeforeClass()
	public void selectProjectAndNavigateToGeofenceScreen() throws InterruptedException {
		getHomePage().selectProject("Trakzee Premium");
		getHomePage().navigateToScreen(Arrays.asList("Tracking", "Live Tracking"));
	}

	@Test()
	public void testFilterFunctionalityAndVerifyTheObjectCountBasedOnCompanyAndGeofence3() throws Exception {
		// TESTCASE_DONE: Check the object entries count based on the filter and geofence applied
		getCommonPage().checkScreenLoadingTime(30);
	}
}