package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ResponseHolder;

/**
 * @author amagendra
 *
 */
/**
 * This Step Definition class is for POST operation (testPOST.feature file) using Rest Assured Library methods
 *
 */
public class stepDefs_testPOST {
	String url;
	Response response;
	Map<String, Object> responseMap;
	Map<String, String> query;
	Map<String, String> body;
	ResponseHolder responseHolder;	
	
	@Given("^The APIs is Running \"([^\"]*)\"$")
	public void the_APIs_is_Running(String url) throws Throwable {
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
	
	@When("^User performs a POST request to \"([^\"]*)\"  with below details$")
	public void user_performs_a_POST_request_to_with_below_details(String url, DataTable dataTable) throws Throwable {
	    this.url = this.url + url;
	    
	    this.body = new LinkedHashMap<String, String>();
	    for (DataTableRow row: dataTable.getGherkinRows()) {
	    	this.body.put(row.getCells().get(0), row.getCells().get(1));
	    }
	}

	@And("^perform the POST request$")
	public void perform_the_POST_request() throws Throwable {
	   response = given().contentType(ContentType.JSON).body(this.body).when().post(this.url);
	   ResponseHolder.setResponse(response);
	}
	
	@Then("^I should see json response with pairs on the filtered \"([^\"]*)\" filter$")
	public void i_should_see_json_response_with_pairs_on_the_filtered_filter(String filter, DataTable dataTable) throws Throwable {
		  Map<String, String> query = new LinkedHashMap<String, String>();
		  for(DataTableRow row : dataTable.getGherkinRows()) {
		  query.put(row.getCells().get(0), row.getCells().get(1)); }
		  
		  System.out.println("DATA FETCHED FROM DATATABLE in POST FEATURE FILE==");	
		  for ( DataTableRow row : dataTable.getGherkinRows()) {
		  System.out.println(row.getCells().get(0) + "|" + row.getCells().get(1)); 
		  }
		  
		  // Reading the Response
		  ObjectReader reader = new ObjectMapper().reader(Map.class);		  
		  responseMap = reader.readValue(ResponseHolder.getResponseBody());
		  System.out.println("DATA FETCHED FROM GET RESPONSE== " +responseMap);
		  responseMap = (Map<String, Object>) responseMap.get(filter);
		  
		  for(String key: query.keySet()) {
			  Assert.assertTrue(responseMap.containsKey(key));
			  Assert.assertEquals(query.get(key), responseMap.get(key).toString());
		  }
	}
}
