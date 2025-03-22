package StepDefinitions;

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

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
        homePage = testContext.getPageObjectManager().getHomePage();
        driver = testContext.getDriverManager().getDriver();
    }

    @Given("Login page is displayed")
    public void loginPageIsDisplayed() throws IOException {
        clearScreenshotsFolder();
        ExtentReportUtil.startTestReport("Login Test");
        Assert.assertTrue(loginPage.emailLoginPageIsDisplayed());

        String actualLoginHeaderTitle = loginPage.getLoginHeaderTitle();
        Assert.assertEquals("Swag Labs", actualLoginHeaderTitle);
        Logger.logStep("Login page is displayed");
        ExtentReportUtil.logInfoWithScreenshot(driver, "Login page is displayed");
    }

    @And("I enter email {string}")
    public void iEnterEmai(String email) throws IOException {
        loginPage.fillEmailData(email);
        Logger.logStep("I enter email "+email);
        ExtentReportUtil.logInfoWithScreenshot(driver, "I enter email "+email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) throws IOException {
        loginPage.fillPasswordData(password);
        Logger.logStep("I enter password "+password);
        ExtentReportUtil.logInfoWithScreenshot(driver, "I enter password "+password);
    }

    @And("Click sign in button")
    public void clickSignInButton() throws IOException {
        loginPage.clickSignInButton();
        Logger.logStep("I click sign in button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click sign in button");
    }

}
