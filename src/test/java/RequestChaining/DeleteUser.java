package RequestChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	public void delete(ITestContext context) {
		int id=(int) context.getSuite().getAttribute("user_id");//this should come from create user.
		String bearerToken=(String) context.getSuite().getAttribute("bearerToken");//this should come from create user.
		given()
		 .headers("Authorization","Bearer "+bearerToken)
		 .pathParam("id", id)
		.when()
		  .delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
		  .statusCode(204)
		  .log().all();
	}
}
