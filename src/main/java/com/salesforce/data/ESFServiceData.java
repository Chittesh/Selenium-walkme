package com.salesforce.data;

public class ESFServiceData {

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoOfPos() {
		return noOfPos;
	}

	public void setNoOfPos(String noOfPos) {
		this.noOfPos = noOfPos;
	}

	public String getIsTGS() {
		return isTGS;
	}

	public void setIsTGS(String isTGS) {
		this.isTGS = isTGS;
	}

	public String getLapFlag() {
		return lapFlag;
	}

	public void setLapFlag(String lapFlag) {
		this.lapFlag = lapFlag;
	}

	private String role = "";
	private String status = "";
	private String noOfPos = "";
	private String isTGS = "";
	private String lapFlag = "";

	public ESFServiceData() {

	}

}
