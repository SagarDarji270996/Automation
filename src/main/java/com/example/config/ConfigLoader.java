package com.example.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String QA_PROPERTIES = "config/qa.properties";
    private static final String DEMO_PROPERTIES = "config/demo.properties";
    private static final String PROD_PROPERTIES = "config/prod.properties";
    private static final String USERS_PROPERTIES = "config/users.properties";
    private static final String DEFAULT_ENV = "qa"; // Default environment

    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        // Determine environment: from system property "env", then environment variable "ENV", then default
        String environment = System.getProperty("env", System.getenv().getOrDefault("ENV", DEFAULT_ENV)).toLowerCase();
        String envPropertiesFile;

        switch (environment) {
            case "qa":
                envPropertiesFile = QA_PROPERTIES;
                break;
            case "demo":
                envPropertiesFile = DEMO_PROPERTIES;
                break;
            case "prod":
                envPropertiesFile = PROD_PROPERTIES;
                break;
            default:
                System.err.println("Warning: Unknown environment '" + environment + "'. Defaulting to QA.");
                envPropertiesFile = QA_PROPERTIES;
        }

        // Load environment-specific properties
        try (InputStream envInputStream = getClass().getClassLoader().getResourceAsStream(envPropertiesFile)) {
            if (envInputStream == null) {
                throw new FileNotFoundException("Environment properties file not found: " + envPropertiesFile);
            }
            properties.load(envInputStream);
            System.out.println("Loaded properties for environment: " + environment);
        } catch (IOException e) {
            System.err.println("Error loading environment properties file: " + envPropertiesFile);
            e.printStackTrace(); // Consider more robust error handling
        }

        // Load user properties
        try (InputStream usersInputStream = getClass().getClassLoader().getResourceAsStream(USERS_PROPERTIES)) {
            if (usersInputStream == null) {
                throw new FileNotFoundException("Users properties file not found: " + USERS_PROPERTIES);
            }
            properties.load(usersInputStream);
             System.out.println("Loaded user properties from: " + USERS_PROPERTIES);
        } catch (IOException e) {
            System.err.println("Error loading users properties file: " + USERS_PROPERTIES);
            e.printStackTrace(); // Consider more robust error handling
        }
    }

    public String getProperty(String key) {
        String property = properties.getProperty(key);
        if (property == null) {
            System.err.println("Warning: Property '" + key + "' not found in loaded configuration files.");
        }
        return property;
    }

    public String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public String getApiBaseUrl() {
        return getProperty("apiBaseUrl");
    }

    public String getDbHost() {
        return getProperty("dbHost");
    }

    public String getDbPort() {
        return getProperty("dbPort");
    }

    public String getUsername(String userType) {
        return getProperty(userType.toLowerCase() + ".username");
    }

    public String getPassword(String userType) {
        return getProperty(userType.toLowerCase() + ".password");
    }

    // Example of how to use it
    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();
        System.out.println("Base URL: " + configLoader.getBaseUrl());
        System.out.println("SuperAdmin Username: " + configLoader.getUsername("SuperAdmin"));
        System.out.println("SuperAdmin Password: " + configLoader.getPassword("SuperAdmin"));

        // To test with a different environment, run with -Denv=demo or set ENV=demo
        // e.g., java -Denv=demo com.example.config.ConfigLoader
    }
}
