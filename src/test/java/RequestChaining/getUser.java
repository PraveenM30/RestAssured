package RequestChaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class getUser {

	@Test
	public void Get(ITestContext context) {
		int id=(int) context.getSuite().getAttribute("user_id");//this should come from create user.
		String bearerToken=(String) context.getSuite().getAttribute("bearerToken");//this should come from create user.
		given()
		 .headers("Authorization","Bearer "+bearerToken)
		 .pathParam("id", id)
		.when()
		  .get("https://gorest.co.in/public/v2/users/{id}")
		.then()
		  .statusCode(200)
		  .log().body();
	}
}
