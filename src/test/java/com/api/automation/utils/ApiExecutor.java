package com.api.automation.utils;

import com.api.automation.base.RequestSpecFactory;
import io.restassured.response.Response;
import com.api.automation.base.TokenManager;
import io.qameta.allure.restassured.AllureRestAssured;

import io.restassured.specification.RequestSpecification;

import java.util.function.Function;

import static io.restassured.RestAssured.given;

public class ApiExecutor {

    private ApiExecutor() {}

    public static Response executeWithAuthRetry(
            Function<RequestSpecification, Response> action) {

        Response response = execute(action);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            TokenManager.refreshAccessToken();   // refresh token
            response = execute(action);          // retry with NEW token
        }

        return response;
    }

    public static Response getWithAuthRetry(String endpoint) {
        return executeWithAuthRetry(
                spec -> spec.get(endpoint)
        );
    }

    private static Response execute(Function<RequestSpecification, Response> action) {
        return action.apply(
                given()
                		.filter(new AllureRestAssured()) 
                        .spec(RequestSpecFactory.authSpec())
                        .when()
        )
        .then()
        .extract()
        .response();
    }
    
}

