package com.api.automation.tests.perf;

import com.api.automation.base.TokenManager;
import com.api.automation.utils.ApiExecutor;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AuthRecoveryPerformanceTest {

    @Test(groups = {"performance"})
    public void verifyAuthAutoRefreshLatency() {

        TokenManager.invalidateAccessTokenForTest();

        long start = System.currentTimeMillis();

        Response response = ApiExecutor.getWithAuthRetry("/auth/me");

        long totalTime = System.currentTimeMillis() - start;

        assertEquals(response.getStatusCode(), 200);

        assertTrue(
            totalTime < 2500,
            "Auth recovery too slow. Time: " + totalTime + " ms"
        );

        System.out.println("Auth recovery time = " + totalTime + " ms");
    }
}
