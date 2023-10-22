@All
Feature: Sorting Product by Price Low

  @PriceSorting
  Scenario: Product Price Sorting
    Given The user has successfully logged in as User A.
    And The user chooses to filter the products.
    Then The products are successfully displayed in order from the lowest price to the highest price.

  @ErrorPriceFilter
  Scenario: Negative Test: Error When Applying Price Filter.
    Given The user has successfully logged in as User B.
    And The user chooses to filter the products.
    Then The user receives an error message indicating that the filter cannot be applied at this time.