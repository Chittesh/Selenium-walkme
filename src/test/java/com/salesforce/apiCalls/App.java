package com.salesforce.apiCalls;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class App {

	public static void main(String[] args) throws IOException, JSONException {
		
		long start = System.currentTimeMillis();
		
		EntityBuilder builder = new EntityBuilder();
		String accountClientdata = builder.getDataFromJson(builder.CreateAccountClient());
		String contactClientData = builder.getDataFromJson(builder.CreateContactClient(accountClientdata));
		
		String accountTalentData = builder.getDataFromJson(builder.CreateAccountTalent());
		String contactTalentData = builder.getDataFromJson(builder.CreateContactTalent(accountTalentData));
				
		String opportunityData = builder.getDataFromJson(builder.CreateOpportunity(accountClientdata, contactClientData));
		
		String opportunityData1 =  builder.getDataFromJson(builder.updateOpportunityFlag(opportunityData));
		
		builder.getDataFromJson(builder.CreateSubmittal(opportunityData, accountTalentData, contactTalentData));
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("Total time :" + sec + " seconds");
		
	}
}
