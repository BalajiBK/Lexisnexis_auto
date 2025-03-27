package pageobjects;

import helpers.ElementHelpers;
import helpers.JavaScriptHelpers;
import helpers.WaitHelpers;
import org.openqa.selenium.WebDriver;

public class SuperPage {
    protected WebDriver driver;
    protected final WaitHelpers waitHelpers = new WaitHelpers();
    protected final ElementHelpers elementHelpers = new ElementHelpers();
    protected final JavaScriptHelpers javaScriptHelpers = new JavaScriptHelpers();

}
