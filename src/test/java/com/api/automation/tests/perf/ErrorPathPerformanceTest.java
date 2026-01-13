package com.api.automation.tests.perf;

import com.api.automation.clients.ProductsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ErrorPathPerformanceTest {

    @Test(groups = {"performance"})
    public void verifyInvalidProductErrorIsFast() {

        Response response = ProductsClient.getProductById(999999);

        long responseTime = response.getTime();

        assertTrue(
            responseTime < 800,
            "Error response too slow: " + responseTime + " ms"
        );

        System.out.println("Error response time = " + responseTime + " ms");
    }
}
