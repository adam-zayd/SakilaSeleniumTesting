package update;

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
public class TestUpdateCucumber {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("the user is on the specific {string} page")
    public void userIsOnInitialPage(String url) {
        driver.get("http://localhost:5173/"+url+"/16");
    }

    @When("the user clicks the UPDATE link")
    public void userClicksCreate(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        List<WebElement> editButtons = driver.findElements(By.className("editButton"));
        for (WebElement button : editButtons) {
            if (button.getText().equalsIgnoreCase("EDIT ALL")) {
                button.click();
                break;
            }
        }
    }

    @Then("the page redirects to a url ending in {string}")
    public void pageRedirectsToUpdatePage(String url){
        assertTrue(driver.getCurrentUrl().endsWith(url), "URL didn't change correctly");
    }

    @Then("the {string} fields pre-load with the existing data from the database")
    public void fieldsPreLoadWithExistingData(String formType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nameInput")));

        switch(formType){
            case "actors":
                WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("lastNameInput")));
                WebElement filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                String firstNameValue = nameInput.getAttribute("value");
                String lastNameValue = lastNameInput.getAttribute("value");
                String filmIdsValue = filmIdsInput.getAttribute("value");

                assertEquals(firstNameValue, "FRED");
                assertEquals(lastNameValue,"COSTNER");
                assertEquals(filmIdsValue,"80,87,101,121,155,177,218,221,267,269,271,280,287,345,438,453,455,456,503,548,582,583,717,758,779,886,967");
                break;

            case "films":
                WebElement releaseYearInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("releaseYearInput")));
                WebElement castInput = driver.findElement(By.className("castInput"));
                WebElement lengthInput = driver.findElement(By.className("lengthInput"));
                WebElement streamsInput = driver.findElement(By.className("streamsInput"));
                WebElement descriptionInput = driver.findElement(By.className("descriptionInput"));
                String titleValue = nameInput.getAttribute("value");
                String releaseYearValue = releaseYearInput.getAttribute("value");
                String castValue = castInput.getAttribute("value");
                String lengthValue = lengthInput.getAttribute("value");
                String streamsValue = streamsInput.getAttribute("value");
                String descriptionValue = descriptionInput.getAttribute("value");

                assertEquals(titleValue,"ALLEY EVOLUTION");
                assertEquals(descriptionValue,"A Fast-Paced Drama of a Robot And a Composer who must Battle a Astronaut in New Orleans");
                assertEquals(releaseYearValue,"2010");
                assertEquals(castValue,"12,57,146,191,192");
                assertEquals(lengthValue,"180");
                assertEquals(streamsValue,"1");
                break;

            case "streams":
                WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                WebElement costInput = driver.findElement(By.className("costInput"));
                filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                String streamValue = nameInput.getAttribute("value");
                String websiteValue = websiteInput.getAttribute("value");
                String costValue = costInput.getAttribute("value");
                filmIdsValue = filmIdsInput.getAttribute("value");

                assertEquals(streamValue,"HULU");
                assertEquals(filmIdsValue,"11,55,233,244,322,435,757,784");
                assertEquals(costValue,"10.99");
                assertEquals(websiteValue,"https://www.hulu.com/");
                break;

            case "categories":
                filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                String categoryValue = nameInput.getAttribute("value");
                filmIdsValue = filmIdsInput.getAttribute("value");

                assertEquals(categoryValue,"Travel");
                assertEquals(filmIdsValue,"41,57,75,84,87,88,103,123,125,167,169,181,224,233,257,286,288,294,299,307,339,342,347,386,405,434,451,472,496,513,518,526,543,601,609,642,645,648,656,783,784,785,787,811,826,848,868,872,878,894,903,914,931,977,981,988,989");
                break;

            default:
                break;
        }

    }


    @Given("the user is on the {string} update page")
    public void userIsOnCreatePage(String url) {
        driver.get("http://localhost:5173/"+url+"/update");
    }

    @When("the user submits a valid update form")
    public void userSubmitsValidCreateForm() {
                WebElement nameInput = driver.findElement(By.className("nameInput"));
                nameInput.click();
                for (int i=0;i<15;i++){
                    nameInput.sendKeys(Keys.ARROW_RIGHT);
                }
                nameInput.sendKeys(" SELENIUM");

        WebElement saveButton = driver.findElement(By.className("saveButton"));
        saveButton.click();
    }
//
    @When("the user submits a valid update {string} form with permitted empty fields")
    public void userSubmitsValidCreateFormWithValidEmptyFields(String formType) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        switch (formType) {
            case "actors":
                WebElement filmsInput = driver.findElement(By.className("filmIdsInput"));
                filmsInput.click();
                filmsInput.sendKeys(selectAll);
                filmsInput.sendKeys(Keys.BACK_SPACE);

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "films":
                WebElement categoriesInput = driver.findElement(By.className("categoriesInput"));
                categoriesInput.click();
                categoriesInput.sendKeys(selectAll);
                categoriesInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                filmsInput = driver.findElement(By.className("filmIdsInput"));
                filmsInput.click();
                filmsInput.sendKeys(selectAll);
                filmsInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                filmsInput = driver.findElement(By.className("filmIdsInput"));
                filmsInput.click();
                filmsInput.sendKeys(selectAll);
                filmsInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }
