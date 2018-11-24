Feature: Customer should be able to login

  @smoke @desktop
  Scenario: Customer can not log in if USERNAME is incorrect
    Given the user launches github login page
    When the user tries to login as "a"
    Then the user should see access restricted page
