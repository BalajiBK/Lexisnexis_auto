Feature: LexisNexis Careers

  Background:
    Given User is on home page "https://risk.lexisnexis.co.uk/"


  Scenario: User should be able to search for a job
    When User navigates to the Careers Page using the About Us link
    And User searches for "automation tester" in the search box using the search all jobs
    Then User should be able to see atleast 1 jobs