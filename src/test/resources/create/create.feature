Feature:
  Scenario Outline: Create links
    Given the user is on the "<url>" page
    When the user clicks the CREATE "<entity>" link
    Then the page redirects to one with a url ending in "<url>"
    Examples:
      | url          | entity    |
      | films        | Movie     |
      | actors       | Actor     |
      | streams      | Stream    |
      | categories   | Category  |