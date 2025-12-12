package SerializationAndDeSerialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class SignInWithSerialization {
	//public static HashMap<String, String> map = new HashMap<>();

	@Test(priority = 1)
	public void SignIn() {
		SignInData s = new SignInData();
		s.setEmailId("pooja.g@fireflink.com");
		s.setPassword("TLUDFQQHo5grv7C2TGhphMKRIBrql1t295HmPj7PDIsxgzd7K93/iAQp8jesKpXddGADP8ic+/SDANztNkTz2ZB1E9CPzG7a7W5Kb1agV40v3g51wkjwhPenz/dCpFrpTp3uak+Upb6GkQk5vmm8RI//RbMU80S1CcGoeaX5wyPQvHcOnFQvy/WtavbyJ8d0jzFRg9VLgs8fkUjwSNrWHVxJ3Zf/21duKr0ZCpjgPz1Pgb/NmLEWpkh6iAnLoxu4K5UcleH1EZ84t4wgDJveY/WGR0HdHcHPqtL0NZIoUZVHUELQSrgjCz83NO/lpei2JG0nJpUvNujguj1ue4fAfA==");
		s.setLastSessionData("/signin");

		given().baseUri("https://app.fireflink.com").basePath("/appmanagement/optimize/v1/public/user/signin")
				.header("Content-Type", "application/json").body(s)
		.when()
		        .post()
		.then()
				.statusCode(200)
				.body("responseCode", equalTo(200))
				.body("responseCode", equalTo(200)).log().all();
	}

//	@Test(priority = 2)
//	public void Deserialization() {
//		SignInData s = given().when().get("").as(SignInData.class);
//		System.out.println(s.getEmailId());
//	}
}
