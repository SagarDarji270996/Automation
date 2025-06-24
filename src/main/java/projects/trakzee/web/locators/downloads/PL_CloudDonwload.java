package projects.trakzee.web.locators.downloads;

import org.openqa.selenium.By;

public class PL_CloudDonwload {

	public static final By btnApplyDonwloadReportFor = By.xpath("(//a[@id='filter_apply'])[1]");
	public static final By textMostRecentDonwloadedReportStringFormat = By
			.xpath("(//tr[@class='z-grid-odd']//td[@name='task_name'][text()='%s'])[1]");
	public static final String btnDownloadReportFromCould = "(//td[@name='task_name'][text()='%s']/following-sibling::td[@name='video_download'])[1]";
	public static final String textDownloadStatus = "(//td[@name='task_name'][text()='%s']/following-sibling::td[@name='status'])[1]";
}
