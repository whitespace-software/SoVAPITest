@dev

  Feature: test the /risk endpoint
    Scenario: get list of risks
      Given a token exists for "ram"
      Then the risk GET endpoint returns a list

    Scenario: add a new risk
      Given a token exists for "ram"
      And be verbose
      And create a newrisk payload for "Test"
      Then calling the risk POST endpoint returns a valid id