package PageObjects;

import DataProviders.ConfigFileReader;
import Utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getHomeTitle() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("home.title.xpath")));
    }

    public WebElement getHomeSelect() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("home.filter.xpath")));
    }

    public WebElement getInventoryName() {
        return webDriver.findElement(By.xpath(ConfigFileReader.getProperty("home.product.xpath")));
    }

    public boolean homePageIsDisplayed() {
        return getHomeTitle().isDisplayed();
    }

    public String getHomeHeaderTitle() {
        return getHomeTitle().getText();
    }

    public void clickFilterSelect(){
        getHomeSelect().click();
        Helper.selectFilter(webDriver,ConfigFileReader.getProperty("home.filter.text"));
    }

    public void clickHyperlinkProduct(){
        try {
            Helper.clickProduct(webDriver, ConfigFileReader.getProperty("home.product.name"));
        } catch (RuntimeException e) {
            System.err.println("Failed to click product hyperlink: " + e.getMessage());
        }
    }

    public void clickHyperlinkProductNonExisting(){
        try {
            Helper.clickProduct(webDriver, ConfigFileReader.getProperty("home.product.nonexistingname"));
        } catch (RuntimeException e) {
            System.err.println("Failed to click product hyperlink: " + e.getMessage());
        }
    }

    public String getTitleInventory(){
        WebElement nameInventory = getInventoryName();
        nameInventory.isDisplayed();
        return nameInventory.getText();
    }

}
