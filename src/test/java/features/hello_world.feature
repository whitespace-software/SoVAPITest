@dev
Feature: Hello World


  Scenario: Prints a string
    And print "Hello World!"


  Scenario: Tests
    Given be Verbose
    And print "Scenario2"
    Then get SOVAPI version