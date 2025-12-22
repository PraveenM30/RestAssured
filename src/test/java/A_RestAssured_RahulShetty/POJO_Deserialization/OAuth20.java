package A_RestAssured_RahulShetty.POJO_Deserialization;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth20 {

	@Test
	public void Auth2() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String tokenGeneration=given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("/oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).log().body()
		.extract().response().asString();
		JsonPath js=new JsonPath(tokenGeneration);
		String access_token=js.getString("access_token");
		System.out.println("------------Generated token is : "+access_token);
		
		System.out.println("=======================Below is for get course===============");
		
		A_getCourseDetails gc=given()
		.queryParam("access_token", access_token)
		.when().get("/oauthapi/getCourseDetails")
		.then().assertThat().statusCode(401)
		.extract().as(A_getCourseDetails.class);

		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		List<C_api> a= gc.getCourses().getApi();
		
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("--------price of the Soup UI course is :"+a.get(i).getPrice());
			}
		}
		
		List<C_webAutomation> b= gc.getCourses().getWebAutomation();
		for(int i=0;i<b.size();i++) {
			System.out.println("-----WebAutomation course is :"+b.get(i).getCourseTitle());
		}

	}
}
