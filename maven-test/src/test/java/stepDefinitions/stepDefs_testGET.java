package stepDefinitions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utilities.ResponseHolder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.Assert;

/**
 * @author amagendra
 *
 */
/**
 * This Step Definition class is for GET operation (testGET.feature file) using Rest Assured Library methods
 *
 */
public class stepDefs_testGET {
	//private static ResponseOptions<Response> response;
	String url;
	Response response;
	Map<String, Object> responseMap;
	Map<String, String> query;
	ResponseHolder responseHolder;
	
	@Given("^The APIs are UP and Running \"([^\"]*)\"$")
	public void the_APIs_are_UP_and_Running(String url) throws Throwable {
		try 
		{
			this.url = url;
			response = given().when().get(url);
			Assert.assertEquals(200,(response.getStatusCode()));
			System.out.println("Status code retrieved from GET request to URL ="+response.getStatusCode());			
		}catch (Exception e)
		{
			e.printStackTrace();
		}	   
	}

	@When("^User performs a GET request to \"([^\"]*)\"$")
	public void user_performs_a_GET_request_to(String api_url) throws Throwable {
		try {
			this.url = this.url + api_url;
		    System.out.println("Complete URL with path= " + this.url);
		}catch (Exception e) {
			e.printStackTrace();
		}	    
	}

	@When("^perform the request$")
	public void perform_the_request() throws Throwable {
	    if(query == null)
	    {
	    	response = given().when().get(this.url);	    	
	    }
	    else 
	    {
	    	response = given().queryParameters(query).when().get(this.url);
	    }
	    // Set the response for respective URL passed as AUT from feature file
	    // e.g. here AUT for this project is http://api.zippopotam.us/
	    ResponseHolder.setResponse(response);
   }

	@Then("^The Response code should be (\\d+)$")
	public void the_Response_code_should_be(int responseCode) throws Throwable {
		try 
		{
			
		    Assert.assertEquals(responseCode, ResponseHolder.getResponseCode());
		}catch (Exception e) 
		{
			e.printStackTrace();
		}	    
	}

	@Then("^I should see json response with pairs on the filtered$")
	public void i_should_see_json_response_with_pairs_on_the_filtered(DataTable dataTable) throws Throwable {
	   	  Map<String, String> query = new LinkedHashMap<String, String>();
		  for(DataTableRow row : dataTable.getGherkinRows()) {
		  query.put(row.getCells().get(0), row.getCells().get(1)); }			  		  
		
		  System.out.println("DATA FETCHED FROM DATATABLE in FEATURE FILE==");	
		  for ( DataTableRow row : dataTable.getGherkinRows()) {
		  System.out.println(row.getCells().get(0) + "|" + row.getCells().get(1)); 
		  }
		  // Reading the Response
		  ObjectReader reader = new ObjectMapper().reader(Map.class);		  
		  responseMap = reader.readValue(ResponseHolder.getResponseBody());
		  System.out.println("DATA FETCHED FROM GET RESPONSE== " +responseMap);
		  
		  // Compare the data fetched from DataTable to the GET Response   		  		  
		  for( String key: query.keySet() ) {
			  System.out.println("KEY from feature file = "+key);
			  Assert.assertTrue(responseMap.containsKey(key)); 
			  Assert.assertEquals(query.get(key), responseMap.get(key).toString());
			  System.out.println("VALUE obtained from GET RESPONSE= " +responseMap.get(key).toString());
		  }		  
	}
}
