Feature: Login Functionality

  Background:
    Given I am on the login page

  Rule: Valid users should be able to log in

    Example: Login with standard user
      When I enter username "standard_user" and password "secret_sauce"
      Then I should see the products page

  Rule: Invalid users should not be able to log in

    Example: Login with locked out user
      When I enter username "locked_out_user" and password "secret_sauce"
      Then I should see the following error message:
    """
    Epic sadface: Sorry, this user has been locked out.
    """

    Example: Login with wrong username and password
      When I enter username "wrong_user" and password "wrong_pass"
      Then I should see the following error message:
    """
    Epic sadface: Username and password do not match any user in this service
    """

    Example: Login with empty username
      When I enter username "" and password "secret_sauce"
      Then I should see the following error message:
        """
        Epic sadface: Username is required
        """

    Example: Login with empty password
      When I enter username "standard_user" and password ""
      Then I should see the following error message:
        """
        Epic sadface: Password is required
        """

