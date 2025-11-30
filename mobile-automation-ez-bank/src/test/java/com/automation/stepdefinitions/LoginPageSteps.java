package com.automation.stepdefinitions;

import com.automation.pages.android.LoginPage;
import com.automation.pages.android.HomePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zebrunner.carina.core.IAbstractTest;
import java.lang.invoke.MethodHandles;

/**
 * Login Page Step Definitions
 * Demonstrates Carina mobile testing with:
 * - Page object pattern
 * - Mobile element interactions
 * - SLF4J logging
 * - Soft assertions
 * - BDD scenarios
 */
public class LoginPageSteps implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SoftAssert softAssert;

    @Given("I launch the EZ Bank mobile application")
    public void launchApplication() {
        LOGGER.info("=== Launching EZ Bank mobile application ===");
        driver = getDriver();
        softAssert = new SoftAssert();
        pause(3); // Wait for app to load
        LOGGER.info("Application launched and driver initialized");
    }

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        LOGGER.info("=== Navigating to login page ===");
        loginPage = new LoginPage(driver);
        
        // Carina function: isLoginPageOpened() - validates login page is displayed
        boolean isLoginPageOpened = loginPage.isLoginPageOpened();
        LOGGER.debug("Login page opened: {}", isLoginPageOpened);
        
        softAssert.assertTrue(isLoginPageOpened, "Login page should be opened");
        LOGGER.info("Login page verification completed");
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        LOGGER.info("=== Entering username: {} ===", username);
        // Carina function: enterUsername() - types username and hides keyboard
        loginPage.enterUsername(username);
        LOGGER.info("Username entered successfully");
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        LOGGER.info("=== Entering password ===");
        // Carina function: enterPassword() - types password
        loginPage.enterPassword(password);
        LOGGER.info("Password entered successfully");
    }

    @When("I click the login button")
    public void clickLoginButton() {
        LOGGER.info("=== Clicking login button ===");
        
        // Carina function: isLoginButtonEnabled() - checks if button is enabled
        boolean isEnabled = loginPage.isLoginButtonEnabled();
        LOGGER.debug("Login button enabled: {}", isEnabled);
        softAssert.assertTrue(isEnabled, "Login button should be enabled");
        
        // Carina function: clickLoginButton() - clicks and initializes next page
        homePage = loginPage.clickLoginButton();
        LOGGER.info("Login button clicked, navigating to home page");
    }

    @When("I login with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        LOGGER.info("=== Starting login workflow with credentials ===");
        // Carina function: login() - complete login workflow
        homePage = loginPage.login(username, password);
        LOGGER.info("Login workflow completed");
    }

    @Then("I should be logged in successfully")
    public void verifySuccessfulLogin() {
        LOGGER.info("=== Verifying successful login ===");
        
        // Carina function: isHomePageOpened() - validates home page is displayed
        boolean isHomePageOpened = homePage.isHomePageOpened();
        LOGGER.debug("Home page opened: {}", isHomePageOpened);
        
        softAssert.assertTrue(isHomePageOpened, "Home page should be opened after successful login");
        LOGGER.info("Login verification completed - user successfully logged in");
    }

    @Then("the home page should display all elements")
    public void verifyAllHomePageElements() {
        LOGGER.info("=== Verifying all home page elements ===");
        
        // Carina function: verifyAllHomePageElements() - validates multiple elements
        boolean allElementsPresent = homePage.verifyAllHomePageElements();
        LOGGER.debug("All elements present: {}", allElementsPresent);
        
        softAssert.assertTrue(allElementsPresent, "All home page elements should be visible");
        LOGGER.info("Home page elements verification completed");
    }

    @Then("I should see the app logo")
    public void verifyAppLogo() {
        LOGGER.info("=== Verifying app logo visibility ===");
        
        // Carina function: isAppLogoVisible() - checks logo visibility
        boolean isLogoVisible = homePage.isAppLogoVisible();
        LOGGER.debug("App logo visible: {}", isLogoVisible);
        
        softAssert.assertTrue(isLogoVisible, "App logo should be visible");
        LOGGER.info("App logo verification completed");
    }

    @Then("the app title should be displayed")
    public void verifyAppTitle() {
        LOGGER.info("=== Verifying app title ===");
        
        // Carina function: getAppTitle() - retrieves app title text
        String appTitle = homePage.getAppTitle();
        LOGGER.debug("App title: {}", appTitle);
        
        softAssert.assertNotNull(appTitle, "App title should be displayed");
        LOGGER.info("App title verification completed - Title: {}", appTitle);
    }

    @Then("the login page should contain the expected elements")
    public void verifyLoginPageElements() {
        LOGGER.info("=== Verifying login page elements ===");
        
        // Validate login page is displayed with all required fields
        boolean isLoginPageOpened = loginPage.isLoginPageOpened();
        LOGGER.debug("Login page opened: {}", isLoginPageOpened);
        
        softAssert.assertTrue(isLoginPageOpened, "Login page should be opened");
        
        // Check if login button is present
        boolean isButtonEnabled = loginPage.isLoginButtonEnabled();
        LOGGER.debug("Login button enabled: {}", isButtonEnabled);
        
        softAssert.assertTrue(isButtonEnabled, "Login button should be enabled");
        LOGGER.info("Login page elements verification completed");
        
        softAssert.assertAll();
    }
}
