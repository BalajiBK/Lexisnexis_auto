package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends SuperPage{

    private static final Logger mylogger = LogManager.getLogger(LandingPage.class);

    public LandingPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver=driver;
        mylogger.info("Landing Page Loaded");
        this.accept_cookies();
    }

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    private WebElement accept_cookies;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][normalize-space()='About Us']")
    private WebElement about_us_lnk;

    @FindBy(how = How.XPATH, using = "//div[@class='score-style-box clearfix']//a[@class='score-button btn-clickable-area'][normalize-space()='Learn More']")
    private WebElement careers_lnk;


    public void accept_cookies() {
        elementHelpers.waitforelement_click(this.driver,accept_cookies,"Accept Cookies",mylogger);
    }

    public CareersPage navigate_to_about_careers(){

        Click_OnAboutUsLink();
        elementHelpers.waitforelement_click(this.driver,careers_lnk,"Careers Link",mylogger);

        // Store the current window handle (parent tab)
        String parentWindow = this.driver.getWindowHandle();

        // Wait for the new tab to open and switch to it
        for (String windowHandle : this.driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                this.driver.switchTo().window(windowHandle);
                mylogger.info("Switched to Careers Page");
                System.out.println(this.driver.getTitle());// Switch to the new tab
                break; // Exit the loop after switching
            }
        }

        return new CareersPage(this.driver);
    }

    private void Click_OnAboutUsLink() {
        boolean clicked = false;
        int maxAttempts = 5;
        long maxTimeMillis = 20000;
        long startTime = System.currentTimeMillis();
        int attempts = 0;
        while (!clicked && attempts < maxAttempts) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > maxTimeMillis) {
                System.out.println("Exceeded maximum time of " + (maxTimeMillis / 1000.0) + " seconds.");
                break;
            }

            try {
                elementHelpers.waitforelement_click(driver, about_us_lnk, "About Us Link", mylogger);
                clicked = true;
            } catch (Exception e) {
                attempts++;
                System.out.println("Attempt " + attempts + " failed: Element not clickable yet, retrying...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    }

