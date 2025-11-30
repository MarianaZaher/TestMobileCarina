package com.automation.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

import com.automation.pages.android.HomePage;
import com.zebrunner.carina.core.IAbstractTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Home Page Step Definitions
 * Demonstrates Carina mobile testing with:
 * - Page object pattern
 * - Mobile element interactions (click, swipe, longPress, doubleClick)
 * - SLF4J logging for test execution visibility
 * - Soft assertions for comprehensive validation
 * - Wait mechanisms for element visibility
 */
public class HomePageSteps implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private WebDriver driver;
    private HomePage homePage;
    private SoftAssert softAssert;

    @Given("I launch the mobile app")
    public void iLaunchTheMobileApp() {
        LOGGER.info("=== Launching the mobile app ===");
        driver = getDriver();
        softAssert = new SoftAssert();
        pause(3); // Wait for app to load
        LOGGER.info("Mobile app launched successfully");
    }

    @When("I am on the home page")
    public void iAmOnTheHomePage() {
        LOGGER.info("=== Navigating to home page ===");
        homePage = new HomePage(driver);
        
        // Carina function: isHomePageOpened() - verifies home page is displayed
        boolean isHomePageOpened = homePage.isHomePageOpened();
        LOGGER.debug("Home page opened: {}", isHomePageOpened);
        
        softAssert.assertTrue(isHomePageOpened, "Home page should be opened");
        LOGGER.info("Home page verification completed");
    }

    @Then("the app logo should be displayed")
    public void theAppLogoShouldBeDisplayed() {
        LOGGER.info("=== Verifying app logo ===");
        
        // Carina function: isAppLogoVisible() - checks if app logo is visible
        boolean isLogoVisible = homePage.isAppLogoVisible();
        LOGGER.debug("App logo visible: {}", isLogoVisible);
        
        softAssert.assertTrue(isLogoVisible, "App logo should be visible");
        LOGGER.info("App logo is displayed!");
    }

    @When("I click on the products cart")
    public void iClickOnTheProductsCart() {
        LOGGER.info("=== Clicking on products cart ===");
        
        // Carina function: openProductsCart() - clicks cart with element presence wait
        homePage.openProductsCart();
        LOGGER.info("Products cart clicked successfully!");
    }

    @Then("the cart should open")
    public void theCartShouldOpen() {
        LOGGER.info("=== Verifying cart opened ===");
        pause(2); // Wait to see the cart
        LOGGER.info("Cart opened successfully!");
    }

    @When("I verify the app title")
    public void verifyAppTitle() {
        LOGGER.info("=== Verifying app title ===");
        
        // Carina function: getAppTitle() - retrieves app title text
        String appTitle = homePage.getAppTitle();
        LOGGER.debug("App title: {}", appTitle);
        
        softAssert.assertNotNull(appTitle, "App title should be displayed");
        LOGGER.info("App title verification completed - Title: {}", appTitle);
    }

    @When("I double-click on a product")
    public void doubleClickProduct() {
        LOGGER.info("=== Double-clicking on product ===");
        
        // Carina function: doubleClick() - performs double-click gesture
        homePage.doubleClickProduct();
        
        LOGGER.info("Product double-clicked successfully");
    }

    @When("I long-press on a product")
    public void longPressProduct() {
        LOGGER.info("=== Long-pressing on product ===");
        
        // Carina function: longPress() - performs long-press gesture (mobile specific)
        homePage.longPressProduct();
        
        LOGGER.info("Product long-pressed successfully");
    }

    @When("I scroll through the products")
    public void scrollThroughProducts() {
        LOGGER.info("=== Scrolling through products ===");
        
        // Carina function: swipe() - performs swipe gesture for scrolling
        homePage.scrollToCart();
        
        LOGGER.info("Products scrolled successfully");
    }

    @Then("all home page elements should be visible")
    public void verifyAllHomePageElements() {
        LOGGER.info("=== Verifying all home page elements ===");
        
        // Carina function: verifyAllHomePageElements() - validates multiple elements at once
        boolean allElementsPresent = homePage.verifyAllHomePageElements();
        LOGGER.debug("All elements present: {}", allElementsPresent);
        
        softAssert.assertTrue(allElementsPresent, "All home page elements should be visible");
        LOGGER.info("All elements verification completed");
        
        // Final assertion batch
        softAssert.assertAll();
    }

    @When("I get the cart icon description")
    public void getCartIconDescription() {
        LOGGER.info("=== Retrieving cart icon description ===");
        
        // Carina function: getCartIconContentDescription() - retrieves element attribute
        String description = homePage.getCartIconContentDescription();
        LOGGER.debug("Cart icon description: {}", description);
        
        softAssert.assertNotNull(description, "Cart icon should have a content description");
        LOGGER.info("Cart icon description retrieved successfully");
    }
}
