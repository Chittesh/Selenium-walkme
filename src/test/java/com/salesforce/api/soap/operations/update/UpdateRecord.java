package com.salesforce.api.soap.operations.update;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.salesforce.utilities.DateTimeConversion;


public class UpdateRecord {
	private Logger logger = LogManager.getLogger(UpdateRecord.class);
	private String role;
	public String activityDueDateOpen = DateTimeConversion.getDaysOut("3", "yyyy-MM-dd") + "T16:00:00.000Z";
	public String activityDueDatePast = DateTimeConversion.getDaysOut("3", "yyyy-MM-dd") + "T16:00:00.000Z";
	public String eventStartDateOpen = DateTimeConversion.getDaysOut("3", "yyyy-MM-dd") + "T16:00:00.000Z";
	public String eventEndDateOpen = DateTimeConversion.getDaysOut("3", "yyyy-MM-dd") + "T16:00:00.000Z";
	public String eventStartDatePast = DateTimeConversion.getDaysOut("-3", "yyyy-MM-dd") + "T16:00:00.000Z";;
	public String emailLabel = "EMAIL";
	public String mobileLabel = "MOBILE";
	public String emailAndMobile = "EMAIL_AND_MOBILE";
	public String yesValue = "Yes";
	public String noValue = "No";
	public String eventEndDatePast = DateTimeConversion.getDaysOut("-3", "yyyy-MM-dd") + "T16:00:00.000Z";;

	/**
	 * Constructor that accepts the role parameter that you are logging into
	 * salesforce API with
	 *
	 * @param role
	 */
	public UpdateRecord(String role) {
		this.role = role;
	}

	/**
	 * Generic method to create an sobject in salesforce API. Takes int he sobject
	 * name, a hashmap of the xml node names and values you want to update in the
	 * template create request xml
	 *
	 * @param sobjectName
	 * @param hashmap
	 * @return the ID of the sobject you are creating
	 * @throws Exception
	 */
	public String updateSObject(String sobjectName, HashMap<String, String> hashmap) throws Exception {

		Update update;
		switch (sobjectName.toLowerCase()) {
		case "updatejobposting":
			update = new Update(role, "UpdateEventRequest");
			break;
		case "update_contact_consentpref":
			update = new Update(role, "UpdateContactRequest");
			break;
		case "updatereqopportunity":
			update = new Update(role, "UpdateReqOpportunityRequest");
			break;
		case "updateorder":
			update = new Update(role, "UpdateOrderRequest");
			break;
		case "updateesf":
			update = new Update(role, "UpdateESFRequest");
			break;
		case "updateesftoprojectreview":
			update = new Update(role, "UpdateESFToProjectReview");
			break;
		case "updatproduct":
			update = new Update(role, "UpdateProductRequest");
			break;
		case "esfupdateapi":
			update = new Update(role, "UpdateESFAPIRequest");
			break;
		case "updatevendorapi":
			update = new Update(role, "UpdateVendorAPI");
			break;
		case "updateesftopendingapproval":
			update = new Update(role, "UpdateESFToPendingApproval");
			break;
		case "updateordertonp":
			update = new Update(role, "UpdateOrderToNPRequest");
			break;
		case "updateesfvalue":
			update = new Update(role, "UpdateESFValue");
			break;
		case "updateopportunity":
			update = new Update(role, "UpdateOpportunity");
			break;
		case "updateopptyflagvalue":
			update = new Update(role, "UpdateOpptyFlagValue");
			break;
		case "updatewithpsprojinfo":
			update = new Update(role, "UpdateESFWithPSProjectInfo");
			break;
		case "updatemdmid":
			update = new Update(role, "UpdateTalentMDMId");
			break;
		case "updateopportunityaspertfa":
			update = new Update(role, "UpdateOpportunity2");
			break;
		case "updateuser":
			update = new Update(role, "UpdateOfficeCode");
			break;
		default:
			throw new Exception("SObject not available: " + sobjectName);
		}
		for (Map.Entry<String, String> entry : hashmap.entrySet()) {
			String fieldName = entry.getKey();
			String value = entry.getValue();
			if (!value.isEmpty()) {
				update.setSObjectNodeByNameAndValue(fieldName, value);
			}
		}

		update.sendRequest();
		Assert.assertTrue(update.getResponseStatusCode().equals("200"), "Verify the create request was successful");
		if (update.getSuccess().equals("false")) {
			throw new Exception("Create Account request not successful - error message: " + update.getErrorMessage());
		}
		return update.getID();
	}

	public String updateOpptyLAPFlag(String opptyId, String lapFlag) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Id", opptyId);
		hm.put("LAP_SPOC__c", lapFlag);

		return updateSObject("updateopptyflagvalue", hm);
	}

	public String updateTalentMDMId(String talentContactId) throws Exception {
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put("Id", talentContactId);
		hm1.put("MDM_ID__c", "01234566");
		return updateSObject("updatemdmid", hm1);
	}

	public String updateOrder(String orderId, String status) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Id", orderId);
		hm.put("Status", status);
		return updateSObject("updateorder", hm);
	}

}
