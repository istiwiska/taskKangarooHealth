package StepDefinitions;

import DataProviders.ConfigFileReader;
import Managers.AllDriverManager;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Runners.Logger;
import Utilities.ExtentReportUtil;
import Utilities.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Utilities.ScreenshotUtil;

import java.io.IOException;

import static Utilities.ExtentReportUtil.*;


public class LoginSteps {

    TestContext testContext;
    LoginPage loginPage;
    HomePage homePage;
    WebDriver driver;

    public LoginSteps(TestContext context) throws IOException {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
        homePage = testContext.getPageObjectManager().getHomePage();
        driver = testContext.getDriverManager().getDriver();
    }

    @Given("Login page is displayed")
    public void loginPageIsDisplayed() throws IOException {
        Assert.assertTrue(loginPage.emailLoginPageIsDisplayed());

        String actualLoginHeaderTitle = loginPage.getLoginHeaderTitle();
        Assert.assertEquals("Swag Labs", actualLoginHeaderTitle);
        Logger.logStep("Login page is displayed");
        ExtentReportUtil.logInfoWithScreenshot(driver, "Login page is displayed");
    }

    @And("I enter username {string}")
    public void iEnterEmai(String username) throws IOException {
        loginPage.fillEmailData(username);
        Logger.logStep("I enter username "+username);
        ExtentReportUtil.logInfoWithScreenshot(driver, "I enter email "+username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) throws IOException {
        loginPage.fillPasswordData(password);
        Logger.logStep("I enter password "+password);
        ExtentReportUtil.logInfoWithScreenshot(driver, "I enter password "+password);
    }

    @And("I click sign in button")
    public void clickSignInButton() throws IOException {
        loginPage.clickSignInButton();
        Logger.logStep("I click sign in button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click sign in button");
    }

    @Then("I expect error message is showing")
    public void iExpectErrorMessageIsShowing() throws IOException {
        String errMessage = loginPage.getErrMessage();
        try {
            Assert.assertEquals(ConfigFileReader.getProperty("login.error.usernamenotexisting"), errMessage);
            Logger.logStep("I expect error message is showing");
            ExtentReportUtil.logPassWithScreenshot(driver, "I expect error message is showing");

        } catch (AssertionError e) {
            Logger.logStep("Assertion failed: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Expected error message not shown");
            throw e;
        }
    }

    @And("I enter username and password")
    public void iEnterUsernameAndPassword() throws IOException {
        loginPage.fillEmailPasswordData();
        Logger.logStep("I enter username and password");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I enter username and password");
    }
}
