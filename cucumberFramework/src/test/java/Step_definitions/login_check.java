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

import io.cucumber.datatable.DataTable;
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
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
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
	//DataTable use to take data from the feature file
	@Given("user is on the Homepage of amazon") 
	public void user_is_on_the_homepage_of_amazon(DataTable data) throws Throwable
	{
	   System.out.println("user is on the homepage of amazon");
	   String ama_url = prop.getProperty("url");
	   
	   List<List<String>> obj = data.asLists();
	   String data_url = obj.get(0).get(0);
	   if (data_url.equalsIgnoreCase(ama_url))
	   {
		   System.out.println("Url is same as config value");
	   }
	   driver.get(data_url);
	   
	   //hovering on the login button
	   WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
	   Actions act = new Actions(driver);
	   act.moveToElement(login_btn).build().perform();
	   Thread.sleep(3000);
	   
	   //clicking on the signin button   
	   WebElement signInbtn = driver.findElement(By.xpath("//span[contains(text(),'Sign in')]"));
	    @SuppressWarnings("deprecation")
		WebDriverWait wait_element = new WebDriverWait(driver, 10);
	    wait_element.until(ExpectedConditions.elementToBeClickable(signInbtn));
	    signInbtn.click();
	   
	    WebElement email = driver.findElement(By.xpath("//input[@id='ap_email']"));
	    String email_address = prop.getProperty("amazon_id");
	    String data_email = obj.get(0).get(1);
	    if (data_email.equalsIgnoreCase(email_address))
		   {
			   System.out.println("Username is same as config value");
		   }
	    email.sendKeys(data_email);
	    
	    WebElement email_next = driver.findElement(By.xpath("//input[@id='continue']"));
	    email_next.click();
	    
	    WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
	    String pass_word = prop.getProperty("amazon_pwd");
	    password.sendKeys(pass_word);
	    
	    //clicking on sign in button
	    driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}
	

	@When("user enters product name")
	public void user_enters_product_name(DataTable datatable) 
	{
	   System.out.println("user enters the product name");
	   WebElement search_bar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	   String product_name = prop.getProperty("product_name");
	   List<List<String>> prod = datatable.asLists();
	   String data_product = prod.get(0).get(0);
	   //if conditions
	   if (data_product.equalsIgnoreCase(product_name))
	   {
		   System.out.println("Profuct name is same as config file");
	   }
	   search_bar.sendKeys(data_product);
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
	    for (int i=0; i<products_name.size();i++)
	    {
	    System.out.println(products_name.get(i).getText());
	    }
	}
	
	@After
	public void exitup() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
	}
}
