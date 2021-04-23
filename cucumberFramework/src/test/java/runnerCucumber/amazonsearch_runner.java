package runnerCucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//runner class
@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "src/test/java/featurePackage",
		glue = "Step_definitions",
		monochrome = true,
		plugin = {"pretty","html:target/HTMLReports",
				  "json:target/JSON/report.json",
				  "junit:target/JunitReports/Reports.xml"}
	
)
public class amazonsearch_runner
{

}
