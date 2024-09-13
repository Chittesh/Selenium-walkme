package com.salesforce.api;

import org.w3c.dom.Document;

import com.salesforce.utilities.XMLTools;



public class HRXML{
	
	private Document hrXMLDoc;

	/**
	 * Constructor that takes in an xml document
	 * @param hrXMLDoc
	 */
	public HRXML(Document hrXMLDoc) {
		this.hrXMLDoc = hrXMLDoc;
	}
	
	
	//*******Getters for the HRXML doc XML******//
	/**
	 * Getter method that allows you to get a value from the query results xml document based on 
	 * the node name such as OpportunityName or PositionTitle, etc
	 * @param name of the xml node
	 * @return value of the xml node
	 * @throws Exception 
	 */
    public String getDataFromXMLNodeName(String name) throws Exception {
        return XMLTools.getValueByXpath(hrXMLDoc, "//" + name);
    }
	/**
	 * Getter method that allows you to get a value from the query results xml document based on 
	 * the node name such as OpportunityName or PositionTitle, with additional parameter of index,
	 * for use in array nodes
	 * @param name of the xml node
	 * @param index of the xml node
	 * @return value of the xml node
	 * @throws Exception 
	 */
    public String getDataFromXMLNodeNamewithIndex(String name, String index) throws Exception {
        return XMLTools.getValueByXpath(hrXMLDoc, "(//" + name + ")["+index+"]");
    }

}
