

Feature: Purchase the order fron Ecommerce website.

  I want to use this template for my feature file
  
  Background:
   Given I landed on Ecommerce page


  @Regression
  Scenario Outline: Positive case ofsubmitting the order.
    Given Logged with username <name> and password <passwprd>
    When I add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page 

    Examples:
      | name     				| passwprd 	|	productName  |
	  | christ12hym@gmail.com 	| Rose12**  |	ZARA COAT 3  |
       