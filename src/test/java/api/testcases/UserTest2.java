package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPayload;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	UserPayload userPayload;
	public static Logger logger;
	@BeforeClass
	public void generateTestData() {
		faker=new Faker();
		userPayload=new UserPayload();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,8));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		logger=LogManager.getLogger("RESTAPIFrameworkDesign");
	}
	@Test(priority=1)
	public void testCreateUser() {

		Response response=UserEndPoints2.createUser(userPayload);
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Create user executed");
	}
	@Test (priority=2)
	public void testGetUser() {

		Response response=UserEndPoints2.getUser(this.userPayload.getUsername());
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Get user executed");
	}
	@Test (priority=3)
	public void testUpdateUser() {

		userPayload.setFirstname(faker.name().username());
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
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
		logger.info("Update user executed");
	}
	@Test (priority=4)
	public void testdeleteUser() {
		System.out.println(this.userPayload.getUsername());
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		//log response
		response.then().log().all();
		//Validation
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Delete user executed");
	}

}
