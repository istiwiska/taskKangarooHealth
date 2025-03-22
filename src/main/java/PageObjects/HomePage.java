package PageObjects;

import Utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Operational Data')]")
    private WebElement operationalDataMenu;

    @FindBy(xpath = "//span[@id='headerHome']//a[contains(text(),'Home')]")
    private WebElement homeMenu;

    @FindBy(xpath = "//img[@src='images/inspectionPalm.png']")
    private WebElement palmManagementMenu;

    @FindBy(xpath = "//*[contains(text(),'Area Statement')]")
    private WebElement titleAreaStatement;

    @FindBy(xpath = "//*[contains(text(),'Harvesting Report')]")
    private WebElement titleHarvestingReport;

    @FindBy(xpath = "//h6[contains(text(),'Field Inspections Control - Kim Loong')]")
    private WebElement titleFieldInspection;

    @FindBy(xpath = "//h6[contains(text(),'Yield Performance - By Month')]")
    private WebElement titleYieldPerformance;

    @FindBy(xpath = "//h6[contains(text(),'Harvesting Round Control')]")
    private WebElement titleHarvestingReportControl;

    @FindBy(xpath = "//h6[contains(text(),'Rainfall Report By Month')]")
    private WebElement titleRainfall;

    @FindBy(xpath = "//h6[contains(text(),'Oil Extraction Rate Report By Month')]")
    private WebElement titleOilExtraction;

    @FindBy(xpath = "//h6[contains(text(),'Attendance Report By Month')]")
    private WebElement titleAttendance;

    @FindBy(xpath = "//h6[contains(text(),'Backlog Control By Month')]")
    private WebElement titleBacklog;

    @FindBy(css = ".utils a[href*='ffb_harvesting_field.jsp?']")
    private WebElement aHrefViewHarvestingReport;

    @FindBy(css = ".utils a[href*='field_inspection.jsp?']")
    private WebElement aHrefViewFieldInspectionReport;

    @FindBy(css = ".utils a[href*='inspection_new.jsp?']")
    private WebElement aHrefAddFielddInspectionReport;

    @FindBy(xpath = "//a[contains(@href, 'field_inspection.jsp?') and contains(text(), 'Sandakan')]")
    private WebElement aHrefSandakanInspectionReport;

    @FindBy(css = ".utils a[href*='yield_avg.jsp?']")
    private WebElement aHrefViewYieldPerformanceReport;

    @FindBy(css = ".utils a[href*='harvesting_round2.jsp?']")
    private WebElement aHrefViewHarvestingRoundReport;

    @FindBy(css = ".utils a[href*='rainfall.jsp']")
    private WebElement aHrefRainfall;

    @FindBy(css = ".utils a[href*='oer.jsp']")
    private WebElement aHrefOilExtraction;

    @FindBy(css = ".utils a[href*='attendance.jsp']")
    private WebElement aHrefAttendance;

    @FindBy(css = ".utils a[href*='backlog_report_bin_month.jsp']")
    private WebElement aHrefBacklog;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement aHrefLogout;

    @FindBy(css = "#headerHome a[href*='home.jsp?']")
    private WebElement aHrefHome;

    public boolean titleHarvestingReportIsDisplayed() {
        titleHarvestingReport.isDisplayed();
        return true;
    }

    public boolean titleFieldInspectionIsDisplayed() {
        titleFieldInspection.isDisplayed();
        return true;
    }

    public boolean titleYieldPerformanceIsDisplayed() {
        titleYieldPerformance.isDisplayed();
        return true;
    }

    public boolean titleHarvestingRoundControlIsDisplayed() {
        titleHarvestingReportControl.isDisplayed();
        return true;
    }

    public boolean titleRainfallIsDisplayed() {
        titleRainfall.isDisplayed();
        return true;
    }

    public boolean titleOilExtractionIsDisplayed() {
        titleOilExtraction.isDisplayed();
        return true;
    }

    public boolean titleAttendanceIsDisplayed() {
        titleAttendance.isDisplayed();
        return true;
    }

    public boolean titleBacklogIsDisplayed() {
        titleBacklog.isDisplayed();
        return true;
    }

    public void clickHomeMenu(){
        try{
            Helper.waitElementDisplay(webDriver,homeMenu);
            homeMenu.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,homeMenu);
            homeMenu.click();
        }
    }

    public void clickAHrefHarvestingReport(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefViewHarvestingReport);
            aHrefViewHarvestingReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefViewHarvestingReport);
            aHrefViewHarvestingReport.click();
        }
    }

    public void clickAHrefFieldInspectionReport(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefViewFieldInspectionReport);
            aHrefViewFieldInspectionReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefViewFieldInspectionReport);
            aHrefViewFieldInspectionReport.click();
        }
    }

    public void clickAHrefSandakanFieldInspectionReport(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefSandakanInspectionReport);
            aHrefSandakanInspectionReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefSandakanInspectionReport);
            aHrefSandakanInspectionReport.click();
        }
    }

    public void clickAHrefYieldPerformanceReport(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefViewYieldPerformanceReport);
            aHrefViewYieldPerformanceReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefViewYieldPerformanceReport);
            aHrefViewYieldPerformanceReport.click();
        }
    }

    public void clickAHrefHarvestingRoundReport(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefViewHarvestingRoundReport);
            aHrefViewHarvestingRoundReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefViewHarvestingRoundReport);
            aHrefViewHarvestingRoundReport.click();
        }
    }

    public void clickAHrefRainfall(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefRainfall);
            aHrefRainfall.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefRainfall);
            aHrefRainfall.click();
        }
    }

    public void clickAHrefOilExtraction(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefOilExtraction);
            aHrefOilExtraction.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefOilExtraction);
            aHrefOilExtraction.click();
        }
    }

    public void clickAHrefAttendance(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefAttendance);
            aHrefAttendance.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefAttendance);
            aHrefAttendance.click();
        }
    }

    public void clickAHrefBacklog(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefBacklog);
            aHrefBacklog.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefBacklog);
            aHrefBacklog.click();
        }
    }


    public void titleAreaStatementIsDisplayed() {
        Helper.waitElementDisplay(webDriver,titleAreaStatement);
    }

    public void clickOperationalDataMenu(){
        operationalDataMenu.isDisplayed();
        operationalDataMenu.click();
    }

    public void clickPalmManagementMenu(){
        try{
            Helper.waitElementDisplay(webDriver,palmManagementMenu);
            palmManagementMenu.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,palmManagementMenu);
            palmManagementMenu.click();
        }
    }

    public void clickOtherWork() throws InterruptedException {
        Helper.clickHyperlink(webDriver,"Other Work");
    }

    public void clickAHrefAddFieldInspection(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefAddFielddInspectionReport);
            aHrefAddFielddInspectionReport.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefAddFielddInspectionReport);
            aHrefAddFielddInspectionReport.click();
        }
    }

    public void clickAHrefLogout(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefLogout);
            aHrefLogout.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefLogout);
            aHrefLogout.click();
        }
    }

    public void clickAHrefHome(){
        try{
            Helper.waitElementDisplay(webDriver,aHrefHome);
            aHrefHome.click();
        }catch (Exception e){
            Helper.scrollToElement(webDriver,aHrefHome);
            aHrefHome.click();
        }
    }

}
