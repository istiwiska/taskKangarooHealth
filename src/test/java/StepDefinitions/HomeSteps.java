package StepDefinitions;

import DataProviders.ConfigFileReader;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Runners.Logger;
import Utilities.ExtentReportUtil;
import Utilities.Helper;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;


public class HomeSteps {

    TestContext testContext;
    HomePage homePage;
    WebDriver driver;

    public HomeSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        driver = testContext.getDriverManager().getDriver();
    }


    @Then("I expect home page is showing")
    public void iExpectHomePageIsShowing() throws IOException {
        Assert.assertTrue(homePage.homePageIsDisplayed());

        String actualLoginHeaderTitle = homePage.getHomeHeaderTitle();
        Assert.assertEquals("Swag Labs", actualLoginHeaderTitle);
        Logger.logStep("I expect home page is showing");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I expect home page is showing");
    }

    @And("I select the filter for price from low to high")
    public void iSelectTheFilterForPriceFromLowToHigh() throws IOException {
        homePage.clickFilterSelect();
        Logger.logStep("I select the filter for price from low to high");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I select the filter for price from low to high");
    }

    @And("I search for a product")
    public void iSearchForAProduct() throws IOException {
        try {
            homePage.clickHyperlinkProduct();
            Logger.logStep("I search for a product");
            ExtentReportUtil.logPassWithScreenshot(driver, "I search for a product");
        } catch (RuntimeException e) {
            Logger.logStep("Error: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Error: Product not found");
            throw e;
        }
    }

    @Then("I should find the product I was looking for")
    public void iShouldFindTheProductIWasLookingFor() throws IOException {
        String titleInventory = homePage.getTitleInventory();
        try {
            Assert.assertEquals(ConfigFileReader.getProperty("home.product.name"), titleInventory);
            Logger.logStep("I should find the product I was looking for");
            ExtentReportUtil.logPassWithScreenshot(driver, "I should find the product I was looking for");

        } catch (AssertionError e) {
            Logger.logStep("Assertion failed: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Expected error message not shown");
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @And("I search for a non existing product")
    public void iSearchForANonExistingProduct() throws IOException {
        try {
            homePage.clickHyperlinkProductNonExisting();
            Logger.logStep("I search for a product");
            ExtentReportUtil.logPassWithScreenshot(driver, "I search for a non existing product");
        } catch (RuntimeException | IOException e) {
            Logger.logStep("Error: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "I search for a non existing product");
            throw e;
        }
    }

    @Then("I should cant find the product I was looking for")
    public void iShouldCantFindTheProductIWasLookingFor() throws IOException {
        Logger.logStep("I should cant find the product I was looking for");
        ExtentReportUtil.logPassWithScreenshot(driver, "I should cant find the product I was looking for");
    }
}
