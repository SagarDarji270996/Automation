package projects.trakzee.web.pages.settings.master;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.projectUtility.DataProviders;
import projects.trakzee.web.projectUtility.ReadDataFromExcelFile;

public class POM_Geofence implements InitializePages {
	private WebDriver driver = TestBase.getWebDriver();

	public POM_Geofence(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth() throws InterruptedException, IOException {
		// SCENARIOS_DONE: verify that user able to select the filter data from the LHS and RHS both

		String[][] rawData = DataProviders.dataProviderGetDataFromExcelFile("CheckNavigationList", "Geofence");
		for (int rowCount = 0; rowCount < rawData.length; rowCount++) { // Start at 0
			String company = rawData[rowCount][0];
			String geofenceType = rawData[rowCount][1];
			int totalColumnIndex = 2;
			getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
			getFilterPage().selectCompanyRHS(company);
			//getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			String totalRecords = getFooterPage().getTotalRecordsPresent();
			String excelFileName = "CheckNavigationList";
			String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
					+ File.separator + excelFileName.trim() + ".xlsx";
			ReadDataFromExcelFile rdfef = new ReadDataFromExcelFile(filePath);
			rdfef.setCellData("Geofence", rowCount + 1, totalColumnIndex, totalRecords);
			Thread.sleep(2000);
		}

	}

	public String verifyThatUserAbleToSelectTheFilterDataFromTheLhsAndRhsBoth(String company, String geofenceType)
			throws InterruptedException, IOException {
		// SCENARIOS_DONE: verify that user able to select the filter data from the LHS and RHS both
		getCommonPage().clickOnFilterIconButtonAndVerifyTooltipWhenApplyButtonAtIndexOne();
		boolean isCompanyPresent = getFilterPage().selectCompanyRHS(company);
		if (isCompanyPresent) {
			getFilterPage().selectGeofenceTypeRHS(geofenceType);
			getFilterPage().clickOnAppyFilterButtonRHS();
			return getFooterPage().getTotalRecordsPresent();
		} else {
			return "Company not found";
		}

	}
}
