package Authorization;
import static io.restassured.RestAssured.*;

public class Oauth1_And_2 {

	public void Oauth1() {
		given()
		  .auth().oauth("consuner key", "consumer secret","access token","Token secret")
		.when()
		  .get()
		.then();
	}
	
	public void Oauth2() {
		given()
		  .auth().oauth2("Token")
		.when()
		  .get()
		.then();
	}
}
