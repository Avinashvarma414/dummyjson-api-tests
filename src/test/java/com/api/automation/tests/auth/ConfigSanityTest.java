package com.api.automation.tests.auth;

import org.testng.annotations.Test;
import com.api.automation.config.FrameworkConfig;

public class ConfigSanityTest {

    @Test
    public void verifyConfigLoads() {
        System.out.println("Base URL: " + FrameworkConfig.getBaseUrl());
    }
}
