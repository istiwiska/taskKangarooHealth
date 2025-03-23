package PageObjects;

import DataProviders.ConfigFileReader;
import Utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getLoginTitle() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("login.title.xpath")));
    }

    public WebElement getUsernameField() {
        return webDriver.findElement(By.id(ConfigFileReader.getProperty("login.username.id")));
    }

    public WebElement getPasswordField() {
        return webDriver.findElement(By.id(ConfigFileReader.getProperty("login.password.id")));
    }

    public WebElement getLoginButton() {
        return webDriver.findElement(By.id(ConfigFileReader.getProperty("login.button.id")));
    }

    public WebElement getErrorMessage() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("login.error.xpath")));
    }


    public boolean emailLoginPageIsDisplayed() {
        return getLoginTitle().isDisplayed();
    }

    public String getLoginHeaderTitle() {
        return getLoginTitle().getText();
    }

    public void fillEmailData(String email) {
        WebElement username = getUsernameField();
        username.clear();
        username.sendKeys(email);
    }

    public void fillPasswordData(String passwordText) {
        WebElement password = getPasswordField();
        password.clear();
        password.sendKeys(passwordText);
    }

    public void fillEmailPasswordData() {
        fillEmailData("standard_user");
        fillPasswordData("secret_sauce");
    }

    public void clickSignInButton() {
        getLoginButton().click();
    }

    public String getErrMessage(){
        WebElement errorMessage = getErrorMessage();
        errorMessage.isDisplayed();
        return errorMessage.getText();
    }
}
