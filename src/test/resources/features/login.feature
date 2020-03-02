Feature: Login Testing

  Background:
    Given I visit login page

  Scenario: Wrong Login
    When I enter wrong details
    And I Select Pharmacy Login Location
    Then Fail Login
    And Close browser

  Scenario: Successful Login
    When I enter right details
    And I Select Pharmacy Login Location
    Then Pass Login
    And Close browser

  Scenario Outline:
    When I enter "<username>" username
    And I enter "<password>" password
    And I Select Pharmacy Login Location
    Then Evaluate Login "<status>"
    And Close browser
    Examples:
      | username| password | status |
      | admin   | wrongPas | false  |
      | wrongUs | Admin123 | false  |
      | wrongUs | wrongPas | false  |
      | admin   | Admin123 | true   |

  Scenario: Log out
    When I enter right details
    And I Select Pharmacy Login Location
    And Pass Login
    And I log out
    Then Is logged out
    And Close browser
