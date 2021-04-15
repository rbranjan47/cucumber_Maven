package cucumber_demo;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//runner class
@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "src/test/resources/features",
		glue = {"stepdefinition"},
		monochrome = true,
		plugin = {"progress","html:target/HTML/HTMLReports",
				  "json:html:target/JSON/report.json"}
	
)

public class cucumber_runnerClass
{
	public static void main(String[] args)
	{
		
	}
}
