Feature: Validate Message API Endpoints


Scenario: Retrieve Messages
  Given The Endpoint "https://automationintesting.online/message/"
  When  The Customer Sends a GET Request
  Then  The Response status code should be 200
  And   The Response should contain messages
  And   The Response should match the JSON schema



  Scenario Outline: Create a message with valid input
    Given The Endpoint "https://automationintesting.online/message/"
    When  The customer sends a POST request with the following details:
      """
  {
    "name": "<name>",
    "email": "<email>",
    "phone": "<phone>",
    "subject": "<subject>",
    "description": "<description>"
  }
  """
    Then The Response status code should be 201
    And  The Response should contain a valid "messageid"


  Examples:
    | name       | email                    | phone        | subject         | description                                   |
    | Dharshini  | dharshini.jc@outlook.com | 328056850665 | Booking enquiry | Booking Enquiry for the upcoming Room checkIN |
    | PraveenRaj | praveenraj@outlook.com   | 329688098991 | Early Checkin   | Enquiry for the early Checkin                 |




  Scenario Outline: Fail to create a message with invalid data
    Given The Endpoint "https://automationintesting.online/message/"
    When  The customer sends a POST request with the invalid details:
     """
  {
    "name": "<name>",
    "email": "<email>",
    "phone": "<phone>",
    "subject": "<subject>",
    "description": "<description>"
  }
  """
    Then The Response status code should be <statusCode>
    And  The response should contain an error message "<errorMessage>"
    Examples:
      | name              | email                    | phone        | subject                       | description                    | statusCode    | errorMessage                                         |
      |                   | dharshini.jc@outlook.com | 324973541715 | Enquiry for the Hotel Booking | Enquiry for Room Availability  | 400           | Name may not be blank                                |
      | dharshini.jc      |                          | 324973541715 | Enquiry for the Hotel Booking | Enquiry for Room Availability  | 400           | Email may not be blank                               |
      | dharshini.jc      | dharshini.jc@outlook.com | 32497        | Enquiry for the Hotel Booking | Enquiry for Room Availability  | 400           | Phone must be between 11 and 21 characters.          |
      | dharshini.jc      | dharshini.jc@outlook.com | 324973541715 | Enq                           | Enquiry for Room Availability  | 400           | Subject must be between 5 and 100 characters.        |
      | dharshini.jc      | dharshini.jc@outlook.com | 324973541715 | Enquiry for the Hotel Booking | Enquiry                        | 400           | Message must be between 20 and 2000 characters.        |
      | dharshini.jc      | dj@.com                  | 324973541715 | Enquiry for the Hotel Booking | Enquiry for Room Availability  | 400           | must be a well-formed email address                               |
