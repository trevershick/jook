



Given /^I have deleted the "([^"]*)" news story$/i do |story_title|
	Given "that I have logged in as \"news_adm\""
	find_link("News").click
	Given "I delete \"xyz\""
end

Given /^I have added the "([^"]*)" news story$/i do |story_title|
	Given "that I have logged in as \"news_adm\""
		
	find_link("News").click
	find_link("New News Item...").click
	fill_in("title", :with=>story_title)
	fill_in("launchDate", :with=>"2010-01-01")
	fill_in("description", :with=>"yay!")
	check("published")
	click_button("Save")
	Then "I should see not see an error"
end

Then /^I should see "([^"]*)"$/i do |content|
	page.should have_content(content)
end

Then /^I should see not see an error$/i do
  page.should_not have_css(".noticeError")
end