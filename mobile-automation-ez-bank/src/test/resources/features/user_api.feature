@api-ez
Feature: Swagger Petstore API Testing
  As a QA Engineer
  I want to test Petstore API endpoints (GET, POST, PUT, DELETE, Batch)
  So that I can ensure CRUD operations work correctly

  # POST - Create a new pet
  Scenario: Create a new pet
    Given the API base URL is configured
    When I send a POST request to create a pet
    Then the response status code should be 200
    And the response should contain field "id" with value "1001"

  # GET - Retrieve a pet
  Scenario: Retrieve pet by ID
    Given the API base URL is configured
    When I send a GET request to retrieve pet with ID "1001"
    Then the response status code should be 200
    And the response should contain pet details

  # PUT - Update existing pet
  Scenario: Update an existing pet
    Given the API base URL is configured
    When I send a PUT request to update pet with ID "1001"
    Then the response status code should be 200
    And the response should contain updated pet information

  # DELETE - Remove a pet
  Scenario: Delete a pet
    Given the API base URL is configured
    When I send a DELETE request to remove pet with ID "1001"
    Then the response status code should be 200

  # Batch operation - Create multiple pets
  Scenario: Batch create multiple pets
    Given the API base URL is configured
    When I send a batch POST request to create multiple pets
    Then the response status code should be 200
    And the response should indicate successful batch operation

  # Validate pet status changes
  Scenario: Validate pet status transitions
    Given the API base URL is configured
    When I send a POST request to create a pet with status "available"
    And I send a PUT request to update pet status to "sold"
    Then the pet status should be updated to "sold"

  # Error handling - Get non-existent pet
  Scenario: Handle pet not found error
    Given the API base URL is configured
    When I send a GET request to retrieve pet with ID "99999"
    Then the response status code should be 404
  Scenario: Create user with authentication
    Given the API base URL is configured
    When I send a POST request with authentication
    Then the response status code should be 201
    And the response should contain session cookie
    