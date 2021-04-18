package step_definition;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//runner class
@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "/cucumber_Maven/src/test/resources/backgroundDemo.feature",
		glue = {"step_definition"},
		monochrome = true,
		plugin = {"pretty","html:target/HTMLReports",
				  "json:target/JSONReports/report.json",
				  "junit:target/JunitReports/report.xml"}
	
)
public class background_runner 
{

}
