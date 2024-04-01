Feature: Testing USD rates against multiple currencies API

  Scenario:1. Verify API call is successful and returns valid price
    Given User makes a GET request to the API
    Then the API call is successful
    And the response contains valid price

  Scenario:2. Verify status code and status returned by the API response
    Given User makes a GET request to the API
    Then the status code is 200 and "SUCCESS"

  Scenario:3. Verify USD price against AED is in range 3.6 - 3.7
   Given User makes a GET request to the API
    Then the USD price against AED is in range 3.6 - 3.7

  Scenario:4. Verify API response time is not less than 3 seconds from current time
   Given User makes a GET request to the API
    Then the API response time is not less than 3 seconds from current time

  Scenario:5. Verify 162 currency pairs are returned by the API
    Given User makes a GET request to the API
    Then 162 currency pairs are returned by the API

  Scenario:6. Verify API response matches the JSON schema
    Given User makes a GET request to the API
    Then the API response matches the JSON schema
