package navbar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.*;

public class TestFromHomepage {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeMethod
    public void beforeEach() {
        driver.get("http://localhost:5173/");
    }

    @Test
    public void testHomeLink(){
        final var navbarHome = driver.findElement(By.className("navbarHome"));
        final var activeHome = driver.findElement(By.cssSelector(".active .navbarHome"));

        String textColor = activeHome.getCssValue("color");
        assertEquals(textColor, "rgba(0, 0, 0, 1)", "Home link text is not black");

        boolean isClickable = navbarHome.getTagName().equals("a");
        assertFalse(isClickable, "Home link should not be clickable");
    }

    @Test
    public void testActorsLink(){
        final var navbarActors = driver.findElement(By.className("navbarActors"));
        navbarActors.click();
        assertTrue(driver.getCurrentUrl().endsWith("/actors"), "URL did not end with /actors");
    }

    @Test
    public void testMoviesLink(){
        final var navbarMovies = driver.findElement(By.className("navbarMovies"));
        navbarMovies.click();
        assertTrue(driver.getCurrentUrl().endsWith("/films"), "URL did not end with /films");
    }

    @Test
    public void testCategoriesLink(){
        final var navbarCategories = driver.findElement(By.className("navbarCategories"));
        navbarCategories.click();
        assertTrue(driver.getCurrentUrl().endsWith("/categories"), "URL did not end with /categories");
    }

    @Test
    public void testStreamsLink(){
        final var navbarStreams = driver.findElement(By.className("navbarStreams"));
        navbarStreams.click();
        assertTrue(driver.getCurrentUrl().endsWith("/streams"), "URL did not end with /streams");
    }

    @AfterClass
    public void afterAll() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
