package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class CareersPage extends SuperPage {
    private static final Logger mylogger = LogManager.getLogger(LandingPage.class);

    public CareersPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

        this.driver=driver;
        mylogger.info("Careers Page Loaded");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Search jobs']")
    private WebElement search_jobs_lnk;

    @FindBy(how = How.CSS, using = ".search-box-input.ais-search-box--input")
    private WebElement search_box;

    @FindBy(how = How.ID, using = "search-box-button")
    private WebElement search_box_button;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,\"search-results\")]/div/div")
    private WebElement search_results;

    private void navigate_to_search_jobs() {
        elementHelpers.waitforelement_click(this.driver,search_jobs_lnk,"Search Jobs Link",mylogger);
    }

    private void enter_search_text(String arg0) {

        elementHelpers.waitforelement_click(this.driver,search_box,"Search Box",mylogger);
        elementHelpers.waitforelement_sendkeys(this.driver,search_box,"Search Box",arg0,mylogger);
        elementHelpers.waitforelement_click(this.driver,search_box_button,"Search Box Button",mylogger);
        javascriptHelpers.scrollToElement(this.driver,search_results);

    }


    public void search_for_job(String arg0) {
        navigate_to_search_jobs();
        enter_search_text(arg0);
    }

    public boolean verify_jobs(int arg0) {
        _waithelpers.waitforelement(this.driver,search_results);
        int search_records_count=this.driver.findElements(By.xpath("//div[contains(@id,\"search-results\")]/div/div")).size();
        if (search_records_count>=arg0) {
            mylogger.info("Search Records Count: " + search_records_count);
            return true;
        }
        else
            return false;
    }
}
