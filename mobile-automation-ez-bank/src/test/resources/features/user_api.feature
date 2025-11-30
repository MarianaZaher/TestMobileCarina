@api-ez
Feature: User API Testing
  As a QA Engineer
  I want to test User API endpoints
  So that I can ensure the API works correctly

  # Basic Status Code Validation
  Scenario: Create a new user with status code validation
    Given the API base URL is configured
    When I send a POST request to create a user
    Then the response status code should be 201

  # Request/Response Validation using Templates
  Scenario: Create user and validate response structure
    Given the API base URL is configured
    When I send a POST request to create a user with template validation
    Then the response should validate against the schema
    And the response status code should be 201

  # Header Management
  Scenario: Create user with custom headers
    Given the API base URL is configured
    When I send a POST request with custom headers
    Then the response status code should be 201
    And the response should contain authentication token

  # URL Parameter Substitution
  Scenario: Retrieve user with URL parameter replacement
    Given the API base URL is configured
    When I send a GET request to retrieve user with ID placeholder
    Then the response status code should be 200
    And the response should contain user details

  # Dynamic Property Replacement
  Scenario: Create user with dynamic property values
    Given the API base URL is configured
    When I send a POST request with dynamic properties
    Then the response status code should be 201
    And the response should contain created user ID

  # Multiple Assertions on Response Content
  Scenario: Validate multiple response fields
    Given the API base URL is configured
    When I send a POST request to create a user
    Then the response status code should be 201
    And the response should contain field "firstName" with value "John"
    And the response should contain field "lastName" with value "Doe"
    And the response should contain field "email"

  # Cookies and Authentication
  Scenario: Create user with authentication
    Given the API base URL is configured
    When I send a POST request with authentication
    Then the response status code should be 201
    And the response should contain session cookie
    