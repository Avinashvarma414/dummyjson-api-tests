package com.api.automation.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

import com.api.automation.config.FrameworkConfig;

public class RequestSpecFactory {

    private RequestSpecFactory() {}

    public static RequestSpecification authSpec() {
        String token = TokenManager.getAccessToken();

        return new RequestSpecBuilder()
            .setBaseUri("https://dummyjson.com")
            .addHeader("Authorization", "Bearer " + token)
            .addCookie("accessToken", token) // 🔥 REQUIRED
            .setContentType(ContentType.JSON)
            .build();
    }


    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(FrameworkConfig.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

}
