
When /^I click the Jook Tab$/ do
  find("a#jooklinksa").click
end

Then /^I should see the Jook Tab$/ do
  page.should have_selector('a#jooklinksa')
end

Then /^I should see a Drawer$/ do
  page.should have_css(".jooktrig")
end

Then /^I should see the "([^"]*)" Drawer$/ do |tabname|
  find_link(tabname).should be_visible
end

Then /^I should not see the "([^"]*)" Drawer$/ do |tabname|
  page.should have_no_link(tabname)
end
