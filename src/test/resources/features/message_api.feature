Feature: Validate Message API Endpoints


Scenario: Retrieve Messages
  Given The Endpoint "https://automationintesting.online/message/"
  When  The Customer Sends a GET Request
  Then  The Response status code should be 200
  And   The Response should contain messages



  Scenario Outline: Create a message with valid input
    Given The Endpoint "https://automationintesting.online/message/"
    When  The customer sends a POST request with the following details:
      | name      | email               | phone         | subject        | description                |
      | <name>    | <email>             | <phone>       | <subject>      | <description>              |
    Then The Response status code should be 201

  Examples:
    | name       | email                    | phone        | subject         | description                             |
    | Dharshini  | dharshini.jc@outlook.com | 328056850665 | Booking enquiry | Booking Enquiry for the upcoming flight |
    | PraveenRaj | praveenraj@outlook.com   | 329688098991 | Early Checkin   | Enquiry for the early Checkin           |