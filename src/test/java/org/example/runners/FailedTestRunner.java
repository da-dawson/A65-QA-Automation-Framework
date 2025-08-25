package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "@target/failed_scenarios.txt",
    glue = {"org.example.stepdefs"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/failed-cucumber-pretty.html",
        "json:target/cucumber-reports/failed-CucumberTestReport.json",
        "junit:target/cucumber-reports/failed-cucumber-results.xml"
    },
    monochrome = true
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
