package Reqres_BDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import junit.framework.Assert;

public class B_DiffWaysToCreatePostMethod {
	int id;
	
	@Test(priority = 1)
	public void postUsingHashmap() {
		
		HashMap map=new HashMap();
			map.put("name", "Praveen");
			map.put("Job", "Software Test Engineer");
		
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users")
		   .header("content-Type","application/json")
		   .body(map)
		.when()	
		   .post()
		.then()
		   .statusCode(201)
		   .body("name", equalTo("Praveen"))
		   .header("Content-Type", "application/json; charset=utf-8")
		   .log().all()
		   .extract().response();		
		String responseBody=response.asString();
		int id =response.jsonPath().getInt("id");
		Assert.assertEquals(responseBody.contains("Engineer"), true);
	}
	
	//@Test(priority = 1)
	public void postUsingJSONLibrary() {
		
	JsonObject data=new JsonObject();
	data.addProperty("name", "Manoj");
	data.addProperty("Job", "Software Test Engineer");
	
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users")
		   .header("content-Type","application/json")
		   .body(data.toString())
		.when()	
		   .post()
		.then()
		   .statusCode(201)
		   .body("name", equalTo("Manoj"))
		   .header("Content-Type", "application/json; charset=utf-8")
		   .log().all()
		   .extract().response();		
		String responseBody=response.asString();
		int id =response.jsonPath().getInt("id");
		Assert.assertEquals(responseBody.contains("Engineer"), true);
	}
	//@Test(priority = 1)
	public void postUsingPOJOClass() {
		
		B_POJOclass data=new B_POJOclass();
		data.setName("Dharani");
		data.setJob("Executive");
		//this data in java object

		
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users")
		   .header("content-Type","application/json")//here the pojo is converted into json
		   .body(data)
		.when()	
		   .post()
		.then()
		   .statusCode(201)
		   .body("name", equalTo("Dharani"))
		   .header("Content-Type", "application/json; charset=utf-8")
		   .log().all()
		   .extract().response();		
		String responseBody=response.asString();
		int id =response.jsonPath().getInt("id");
		Assert.assertEquals(responseBody.contains("Executive"), true);
	}
	
	//@Test(priority = 1)
	public void postUsingExternalJosnFile() throws FileNotFoundException {
		
		File f=new File(".\\body.json");
		java.io.FileReader fr=new java.io.FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		Response response=
		given()
		   .baseUri("https://reqres.in")
		   .basePath("/api/users")
		   .header("content-Type","application/json")
		   .body(data.toString())
		.when()	
		   .post()
		.then()
		   .statusCode(201)
		   .body("name", equalTo("Praveen M"))
		   .header("Content-Type", "application/json; charset=utf-8")
		   .log().all()
		   .extract().response();		
		String responseBody=response.asString();
		int id =response.jsonPath().getInt("id");
		Assert.assertEquals(responseBody.contains("Engineer"), true);
	}
	@Test(priority = 2)
	public void delete() {
		given()
		.when()
		  .delete("https://reqres.in/api/users/"+id)
		.then()
		  .statusCode(204)
		  .log().all();
	}
}

