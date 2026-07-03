

Feature: Error Validation.
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative case oflogin Application.
   Given I landed on Ecommerce page
    When Logged with username <name> and password <passwprd>
    Then "Incorrect email or password. message is displayed

    Examples:
      | name     				| passwprd 	|
	  | christ12hymm@gmail.com 	| Rose12**  |
       