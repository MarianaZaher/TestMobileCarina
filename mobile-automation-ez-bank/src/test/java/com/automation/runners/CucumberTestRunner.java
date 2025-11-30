package com.automation.runners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import com.zebrunner.carina.core.IAbstractTest;
import com.automation.api.RestAssuredSSLConfig;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automation.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        dryRun = false
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests implements IAbstractTest {

    @BeforeSuite
    public static void setup() {
        // Configure RestAssured to handle SSL certificate issues
        RestAssuredSSLConfig.configure();
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
