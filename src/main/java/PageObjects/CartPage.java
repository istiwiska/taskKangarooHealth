package PageObjects;

import DataProviders.ConfigFileReader;
import Utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public WebDriver webDriver;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getCartButton() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.button.xpath")));
    }

    public WebElement getContinueCartButton() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.continueshopping.xpath")));
    }

    public WebElement getCheckoutButton() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.checkout.xpath")));
    }

    public WebElement getContinueButton() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.continue.xpath")));
    }

    public WebElement getFinishButton() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.finish.xpath")));
    }

    public WebElement getFirstNameField() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.firstname.xpath")));
    }

    public WebElement getLastNameField() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.lastname.xpath")));
    }

    public WebElement getPostalCodeField() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.postalcode.xpath")));
    }

    public WebElement getSuccessField() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("cart.successmessage.xpath")));
    }

    public void clickAddToCartProduct(){
        try {
            Helper.clickProduct(webDriver, ConfigFileReader.getProperty("home.product.name"),"cart");
        } catch (RuntimeException e) {
            System.err.println("Failed to click product cart: " + e.getMessage());
        }
    }

    public void clickOthrAddToCartProduct(){
        try {
            Helper.clickProduct(webDriver, ConfigFileReader.getProperty("home.product.name2"),"cart");
        } catch (RuntimeException e) {
            System.err.println("Failed to click product cart: " + e.getMessage());
        }
    }

    public void clickCartButton(){
        getCartButton().isDisplayed();
        try {
            getCartButton().click();
        } catch (RuntimeException e) {
            System.err.println("Cant click cart button " + e.getMessage());
        }
    }

    public void clickContinueShoppingButton(){
        getContinueCartButton().isDisplayed();
        try {
            getContinueCartButton().click();
        } catch (RuntimeException e) {
            System.err.println("Cant click cart button " + e.getMessage());
        }
    }

    public void clickCheckoutButton(){
        getCheckoutButton().isDisplayed();
        try {
            getCheckoutButton().click();
        } catch (RuntimeException e) {
            System.err.println("Cant click checkout button " + e.getMessage());
        }
    }

    public void clickContinueButton(){
        getContinueButton().isDisplayed();
        try {
            getContinueButton().click();
        } catch (RuntimeException e) {
            System.err.println("Cant click continue button " + e.getMessage());
        }
    }

    public void clickFinishButton(){
        getFinishButton().isDisplayed();
        try {
            getFinishButton().click();
        } catch (RuntimeException e) {
            System.err.println("Cant click finish button " + e.getMessage());
        }
    }

    public void typeFirstname(){
        getFirstNameField().isDisplayed();
        try {
            Helper.inputText(getFirstNameField(),"test");
        } catch (RuntimeException e) {
            System.err.println("Cant type first name " + e.getMessage());
        }
    }

    public void typeLastname(){
        getLastNameField().isDisplayed();
        try {
            Helper.inputText(getLastNameField(),"test");
        } catch (RuntimeException e) {
            System.err.println("Cant type last name " + e.getMessage());
        }
    }

    public void typePostalCode(){
        getPostalCodeField().isDisplayed();
        try {
            Helper.inputText(getPostalCodeField(),"test");
        } catch (RuntimeException e) {
            System.err.println("Cant type postal code " + e.getMessage());
        }
    }

    public String getSuccessMessage(){
        WebElement errorMessage = getSuccessField();
        errorMessage.isDisplayed();
        return errorMessage.getText();
    }

}
