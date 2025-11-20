Feature: YouTube video search
  As a YouTube user
  I want to search for a video by text
  So that I can validate the results returned by the application

  @youtube
  Scenario: Search for "Spiderman No way Home" and verify more than one result is displayed
    Given the user has the YouTube application opened
    When the user searches for the video "Spiderman No way Home"
    Then the user should see more than one result in the results list
