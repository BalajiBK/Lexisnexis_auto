package helpers;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementHelpers {
    private final WaitHelpers wait;

    public ElementHelpers() {
        this.wait = new WaitHelpers();
    }

    public void waitForElementClick(WebDriver driver, WebElement element, String elementName, Logger logger) {
        this.wait.waitForElement(driver, element);
        element.click();
        logger.info("Clicked on " + elementName);
    }

    public void waitForElementSendKeys(WebDriver driver, WebElement element, String elementName, String value, Logger logger) {
        this.wait.waitForElement(driver, element);
        element.sendKeys(value);
        logger.info("Entered value " + value + " in field " + elementName);
    }
}
