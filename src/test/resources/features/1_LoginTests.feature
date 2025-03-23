Feature: Login tests

  @modul:Login_negative
  Scenario Outline: Should be an error message displayed when entering a non-existent username
    Given Login page is displayed
    And I enter username "<username>"
    And I enter password "<password>"
    And I click sign in button
    Then I expect error message is showing

    Examples:
      |username   | password |
      |ist     | secret_sauce  |

  @modul:Login_positive
  Scenario Outline:  Should be able to log in when entering the correct username and password
    Given Login page is displayed
    And I enter username "<username>"
    And I enter password "<password>"
    And I click sign in button
    Then I expect home page is showing

    Examples:
      |username   | password |
      |standard_user     | secret_sauce  |
