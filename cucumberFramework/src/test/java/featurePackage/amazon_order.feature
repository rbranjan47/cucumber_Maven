#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#Scenario outline is used for Parameters, whenever user have to pass multiple value
Feature: User is making order

  Background: user is login into amazon
    | https://www.amazon.in/ |

    Given user navigate to login page
    When user enters valid email and password
    Then user will redirect into homepage

   
  Scenario Outline: Checking product order functionality in amazon
    When user enters <product> name
    And clicks on the search button to get the product
    And clicks on product and clicks on Add cart
    Then user should able to order from the cart

    Examples: 
      | product |
      | asus laptop |
