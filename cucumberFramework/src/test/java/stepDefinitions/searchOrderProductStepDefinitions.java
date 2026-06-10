package stepDefinitions;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class searchOrderProductStepDefinitions {
    // Url: https://rahulshettyacademy.com/seleniumPractise/#/

    public WebDriver driver;
    public WebDriverWait waitForEle;
    public String reqOrder = "";

    @Given("User is on login page")
    public void user_is_on_login_page() {

        // Setup browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // window maximize
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("User search the given product")
    public void user_search_the_given_product() {
        WebElement searchField = driver.findElement(By.xpath("//input[@type='search']"));

        searchField.sendKeys("Tom");
        WebElement searchClick = driver.findElement(By.xpath("//button[@type='submit']"));
        searchClick.click();
    }

    @Then("User get all relevant products based on search")
    public void user_get_all_relevant_products_based_on_search() throws InterruptedException {

        Thread.sleep(3000);

        List<WebElement> results = driver.findElements(By.cssSelector("h4.product-name"));
        // Wait for all elements
        ExpectedConditions.visibilityOfAllElements();
        for (WebElement elementReq : results) {
            if (elementReq.getText().contains(("Tomato"))) {
                String[] elementReqSplited = elementReq.getText().split("-");
                reqOrder = elementReqSplited[0].trim();
            }
        }
        System.out.println(reqOrder);

    }

    @Then("User orders desired product {string}")
    public void user_orders_desired_product(String tomProduct) throws InterruptedException {
        // a[contains(text(), 'Top Deals')]

        WebElement topDeals = driver.findElement(By.linkText("Top Deals"));
        waitForEle = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForEle.until(ExpectedConditions.elementToBeClickable(topDeals));
        topDeals.click();

        Thread.sleep(5000);

        Set<String> windoHandles = driver.getWindowHandles();

        Iterator<String> itrs = windoHandles.iterator();

        String parentWindow = itrs.next();
        String childWindow = itrs.next();
        driver.switchTo().window(childWindow);

        // get product name
        WebElement searchField2 = driver.findElement(By.xpath("//input[@type='search']"));
        // passsing argument, coming from Gherkin
        // waitForEle.until(ExpectedConditions.visibilityOf(searchField2));
        searchField2.click();
        searchField2.sendKeys(tomProduct);

        Thread.sleep(2000);
        WebElement searchResultEle = driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'Tomato')]"));
        String searchResult = searchResultEle.getText().stripTrailing();

        // assetions
        Assert.assertEquals(reqOrder, searchResult);

        driver.switchTo().window(parentWindow);
    }

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}
