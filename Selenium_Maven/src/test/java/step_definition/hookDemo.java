package step_definition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class hookDemo 
{
	WebDriver driver = null;
	
	@Before
	@SuppressWarnings("deprecation")
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	//opening browser
	@Given("user is on google search page")
	public void user_is_on_google_search_page()
	{
		driver.get("https://google.com");
	}

	@When("user enters some text in search bar")
	public void user_enters_some_text_in_search_bar() 
	{
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("thinksys");
	}

	@When("clicks on search button")
	public void clicks_on_search_button() throws InterruptedException 
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h3[contains(text(),'Gmail - Google')]")).click();
	}

	@Then("user will on search result page")
	public void user_will_on_search_result_page()
	{
	   WebElement result_thinksys = driver.findElement(By.xpath("//div[@id='center_col']/div[2]/div[2]/div/div/div[1]/div[1]"));
	   result_thinksys.click();
	}
	
	@After
	public void exitup()
	{
		driver.close();
		driver.quit();
	}
}
