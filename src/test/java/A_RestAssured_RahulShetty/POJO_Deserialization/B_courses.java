package A_RestAssured_RahulShetty.POJO_Deserialization;

import java.util.List;

public class B_courses {
	private List<C_webAutomation> webAutomation;
	private List<C_api> api;
	private List<C_mobile> mobile;

	public List<C_webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<C_webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<C_api> getApi() {
		return api;
	}
	public void setApi(List<C_api> api) {
		this.api = api;
	}
	public List<C_mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<C_mobile> mobile) {
		this.mobile = mobile;
	}
	
}
