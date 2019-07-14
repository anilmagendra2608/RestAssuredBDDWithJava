package stepDefinitions;
import cucumber.api.java.Before;
import utilities.ResponseHolder;
import utilities.RestAssureExtention;

/**
 * @author amagendra
 *
 */
/**
 * This class is Hooks for Cucumber Tests to initialize the utility wrapper
 */
public class TestInitialize {

	@Before
	public void TestSetup() {
		RestAssureExtention restAssuredExtention = new RestAssureExtention();
		ResponseHolder responseHolder = new ResponseHolder();
	}
}
