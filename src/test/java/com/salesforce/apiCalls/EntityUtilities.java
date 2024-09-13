package com.salesforce.apiCalls;

import java.util.Random;

import io.restassured.RestAssured;

public class EntityUtilities {
	
	public String getRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	private String getName(String name) {
		return name + this.getRandomString();
	}
	
	public String getAccountClientQuery() {
		return "{\r\n" + "\"Name\":\"" + this.getName("Conn_Test_AC_") + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqL\",\r\n" + "\"ShippingCity\":\"Hanover\",\r\n"
				+ "\"ShippingState\":\"MD\",\r\n" + "\"ShippingCountry\":\"United States\",\r\n"
				+ "\"ShippingStreet\":\"7312 Parkway Drive\",\r\n" + "\"ShippingPostalCode\":\"21076\"\r\n" + "}";		
	}
	
	public String getContactClientQuery(String accountClientId) {
		return "{\r\n" + "\"FirstName\":\"" + this.getName("Conn_Test_FCC_") + "\",\r\n" + "\"LastName\":\""
				+ this.getName("Conn_Test_LCC_") + "\",\r\n" + "\"Title\":\"Mister\",\r\n"
				+ "\"MailingStreet\":\"7312 Parkway Drive\",\r\n" + "\"MailingCity\":\"Hanover\",\r\n"
				+ "\"MailingState\":\"MD\",\r\n" + "\"MailingPostalCode\":\"21076\",\r\n"
				+ "\"MailingCountry\":\"USA\",\r\n" + "\"Email\":\"test@test.test\", \r\n"
				+ "\"MobilePhone\":\"1234567890\",\r\n" + "\"AccountId\":\"" + accountClientId + "\",\r\n"
				+ "\"RecordTypeId\":\"012U0000000DWoy\"\r\n" + "}";
	}
	
	public String getAccountTalentQuery() {
		return "{\r\n" + "\"Name\":\"" + this.getName("Conn_Test_AT_") + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqQAAW\",\r\n" + "\"Talent_Ownership__c\":\"TEK\"\r\n" + "}";	
	}
	
	public String getContactTalentQuery(String accountTalentId) {
		return "{\r\n" + "\"FirstName\":\"" + this.getName("Conn_Test_FCT_") + "\",\r\n" + "\"LastName\":\""
				+ this.getName("Conn_Test_LCT_") + "\",\r\n" + "\"Title\":\"Business Analyst\",\r\n"
				+ "\"MailingStreet\":\"7312 Parkway Drive South\",\r\n" + "\"MailingCity\":\"Hanover\",\r\n"
				+ "\"MailingState\":\"Maryland\",\r\n" + "\"MailingPostalCode\":\"21076\",\r\n"
				+ "\"MailingCountry\":\"USA\",\r\n" + "\"Email\":\"test@test.test\",\r\n"
				+ "\"MobilePhone\":\"1234567890\",\r\n" + "\"AccountId\":\"" + accountTalentId + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqVAAW\",\r\n" + "\"Talent_Country_Text__c\":\"United States\",\r\n"
				+ "\"MailingLatitude\":\"35.5393\",\r\n" + "\"MailingLongitude\":\"-82.518\"\r\n" + "}";
	}	
	
	public String getSubmittalQuery(String opportunityId, String accountTalentId,
			String contactTalentId) {
		return "{\r\n" + "\"RecordTypeId\":\"01224000000kOSEAA2\",\r\n" + "\"AccountId\":\"" + accountTalentId
				+ "\",\r\n" + "\"Status\":\"Linked\",\r\n" + "\"OpportunityId\":\"" + opportunityId + "\",\r\n"
				+ "\"ShipToContactId\":\"" + contactTalentId + "\",\r\n" + "\"EffectiveDate\":\"2023-03-17\"\r\n" + "}";
	}
	
