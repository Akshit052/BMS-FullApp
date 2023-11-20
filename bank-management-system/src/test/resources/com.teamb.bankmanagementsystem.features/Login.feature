Feature: Customer Login

  Scenario: Valid Customer Authentication
    Given a customer with ID "CS1150966" and password "password"
    When I authenticate the customer
    Then I should get a customer object

