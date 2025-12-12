package Reqres_BDD;
import static io.restassured.RestAssured.given;
import java.io.File;
import org.testng.annotations.Test;


public class E_FormData {

	
	@Test
	public void formdata() {
		
		File Myfile=new File("C:\\Users\\user\\eclipse-workspace\\RestAssuredAutomation_BDD\\Screenshot (452).png");
		//Basic Authorization
//		PreemptiveBasicAuthScheme AuthScheme=new PreemptiveBasicAuthScheme();
//		AuthScheme.setUserName("rsyedabuthakir_jI7aVk");
//		AuthScheme.setPassword("cQEy6xrzrrrGosnRwufB");
//		RestAssured.authentication=AuthScheme;	
		
		//use above or .auth().preemptive().basic( 
		
		given()
		.auth().preemptive().basic("rsyedabuthakir_jI7aVk", "cQEy6xrzrrrGosnRwufB")
		.multiPart("custom_id","Test")
		.multiPart("file",Myfile)
		.contentType("multipart/form-data; boundary=<calculated when request is sent>")
		
		.when()
		.post("https://api-cloud.browserstack.com/app-automate/upload-media")
		
		.then()
		.statusCode(200)
		.log().body();
	}
}
