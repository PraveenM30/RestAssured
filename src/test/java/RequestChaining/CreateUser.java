package RequestChaining;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	public void Create(ITestContext context) {
		Faker faker=new Faker();
		String bearerToken="2b35da28422656a3133b153abb1a92cb6a36d6b7497527de565d89924bb91802";
		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "InActive");
		
		
		int id=given()
		  .headers("Authorization","Bearer "+bearerToken)
		  .contentType("application/json")
		  .body(data.toString())
		  
		.when()
		  .post("https://gorest.co.in/public/v2/users")
		  .jsonPath().getInt("id");
		
		System.out.println("Generated id is: "+id);
		
		context.getSuite().setAttribute("user_id", id);//it is like a envi variable
		context.getSuite().setAttribute("bearerToken", bearerToken);//it is like a envi variable

	}
}
