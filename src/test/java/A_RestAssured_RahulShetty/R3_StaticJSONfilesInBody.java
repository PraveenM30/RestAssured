package A_RestAssured_RahulShetty;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class R3_StaticJSONfilesInBody {
	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(
						Paths.get("C:\\Users\\user\\eclipse-workspace\\RestAssuredAutomation_BDD\\body2.json"))))
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.header("Server", "Apache/2.4.52 (Ubuntu)")
				.body("scope", equalTo("APP"))
				.extract().response().asString();

		System.out.println("Extracted response is: " + res);

		JsonPath js = new JsonPath(res);
		String placeID = js.getString("place_id");
		System.out.println("Extracted place id is: " + placeID);
	}
}
