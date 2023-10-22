@All
Feature: User add items to the shopping cart.

  @PositiveAddItem
  Scenario: Success add items to the shopping cart.
    Given The user has opened the login page.
    And The user has entered a valid username.
    And The user has entered a valid password.
    When The user clicks the Login button and successfully logs in.
    When The user select item and clicks the Add to Cart button
    Then The added item successfully appears in the cart.

  @NegativeRemoveItem
  Scenario: Failed remove items to the shopping cart.
    Given The user has opened the login page.
    And The user has entered a valid username.
    And The user has entered a valid password.
    When The user clicks the Login button and successfully logs in.
    When The user select item and clicks the Add to Cart button
    And The user has selected an item to remove.
    When User clicks Remove with no response.
    Then The item is not removed from the cart.