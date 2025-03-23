Feature: Home tests

  Background: :
    Given Login page is displayed
    And I enter username and password
    And I click sign in button
    Then I expect home page is showing

  @modul:Home_Positive
  Scenario:Should be able to filter and find a product successfully
    And I select the filter for price from low to high
    And I search for a product
    Then I should find the product I was looking for

  @modul:Home_Negative
  Scenario: Should not be able to click on a non-existent product
    And I select the filter for price from low to high
    And I search for a non existing product
    Then I should cant find the product I was looking for


