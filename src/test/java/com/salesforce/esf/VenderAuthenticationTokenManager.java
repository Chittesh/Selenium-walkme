package com.salesforce.esf;

import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;

import com.salesforce.data.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VenderAuthenticationTokenManager {
	
	public String getSessionId(String env) throws JSONException {
	    String url = getVendorODataUrl(env);
	    String cId = getODataClientId(env);
	    String cSecret = getODataClientSecret(env);
	    String resource = getODataClientResource(env);

	    Response response = getAccessTokenResponse(url, cId, cSecret, resource);
	    String sessionId = extractSessionId(response);

	    System.out.println("access token/session Id : " + sessionId);
	    return sessionId;
	}

	private String getVendorODataUrl(String env) {
	    ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
	    return userCredentialRepo.getString(env.toUpperCase() + "_VENDOR_ODATA_URL");
	}

	private String getODataClientId(String env) {
	    ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
	    return userCredentialRepo.getString(env.toUpperCase() + "_ODATA_CLIENTID");
	}

	private String getODataClientSecret(String env) {
	    ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
	    return userCredentialRepo.getString(env.toUpperCase() + "_ODATA_CLIENT_SECRET");
	}

	private String getODataClientResource(String env) {
	    ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
	    return userCredentialRepo.getString(env.toUpperCase() + "_ODATA_CLIENT_RESOURCE");
	}

	private Response getAccessTokenResponse(String url, String cId, String cSecret, String resource) {
	    return RestAssured.given()
	            .header("Content-Type", "application/x-www-form-urlencoded")
	            .formParam("grant_type", "client_credentials")
	            .formParam("client_id", cId)
	            .formParam("client_secret", cSecret)
	            .formParam("resource", resource)
	            .post(url + "/allegiscloud.onmicrosoft.com/oauth2/token")
	            .then().extract().response();
	}

	private String extractSessionId(Response response) throws JSONException {
	    JSONObject obj = new JSONObject(response.getBody().asString());
	    return obj.getString("access_token");
	}
}
