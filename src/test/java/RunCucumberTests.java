import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty"}
)
public class RunCucumberTests extends AbstractTestNGCucumberTests {
}
