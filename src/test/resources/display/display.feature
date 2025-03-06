#Feature: Display
#  Scenario Outline: On the All* page, all functionalities are displayed
#    Given the user is on "<url>" page
#    Then a page title is displayed
#    And all entities are displayed on the page
#    And a create and search button are displayed
#    Examples:
#      | url       |
#      | actors       |
#      | films        |
#      | streams      |
#      | categories   |
#
#  Scenario Outline: Selecting
#    Given the user is on "<url>" page
#    When the user selects an individual
#    Then the individual is selected
#    Examples:
#      | url       |
#      | actors       |
#      | films        |
#      | streams      |
#      | categories   |
#
#  Scenario Outline: Resetting selected
#    Given the user is on "<url>" page
#    When the user selects an individual
#    And the user clicks reset
#    Then all selected are deselected
#    Examples:
#      | url        |
#      | actors     |
#      | films      |
#      | streams    |
#      | categories |
#
#  Scenario Outline: Deleting selected
#    Given the user is on "<url>" page
#    When the user selects an individual
#    And the user clicks delete
#    Then the selecteds are deleted
#    Examples:
#      | url        |
#      | actors     |
#      | films      |
#      | streams    |
#      | categories |
#
#
#
