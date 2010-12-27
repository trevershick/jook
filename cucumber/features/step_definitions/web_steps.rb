
When /^I click the Jook Tab$/i do
  find("a#jooklinksa").click
end

Then /^I should see the Jook Tab$/i do
	find("a#jooklinksa")
end

Then /^I should see a Drawer$/i do
  page.should have_css(".jooktrig")
end

Then /^I should see the "([^"]*)" Drawer$/i do |tabname|
  find_link(tabname).should be_visible
end

Then /^I should not see the "([^"]*)" Drawer$/i do |tabname|
  page.should have_no_link(tabname)
end

When /^I click the "([^"]*)" Drawer$/i do |tabname|
  find_link(tabname).click
end

# ----------------------------------------------------------------
#
#	Link Stuff
#
# ----------------------------------------------------------------


When /^i click the "([^"]*)" link$/i do |arg1|
  find_link(arg1).click
end

Then /^i should see a "([^"]*)" link$/i do |arg1|
  page.should have_link(arg1)
end


# ----------------------------------------------------------------
#
#	Button Stuff
#
# ----------------------------------------------------------------

When /^i click on the "([^"]*)" button$/i do |arg1|
  find_button(arg1).click
end



# ----------------------------------------------------------------
#
#	SSO Stuff
#
# ----------------------------------------------------------------

Given /^that I have logged in as "([^"]*)"$/i do |arg1|
  visit "/sso/logout.do"
  select arg1, :from => 'sso.userId'
  click_button "Login"
end


Given /^I am on the (JookTest|Jook) home page$/i do |which|
  visit("/#{which.downcase}")
end





# ----------------------------------------------------------------
#
#	Form Stuff
#
# ----------------------------------------------------------------

When /^i enter "([^"]*)" in the "([^"]*)" field$/i do |value, fieldname|
  @thefield = all("label").detect { |l| (l.text =~ /#{fieldname}/i).nil? == false }
  if @thefield.nil? then
    raise Exception.new("Couldn't find field '#{fieldname}'")
  end
  fill_in @thefield[:for], :with=>value
end