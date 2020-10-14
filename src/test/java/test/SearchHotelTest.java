package test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = {"classpath:features"},
        glue = "steps",
        plugin = { "pretty",
                "html:target/cucumber-html-reports.html",
                "json:target/cucumber-json-reports.json"}
)
public class SearchHotelTest extends AbstractTestNGCucumberTests {

}
