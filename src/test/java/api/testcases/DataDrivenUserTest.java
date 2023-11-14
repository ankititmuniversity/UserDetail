package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.UserDataProvider;
import io.restassured.response.Response;

public class DataDrivenUserTest {
	UserPayload userPayload;
	
	@Test(priority=1, dataProvider="AllDetailOfUser",dataProviderClass=UserDataProvider.class)
	public void testCreateUser(String id,String username,String firstname,String lastname,String email,String password,String phone) {
		userPayload=new UserPayload();
		System.out.println("ID : "+Integer.parseInt(id));
		System.out.println("Phone No : "+phone);
		System.out.println("username is : "+username);
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(username);
		userPayload.setFirstname(firstname);
		userPayload.setLastName(lastname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		Response response=UserEndPoints.createUser(userPayload);
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test (priority=2)
	public void testGetUser() {

		Response response=UserEndPoints.getUser(userPayload.getUsername());
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		}
	@Test (priority=3)
	public void testUpdateUser() {

		userPayload.setFirstname(userPayload.getUsername());
		Response response=UserEndPoints.updateUser(userPayload.getUsername(),userPayload);
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Data after Update");
		response=UserEndPoints.getUser(this.userPayload.getUsername());
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test (priority=4)
	public void testdeleteUser() {
		System.out.println(userPayload.getUsername());
		Response response=UserEndPoints.deleteUser(userPayload.getUsername());
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
