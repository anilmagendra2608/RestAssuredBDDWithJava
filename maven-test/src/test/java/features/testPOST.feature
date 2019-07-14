Feature: Test POST operation using RestAssured

This feature verifies all Rest-Assured POST operation
AUT is website from where user can get the information detail about the movie booking information

Scenario: Check the Movie Details:

  Given The APIs is Running "http://cmapi.bananaappscenter.com/"
  When User performs a POST request to "api/MovieBooking/MovieBooking"  with below details
  | Mov_ID               | 3 |
  | Location_ID          | 5 |
 
  And perform the POST request
  Then The Response code should be 200
  And I should see json response with pairs on the filtered "Msg" filter  
  | Message              | Success Moviebooking Details    |
  | StatusCode           | 200                             |
  | isError              | false                           | 
  | isSuccess            | true                            |