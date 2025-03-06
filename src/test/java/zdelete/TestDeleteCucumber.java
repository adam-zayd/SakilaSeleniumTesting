package zdelete;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.testng.Assert.*;

@SuppressWarnings("ALL")
public class TestDeleteCucumber {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Given("the user is on a specific page for {string}")
    public void the_user_is_on_a_specific_page(String url) {
        driver.get("http://localhost:5173/"+url); // Update with actual URL if needed
    }

    @When("the user clicks on the delete button")
    public void the_user_clicks_on_the_delete_button() {
        WebElement deleteButton = driver.findElement(By.className("cancelButton"));
        deleteButton.click();
    }

    @Then("an alert pops up for confirmation")
    public void an_alert_pops_up_for_confirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("Are you sure you want to delete"));
    }

    @When("the user accepts the delete confirmation")
    public void the_user_accepts_the_delete_confirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }
//
    @Then("the URL should end in {string}")
    public void the_url_should_become_one_ending_in(String url) {
        assertTrue(driver.getCurrentUrl().endsWith(url));
    }

    @Then("the {string} should not display")
    public void the_entity_should_not_display(String pageType) {
        List<WebElement> entityList = driver.findElements(By.className("individuals"));
        switch (pageType){
            case "actors":
                boolean found = entityList.stream()
                        .map(entity -> entity.findElement(By.className("name")).getText())
                        .anyMatch(name -> name.contains("COSTNER"));
                assertFalse(found);
                break;

            case "films":
                found = entityList.stream()
                        .map(entity -> entity.findElement(By.className("name")).getText())
                        .anyMatch(name -> name.contains("ALLEY EVOLUTION"));
                assertFalse(found);
                break;

            case "streams":
                found = entityList.stream()
                        .map(entity -> entity.findElement(By.className("name")).getText())
                        .anyMatch(name -> name.contains("HULU"));
                assertFalse(found);
                break;

            case "categories":
                found = entityList.stream()
                        .map(entity -> entity.findElement(By.className("name")).getText())
                        .anyMatch(name -> name.contains("TRAVEL"));
                assertFalse(found);
                break;

            default:
                break;
        }
    }
//
    @When("the user cancels the delete confirmation")
    public void the_user_cancels_the_delete_confirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    @Then("the {string} should still be visible")
    public void the_entity_should_still_be_visible(String entity) {
        boolean isEntityVisible = driver.findElements(By.className("specificPageTitle")).size() > 0;
        assertTrue(isEntityVisible);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
