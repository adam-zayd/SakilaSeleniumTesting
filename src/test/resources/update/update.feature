Feature:
  Scenario Outline: Update links
    Given the user is on the specific "<url>" page
    When the user clicks the UPDATE link
    Then the page redirects to a url ending in "<url>/16/update"
    Examples:
      | url          |
      | films        |
      | actors       |
      | streams      |
      | categories   |

    Scenario Outline: Checking pre-loaded data
    Given the user is on the specific "<entity>" page
      And the user clicks the UPDATE link
    Then the "<entity>" fields pre-load with the existing data from the database
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |

  Scenario Outline: Updating to valid entity
    Given the user is on the "<entity>/16" update page
    When the user submits a valid update form
    Then an update "success" pops up
    And the page redirects to a url ending in "<entity>/16"
    And the change can be found on the "<entity>" page
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |

  Scenario Outline: Creating valid entity with allowed empty fields
    Given the user is on the "<entity>/16" update page
    When the user submits a valid update "<entity>" form with permitted empty fields
    Then an update "success" pops up
    And the page redirects to a url ending in "<entity>/16"
    And the change with valid empty fields can be found on the specific page
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |

#  Should test for both first and last name being empty. for completeness
  Scenario Outline: Creating invalid entity with null fields
    Given the user is on the "<entity>/16" update page
    When the user submits an update "<entity>" form with invalid empty fields
    Then an update message pops up with a warning
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |

  Scenario Outline: Creating invalid entity with invalid IDs
    Given the user is on the "<entity>/16" update page
    When the user submits an update "<entity>" form with invalid ids in a field
    Then an update "id" pops up
    And the url for the "<entity>/16" stays the same
    And the user does not lose their updated "<entity>" information
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |

#  Should test for all types of invalid entries. for completeness.
  Scenario Outline: Creating invalid entity with prohibited entries
    Given the user is on the "<entity>/16" update page
    When the user submits an update "<entity>" form with a prohibited entry
    Then an update "<alert>" pops up
    And the url for the "<entity>/16" stays the same
    Examples:
      | entity       | alert         |
      | actors       | firstnamelong |
        | films        | invalidyear |
        | streams      | invalidcost |
#
  Scenario Outline: Cancelling creation
    Given the user is on the "<entity>/16" update page
    When the user clicks the cancel update button
    Then an update "cancel" pops up
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |
#
  Scenario Outline: Cancelling creation
    Given the user is on the "<entity>/16" update page
    And the user clicks the cancel update button
    When the user confirms cancellation
    Then the page redirects to a url ending in "<entity>/16"
    Examples:
      | entity       |
      | actors       |
        | films        |
        | streams      |
        | categories   |
