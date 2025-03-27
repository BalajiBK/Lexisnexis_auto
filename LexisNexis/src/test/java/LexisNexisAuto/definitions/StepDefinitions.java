package LexisNexisAuto.definitions;

import pageobjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class StepDefinitions {
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int PAGE_LOAD_TIMEOUT_SECONDS = 20;
    
    private static WebDriver driver;
    private LandingPage landingPage;
    private CareersPage careersPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT_SECONDS));
    }

    @Given("User is on home page {string}")
    public void userIsOnHomePage(String url) {
        driver.get(url);
        landingPage = new LandingPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @When("User navigates to the Careers Page using the About Us link")
    public void userNavigatesToTheCareersPageUsingTheAboutUsLink() {
        careersPage = landingPage.navigateToAboutCareers();
    }

    @And("User searches for {string} in the search box using the search all jobs")
    public void userSearchesForInTheSearchBoxUsingTheSearchAllJobs(String searchText) {
        careersPage.searchForJob(searchText);
    }

    @Then("User should be able to see atleast {int} jobs")
    public void userShouldBeAbleToSeeAtLeastJobs(int minimumJobs) {
        Assert.assertTrue(careersPage.verifyJobs(minimumJobs));
    }
}