//
    @When("the user submits an update {string} form with invalid empty fields")
    public void userSubmitsInvalidCreateFormWithEmptyFields(String formType) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        switch (formType) {
            case "actors":
                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys(selectAll);
                lastNameInput.sendKeys(Keys.BACK_SPACE);

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "films":
                WebElement titleInput = driver.findElement(By.className("nameInput"));
                titleInput.click();
                titleInput.sendKeys(selectAll);
                titleInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("nameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys(selectAll);
                streamNameInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                WebElement categoryNameInput = driver.findElement(By.className("nameInput"));
                categoryNameInput.click();
                categoryNameInput.sendKeys(selectAll);
                categoryNameInput.sendKeys(Keys.BACK_SPACE);

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }
//
    @When("the user submits an update {string} form with invalid ids in a field")
    public void userSubmitsInvalidCreateFormWithInvalidIds(String formType) {
        switch (formType){
            case "actors":
                WebElement filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("103942940");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "films":
                WebElement categoriesInput = driver.findElement(By.className("categoriesInput"));
                categoriesInput.click();
                categoriesInput.sendKeys("167657651");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("18797803");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                filmIdsInput = driver.findElement(By.className("filmIdsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("18797803");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }
//
    @When("the user submits an update {string} form with a prohibited entry")
    public void userSubmitsInvalidCreateFormWithInvalidEntry(String formType) {
        switch (formType){
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("nameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("0123456789012345678901234567890123456789012345");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "films":
                WebElement releaseYearInput = driver.findElement(By.className("releaseYearInput"));
                releaseYearInput.click();
                releaseYearInput.sendKeys("01/01/2000");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement costInput = driver.findElement(By.className("costInput"));
                costInput.click();
                costInput.sendKeys("4.2476576");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            default:
                break;
        }
    }
//
    @When("the user clicks the cancel update button")
    public void cancelButtonClicked(){
        WebElement cancelButton = driver.findElement(By.className("cancelButton"));
        cancelButton.click();
    }
//
    @When("the user confirms cancellation")
    public void userClicksCancelButtonConfirm(){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();

    }
//
    @Then("the change can be found on the {string} page")
    public void createdFoundOnAllPage(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("specificPageTitle")));
        WebElement name = driver.findElement(By.className("specificPageTitle"));

        switch (url){
            case "actors":
                assertEquals(name.getText(),"FRED SELENIUM");
                break;
            case "films":
                assertEquals(name.getText(),"ALLEY EVOLUTION SELENIUM");
                break;
            case "streams":
                assertEquals(name.getText(),"HULU SELENIUM");
                break;
            case "categories":
                assertEquals(name.getText(),"TRAVEL SELENIUM");
                break;
        }

    }
//
    @Then("the change with valid empty fields can be found on the specific page")
    public void createdWithValidEmptyFieldsFoundOnAllPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("buttons")));
        WebElement name = driver.findElement(By.className("buttons"));
        assertEquals(name.getText(),"Unknown");

    }
//
    @Then("an update message pops up with a warning")
    public void userReceivesFillInFieldWarning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }
//
    @Then("an update {string} pops up")
    public void userReceivesWarningAlert(String alertType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        switch (alertType) {
            case "success":
                assertTrue(alert.getText().endsWith("updated successfully!"));
                break;

            case "id":
                assertTrue(alert.getText().contains("ID/s may be invalid"));
                break;

            case "firstnamelong":
                assertEquals(alert.getText(), "First and last name must be between 1 and 45 characters.");
                break;

            case "invalidyear":
                assertEquals(alert.getText(), "Release Year must be a four-digit number.");
                break;

            case "invalidcost":
                assertEquals(alert.getText(), "Cost can only have up to 3 digits before and up to 2 digits after the decimal.");
                break;

            case "cancel":
                assertEquals(alert.getText(), "Are you sure you want to cancel this update? You will lose all changes.");
                break;


            default:
                break;
        }
        alert.accept();
    }
//
    @Then("the url for the {string} stays the same")
    public void checkTheUrlStaysTheSame(String url) {
        assertEquals(driver.getCurrentUrl(), "http://localhost:5173/" + url + "/update");
    }
//
    @Then("the user does not lose their updated {string} information")
    public void inputtedInformationHasNotBeenLost(String formType) {
        switch (formType) {
            case "actors":
                WebElement filmsInput = driver.findElement(By.className("filmIdsInput"));
                String storedFilms = filmsInput.getAttribute("value");
                assertEquals(storedFilms, "103942940");
                break;


            case "films":
                WebElement categoriesInput = driver.findElement(By.className("categoriesInput"));
                String storedCats = categoriesInput.getAttribute("value");
                assertEquals(storedCats, "167657651");
                break;

            case "streams":
                filmsInput = driver.findElement(By.className("filmIdsInput"));
                storedFilms = filmsInput.getAttribute("value");
                assertEquals(storedFilms, "18797803");
                break;

            case "categories":

                filmsInput = driver.findElement(By.className("filmIdsInput"));
                storedFilms = filmsInput.getAttribute("value");
                assertEquals(storedFilms, "18797803");
                break;
        }
    }
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
