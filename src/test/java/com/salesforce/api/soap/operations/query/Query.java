package com.salesforce.api.soap.operations.query;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.salesforce.api.HRXML;
import com.salesforce.api.soap.SalesForceSoapAPI;
import com.salesforce.api.soap.operations.login.APILogin;
import com.salesforce.data.Constants;
import com.salesforce.utilities.SalesforceStaticData;
import com.salesforce.utilities.XMLTools;



public class Query extends SalesForceSoapAPI {
	public boolean IsQuery = false;

	/**
	 * Constructor method that takes in the role parameter and goes ahead and logs
	 * into the salesforce API, grabs the session ID and sets it to the new Query
	 * request, grabs the authenticated URL and sets it to the new service URL for
	 * the query request. Will also set the headers for the query request so that
	 * everything is ready to go to send in except for the dynamic data
	 *
	 * @param role
	 * @throws Exception 
	 */
	public Query(String role) throws Exception {
		Thread.sleep(5000);
		SalesforceStaticData.IsQuery = true;
		// Create instance of the login request class
		APILogin login = new APILogin();
			// first check to see if sessionID & URL has been set during the current test
			// run.
			if (sessionID == null) {
				// grab the session ID from the response xml
				login.getOAuthToken(role);
			}
		// use a setter to specify which soap operation we are using
		setOperationName("Query");
		// grab the template query request xml file
		File xml = new File(
				this.getClass().getResource(Constants.XML_FILES + "/salesforcequery/QueryRequest.xml").getPath());
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
		SalesforceStaticData.IsQuery = false;
	}

	// *******Methods for the Query operation*******//
	/**
	 * Sends in a query reqest xml to the salesforce api Will verify that the query
	 * request returns a 200 ok and logs all details to the test reporter Returns
	 * the response transformed into XML document
	 *
	 * @date 9/19/2017
	 * @author jessica marshall
	 * @param query
	 * @return xml Document
	 * @throws Exception 
	 */
	public Document sendQueryRequest(String query) throws Exception {
		// find the query node in the create request and add the sql query as value
		setQuery(query);
		// send in soap request
		sendRequest();
		// log the soap response details to reporter
		
		// verify we get back a 200 reponse
		Assert.assertTrue(getResponseStatusCode().equals("200"),
				"Verify the Query request was successful.  Status code: " + getResponseStatusCode());

		// get the number of records returned from the sql query
		int resultSize = Integer.parseInt(getResultSize());
		// verify we have at least 1 record returned.
		Assert.assertTrue(resultSize != 0,
				"Verify that records came back from the query request - number of records: [" + resultSize + "]");
		return getResponseDocument();
	}
	
	public Document waitForQueryResult(String query) throws Exception {
		
		int resultSize;
		long start = System.currentTimeMillis();
		long end = start + 180 * 1000;
		while (System.currentTimeMillis() < end) {
			setQuery(query);
			// send in soap request
			sendRequest();
			// log the soap response details to reporter
			resultSize = Integer.parseInt(getResultSize());	
			if (resultSize > 0) {
				return getResponseDocument();
			}
			Thread.sleep(3000);
		}
		return getResponseDocument();
	}
	
	public Document sendQueryRequestWithoutCheckingResultsSize(String query) throws Exception {
		// find the query node in the create request and add the sql query as value
		setQuery(query);
		// send in soap request
		sendRequest();
		// log the soap response details to reporter
		//TestReporter.logAPI(getResponseStatusCode().equals("200"),
		//		"Logging the request and response info for the Query operation", this);
		// verify we get back a 200 reponse
		Assert.assertTrue(getResponseStatusCode().equals("200"),
				"Verify the Query request was successful.  Status code: " + getResponseStatusCode());
		return getResponseDocument();
	}

