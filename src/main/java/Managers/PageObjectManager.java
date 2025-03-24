package Managers;

import PageObjects.*;
import org.openqa.selenium.WebDriver;

import javax.swing.text.View;

public class PageObjectManager {

    private final WebDriver webDriver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;


    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Short Hand If...Else
    //General If...Else
    public LoginPage getLoginPage() {

        if (loginPage == null) {
            loginPage = new LoginPage(webDriver);
            webDriver.manage().window().maximize();
        }
        return loginPage;

    }

    public HomePage getHomePage() {

        if (homePage == null) {
            homePage = new HomePage(webDriver);
            webDriver.manage().window().maximize();
        }
        return homePage;

    }

    public CartPage getCartPage() {

        if (cartPage == null) {
            cartPage = new CartPage(webDriver);
            webDriver.manage().window().maximize();
        }
        return cartPage;

    }



}
