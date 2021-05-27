package Step_definitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)
	public class flipkart_search 
	{
	public WebDriver driver;
	public Properties properties;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setup()
	{
		try
		{
			properties= new Properties();
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"config.properties");
			properties.load(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		 catch(IOException e)
		{
			 e.printStackTrace();
		}	
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	    @Given("^User is on flipkart page with logged in$")
	    public void user_is_on_flipkart_page_with_logged_in() throws Throwable 
	    {
	        throw new PendingException();
	    }

	    @When("^User search the product$")
	    public void user_search_the_product() throws Throwable 
	    {
	        throw new PendingException();
	    }

	    @Then("^User got multiple result for searched profuduct$")
	    public void user_got_multiple_result_for_searched_profuduct() throws Throwable 
	    {
	        throw new PendingException();
	    }

	}
