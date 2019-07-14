package com.apitest;
import org.testng.annotations.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * @author amagendra
 *
 */
/**
 * Non BDD Style of writing Rest Assured Tests using its library
 *
 */
public class RestAssureAppTest {

	@DataProvider(name = "MockData")
	public static Object[][] zipCodesAndPlaces(){
		return new Object[][] {
			{"us", "90210", "Beverly Hills"},
			{"us", "12345", "Schenectady"},
			{"ca", "B2R", "Waverley"}
		};
	}
	
	// Checking Status Code 200 for URL
	@Test
	public void getStatusCodeTest() {
		try {
			given().
			when().
			get("http://api.zippopotam.us/IN/110001").
			then().
			assertThat().statusCode(200);
			System.out.println("Status code is 200");
		}
		catch(Exception e) 
		{	
			System.out.println("Error with Status code");
		}
	}

	// Checking the response content Type ContentType.JSON or application/JSON
	@Test
	public void getResponseContentType() {			
		given().
		when().
		get("http://api.zippopotam.us/IN/110001").
		then().
		assertThat().contentType(ContentType.JSON);	
	}	
	
	@Test
	// LOGGING request and response details as body using RestAssure functions
	public void logRequestnResponseDetails() {
		given().log().all().
		when().get("http://zippopotam.us/IN/110001").
		then().
		log().body();
	}
	
	@Test
	// Checking the Response Body
	public void checkResponseBody() {
		
		  given().when().get("http://zippopotam.us/IN/110001").then().assertThat().
		  body("places[0].'place name'", equalTo("Janpath"));
		  
		  given().when().get("http://zippopotam.us/IN/110001").then().assertThat().
		  body("places[0].'place name'", not(hasItem("xxx")) );
		  given().when().get("http://zippopotam.us/IN/110001").then().assertThat().
		  body("places[1].'place name'", equalTo("Rail Bhawan"));		 
				
	}
		
	// Test to check the temparature of the city
	@Test
	public void getTemparatureTest() {
		/*
		 * given(). when(). get("http://restapi.demoqa.com/utilities/weather/city/Goa").
		 * then(). assertThat(). body("Temperature", equalTo("27.762 Degree celsius"));
		 */
	}
	
	@Test
	public void test_ResponseHeaderData_ShouldBeCorrect() {
	        
	    given().contentType(ContentType.JSON).
	    when().
	    	        get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("4551"));
	}		
}