package com.salesforce.utilities;

/**
 * Holds all the SQL queries used during testing of salesforce
 *
 *
 */
public class SalesForceQueries {

	public static String getUserId(String profileName) {
		return "SELECT Id FROM User WHERE Name = '" + profileName + "'";

	}

	public static String getOpportunityNumber(String opportunityID) {
		return "SELECT Opportunity_Num__c FROM Opportunity WHERE Id = '" + opportunityID + "'";
	}

	public static String queryOfficeId(String officeName) {
		String query = "SELECT Id FROM User_Organization__c WHERE Name = '" + officeName + "' ";
		return query;
	}

	public static String queryProdcut2Id(String opco, String division) {
		String query = "SELECT Id FROM Product2 WHERE OpCo__c = '" + opco + "' AND Division_Name__c = '" + division
				+ "' AND IsActive = true LIMIT 1";
		return query;
	}

	public static String getServicesESFId(String orderId) {
		return "SELECT Id FROM Employee_Status_Form__c  WHERE Submittal__c  = '" + orderId + "'";
	}

}