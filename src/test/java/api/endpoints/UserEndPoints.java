package api.endpoints;
import api.payload.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(UserPayload payload) {
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        //This is for which type of content it will accept.
				.contentType(ContentType.JSON)	   //This is for which type of content we will send with request body.	
				.body(payload)

				.when()
				.post(Routes.post_url);

		return response;
	}

	public static Response getUser(String username) {
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.pathParam("username", username)

				.when()
				.get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String username,UserPayload payload) {
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.contentType(ContentType.JSON)	 
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(Routes.put_url);

		return response;
	}

	public static Response deleteUser(String username) {
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.pathParam("username", username)

				.when()
				.delete(Routes.delete_url);

		return response;
	}

}
