package stepDefinitions;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.RestAssureExtention;

/**
 * @author amagendra
 *
 */
/**
 * This is Step Definition class to understand the core concept of BDD 
 *
 */
public class StepDefs_testAPI {
	RestAssureExtention request;	
	@Given("^I launch Chrome browser$")
	public void i_launch_Chrome_browser() throws Exception {	
		System.out.println("Launch Chrome");
	}

	@When("^I open Google Homepage$")
	public void i_open_Google_Homepage() throws Exception {
		//request.GetStatusCode();
		System.out.println("Open Google home page");
	}

	@Then("^I verify that the page displays search text box$")
	public void i_verify_that_the_page_displays_search_text_box() throws Exception {
		System.out.println("Verify page displays");
	}

	@Then("^the page displays Google Search button$")
	public void the_page_displays_Google_Search_button() throws Exception {	
		System.out.println("Verify page displays button");
	}

	@Then("^the page displays Im Feeling Lucky button$")
	public void the_page_displays_Im_Feeling_Lucky_button() throws Exception {	 
		System.out.println("Verify page displays lucky button");
	}
}
