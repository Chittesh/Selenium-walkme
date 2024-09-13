package com.salesforce.servicedata;

public class CompensationWorkServiceData {
	
	private String placementType = "";
	private String schedule = "";
	private String workplaceType = "";
	private String desiredShift = "";
	
	public CompensationWorkServiceData(){
	}
	
	public String getPlacementType() {
		return placementType;
	}
	public void setPlacementType(String placementType) {
		this.placementType = placementType;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getWorkplaceType() {
		return workplaceType;
	}
	public void setWorkplaceType(String workplaceType) {
		this.workplaceType = workplaceType;
	}
	public String getDesiredShift() {
		return desiredShift;
	}
	public void setDesiredShift(String desiredShift) {
		this.desiredShift = desiredShift;
	}

}
