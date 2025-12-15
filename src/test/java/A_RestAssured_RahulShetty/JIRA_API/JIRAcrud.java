package A_RestAssured_RahulShetty.JIRA_API;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JIRAcrud{
	@Test
	public void CreateIssue() {
		RestAssured.baseURI="https://praveenm.atlassian.net/";
		String response=given().log().all()
		.header("Content-Type","application/json")
		.header("Authorization","Basic cHJhdmVlbi5tb2hhbnN1bmRhcjlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEJFaUlKNU9YOGxXb2R0WUFZdGxaMjhENUNXdkZKekd6NVVVM0NSZUROSGVlejI2VGxfMG9jNlhnX1laN0ZQOHlnRFQ5Vzc2clY2OHdfSE5zX1BrdFdWT1BhQ3BFRnNzVFRNdFpncmpjRThERXV2b0tDT1c2alZhenFqV1g5clRFcmtuMWc1dzcxQ1JtTVVHX1BDOVN3U0laMzhrX3R6N2JsMk5rcm1wZkpDWT00MUVFQTEwRA==")
		.body(JIRApayload.Ticketpayload("Web service|UI: Unable to import the Curl"))
		.when().post("rest/api/3/issue")
		.then().assertThat().statusCode(201).log().body()
		.extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String issueId=js.getString("id");
		System.out.println("JIRA tick ID is: "+issueId);
		
		System.out.println("ADDING ATTACHMENTS  -------------------------------------------");

		given()
		.pathParam("key", issueId)
		.header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>")
		.header("Authorization","Basic cHJhdmVlbi5tb2hhbnN1bmRhcjlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEJFaUlKNU9YOGxXb2R0WUFZdGxaMjhENUNXdkZKekd6NVVVM0NSZUROSGVlejI2VGxfMG9jNlhnX1laN0ZQOHlnRFQ5Vzc2clY2OHdfSE5zX1BrdFdWT1BhQ3BFRnNzVFRNdFpncmpjRThERXV2b0tDT1c2alZhenFqV1g5clRFcmtuMWc1dzcxQ1JtTVVHX1BDOVN3U0laMzhrX3R6N2JsMk5rcm1wZkpDWT00MUVFQTEwRA==")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C:\\Users\\user\\eclipse-workspace\\RestAssuredAutomation_BDD\\Screenshot (452).png"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().assertThat().statusCode(200).log().body();
		
		System.out.print("Fetching issue details  -------------------------------------------");

		given()
		.pathParam("key", issueId)
		.header("Authorization","Basic cHJhdmVlbi5tb2hhbnN1bmRhcjlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEJFaUlKNU9YOGxXb2R0WUFZdGxaMjhENUNXdkZKekd6NVVVM0NSZUROSGVlejI2VGxfMG9jNlhnX1laN0ZQOHlnRFQ5Vzc2clY2OHdfSE5zX1BrdFdWT1BhQ3BFRnNzVFRNdFpncmpjRThERXV2b0tDT1c2alZhenFqV1g5clRFcmtuMWc1dzcxQ1JtTVVHX1BDOVN3U0laMzhrX3R6N2JsMk5rcm1wZkpDWT00MUVFQTEwRA==")
		.when().get("rest/api/3/issue/{key}")
		.then().assertThat().statusCode(200).log().body();
	}
}
