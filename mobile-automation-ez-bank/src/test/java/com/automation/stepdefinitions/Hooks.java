package com.automation.stepdefinitions;

import com.zebrunner.carina.core.IAbstractTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks implements IAbstractTest {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("=".repeat(60));
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("\n" + "=".repeat(60));
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
        } else {
            System.out.println("Scenario Passed: " + scenario.getName());
        }
        System.out.println("=".repeat(60) + "\n");
        
        // Driver will be closed automatically by Carina framework
    }
}
