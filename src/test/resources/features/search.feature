Feature: Booking Search
  Searching for different hotels

  Scenario: Search "Greenwood"
    Given User open search page
    When User input "GreenWood hotel"
    And Press 'Search'
    Then Name of hotel is "Greenwood Hotel" on page "1"
    And Hotel "Greenwood Hotel" should have rating "8.5"
