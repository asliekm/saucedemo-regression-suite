@cart
Feature: Cart Functionality

  Background:
    Given I am logged in as a standard user

  Rule: Adding products to the cart

    Scenario Outline: User can add a product to the cart and see correct details
      When I add the product "<product>" to the cart
      And I click the "cart" button
      Then I should see "<product>" in the cart
      And The price for "<product>" should be displayed correctly
      And The "Remove" button should be visible for "<product>"

      Examples:
        | product                  |
        | Sauce Labs Bike Light    |
        | Sauce Labs Fleece Jacket |

  Rule: Removing products from the cart

    Scenario Outline: User can remove a product from the cart
      When I add the product "<product>" to the cart
      And I click the "cart" button
      And I remove the product "<product>" from the cart
      Then The cart should be empty

      Examples:
        | product                  |
        | Sauce Labs Bike Light    |
        | Sauce Labs Fleece Jacket |

  Rule: Cart state with no products

    Scenario: Cart is empty if no product is added
      When I click the "cart" button
      Then The cart should be empty

