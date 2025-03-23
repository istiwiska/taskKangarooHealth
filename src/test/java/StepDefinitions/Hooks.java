package StepDefinitions;

import Managers.FileReaderManager;
import Utilities.ExtentReportUtil;
import Utilities.TestCase;
import Utilities.TestContext;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import io.cucumber.plugin.event.Result;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ExtentReportUtil.clearScreenshotsFolder;

public class Hooks {

    TestContext testContext;
    WebDriver webDriver;
    static List<TestCase> testCases = new ArrayList<>();

    public static List<String> steps = new ArrayList<>();


    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void setUp(Scenario scenario) throws IOException {
        webDriver = testContext.getDriverManager().getDriver();
        webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
        steps.clear();
        ExtentReportUtil.startTestReport(scenario.getName());
    }


    @After
    public void captureScenarioStatus(Scenario scenario) {

        String modul = extractModulNameFromScenario(scenario);

        if(scenario.isFailed()) {
            testCases.add(new TestCase(modul,scenario.getName(), getFailureMessage(scenario),steps));
        }else{
            testCases.add(new TestCase(modul,scenario.getName(), "PASS",steps));
        }

        testContext.getDriverManager().closeDriver();
    }

    public String getFailureMessage(Scenario scenario) {
        Result failResult = null;


        try {
            // Get the delegate from the scenario
            Field delegate = scenario.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true);
            TestCaseState testCaseState = (TestCaseState) delegate.get(scenario);

            // Get the test case results from the delegate
            Field stepResults = testCaseState.getClass().getDeclaredField("stepResults");
            stepResults.setAccessible(true);
            List<Result> results = (List<Result>) stepResults.get(testCaseState);

            for (Result result : results) {
                if (result.getStatus().name().equalsIgnoreCase("FAILED")) {
                    failResult = result;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return (failResult != null) ? failResult.getError().getMessage() : "";
    }

    public static List<TestCase> getScenarioDetailsList() {
        return testCases;
    }

    public static void logStep(String stepName) {
        steps.add(stepName);
    }

    private String extractModulNameFromScenario(Scenario scenario) {
        // Example method to extract the modul name from the scenario tags or name
        String modul = "Default Modul"; // Default modul name if none is specified

        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@modul:")) {
                modul = tag.substring(7); // Extract modul name from tag, assuming format @modul:ModuleName
                break;
            }
        }

        return modul;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String errorMessage = "Test failed: "+getFailureMessage(scenario);
            System.out.println(errorMessage); // Debugging di console
            ExtentReportUtil.logFail(errorMessage);
            ExtentReportUtil.flushReport();
        }
    }


}
