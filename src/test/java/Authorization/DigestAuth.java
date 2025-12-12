package Authorization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class DigestAuth {
	@Test(priority = 1)
	public void Digest() {
		given()
		  .auth().basic("postman", "password")
		.when()
		  .get("https://postman-echo.com/digest-auth")
		.then()
		  .statusCode(200)
		  .body("authenticated", equalTo(true))
		  .log().all();
	}
}
