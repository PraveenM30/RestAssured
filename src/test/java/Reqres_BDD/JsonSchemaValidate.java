package Reqres_BDD;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;


public class JsonSchemaValidate {

	@Test
	public void jsonValidate() {
		
				given()
				.when()
				.get("https://reqres.in/api/users?page=2")
				.then()
				.assertThat().statusCode(200)
				.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchemaValidation.json"));
	}
}
