package com.automation.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.automation.pages.android.HomePage;

/**
 * Base page for LoginPage - defines contract for all platform implementations
 * Used for user authentication in EZ Bank application
 */
public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter username in login form
     * @param username the username to enter
     */
    public abstract void enterUsername(String username);

    /**
     * Enter password in login form
     * @param password the password to enter
     */
    public abstract void enterPassword(String password);

    /**
     * Click login button
     * @return HomePage after successful login
     */
    public abstract HomePage clickLoginButton();

    /**
     * Perform complete login action
     * @param username the username
     * @param password the password
     * @return HomePage after successful login
     */
    public abstract HomePage login(String username, String password);

    /**
     * Check if login page is displayed
     * @return true if login page is visible
     */
    public abstract boolean isLoginPageOpened();

    /**
     * Check if login button is enabled
     * @return true if login button is enabled
     */
    public abstract boolean isLoginButtonEnabled();
}
