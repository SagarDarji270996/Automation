package listeners;

import projects.taskeye.configurations.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomTestListener implements ITestListener {

    // Extent Report Objects
    private static final ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    // Web and Mobile Drivers
    private WebDriver driver;
    private AppiumDriver<MobileElement> mobileDriver;

    // Unified Report & Screenshot directory
    private static final String reportBaseDirectory = System.getProperty("user.dir") + "/extentReports";
    private static final String screenshotDirectory = reportBaseDirectory + "/screenshots";

    // Static block to initialize Extent Reports
    static {
        ensureDirectoryExists(reportBaseDirectory);
        ensureDirectoryExists(screenshotDirectory);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportBaseDirectory + "/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
        deleteOldScreenshots();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        extent.flush();  // Ensure all reports are written
    }

    @Override
    public void onTestStart(ITestResult result) {
        driver = getDriverFromTestBase(result);
        mobileDriver = getMobileDriverFromTestBase(result);

        // Create test entry in Extent Report
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
        test.info("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.pass("Test passed successfully");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.fail("Test failed: " + result.getThrowable().getMessage());

            String screenshotPath = captureScreenshot(result);
            if (screenshotPath != null) {
                test.fail("Screenshot on failure:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.skip("Test skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented, but can be used if needed
    }

    /**
     * Capture a screenshot for failed tests.
     * @param result Test result object
     * @return Path to the captured screenshot
     */
    private String captureScreenshot(ITestResult result) {
        ensureDirectoryExists(screenshotDirectory);
        String screenshotPath = screenshotDirectory + "/" + result.getMethod().getMethodName() + ".png";

        try {
            File screenshot = null;
            if (driver != null) {
                screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            } else if (mobileDriver != null) {
                screenshot = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.FILE);
            }

            if (screenshot != null) {
                Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
                System.out.println("Screenshot captured: " + screenshotPath);
                return screenshotPath;
            } else {
                System.err.println("No valid driver found for screenshot.");
            }
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
        return null;
    }

    /**
     * Ensure the target directory exists, otherwise create it.
     * @param directoryPath Directory path
     */
    private static void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdirs()) {
            System.err.println("Failed to create directory: " + directoryPath);
        }
    }

    /**
     * Delete old screenshots before starting a new test run.
     */
    private void deleteOldScreenshots() {
        ensureDirectoryExists(screenshotDirectory);
        File[] oldScreenshots = new File(screenshotDirectory).listFiles();
        if (oldScreenshots != null) {
            for (File file : oldScreenshots) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    if (!file.delete()) {
                        System.err.println("Failed to delete: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private WebDriver getDriverFromTestBase(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof TestBase) {
            return ((TestBase) testClass).getWebDriver();
        }
        return null;
    }

    private AppiumDriver<MobileElement> getMobileDriverFromTestBase(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof TestBase) {
            return ((TestBase) testClass).getMobileDriver();
        }
        return null;
    }
}
