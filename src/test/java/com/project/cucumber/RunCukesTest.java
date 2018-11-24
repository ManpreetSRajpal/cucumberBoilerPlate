package com.project.cucumber;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.project.cucumber.stepdefs.DriverHooks;
import cucumber.api.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport = "target/cucumber-report.json",
        jsonUsageReport = "target/cucumber-usage.json",
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        usageReport = true,
        outputFolder = "target/",
        screenShotLocation = "target/")
@CucumberOptions(
        strict = true,
        plugin = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber-report.json",
                "usage:target/cucumber-usage.json"
        },
        tags = {"~@ignore"}
)
public class RunCukesTest {
    private static final Logger LOGGER = LogManager.getLogger(RunCukesTest.class);

    @BeforeClass
    public static void setup() {
        LOGGER.info("Initiating the one time setup");
        DriverHooks.beforeSuite();
        LOGGER.info("Completed the one time setup");
    }

    @AfterClass
    public static void teardown() {
        LOGGER.info("Initiating the final cleanup");
        DriverHooks.afterSuite();
        LOGGER.info("Completed the final cleanup");
    }
}
