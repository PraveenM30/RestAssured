package SerializationAndDeSerialization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class reqres_Create {

	@Test(priority = 1)
	public void create() {
		reqres_Data rd=new reqres_Data();
		rd.setName("Praveen");
		rd.setJob("Software Test Engineer");
		rd.setAge(25);
		
	given()
	  .baseUri("https://reqres.in/api/users")
	  .header("Content-type","application/json")
	  .body(rd)
	.when()
	  .post()
	.then()
	  .statusCode(201)
	  .assertThat().body("name", equalTo("Praveen"))
	  .log().all();
	}
	
	@Test(priority = 2)
	public void getCreatedUser() {
		
		reqres_Data r=RestAssured.get("https://reqres.in/api/unknown/2").as(reqres_Data.class);
		System.out.println(r.getData().GetreqresData());
		
	}
}
