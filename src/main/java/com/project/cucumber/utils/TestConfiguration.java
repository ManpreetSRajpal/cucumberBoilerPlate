package com.project.cucumber.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TestConfiguration {
    private static TestConfiguration testConfiguration = null;
    private static TestConfig testConfig = null;
    private static final String defaultBrowser = "chrome";
    private static String browser;
    private static final String configName = "config/config.yml";
    private static final Logger LOGGER = LogManager.getLogger(TestConfiguration.class);

    private TestConfiguration(String environment) {
        List<TestConfig> lsOfTestConfig = new YAMLLoader().getAllConfig(configName);
        lsOfTestConfig
                .stream()
                .filter(config -> config.getName().equals(environment))
                .forEach(config -> testConfig = config);
    }

    public static TestConfiguration loadConfiguration(String env) {
        if (testConfiguration == null) {

            LOGGER.info("Default Browser set for test execution: " + defaultBrowser);
            String tempBrowser = System.getProperty("BROWSER");
            browser = tempBrowser == null || tempBrowser.isEmpty() ? defaultBrowser : tempBrowser;
            LOGGER.info("Executing the test via the browser: " + browser);
            testConfiguration = new TestConfiguration(env);
            LOGGER.info("Loading the " + env + " environment details from the config file.");
        }
        return testConfiguration;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getLoginURL() {
        return testConfig.getLoginUrl();
    }

    public static int getPageTimeOut() {
        return testConfig.getPageTimeOut();
    }

    public static String getEnvironment() {
        return testConfig.getName();
    }

//    public static String getSeleniumHubURL() {
//        return testConfig.getSeleniumHubURL();
//    }
}

