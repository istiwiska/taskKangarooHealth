Feature: Cart tests

  Background: :
    Given Login page is displayed
    And I enter username and password
    And I click sign in button
    Then I expect home page is showing

  @modul:Cart_Positive
  Scenario:Should be able to checkout
    And I click add to cart for product
    Then I click icon cart
    And I click continue shopping button
    And I click add to cart for other product
    Then I click icon cart
    And I click checkout button
    And I type first name
    And I type last name
    And I type postal code
    And I click continue button
    And I click finish button
    Then I should see success message

  @modul:Cart_Negative
  Scenario:Should not be able to proceed checkout if the postal code is left empty
    And I click add to cart for product
    Then I click icon cart
    And I click continue shopping button
    And I click add to cart for other product
    Then I click icon cart
    And I click checkout button
    And I type first name
    And I type last name
    And I click continue button
    Then I should see error message



