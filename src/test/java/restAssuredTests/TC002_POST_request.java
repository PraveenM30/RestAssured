package restAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;


public class TC002_POST_request {

	public static HashMap map=new HashMap();
	@BeforeMethod
	public void postData() {
		map.put("name", "Praveen");
		map.put("Job", "Software Test Engineer");
		//RestAssured.baseURI="https://reqres.in";
		//RestAssured.basePath="/api/users";
	}
	
	@Test
	public void createUser() {
		
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users")
		   .header("content-Type","application/json")
		   .body(map)
		.when()	
		   .post()
		.then()
		   .statusCode(201)
		   .body("name", equalTo("Praveen"))
		   .log().all()
		   .extract().response();
		
		String responseBody=response.asString();
		Assert.assertEquals(responseBody.contains("Praveen"), true);
	}
}
