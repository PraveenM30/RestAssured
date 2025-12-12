package Reqres_BDD;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.Iterator;
import java.util.Map;

public class Cookies {

	@Test(priority = 1)
	public void cookie() {
		given()
		.when()
		  .get("https://www.google.com")
		.then()
		 //.cookie("AEC","asdfghjklkjhg")
		  .log().all();
	}

	@Test(priority = 2)
	public void getcookie() {
		Response res=given()
		.when()
		  .get("https://www.google.com");
		  
		 String cookie_value= res.getCookie("AEC");
		 System.out.println("AEC >>"+cookie_value);
	}
	
	@Test(priority = 3)
	public void getCookies() {
		Response res=given()
		.when().get("https://www.google.com");
		
		//get all cookies info
		Map<String,String> cookies_value=res.getCookies();
		System.out.println(cookies_value.keySet());
		for (String k :cookies_value.keySet()){
			String cookie_value=res.getCookie(k);  
			System.out.println(k+"   "+cookie_value);
		}
	}
	
}
