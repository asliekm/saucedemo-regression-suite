@regression
Feature: Checkout - Your Information Step

  Background:
    Given I am logged in as a standard user
    Then I should see the "products" page
    When I add the product "Sauce Labs Bike Light" to the cart
    And I click the "cart" button
    Then I should see the "Cart" page
    When I click the "Checkout" button
    Then I should see the "Checkout: Your Information" page

  Rule: Completing checkout information

    @regression
    Scenario: User completes checkout information with valid data
      When I fill in the checkout form with valid data
      And I click the "Continue" button
      Then I should see the "Checkout: Overview" page



