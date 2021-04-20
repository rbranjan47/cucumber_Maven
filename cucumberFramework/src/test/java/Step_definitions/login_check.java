package Step_definitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class login_check 
{
	public WebDriver driver;
	public Properties prop;
	@SuppressWarnings("deprecation")
	@Before
	public void setup()
	{
		try
		{
			prop= new Properties();
			FileInputStream file=new FileInputStream("C:\\Users\\rbran\\eclipse-workspace"
					+ "\\cucumberFramework\\config.properties");
			prop.load(file);
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
	
	@Given("user is on the Homepage of amazon") 
	public void user_is_on_the_homepage_of_amazon() throws InterruptedException
	{
	   System.out.println("user is on the homepage of amazon");
	   String ama_url = prop.getProperty("url");
	   driver.get(ama_url);
	   
	   //hovering on the login button
	   WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
	   Actions act = new Actions(driver);
	   act.moveToElement(login_btn).build().perform();
	   Thread.sleep(3000);
	   
	   //clicking on the signin button   
	   WebElement signInbtn = driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']"
	   		+ "//span[@class='nav-action-inner'][normalize-space()='Sign in']"));
	    @SuppressWarnings("deprecation")
		WebDriverWait wait_element = new WebDriverWait(driver, 10);
	    wait_element.until(ExpectedConditions.elementToBeClickable(signInbtn));
	   
	    WebElement email = driver.findElement(By.id("ap_email"));
	    WebElement email_next = driver.findElement(By.id("continue"));
	    WebElement password = driver.findElement(By.id("ap_password"));
	    String email_address = prop.getProperty("amazon_id");
	    String pass_word = prop.getProperty("amazon_pwd");
	    email.sendKeys(email_address);
	    email_next.click();
	    password.sendKeys(pass_word);
	    //clicking on sign in button
	    driver.findElement(By.xpath("signInSubmit")).click();
	}
	

	@When("user enters product name")
	public void user_enters_product_name() 
	{
	   System.out.println("user enters the product name");
	   WebElement search_bar = driver.findElement(By.id("twotabsearchtextbox"));
	   String product_name = prop.getProperty("product_name");
	   search_bar.sendKeys(product_name);
	}

	@When("clicks on the search button to get the product")
	public void clicks_on_the_search_button_to_get_the_product()
	{
	    System.out.println("clicks on the search button to get the product");
	    //clicking on search button
	    driver.findElement(By.id("nav-search-submit-button")).click();
	}

	@Then("user is on product search result")
	public void user_is_on_product_search_result() 
	{
	    System.out.println("user is on the product search result");
	    List<WebElement> products_name = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']"));
	    System.out.println(products_name);
	}
	
	@After
	public void exitup()
	{
		driver.close();
	}
}
