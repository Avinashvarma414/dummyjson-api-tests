package com.api.automation.tests.auth;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import com.api.automation.base.RequestSpecFactory;

public class RequestSpecSanityTest {

    @Test
    public void verifyBaseSpecWorks() {
        given(RequestSpecFactory.baseSpec())
                .when()
                .get("/test")
                .then()
                .statusCode(200);
    }
}
