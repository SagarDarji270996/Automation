package projects.trakzee.configurations.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private final String environment;

    public ConfigLoader() {
		System.out.println("ConfigLoader: 1");
        properties = new Properties();
		environment = System.getProperty("env", "demo");
        String configFilePath = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/configurations/config.properties";

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
            System.out.println("Properties loaded for environment: " + environment);
        } catch (IOException e) {
            System.err.println("Error loading properties file from path: " + configFilePath);
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
		System.out.println("getProperty: 1");
        String fullKey = environment + "." + key;
        String value = properties.getProperty(fullKey);

        if (value == null) {
            System.out.println("Warning: Property " + fullKey + " not found for environment: " + environment);
            return "";
        }

        System.out.println("Fetched property: " + fullKey + " = " + value);
        return value;
    }

    public String getUserCredentials(String project, String userType) {
		System.out.println("getUserCredentials: 1");
        String usernameKey = environment + "." + project + "." + userType + ".username";
        String passwordKey = environment + "." + project + "." + userType + ".password";

        String username = properties.getProperty(usernameKey);
        String password = properties.getProperty(passwordKey);

        if (username == null || password == null) {
            throw new IllegalArgumentException("Credentials not found for userType: " + userType
                    + " in project: " + project
                    + " for environment: " + environment);
        }

        return username + ":" + password;
    }

}
