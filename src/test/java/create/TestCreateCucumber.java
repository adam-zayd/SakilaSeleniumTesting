package create;

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
import java.util.Objects;
import static org.testng.Assert.*;

@SuppressWarnings("ALL")
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

    @When("the user clicks the CREATE link")
    public void userClicksCreate(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        WebElement createLink = driver.findElement(By.className("createLink"));
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

    @Given("the user enters data into the create {string} form")
    public void userEntersDataInCreateForm(String formType) {
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
                break;

            default:
                break;
        }
    }
    @When("the user submits a valid create {string} form")
    public void userSubmitsValidCreateForm(String formType) {
        switch (formType){
            case "actors":
                WebElement firstNameInput = driver.findElement(By.className("firstNameInput"));
                firstNameInput.click();
                firstNameInput.sendKeys("SELENIUM");

                WebElement lastNameInput = driver.findElement(By.className("lastNameInput"));
                lastNameInput.click();
                lastNameInput.sendKeys("TESTERVALID");

                WebElement filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102, 103");

                WebElement saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "films":
                WebElement titleInput = driver.findElement(By.className("titleInput"));
                titleInput.click();
                titleInput.sendKeys("SELENIUM");

                WebElement descriptionInput = driver.findElement(By.className("descriptionInput"));
                descriptionInput.click();
                descriptionInput.sendKeys("This is a selenium test.");

                WebElement releaseYearInput = driver.findElement(By.className("releaseYearInput"));
                releaseYearInput.click();
                releaseYearInput.sendKeys("2000");

                WebElement lengthInput = driver.findElement(By.className("lengthInput"));
                lengthInput.click();
                lengthInput.sendKeys(Keys.BACK_SPACE);
                lengthInput.sendKeys("120");

                WebElement languageInput = driver.findElement(By.className("languageInput"));
                languageInput.click();
                languageInput.sendKeys(Keys.BACK_SPACE);
                languageInput.sendKeys("1");

                WebElement castInput = driver.findElement(By.className("castInput"));
                castInput.click();
                castInput.sendKeys("101,102,103");

                WebElement categoriesInput = driver.findElement(By.className("categoriesInput"));
                categoriesInput.click();
                categoriesInput.sendKeys("11");

                WebElement streamsInput = driver.findElement(By.className("streamsInput"));
                streamsInput.click();
                streamsInput.sendKeys("17");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys("SELENIUM");

                WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                websiteInput.click();
                websiteInput.sendKeys("www.thisIsATest.com");

                WebElement costInput = driver.findElement(By.className("costInput"));
                costInput.click();
                costInput.sendKeys("4.24");

                filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102, 103");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                WebElement categoryNameInput = driver.findElement(By.className("categoryNameInput"));
                categoryNameInput.click();
                categoryNameInput.sendKeys("SELENIUM");

                filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101, 102, 103");

                saveButton = driver.findElement(By.className("saveButton"));
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

            case "films":
                WebElement titleInput = driver.findElement(By.className("titleInput"));
                titleInput.click();
                titleInput.sendKeys("SELENIUM VALIDEMPTY");

                WebElement languageInput = driver.findElement(By.className("languageInput"));
                languageInput.click();
                languageInput.sendKeys(Keys.BACK_SPACE);
                languageInput.sendKeys("1");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys("SELENIUM VALIDEMPTY");

                WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                websiteInput.click();
                websiteInput.sendKeys("www.thisIsATest.com");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                WebElement categoryNameInput = driver.findElement(By.className("categoryNameInput"));
                categoryNameInput.click();
                categoryNameInput.sendKeys("SELENIUM VALIDEMPTY");

                saveButton = driver.findElement(By.className("saveButton"));
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

            case "films":
                WebElement titleInput = driver.findElement(By.className("titleInput"));
                titleInput.click();
                titleInput.sendKeys("SELENIUM VALIDEMPTY");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys("SELENIUM VALIDEMPTY");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                saveButton = driver.findElement(By.className("saveButton"));
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

            case "films":
                WebElement titleInput = driver.findElement(By.className("titleInput"));
                titleInput.click();
                titleInput.sendKeys("SELENIUM");

                WebElement descriptionInput = driver.findElement(By.className("descriptionInput"));
                descriptionInput.click();
                descriptionInput.sendKeys("This is a selenium test.");

                WebElement releaseYearInput = driver.findElement(By.className("releaseYearInput"));
                releaseYearInput.click();
                releaseYearInput.sendKeys("2000");

                WebElement lengthInput = driver.findElement(By.className("lengthInput"));
                lengthInput.click();
                lengthInput.sendKeys(Keys.BACK_SPACE);
                lengthInput.sendKeys("120");

                WebElement languageInput = driver.findElement(By.className("languageInput"));
                languageInput.click();
                languageInput.sendKeys(Keys.BACK_SPACE);
                languageInput.sendKeys("1");

                WebElement castInput = driver.findElement(By.className("castInput"));
                castInput.click();
                castInput.sendKeys("101,102,10987987973");

                WebElement categoriesInput = driver.findElement(By.className("categoriesInput"));
                categoriesInput.click();
                categoriesInput.sendKeys("11");

                WebElement streamsInput = driver.findElement(By.className("streamsInput"));
                streamsInput.click();
                streamsInput.sendKeys("17");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys("SELENIUM");

                WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                websiteInput.click();
                websiteInput.sendKeys("www.thisIsATest.com");

                WebElement costInput = driver.findElement(By.className("costInput"));
                costInput.click();
                costInput.sendKeys("4.24");

                filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101,18797802,103");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "categories":
                WebElement categoryNameInput = driver.findElement(By.className("categoryNameInput"));
                categoryNameInput.click();
                categoryNameInput.sendKeys("SELENIUM");

                filmIdsInput = driver.findElement(By.className("filmsInput"));
                filmIdsInput.click();
                filmIdsInput.sendKeys("101,1686686802,103");

                saveButton = driver.findElement(By.className("saveButton"));
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

            case "films":
                WebElement titleInput = driver.findElement(By.className("titleInput"));
                titleInput.click();
                titleInput.sendKeys("SELENIUM INVALID");

                WebElement releaseYearInput = driver.findElement(By.className("releaseYearInput"));
                releaseYearInput.click();
                releaseYearInput.sendKeys("01/01/2000");

                WebElement languageInput = driver.findElement(By.className("languageInput"));
                languageInput.click();
                languageInput.sendKeys(Keys.BACK_SPACE);
                languageInput.sendKeys("1");

                saveButton = driver.findElement(By.className("saveButton"));
                saveButton.click();
                break;

            case "streams":
                WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                streamNameInput.click();
                streamNameInput.sendKeys("SELENIUM");

                WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                websiteInput.click();
                websiteInput.sendKeys("www.thisIsATest.com");

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

        if (url.equals("actors")) {
            boolean found = entityList.stream()
                    .map(entity -> entity.findElement(By.className("name")).getText())
                    .anyMatch(name -> name.equals("SELENIUM TESTERVALID"));
            assertTrue(found);
        }
        else {
            boolean found = entityList.stream()
                    .map(entity -> entity.findElement(By.className("name")).getText())
                    .anyMatch(title -> title.equals("SELENIUM"));
            assertTrue(found);
        }

    }

    @Then("the created with valid empty fields can be found on the {string} page")
    public void createdWithValidEmptyFieldsFoundOnAllPage(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("individuals")));
        List<WebElement> entityList = driver.findElements(By.className("individuals"));

        if (url.equals("actors")) {
            boolean found = entityList.stream()
                    .map(entity -> entity.findElement(By.className("name")).getText())
                    .anyMatch(name -> name.equals("SELENIUM TESTERVALIDEMPTY"));
            assertTrue(found);
        }
        else {
            boolean found = entityList.stream()
                    .map(entity -> entity.findElement(By.className("name")).getText())
                    .anyMatch(title -> title.equals("SELENIUM VALIDEMPTY"));
            assertTrue(found);
        }
    }

    @Then("a message pops up with a warning")
    public void userReceivesFillInFieldWarning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
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
                assertTrue(alert.getText().contains("ID/s may be invalid."));
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

                    case "films":
                        if (Objects.equals(errorType, "invalidId")) {
                            WebElement titleInput = driver.findElement(By.className("titleInput"));
                            String storedTitle = titleInput.getAttribute("value");
                            assertEquals(storedTitle, "SELENIUM");

                            WebElement castInput = driver.findElement(By.className("castInput"));
                            String storedCast = castInput.getAttribute("value");
                            assertEquals(storedCast, "101,102,10987987973");
                            break;
                        }
                        else if (Objects.equals(errorType, "invalidEntry")) {
                            WebElement titleInput = driver.findElement(By.className("titleInput"));
                            String storedTitle = titleInput.getAttribute("value");
                            assertEquals(storedTitle, "SELENIUM INVALID");

                            WebElement languageInput = driver.findElement(By.className("languageInput"));
                            String storedLanguage = languageInput.getAttribute("value");
                            assertEquals(storedLanguage, "1");

                            WebElement releaseYearInput = driver.findElement(By.className("releaseYearInput"));
                            String storedReleaseYear = releaseYearInput.getAttribute("value");
                            assertEquals(storedReleaseYear, "01/01/2000");
                            break;
                        }

                    case "streams":
                        if (Objects.equals(errorType, "invalidId")) {
                            WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                            String storedStreamName = streamNameInput.getAttribute("value");
                            assertEquals(storedStreamName, "SELENIUM");

                            WebElement filmsInput = driver.findElement(By.className("filmsInput"));
                            String storedFilms = filmsInput.getAttribute("value");
                            assertEquals(storedFilms, "101,18797802,103");
                            break;
                        }
                        else if (Objects.equals(errorType, "invalidEntry")) {
                            WebElement streamNameInput = driver.findElement(By.className("streamNameInput"));
                            String storedStreamName = streamNameInput.getAttribute("value");
                            assertEquals(storedStreamName, "SELENIUM");

                            WebElement websiteInput = driver.findElement(By.className("websiteInput"));
                            String storedWebsite = websiteInput.getAttribute("value");
                            assertEquals(storedWebsite, "www.thisIsATest.com");

                            WebElement costInput = driver.findElement(By.className("costInput"));
                            String storedCost = costInput.getAttribute("value");
                            assertEquals(storedCost, "4.2476576");
                        }

                    case "categories":
                        if (Objects.equals(errorType, "invalidId")) {
                            WebElement categoryNameInput = driver.findElement(By.className("categoryNameInput"));
                            String storedCategoryName = categoryNameInput.getAttribute("value");
                            assertEquals(storedCategoryName, "SELENIUM");

                            WebElement filmsInput = driver.findElement(By.className("filmsInput"));
                            String storedFilms = filmsInput.getAttribute("value");
                            assertEquals(storedFilms, "101,1686686802,103");
                            break;
                        }

                    default:
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
