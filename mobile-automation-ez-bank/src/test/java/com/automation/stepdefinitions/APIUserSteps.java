package com.automation.stepdefinitions;

import com.automation.api.*;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zebrunner.carina.core.IAbstractTest;
import java.lang.invoke.MethodHandles;

public class APIUserSteps implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Response response;
    private PostUserMethod postPetMethod;
    private GetUserMethod getPetMethod;
    private PutPetMethod putPetMethod;
    private DeletePetMethod deletePetMethod;
    private BatchCreatePetsMethod batchCreateMethod;
    private SoftAssert softAssert;
    private static final long TEST_PET_ID = 1001;

    @Given("the API base URL is configured")
    public void apiConfigured() {
        // Carina framework configuration is loaded from _config.properties
        // Using Swagger Petstore API: https://petstore.swagger.io/v2
        LOGGER.info("API configured to use Swagger Petstore: https://petstore.swagger.io/v2");
        softAssert = new SoftAssert();
    }

    @When("I send a POST request to create a pet")
    public void sendPostPet() {
        LOGGER.info("=== Sending POST request to create a pet ===");
        postPetMethod = new PostUserMethod();
        // Carina function: setRequestBody() - sets request body directly
        postPetMethod.buildDefaultPetRequest();
        LOGGER.debug("Request body built with default pet data (Fluffy)");
        response = postPetMethod.callAPI();
        LOGGER.info("POST request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a GET request to retrieve pet with ID {string}")
    public void sendGetPetWithId(String petIdStr) {
        LOGGER.info("=== Sending GET request to retrieve pet with ID: {} ===", petIdStr);
        getPetMethod = new GetUserMethod();
        long petId = Long.parseLong(petIdStr);
        // Carina function: replaceUrlPlaceholder() - dynamically replaces URL placeholders
        getPetMethod.replaceUrlPlaceholder("pet_id", petIdStr);
        LOGGER.debug("URL placeholder replaced: pet_id = {}", petId);
        response = getPetMethod.callAPI();
        LOGGER.info("GET request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a PUT request to update pet with ID {string}")
    public void sendUpdatePetWithId(String petIdStr) {
        LOGGER.info("=== Sending PUT request to update pet with ID: {} ===", petIdStr);
        putPetMethod = new PutPetMethod();
        long petId = Long.parseLong(petIdStr);
        // Carina function: setRequestBody() - sets request body with updated data
        putPetMethod.buildUpdatePetRequest(petId, "Fluffy Updated", "sold");
        LOGGER.debug("Request body built to update pet {} to 'sold' status", petId);
        response = putPetMethod.callAPI();
        LOGGER.info("PUT request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a DELETE request to remove pet with ID {string}")
    public void sendDeletePetWithId(String petIdStr) {
        LOGGER.info("=== Sending DELETE request to remove pet with ID: {} ===", petIdStr);
        deletePetMethod = new DeletePetMethod();
        // Carina function: replaceUrlPlaceholder() - sets URL parameter for deletion
        deletePetMethod.replaceUrlPlaceholder("pet_id", petIdStr);
        LOGGER.debug("Pet ID set for deletion: {}", petIdStr);
        response = deletePetMethod.callAPI();
        LOGGER.info("DELETE request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a batch POST request to create multiple pets")
    public void sendBatchCreatePets() {
        LOGGER.info("=== Sending batch POST request to create multiple pets ===");
        batchCreateMethod = new BatchCreatePetsMethod();
        // Carina function: setRequestBody() - handles array of objects for batch operation
        batchCreateMethod.buildDefaultBatchRequest();
        LOGGER.debug("Batch request body built for 3 pets");
        response = batchCreateMethod.callAPI();
        LOGGER.info("Batch POST request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a POST request to create a pet with status {string}")
    public void sendPostPetWithStatus(String status) {
        LOGGER.info("=== Sending POST request to create a pet with status: {} ===", status);
        postPetMethod = new PostUserMethod();
        postPetMethod.buildPetRequest(TEST_PET_ID, "TestPet", status);
        LOGGER.debug("Request body built for pet with status: {}", status);
        response = postPetMethod.callAPI();
        LOGGER.info("POST request completed. Response status: {}", response.getStatusCode());
    }

    @When("I send a PUT request to update pet status to {string}")
    public void sendUpdatePetStatus(String newStatus) {
        LOGGER.info("=== Sending PUT request to update pet status to: {} ===", newStatus);
        putPetMethod = new PutPetMethod();
        putPetMethod.buildUpdatePetRequest(TEST_PET_ID, "TestPet", newStatus);
        LOGGER.debug("Request body built to update pet status to: {}", newStatus);
        response = putPetMethod.callAPI();
        LOGGER.info("PUT request completed. Response status: {}", response.getStatusCode());
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int code) {
        LOGGER.info("=== Verifying response status code ===");
        int actualStatusCode = response.getStatusCode();
        LOGGER.debug("Expected status code: {}, Actual status code: {}", code, actualStatusCode);
        softAssert.assertEquals(actualStatusCode, code, "Status code should be " + code);
        LOGGER.info("Status code verification completed");
    }

    @Then("the response should contain field {string} with value {string}")
    public void verifyResponseField(String fieldName, String expectedValue) {
        LOGGER.info("=== Verifying response field: {} ===", fieldName);
        // Carina function: jsonPath() - validates field content using JSON path
        String actualValue = response.jsonPath().getString(fieldName);
        LOGGER.debug("Field '{}' - Expected: '{}', Actual: '{}'", fieldName, expectedValue, actualValue);
        softAssert.assertEquals(actualValue, expectedValue, 
            "Field " + fieldName + " does not match expected value");
        LOGGER.info("Field verification completed for: {}", fieldName);
    }

    @Then("the response should contain pet details")
    public void verifyPetDetails() {
        LOGGER.info("=== Verifying pet details in response ===");
        // Carina function: jsonPath() - extracts and validates multiple fields
        Long petId = response.jsonPath().getLong("id");
        String petName = response.jsonPath().getString("name");
        String petStatus = response.jsonPath().getString("status");
        
        LOGGER.debug("Pet ID: {}", petId);
        LOGGER.debug("Pet Name: {}", petName);
        LOGGER.debug("Pet Status: {}", petStatus);
        
        softAssert.assertNotNull(petId, "Pet ID not found");
        softAssert.assertNotNull(petName, "Pet name not found");
        softAssert.assertNotNull(petStatus, "Pet status not found");
        LOGGER.info("Pet details verification completed");
    }

    @Then("the response should contain updated pet information")
    public void verifyUpdatedPetInfo() {
        LOGGER.info("=== Verifying updated pet information in response ===");
        Long petId = response.jsonPath().getLong("id");
        String status = response.jsonPath().getString("status");
        
        LOGGER.debug("Updated Pet ID: {}", petId);
        LOGGER.debug("Updated Pet Status: {}", status);
        
        softAssert.assertNotNull(petId, "Updated pet ID not found");
        softAssert.assertEquals(status, "sold", "Pet status should be 'sold' after update");
        LOGGER.info("Updated pet information verification completed");
    }

    @Then("the response should indicate successful batch operation")
    public void verifyBatchSuccess() {
        LOGGER.info("=== Verifying batch operation success ===");
        // For batch operations, response should be OK (200)
        int statusCode = response.getStatusCode();
        LOGGER.debug("Batch operation status code: {}", statusCode);
        softAssert.assertEquals(statusCode, 200, "Batch operation should return 200 OK");
        LOGGER.info("Batch operation success verification completed");
    }

    @Then("the pet status should be updated to {string}")
    public void verifyPetStatusUpdate(String expectedStatus) {
        LOGGER.info("=== Verifying pet status updated to: {} ===", expectedStatus);
        String actualStatus = response.jsonPath().getString("status");
        LOGGER.debug("Expected status: {}, Actual status: {}", expectedStatus, actualStatus);
        softAssert.assertEquals(actualStatus, expectedStatus, "Pet status should be " + expectedStatus);
        LOGGER.info("Pet status update verification completed");
        softAssert.assertAll();
    }
}
