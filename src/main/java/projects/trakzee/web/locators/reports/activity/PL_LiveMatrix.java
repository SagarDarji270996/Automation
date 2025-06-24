package projects.trakzee.web.locators.reports.activity;

import org.openqa.selenium.By;

public class PL_LiveMatrix {
	public static final By listMatrixOnLiveMatrixPage = By
			.xpath("//div[@id='matrix_details']//div[starts-with(@class,'matrix_title')]//span[1]");
}