package com.api.automation.base;

import com.api.automation.config.FrameworkConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecFactory {

    private ResponseSpecFactory() {
        // prevent instantiation
    }

    /**
     * Generic success response spec
     * Used for 200 / 201 responses
     */
    public static ResponseSpecification successSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan((long) FrameworkConfig.getTimeout()))
                .build();
    }

    /**
     * Delete response spec
     * Used for simulated delete endpoints
     */
    public static ResponseSpecification deleteSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectBody("isDeleted", org.hamcrest.Matchers.equalTo(true))
                .expectBody("deletedOn", org.hamcrest.Matchers.notNullValue())
                .build();
    }
}
