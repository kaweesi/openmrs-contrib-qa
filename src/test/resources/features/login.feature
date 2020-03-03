Feature: Login Testing

  Background:
    Given User visits login page

  Scenario: Wrong Login
    When User enters wrong details
    And User Selects Pharmacy Login Location
    Then System Fails Login
    And System Closes browser

  Scenario: Successful Login
    When User enters right details
    And User Selects Pharmacy Login Location
    Then System Passes Login
    And System Closes browser

  Scenario Outline:
    When User enters "<username>" username
    And User enters "<password>" password
    And User Selects Pharmacy Login Location
    Then System Evaluates Login "<status>"
    And System Closes browser
    Examples:
      | username| password | status |
      | admin   | wrongPas | false  |
      | wrongUs | Admin123 | false  |
      | wrongUs | wrongPas | false  |
      | admin   | Admin123 | true   |

  Scenario: Log out
    When User enters right details
    And User Selects Pharmacy Login Location
    And System Passes Login
    And User logs out
    Then User Is logged out
    And System Closes browser
