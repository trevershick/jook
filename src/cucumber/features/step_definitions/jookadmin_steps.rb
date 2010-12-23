
Given /^that I have logged in as "([^"]*)"$/i do |arg1|
  visit "/sso/logout.do"
  select arg1, :from => 'sso.userId'
  click_button "Login"
end

Then /^i should see the home page$/i do
  page.should have_content("signed in as")
end

When /^i click the "([^"]*)" link$/i do |arg1|
  find_link(arg1).click
end

Then /^i should see a "([^"]*)" link$/i do |arg1|
  page.should have_link(arg1)
end

Then /^i should be able to see a table of providers$/i do
  page.should have_content("Integration Links")
  page.should have_css("table.results")
end

Then /^i should see "([^"]*)" at the top$/i do |arg1|
  find("h1").should have_content(arg1)
end

Given /^i am on the "([^"]*)" page$/i do |arg1|
  if arg1 == "Provider List"
    visit "/jook/main/secure/jook/providers"
  else
    raise Exception.new("Unknown Page #{arg1}")
  end
  find("h1").should have_content(arg1)
end


Then /^i should see the "([^"]*)" form$/i do |arg1|
  find("form").find("h1").should have_content(arg1)
end


When /^i click on the "([^"]*)" button$/i do |arg1|
  find_button(arg1).click
end


Then /^I should see an error$/i do
  page.should have_css(".noticeError")
end

When /^i enter "([^"]*)" in the "([^"]*)" field$/i do |value, fieldname|
  @thefield = all("label").detect { |l| (l.text =~ /#{fieldname}/i).nil? == false }
  if @thefield.nil? then
    raise Exception.new("Couldn't find field '#{fieldname}'")
  end
  fill_in @thefield[:for], :with=>value
end

Then /^I (can|cannot)? see "([^"]*)" in the table$/i do |cancannot, value|
  @expected_count = 1
  if cancannot == "cannot" then
    @expected_count = 0
  end
  print "Expected count for #{value} is #{@expected_count}"
  page.should have_xpath("//table//*[contains(text(), '#{value}')]", :count => @expected_count)
end

When /^I delete "([^"]*)"$/i do |value|
  page.evaluate_script('window.confirm = function() { return true; }') 
  within(:xpath, "//tr[.//*[contains(text(), '#{value}')]]") do
    find(:xpath, ".//a[contains(@href,'delete')]").click
  end
end
