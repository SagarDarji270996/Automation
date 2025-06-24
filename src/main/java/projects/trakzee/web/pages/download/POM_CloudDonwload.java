package projects.trakzee.web.pages.download;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import projects.trakzee.configurations.base.InitializePages;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.web.locators.downloads.PL_CloudDonwload;
import projects.trakzee.web.projectUtility.ProjectUtility;

public class POM_CloudDonwload implements InitializePages {

	private WebDriver driver = TestBase.getWebDriver();
	WebDriverWait wait;
	ProjectUtility pu = new ProjectUtility();
	//POM_HomePage pom_homePage;

	public POM_CloudDonwload(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}

	public void verifyThatUserAbleToDownloadTheReportFromCloudDownload(String mostRecentDownloadReportName)
			throws InterruptedException {
		//SCENARIOS TODO: verifyThatUserAbleToDownloadTheReportFromCloudDownload
		waitAndDownloadCloudReportTillNotCompleted();
		checkTheStatusOfTheReportAddedToTheCloudDownload(mostRecentDownloadReportName);
		clickOnDownloadIconButtonPresentAtCloudDonwloadList(mostRecentDownloadReportName);

	}

	public void waitAndDownloadCloudReportTillNotCompleted() throws InterruptedException {
		//SCENARIOS_DONE: Wait and download report till completed
		getHomePage().clickOnCloudDownloadIcon();
		getFilterPage().selectTheDownlaodRequestFor("Report");
		getFilterPage().clickOnAppyFilterPrsentAtInsideCloudDonwloadFilerButton();
	}

	public void checkTheStatusOfTheReportAddedToTheCloudDownload(String reportName) throws InterruptedException {
		// STEP_DONE: check the status of the report added to the cloud download
		getIFramePage().switchToContentFrame();
		String status = null;

		status = pu.getElementText(driver, String.format(PL_CloudDonwload.textDownloadStatus, reportName));

		if (status.equalsIgnoreCase("Completed")) {
			return;
		}

		if (status.equalsIgnoreCase("In-Progress")) {
			for (int count = 1; count <= 10; count++) {
				System.out.println("Waiting for next 10 seconds");
				Thread.sleep(10000);
				waitAndDownloadCloudReportTillNotCompleted();
				status = pu.getElementText(driver,
						String.format(PL_CloudDonwload.textDownloadStatus, reportName));
				if (status.equalsIgnoreCase("completed")) {
					break;
				}
			}
		} 

	}

	public void clickOnDownloadIconButtonPresentAtCloudDonwloadList(String reportName) {
		// STEP_DONE: click on download icon button present at cloud donwload list
		getIFramePage().switchToContentFrame();
		pu.clickOnButton(driver, String.format(PL_CloudDonwload.btnDownloadReportFromCould, reportName));
	}
}
