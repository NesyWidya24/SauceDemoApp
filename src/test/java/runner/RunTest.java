package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
        glue = "stepDef",
        tags = "@PositiveLogin or @NegativeLogin " +
                "or @PositiveAddItem or @NegativeRemoveItem " +
                "or @PositiveInputInformation or @NegativeInputInformation " +
                "or @PriceSorting or @ErrorPriceFilter",
        plugin = {"pretty", "html:target/RegressionReport.html"}
)
public class RunTest {

}
