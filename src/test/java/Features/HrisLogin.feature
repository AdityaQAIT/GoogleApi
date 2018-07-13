Feature: Hris Login

Scenario: Login with valid credentials
Given i have opened hris link
When i enter valid credentials
Then time sheet should be displayed




Scenario: login with invalid credentials
Given i have opened hris link
When i enter invalid credentials
Then Error message should be displayed