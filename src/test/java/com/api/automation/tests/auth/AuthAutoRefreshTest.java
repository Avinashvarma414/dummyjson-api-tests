package com.api.automation.tests.auth;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

import com.api.automation.base.TokenManager;
import com.api.automation.utils.ApiExecutor;

public class AuthAutoRefreshTest {

    @Test(groups = {"smoke", "regression"})
    public void verifyTokenAutoRefreshAndRetry() {

        // Force invalid token
        TokenManager.invalidateAccessTokenForTest();

        Response response = ApiExecutor.getWithAuthRetry("/auth/me");

        assertEquals(response.getStatusCode(), 200);
    }
}
