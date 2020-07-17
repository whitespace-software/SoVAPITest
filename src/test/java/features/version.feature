@dev
Feature: Test the Version Message

  Scenario: Check SOVAPI version
    Given Verbose Version
    And set environment
    Then check SOVAPI version