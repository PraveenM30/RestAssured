package Reqres_BDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import com.google.gson.JsonObject;

import io.restassured.response.Response;
import junit.framework.Assert;

public class D_GET_request {

	//@Test(priority = 1)
	public void ValidateData_1() {
	given()
	//.baseUri("https://reqres.in/api/")
	.pathParam("MyPath", "users")
	.queryParam("page", "2")
	.when()
	   .get("https://reqres.in/api/{MyPath}") 
	.then()
	   .statusCode(200)
	   .assertThat().body("data[5].first_name", equalTo("Rachel"))
	   .log().body();
	}
	
	@Test(priority = 2)
	public void ValidateData_2() {
	Response res=given()
	//.baseUri("https://reqres.in/api/")
	.pathParam("Mypath", "users")
	.queryParam("page", "2")
	.when()
	   .get("https://reqres.in/api/{Mypath}");
	
	/* ------------------1st Way------------------*/
	
	Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
	String firstName=res.jsonPath().get("data[5].first_name").toString();
	Assert.assertEquals(firstName, "Rachel");
	
	/* ------------------2nd Way------------------*/
	
	  // Parse the response body into a JsonObject
    JsonObject jo = JsonParser.parseString(res.getBody().asString()).getAsJsonObject();

    // Get the JsonArray of 'data' from the JsonObject
    JsonArray dataArray = jo.getAsJsonArray("data");
    String allFirstNames = "";

    // Iterate over the dataArray and extract each first_name
    for (int i = 0; i < dataArray.size(); i++) {
        JsonObject user = dataArray.get(i).getAsJsonObject(); // Get each element and cast to JsonObject
        String firstNamee = user.get("first_name").getAsString(); // Extract "first_name"
        allFirstNames =allFirstNames + firstNamee + " "; // Append to a string (or use another structure to store)
    }
    // Optionally print out all first names or use it in an assertion
    System.out.println("All First Names: " + allFirstNames);
    
    // Example assertion if you want to validate the first name of the first user
    Assert.assertTrue(allFirstNames.contains("Rachel"));  // Just as an example, adjust based on needs
}
	}
