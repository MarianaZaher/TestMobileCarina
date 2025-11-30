@mobile @android
Feature: Home Page Testing
  As a mobile tester
  I want to test the home page
  So that I can verify all elements are working

  Background:
    Given I launch the mobile app
    And I am on the home page

  Scenario: Verify app logo is displayed
    Then the app logo should be displayed

  Scenario: Open products cart
    When I click on the products cart
    Then the cart should open
