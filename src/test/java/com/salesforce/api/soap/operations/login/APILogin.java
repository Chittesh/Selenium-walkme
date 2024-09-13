package com.salesforce.api.soap.operations.login;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.testng.Assert;

import com.salesforce.api.soap.SalesForceSoapAPI;
import com.salesforce.data.Constants;
import com.salesforce.utilities.XMLTools;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class APILogin extends SalesForceSoapAPI {

    private static String LOWER_ENV_LOGIN_URL = "https://test.salesforce.com/services/Soap/u/40.0";
    private static String PROD_ENV_LOGIN_URL = "https://login.salesforce.com/services/Soap/u/40.0";

    /**
     * Constructor method that gets the login request xml template and loads in memory
     * @throws Exception 
     */
    public APILogin() throws Exception {

        File xml = new File(this.getClass().getResource(Constants.XML_FILES + "salesforcequery/LoginRequest.xml").getPath());

        setOperationName("Login");
        setRequestDocument(XMLTools.makeXMLDocument(xml));
        removeComments();
        removeWhiteSpace();
    }

   

    
    
  

     
    public void getOAuthToken(String role) {
    	//Capture credential file path
    	ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
		//ResourceBundle enrUrls = ResourceBundle.getBundle("EnvironmentURLs");

    	Response response = null;
		String cId = null, cSecret = null;
		String url;
		String[] splitRole = role.split("_");
		String apiRole = ("API_" + splitRole[splitRole.length - 1]).toUpperCase();
        String username = userCredentialRepo.getString(apiRole);

        // Grab the password for the api user role
        String passwordDecode = userCredentialRepo.getString(apiRole + "_PASSWORD");
        
        byte[] decodedBytes = Base64.getDecoder().decode(passwordDecode);
		String pwd = new String(decodedBytes);
        
        //String pwd = Base64Coder.decodeString(passwordDecode);
        
        if (role.toLowerCase().contains("prod")) {
             url="https://login.salesforce.com";
        } else {
            url = "https://test.salesforce.com";
        }

		RestAssured.baseURI = url;

		
		cId = userCredentialRepo.getString("CID_" + splitRole[2]);
		cSecret = userCredentialRepo.getString("CSECRET_" + splitRole[2]);
		
		response = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type","password").formParam("client_secret", cSecret).formParam("client_id", cId)
				.formParam("username", username).formParam("password", pwd).post("/services/oauth2/token");
		System.out.println(response.asString());
		//TestReporter.log(response.asString());
		JsonPath jsonPathEvaluator = response.jsonPath();
		sessionID = jsonPathEvaluator.get("access_token");
		authenticatedURL=jsonPathEvaluator.get("instance_url");
		//TestReporter.log("access token/session Id : " + sessionID);
		
    }   
    
    
    
    
    

    

    // ******Getters & Setters for the login request******//
    public void setUsername(String value) throws Exception {
        setRequestNodeValueByXPath("/Envelope/Body/login/username", value);
    }

    public void setPassword(String value) throws Exception {
        setRequestNodeValueByXPath("/Envelope/Body/login/password", value);
    }

    // ******Getters from the login response *******//
    // To get the session id from login response
    public String getSessionId() throws Exception {
        return getResponseNodeValueByXPath("/Envelope/Body/loginResponse/result/sessionId");
    }

    // To get the Server url from login
    public String getURL() throws Exception {
        return getResponseNodeValueByXPath("/Envelope/Body/loginResponse/result/serverUrl");
    }

}
