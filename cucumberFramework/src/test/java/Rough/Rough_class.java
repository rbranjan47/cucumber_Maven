package Rough;

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

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class Rough_class 
{
	public WebDriver driver;
	public Properties proper;
	String product;
	
	@Before
	public void setup()
	{
		try
		{
			proper= new Properties();
			FileInputStream file=new FileInputStream("C:\\Users\\rbran\\git\\Selenium_Maven\\cucumberFramework\\config.properties");
			proper.load(file);
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
	
	@Given("user navigate to login page")
	public void user_navigate_to_login_page(DataTable data)
	{
		 System.out.println("user is on the homepage of amazon");
		   String ama_url = proper.getProperty("url");
		   
		   List<List<String>> obj = data.asLists();
		   String data_url = obj.get(0).get(0);
		   if (data_url.equalsIgnoreCase(ama_url))
		   {
			   System.out.println("Url is same as config value");
		   }
		   driver.get(data_url);
	}

	@When("user enters valid email and password")
	public void user_enters_valid_email_and_password(DataTable data) throws Throwable 
	{
		//hovering on the login button
		   WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
		   Actions act = new Actions(driver);
		   act.moveToElement(login_btn).build().perform();
		   Thread.sleep(3000);
		   
		   //clicking on the signin button   
		   WebElement signInbtn = driver.findElement(By.xpath("//span[contains(text(),'Sign in')]"));
		    
		   WebDriverWait wait_element = new WebDriverWait(driver, 10);
		    wait_element.until(ExpectedConditions.elementToBeClickable(signInbtn));
		    signInbtn.click();
		   
		    WebElement email = driver.findElement(By.xpath("//input[@id='ap_email']"));
		    String email_address = proper.getProperty("amazon_id");
		    
		    List<List<String>> obj = data.asLists();
		    String data_email = obj.get(0).get(1);
		    if (data_email.equalsIgnoreCase(email_address))
			   {
				   System.out.println("Username is same as config value");
			   }
		    email.sendKeys(data_email);
		    
		    WebElement email_next = driver.findElement(By.xpath("//input[@id='continue']"));
		    email_next.click();
		    
		    WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
		    String pass_word = proper.getProperty("amazon_pwd");
		    password.sendKeys(pass_word);
		    
		    //clicking on sign in button
		    driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}

	@Then("user will redirect into homepage")
	public void user_will_redirect_into_homepage()
	{
	    String title_page = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	    String title = driver.getTitle();
	    Assert.assertEquals(title_page, title);
	    
	}

	@When("user enters {String} name ")
	public void user_enters_asus_laptop_name(String product) 
	{
		System.out.println("user enters the product name");
		   WebElement search_bar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		   String product_name = proper.getProperty("product_name");
		   
		   if (product.equalsIgnoreCase(product_name))
		   {
			   System.out.println("Profuct name is same as config file");
		   }
		   Assert.assertTrue(product_name.equalsIgnoreCase(product));
		   search_bar.sendKeys(this.product);
		   
		   System.out.println("clicking on the search button to get the product");
		    //clicking on search button
		    driver.findElement(By.id("nav-search-submit-button")).click();
	}

	@When("clicks on product and clicks on Add cart")
	public void clicks_on_product_and_clicks_on_add_cart() 
	{
		System.out.println("user is on the product search result");
	    List<WebElement> products_name = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']"));
	    for (int i=0; i<products_name.size();i++)
	    {
	    System.out.println(products_name.get(i).getText());
	    }

	}

	@Then("user should able to order from the cart")
	public void user_should_able_to_order_from_the_cart()
	{
		//selecting top laptop from the lis
	   WebElement laptop = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"
	   		+ "[contains(text(),'Asus X509MA-BR270T/ Silver/ Intel Celeron N4020/ R')]"));
	   laptop.click();
	   String laptop_text = laptop.getText();
	   System.out.println(laptop_text);
	}
	
	@After
	public  void exitup()
	{
		driver.close();
		driver.quit();
	}
	
}
