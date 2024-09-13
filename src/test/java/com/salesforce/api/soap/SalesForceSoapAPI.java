package com.salesforce.api.soap;

import java.util.HashMap;

import com.salesforce.utilities.SoapService;



public class SalesForceSoapAPI extends SoapService {

	private String serverURL = "";
    protected static String sessionID = null;
    protected static String sdUserSessionId = null;
    protected static String authenticatedURL = null;
    protected static String soapURI= "/services/Soap/u/45.0";
    protected static HashMap<String,String> hmOathDetails = new HashMap<String, String>();
   
    public SalesForceSoapAPI() {
        setServiceName("Sales Force Query");
        setServiceURL(serverURL);
    }
}