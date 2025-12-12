package restAssuredTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TC006_BasicValidations_XML {
	
	 String xmlBody = 
	            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
	            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
	            "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
	            "    <soap:Body>\n" +
	            "        <FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">\n" +
	            "            <Fahrenheit>100</Fahrenheit>\n" +
	            "        </FahrenheitToCelsius>\n" +
	            "    </soap:Body>\n" +
	            "</soap:Envelope>";
	
	@Test
	public void ValidateXMLApproach_1() {
		given()
		  .baseUri("https://www.w3schools.com/xml/tempconvert.asmx")  // Replace with actual endpoint
          .header("Content-Type", "text/xml")  // ✅ Required for SOAP
          .header("SOAPAction", "https://www.w3schools.com/xml/FahrenheitToCelsius") // ✅ Mandatory SOAP Action
          .body(xmlBody)
        .when()
          .post()
        .then()
          .log().all()
          .body("Envelope.Body.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult\r\n",equalTo("37.7777777777778"))
          
          //writing xpath for XML response
          .body(hasXPath("//*[local-name()='FahrenheitToCelsiusResult']",containsString("37.7777777777778")));

	}
	
	@Test
	public void ValidateXMLApproach_2() {
		Response res=
		given()
		  .baseUri("https://www.w3schools.com/xml/tempconvert.asmx")  // Replace with actual endpoint
          .header("Content-Type", "text/xml")  // ✅ Required for SOAP
          .header("SOAPAction", "https://www.w3schools.com/xml/FahrenheitToCelsius") // ✅ Mandatory SOAP Action
          .body(xmlBody)
        .when()
          .post();
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "text/xml; charset=utf-8");
		String data=res.xmlPath().get("Envelope.Body.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult\r\n").toString();
		Assert.assertEquals(data, "37.7777777777778");
		Assert.assertTrue(res.asString().contains("37.7777777777778"));

	}
}
