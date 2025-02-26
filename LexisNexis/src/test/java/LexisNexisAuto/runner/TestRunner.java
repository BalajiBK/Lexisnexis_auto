
package LexisNexisAuto.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features/LexisNexisCareers.feature"}, glue = {"LexisNexisAuto.definitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends AbstractTestNGCucumberTests {

}