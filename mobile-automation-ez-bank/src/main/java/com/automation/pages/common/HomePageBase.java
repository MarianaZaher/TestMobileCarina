package com.automation.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

/**
 * Base page for HomePage - defines contract for all platform implementations
 * Part of EZ Bank mobile application page object model
 */
public abstract class HomePageBase extends AbstractPage {

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if home page is opened
     * @return true if home page is displayed
     */
    public abstract boolean isHomePageOpened();

    /**
     * Open products cart
     */
    public abstract void openProductsCart();

    /**
     * Check if app logo is visible
     * @return true if app logo is visible
     */
    public abstract boolean isAppLogoVisible();

    /**
     * Get app title text
     * @return the app title
     */
    public abstract String getAppTitle();

    /**
     * Get cart icon content description
     * @return the cart icon description
     */
    public abstract String getCartIconContentDescription();

    /**
     * Double-click on product
     */
    public abstract void doubleClickProduct();

    /**
     * Long-press on product
     */
    public abstract void longPressProduct();

    /**
     * Scroll to cart
     */
    public abstract void scrollToCart();

    /**
     * Verify all home page elements
     * @return true if all elements are present
     */
    public abstract boolean verifyAllHomePageElements();
}
