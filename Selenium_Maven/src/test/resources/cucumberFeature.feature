#Author: kumar.rabi@thinksys.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>

@Smoke
Feature: Title of your feature
  I want to use this template for my feature file

  @SmokeScenario
  Scenario: Login in the A-STAR
    Given Open the chrome browser 
    And search the website in the Search bar and click on search button
    When user enters clicks on Get Started
    And enters username and password
    And user clicks on login button
    Then user will redirect to main page
    And check more outcomes

  @SmokeScenarioOutline
  Scenario Outline: Login in the A-STAR
    Given Open the chrome <browser>
    And search the website in the Search bar and click on <search> button
    When user enters <clicks> on Get Started
    And enters <username> and <password>
    And user clicks on <login> button
    Then user will redirect to <main> page

    Examples: 
      | browser       | search             | clicks  | username  | password | login   | main     |
      | Google chrome | imastartesting.com | success | tokyo rio | 123456   | Success | Home_page|
