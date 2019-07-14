Feature: Test GET operation using RestAssured
This feature verifies all Rest-Assured GET operation
AUT is website from where user can get the information about the country postal address by passing just postal code value 
 
 Scenario: Get Country details
  
  Given The APIs are UP and Running "http://api.zippopotam.us/"
  When User performs a GET request to "IN/110001"  
  And perform the request
  Then The Response code should be 200
  And I should see json response with pairs on the filtered 
  
  | post code            | 110001       |
  | country              | India        |
  | country abbreviation | IN           | 