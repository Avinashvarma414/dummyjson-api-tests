package com.api.automation.base;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {

    private static String accessToken;
    private static String refreshToken;

    public static String getAccessToken() {
        if (accessToken == null) {
            login();
        }
        return accessToken;
    }

    public static void invalidateAccessTokenForTest() {
        accessToken = "INVALID_TOKEN";
        if (refreshToken == null) {
            login(); // ensure refreshToken exists
            accessToken = "INVALID_TOKEN";
        }
    }


    public static synchronized void refreshAccessToken() {
        if (refreshToken == null) {
            login();
            return;
        }

        Response response =
            RestAssured.given()
                .contentType("application/json")
                .body(Map.of("refreshToken", refreshToken))
                .post("https://dummyjson.com/auth/refresh");

        if (response.getStatusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        } else {
            login(); // fallback
        }
    }


    private static void login() {
        Response response =
            io.restassured.RestAssured.given()
                .contentType("application/json")
                .body(Map.of(
                    "username", "emilys",
                    "password", "emilyspass"
                ))
                .post("https://dummyjson.com/auth/login");

        accessToken = response.jsonPath().getString("accessToken");
        refreshToken = response.jsonPath().getString("refreshToken");
    }
}
