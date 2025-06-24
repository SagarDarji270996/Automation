package projects.trakzee.configurations.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import projects.trakzee.configurations.common.IframesOfApplication;
import projects.trakzee.web.pages.common.POM_Common;
import projects.trakzee.web.pages.common.footer.POM_Footer;
import projects.trakzee.web.pages.common.header.POM_Chart;
import projects.trakzee.web.pages.common.header.POM_Favorite;
import projects.trakzee.web.pages.common.header.POM_Filter;
import projects.trakzee.web.pages.common.header.POM_ScheduleReport;
import projects.trakzee.web.pages.common.header.POM_Setting;
import projects.trakzee.web.pages.download.POM_CloudDonwload;
import projects.trakzee.web.pages.loginAndHome.POM_HomePage;
import projects.trakzee.web.pages.loginAndHome.POM_LoginPage;
import projects.trakzee.web.pages.reports.activity.POM_Travel;
import projects.trakzee.web.pages.settings.general.POM_Admin;
import projects.trakzee.web.pages.settings.general.POM_Admin;
import projects.trakzee.web.pages.settings.master.POM_Geofence;
import projects.trakzee.web.projectUtility.CommonMethods;

public interface InitializePages {

	ThreadLocal<Map<Class<?>, Object>> pageObjects = ThreadLocal.withInitial(HashMap::new);

	@SuppressWarnings("unchecked")
	default <T> T getPage(Class<T> pageClass) {
		return (T) pageObjects.get().computeIfAbsent(pageClass, cls -> {
			try {
				return cls.getDeclaredConstructor(WebDriver.class).newInstance(TestBase.getWebDriver());
			} catch (Exception e) {
				throw new RuntimeException("Failed to initialize page: " + cls.getName(), e);
			}
		});
	}

	default POM_HomePage getHomePage() {
		return getPage(POM_HomePage.class);
	}

	default POM_Setting getSettingPage() {
		return getPage(POM_Setting.class);
	}

	default POM_Geofence getGeofencePage() {
		return getPage(POM_Geofence.class);
	}

	default POM_Common getCommonPage() {
		return getPage(POM_Common.class);
	}

	default POM_Filter getFilterPage() {
		return getPage(POM_Filter.class);
	}

	default POM_Travel getTravelPage() {
		return getPage(POM_Travel.class);
	}

	default POM_Footer getFooterPage() {
		return getPage(POM_Footer.class);
	}

	default POM_Chart getChartPage() {
		return getPage(POM_Chart.class);
	}

	default POM_ScheduleReport getSchedulePage() {
		return getPage(POM_ScheduleReport.class);
	}

	default CommonMethods getCommonMethodPage() {
		return getPage(CommonMethods.class);
	}

	default IframesOfApplication getIFramePage() {
		return getPage(IframesOfApplication.class);
	}

	default POM_LoginPage getLoginPage() {
		return getPage(POM_LoginPage.class);
	}

	default POM_Favorite getFavoritePage() {
		return getPage(POM_Favorite.class);
	}

	default POM_CloudDonwload getCloudDownloadPage() {
		return getPage(POM_CloudDonwload.class);
	}

	default POM_Admin getAdminPage() {
		return getPage(POM_Admin.class);
	}
}
