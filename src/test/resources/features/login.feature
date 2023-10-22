@All
Feature: User login with valid account

  @PositiveLogin
  Scenario: Success login
    Given The user accesses the website.
    And The user enters their valid username.
    And The user enters their valid password.
    When The user clicks the Login button to log in.
    Then User verify login result

  @NegativeLogin
  Scenario: Failed login - invalid username
    Given The user accesses the website.
    And The user enters their empty username.
    And The user enters their empty password.
    When The user clicks the Login button to log in.
    Then User verify failed login result