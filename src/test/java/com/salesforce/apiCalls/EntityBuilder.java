package com.salesforce.apiCalls;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;

public class EntityBuilder {
	ApiAuthorizationToken authorizationToken;
	ApiManager apiManager;
	EntityUtilities entityUtilities;	
	private static final String accountRoute = "/services/data/v56.0/sobjects/Account";
	private static final String contactRoute =  "/services/data/v56.0/sobjects/Contact";
	private static final String opportunityRoute =  "/services/data/v56.0/sobjects/Opportunity";
	private static final String orderRoute =  "/services/data/v56.0/sobjects/Order";
	String token;
	
	public EntityBuilder() {
		authorizationToken = new ApiAuthorizationToken();
		apiManager = new ApiManager();
		entityUtilities = new EntityUtilities();		
	}
	
	public String CreateAccountClient() throws JSONException {		
		String query = entityUtilities.getAccountClientQuery();	
		this.setToken();
		return apiManager.createPostRequest(this.token, query, accountRoute);
	}
	
	public String CreateContactClient(String accountClientdata) {		
		String query = entityUtilities.getContactClientQuery(accountClientdata);
		return apiManager.createPostRequest(this.token, query, contactRoute);
	}
	
	public String CreateAccountTalent() throws JSONException {		
		String query = entityUtilities.getAccountTalentQuery();
		return apiManager.createPostRequest(this.token, query, accountRoute);
	}
	
	public String CreateContactTalent(String accountTalentData) throws JSONException {		
		String query = entityUtilities.getContactTalentQuery(accountTalentData);
		return apiManager.createPostRequest(this.token, query, contactRoute);
	}
	
	public String CreateOpportunity(String accountClientdata, String contactClientData) {
		String query = entityUtilities.getOpportunityQuery(accountClientdata, contactClientData);
		return apiManager.createPostRequest(this.token, query, opportunityRoute);
	}
	
	public String updateOpportunityFlag(String opportunityId) {
		String query = entityUtilities.updateOpportunityQuery(opportunityId);
		return apiManager.createPostRequest(this.token, query, opportunityRoute);
	}
	
	public String CreateSubmittal(String opportunityId, String accountTalentId, String contactTalentId) {
		String query = entityUtilities.getSubmittalQuery(opportunityId, accountTalentId, contactTalentId);
		return apiManager.createPostRequest(this.token, query, orderRoute);
	}
	
	private void setToken() throws JSONException {
		if(token == null) {
			String tokenKey = this.authorizationToken.getToken();
			JSONObject json = new JSONObject(tokenKey);
			this.token = json.getString("access_token");
		}
		else {
			
		}
	}
	
	public String getDataFromJson(String json) throws JSONException {
		String key = "id";
		JSONObject body = new JSONObject(json);
		return body.getString(key);
	}
}
