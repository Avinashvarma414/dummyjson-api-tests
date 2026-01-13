package com.api.automation.config;

import java.io.InputStream;
import java.util.Properties;

public class FrameworkConfig {

    private static Properties properties = new Properties();

    static {
        try {
            String env = System.getProperty("env", "qa");
            InputStream input = FrameworkConfig.class
                    .getClassLoader()
                    .getResourceAsStream("env/" + env + ".properties");
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
