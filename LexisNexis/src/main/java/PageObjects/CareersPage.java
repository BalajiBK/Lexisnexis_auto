package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class CareersPage extends SuperPage {
    private static final Logger logger = LogManager.getLogger(CareersPage.class);
    private static final int PAGE_LOAD_DELAY_MS = 1000;

    public CareersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        logger.info("Careers Page Loaded");
        try {
            Thread.sleep(PAGE_LOAD_DELAY_MS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Search jobs']")
    private WebElement searchJobsLink;

    @FindBy(how = How.CSS, using = ".search-box-input.ais-search-box--input")
    private WebElement searchBox;

    @FindBy(how = How.ID, using = "search-box-button")
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,\"search-results\")]/div/div")
    private WebElement searchResults;

    public void searchForJob(String searchText) {
        // Navigate to search jobs
        elementHelpers.waitForElementClick(this.driver, searchJobsLink, "Search Jobs Link", logger);
        
        // Enter search text and submit
        elementHelpers.waitForElementClick(this.driver, searchBox, "Search Box", logger);
        elementHelpers.waitForElementSendKeys(this.driver, searchBox, "Search Box", searchText, logger);
        elementHelpers.waitForElementClick(this.driver, searchButton, "Search Button", logger);
        
        // Scroll to results
        javaScriptHelpers.scrollToElement(this.driver, searchResults);
    }

    public boolean verifyJobs(int minimumJobs) {
        waitHelpers.waitForElement(this.driver, searchResults);
        int searchResultsCount = this.driver.findElements(By.xpath("//div[contains(@id,\"search-results\")]/div/div")).size();
        if (searchResultsCount >= minimumJobs) {
            logger.info("Search Results Count: " + searchResultsCount);
            return true;
        }
        return false;
    }
}
