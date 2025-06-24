package projects.taskeye.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import projects.taskeye.mobile.locators.MobileLoginPageLocators;

public class MobileLoginPage {

    private final AppiumDriver<MobileElement> driver;
    private final String platform;

    public MobileLoginPage(AppiumDriver<MobileElement> driver, String platform) {
        this.driver = driver;
        this.platform = platform.toLowerCase();
    }

    public void clickOkButtonInBottomSheet() {
        MobileElement element = null;
        if (platform.equals("android")) {
            element = driver.findElement(MobileLoginPageLocators.ANDROID_LOCATION_DIALOG_OK_BUTTON);
            element.click();
        } else if (platform.equals("ios")) {
            element = driver.findElement(MobileLoginPageLocators.IOS_LOCATION_DIALOG_OK_BUTTON);
            element.click();
        }
    }
}
