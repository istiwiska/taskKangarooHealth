package Runners;

import StepDefinitions.Hooks;
import Utilities.SendEmail;
import Utilities.TestCase;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@CucumberOptions(
        features = "src/test/reso" +
                "urces/features",
        glue = "StepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "timeline:target/test-output-thread/"
        }

)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("================ BEFORE SUITE ================");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Tear Down");
        List<TestCase> testcases = Hooks.getScenarioDetailsList();
        List<String> recipientEmails = List.of("istiw28@gmail.com");
        SendEmail.sendEmail("t70840400@gmail.com","kdzw nbqj fzbm gmlt",recipientEmails,"Test E2E Kim Loong Web Test Server",testcases);
        System.out.println("================ AFTER SUITE ================");
    }
}
