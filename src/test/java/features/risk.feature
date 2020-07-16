@dev

  Feature: test the /risk endpoint
    Scenario: get list of risks
      Given a token exists for "User1"
      Then the risk GET endpoint returns a list