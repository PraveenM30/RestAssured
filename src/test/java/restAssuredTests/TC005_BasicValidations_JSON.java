package restAssuredTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import java.util.HashMap;
import org.testng.annotations.Test;

public class TC005_BasicValidations_JSON {
	
	public static HashMap<String, String> map = new HashMap<>();
    
   //Verify Status code
	@Test(priority = 1)
	public void StatusCode() {
		given()
		  .baseUri("https://reqres.in/api/users")
		  .basePath("?page=2")
		.when()
		  .get()
		.then()
		  .statusCode(200);
	}
	
	//Log all Response
	@Test(priority = 2)
	public void logAll() {
		given()
		.when()
		  .get("https://reqres.in/api/users?page=3")
		.then()
		  .statusCode(200)
		  .log().all();  
	}
	
	//Verify single content from response.
	@Test(priority = 3)
	public void SingleContent() {
		given()
		.when()
		  .get("https://reqres.in/api/users?page=4")
		.then()
		  .statusCode(200)
		  .body("page", equalTo(4));
	}
	
	//Verify Multiple contents from response
	@Test(priority = 4)
	public void MultipleContent() {
		given()
		.when()
		  .get("https://reqres.in/api/users?page=2")
		.then()
		  .statusCode(200)
		  .body("data.first_name",hasItems("Michael","Lindsay"));
	}
	
	//Set params and headers for the request.
	@Test(priority = 5)
	public void setData() {
		map.put("emailId", "pooja.g@fireflink.com");
		map.put("password", "TLUDFQQHo5grv7C2TGhphMKRIBrql1t295HmPj7PDIsxgzd7K93/iAQp8jesKpXddGADP8ic+/SDANztNkTz2ZB1E9CPzG7a7W5Kb1agV40v3g51wkjwhPenz/dCpFrpTp3uak+Upb6GkQk5vmm8RI//RbMU80S1CcGoeaX5wyPQvHcOnFQvy/WtavbyJ8d0jzFRg9VLgs8fkUjwSNrWHVxJ3Zf/21duKr0ZCpjgPz1Pgb/NmLEWpkh6iAnLoxu4K5UcleH1EZ84t4wgDJveY/WGR0HdHcHPqtL0NZIoUZVHUELQSrgjCz83NO/lpei2JG0nJpUvNujguj1ue4fAfA==");
		map.put("lastSessionData", "/signin");
	}
	@Test(priority=6)
	public void SetParamsAndHeaders() {
         given()
		  .baseUri("https://backend1.fireflink.com")
		  .basePath("/appmanagement/optimize/v1/public/user/signin")
		  .header("Content-Type","application/json")
		  .body(map)
		.when()
		  .post()
		.then()
		  .statusCode(200)
		  .body("responseCode",equalTo(200))
		  .log().all();
	}
}
