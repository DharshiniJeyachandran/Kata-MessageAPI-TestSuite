Feature: Validate Message API Endpoints


Scenario: Retrieve Messages
  Given The Endpoint "https://automationintesting.online/message/"
  When  The Customer Sends a Get Request
  Then   The Response status code should be 200
  And    The Response should contain messages