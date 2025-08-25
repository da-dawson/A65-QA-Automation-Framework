Feature: Login Functionality
  As a user
  I want to be able to log in and out of the application
  So that I can access and secure my account

  @smoke
  Scenario: Complete login and logout flow
    Given I am on the login page
    When I enter valid username and password
    And I click the login button
    Then I should be logged in successfully
    When I click the logout button
    Then I should be logged out successfully
