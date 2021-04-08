package cucumber_demo;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//runner class
@RunWith(Cucumber.class)
@CucumberOptions
(
features = "src/test/cucumberFeature", glue={"src/main/stepDefinition"}
)
public class cucumber_runnerClass
{
	@Given("User want to login in the gmail")
	public void user_want_to_login_in_the_gmail() {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Given("with valid username and password")
	public void with_valid_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("User login with valid email address and password")
	public void user_login_with_valid_email_address_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("user should able to login and get status pass")
	public void user_should_able_to_login_and_get_status_pass() {
	    // Write code here that turns the phrase above into concrete actions
	   
	}
}
