package com.automation.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

import com.automation.pages.common.HomePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

/**
 * Android Home Page implementation
 * Demonstrates Carina mobile testing framework capabilities:
 * - Element locators with @FindBy
 * - Carina methods: isElementPresent(), click(), waitForElementPresent()
 * - Logging with SLF4J
 * - Mobile UI element interactions
 */
public class HomePage extends HomePageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // Carina function: @FindBy with ID locator - most reliable for mobile testing
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/mTvTitle")
    private ExtendedWebElement appLogo;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private ExtendedWebElement productsCart;

    // Additional locators for comprehensive testing
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/mTvTitle")
    private ExtendedWebElement homePageTitle;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.saucelabs.mydemoapp.android:id/cartIV']")
    private ExtendedWebElement cartIcon;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.saucelabs.mydemoapp.android:id/imageIV']")
    private ExtendedWebElement productImage;

    public HomePage(WebDriver driver) {
        super(driver);
        LOGGER.info("HomePage initialized for Android device");
    }

    /**
     * Carina function: isElementPresent() - checks if element is visible on the page
     * Used for page validation and assertions
     * @return true if home page is opened and app logo is visible
     */
    @Override
    public boolean isHomePageOpened() {
        LOGGER.debug("Checking if home page is opened");
        boolean isOpened = appLogo.isElementPresent();
        LOGGER.info("Home page opened status: {}", isOpened);
        return isOpened;
    }

    /**
     * Carina function: click() - clicks on UI element
     * Demonstrates mobile element interaction
     */
    @Override
    public void openProductsCart() {
        LOGGER.info("=== Opening products cart ===");
        
        // Carina function: isElementPresent() - checks if element is visible before clicking
        LOGGER.debug("Verifying cart icon is present");
        if (!productsCart.isElementPresent()) {
            LOGGER.error("Cart icon not found");
            throw new RuntimeException("Cart icon not found");
        }
        
        // Carina function: click() - performs click action
        LOGGER.debug("Clicking on products cart");
        productsCart.click();
        
        LOGGER.info("Products cart opened");
    }

    /**
     * Carina function: isVisible() - checks if element is visible (alternative to isElementPresent)
     * @return true if app logo is visible
     */
    public boolean isAppLogoVisible() {
        LOGGER.debug("Checking if app logo is visible");
        boolean isVisible = appLogo.isVisible();
        LOGGER.info("App logo visibility: {}", isVisible);
        return isVisible;
    }

    /**
     * Carina function: getText() - retrieves text from element
     * @return the app title text
     */
    public String getAppTitle() {
        LOGGER.debug("Getting app title text");
        String title = homePageTitle.getText();
        LOGGER.info("App title: {}", title);
        return title;
    }

    /**
     * Carina function: getAttribute() - retrieves element attribute value
     * @return the content description of cart icon
     */
    public String getCartIconContentDescription() {
        LOGGER.debug("Getting cart icon content description");
        String contentDesc = cartIcon.getAttribute("content-desc");
        LOGGER.info("Cart icon description: {}", contentDesc);
        return contentDesc;
    }

    /**
     * Carina function: doubleClick() - performs double-click action
     * Useful for mobile interactions like zooming or rapid selections
     */
    public void doubleClickProduct() {
        LOGGER.info("=== Double-clicking on product ===");
        
        // Verify product image is present
        LOGGER.debug("Verifying product image is present");
        if (!productImage.isElementPresent()) {
            LOGGER.error("Product image not found");
            throw new RuntimeException("Product image not found");
        }
        
        // Carina function: doubleClick() - double-click mobile element
        LOGGER.debug("Performing double-click on product");
        productImage.doubleClick();
        
        LOGGER.info("Double-click on product completed");
    }

    /**
     * Carina function: hover() - performs hover/focus action on element
     * Mobile alternative to long-press
     */
    public void longPressProduct() {
        LOGGER.info("=== Performing hover action on product ===");
        
        // Carina function: hover() - hover/focus on mobile element
        LOGGER.debug("Performing hover on product");
        productImage.hover();
        
        LOGGER.info("Hover action on product completed");
    }

    /**
     * Carina function: rightClick() - performs right-click/context menu action
     * Mobile alternative to swipe for scrolling
     */
    public void scrollToCart() {
        LOGGER.info("=== Scrolling/navigating to cart ===");
        
        // Verify product is present
        LOGGER.debug("Verifying product image is present");
        if (!productImage.isElementPresent()) {
            LOGGER.error("Product image not found");
            throw new RuntimeException("Product image not found");
        }
        
        // Carina function: rightClick() - context action (mobile alternative)
        LOGGER.debug("Performing context action for scroll");
        productImage.rightClick();
        
        LOGGER.info("Scroll completed");
    }

    /**
     * Verify multiple page elements are present
     * Comprehensive page validation using Carina methods
     * @return true if all home page elements are visible
     */
    public boolean verifyAllHomePageElements() {
        LOGGER.info("=== Verifying all home page elements ===");
        
        boolean logoPresent = appLogo.isElementPresent();
        LOGGER.debug("App logo present: {}", logoPresent);
        
        boolean cartPresent = productsCart.isElementPresent();
        LOGGER.debug("Cart icon present: {}", cartPresent);
        
        boolean productPresent = productImage.isElementPresent();
        LOGGER.debug("Product image present: {}", productPresent);
        
        boolean allPresent = logoPresent && cartPresent && productPresent;
        LOGGER.info("All home page elements present: {}", allPresent);
        
        return allPresent;
    }
}
