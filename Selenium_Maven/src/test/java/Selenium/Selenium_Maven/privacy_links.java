package Selenium.Selenium_Maven;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class privacy_links {
	public static JavascriptExecutor js;

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://freecrm.com/");

		@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement login = driver.findElement(
				By.xpath("//a[@class='btn btn-primary btn-xs-2 btn-shadow btn-rect btn-icon btn-icon-left']"));
		driver.getTitle();
		// wait.until(ExpectedConditions.elementToBeClickable(login));
		login.click();
		driver.getTitle();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("rbranjan47@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Rabi8936@#");

		driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']")).click();
		Thread.sleep(4000);

		// driver.switchTo().frame("mainpanel");

		// getting all links in the webpage
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));
		// all links
		System.out.println("All links present on the Page is " + links.size());

		List<WebElement> activelinks = new ArrayList<WebElement>();
		// adding on the Active links
		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href") != null
					&& !links.get(i).getAttribute("href").contains("javascript")) {
				activelinks.addAll(links);
			}
		}
		System.out.println("active links are " + activelinks.size());

		// checking the Links is correct by HTTPS API
		try {
			for (int j = 0; j < activelinks.size(); j++) {
				// we can verify by making connection with the help of HTTPConnection class
				HttpURLConnection connection = (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href"))
						.openConnection();
				// connecting
				connection.connect();
				// checking the response
				String response = connection.getResponseMessage();
				long responsecode = connection.getResponseCode();
				// disconnect
				connection.disconnect();

				System.out.println("Response is " + activelinks.get(j).getAttribute("href") + response);

				if (responsecode >= 400) {
					System.out.println("Broken links are " + activelinks);
				} else {
					System.out.println("Valid links " + activelinks);
				}

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//i[@class='settings icon']/../../div/i")).click();
		WebElement logout = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
		// wait.until(ExpectedConditions.elementToBeClickable(logout));
		logout.click();
		driver.quit();

	}
}
