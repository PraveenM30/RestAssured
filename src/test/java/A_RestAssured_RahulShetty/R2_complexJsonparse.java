package A_RestAssured_RahulShetty;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class R2_complexJsonparse {

	public static void main(String[] args) {
		int sum = 0;
		JsonPath js = new JsonPath(R1_payload.Complexpayload());

		// 1. Print No of courses returned by API
		int CourseSize = js.getInt("courses.size()");
		System.out.println(CourseSize);

		// 2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		// 3. Print Title of the first course
		int price = js.getInt("courses[0].price");
		System.out.println(price);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < CourseSize; i++) {
			System.out.println(js.get("courses[" + i + "].title").toString());
			System.out.println(js.getInt("courses[" + i + "].price".toString()));
		}

		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < CourseSize; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			if (courseTitle.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("RPA copies count is: " + copies);
				break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < CourseSize; i++) {
			int price1 = js.getInt("courses[" + i + "].price");
			int copies1 = js.getInt("courses[" + i + "].copies");
			int total = price1 * copies1;
			sum = sum + total;
		}
		System.out.println("Actual SUM of purchase amount is: " + sum);
		int TotalpurchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, TotalpurchaseAmount);
	}
}
