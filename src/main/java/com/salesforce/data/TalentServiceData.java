package com.salesforce.data;

public class TalentServiceData {
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailStreet() {
		return mailStreet;
	}
	public void setMailStreet(String mailStreet) {
		this.mailStreet = mailStreet;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	private String accountId = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String mailStreet = "";
	private String mobilePhone = "";
}
