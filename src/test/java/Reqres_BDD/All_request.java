package Reqres_BDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class All_request {
 int id;
	@Test(priority = 1)
	public void get() {
		given()
		.when()
		  .get("https://reqres.in/api/users?page=2")
		.then()
		  .statusCode(200);
	}
	
	@Test(priority = 2)
	public void post() {
//		
//		HashMap data=new HashMap();
//		data.put("name", "Manoj");
//		data.put("job", "QA");
//		
		A_ReqresData a=new A_ReqresData();
		a.setname("Praveen M");
		a.setjob("Software Test Engineer");
		
		id=given()
		  .header("Content-Type","application/json")
		  .body(a)
		.when()
		  .post("https://reqres.in/api/users")
		  .jsonPath().getInt("id");
	}
	
	@Test(priority = 3)
	public void put() {
		
//		HashMap data=new HashMap();
//		data.put("name", "Praveen M");
//		data.put("job", "Software Test Engineer");
//		
		A_ReqresData a=new A_ReqresData();
		a.setname("Manoj B");
		a.setjob("QA");
		
		given()
		  .header("Content-Type","application/json")
		  .body(a)
		.when()
		  .put("https://reqres.in/api/users/"+id)
		.then()
		  .statusCode(200)
		  .log().all()
	      .assertThat().body("name", equalTo("Manoj B"));
	}
	
	@Test(priority = 4)
	public void delete() {
		given()
		.when()
		  .delete("https://reqres.in/api/users/"+id)
		.then()
		  .statusCode(204);
	}
}
