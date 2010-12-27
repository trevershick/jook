Feature: Landing
	In order to see what's available to me
  	as a web user
  	I want to view the railinc tab on my landing page

  Background:
	Given I am on the jooktest home page
		
  Scenario: View Tabs
    Then I should see the Jook Tab 

  Scenario: The Jook Tab Expands
	Then I should see the Jook Tab
    When I click the Jook Tab
    Then I should see a Drawer

    