package resourses;

public enum APIresources {
	
	AddplaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIresources(String resource){
		this.resource = resource;
	}
	
	public String getResources() {
		return resource;
	}
	
}
