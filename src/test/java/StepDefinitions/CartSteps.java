package StepDefinitions;

import DataProviders.ConfigFileReader;
import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Runners.Logger;
import Utilities.ExtentReportUtil;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;


public class CartSteps {

    TestContext testContext;
    CartPage cartPage;
    LoginPage loginPage;
    WebDriver driver;

    public CartSteps(TestContext context) throws IOException {
        testContext = context;
        cartPage = testContext.getPageObjectManager().getCartPage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
        driver = testContext.getDriverManager().getDriver();
    }


    @And("I click add to cart for product")
    public void iClickAddToCartForProduct() throws IOException {
        try {
            cartPage.clickAddToCartProduct();
            Logger.logStep("I click add to cart for product");
            ExtentReportUtil.logPassWithScreenshot(driver, "I click add to cart for product");
        } catch (RuntimeException e) {
            Logger.logStep("Error: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Error: Product not found");
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("I click icon cart")
    public void iClickIconCart() throws IOException {
        cartPage.clickCartButton();
        Logger.logStep("I click add to cart for product");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click add to cart for product");
    }

    @And("I click continue shopping button")
    public void iClickContinueShoppingButton() throws IOException {
        cartPage.clickContinueShoppingButton();
        Logger.logStep("I click continue shopping button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click continue shopping button");
    }

    @And("I click add to cart for other product")
    public void iClickAddToCartForOtherProduct() throws IOException {
        try {
            cartPage.clickOthrAddToCartProduct();
            Logger.logStep("I click add to cart for other product");
            ExtentReportUtil.logPassWithScreenshot(driver, "I click add to cart for other product");
        } catch (RuntimeException e) {
            Logger.logStep("Error: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Error: Product not found");
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @And("I click checkout button")
    public void iClickCheckoutButton() throws IOException {
        cartPage.clickCheckoutButton();
        Logger.logStep("I click checkout button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click checkout button");
    }

    @And("I type first name")
    public void iTypeFirstName() throws IOException {
        cartPage.typeFirstname();
        Logger.logStep("I type first name");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I type first name");
    }

    @And("I type last name")
    public void iTypeLastName() throws IOException {
        cartPage.typeLastname();
        Logger.logStep("I type last name");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I type last name");
    }

    @And("I type postal code")
    public void iTypePostalCode() throws IOException {
        cartPage.typePostalCode();
        Logger.logStep("I type postal code");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I type postal code");
    }

    @And("I click continue button")
    public void iClickContinueButton() throws IOException {
        cartPage.clickContinueButton();
        Logger.logStep("I click continue button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click continue button");
    }

    @And("I click finish button")
    public void iClickFinishButton() throws IOException {
        cartPage.clickFinishButton();
        Logger.logStep("I click finish button");
        ExtentReportUtil.logInfoWithScreenshot(driver, "I click finish button");
    }

    @Then("I should see success message")
    public void iShouldSeeSuccessMessage() throws IOException {
        String errMessage = cartPage.getSuccessMessage();
        try {
            Assert.assertEquals(ConfigFileReader.getProperty("cart.success.message"), errMessage);
            Logger.logStep("I should see success message");
            ExtentReportUtil.logPassWithScreenshot(driver, "I should see success message");

        } catch (AssertionError | IOException e) {
            Logger.logStep("Assertion failed: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Expected success message not shown");
            throw e;
        }
    }

    @Then("I should see error message")
    public void iShouldSeeErrorMessage() throws IOException {
        String errMessage = loginPage.getErrMessage();
        try {
            Assert.assertEquals(ConfigFileReader.getProperty("cart.errPostalCode.message"), errMessage);
            Logger.logStep("I should see error message");
            ExtentReportUtil.logPassWithScreenshot(driver, "I should see error message");

        } catch (AssertionError | IOException e) {
            Logger.logStep("Assertion failed: " + e.getMessage());
            ExtentReportUtil.logFailWithScreenshot(driver, "Expected error message not shown");
            throw e;
        }
    }
}
