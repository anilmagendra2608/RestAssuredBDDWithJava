package utilities;
import com.fasterxml.jackson.core.JsonParser;

import io.restassured.http.Header;
/**
 * Wrapper Class to create methods to use in project
 */
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

/**
 * @author amagendra
 *
 */
/**
 * Wrapper Class created to maintain all operations related with Rest Assured library files
 *
 */
public class ResponseHolder {
	
	public static Response response;
	public static int responseCode;
	public static String responseBody;
	public static Headers responseHeaders;
	

	public static void setResponse(Response response) {
		ResponseHolder.response = response;
	}
	
	public static Response getResponse() {
		return response;
	}
	
	public static int getResponseCode() {
		responseCode = response.getStatusCode();
		return responseCode;
	}
	
	public static String getResponseBody() {
		responseBody = response.asString();
		return responseBody;
	}
	
	public static Headers getResponseHeaders() {
		responseHeaders = response.getHeaders();
		return responseHeaders;
	}	
	
}
