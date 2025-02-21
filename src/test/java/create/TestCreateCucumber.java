package create;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static java.lang.System.err;
import static org.testng.Assert.*;

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
        assertTrue(driver.getCurrentUrl().endsWith(url), "URL did not change correctly");
    }

    @Given("the user is on the {string} create page")
    public void userIsOnCreatePage(String url) {
        driver.get("http://localhost:5173/"+url+"/create");
    }

    @When("the user submits a valid create {string} form")
    public void userSubmitsValidCreateForm(String formType) {
        switch (formType){
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("Selenium");

                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TesterValid");

                WebElement filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102, 103");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }

    @When("the user submits a valid create {string} form with permitted empty fields")
    public void userSubmitsValidCreateFormWithValidEmptyFields(String formType) {
        switch (formType) {
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("Selenium");

                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TesterValidEmpty");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }

    @When("the user submits a create {string} form with invalid empty fields")
    public void userSubmitsInvalidCreateFormWithEmptyFields(String formType) {
        switch (formType) {
            case "actors":
                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TesterInvalidEmpty");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }

    @When("the user submits a create {string} form with invalid ids in a field")
    public void userSubmitsInvalidCreateFormWithInvalidIds(String formType) {
        switch (formType){
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("Selenium");

                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TesterInvalidIds");

                WebElement filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102, 103942940");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }

    @When("the user submits a create {string} form with a prohibited entry")
    public void userSubmitsInvalidCreateFormWithInvalidEntry(String formType) {
        switch (formType){
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("0123456789012345678901234567890123456789012345");

                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TesterInvalidEntry");

                WebElement filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }

    @When("the user clicks the cancel button")
    public void cancelButtonClicked(){
        WebElement cancelButton = driver.findElement(By.className("cancelButton"));
        cancelButton.click();
    }

    @When("the user {string} cancellation")
    public void userClicksCancelButtonConfirm(String decision){
        if (decision.equals("confirms")){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
        else if (decision.equals("cancels")){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
        }

    @Then("the created can be found on the {string} page")
    public void createdFoundOnAllPage(String url) {
        driver.get("http://localhost:5173/"+url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("individuals")));
        List<WebElement> entityList = driver.findElements(By.className("individuals"));

        boolean found = entityList.stream()
                .map(entity -> entity.findElement(By.className("name")).getText())
                .anyMatch(name -> name.equals("SELENIUM TESTERVALID"));
        assertTrue(found);
    }

    @Then("the created with valid empty fields can be found on the {string} page")
    public void createdWithValidEmptyFieldsFoundOnAllPage(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("individuals")));
        List<WebElement> entityList = driver.findElements(By.className("individuals"));

        boolean found = entityList.stream()
                .map(entity -> entity.findElement(By.className("name")).getText())
                .anyMatch(name -> name.equals("SELENIUM TESTERVALIDEMPTY"));
        assertTrue(found);
    }

    @Then("a message pops up with a warning")
    public void userReceivesFillInFieldWarning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        boolean warning = (Boolean)((JavascriptExecutor) driver)
                .executeScript("return document.querySelector('.firstNameInput').matches(':invalid');");
        assertTrue(warning);
    }

    @Then("a {string} pops up")
    public void userReceivesWarningAlert(String alertType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        switch (alertType) {
            case "success":
                assertTrue(alert.getText().endsWith("created successfully!"));
                break;

            case "id":
                assertTrue(alert.getText().endsWith("ID/s may be invalid."));
                break;

            case "name":
                assertEquals(alert.getText(), "First and last name must be between 1 and 45 characters.");
                break;

            case "cancel":
                assertEquals(alert.getText(), "Are you sure you want to cancel this create? You will lose all changes.");
                break;


            default:
                break;
        }
        alert.accept();
    }

    @Then("the {string} stays the same")
    public void checkTheUrlStaysTheSame(String url) {
        assertEquals(driver.getCurrentUrl(), "http://localhost:5173/" + url + "/create");
    }

    @Then("the user does not lose their {string} {string} information")
    public void inputtedInformationHasNotBeenLost(String formType, String errorType) {
        switch (formType){
            case "actors":
                if (Objects.equals(errorType, "invalidId")) {
                    WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                    String storedFirstName = firstNameInput.getAttribute("value");
                    assertEquals(storedFirstName, "Selenium");

                    WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                    String storedLastName = lastNameInput.getAttribute("value");
                    assertEquals(storedLastName, "TesterInvalidIds");

                    WebElement filmsInput = driver.findElement(By.className("filmsInput"));
                    String storedFilms = filmsInput.getAttribute("value");
                    assertEquals(storedFilms, "101,102,103942940");
                    break;
                }
                else if (Objects.equals(errorType, "invalidEntry")) {
                    WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                    String storedFirstName = firstNameInput.getAttribute("value");
                    assertEquals(storedFirstName, "0123456789012345678901234567890123456789012345");

                    WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                    String storedLastName = lastNameInput.getAttribute("value");
                    assertEquals(storedLastName, "TesterInvalidEntry");

                    WebElement filmsInput = driver.findElement(By.className("filmsInput"));
                    String storedFilms = filmsInput.getAttribute("value");
                    assertEquals(storedFilms, "101,102");
                    break;
                }
                else if (Objects.equals(errorType, "cancelled")) {
                    WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                    String storedFirstName = firstNameInput.getAttribute("value");
                    assertEquals(storedFirstName, "Selenium");

                    WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                    String storedLastName = lastNameInput.getAttribute("value");
                    assertEquals(storedLastName, "TesterValid");

                    WebElement filmsInput = driver.findElement(By.className("filmsInput"));
                    String storedFilms = filmsInput.getAttribute("value");
                    assertEquals(storedFilms, "101,102,103");
                    break;
                }
        }
    }

        @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
