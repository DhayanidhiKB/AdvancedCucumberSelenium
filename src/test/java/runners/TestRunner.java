package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "@Regression and @EParcel and @ExistingCustomers and @GenerateAgreement and @EParcelBundle",
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber_reports/UCTProject.html",
                "json:target/cucumber_reports/UCTProject.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:results/failed_scenarios.txt"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}