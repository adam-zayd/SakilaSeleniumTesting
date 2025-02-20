Feature: Navbar links

  Scenario Outline: Attempting to click the same page's navbar link
    Given the user is on the page with "<url>"
    When the user tries to click on the "<page>" navbar link
    Then the "<page>" link should be unclickable
    And the "<page>" link should be in a black text
    And the URL should stay as ending in "<url>"

    Examples:
      |  page                | url        |
      |  Home                | /          |
      |  Movies              | /films     |
      |  Actors              | /actors    |
      |  Categories          | /categories|
      |  Streams             | /streams   |



  Scenario Outline: outcomes of clicking navbar links
    Given the user is on the home page
    When the user clicks on the "<name>" navbar link
    Then the URL should become one ending in "<url>"

    Examples:
      |  name                | url        |
      |  Movies              | /films     |
      |  Actors              | /actors    |
      |  Categories          | /categories|
      |  Streams             | /streams   |