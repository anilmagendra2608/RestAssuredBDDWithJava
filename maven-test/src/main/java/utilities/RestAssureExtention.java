/**
 * This class contains all utility methods used in our tests
 * Another way to create built in library to do all operations related with  
 *
 */
package utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

/**
 * @author amagendra
 *
 */
public class RestAssureExtention {
	
	public static int status;
	public static RequestSpecification Request;
	public RestAssureExtention() 
	{
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri("http://api.zippopotam.us/");
		builder.setContentType(ContentType.JSON);
		RequestSpecification requestSpec = builder.build();	
		Request = RestAssured.given().spec(requestSpec);
	}	
	
	public static void GetOpsWithParam(String url, Map<String, String> pathParam) throws URISyntaxException {
		Request.pathParams(pathParam);
		try {
			Request.get(new URI(url));
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static ResponseOptions<Response> GetOps(String url) throws URISyntaxException {
		try {
			return Request.get(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
}			