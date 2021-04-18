package step_definition;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class login_check 
{
	
	WebDriver driver = null;
	@SuppressWarnings("deprecation")
	@Given("Open the chrome browser")
	public void open_the_chrome_browser()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//getting the google page
		String url = "https://www.google.com/";
		driver.get(url);
		System.out.println("User is opening the chrome browser");
	}

	@Given("enter the gmail in the Search bar and click on search button")
	public void enter_the_gmail_in_the_search_bar_and_click_on_search_button() 
	{
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("gmail");
	    System.out.println("user is searching the gmail in search bar");
	}

	@When("user enters clicks on gmail first link")
	public void user_enters_clicks_on_gmail_first_link() throws InterruptedException 
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h3[contains(text(),'Gmail - Google')]")).click();
	  System.out.println("User clicks on the gmail");
	}

	@When("clicks on sigin button")
	public void clicks_on_sigin_button() 
	{
		WebElement signIn_btn = driver.findElement(By.xpath("//a[contains(text(),'Sign in')]/../../../../div[5]/ul/li[2]/a"));
		signIn_btn.click();
	    System.out.println("user clicks on signin button after entering valid email and password");
	}

	@When("user enters valid email address and password")
	public void user_enters_valid_email_address_and_password()
	{
		//switching into another window
		String parent_window = driver.getWindowHandle();
		
		Set<String> windows = driver.getWindowHandles();
		//iterating over windows
		Iterator<String> itr = windows.iterator();
		while(itr.hasNext())
		{
			String child_window  = itr.next();
			if(!parent_window.equals(child_window))
			{
				driver.switchTo().window(child_window);
				//entering the email id
				String email_id = "brawlpower1120@gmail.com";
				String password = "Brawl1120@#";
				driver.findElement(By.id("identifierId")).sendKeys(email_id);
				WebElement next_btn = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d']"));
				next_btn.sendKeys(Keys.ENTER);
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
				
			}
		}
	   System.out.println("Entering valid email and password");
	}

	@Then("user will redirect to home page")
	public void user_will_redirect_to_home_page()
	{
		WebElement Inbox_btn = driver.findElement(By.xpath("//a[contains(text(),'Inbox')]"));
		//clicking on the inbox button
		Inbox_btn.click();
	  System.out.println("rdirecting to home page");
	}

	@Then("user is able to check its mails")
	public void user_is_able_to_check_its_mails() 
	{
		WebElement chat_list = driver.findElement(By.xpath("//div[@class='nH bkK nn']"));
		//getting text from the chat list
		String chat_text = chat_list.getText();
		System.out.print(chat_text);
		
		String required_text = "University of North Texas recently posted";
		if (chat_text.equalsIgnoreCase(required_text))
		{
			chat_list.click();
		}
	    System.out.println("User is able to check its mail");
	}
}
