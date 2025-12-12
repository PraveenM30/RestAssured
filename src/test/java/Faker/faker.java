package Faker;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class faker {

	@Test
	public void generateRan() {
		Faker faker =new Faker();
		String Full_Name=faker.name().fullName();
		String First_Name=faker.name().firstName();
		String Last_Name=faker.name().lastName();
		String UN=faker.name().username();
		String pwd=faker.internet().password();
		String Phone_Num=faker.phoneNumber().phoneNumber();
		String eMail=faker.internet().emailAddress();
		
		System.out.println("Full_Name : "+Full_Name);
		System.out.println("First_Name : "+First_Name);
		System.out.println("Last_Name : "+Last_Name);
		System.out.println("UN : "+UN);
		System.out.println("pwd : "+pwd);
		System.out.println("Phone_Num : "+Phone_Num);
		System.out.println("eMail : "+eMail);
	}
}
