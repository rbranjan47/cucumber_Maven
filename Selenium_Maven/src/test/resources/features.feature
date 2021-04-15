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
Feature: Search the gmail in google and login into it

  @SmokeScenario
  Scenario: Search and Login into the Gmail
    Given Open the chrome browser
    And enter the gmail in the Search bar and click on search button
    When user enters clicks on gmail first link
    And clicks on sigin button
    And user enters valid email address and password
    Then user will redirect to home page
    And user is able to check its mails

  @SmokeScenarioOutline
  Scenario Outline: Search in the browser and Login into the Gmail
    Given Open the chrome <browser>
    And enter the gmail in the Search bar and click on <search> button
    When user enters <clicks> on gmail first link
    And clicks on <signin> button
    And user enters valid <username> and <password>
    Then user will redirect to <homepage>
    And user is able to check its mails

    Examples: 
      | browser       | search             | clicks  | signin  | username                | password  | homepage |
      | Google chrome | imastartesting.com | success | success | testuserxyz54@gmail.com | Test1234@ | Success  |
