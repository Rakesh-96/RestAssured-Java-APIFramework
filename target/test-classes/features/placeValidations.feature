Feature: Validating Place API's
@AddPlace
Scenario Outline:  Verify the add place functionality
     Given Add place payload  "<name>" "<language>" "<address>"
     When User calls "AddplaceAPI" with "POST" http request
     Then The API call success code with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And Verify place id created maps to "<name>" using "getPlaceAPI"
     
     
Examples: 
	|name 		    | language |address					  |
	|Frontline house|French-IN | 29, side layout, cohen 09|
@DeletePlace	
Scenario: Verify the delete place functionality
     Given delete place payload
     When User calls "DeletePlaceAPI" with "POST" http request
     Then The API call success code with status code 200
     And "status" in response body is "OK"