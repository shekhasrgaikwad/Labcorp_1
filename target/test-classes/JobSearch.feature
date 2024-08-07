Feature: Job Search

  Scenario: Verify job posting details
    Given I open the LabCorp url
    When I navigate to the Careers page
    And I search for a "Portal Developer, Service Now (Remote)" position
    And I select the first job from the results
    Then I verify the job details
    And I click Apply Now and verify the application page details
    And I return to the job search page
