Feature: News
	In order to see what news is availabe blah,blah,blah
  	as a web user
  	I want to view the railinc tab on my landing page

  Scenario: View News
  	Given I have added the "xyz" news story
    	And I am on the JookTest home page
    Then I should see the Jook Tab
   	When I click the Jook Tab
   		And I click the "News" Drawer 
   	Then I should see "xyz"
   	# Cleanup
  	Given I have deleted the "xyz" news story
   	

    