package A_RestAssured_RahulShettyAcademy_EndToEnd;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import A_RestAssured_RahulShettyAcademy_EndToEnd.POJO.CreateOrder_Request_OrderDetails_POJO;
import A_RestAssured_RahulShettyAcademy_EndToEnd.POJO.CreateOrder_Request_Order_POJO;
import A_RestAssured_RahulShettyAcademy_EndToEnd.POJO.CreateOrder_Response;
import A_RestAssured_RahulShettyAcademy_EndToEnd.POJO.Login_Request_POJO;
import A_RestAssured_RahulShettyAcademy_EndToEnd.POJO.Login_Response_POJO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class A_logIn {

	@Test
	public void logIn() {
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		Login_Request_POJO login=new Login_Request_POJO();
		login.setUserEmail("forrtesting123@gmail.com");
		login.setUserPassword("Password@123");
		
	RequestSpecification reqlogin=given().log().body().spec(req).body(login);
		
	Login_Response_POJO LoginResponse=reqlogin.when().post("/api/ecom/auth/login")
		.then().log().body().assertThat().statusCode(200).extract().response().as(Login_Response_POJO.class);
	
	String token=LoginResponse.getToken();
	System.out.println("-------TOKEN : "+token);
	System.out.println("-------MESSAGE : "+LoginResponse.getMessage());
	String UserId=LoginResponse.getUserId();
	System.out.println("-------USERID : "+UserId);

	//Add product
	
	RequestSpecification AddProductBaseURL=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token).build();
	
	RequestSpecification AddProduct=given().spec(AddProductBaseURL).log().body()
			.formParam("productName", "Indian Jersey")
			.formParam("productAddedBy", UserId)
			.formParam("productCategory", "Fashion")
			.formParam("productSubCategory", "Sports")
			.formParam("productPrice", "18000")
			.formParam("productDescription", "One8")
			.formParam("productFor", "Sports")
			.multiPart("productImage",new File("C:\\Users\\user\\eclipse-workspace\\RestAssuredAutomation_BDD\\src\\test\\resources\\Virat Kohli.png"));
	String AddproductResponse=AddProduct.when().post("/api/ecom/product/add-product")
	.then().log().body().extract().response().asString();
	
	JsonPath js=new JsonPath(AddproductResponse);
	String productId=js.getString("productId");

	//Create Order
	
	RequestSpecification req2=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization", token).build();
	
	
	CreateOrder_Request_OrderDetails_POJO o=new CreateOrder_Request_OrderDetails_POJO();
	o.setCountry("India");
	o.setProductOrderedId(productId);
	
	List<CreateOrder_Request_OrderDetails_POJO> l=new ArrayList<CreateOrder_Request_OrderDetails_POJO>();
	l.add(o);
	CreateOrder_Request_Order_POJO order=new CreateOrder_Request_Order_POJO();
	order.setOrders(l);
	
	RequestSpecification createOrder=given().log().body().spec(req2).body(order);

	CreateOrder_Response CreateOrderResponse=createOrder.when().post("/api/ecom/order/create-order")
	.then().log().body().extract().response().as(CreateOrder_Response.class);
	
	String orders=CreateOrderResponse.getOrders().get(0);
	String productOrderId=CreateOrderResponse.getOrders().get(0);
	String message=CreateOrderResponse.getMessage();
	System.out.println("------Order id : "+orders);
	System.out.println("------ProductOrderid : "+productOrderId);
	System.out.println("------Message : "+message);

	// DeleteOrder
	
	RequestSpecification req3=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();

	RequestSpecification deleteOrder=given().log().body().spec(req3).pathParam("productId", productId);

	String deleteOrderResponse=deleteOrder.when().delete("/api/ecom/product/delete-product/{productId}")
	.then().log().body().extract().response().asString();
	
    JsonPath js4=new JsonPath(deleteOrderResponse);
    System.out.println(js4.getString("message"));
	}
}
