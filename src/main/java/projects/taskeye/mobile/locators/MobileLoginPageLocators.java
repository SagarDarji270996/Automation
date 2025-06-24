package projects.taskeye.mobile.locators;

import org.openqa.selenium.By;

public class MobileLoginPageLocators {
    //ANDROID LOCATORS

    public static final By ANDROID_SPLASH_SCREEN = By.id("com.uffizio.taskeye:id/action_bar_root");
    public static final By ANDROID_LOCATION_DIALOG = By.id("com.uffizio.taskeye:id/lay_dialog");
    public static final By ANDROID_LOCATION_DIALOG_OK_BUTTON = By.xpath("//android.widget.Button[@resource-id='com.uffizio.taskeye:id/btnOk']");
    public static final By ANDROID_QUICK_START_OF_SKIP_BUTTON = By.id("com.uffizio.taskeye:id/tv_skip");
    public static final By ANDROID_DEVICE_LOCATION_SCREEN_OF_NO_THANKS_BUTTON = By.id("com.uffizio.taskeye:id/btnNoThanks");
    public static final By ANDROID_BATTERY_OPTIMIZATION_SCREEN_OF_NO_THANKS_BUTTON = By.id("com.uffizio.taskeye:id/btnNoThanks");

    //IOS LOCATORS
    public static final By IOS_SPLASH_SCREEN = By.xpath("//XCUIElementTypeOther[@name='splash_screen']");
    public static final By IOS_LOCATION_DIALOG = By.id("com.uffizio.taskeye:id/lay_dialog");
    public static final By IOS_LOCATION_DIALOG_OK_BUTTON = By.id("com.uffizio.taskeye:id/btnOk");
    public static final By IOS_QUICK_START_OF_SKIP_BUTTON = By.id("com.uffizio.taskeye:id/tv_skip");
    public static final By IOS_DEVICE_LOCATION_SCREEN_OF_NO_THANKS_BUTTON = By.id("com.uffizio.taskeye:id/btnNoThanks");
    public static final By IOS_BATTERY_OPTIMIZATION_SCREEN_OF_NO_THANKS_BUTTON = By.id("com.uffizio.taskeye:id/btnNoThanks");


}
