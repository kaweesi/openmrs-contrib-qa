Feature: Home Dashboard

  Background:
    Given I visit login page

  Scenario: Visiting home page
    When I enter right details
    And I Select Pharmacy Login Location
    And Pass Login
    And I visit home page
    Then I should be on the Home page with all app links
    And Close browser

  Scenario: Auto directed to Home page
    When I enter right details
    And I Select Pharmacy Login Location
    And Pass Login
    Then I should be on the Home page with all app links
    And Close browser