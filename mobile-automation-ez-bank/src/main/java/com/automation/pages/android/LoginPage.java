package com.automation.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

import com.automation.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

/**
 * Android Login Page implementation
 * Demonstrates Carina mobile testing framework capabilities:
 * - Locator strategies (ID, XPath, Text)
 * - Mobile utilities (hideKeyboard, wait for element)
 * - Page object pattern with Carina
 * - Logging with SLF4J
 */
@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // Carina function: @FindBy with ID locator - most reliable for mobile
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/usernameInput")
    private ExtendedWebElement usernameInputField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/passwordInput")
    private ExtendedWebElement passwordInputField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//android.view.View[@content-desc='Log In']")
    private ExtendedWebElement loginPageTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
        LOGGER.info("LoginPage initialized for Android device");
    }

    /**
     * Carina function: isElementPresent() - checks if element is visible
     * @return true if login page is displayed
     */
    @Override
    public boolean isLoginPageOpened() {
        LOGGER.debug("Checking if login page is opened");
        boolean isOpened = loginPageTitle.isElementPresent();
        LOGGER.info("Login page opened status: {}", isOpened);
        return isOpened;
    }

    /**
     * Carina function: type() - enters text in input field
     * Carina function: hideKeyboard() - hides mobile keyboard after input
     * @param username the username to enter
     */
    @Override
    public void enterUsername(String username) {
        LOGGER.info("Entering username: {}", username);
        usernameInputField.type(username);
        LOGGER.debug("Username entered, hiding keyboard");
        hideKeyboard();
    }

    /**
     * Carina function: type() - enters text in input field
     * @param password the password to enter
     */
    @Override
    public void enterPassword(String password) {
        LOGGER.info("Entering password");
        passwordInputField.type(password);
        LOGGER.debug("Password entered");
    }

    /**
     * Carina function: click() - clicks on button element
     * Carina function: initPage() - initializes next page after action
     * @return HomePageBase after successful login
     */
    @Override
    public HomePage clickLoginButton() {
        LOGGER.info("Clicking login button");
        loginButton.click();
        LOGGER.debug("Login button clicked, initializing HomePage");
        return initPage(getDriver(), HomePage.class);
    }

    /**
     * Carina function: getAttribute() - retrieves element attribute value
     * @return true if login button is enabled
     */
    @Override
    public boolean isLoginButtonEnabled() {
        LOGGER.debug("Checking if login button is enabled");
        boolean isEnabled = Boolean.parseBoolean(loginButton.getAttribute("enabled"));
        LOGGER.info("Login button enabled status: {}", isEnabled);
        return isEnabled;
    }

    /**
     * Complete login workflow
     * Demonstrates chaining Carina methods for full user journey
     * @param username the username
     * @param password the password
     * @return HomePageBase after successful login
     */
    @Override
    public HomePage login(String username, String password) {
        LOGGER.info("=== Starting login workflow ===");
        enterUsername(username);
        enterPassword(password);
        
        // Verify login button is present before clicking
        LOGGER.debug("Verifying login button is present");
        if (!loginButton.isElementPresent()) {
            LOGGER.error("Login button not found");
            throw new RuntimeException("Login button not found");
        }
        
        HomePage homePage = clickLoginButton();
        LOGGER.info("=== Login workflow completed, HomePage initialized ===");
        return homePage;
    }
}
