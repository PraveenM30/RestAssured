package Authorization;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class API_Key {

	@Test
	public void apiKey_QP() {
		given()
		  .queryParam("apiKey", "b4b8e97c51d84979888f0061e409c363")
		  .queryParam("Country", "US")
		.when()
		  .get("https://newsapi.org/v2/top-headlines?Country=US")
		.then()
		  .statusCode(200);
		  //.log().all();
	}
	@Test
	public void apiKey_RH() {
		given()
		  .queryParam("Country", "US")
		  .header("x-api-Key","b4b8e97c51d84979888f0061e409c363")
		.when()
		  .get("https://newsapi.org/v2/top-headlines?Country=US")
		.then()
		  .statusCode(200)
		  .header("server", "cloudflare")
		  .body("articles[0].source.name",equalTo("Bloomberg"))
		  .log().all();
	}
}
