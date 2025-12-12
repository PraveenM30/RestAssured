package RequestChaining;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	public void Update(ITestContext context) {
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearerToken=(String) context.getSuite().getAttribute("bearerToken");//this should come from create user.//this should come from create user.
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Female");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "active");
		
		
		given()
		  .headers("Authorization","Bearer "+bearerToken)
		  .contentType("application/json")
		  .pathParam("id", id)
		  .body(data.toString())
		  
		.when()
		  .patch("https://gorest.co.in/public/v2/users/{id}")
		.then()
		  .statusCode(200)
		  .log().body();
	}
}
