package projects.trakzee.web.projectUtility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotHandler {

	private static SoftAssert softAssert = TestBase.getSoftAssert();

	public void takeScreenshot(WebDriver driver, By elementLocator) throws IOException {
		WebElement divElement = driver.findElement(elementLocator); // Replace with the correct locator
		File screenshot = divElement.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("path/to/save/div_screenshot.png");
		FileHandler.copy(screenshot, destinationFile);
	}

	public static boolean compareElementScreenshot(WebDriver driver, By elementLocator, String baseImageAddress)
			throws IOException {
		WebElement element = driver.findElement(elementLocator); // Replace with the correct locator

		// Capture screenshot of the WebElement using AShot
		Screenshot screenshot = new AShot().takeScreenshot(driver, element);
		BufferedImage actualImage = screenshot.getImage();

		// Load the baseline image from the provided path
		BufferedImage expectedImage = ImageIO.read(new File(baseImageAddress));

		// Compare the images using ImageDiffer
		ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualImage);

		// Return true if images are identical, false otherwise
		softAssert.assertTrue(!diff.hasDiff(), "Both screenshot data not machted");

		return !diff.hasDiff();
	}

	public static boolean compareElementScreenshot(WebDriver driver, By elementLocator,
			String baseImageAddressWithFileNameAndExtention, String baseImageName, String outputFolder)
			throws IOException, InterruptedException {
		Thread.sleep(3000);
		// Find the web element
		WebElement element = driver.findElement(elementLocator);

		// Capture screenshot of the WebElement using AShot
		Screenshot screenshot = new AShot().takeScreenshot(driver, element);
		BufferedImage actualImage = screenshot.getImage();

		// Ensure the output folder exists
		File folder = new File(outputFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		// Save the captured screenshot in the specified output folder
		String outputImagePath = outputFolder + File.separator + "captured_screenshot_"
				+ baseImageName.replaceAll(" ", "_");
		outputImagePath = outputImagePath + ".png";
		File outputImageFile = new File(outputImagePath);

		try {
			// Ensure compatibility of the image format
			BufferedImage compatibleImage = new BufferedImage(actualImage.getWidth(), actualImage.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = compatibleImage.createGraphics();
			g.drawImage(actualImage, 0, 0, null);
			g.dispose();

			// Save the image
			ImageIO.write(compatibleImage, "png", outputImageFile);
			System.out.println("Screenshot saved at: " + outputImagePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error saving the image: " + e.getMessage());
			return false;
		}

		// Load the baseline image
		BufferedImage expectedImage = ImageIO.read(new File(baseImageAddressWithFileNameAndExtention));

		// Compare the images using ImageDiffer
		ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualImage);

		// Return true if images are identical, false otherwise
		softAssert.assertTrue(!diff.hasDiff(), "Both screenshot data not machted");

		return !diff.hasDiff();
	}

}
