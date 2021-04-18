package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class background_demo 
{
	@Given("user is on login page of the orange hrm")
	public void user_is_on_login_page_of_the_orange_hrm() 
	{
	    System.out.println("user is on the login page");
	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_and_password() 
	{
	    System.out.println("user enters valid username and password");
	}

	@When("clicks on the login button")
	public void clicks_on_the_login_button()
	{
	    System.out.println("clicking on the login button");
	}

	@Then("user is navigated into home page")
	public void user_is_navigated_into_home_page() 
	{
	    System.out.println("user is navigated to home page");
	}

	@When("user clicks on the dashboard link")
	public void user_clicks_on_the_dashboard_link() 
	{
	    System.out.println("user clicks on the dashboard");
	}

	@Then("quick launch toolbar is diaplyed")
	public void quick_launch_toolbar_is_diaplyed()
	{
	    System.out.println(" verifying the toolbar");
	}

}
