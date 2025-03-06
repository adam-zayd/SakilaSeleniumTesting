#Feature:
#  Scenario Outline: Create links
#    Given the user is on the "<url>" page
#    When the user clicks the CREATE link
#    Then the page redirects to one with a url ending in "<url>/create"
#    Examples:
#      | url          |
#      | films        |
#      | actors       |
#      | streams      |
#      | categories   |
#
#    Scenario Outline: Creating valid entity
#      Given the user is on the "<entity>" create page
#      When the user submits a valid create "<entity>" form
#      Then a "success" pops up
#      And the page redirects to one with a url ending in "<entity>"
#      And the created can be found on the "<entity>" page
#      Examples:
#        | entity       |
#        | actors       |
#        | films        |
#        | streams      |
#        | categories   |
#
#  Scenario Outline: Creating valid entity with allowed empty fields
#    Given the user is on the "<entity>" create page
#    When the user submits a valid create "<entity>" form with permitted empty fields
#    Then a "success" pops up
#    And the page redirects to one with a url ending in "<entity>"
#    And the created with valid empty fields can be found on the "<entity>" page
#    Examples:
#      | entity       |
#      | actors       |
#        | films        |
#        | streams      |
#        | categories   |
#
##  Should test all empty fields in isolation. for completeness
#  Scenario Outline: Creating invalid entity with null fields
#    Given the user is on the "<entity>" create page
#    When the user submits a create "<entity>" form with invalid empty fields
#    Then a message pops up with a warning
#    Examples:
#      | entity       |
#      | actors       |
#        | films        |
#        | streams      |
#        | categories   |
#
#  #  Should test for all fields staying same. for completeness
#  Scenario Outline: Creating invalid entity with invalid IDs
#    Given the user is on the "<entity>" create page
#    When the user submits a create "<entity>" form with invalid ids in a field
#    Then a "id" pops up
#    And the "<entity>" stays the same
#    And the user does not lose their "<entity>" "invalidId" information
#    Examples:
#      | entity       |
#      | actors       |
#        | films        |
#        | streams      |
#        | categories   |
#
#  #Should test for all types of invalid entries. for completeness.
#  Scenario Outline: Creating invalid entity with prohibited entries
#    Given the user is on the "<entity>" create page
#    When the user submits a create "<entity>" form with a prohibited entry
#    Then a "<alert>" pops up
#    And the "<entity>" stays the same
#    And the user does not lose their "<entity>" "invalidEntry" information
#    Examples:
#      | entity       | alert       |
#      | actors       | firstnamelong        |
#        | films        | invalidyear |
#        | streams      | invalidcost |
##
#  Scenario Outline: Cancelling creation
#    Given the user is on the "<entity>" create page
#    When the user clicks the cancel button
#    Then a "cancel" pops up
#    Examples:
#      | entity       |
#      | actors       |
#        | films        |
#        | streams      |
#        | categories   |
#
#  Scenario Outline: Cancelling creation
#    Given the user is on the "<entity>" create page
#    And the user clicks the cancel button
#    When the user "confirms" cancellation
#    Then the page redirects to one with a url ending in "<entity>"
#    Examples:
#      | entity       |
#      | actors       |
#        | films        |
#        | streams      |
#        | categories   |
