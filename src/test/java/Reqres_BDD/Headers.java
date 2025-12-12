package Reqres_BDD;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class Headers {

	@Test(priority = 1)
	public void VerifyHeaders() {
		given()
		.when()
		  .get("https://www.google.com")
		.then()
		  .header("Content-Encoding", "gzip")
		  .header("Server", "gws")
		  .header("Content-Type", "text/html; charset=ISO-8859-1");
	}
	
	@Test(priority = 2)
	public void getHeaders() {
		Response res=given()
		.when()
		  .get("https://www.google.com");
		
		String content_Type=res.getHeader("Content-Type");
		System.out.println("content type :"+content_Type);
	}
	

	@Test(priority = 3)
	public void getAllHeadersInfo() {
		Response res=given()
		.when()
		  .get("https://www.google.com");
		
		io.restassured.http.Headers myHeaders =res.getHeaders();
		for(Header hd:myHeaders) {
			System.out.println(hd.getName()+"  "+hd.getValue());
		}
	}
}
