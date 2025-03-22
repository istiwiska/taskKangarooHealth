package Runners;

import StepDefinitions.Hooks;

public class Logger {
    public static void logStep(String stepName) {
        Hooks.logStep(stepName);
    }
}
