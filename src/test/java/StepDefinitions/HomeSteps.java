package StepDefinitions;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Runners.Logger;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


public class HomeSteps {

    TestContext testContext;
    LoginPage loginPage;
    HomePage homePage;

    public HomeSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();

    }


//    @Then("i see title area statement")
//    public void iSeeTitleAreaStatement() {
//        homePage.titleAreaStatementIsDisplayed();
//    }

    @And("I see title harvesting report")
    public void iSeeTitleHarvestingReport() {
        homePage.titleHarvestingReportIsDisplayed();
        Logger.logStep("I see title harvesting report");
    }

    @And("I see title yield performance")
    public void iSeeTitleYieldPerformance() {
        homePage.titleYieldPerformanceIsDisplayed();
        Logger.logStep("I see title yield performance");
    }


    @And("I see title harvesting round")
    public void iSeeTitleHarvestingRound() {
        homePage.titleHarvestingRoundControlIsDisplayed();
        Logger.logStep("I see title harvesting round");
    }

    @And("I see title rainfall")
    public void iSeeTitleRainfall() {
        homePage.titleRainfallIsDisplayed();
        Logger.logStep("I see title rainfall");
    }

    @And("I see title oil extraction")
    public void iSeeTitleOilExtraction() {
        homePage.titleOilExtractionIsDisplayed();
        Logger.logStep("I see title oil extraction");
    }

    @And("I see title backlog")
    public void iSeeTitleBacklog() {
        homePage.titleBacklogIsDisplayed();
        Logger.logStep("I see title backlog");
    }

    @And("I click operational data")
    public void iClickOperationalData() {
        homePage.clickOperationalDataMenu();
        Logger.logStep("I click operational data");
    }

    @And("I click palm management")
    public void iClickPalmManagement() {
        homePage.clickPalmManagementMenu();
        Logger.logStep("I click palm management");
    }

    @And("I click other work tab")
    public void iClickOtherWorkTab() throws InterruptedException {
        homePage.clickOtherWork();
        Logger.logStep("I click other work tab");
    }

    @And("I click logout")
    public void iClickLogout() {
        homePage.clickAHrefLogout();
        Logger.logStep("I click logout");
    }
}
