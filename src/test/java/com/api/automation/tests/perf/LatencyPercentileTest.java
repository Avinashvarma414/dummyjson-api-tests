package com.api.automation.tests.perf;

import com.api.automation.clients.ProductsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LatencyPercentileTest {

    @Test(groups = {"performance"})
    public void verifyProductsP95Latency() {
//    	int rc=100;

        List<Long> responseTimes = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Response response = ProductsClient.getAllProducts();
            assertEquals(response.getStatusCode(), 200);
            responseTimes.add(response.getTime());
        }

        Collections.sort(responseTimes);

        int index = (int) Math.ceil(0.95 * responseTimes.size()) - 1;
        long p95 = responseTimes.get(index);

        System.out.println("P95 latency = " + p95 + " ms");

        assertTrue(
            p95 < 2000,
            "P95 latency exceeded SLA. Actual: " + p95
        );
    }
}
