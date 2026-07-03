package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= "src/test/java/Cucumber",glue = "Automation.CucumberStepDefinitions",
monochrome=true, tags = "@Regression",  plugin = {"html: target/cucumber.html"}) //helperattribute encode &generate the report
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
