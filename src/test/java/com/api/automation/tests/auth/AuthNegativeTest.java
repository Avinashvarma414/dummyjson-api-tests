package com.api.automation.tests.auth;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AuthNegativeTest {
	@Test(groups = {"negative"})
	public void verifyLoginWithInvalidPassword() {

	    Response response =
	        io.restassured.RestAssured.given()
	            .contentType("application/json")
	            .body("{\"username\":\"emilys\",\"password\":\"wrongpass\"}")
	            .post("https://dummyjson.com/auth/login");

	    assertEquals(response.getStatusCode(), 400);
	}
	@Test(groups = {"negative"})
	public void verifyLoginWithMissingCredentials() {

	    Response response =
	        io.restassured.RestAssured.given()
	            .contentType("application/json")
	            .body("{}")
	            .post("https://dummyjson.com/auth/login");

	    assertTrue(
	        response.getStatusCode() == 400 || response.getStatusCode() == 422
	    );
	}
	@Test(groups = {"negative"})
	public void verifyAuthMeWithoutTokenFails() {

	    Response response =
	        io.restassured.RestAssured.given()
	            .get("https://dummyjson.com/auth/me");

	    assertTrue(
	        response.getStatusCode() == 401 || response.getStatusCode() == 403
	    );
	}
	@Test(groups = {"negative"})
	public void verifyAuthMeWithInvalidTokenFails() {

	    Response response =
	        io.restassured.RestAssured.given()
	            .header("Authorization", "Bearer INVALID_TOKEN")
	            .get("https://dummyjson.com/auth/me");

	    assertTrue(
	        response.getStatusCode() == 401 || response.getStatusCode() == 403
	    );
	}
	@Test(groups = {"negative"})
	public void verifyRefreshWithInvalidTokenFails() {

	    Response response =
	        io.restassured.RestAssured.given()
	            .contentType("application/json")
	            .body("{\"refreshToken\":\"INVALID_REFRESH\"}")
	            .post("https://dummyjson.com/auth/refresh");

	    // 1️⃣ Acceptable failure statuses
	    int status = response.getStatusCode();
	    assertTrue(status == 400 || status == 403);

	    // 2️⃣ Error message must exist
	    String message = response.jsonPath().getString("message");
	    assertTrue(message != null && !message.isEmpty());

	    // 3️⃣ Must not return new access token
	    assertTrue(response.jsonPath().get("accessToken") == null);
	}







}
