Feature: Landing
	In order to see what's available to me
  	as a web user
  	I want to view the railinc tab on my landing page

  Scenario: View Tabs
    Given I am on the home page
    Then I should see the Jook Tab 

  Scenario: The Jook Tab Expands
    Given I am on the home page
	Then I should see the Jook Tab
    When I click the Jook Tab
    Then I should see a Drawer

    