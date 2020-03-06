Feature: Home Dashboard

  Background:
    Given User visits login page
    When User enters right details
    And User Selects Pharmacy Login Location
    And System Passes Login
    
  Scenario: Visiting home page
    And User visits home page
    Then User should be on the Home page with all app links
    And System Closes browser

  Scenario: Auto directed to Home page
    Then User should be on the Home page with all app links
    And System Closes browser