	/**
	 * Sends it a query request for an HRXML document. Uses the generic
	 * sendQueryMethod() and sends in the sql query. Additionally needs to know the
	 * name of the database column you are using for the HRXML doc, depending on
	 * whether it is talent (Talent_HRXML__c) or req opportunity HRXML
	 * (Req_HRXML_0__c). Talent HRXML needs to be stripped of surrounding double
	 * quotes before it can be transformed into xml doc.
	 *
	 * Will transform the hrxml into an xml doc via the HRXML class which then has
	 * getter methods to allow user to get the nodes from that xml
	 *
	 * @date 9/19/2017
	 * @author jessica marshall
	 * @param query
	 * @param HRXMLColumnName
	 * @return instance of the HRXML class
	 * @throws Exception 
	 */
	public HRXML sendHRXMLQuery(String query, String HRXMLColumnName) throws Exception {
		// send query request in
		sendQueryRequest(query);
		// from the xml response, find the HRXML node name
		String hrxmlString = getDataFromQueryResultsNodeName(HRXMLColumnName);
		// if needed, strip the surrounding double quotes
		if (hrxmlString.equals("")) {
			throw new Exception("No HRXML returned from query: " + query);
			//TestReporter.assertFail("No HRXML returned from query: " + query);
		}
		if (hrxmlString.startsWith("\"")) {
			hrxmlString = hrxmlString.substring(1);
		}
		if (hrxmlString.endsWith("\"")) {
			hrxmlString = hrxmlString.substring(0, hrxmlString.length() - 1);
		}
		// create an instance of the HRXML class and pass in the hrxml transformed into
		// doc
		HRXML hrxml = new HRXML(XMLTools.loadXML(hrxmlString));
		return hrxml;
	}
	
	// *******Methods for the Query operation*******//
	/**
	 * Sends in a query reqest xml to the salesforce api Will verify that the query
	 * request returns a 200 ok and logs all details to the test reporter Returns
	 * the response transformed into XML document
	 *
	 * @date 9/19/2017
	 * @author jessica marshall, Updated By: Nagesh Akula 08/27/2020
	 * @param query
	 * @return xml Document
	 * @throws Exception 
	 */
	public Document sendQueryRequestAndWaitForTheResults(String query, int waitTime) throws Exception {
		// find the query node in the create request and add the sql query as value
		setQuery(query);
		// send in soap request
		int resultSize = 0;
		Outer: for(int i =0;i<=30;i++) {
			try {
			sendRequest();
			// log the soap response details to reporter
			//TestReporter.logAPI(getResponseStatusCode().equals("200"),
			//		"Logging the request and response info for the Query operation", this);
			}catch(Exception e) {
				Thread.sleep(3000*20);
				continue Outer;
			}
			// verify we get back a 200 reponse
			Assert.assertTrue(getResponseStatusCode().equals("200"),
					"Verify the Query request was successful.  Status code: " + getResponseStatusCode());

			// get the number of records returned from the sql query
			resultSize = Integer.parseInt(getResultSize());
		
			if(resultSize != 0) {
				break;
			}else {
				//WaitAllegis.hardWait(Constants.SHORT_TIMEOUT);
				Thread.sleep(3000);
				if(i==waitTime) {
					break;
				}
			}
		}
		
		// verify we have at least 1 record returned.
		Assert.assertTrue(resultSize != 0,
				"Verify that records came back from the query request - number of records: [" + resultSize + "]");
		return getResponseDocument();
	}

	// ******Getters and setters for the Query request******//
	public void setSessionID(String value) throws Exception {
		setRequestNodeValueByXPath("/Envelope/Header/SessionHeader/sessionId", value);
	}

	public void setQuery(String value) throws Exception {
		setRequestNodeValueByXPath("/Envelope/Body/query/queryString", value);
	}

	// ********Getters for the Query response******//
	public String getDataFromQueryResultsNodeName(String value) throws Exception {
		return getResponseNodeValueByXPath("/Envelope/Body/queryResponse/result/records/" + value);
	}
	
	
	
	public NodeList getNodeList(String value, Document sendQueryRequest) throws Exception {
		return XMLTools.getNodeList(sendQueryRequest, "/Envelope/Body/queryResponse/result/records/" + value);
	}
	
	
	

	public String getResultSize() throws Exception {
		return getResponseNodeValueByXPath("/Envelope/Body/queryResponse/result/size");
	}
	
	

	
}