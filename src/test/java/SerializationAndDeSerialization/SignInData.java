package SerializationAndDeSerialization;

public class SignInData {

	public String emailId;
	public String password;
	public String lastSessionData;

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	public void setLastSessionData(String lastSessionData) {
		this.lastSessionData = lastSessionData;
	}

	public String getLastSessionData() {
		return lastSessionData;
	}
	
	public String getSignInData() {
		return (this.emailId+"   "+this.password+"   "+this.lastSessionData);
	}
}
