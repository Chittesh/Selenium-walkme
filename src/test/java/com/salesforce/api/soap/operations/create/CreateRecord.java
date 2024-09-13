package com.salesforce.api.soap.operations.create;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import com.salesforce.api.soap.operations.query.Query;
import com.salesforce.data.TalentServiceData;
import com.salesforce.utilities.DateTimeConversion;
import com.salesforce.utilities.SalesForceQueries;
import com.salesforce.utilities.SalesforceStaticData;
import com.salesforce.utilities.TestEnvironment;


public class CreateRecord extends TestEnvironment {

	private String role;

	/**
	 * Constructor that accepts the role parameter that you are logging into
	 * salesforce API with
	 *
	 * @param role
	 */
	public CreateRecord(String role) {
		this.role = role;
	}

	/**
	 * Generic method to create an sobject in salesforce API. Takes in the sobject
	 * name, a hashmap of the xml node names and values you want to update in the
	 * template create request xml
	 *
	 * @param sobjectName
	 * @param hashmap
	 * @return the ID of the sobject you are creating
	 * @throws Exception 
	 */

	public String createSObject(String sobjectName, HashMap<String, String> hashmap) throws Exception {

		Create create;
		sobjectName =sobjectName.toLowerCase();
		// Depending on which sobject you are creating, use that template xml to pass
		// into
		// constructor for the Create class, can add more options to this
		switch (sobjectName) {
		case "currentaccount":
			create = new Create(role, "CreateAccountRequestForCurrent");
			break;
		case "account":
			create = new Create(role, "CreateAccountRequest");
			break;
		case "contact":
			create = new Create(role, "CreateContactRequest");
			break;
		case "talentcontact":
			create = new Create(role, "CreateTalentContactRequest");
			break;
		
		case "opportunity":
			create = new Create(role, "CreateOpportunityRequest");
			break;
		case "createtalentworkhistory":
			create = new Create(role, "CreateTalentWorkHistory");
			break;
		case "accountteammember":
			create = new Create(role, "AccountTeamMember");
			break;
		case "order":
			create = new Create(role, "CreateOrderRequest");
			break;
		case "opportunitywithqualified":
			create = new Create(role, "CreateOppWithQualified");
			break;
		case "opportunityclone":
			create = new Create(role, "CreateOpportunityReqClone");
			break;
		case "createburden":
			create = new Create(role, "CreateBurdenRequest");
			break;
		
		default:
			throw new Exception("SObject not available: " + sobjectName);
		}
		// since the templated create xml request is now stored in memory, find the
		// nodes you want to update and update their value
		for (Map.Entry<String, String> entry : hashmap.entrySet()) {
			String fieldName = entry.getKey();
			String value = entry.getValue();
			if (!value.isEmpty()) {
				create.setSObjectNodeByNameAndValue(fieldName, value);
			}
		}

		create.sendRequest();
		
		Assert.assertTrue(create.getResponseStatusCode().equals("200"),
				"Verify the create request was successful");

		if (create.getSuccess().equals("false")) {
			throw new Exception("Create Account request not successful - error message: " + create.getErrorMessage());
			//TestAssertions
			//		.assertFail("Create Account request not successful - error message: " + create.getErrorMessage());
		}

		return create.getID();
	}

	/**
	 * This is a overloaded method for PeopleSoftId Creates a talent account in
	 * salesforce api and retuns the new account ID
	 *
	 * @param accName
	 * @param talOwnrShip
	 * @return account ID
	 * @param ps ID
	 * @throws Exception 
	 */
	public String createTalentAccount(String OwnerId, String accName, String talOwnrShip, String psId,
			String CurrentEmployer, String candidateStatus, String StartDate) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Name", accName.trim());
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_ACCOUNT_RECORDTYPEID.trim());
		hm.put("Talent_Ownership__c", talOwnrShip);
		hm.put("Peoplesoft_ID__c", psId);
		hm.put("Candidate_Status__c", candidateStatus);
		hm.put("OwnerId", OwnerId);
		hm.put("Talent_Current_Employer_Text__c", CurrentEmployer);
		hm.put("Talent_Start_Date__c", StartDate);

		return createSObject("currentaccount", hm);
	}
	
	public String createTalentAccount(String accName, String talOwnrShip) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Name", accName.trim());
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_ACCOUNT_RECORDTYPEID.trim());
		hm.put("Talent_Ownership__c", talOwnrShip);

		return createSObject("Account", hm);
	}
	public String createTalentContact(TalentServiceData talentServiceData) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_CONTACT_RECORDTYPEID);
		hm.put("AccountId", talentServiceData.getAccountId());
		hm.put("FirstName", talentServiceData.getFirstName());
		hm.put("LastName", talentServiceData.getLastName());
		hm.put("Email", talentServiceData.getEmail());
		hm.put("MailingStreet", talentServiceData.getMailStreet());
		hm.put("MobilePhone", talentServiceData.getMobilePhone());
		return createSObject("Contact", hm);
	}

