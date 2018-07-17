Feature: Hris Login

@Hris_01
Scenario: Login with valid credentials
Given i have opened hris link
When i enter valid credentials
Then time sheet should be displayed



@Hris_02
Scenario: login with invalid credentials
Given i have opened hris link
When i enter invalid credentials
Then Error message should be displayed


@Hris_03
Scenario: login with blank password field
Given i have opened hris link
When enter only username and click signin
Then password become highlighted with red boundary 