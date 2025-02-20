package display;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.List;

public class TestMoviepageDisplayCucumber {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("the user is on the movies page")
    public void user_is_on_movies_page() {
        driver.get("http://localhost:5173/films");
    }

    @Then("movies display on the page")
    public void movies_on_the_page_should_match_the_movies_in_the_database() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pageTitle")));
        WebElement pageTitle = driver.findElement(By.className("pageTitle"));
        assertTrue(pageTitle.isDisplayed(), "Page title is missing");
        assertEquals(pageTitle.getText(), "ALL MOVIES");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("individuals")));
        List<WebElement> movieList = driver.findElements(By.className("individuals"));
        assertFalse(movieList.isEmpty(), "Movies did not load");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}


