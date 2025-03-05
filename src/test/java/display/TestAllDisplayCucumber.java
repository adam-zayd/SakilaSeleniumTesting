package display;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.List;

public class TestAllDisplayCucumber {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("the user is on {string} page")
    public void user_is_on_page(String page) {
        driver.get("http://localhost:5173/"+page+"/");
    }

    @When("the user selects an individual")
    public void select() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        WebElement parentElement = driver.findElement(By.cssSelector(".individuals"));
        Actions actions = new Actions(driver);
        actions.moveToElement(parentElement).perform();
        WebElement checkbox = parentElement.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
    }

    @Then("the individual is selected")
    public void check_selected() {
        List<WebElement> selectedCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']:checked"));
        assertFalse(selectedCheckboxes.isEmpty(), "A checkbox should be selected");
    }

    @When("the user clicks reset")
    public void click_reset_selected() {
        driver.findElement(By.className("resetButton")).click();
        driver.switchTo().alert().accept();
    }

    @When("the user clicks delete")
    public void click_delete() {
        driver.findElement(By.className("deleteButton")).click();
    }

    @Then("the selecteds are deleted")
    public void check_selected_are_deleted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert message = driver.switchTo().alert();
        assertTrue(message.getText().endsWith("have been deleted."));
    }

    @Then("all selected are deselected")
    public void check_deselected() {
        List<WebElement> selectedCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']:checked"));
        assertTrue(selectedCheckboxes.isEmpty(), "checkbox should be deselected");
    }

    @Then("a page title is displayed")
    public void pageTitle_should_display() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pageTitle")));
        WebElement pageTitle = driver.findElement(By.className("pageTitle"));
        assertTrue(pageTitle.isDisplayed(), "Page title is missing");
    }

    @Then("all entities are displayed on the page")
    public void entities_on_the_page_should_display() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("individuals")));
        List<WebElement> movieList = driver.findElements(By.className("individuals"));
        assertFalse(movieList.isEmpty(), "Entities did not load");
    }

    @Then("a create and search button are displayed")
    public void create_and_search_should_display() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("createButton")));
        WebElement buttons = driver.findElement(By.className("createButton"));
        assertTrue(buttons.isDisplayed(), "Search and Create are missing");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}


