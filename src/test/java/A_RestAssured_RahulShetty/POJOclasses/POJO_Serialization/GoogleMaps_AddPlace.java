package A_RestAssured_RahulShetty.POJOclasses.POJO_Serialization;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class GoogleMaps_AddPlace {
	@Test
public void AddPlace() {
	AddPlaceBody b=new AddPlaceBody();
	Location l=new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	
	b.setLocation(l);
	b.setAccuracy(50);
	b.setName("Frontline house");
	b.setPhone_number("(+91) 983 893 3937");
	b.setAddress("29, side layout, cohen 09");
	List<String> t=new ArrayList<String>();
	t.add("shoe park");
	t.add("shoe");
	b.setTypes(t);
	b.setWebsite("http://google.com");
	b.setLanguage("French-IN");
		
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String res=given().log().all()
	.queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body(b)
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).log().body()
	.extract().response().asString();
}
}
