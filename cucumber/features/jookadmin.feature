Feature: Provider Management
	In order to configure the Jook Providers
  	as an admin user
  	I want to manage the jook providers

	Background:
  		Given that I have logged in as "jook_adm"
	
	Scenario: Navigate to the Provider List
  		I should see the home page
  		When I click the "Jook" link
  		Then I should see a "Providers" link
  		When I click the "Providers" link
    	Then I should be able to see a table of providers
    	And I should see "Provider List" at the top

	Scenario: Provider Required Fields Validation
  		Given I am on the "Provider List" page
  		When I click the "New Provider" link
  		Then I should see the "New Provider" form
  		
  		When I click on the "Save" button
  		Then I should see an error
  		
	Scenario: Adding a Provider	
  		Given I am on the "Provider List" page
  		When I click the "New Provider" link
  		Then I should see the "New Provider" form
		
		When I enter "xyz" in the "Name" field
			And i enter "/jook/test1" in the "public url" field
			And i enter "/jook/secured/test1" in the "Secure URL" field   		
  			And I click on the "Save" button
  		Then I am on the "Provider List" page
  			And i can see "xyz" in the table
  		When I delete "xyz" 
    		Then I should see "Provider List" at the top
  			And i cannot see "xyz" in the table
  			
	
  		
	
	


    