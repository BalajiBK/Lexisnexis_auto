package LexisNexisAuto.definitions;

import PageObjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class Step_Definitions {

    public final static int TIMEOUT = 10;
    private static WebDriver driver;
    public LandingPage landingPage;
    public CareersPage careersPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @Given("User is on home page {string}")
    public void userIsOnHomePage(String my_url) {
        driver.get(my_url);
        landingPage=new LandingPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @When("User navigates to the Careers Page using the About Us link")
    public void userNavigatesToTheCareersPageUsingTheAboutUsLink() {
        careersPage=landingPage.navigate_to_about_careers();

    }

    @And("User searches for {string} in the search box using the search all jobs")
    public void userSearchesForInTheSearchBoxUsingTheSearchAllJobs(String arg0) {
        careersPage.search_for_job(arg0);
    }

    @Then("User should be able to see atleast {int} jobs")
    public void userShouldBeAbleToSeeAtleastJobs(int arg0) {
        Assert.assertTrue(careersPage.verify_jobs(arg0));
    }


}
