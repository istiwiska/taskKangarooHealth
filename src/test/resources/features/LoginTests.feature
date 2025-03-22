Feature: Login tests

  @modul:Login
  Scenario Outline: Should be an error message displayed when an invalid email is entered or the password field is left blank
    Given Login page is displayed
    And I enter email "<email>"
    And Click sign in button
#    Then I expect error email and password blank will show

    Examples:
      |email   |
      |ist     |
