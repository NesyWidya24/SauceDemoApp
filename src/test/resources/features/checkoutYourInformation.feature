@All
Feature: Filling Information during Checkout.

  @PositiveInputInformation
  Scenario: Success fill information during checkout
    Given The user has successfully logged in.
    And The user has added the selected item to the cart.
    And The user is on the cart page.
    When The user clicks the Checkout button.
    And The user enters their first name.
    And The user enters their last name.
    And The user enters the postal code.
    And The user clicks the Continue button.
    Then Shipping and payment information has been entered.

  @NegativeInputInformation
  Scenario: Failed fill information during checkout
    Given The user has successfully logged in.
    And The user has added the selected item to the cart.
    And The user is on the cart page.
    When The user clicks the Checkout button.
    And The user enters their first name.
    And The user does not enter their last name.
    And The user enters the postal code.
    And The user clicks the Continue button.
    Then The system does not allow the user to proceed to the next step in the checkout process.
    And An error message is displayed indicating that the last name is required.