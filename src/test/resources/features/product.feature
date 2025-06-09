@product
Feature: Product Listing and Cart Functionality

  Background:
    Given I am logged in as a standard user
    Then I should see the "products" page

  Rule: Add and remove functionality in product page

    Scenario Outline: Add and remove single product from cart
      When I add the product "<product>" to the cart
      Then the cart icon should show "<afterAdd>"
      When I remove "<product>" from my cart via the products page
      Then the cart icon should show "<afterRemove>"

      Examples:
        | product                 | afterAdd | afterRemove |
        | Sauce Labs Backpack     | 1        | 0           |
        | Sauce Labs Bike Light   | 1        | 0           |
        | Sauce Labs Bolt T-Shirt | 1        | 0           |


