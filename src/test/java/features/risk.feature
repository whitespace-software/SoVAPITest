@dev

  Feature: test the /risk endpoint
    Scenario: get list of risks
      Given a token exists for "ram"
      Then the risk GET endpoint returns a list