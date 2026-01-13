package com.api.automation.tests.products;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

import com.api.automation.clients.ProductsClient;
import com.api.automation.base.TokenManager;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ProductsNegativeTest {

    @Test(groups = {"regression"})
    public void verifyGetProductWithInvalidId() {

        Response response = ProductsClient.getProductById(999999);

        assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression"})
    public void verifySearchWithNoResults() {

        Response response = ProductsClient.searchProducts("abcdefxyz");

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getList("products").size(), 0);
    }

    @Test
    public void verifyProductsAccessibleEvenWithInvalidToken() {

        TokenManager.invalidateAccessTokenForTest();

        Response response = ProductsClient.getAllProducts();

        assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression"})
    public void verifyInvalidProductErrorSchema() {

        Response response = ProductsClient.getProductById(999999);

        assertEquals(response.getStatusCode(), 404);

        response.then()
                .body(matchesJsonSchemaInClasspath(
                        "schemas/error-schema.json"));
    }
}