	public String updateOpportunityQuery(String opportunityId) {
		return "{\r\n" + "\"Id\":\"" + opportunityId
				+ "\",\r\n" + "\"LAP_SPOC__c\":\"true\",\r\n" + "}";
	}
	
	public String getOpportunityQuery(String accountClientId, String contactClientId) {
		return "{\r\n" + "\"Name\":\"" + this.getName("Conn_Test_FCT_") + "\",\r\n" + "\"AccountId\":\"" + accountClientId
				+ "\",\r\n" + "\"RecordTypeId\":\"01224000000kMQ4\",\r\n" + "\"Currency__c\":\"USD - U.S Dollar\",\r\n"
				+ "\"Req_External_Job_Description__c\":\"test job description\",\r\n" + "\"Req_Hiring_Manager__c\":\""
				+ contactClientId + "\",\r\n" + "\"Req_Job_Title__c\":\"Automation Tester\",\r\n"
				+ "\"Req_Product__c\":\"Contract\",\r\n" + "\"OpCo__c\":\"TEKsystems, Inc.\",\r\n"
				+ "\"Req_Worksite_City__c\":\"Hanover\",\r\n" + "\"Req_Total_Positions__c\":\"1\",\r\n"
				+ "\"Req_Division__c\":\"Global Services\",\r\n" + "\"Req_Worksite_Country__c\":\"USA\",\r\n"
				+ "\"Req_Worksite_State__c\":\"Maryland\",\r\n"
				+ "\"Req_Worksite_Street__c\":\"7312 Parkway Drive\",\r\n"
				+ "\"Req_Worksite_Postal_Code__c\":\"21076\",\r\n" + "\"Req_Bill_Rate_Max__c\":\"100\",\r\n"
				+ "\"Req_Bill_Rate_Min__c\":\"75\",\r\n" + "\"Req_Job_Description__c\":\"test job description\",\r\n"
				+ "\"Req_Draft_Reason__c\":\" Qualifying\",\r\n" + "\"Req_Duration__c\":\"4.00\",\r\n"
				+ "\"Req_Rate_Frequency__c\":\"Hourly\",\r\n" + "\"Req_Duration_Unit__c\":\"Month(s)\",\r\n"
				+ "\"Req_Pay_Rate_Max__c\":\"50\",\r\n" + "\"Req_Pay_Rate_Min__c\":\"20\",\r\n"
				+ "\"Req_Red_Zone__c\":\"true\",\r\n"
				+ "\"EnterpriseReqSkills__c\":\"[{\\\"name\\\":\\\"java development\\\",\\\"favorite\\\":true},{\\\"name\\\":\\\"java\\\",\\\"favorite\\\":false}]\",\r\n"
				+ "\"Req_Skill_Details__c\":\"java,sql,apex\",\r\n" + "\"Req_Standard_Burden__c\":\"1.00\",\r\n"
				+ "\"Req_Internal_External_Customer__c\":\"whoIsIntrlExtnlCustm\",\r\n"
				+ "\"Organization_Office__c\":\"a1a1E000003anqRQAQ\",\r\n" + "\"StageName\":\"Draft\",\r\n"
				+ "\"Start_Date__c\":\"2023-02-17\",\r\n" + "\"Legacy_Product__c\":\"01t1E00000Rj7RMQAZ\",\r\n"
				+ "\"Req_TGS_Requirement__c\":\"Yes\",\r\n" + "\"Product__c\":\"Core Applications\",\r\n"
				+ "\"Internal_Hire__c\":\"No\",\r\n"
				+ "\"Practice_Engagement__c\":\"Business Operations Support (BOS)\",\r\n"
				+ "\"GlobalServices_Location__c\":\"Montreal Center\",		 \r\n"
				+ "\"Employment_Alignment__c\":\"COS: Contractor or C2C\",\r\n"
				+ "\"Final_Decision_Maker__c\":\"Client\",\r\n" + "\"Is_International_Travel_Required__c\":\"No\"\r\n"
				+ "}";
	}
	
}
