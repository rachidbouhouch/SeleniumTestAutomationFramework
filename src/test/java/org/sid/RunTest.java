package org.sid;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources/features/",
        glue = {"org.sid.stepsdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-pretty.html",
                "json:target/CucumberTestReport.json"}

)

public class RunTest {
}
