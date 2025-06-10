@regression
Feature: Checkout Overview - Order Summary Validations

  Background:
    Given I am logged in as a standard user

  Rule: Order summary calculations

    @regression @order-summary
    Scenario Outline: The checkout overview calculations should be correct for various carts
      When I add the product "<product1>" to the cart
      And I add the product "<product2>" to the cart
      And I go to the checkout overview page
      Then The item total should match the sum of product data
      And The tax should be calculated correctly
      And The total price should match the sum of product data and tax

      Examples:
        | product1                | product2                  |
        | Sauce Labs Bike Light   | Sauce Labs Backpack       |
        | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket  |

  Rule: Order summary details

    @regression @order-summary
    Scenario: The correct products are listed in the order summary
      When I add the product "Sauce Labs Bike Light" to the cart
      And I go to the checkout overview page
      Then I should see "Sauce Labs Bike Light" in the product summary

    @regression @order-summary
    Scenario: The payment information should be correct
      When I add the product "Sauce Labs Bike Light" to the cart
      And I go to the checkout overview page
      Then The payment information should be "SauceCard #31337"

    @regression @order-summary
    Scenario: The shipping information should be correct
      When I add the product "Sauce Labs Bike Light" to the cart
      And I go to the checkout overview page
      Then The shipping information should be "Free Pony Express Delivery!"

  Rule: Completing the order

    @regression @order-summary
    Scenario: User should be able to complete the order
      When I add the product "Sauce Labs Bike Light" to the cart
      And I go to the checkout overview page
      And I click the "Finish" button
      Then I should see the "Checkout: Complete" page
      And I should see a confirmation message
