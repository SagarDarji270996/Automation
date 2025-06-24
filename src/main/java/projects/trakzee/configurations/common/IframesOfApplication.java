package projects.trakzee.configurations.common;

import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IframesOfApplication {
    private static final Logger logger = LogManager.getLogger(IframesOfApplication.class);
    private final WebDriver driver;

    public IframesOfApplication(WebDriver driver) {
        this.driver = driver;
    }

    private void switchToFrame(String... frameNames) {
        try {
            driver.switchTo().defaultContent();
            for (String frame : frameNames) {
                driver.switchTo().frame(frame);
            }
            logger.info("Switched to iframe: {}", (Object[]) frameNames);
        } catch (NoSuchFrameException e) {
            logger.error("No such frame found: {}", frameNames, e);
        } catch (Exception e) {
            logger.error("Error switching to iframe: {}", frameNames, e);
        }
    }

    public void switchToDivFrame() {
        try {
            driver.switchTo().defaultContent();
            driver.switchTo().frame("divframe");
        } catch (NoSuchFrameException e) {
            logger.error("No such frame found: popup_dialog.", e);
        } catch (Exception e) {
            logger.error("Error switching to popup_dialog iframe.", e);
        }
    }

    public void switchToWindowFrame() {
        switchToFrame("divframe", "windowframe");
    }

    public void switchToBottomFrame() {
        switchToFrame("divframe", "windowframe", "bottomframe");
    }

    public void switchToContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe");
    }

    public void switchToTitleFrame() {
        switchToFrame("divframe", "windowframe", "titleframe");
    }

    public void switchToScheduleScreen() {
        switchToFrame("divframe", "windowframe", "contentframe", "iframe_schedule");
    }

    public void switchToSettingPopup() {
        try {
            driver.switchTo().defaultContent();
            driver.switchTo().frame("divframe");
            WebElement frame = driver.findElement(By.xpath("//iframe[@src='TrakzeeAdavncedSort.jsp']"));
            driver.switchTo().frame(frame);
            logger.info("Switched to iframe: advanced setting popup.");
        } catch (NoSuchFrameException e) {
            logger.error("No such frame found: advanced setting popup.", e);
        } catch (Exception e) {
            logger.error("Error switching to advanced setting popup.", e);
        }
    }

    public void switchToCompanyRuleContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "company_rule_iframe");
    }

    public void switchToRuleContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "rule_iframe");
    }

    public void switchToAdminRuleFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "admin_rule_iframe");
    }

    public void switchToSubAdminUserFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "sub_account");
    }

    public void switchToTemplateFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "template_iframe");
    }

    public void switchToPaymentGatewayFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "payment_gateway");
    }

    public void switchToRenameLabelFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "rename_label_iframe");
    }

    public void switchToSocialMediaApiFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "social_media_api");
    }

    public void switchToCompanyMapContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "map_iframe");
    }

    public void switchToEmployeeLeaveFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "leave_iframe");
    }

    public void switchToBranchContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "branch_iframe");
    }

    public void switchToDepartmentContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "department_iframe");
    }

    public void switchTaskCategoryContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "task_category_iframe");
    }

    public void switchExpenseCategoryContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "expense_category_iframe");
    }

    public void switchShiftContentFrame() {
        switchToFrame("divframe", "windowframe", "contentframe", "shift_iframe");
    }

    public void switchToPopUpDialogFrame() {
        try {
            driver.switchTo().defaultContent();
            driver.switchTo().frame("divframe");
            driver.switchTo().frame("windowframe");
            driver.switchTo().frame("contentframe");
            driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='popup_dialog']//iframe")));
            logger.info("Switched to iframe: popup_dialog.");
        } catch (NoSuchFrameException e) {
            logger.error("No such frame found: popup_dialog.", e);
        } catch (Exception e) {
            logger.error("Error switching to popup_dialog iframe.", e);
        }
    }
}
