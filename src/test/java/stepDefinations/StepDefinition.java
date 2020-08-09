package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.LoacationMap;
import resourses.APIresources;
import resourses.TestBuild;
import resourses.Utils;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestBuild data = new TestBuild();
	
	JsonPath js;
	static String place_id;

	@Given("Add place payload  {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address ));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIresources resoursesAPI = APIresources.valueOf(resource);
		System.out.println(resoursesAPI.getResources());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resoursesAPI.getResources()).then().spec(resspec).extract().response();
		else if(method.equalsIgnoreCase("GET")) 
			response = res.when().post(resoursesAPI.getResources()).then().spec(resspec).extract().response();
	}

	@Then("The API call success code with status code {int}")
	public void the_api_call_success_code_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	@Then("Verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	     //prepare request spec 
	
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,  "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	
		
	}
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
	 res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	
}
