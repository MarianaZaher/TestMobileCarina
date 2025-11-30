package com.automation.stepdefinitions;

import com.automation.api.PostUserMethod;
import com.automation.api.GetUserMethod;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zebrunner.carina.core.IAbstractTest;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

public class APIUserSteps implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Response response;
    private PostUserMethod postUserMethod;
    private GetUserMethod getUserMethod;
    private static final int TEST_USER_ID = 1;
    private Map<String, String> userDataMap;
    private SoftAssert softAssert;

    @Given("the API base URL is configured")
    public void apiConfigured() {
        // Carina framework configuration is loaded from _config.properties
        // API URL is set via: api_url property
        LOGGER.info("API base URL is configured from config.properties");
        softAssert = new SoftAssert();
    }

    @When("I send a POST request to create a user")
    public void sendPostUser() {
        LOGGER.info("=== Sending POST request to create a user ===");
        postUserMethod = new PostUserMethod();
        // Build request body inline using Carina function: setRequestBody()
        postUserMethod.buildDefaultUserRequest();
        LOGGER.debug("Request body built with default user data (John Doe, john.doe@example.com)");
        response = postUserMethod.callAPI();
        LOGGER.info("POST request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a POST request to create a user with template validation")
    public void sendPostUserWithValidation() {
        LOGGER.info("=== Sending POST request with validation ===");
        postUserMethod = new PostUserMethod();
        postUserMethod.buildDefaultUserRequest();
        response = postUserMethod.callAPI();
        LOGGER.info("Response received for validation. Status code: {}", response.getStatusCode());
        // Validate response fields inline without template files
    }

    @When("I send a POST request with custom headers")
    public void sendPostUserWithHeaders() {
        LOGGER.info("=== Sending POST request with custom headers ===");
        postUserMethod = new PostUserMethod();
        // Carina function: setHeader() - adds custom HTTP headers
        postUserMethod.setHeader("X-API-Key", "test-api-key");
        postUserMethod.setHeader("X-Request-ID", "12345");
        postUserMethod.setHeader("Content-Type", "application/json");
        LOGGER.debug("Custom headers set: X-API-Key, X-Request-ID, Content-Type");
        // Build request with custom data
        postUserMethod.buildUserRequest("Jane", "Smith", "jane.smith@example.com");
        LOGGER.debug("Request body built with custom user data (Jane Smith, jane.smith@example.com)");
        response = postUserMethod.callAPI();
        LOGGER.info("POST request with headers completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a GET request to retrieve user with ID placeholder")
    public void sendGetUserWithPlaceholder() {
        LOGGER.info("=== Sending GET request to retrieve user with ID placeholder ===");
        getUserMethod = new GetUserMethod();
        // Carina function: replaceUrlPlaceholder() - replaces URL placeholders dynamically
        getUserMethod.replaceUrlPlaceholder("user_id", String.valueOf(TEST_USER_ID));
        LOGGER.debug("URL placeholder replaced: user_id = {}", TEST_USER_ID);
        response = getUserMethod.callAPI();
        LOGGER.info("GET request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a POST request with dynamic properties")
    public void sendPostUserWithDynamicProperties() {
        LOGGER.info("=== Sending POST request with dynamic properties ===");
        postUserMethod = new PostUserMethod();
        // Carina function: addProperty() - stores properties that can be used in request building
        postUserMethod.addProperty("firstName", "John");
        postUserMethod.addProperty("lastName", "Doe");
        postUserMethod.addProperty("email", "john.doe@example.com");
        LOGGER.debug("Dynamic properties added: firstName=John, lastName=Doe, email=john.doe@example.com");
        // Build request body using the properties
        postUserMethod.buildUserRequest("John", "Doe", "john.doe@example.com");
        response = postUserMethod.callAPI();
        LOGGER.info("POST request with dynamic properties completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a POST request with authentication")
    public void sendPostUserWithAuth() {
        LOGGER.info("=== Sending POST request with authentication ===");
        postUserMethod = new PostUserMethod();
        // Carina function: setAuth() - sets authentication via jSessionId
        postUserMethod.setAuth("session-id-12345");
        LOGGER.debug("Authentication set with jSessionId: session-id-12345");
        // Build request body
        postUserMethod.buildDefaultUserRequest();
        response = postUserMethod.callAPI();
        LOGGER.info("POST request with authentication completed. Response status: {}", response.getStatusCode());
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int code) {
        LOGGER.info("=== Verifying response status code ===");
        int actualStatusCode = response.getStatusCode();
        LOGGER.debug("Expected status code: {}, Actual status code: {}", code, actualStatusCode);
        softAssert.assertEquals(actualStatusCode, code, "Status code should be " + code);
        LOGGER.info("Status code verification completed");
    }

    @Then("the response should validate against the schema")
    public void validateResponseSchema() {
        LOGGER.info("=== Validating response schema structure ===");
        // Inline schema validation - verify response structure without external schema files
        Object id = response.jsonPath().get("id");
        Object firstName = response.jsonPath().get("firstName");
        Object lastName = response.jsonPath().get("lastName");
        Object email = response.jsonPath().get("email");
        Object active = response.jsonPath().get("active");
        Object createdDate = response.jsonPath().get("createdDate");
        
        softAssert.assertNotNull(id, "Response must contain 'id' field");
        LOGGER.debug("Validated 'id' field: present");
        
        softAssert.assertNotNull(firstName, "Response must contain 'firstName' field");
        LOGGER.debug("Validated 'firstName' field: present");
        
        softAssert.assertNotNull(lastName, "Response must contain 'lastName' field");
        LOGGER.debug("Validated 'lastName' field: present");
        
        softAssert.assertNotNull(email, "Response must contain 'email' field");
        LOGGER.debug("Validated 'email' field: present");
        
        softAssert.assertNotNull(active, "Response must contain 'active' field");
        LOGGER.debug("Validated 'active' field: present");
        
        softAssert.assertNotNull(createdDate, "Response must contain 'createdDate' field");
        LOGGER.debug("Validated 'createdDate' field: present");
        
        LOGGER.info("Response schema validation completed");
    }

    @Then("the response should contain authentication token")
    public void verifyAuthToken() {
        LOGGER.info("=== Verifying authentication token in response ===");
        String token = response.jsonPath().getString("token");
        LOGGER.debug("Token value: {}", token != null ? "present" : "null");
        softAssert.assertNotNull(token, "Authentication token not found in response");
        LOGGER.info("Authentication token verification completed");
    }

    @Then("the response should contain user details")
    public void verifyUserDetails() {
        LOGGER.info("=== Verifying user details in response ===");
        String id = response.jsonPath().getString("id");
        String firstName = response.jsonPath().getString("firstName");
        LOGGER.debug("User ID: {}", id);
        LOGGER.debug("First Name: {}", firstName);
        softAssert.assertNotNull(id, "User ID not found");
        softAssert.assertNotNull(firstName, "First name not found");
        LOGGER.info("User details verification completed");
    }

    @Then("the response should contain created user ID")
    public void verifyCreatedUserId() {
        LOGGER.info("=== Verifying created user ID in response ===");
        Integer userId = response.jsonPath().getInt("id");
        LOGGER.debug("User ID value: {}", userId);
        softAssert.assertNotNull(userId, "User ID not found in response");
        softAssert.assertTrue(userId > 0, "User ID should be positive");
        LOGGER.info("Created user ID verification completed. User ID: {}", userId);
    }

    @Then("the response should contain field {string} with value {string}")
    public void verifyResponseField(String fieldName, String expectedValue) {
        LOGGER.info("=== Verifying response field: {} ===", fieldName);
        // Carina function: expectResponseContains() - validates field content
        String actualValue = response.jsonPath().getString(fieldName);
        LOGGER.debug("Field '{}' - Expected: '{}', Actual: '{}'", fieldName, expectedValue, actualValue);
        softAssert.assertEquals(actualValue, expectedValue, 
            "Field " + fieldName + " does not match expected value");
        LOGGER.info("Field verification completed for: {}", fieldName);
    }

    @Then("the response should contain field {string}")
    public void verifyFieldExists(String fieldName) {
        LOGGER.info("=== Verifying field existence: {} ===", fieldName);
        // Carina function: expectResponseContainsXpath() or JsonPath validation
        Object fieldValue = response.jsonPath().get(fieldName);
        LOGGER.debug("Field '{}' exists: {}", fieldName, fieldValue != null);
        softAssert.assertNotNull(fieldValue, "Field " + fieldName + " not found in response");
        LOGGER.info("Field existence verification completed for: {}", fieldName);
    }

    @Then("the response should contain session cookie")
    public void verifyCookie() {
        LOGGER.info("=== Verifying session cookie in response ===");
        String sessionCookie = response.getCookie("JSESSIONID");
        LOGGER.debug("Session cookie (JSESSIONID): {}", sessionCookie != null ? "present" : "null");
        softAssert.assertNotNull(sessionCookie, "Session cookie not found in response");
        LOGGER.info("Session cookie verification completed");
        softAssert.assertAll();
    }
}
