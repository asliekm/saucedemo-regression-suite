Feature: Product Listing and Cart Functionality

  Background:
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    Then I should see the "products" page

  Rule: Cart should reflect added and removed items correctly

    Scenario Outline: Add and remove single product from cart
      When I add the product "<product>" to the cart
      Then the cart icon should show "<afterAdd>"
      When I remove the product "<product>" from the cart
      Then the cart icon should show "<afterRemove>"

      Examples:
        | product                 | afterAdd | afterRemove |
        | Sauce Labs Backpack     | 1        | 0           |
        | Sauce Labs Bike Light   | 1        | 0           |
        | Sauce Labs Bolt T-Shirt | 1        | 0           |


