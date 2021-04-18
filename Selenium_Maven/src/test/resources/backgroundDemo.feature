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

Feature: Checked links in orangehrm

#Given feature is taken care by the Background in the feature
  Background: user is logged in
  Given user is on login page of the orange hrm
  When user enters valid username and password
  And clicks on the login button
  Then user is navigated into home page
  
  Scenario: check logout link
    #Given user is in login page of orangehrm
    When user enters valid email address and password
    And clicks on login button
    Then user is navigated to homepage
    And logout button is present in the corner of the home page
    
  Scenario: verify quick launch toolbar is present
   #Given user is logged in 
   When user clicks on the dashboard link
   Then quick launch toolbar is diaplyed