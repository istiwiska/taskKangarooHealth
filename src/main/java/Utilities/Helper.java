package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Helper {

    public static JavascriptExecutor js;
    public static WebDriver webDriver;
    public static Actions action;

    public static void scrollToElement(WebDriver webDriver,WebElement webElement){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    private static boolean checkDesiredLink(String hyperlinkText) {
        try {
            webDriver.findElement(By.linkText(hyperlinkText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clickHyperlink(WebDriver webDriver,String hyperlinkText) throws InterruptedException {
//        while (!checkDesiredLink(hyperlinkText)) {
//            // Click on the "Next" button or element to navigate to the next page
//            WebElement nextButton = webDriver.findElement(By.xpath("//a[@class='next']"));
//            nextButton.click();
//
//            webDriver.wait(10);
//        }
        webDriver.findElement(By.linkText(hyperlinkText)).click();
    }

    public static void clickButton(WebElement webElement) {
        try {
            new WebDriverWait(webDriver, 20)
                    .until(ExpectedConditions.visibilityOf(webElement));
            webElement.isDisplayed();
            webElement.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].scrollIntoView(true);",
                    webElement);
            WebElement scanEle = new WebDriverWait(webDriver, 20)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            action.moveToElement(scanEle).click().build().perform();
        }
    }

    public static boolean clickPicture(WebDriver webDriver, WebElement webElement) {
        try {
            webElement.click();
            System.out.println("Successfull click image");
            return true;
        } catch (Exception e) {
            try {
                WebElement scanEle = new WebDriverWait(webDriver, 20).until(
                        ExpectedConditions.elementToBeClickable(webElement));
                action.moveToElement(scanEle).click().build().perform();
                return true;
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("No Image in this data.");
                return false;
            }
        }
    }

    public static void inputText(WebElement webElement, String text) {
        try {
            webElement.sendKeys(text);
        } catch (Exception e) {
            // TODO: handle exception
            js.executeScript("arguments[0].scrollIntoView(true);", webElement);
            webElement.sendKeys(text);
        }
    }


    public static void waitElementDisplay(WebDriver webDriver, WebElement webElement) {
       try{
           webElement.isDisplayed();
       }catch (Exception e) {
           new WebDriverWait(webDriver, 20)
                   .until(ExpectedConditions.visibilityOf(webElement));
           webElement.isDisplayed();
        }
    }


    public static void scrollToAndClick(WebDriver driver, String locator, String locatorType) {
        By byLocator;

        // Determine the type of locator
        switch (locatorType.toLowerCase()) {
            case "id":
                byLocator = By.id(locator);
                break;
            case "name":
                byLocator = By.name(locator);
                break;
            case "css":
                byLocator = By.cssSelector(locator);
                break;
            case "xpath":
                byLocator = By.xpath(locator);
                break;
            case "class":
                byLocator = By.className(locator);
                break;
            case "tag":
                byLocator = By.tagName(locator);
                break;
            case "linktext":
                byLocator = By.linkText(locator);
                break;
            case "partiallinktext":
                byLocator = By.partialLinkText(locator);
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }

        // Locate the element using the resolved By locator
        WebElement element = driver.findElement(byLocator);

        // Use JavaScriptExecutor to scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        // Optional: Add a delay to ensure the scroll has completed
        try {
            Thread.sleep(500); // Adjust delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the element
        element.click();
    }

    public static Boolean AssertTextMessage(WebDriver driver,String className,String text){
        System.out.println("//"+className+"[@class='success' and contains(text(), '"+text+"')]");
        WebElement successMessage = driver.findElement(By.xpath("//"+className+"[@class='success' and contains(text(), '"+text+"')]"));

        // Get the text of the element
        String actualText = successMessage.getText();

        // Expected text
        String expectedText = text;

        // Assert the text
        if (actualText.equals(expectedText)) {
            return true;
        } else {
            return false;
        }
    }


    public static void clickRadioButton(WebDriver driver, String idElement){
        WebElement radioButton = driver.findElement(By.id(idElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButton);
        radioButton.click();
    }

    public static void clickCheckbox(WebDriver driver, String text){
        WebElement checkbox = driver.findElement(By.xpath("//label[b[text()='"+text+"']]/input"));
        // Scroll the checkbox into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        // Click the checkbox
        checkbox.click();
        // Optionally, log confirmation
        System.out.println("Checkbox 'AH1 -PM10A' clicked successfully.");
    }

    public static String removeShould(String input) {
        // Replace the phrases "Should be an " and "Should " with an empty string
        input = input.replace("Should be an ", "");
        input = input.replace("Should ", "");
        return input;
    }




}