/**
	 * Sends a create request into salesforce API with an object of type contact in
	 * order to create a new talent contact
	 *
	 * @param AccId
	 * @param FirstName
	 * @param LastName
	 * @param Email
	 * @param MailStreet
	 * @param MobPhone
	 * @param PSID
	 * @return the contact ID
 * @throws Exception 
	 */

	public String createTalentContact(String accId, String firstName, String lastName, String email, String mailStreet,
			String mobPhone, String PSID) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_CONTACT_RECORDTYPEID);
		hm.put("AccountId", accId);
		hm.put("FirstName", firstName);
		hm.put("LastName", lastName);
		hm.put("Email", email);
		hm.put("MailingStreet", mailStreet);
		hm.put("MobilePhone", mobPhone);
		return createSObject("Contact", hm);
	}
	
	public String createTalentContact(String accId, String firstName, String lastName, String email, String mailStreet,
			String mobPhone) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_CONTACT_RECORDTYPEID);
		hm.put("AccountId", accId);
		hm.put("FirstName", firstName);
		hm.put("LastName", lastName);
		hm.put("Email", email);
		hm.put("MailingStreet", mailStreet);
		hm.put("MobilePhone", mobPhone);
		return createSObject("Contact", hm);
	}

	/**
	 * @CreateBy
	 * @Desc Creates a talent Work history in salesforce api and returns the id
	 * @ModifiedBy N.AKula 10/9/2019
	 * @param talentAccId, BillRate, CurrType, SourceCompany, SourceCompanyId,
	 *                     StartDate, EndDate, Psid, JobTitle
	 * @return Talent History ID
	 * @throws Exception 
	 */
	public String CreateWorkHistory(String accId, String BillRate, String CurrencyType, /* String CurrentAssignment, */
			String SourceCompany__c, String SourceCompanyId__c, String Start_Date__c, String End_Date__c, String Psid,
			String JobTitle) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Talent__c", accId);
		hm.put("Bill_rate__c", BillRate);
		hm.put("Currency_Type__c", CurrencyType);
		hm.put("SourceCompany__c", SourceCompany__c);
		hm.put("SourceCompanyId__c", SourceCompanyId__c);
		hm.put("Start_Date__c", Start_Date__c);
		hm.put("End_Date__c", End_Date__c);
		hm.put("SourceId__c", Psid);
		hm.put("Job_Title__c", JobTitle);
		hm.put("Talent_Current_Employer_Text__c", "");
		hm.put("ESFId__c", SalesforceStaticData.ESF_ID);
		hm.put("Main_Skill__c", SalesforceStaticData.SKILLS1);
		hm.put("Finish_Reason__c", SalesforceStaticData.FINISH_REASON);
		hm.put("Finish_Code__c", SalesforceStaticData.FINISH_REASON_CODE);
		hm.put("Pay_Rate__c", SalesforceStaticData.PAY_RATE);
		hm.put("DivisionName__c", SalesforceStaticData.DIVISION_NAME);

		return createSObject("CreateTalentWorkHistory", hm);
	}

	/**
	 * @CreateBy
	 * @Desc Creates a record in AccountTeamMember
	 * @CreatedBy Saravan
	 * @param UserId,AccountId
	 * @return
	 * @throws Exception 
	 */
	public String CreateAccountTeamMember(String UserId, String AccountId) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("AccountId", AccountId);
		hm.put("UserId", UserId);
		return createSObject("AccountTeamMember", hm);
	}
	
	public String createClientAccount(String accName, String city, String state, String country, String streetAddress,
			String zipcode) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Name", accName);
		hm.put("RecordTypeId", SalesforceStaticData.CLIENT_ACCOUNT_RECORDTYPEID);
		hm.put("ShippingCity", city);
		hm.put("ShippingState", state);
		hm.put("ShippingCountry", country);
		hm.put("ShippingStreet", streetAddress);
		hm.put("ShippingPostalCode", zipcode);

		return createSObject("Account", hm);

	}
	
	public String createClientContact(String accId, String firstName, String lastName, String email, String mailStreet,
			String mailingCity, String mailingState, String mailingPostalCode, String mailingCountry, String mobPhone) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.CLIENT_CONTACT_RECORDTYPEID);
		hm.put("AccountId", accId);
		hm.put("FirstName", firstName);
		hm.put("LastName", lastName);
		hm.put("Email", email);
		hm.put("MailingStreet", mailStreet);
		hm.put("MailingCity", mailingCity);
		hm.put("MailingState", mailingState);
		hm.put("MailingPostalCode", mailingPostalCode);
		hm.put("MailingCountry", mailingCountry);
		hm.put("MobilePhone", mobPhone);
		hm.put("Title", "Machine Operator");

		return createSObject("Contact", hm);
	}
	
	public String createReqOpportunityQualified(String AccId, String oppName, String OppCloseDate, String oppStage,
			String oppProduct, String oppTotPos, String oppJobTitleID, String oppTermsOfEngmnt, String city,
			String country, String state, String streetAddress, String postCode, String opco, String hiringManager,
			String currencyType, String division, String duartion, String durationUnit, String qualification,
			String rateFreq, String billRateMin, String billRateMax, String payRateMin, String payRateMax,
			String stBurden, String jobDesc, String skill, String minSalary, String maxSalary, String remoteWork,
			String reqOwnerId, String skillSpecialty) throws Exception {
		String legacyProdId = "";
		String officeId = "";
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.REQ_OPPORTUNITY_RECORDTYPEID);
		hm.put("AccountId", AccId);
		hm.put("Name", oppName);
		hm.put("CloseDate", OppCloseDate);
		hm.put("StageName", oppStage);
		hm.put("Req_Product__c", oppProduct);
		hm.put("Req_Total_Positions__c", oppTotPos);
		hm.put("Req_Job_Title__c", oppJobTitleID);
		hm.put("Req_Terms_of_engagement__c", oppTermsOfEngmnt);
		hm.put("Req_Worksite_City__c", city);
		hm.put("Req_Worksite_Country__c", country);
		hm.put("Req_Worksite_State__c", state);
		hm.put("Req_Worksite_Street__c", streetAddress);
		hm.put("Req_Worksite_Postal_Code__c", postCode);
		hm.put("OpCo__c", opco);
		hm.put("Req_Hiring_Manager__c", hiringManager);
		hm.put("Currency__c", currencyType);
		hm.put("Req_Division__c", division);
		hm.put("Req_Duration__c", duartion);
		hm.put("Req_Duration_Unit__c", durationUnit);
		hm.put("Req_Qualification__c", qualification);
		hm.put("Req_Rate_Frequency__c", rateFreq);
		hm.put("Req_Bill_Rate_Min__c", billRateMin);
		hm.put("Req_Bill_Rate_Max__c", billRateMax);
		hm.put("Req_Pay_Rate_Min__c", payRateMin);
		hm.put("Req_Pay_Rate_Max__c", payRateMax);
		hm.put("Req_Salary_Min__c", minSalary);
		hm.put("Req_Salary_Max__c", maxSalary);
		hm.put("Req_Standard_Burden__c", stBurden);
		hm.put("Req_Job_Description__c", jobDesc);
		hm.put("EnterpriseReqSkills__c", skill);

		hm.put("Req_Work_Remote__c", remoteWork);
		hm.put("Req_Opportunity_Owner__c", reqOwnerId);
		hm.put("Req_EVP__c", "Employee Value Proposition comments");

		hm.put("OwnerId", reqOwnerId);
		String office = SalesforceStaticData.TEM_OFFICE;
		if ((opco.equals(SalesforceStaticData.OPCO_AP)) || (opco.equals(SalesforceStaticData.OPCO_AGS))
				|| (opco.equals(SalesforceStaticData.OPCO_EMEA))) {
			hm.put("Name", oppName);
			hm.put("Req_Terms_of_engagement__c", oppTermsOfEngmnt);

		}

		if (oppStage.equals("Draft")) {
			if ((opco.equals(SalesforceStaticData.OPCO_TEK))
					|| (opco.equals(SalesforceStaticData.OPCO_ONS) || (opco.equals(SalesforceStaticData.OPCO_EAS)))
					|| (opco.equals(SalesforceStaticData.OPCO_SJA)) || (opco.equals(SalesforceStaticData.OPCO_IND))) {

				hm.put("Req_Draft_Reason__c", "Qualifying");
				hm.put("Req_Rate_Frequency__c", "Hourly");
				hm.put("Req_Skill_Specialty__c", skillSpecialty);

			}
			if ((opco.equals(SalesforceStaticData.OPCO_AGS))) {
				hm.put("Req_Rate_Frequency__c", "Hourly");

			}

			if ((opco.equals(SalesforceStaticData.OPCO_EMEA))) {
				hm.put("inside_IR35__c", "No");
			}

			if ((opco.equals(SalesforceStaticData.OPCO_TEK))) {
				hm.put("Req_TGS_Requirement__c", "No");
				hm.put("Product__c", "Core Applications");
				office = SalesforceStaticData.TEK_OFFICE;
			}
		}

		// querying the office id
		Query query = new Query(role);
		query.sendQueryRequestAndWaitForTheResults(SalesForceQueries.queryOfficeId(office), 90);
		officeId = query.getDataFromQueryResultsNodeName("Id");
		hm.put("Organization_Office__c", officeId);

		Query product2IDQuery = new Query(role);
		if (opco.equals(SalesforceStaticData.OPCO_TEK) || opco.equals(SalesforceStaticData.OPCO_ONS)
				|| opco.equals(SalesforceStaticData.OPCO_SJA) || opco.equals(SalesforceStaticData.OPCO_EAS)
				|| opco.equals(SalesforceStaticData.OPCO_IND)) {

			product2IDQuery.sendQueryRequest(SalesForceQueries.queryProdcut2Id(opco, division));
			legacyProdId = product2IDQuery.getDataFromQueryResultsNodeName("Id");
		}
		hm.put("Legacy_Product__c", legacyProdId);
		return createSObject("opportunitywithqualified", hm);

	}
	
	public String createSubmittalHasApplication(String accId, String status, String OpportunityId,
			String ShipToContactId, String EffectiveDate) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", "01224000000kOSEAA2");
		hm.put("AccountId", accId);
		hm.put("Status", "Applicant");
		hm.put("OpportunityId", OpportunityId);
		hm.put("ShipToContactId", ShipToContactId);
		hm.put("EffectiveDate", EffectiveDate);
		hm.put("Has_Application__c", "true");
		return createSObject("Order", hm);
	}
	
	public static enum CreateOpportunityTestData {
		CONTACTID, OPPORTUNITYNAME, ACCOUNTID, CLOSEDATE, OPPSTAGE, PRODUCT, TOTALPOSITIONS, JOBTITLE, WORKSITECITY,
		WORKSITECOUNTRY, WORKSITESTREET, WORKSITESTATE, ZIPCODE, OPCO, HIRINGMANAGER, CURRENCY, DIVISION,
		EXTCOMMUNITIES, ADDINFO, ADDITIONALQUALIFICATION, BACKGROUNDCHECK, BILLRATEMAX, BILLRATEMIN, BONUSPERCENT,
		BUSINESSCHALLENGE, APPROVEDSUBVENDOR, CLIENTWORKING, COMPETITIONINFO, COMPLIANCE, DESCRIPTION, DRAFTREASON,
		DRUGTEST, DURATION, DURATIONUNIT, ENDCLIENT, EVP, EXCLUSIVE, EXPERIENCELEVEL, GOTOWORK, GOVERNMENT,
		INTERNALEXTRENALCUSTM, INTERVIEWINFO, OTMULTIPLIER, PAYRATEMAX, PAYRATEMIN, REDZONE, SKILLS, TOPSKILLS,
		TOTALBURDEN, WHOISINTEXTCUSTM, WORKENVIRONMENT, DATECLIENTOPENPOSITION, INTERVIEWDATE, PROJECTSTAGE, STARTDATE,
		WHYPOSITIONOPEN, WORKREMOTE, LOB, APPROVAL, FEEPERCENT, FEE, SALARYMAX, SALARYMIN, OFCCPREQUIRED,
		TGSREQUIREMENT, INTERNALHIRE
	}
	
	public String createTGSReqOppty(Map<CreateOpportunityTestData, String> optyTestData) throws Exception {

		String accountId = optyTestData.get(CreateOpportunityTestData.ACCOUNTID);
		String closeDate = optyTestData.get(CreateOpportunityTestData.CLOSEDATE);
		String oppStage = optyTestData.get(CreateOpportunityTestData.OPPSTAGE);
		String product = optyTestData.get(CreateOpportunityTestData.PRODUCT);
		String totalPositions = optyTestData.get(CreateOpportunityTestData.TOTALPOSITIONS);
		String jobTitle = optyTestData.get(CreateOpportunityTestData.JOBTITLE);
		String worksiteCity = optyTestData.get(CreateOpportunityTestData.WORKSITECITY);
		String worksiteCountry = optyTestData.get(CreateOpportunityTestData.WORKSITECOUNTRY);
		String workSiteStreet = optyTestData.get(CreateOpportunityTestData.WORKSITESTREET);
		String worksiteState = optyTestData.get(CreateOpportunityTestData.WORKSITESTATE);
		String zipCode = optyTestData.get(CreateOpportunityTestData.ZIPCODE);
		String opco = optyTestData.get(CreateOpportunityTestData.OPCO);
		String hiringManager = optyTestData.get(CreateOpportunityTestData.HIRINGMANAGER);
		String currency = optyTestData.get(CreateOpportunityTestData.CURRENCY);
		String division = optyTestData.get(CreateOpportunityTestData.DIVISION);
		String extComm = optyTestData.get(CreateOpportunityTestData.EXTCOMMUNITIES);
		String addInfo = optyTestData.get(CreateOpportunityTestData.ADDINFO);
		String additionalQualification = optyTestData.get(CreateOpportunityTestData.ADDITIONALQUALIFICATION);
		String backgroundCheck = optyTestData.get(CreateOpportunityTestData.BACKGROUNDCHECK);
		String billRateMax = optyTestData.get(CreateOpportunityTestData.BILLRATEMAX);
		String billRateMin = optyTestData.get(CreateOpportunityTestData.BILLRATEMIN);
		String bonusPercent = optyTestData.get(CreateOpportunityTestData.BONUSPERCENT);
		String businessChallenge = optyTestData.get(CreateOpportunityTestData.BUSINESSCHALLENGE);
		String approvedSubVendor = optyTestData.get(CreateOpportunityTestData.APPROVEDSUBVENDOR);
		String clientWorking = optyTestData.get(CreateOpportunityTestData.CLIENTWORKING);
		String competitionInfo = optyTestData.get(CreateOpportunityTestData.COMPETITIONINFO);
		String compliance = optyTestData.get(CreateOpportunityTestData.COMPLIANCE);
		String description = optyTestData.get(CreateOpportunityTestData.DESCRIPTION);
		String draftReason = optyTestData.get(CreateOpportunityTestData.DRAFTREASON);
		String drugTest = optyTestData.get(CreateOpportunityTestData.DRUGTEST);
		String duration = optyTestData.get(CreateOpportunityTestData.DURATION);
		String durationUnit = optyTestData.get(CreateOpportunityTestData.DURATIONUNIT);
		String endClient = optyTestData.get(CreateOpportunityTestData.ENDCLIENT);
		String eVP = optyTestData.get(CreateOpportunityTestData.EVP);
		String exclusive = optyTestData.get(CreateOpportunityTestData.EXCLUSIVE);
		String expLevel = optyTestData.get(CreateOpportunityTestData.EXPERIENCELEVEL);
		String goverment = optyTestData.get(CreateOpportunityTestData.GOVERNMENT);
		String internalExternalCustm = optyTestData.get(CreateOpportunityTestData.INTERNALEXTRENALCUSTM);
		String interviewInformation = optyTestData.get(CreateOpportunityTestData.INTERVIEWINFO);
		String otmultiplier = optyTestData.get(CreateOpportunityTestData.OTMULTIPLIER);
		String payRateMax = optyTestData.get(CreateOpportunityTestData.PAYRATEMAX);
		String payRateMin = optyTestData.get(CreateOpportunityTestData.PAYRATEMIN);
		String redZone = optyTestData.get(CreateOpportunityTestData.REDZONE);
		String skills = optyTestData.get(CreateOpportunityTestData.SKILLS);
		String topSkills = optyTestData.get(CreateOpportunityTestData.TOPSKILLS);
		String totalBurden = optyTestData.get(CreateOpportunityTestData.TOTALBURDEN);
		String whoIsIntrlExtnlCustm = optyTestData.get(CreateOpportunityTestData.WHOISINTEXTCUSTM);
		String workEnviroment = optyTestData.get(CreateOpportunityTestData.WORKENVIRONMENT);
		String dateClientOpenPosition = optyTestData.get(CreateOpportunityTestData.DATECLIENTOPENPOSITION);
		String interviewDate = optyTestData.get(CreateOpportunityTestData.INTERVIEWDATE);
		String projectStage = optyTestData.get(CreateOpportunityTestData.PROJECTSTAGE);
		String startDate = optyTestData.get(CreateOpportunityTestData.STARTDATE);
		String whyPositionOpen = optyTestData.get(CreateOpportunityTestData.WHYPOSITIONOPEN);
		String lob = optyTestData.get(CreateOpportunityTestData.LOB);
		String approval = optyTestData.get(CreateOpportunityTestData.APPROVAL);
		String feePercent = optyTestData.get(CreateOpportunityTestData.FEEPERCENT);
		String flatFee = optyTestData.get(CreateOpportunityTestData.FEE);
		String salaryMax = optyTestData.get(CreateOpportunityTestData.SALARYMAX);
		String salaryMin = optyTestData.get(CreateOpportunityTestData.SALARYMIN);
		String ofccpRequired = optyTestData.get(CreateOpportunityTestData.OFCCPREQUIRED);
		String isTgsReq = optyTestData.get(CreateOpportunityTestData.TGSREQUIREMENT);
		String officeId = "";
		String legacyProdId = "";
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.REQ_OPPORTUNITY_RECORDTYPEID);
		hm.put("AccountId", accountId);
		hm.put("StageName", oppStage);
		hm.put("Req_Product__c", product);
		hm.put("Req_Total_Positions__c", totalPositions);
		hm.put("Req_Job_Title__c", jobTitle);
		hm.put("Req_Hiring_Manager__c", hiringManager);
		hm.put("Currency__c", currency);
		hm.put("OpCo__c", opco);
		hm.put("Req_Division__c", division);
		hm.put("Req_Skill_Details__c", topSkills);
		hm.put("Req_Qualification__c", additionalQualification);
		hm.put("Req_Business_Challenge__c", businessChallenge);
		hm.put("Req_Compliance__c", compliance);
		hm.put("Req_Job_Description__c", description);
		hm.put("Req_End_Client__c", endClient);
		hm.put("Req_Job_Level__c", expLevel);
		hm.put("Req_Government__c", goverment);
		hm.put("Req_Worksite_City__c", worksiteCity);
		hm.put("Req_Worksite_Country__c", worksiteCountry);
		hm.put("Req_Worksite_State__c", worksiteState);
		hm.put("Req_Worksite_Street__c", workSiteStreet);
		hm.put("Req_Worksite_Postal_Code__c", zipCode);
		hm.put("EnterpriseReqSkills__c", skills);
		hm.put("Req_Date_Client_Opened_Position__c", dateClientOpenPosition);
		hm.put("Start_Date__c", startDate);

		// After every sand box refresh admin loading this test office manually and new
		// office id will be generated - this below method returns office id
		Query query = new Query(role);
		query.sendQueryRequest(SalesForceQueries.queryOfficeId(SalesforceStaticData.TEK_OFFICE));
		officeId = query.getDataFromQueryResultsNodeName("Id");
		hm.put("Organization_Office__c", officeId);

		if (opco.equals(SalesforceStaticData.OPCO_TEK)) {
			hm.put("Req_Red_Zone__c", redZone);
			hm.put("Req_Rate_Frequency__c", "Hourly");
			hm.put("Req_Pay_Rate_Max__c", payRateMax);
			hm.put("Req_Pay_Rate_Min__c", payRateMin);
			hm.put("Req_Bill_Rate_Max__c", billRateMax);
			hm.put("Req_Bill_Rate_Min__c", billRateMin);
			hm.put("Req_Drug_Test_Required__c", drugTest);
			hm.put("Req_Exclusive__c", exclusive);
			hm.put("Req_Standard_Burden__c", totalBurden);
			hm.put("Req_Duration__c", duration);
			hm.put("Req_Duration_Unit__c", durationUnit);
			hm.put("Req_Background_Check_Required__c", backgroundCheck);
			hm.put("Immediate_Start__c", "true");
			hm.put("Req_Draft_Reason__c", "Qualifying");
			hm.put("Req_Draft_Reason__c", draftReason);
			hm.put("Req_Interview_Information__c", interviewInformation);
			hm.put("Req_EVP__c", eVP);
			hm.put("Req_Work_Environment__c", workEnviroment);
			hm.put("Req_Interview_Date__c", interviewDate);
			hm.put("Req_Client_working_on_Req__c", clientWorking);
			hm.put("Req_External_Job_Description__c", extComm);
			hm.put("Req_Competition_Info__c", competitionInfo);
			hm.put("Req_Additional_Information__c", addInfo);
			hm.put("OT_Multiplier__c", otmultiplier);
			hm.put("Req_Can_Use_Approved_Sub_Vendor__c", approvedSubVendor);
			hm.put("Req_TGS_Requirement__c", isTgsReq);
			hm.put("Req_Impact_to_Internal_External_Customer__c", internalExternalCustm);
			hm.put("Req_Approval_Process__c", approval);
			hm.put("CloseDate", closeDate);
			hm.put("Req_Why_Position_Open_Details__c", whyPositionOpen);
			hm.put("Req_Internal_External_Customer__c", whoIsIntrlExtnlCustm);
			hm.put("Req_Fee_Percent__c", feePercent);
			hm.put("REQ_Opportunity_driven_mostly_by_LOB__c", lob);
			hm.put("Req_Flat_Fee__c", flatFee);
			hm.put("Req_Project_Stage_Lifecycle_Info__c", projectStage);
			hm.put("Req_Work_Remote__c", "On-site");
			hm.put("Req_Bonus__c", bonusPercent);
			hm.put("Req_Salary_Max__c", salaryMax);
			hm.put("Req_Salary_Min__c", salaryMin);
			hm.put("Req_Other_Compensations__c", "Test other compensation");
			hm.put("Req_OFCCP_Required__c", ofccpRequired);
			// TGS Delivery Details Section
			hm.put("Req_TGS_Requirement__c", isTgsReq);
			hm.put("Practice_Engagement__c", "Business Operations Support (BOS)");
			hm.put("GlobalServices_Location__c", "Montreal Center");
			hm.put("Employment_Alignment__c", "COS: Contractor or C2C");
			hm.put("Final_Decision_Maker__c", "Client");
			hm.put("Is_International_Travel_Required__c", "No");
			hm.put("Internal_Hire__c", "No");

			hm.put("Product__c", "Core Applications");
			if (ofccpRequired != null && !ofccpRequired.isEmpty()) {
				hm.put("Req_OFCCP_Required__c", ofccpRequired);
			} else {
				hm.put("Req_OFCCP_Required__c", "true");
			}
		}
		Query product2IDQuery = new Query(role);
		String s = SalesForceQueries.queryProdcut2Id(opco, division);
		product2IDQuery.sendQueryRequest(s);
		legacyProdId = product2IDQuery.getDataFromQueryResultsNodeName("Id");
		hm.put("Legacy_Product__c", legacyProdId);
		String sf = new String();
		sf = createSObject("opportunityclone", hm);
		return sf;
	}
	
	public String createTalentContact(String accId, String firstName, String lastName, String jobTitle, String email,
			String mailStreet, String mailingCity, String mailingState, String mailingPostalCode, String mailingCountry,
			String mobPhone) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", SalesforceStaticData.TALENT_CONTACT_RECORDTYPEID);
		hm.put("AccountId", accId);
		hm.put("FirstName", firstName);
		hm.put("LastName", lastName);
		hm.put("Title", jobTitle);
		hm.put("Email", email);
		hm.put("MailingStreet", mailStreet);
		hm.put("MailingCity", mailingCity);
		hm.put("MailingState", mailingState);
		hm.put("MailingPostalCode", mailingPostalCode);
		hm.put("Talent_Country_Text__c", "United States");

		hm.put("MailingCountry", mailingCountry);
		hm.put("MailingLatitude", "35.5393");
		hm.put("MailingLongitude", "-82.518");
		hm.put("MobilePhone", mobPhone);

		return createSObject("talentcontact", hm);
	}
	
	public String createSubmittal(String accId, String status, String OpportunityId, String ShipToContactId,
			String EffectiveDate) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("RecordTypeId", "01224000000kOSEAA2");
		hm.put("AccountId", accId);
		hm.put("Status", status);
		hm.put("OpportunityId", OpportunityId);
		hm.put("ShipToContactId", ShipToContactId);
		hm.put("EffectiveDate", EffectiveDate);
		return createSObject("Order", hm);
	}
	


}