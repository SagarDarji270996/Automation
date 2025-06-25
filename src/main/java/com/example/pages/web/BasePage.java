package com.example.pages.web;

import com.example.config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigLoader configLoader;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait time
        PageFactory.initElements(driver, this);
        this.configLoader = new ConfigLoader();
    }

    // Overloaded constructor for initializing without an existing driver
    public BasePage() {
        this.configLoader = new ConfigLoader();
        // Driver initialization will be handled by a dedicated method or in subclasses
    }

    protected void initializeWebDriver(String browser) {
        boolean headless = Boolean.parseBoolean(configLoader.getProperty("headlessMode")); // Get from config

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver"); // Set driver path if not in PATH
                this.driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                // System.setProperty("webdriver.edge.driver", "path/to/msedgedriver"); // Set driver path if not in PATH
                this.driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox"); // Often required in CI environments
                chromeOptions.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
                chromeOptions.addArguments("--disable-features=VizDisplayCompositor"); // Helps in some CI/headless scenarios
                if (headless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu"); // Recommended for headless Chrome
                    chromeOptions.addArguments("--window-size=1920,1080"); // Optional
                }
                // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Set driver path if not in PATH
                this.driver = new ChromeDriver(chromeOptions);
                break;
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(configLoader.getProperty("defaultWaitTimeout"))));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configLoader.getProperty("implicitWaitTimeout"))));
        this.driver.manage().window().maximize();
    }

    // Call this method in your test setup
    public void setupDriver(String browser) {
        initializeWebDriver(browser);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(WebElement element) {
        waitForClickability(element).click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
