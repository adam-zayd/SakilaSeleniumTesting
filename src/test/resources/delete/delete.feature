Feature: Delete from Specific Page

  Scenario Outline: Delete confirmation alert
    Given the user is on a specific page for "<entity>/16"
    When the user clicks on the delete button
    Then an alert pops up for confirmation
    Examples:
      | entity       |
      | actors       |
      | films        |
      | streams      |
      | categories   |

  Scenario Outline: Accepting delete confirmation
    Given the user is on a specific page for "<entity>/16"
    And the user clicks on the delete button
    When the user accepts the delete confirmation
    Then the URL should become one ending in "<entity>"
    And the "<entity>" should not display
    Examples:
      | entity       |
      | actors       |
      | films        |
      | streams      |
      | categories   |

  Scenario Outline: Cancelling from delete confirmation
    Given the user is on a specific page for "<entity>/16"
    And the user clicks on the delete button
    When the user cancels the delete confirmation
    Then the URL should stay the same, as ending in "<entity>/16"
    And the "<entity>" should still be visible
    Examples:
      | entity       |
      | actors       |
      | films        |
      | streams      |
      | categories   |
