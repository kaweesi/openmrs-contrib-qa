Feature: Find patient Dashboard

  Background:
    Given Cypress User visits login page
    When Cypress User enters right details
    And Cypress User Selects Pharmacy Login Location
    And Cypress System Passes Login
    
  Scenario: Find patient
    Then Cypress User should be on the Home page with all app links
    And Cypress User presses find patient
    Then Cypress User should be on the find patient page with all app links
    And Cypress System Closes browser