package com.api.automation.tests.auth;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import com.api.automation.base.RequestSpecFactory;
import com.api.automation.base.ResponseSpecFactory;

public class ResponseSpecSanityTest {

    @Test
    public void verifySuccessResponseSpec() {
        given(RequestSpecFactory.baseSpec())
                .when()
                .get("/test")
                .then()
                .spec(ResponseSpecFactory.successSpec());
    }
}
