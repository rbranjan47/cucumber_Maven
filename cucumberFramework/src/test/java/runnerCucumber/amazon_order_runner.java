package runnerCucumber;
//runner class

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "src/test/java/featurePackage/amazon_order.feature",
		glue = "Step_definitions.amazon_orderTest",
		monochrome = true,
		plugin = {"pretty","html:target/HTMLReports_order",
				  "json:target/JSON/report_order.json",
				  "junit:target/JunitReports/Reports_order.xml"}
)

public class amazon_order_runner 
{

}
