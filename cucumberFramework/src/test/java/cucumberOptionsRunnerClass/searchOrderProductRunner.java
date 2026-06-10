package cucumberOptionsRunnerClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "C:\\Users\\rbran\\git\\cucumber_Maven\\cucumberFramework\\src\\test\\java\\features\\searchProductFeature.feature",
        glue = {"stepDefinitions"},
        monochrome = false,
        dryRun = false,
        plugin = {"pretty", "html:format"}
)

public class searchOrderProductRunner extends AbstractTestNGCucumberTests {

}
