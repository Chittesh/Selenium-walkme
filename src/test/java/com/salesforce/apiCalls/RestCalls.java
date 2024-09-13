package com.salesforce.apiCalls;

import java.io.IOException;
import java.util.Random;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCalls {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		// ApiAuthorizationToken authToken = new ApiAuthorizationToken();
		String token = getToken();
		System.out.println(token);

		// Client Account & Contact Creation
		String accountClientdata = CreateAccountClient(token);
		String contactClientData = CreateContactClient(token, accountClientdata);
		// Opportunity Creation with Client Account & contact
		String opportunityData = CreateOpportunity(token, accountClientdata, contactClientData);

		// Talent Account & Contact Creation
		String accountTalentData = CreateAccountTalent(token);
		String contactTalentData = CreateContactTalent(token, accountTalentData);
		// Talent Talent work history & Account Team Member
		CreateTalentWorkHistory(token, accountTalentData);
		CreateAccountTeamMember(token, accountTalentData);

		// Submittal Creation with Talent account & contact
		String submittalData = CreateSubmittal(token, opportunityData, accountTalentData, contactTalentData);

		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("Total time :" + sec + " seconds");

	}

	public static String getToken() {
		Response response = null;
		String cId = "3MVG9eQyYZ1h89HeO90UV6o6amO3SSg971FrziKWgzvSPxTRHFm.z3VGHIa3VHMxp_o3TwjCwuO310lUmGabl";
		String cSecret = "5022ECFBB84540901A2435AA7A6B15A2D1450AEEC25CDC79F78E719BB14696B4";
		String uname = "apiautouser@allegisgroup.com.load";
		String pwd = "API_Aut0user";
		RestAssured.baseURI = "https://test.salesforce.com";
		response = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "password").formParam("client_secret", cSecret).formParam("client_id", cId)
				.formParam("username", uname).formParam("password", pwd).post("/services/oauth2/token");

		JsonPath jsonPathEvaluator1 = response.jsonPath();
		String Token = jsonPathEvaluator1.get("access_token");
		System.out.println("Generated Admin Token : " + Token);
		return Token;
	}

	public static String CreateAccountClient(String token) {
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String accountClientName = "Conn_Test_AC_" + getRandomString();
		String query = "{\r\n" + "\"Name\":\"" + accountClientName + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqL\",\r\n" + "\"ShippingCity\":\"Hanover\",\r\n"
				+ "\"ShippingState\":\"MD\",\r\n" + "\"ShippingCountry\":\"United States\",\r\n"
				+ "\"ShippingStreet\":\"7312 Parkway Drive\",\r\n" + "\"ShippingPostalCode\":\"21076\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Account";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String accountClientId = jsonPathEvaluator1.get("id");
		System.out.println("Account Client ID : " + accountClientId);
		return accountClientId;

	}

	public static String CreateContactClient(String token, String accountClientId) {
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String contactClientFirstName = "Conn_Test_FCC_" + getRandomString();
		String contactClientLastName = "Conn_Test_LCC_" + getRandomString();
		String query = "{\r\n" + "\"FirstName\":\"" + contactClientFirstName + "\",\r\n" + "\"LastName\":\""
				+ contactClientLastName + "\",\r\n" + "\"Title\":\"Mister\",\r\n"
				+ "\"MailingStreet\":\"7312 Parkway Drive\",\r\n" + "\"MailingCity\":\"Hanover\",\r\n"
				+ "\"MailingState\":\"MD\",\r\n" + "\"MailingPostalCode\":\"21076\",\r\n"
				+ "\"MailingCountry\":\"USA\",\r\n" + "\"Email\":\"test@test.test\", \r\n"
				+ "\"MobilePhone\":\"1234567890\",\r\n" + "\"AccountId\":\"" + accountClientId + "\",\r\n"
				+ "\"RecordTypeId\":\"012U0000000DWoy\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Contact";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String contactClientId = jsonPathEvaluator1.get("id");
		System.out.println("Contact Client ID : " + contactClientId);
		return contactClientId;

	}

	public static String CreateOpportunity(String token, String accountClientId, String contactClientId) {
		String opportunityName = "Conn_Test_OPP_" + getRandomString();
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String query = "{\r\n" + "\"Name\":\"" + opportunityName + "\",\r\n" + "\"AccountId\":\"" + accountClientId
				+ "\",\r\n" + "\"RecordTypeId\":\"01224000000kMQ4\",\r\n" + "\"Currency__c\":\"USD - U.S Dollar\",\r\n"
				+ "\"Req_External_Job_Description__c\":\"test job description\",\r\n" + "\"Req_Hiring_Manager__c\":\""
				+ contactClientId + "\",\r\n" + "\"Req_Job_Title__c\":\"Automation Tester\",\r\n"
				+ "\"Req_Product__c\":\"Contract\",\r\n" + "\"OpCo__c\":\"TEKsystems, Inc.\",\r\n"
				+ "\"Req_Worksite_City__c\":\"Hanover\",\r\n" + "\"Req_Total_Positions__c\":\"1\",\r\n"
				+ "\"Req_Division__c\":\"Global Services\",\r\n" + "\"Req_Worksite_Country__c\":\"USA\",\r\n"
				+ "\"Req_Worksite_State__c\":\"Maryland\",\r\n"
				+ "\"Req_Worksite_Street__c\":\"7312 Parkway Drive\",\r\n"
				+ "\"Req_Worksite_Postal_Code__c\":\"21076\",\r\n" + "\"Req_Bill_Rate_Max__c\":\"100\",\r\n"
				+ "\"Req_Bill_Rate_Min__c\":\"75\",\r\n" + "\"Req_Job_Description__c\":\"test job description\",\r\n"
				+ "\"Req_Draft_Reason__c\":\" Qualifying\",\r\n" + "\"Req_Duration__c\":\"4.00\",\r\n"
				+ "\"Req_Rate_Frequency__c\":\"Hourly\",\r\n" + "\"Req_Duration_Unit__c\":\"Month(s)\",\r\n"
				+ "\"Req_Pay_Rate_Max__c\":\"50\",\r\n" + "\"Req_Pay_Rate_Min__c\":\"20\",\r\n"
				+ "\"Req_Red_Zone__c\":\"true\",\r\n"
				+ "\"EnterpriseReqSkills__c\":\"[{\\\"name\\\":\\\"java development\\\",\\\"favorite\\\":true},{\\\"name\\\":\\\"java\\\",\\\"favorite\\\":false}]\",\r\n"
				+ "\"Req_Skill_Details__c\":\"java,sql,apex\",\r\n" + "\"Req_Standard_Burden__c\":\"1.00\",\r\n"
				+ "\"Req_Internal_External_Customer__c\":\"whoIsIntrlExtnlCustm\",\r\n"
				+ "\"Organization_Office__c\":\"a1a1E000003anqRQAQ\",\r\n" + "\"StageName\":\"Draft\",\r\n"
				+ "\"Start_Date__c\":\"2023-02-17\",\r\n" + "\"Legacy_Product__c\":\"01t1E00000Rj7RMQAZ\",\r\n"
				+ "\"Req_TGS_Requirement__c\":\"Yes\",\r\n" + "\"Product__c\":\"Core Applications\",\r\n"
				+ "\"Internal_Hire__c\":\"No\",\r\n"
				+ "\"Practice_Engagement__c\":\"Business Operations Support (BOS)\",\r\n"
				+ "\"GlobalServices_Location__c\":\"Montreal Center\",		 \r\n"
				+ "\"Employment_Alignment__c\":\"COS: Contractor or C2C\",\r\n"
				+ "\"Final_Decision_Maker__c\":\"Client\",\r\n" + "\"Is_International_Travel_Required__c\":\"No\"\r\n"
				+ "}";

		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Opportunity";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String opportunityId = jsonPathEvaluator1.get("id");
		System.out.println("Opportunity ID : " + opportunityId);
		return opportunityId;

	}

	public static String CreateAccountTalent(String token) {
		String accountTalentName = "Conn_Test_AT_" + getRandomString();
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String query = "{\r\n" + "\"Name\":\"" + accountTalentName + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqQAAW\",\r\n" + "\"Talent_Ownership__c\":\"TEK\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Account";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String accountTalentId = jsonPathEvaluator1.get("id");
		System.out.println("Account Talent ID : " + accountTalentId);
		return accountTalentId;
	}

	public static String CreateTalentWorkHistory(String token, String accountTalentId) {
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";

		String query = "{\r\n" + "    \"Talent__c\":\"" + accountTalentId + "\",\r\n"
				+ "    \"Bill_rate__c\":\"70.25\",\r\n" + "    \"Currency_Type__c\":\"USD - U.S Dollar\",\r\n"
				+ "    \"SourceCompany__c\":\"APPLUS TECHNOLOGIES INC\",\r\n"
				+ "    \"SourceCompanyId__c\":\"270255\",\r\n" + "    \"Start_Date__c\":\"2023-01-01\",\r\n"
				+ "    \"End_Date__c\":\"2023-08-01\",\r\n" + "    \"SourceId__c\":\"R.67843\",\r\n"
				+ "    \"Job_Title__c\":\"Test Engineer\",\r\n" + "    \"ESFId__c\":\"345678\",\r\n"
				+ "    \"Main_Skill__c\":\"java\",\r\n" + "    \"Finish_Reason__c\":\"Resignation\",\r\n"
				+ "    \"Finish_Code__c\":\"RES\",\r\n" + "    \"Pay_Rate__c\":\"60.50\",\r\n"
				+ "    \"DivisionName__c\":\"GS - Applications\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Talent_Work_History__c";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String talentWorkHistoryId = jsonPathEvaluator1.get("id");
		System.out.println("Talent Work History ID : " + talentWorkHistoryId);
		return talentWorkHistoryId;

	}

	public static String CreateAccountTeamMember(String token, String accountTalentId) {
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";

		String query = "{\r\n" + "    \"AccountId\":\"" + accountTalentId + "\",\r\n"
				+ "    \"UserId\":\"005240000080cM8AAI\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/AccountTeamMember";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String accountTeamMemberId = jsonPathEvaluator1.get("id");
		System.out.println("Account Team Member ID : " + accountTeamMemberId);
		return accountTeamMemberId;

	}

	public static String CreateContactTalent(String token, String accountTalentId) {
		String contactTalentFirstName = "Conn_Test_FCT_" + getRandomString();
		String contactTalentLastName = "Conn_Test_LCT_" + getRandomString();
		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String query = "{\r\n" + "\"FirstName\":\"" + contactTalentFirstName + "\",\r\n" + "\"LastName\":\""
				+ contactTalentLastName + "\",\r\n" + "\"Title\":\"Business Analyst\",\r\n"
				+ "\"MailingStreet\":\"7312 Parkway Drive South\",\r\n" + "\"MailingCity\":\"Hanover\",\r\n"
				+ "\"MailingState\":\"Maryland\",\r\n" + "\"MailingPostalCode\":\"21076\",\r\n"
				+ "\"MailingCountry\":\"USA\",\r\n" + "\"Email\":\"test@test.test\",\r\n"
				+ "\"MobilePhone\":\"1234567890\",\r\n" + "\"AccountId\":\"" + accountTalentId + "\",\r\n"
				+ "\"RecordTypeId\":\"01224000000FFqVAAW\",\r\n" + "\"Talent_Country_Text__c\":\"United States\",\r\n"
				+ "\"MailingLatitude\":\"35.5393\",\r\n" + "\"MailingLongitude\":\"-82.518\"\r\n" + "}";
		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Contact";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String contactTalentId = jsonPathEvaluator1.get("id");
		System.out.println("Contact Talent ID : " + contactTalentId);
		return contactTalentId;

	}

	public static String CreateSubmittal(String token, String opportunityId, String accountTalentId,
			String contactTalentId) {

		RestAssured.baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";
		String query = "{\r\n" + "\"RecordTypeId\":\"01224000000kOSEAA2\",\r\n" + "\"AccountId\":\"" + accountTalentId
				+ "\",\r\n" + "\"Status\":\"Linked\",\r\n" + "\"OpportunityId\":\"" + opportunityId + "\",\r\n"
				+ "\"ShipToContactId\":\"" + contactTalentId + "\",\r\n" + "\"EffectiveDate\":\"2023-03-17\"\r\n" + "}";

		// System.out.println(query);
		String endPoint = "/services/data/v56.0/sobjects/Order";
		Response response = createPostRequest(token, query, endPoint, 201);
		JsonPath jsonPathEvaluator1 = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 201);
		String submittalId = jsonPathEvaluator1.get("id");
		System.out.println("Submittal ID : " + submittalId);
		return submittalId;

	}

	public static Response createPostRequest(String token, String query, String endPoint, int expectedResponseCode) {
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Authorization", "Bearer " + token);
		httpRequest.accept(ContentType.JSON);
		httpRequest.contentType(ContentType.JSON);
		httpRequest.relaxedHTTPSValidation();
		httpRequest.body(query);
		Response response = httpRequest.post(endPoint);
		// response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), expectedResponseCode);
		return response;
	}

	public static String getRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public Response createGetRequest(String token, String query, String endPoint, int expectedResponseCode) {
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Authorization", "Bearer " + token);
		httpRequest.accept(ContentType.JSON);
		httpRequest.relaxedHTTPSValidation();
		httpRequest.queryParam("params", query);
		httpRequest.log().all();
		Response response = httpRequest.get(endPoint);
		Assert.assertEquals(response.getStatusCode(), expectedResponseCode);
		return response;
	}

}
