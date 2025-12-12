package Reqres_BDD;

public class A_ReqresData {

	private String name;
	private String job;
	
	public void setname(String name) {
		this.name= name;
	}
	
	public String getname() {
		return name;
	}
	
	public void setjob(String job) {
		this.job=job;
	}
	
	public String getjob() {
		return job;
	}
	
	public String getAllData() {
		return (this.name+" "+this.job);
	}
}
