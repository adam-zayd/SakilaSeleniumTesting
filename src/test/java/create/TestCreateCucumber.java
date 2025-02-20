package create;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.testng.Assert.assertTrue;

public class TestCreateCucumber {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("the user is on the {string} page")
    public void userIsOnInitialPage(String url) {
        driver.get("http://localhost:5173/"+url);
    }

    @When("the user clicks the CREATE {string} link")
    public void userClicksCreate(String entity){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        WebElement createLink = driver.findElement(By.className("create"+entity+"Link"));
        createLink.click();
    }

    @Then("the page redirects to one with a url ending in {string}")
    public void pageRedirectsToEndingIn(String url){
        assertTrue(driver.getCurrentUrl().endsWith(url+"/create"), "URL did not change correctly");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
