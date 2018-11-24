package com.project.cucumber.stepdefs;


import com.project.cucumber.utils.TestConfiguration;
import com.project.cucumber.utils.TestDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

public class DriverHooks {

    private static TestDriver testDriver = null;
    private static final String defaultEnvironment = "dev";
    public static String platform;
    private static final Logger LOGGER = LogManager.getLogger(DriverHooks.class);

    static {
        String tempEnv = System.getProperty("ENV");
        String environment = tempEnv == null || tempEnv.isEmpty() ? defaultEnvironment : tempEnv;
        TestConfiguration.loadConfiguration(environment);
    }

    public static void beforeSuite() {
        testDriver = TestDriver.getInstance();
        testDriver.setTimeOut(TestConfiguration.getPageTimeOut());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        testDriver.clearCookies();
        testDriver.listCookies();
        if (scenario.getSourceTagNames().contains("@mobile")) {
            platform = "mobile";
            testDriver.setWindowSize(new Dimension(320, 480));
        }
        if (scenario.getSourceTagNames().contains("@desktop")) {
            platform = "desktop";
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        testDriver.takeScreenShot(scenario);
    }

    public static void afterSuite() {
        if (testDriver != null) {
            testDriver.quit();
        }
    }

}
