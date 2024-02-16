package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "@Regression and @EParcel and @ExistingCustomers and @GenerateAgreement and  @InternationalDigitalPCMSBundleInclAirmailLetters",
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber_reports/UCTProject.html",
                "com.my.salesforce.sandbox.application.toolsIntegration.PractiTestManager",
                "json:target/cucumber_reports/UCTProject.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:results/failed_scenarios.txt"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}