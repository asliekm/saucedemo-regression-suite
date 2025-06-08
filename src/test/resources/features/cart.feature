@regression
Feature: Cart Functionality

  Background:
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    Then I should see the "products" page

  Rule: Cart should reflect added and removed items correctly

    Scenario Outline: Checkout with various user data
      When I add the product "<product>" to the cart
      And I click the "cart" button
      Then I should see the "Cart" page
      When I click the "checkout" button
      Then I should see the "Checkout: Your Information" page

      Examples:
        | product                  |
        | Sauce Labs Bike Light    |
        | Sauce Labs Fleece Jacket |
