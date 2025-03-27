package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelpers {
    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

    public void waitForElement(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        wait.until(d -> element.isDisplayed());
    }
} 