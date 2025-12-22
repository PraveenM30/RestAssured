package A_RestAssured_RahulShettyAcademy_EndToEnd.POJO;

import java.util.List;

public class CreateOrder_Request_Order_POJO {
	
	private List<CreateOrder_Request_OrderDetails_POJO> orders;

	public List<CreateOrder_Request_OrderDetails_POJO> getOrders() {
		return orders;
	}

	public void setOrders(List<CreateOrder_Request_OrderDetails_POJO> orders) {
		this.orders = orders;
	}


	
	
}
