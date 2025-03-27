package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends SuperPage {
    private static final Logger logger = LogManager.getLogger(LandingPage.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final int RETRY_DELAY_MS = 1000;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        logger.info("Landing Page Loaded");
        this.acceptCookies();
    }

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][normalize-space()='About Us']")
    private WebElement aboutUsLink;

    @FindBy(how = How.XPATH, using = "//div[@class='score-style-box clearfix']//a[@class='score-button btn-clickable-area'][normalize-space()='Learn More']")
    private WebElement careersLink;

    public void acceptCookies() {
        elementHelpers.waitForElementClick(this.driver, acceptCookiesButton, "Accept Cookies Button", logger);
    }

    public CareersPage navigateToAboutCareers() {
        clickOnAboutUsLink();
        elementHelpers.waitForElementClick(this.driver, careersLink, "Careers Link", logger);

        // Store the current window handle (parent tab)
        String parentWindow = this.driver.getWindowHandle();

        // Wait for the new tab to open and switch to it
        for (String windowHandle : this.driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                this.driver.switchTo().window(windowHandle);
                logger.info("Switched to Careers Page");
                System.out.println(this.driver.getTitle());
                break;
            }
        }

        return new CareersPage(this.driver);
    }

    private void clickOnAboutUsLink() {
        for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                elementHelpers.waitForElementClick(driver, aboutUsLink, "About Us Link", logger);
                return;
            } catch (Exception e) {
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    throw new RuntimeException("Failed to click About Us link after " + MAX_RETRY_ATTEMPTS + " attempts", e);
                }
                logger.warn("Attempt " + attempt + " failed to click About Us link, retrying...");
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

