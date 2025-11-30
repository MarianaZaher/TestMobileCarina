@mobile @login
Feature: User Login Testing
  As a user of EZ Bank mobile app
  I want to log in with my credentials
  So that I can access the application

  Background:
    Given I launch the EZ Bank mobile application

  Scenario: Verify login page is displayed
    Given I am on the login page
    Then the login page should contain the expected elements

  Scenario: Successful user login
    Given I am on the login page
    When I login with username "test_user" and password "password123"
    Then I should be logged in successfully
    And the home page should display all elements

  Scenario: Login and verify home page elements
    Given I am on the login page
    When I enter username "test_user"
    And I enter password "password123"
    And I click the login button
    Then I should be logged in successfully
    And I should see the app logo
    And the app title should be displayed

  Scenario: Navigate to products cart after login
    Given I am on the login page
    When I login with username "test_user" and password "password123"
    Then I should be logged in successfully
    When I click on the products cart
    Then the app should display the products cart
