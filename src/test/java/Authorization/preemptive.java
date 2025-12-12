package Authorization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class preemptive {
	//combination of Basic and Digest
	@Test(priority = 1)
	public void preemptive() {
		given()
		  .auth().preemptive().basic("postman", "password")
		.when()
		  .get("https://postman-echo.com/digest-auth")
		.then()
		  .statusCode(200)
		  .body("authenticated", equalTo(true))
		  .log().all();
	}
}
