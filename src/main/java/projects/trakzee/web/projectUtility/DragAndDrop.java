package projects.trakzee.web.projectUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {
	public static void dragAndDropInSamePlane(WebDriver driver, By xpath_ELEMENT, int xOffset, int yOffset) {
		Actions actions = new Actions(driver);
		try {
			WebElement textElement = driver.findElement(xpath_ELEMENT);

			// Move to element before dragging
			actions.moveToElement(textElement).perform();

			actions.clickAndHold(textElement).moveByOffset(xOffset, yOffset).release().perform();

			// Perform drag and drop
			//actions.dragAndDropBy(textElement, xOffset, yOffset).perform();

			System.out.println("Element dragged and dropped successfully.");
		} catch (Exception e) {
			System.err.println("Exception in dragAndDropInSamePlane: " + e.getMessage());
		}
	}
}