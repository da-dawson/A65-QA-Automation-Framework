package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.stepdefs"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/smoke-cucumber-pretty.html",
        "json:target/cucumber-reports/smoke-CucumberTestReport.json",
        "junit:target/cucumber-reports/smoke-cucumber-results.xml"
    },
    monochrome = true,
    tags = "@smoke and not @ignore"
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
