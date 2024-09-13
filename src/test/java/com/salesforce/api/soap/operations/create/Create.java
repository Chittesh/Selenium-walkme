package com.salesforce.api.soap.operations.create;

import java.io.File;

import com.salesforce.api.soap.SalesForceSoapAPI;
import com.salesforce.api.soap.operations.login.APILogin;
import com.salesforce.data.Constants;
import com.salesforce.utilities.XMLTools;



public class Create extends SalesForceSoapAPI {

    // private String sessionID;
    // private String authenticatedURL;

    /**
     * Constructor method
     * This method sets up a template request xml using the xml file and goes ahead and sets
     * the authenticated URL, session ID, and request headers. The template request is now ready
     * for any create use
     * There are different templated create request xmls to choose from in the resources folder, specifiy which one you are using
     * with the xmlFileName parameter e.g. CreateContactRequest, CreateAccountRequest, etc
     *
     * @param role
     * @param xmlFileName
     * @throws Exception 
     */
	public Create(String role, String xmlFileName) throws Exception {
    	// first check to see if sessionID & URL has been set during the current test run.
		
		if (sessionID == null) {
            // Create instance of the login request class
			 APILogin login = new APILogin();
           // grab the oAuth token from the connected app
             login.getOAuthToken(role);
        }
        
        // use a setter to specify which soap operation we are using
        setOperationName("Create");
        // Based on what type of sobject you are creating, grab the template xml from resources folder
        File xml = new File(this.getClass().getResource(Constants.XML_FILES + "/salesforcequery/" + xmlFileName + ".xml").getPath());
       // System.out.println("Path for XML file:"+xml);
        // set the xml file in memory so we can add the dynamic values later
        setRequestDocument(XMLTools.makeXMLDocument(xml));
        // clean up xml
        removeComments();
        removeWhiteSpace();
        // set header on the request
        addRequestHeader("SoapAction", "Test");
        addRequestHeader("Content-Type", "text/xml");
        // set the session ID in the query request
        setSessionID(sessionID);
        // update the endpoint with the authenticated URL
        setServiceURL(authenticatedURL+soapURI);
    }

    // ******Setters for the Create request******//

    public void setSessionID(String value) {
        try {
			setRequestNodeValueByXPath("/Envelope/Header/SessionHeader/sessionId", value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void setSObjectNodeByNameAndValue(String nodeName, String value) {
        try {
			setRequestNodeValueByXPath("/Envelope/Body/create/sObjects/" + nodeName, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // *******Getters for the Create response******//

    /**
     * Grabs the ID node from the create response. This can be an account ID, a contact ID, opportunity ID, etc.
     * It is the same node for all three types
     *
     * @return
     * @throws Exception 
     */
    public String getID() throws Exception {
        return getResponseNodeValueByXPath("/Envelope/Body/createResponse/result/id");

    }

    /**
     * Gets the result from the success node. Even if the request was successful there might be an issue with the role, data, etc
     * Will give a true value if the account creation was successful and false if not
     * @throws Exception 
     *
     */
    public String getSuccess() throws Exception {
        return getResponseNodeValueByXPath("/Envelope/Body/createResponse/result/success");
    }

    /**
     * If the success is false, then the API will give an error message detailing what the problem is.
     * Error's only occur during a create(), merge(), process(), update(), upsert(), delete(), or undelete() call.
     *
     * @return
     * @throws Exception 
     */
    public String getErrorMessage() throws Exception {
        return getResponseNodeValueByXPath("/Envelope/Body/createResponse/result/errors/message");
    }
}