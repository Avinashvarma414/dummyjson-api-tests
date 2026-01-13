package com.api.automation.tests.auth;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import com.api.automation.base.RequestSpecFactory;

public class AuthMeSanityTest {

    @Test
    public void verifyAuthMeWorks() {
        given(RequestSpecFactory.authSpec())
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200);
    }
}
