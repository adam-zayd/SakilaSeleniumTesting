package navbar;

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

import static org.testng.Assert.*;

public class TestNavbarCucumber {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("the user is on the page with {string}")
    public void theUserIsOnThePage(String url) {
        driver.get("http://localhost:5173" + url);
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        driver.get("http://localhost:5173/");
    }

    @When("the user tries to click on the {string} navbar link")
    public void theUserTriesToClickOnTheNavbarLink(String classExt) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        WebElement navbarLink = driver.findElement(By.className("navbar" + classExt));
        navbarLink.click();
    }

    @Then("the {string} link should be unclickable")
    public void theLinkShouldBeUnclickable(String classExt) {
        WebElement navbarLink = driver.findElement(By.className("navbar" + classExt));
        boolean isClickable = navbarLink.getTagName().equals("a");
        assertFalse(isClickable, "Link should not be clickable");
    }

    @Then("the {string} link should be in a black text")
    public void theLinkShouldBeInBlack(String classExt) {
        WebElement activeHome = driver.findElement(By.cssSelector(".active .navbar"+ classExt));
        String textColor = activeHome.getCssValue("color");
        assertEquals(textColor, "rgba(0, 0, 0, 1)", "Home link text is not black");
    }

    @Then("the URL should stay as ending in {string}")
    public void theUrlShouldStayAsEndingIn(String url) {
        assertTrue(driver.getCurrentUrl().endsWith(url), "URL did not stay the same");
    }

    @When("the user clicks on the {string} navbar link")
    public void theUserClicksOnTheNavbarLink(String classExt) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        WebElement navbarLink = driver.findElement(By.className("navbar" + classExt));
        navbarLink.click();
    }

    @Then("the URL should become one ending in {string}")
    public void theUrlShouldBecomeOneEndingIn(String url) {
        assertTrue(driver.getCurrentUrl().endsWith(url), "URL did not change correctly");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
