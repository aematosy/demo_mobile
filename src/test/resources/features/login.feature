Feature: Login functionality
  As a user
  I want to login to the application
  So that I can access my account

  Background:
    Given User is on the products screen

  @android @loginandroid @ios
  Scenario: Successful login on Android
    When the user opens the menu
    And the user selects Log In
    Then the user should see the login screen
    And the user logs in with username "bod@example.com" and password "10203040"
    Then the Products screen is displayed
    And the menu shows the option Log Out

