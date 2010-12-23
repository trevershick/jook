Given /^I am on the home page$/ do
  visit('/jooktest')
end

Given /^I have entered "([^"]*)" into the "([^"]*)" field$/ do |text, field|
  fill_in field, :with => text
end
