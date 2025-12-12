package restAssuredTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class TC004_DELETE_request {
	
	@Test
	public void DeleteUser() {
		
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users/2")
		.when()	
		   .delete()
		.then()
		   .statusCode(204)
		   .log().all();
	}
}