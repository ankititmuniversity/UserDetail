package api.endpoints;
import java.util.ResourceBundle;

import api.payload.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	public static ResourceBundle getURL() {
		ResourceBundle routes=ResourceBundle.getBundle("Routes");
		return routes;
	}
	public static Response createUser(UserPayload payload) {
		String post_url=getURL().getString("post_url");
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        //This is for which type of content it will accept.
				.contentType(ContentType.JSON)	   //This is for which type of content we will send with request body.	
				.body(payload)

				.when()
				.post(post_url);

		return response;
	}

	public static Response getUser(String username) {
		String get_url=getURL().getString("get_url");
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.pathParam("username", username)

				.when()
				.get(get_url);

		return response;
	}

	public static Response updateUser(String username,UserPayload payload) {
		String put_url=getURL().getString("put_url");
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.contentType(ContentType.JSON)	 
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(put_url);

		return response;
	}

	public static Response deleteUser(String username) {
		String delete_url=getURL().getString("delete_url");
		Response response=RestAssured.given()
				.accept(ContentType.JSON)        
				.pathParam("username", username)

				.when()
				.delete(delete_url);

		return response;
	}

}
