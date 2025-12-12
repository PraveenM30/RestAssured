package restAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class TC003_PUT_request {
	public static HashMap map=new HashMap();
	@BeforeMethod
	public void postData() {
		map.put("name", "Dharani");
		map.put("Job", "Executive");
		//RestAssured.baseURI="https://reqres.in";
		//RestAssured.basePath="/api/users";
	}
	
	@Test
	public void UpdateUser() {
		
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users/2")
		   .header("x-api-key","YOUR_API_KEY")
		   .header("content-Type","application/json")
		   .body(map)
		.when()	
		   .put()
		.then()
		   .statusCode(200)
		   .body("name", equalTo("Dharani"))
		   .log().all()
		   .extract().response();
		
		String responseBody=response.asString();
		Assert.assertEquals(responseBody.contains("Executive"), true);
	}
}
