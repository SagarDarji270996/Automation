package projects.taskeye.web.pages;

import org.openqa.selenium.*;
import projects.commonMethods.HomePageCommonMethods;

public class HomePage {

    private final WebDriver driver;
    private final HomePageCommonMethods homePageCommonMethods;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.homePageCommonMethods = new HomePageCommonMethods(driver);
    }

    public HomePage selectProject(String projectName) {
        homePageCommonMethods.clickOnUserInfoButton();
        homePageCommonMethods.selectProject("TaskEye");
        return this;
    }

    public HomePage navigateToMenu(String mainModule, String subModule, String screen) throws InterruptedException {
        homePageCommonMethods.navigateToScreen(mainModule,subModule,screen);
        return this;
    }
}
