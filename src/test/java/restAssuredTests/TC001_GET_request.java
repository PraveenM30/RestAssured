package restAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class TC001_GET_request {

	@Test
	public void getAllUsers() {
	given()
	//.baseUri("https://reqres.in/api/")
	.pathParam("MyPath", "users")
	.queryParam("page", "2")
	.when()
	   .get("https://reqres.in/api/{MyPath}") 
	.then()
	   .statusCode(200)
	   .statusLine("HTTP/1.1 200 OK")
	   .assertThat().body("page", equalTo(2))
	   .header("Content-Type", equalTo("application/json; charset=utf-8"))
	   .log().all();
	}
}
