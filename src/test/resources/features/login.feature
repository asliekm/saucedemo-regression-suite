@login
Feature: Login Functionality

  Background:
    Given I am on the login page

  Rule: Valid users should be able to log in

    Example: Login with standard user
      When I enter username "standard_user" and password "secret_sauce"
      Then I should see the "products" page

  Rule: Invalid users should not be able to log in

    Example: Login with locked out user
      When I enter username "locked_out_user" and password "secret_sauce"
      Then I should see the "locked out user" error message

    Example: Login with wrong username and password
      When I enter username "wrong_user" and password "wrong_pass"
      Then I should see the "invalid credentials" error message

    Example: Login with empty username
      When I enter username "" and password "secret_sauce"
      Then I should see the "username required" error message

    Example: Login with empty password
      When I enter username "standard_user" and password ""
      Then I should see the "password required" error message
