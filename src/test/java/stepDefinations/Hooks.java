package stepDefinations;

import java.io.IOException;

import com.fasterxml.jackson.databind.type.PlaceholderForType;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException 
	{
	
		StepDefinition step = new StepDefinition();
		if(StepDefinition.place_id==null) {
		step.add_place_payload("Rocky", "French", "Asia");
		step.user_calls_with_http_request("AddplaceAPI", "POST");
		step.verify_place_id_created_maps_to_using("Rocky", "getPlaceAPI");
	}
		
	}
}
