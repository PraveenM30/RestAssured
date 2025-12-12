package A_RestAssured_RahulShetty;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class R1 {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res=given()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(R1_payload.payload())
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.assertThat().statusCode(200)
		.header("Server", "Apache/2.4.52 (Ubuntu)")
		.body("scope",equalTo("APP"))
		.extract().response().asString();
		
		System.out.println("Extracted response is: "+res);
				
		JsonPath js=new JsonPath(res);
		String placeID=js.getString("place_id");
		System.out.println("Extracted place id is: "+placeID);
		
		
		//Update the place
		String newAddress="Winter House, Africa";

		given()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then()
		.assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"))
		.log().body();
		
		//get place
		
		String getPlaceResponse=given()
		.queryParam("place_id", placeID)
		.queryParam("key", "qaclick123")
		.when().get("/maps/api/place/get/json")
		.then()
		.assertThat().statusCode(200)
		.extract().response().asString();

		JsonPath jss=new JsonPath(getPlaceResponse);
		String UpdatedAddress=jss.getString("address");
		System.out.println("Updated address is: "+UpdatedAddress);
	    
		Assert.assertEquals(UpdatedAddress, newAddress);
	}
}
