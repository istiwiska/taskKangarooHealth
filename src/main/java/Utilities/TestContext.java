package Utilities;

import Managers.AllDriverManager;
import Managers.PageObjectManager;

import java.io.IOException;

public class TestContext {

    private AllDriverManager driverManager;
    private PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;

    public TestContext() throws IOException {
        driverManager = new AllDriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public AllDriverManager getDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
