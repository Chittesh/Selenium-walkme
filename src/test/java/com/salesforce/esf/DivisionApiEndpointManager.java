package com.salesforce.esf;

import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;

import com.salesforce.data.Constants;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DivisionApiEndpointManager {
	VenderAuthenticationTokenManager tokenManager;
	public DivisionApiEndpointManager() {
		this.tokenManager = new VenderAuthenticationTokenManager();
	}
	
	private String getEndpointUrl(String env) {
	    ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
	    return userCredentialRepo.getString(env.toUpperCase() + "_VENDOR_ODATA");
	}

	private String getDivisionApi(String env) throws Exception {
	    switch (env.toUpperCase()) {
	        case "TEST":
	            return "/allegis-testx-esfproducttempapi/v1/products";
	        case "LOAD":
	            return "/allegis-load-esfproducttempapi/v1/products";
	        default:
	            throw new Exception("env parameter incorrect value");
	    }
	}

	private Response getDivisionApiResponse(String oauthToken, String endpointUrl, String divisionApi, String companyID) {
	    return RestAssured.given()
	            .headers("Authorization", "Bearer " + oauthToken, "Accept", "application/json").relaxedHTTPSValidation()
	            .queryParam("CompanyID", companyID).queryParam("Concat", "Y").when()
	            .get(endpointUrl + divisionApi).then().extract().response();
	}

	private List<String> getAllDivisionDescriptions(Response response) throws JSONException {
	    JSONObject obj = new JSONObject(response.getBody().asString());
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    return jsonPathEvaluator.getList("Products.ProductDescription");
	}
	
	public List<String> getDivisionDetails(String env, String companyID) throws Exception {
	    System.out.println("Get Number of records displayed for Division API request");
	    String oauthToken = this.tokenManager.getSessionId(env);
	    String endpoint_url = getEndpointUrl(env);
	    String divisionApi = getDivisionApi(env);

	    Response response = getDivisionApiResponse(oauthToken, endpoint_url, divisionApi, companyID);
	    List<String> allDivisions = getAllDivisionDescriptions(response);

	    System.out.println("Verify API is returning list of Division details: " + allDivisions);
	    return allDivisions;
	}

	
}
