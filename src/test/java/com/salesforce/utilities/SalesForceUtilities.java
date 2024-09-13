package com.salesforce.utilities;



import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.BasePage;
import com.salesforce.api.soap.operations.create.CreateRecord;
import com.salesforce.api.soap.operations.create.CreateRecord.CreateOpportunityTestData;
import com.salesforce.api.soap.operations.query.Query;
import com.salesforce.api.soap.operations.update.UpdateRecord;


import com.salesforce.data.ESFServiceData;





public class SalesForceUtilities extends BasePage {

	public SalesForceUtilities(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	//private Logger logger = LogManager.getLogger(SalesForceUtilities.class);
	
	
	
	
	public HashMap<String, String> dataSetupForMyCurrents(String role, String talOwnrShip, String profileName) throws Exception {
		// Create Contact through API and get the Contact Name to search through
		HashMap<String, String> hashMapOfTalent = new HashMap<String, String>();
		String FirstName = "automationUserFirstName" + Randomness.randomNumber(10);
		String LastName = "automationUserLastName" + Randomness.randomString(4);
		String Phonenumber = Randomness.randomNumber(10);
		String Psid = "R." + Randomness.randomNumber(5);
		String Email = Randomness.randomString(4) + "@gmail.com";
		String accName = "AutomationTest";
		String pastDate = DateTimeConversion.getDaysOut("-45", "yyyy-MM-dd");
		String futureDate = DateTimeConversion.getDaysOut("120", "yyyy-MM-dd");

		String sourceCompany = "APPLUS TECHNOLOGIES INC";
		String hourlyRate = "70.25";
		String currencyType = "USD - U.S Dollar";
		String jobTitle = "Test Engineer";
		String sourceCompanyId = "270255";
		String currentStatus = "Current";
		String address = "3110 Wheaton way , Ellicott City,MD,21043";

		CreateRecord objcr = new CreateRecord(role);
	

		Query accountQuery1 = new Query(role);
		accountQuery1.sendQueryRequest(SalesForceQueries.getUserId(profileName));
		String OwnerId = accountQuery1.getDataFromQueryResultsNodeName("Id");
		
		System.out.println("Owner ID Cretated From API is "+OwnerId);
		
		String talentAccId = objcr.createTalentAccount(OwnerId, accName, talOwnrShip, Psid, sourceCompany,
				currentStatus, pastDate);
		System.out.println("Talent Account ID Cretated From API is "+talentAccId);
		
		String talentconId = objcr.createTalentContact(talentAccId, FirstName, LastName, Email, address, Phonenumber,
				Psid);
		System.out.println("Talent Contact ID Cretated From API is  "+talentconId);
		
		String workHistoryId = objcr.CreateWorkHistory(talentAccId, hourlyRate, currencyType, sourceCompany,
				sourceCompanyId, pastDate, futureDate, Psid, jobTitle);
		System.out.println("Talent Work History Cretated From API is  "+workHistoryId);

		String AccountTeamMember = objcr.CreateAccountTeamMember(OwnerId, talentAccId);
		System.out.println("Talent Account Team Member Cretated From API is  "+AccountTeamMember);
		
		hashMapOfTalent.put("FirstName", FirstName);
		hashMapOfTalent.put("LastName", LastName);
		hashMapOfTalent.put("Phonenumber", Phonenumber);
		hashMapOfTalent.put("Psid", Psid);
		hashMapOfTalent.put("Email", Email);
		hashMapOfTalent.put("TalentAccountId", talentAccId);
		hashMapOfTalent.put("ContactId", talentconId);
		hashMapOfTalent.put("SourceCompany", sourceCompany);
		hashMapOfTalent.put("CurrentStatus", currentStatus);
		hashMapOfTalent.put("JobTitle", jobTitle);
		hashMapOfTalent.put("HourlyRate", hourlyRate);
		hashMapOfTalent.put("Currency Type", currencyType);
		return hashMapOfTalent;
	}
	
	
	
	public String[] createServiceESF(ESFServiceData eSFServiceData) throws Exception {
		System.out.println("Create ESF in TGS profile via API");
		CreateRecord objcr = new CreateRecord(eSFServiceData.getRole());
		UpdateRecord objup = new UpdateRecord(eSFServiceData.getRole());
		Query query = new Query(eSFServiceData.getRole());
		String ids[] = new String[7];

	
		
		String CLIENT_ACCOUNT_NAME = "AutoESFTestAccount" + Randomness.randomString(4);
		String WORKSITE_CITY = "Hanover";
		String WORKSITE_STATE_MD = "MD";
		String WORKSITE_COUNTRY = "United States";
		String WORKSITE_STREET = "7312 Parkway Dr";
		String POSTAL_CODE = "21076";
		String WORKSITE_STATE = "Maryland";
		String WORKSITE_COUNTRY_USA = "USA";
		String OPPTY_NAME = "AutoESFTestOppty" + Randomness.randomString(4);
		String TALENT_ACCOUNT_NAME = "AutoUserTest" + Randomness.randomString(4);
		String TALENT_CONTACT_FIRSTNAME = Randomness.randomString(6);
		String TALENT_CONTACT_LASTNAME = Randomness.randomString(4);
		String TALENT_JOBTITLE = "Business Analyst";
		String TALENT_EMAIL_PREFIX = "autuserotest" + Randomness.randomString(4) + "@test.com";	
		String CLOSE_DATE = DateTimeConversion.getSystemDate("yyyy-MM-dd");
		
		
		String clientAccountId = objcr.createClientAccount(CLIENT_ACCOUNT_NAME, WORKSITE_CITY,
				WORKSITE_STATE_MD, WORKSITE_COUNTRY, WORKSITE_STREET, "");
		ids[0] = clientAccountId;
		System.out.println("Client Account ID Created by API is : " + ids[0]);
		
	
		String CLIENT_CONTACT_FIRSTNAME = "AutoContactFN" + Randomness.randomString(6);
		String CLIENT_CONTACT_LASTNAME = "AutoContactLN" + Randomness.randomString(6);
		String EMAIL = Randomness.randomAlphaNumeric(6) + "esfautotest@gmail.com";
		String PHONE = Randomness.randomNumber(10);
		String clientContactId = objcr.createClientContact(clientAccountId, CLIENT_CONTACT_LASTNAME,
				CLIENT_CONTACT_FIRSTNAME, EMAIL, WORKSITE_STREET, WORKSITE_CITY,
				WORKSITE_STATE, POSTAL_CODE, WORKSITE_COUNTRY_USA, PHONE);
		ids[1] = clientContactId;
		System.out.println("Client Contact ID Created by API is : " + ids[1]);
		

		
		Map<CreateOpportunityTestData, String> optyTestData = new HashMap<CreateOpportunityTestData, String>();
		optyTestData.put(CreateOpportunityTestData.CONTACTID, clientContactId);
		optyTestData.put(CreateOpportunityTestData.OPPORTUNITYNAME, OPPTY_NAME);
		optyTestData.put(CreateOpportunityTestData.ACCOUNTID, clientAccountId);
		optyTestData.put(CreateOpportunityTestData.CLOSEDATE, ReqOppTestData.closedDate);
		optyTestData.put(CreateOpportunityTestData.OPPSTAGE, ReqOppTestData.oppStage);
		optyTestData.put(CreateOpportunityTestData.PRODUCT, ReqOppTestData.product);
		optyTestData.put(CreateOpportunityTestData.TOTALPOSITIONS, eSFServiceData.getNoOfPos());
		optyTestData.put(CreateOpportunityTestData.JOBTITLE, ReqOppTestData.jobTitle);
		optyTestData.put(CreateOpportunityTestData.WORKSITECITY, ReqOppTestData.worksiteCity);
		optyTestData.put(CreateOpportunityTestData.WORKSITECOUNTRY, ReqOppTestData.worksiteCountry);
		optyTestData.put(CreateOpportunityTestData.WORKSITESTREET, ReqOppTestData.workSiteStreet);
		optyTestData.put(CreateOpportunityTestData.WORKSITESTATE, ReqOppTestData.worksiteState);
		optyTestData.put(CreateOpportunityTestData.ZIPCODE, ReqOppTestData.zipCode);
		optyTestData.put(CreateOpportunityTestData.OPCO, SalesforceStaticData.OPCO_TEK);
		optyTestData.put(CreateOpportunityTestData.HIRINGMANAGER, clientContactId);
		optyTestData.put(CreateOpportunityTestData.CURRENCY, ReqOppTestData.currency);
		optyTestData.put(CreateOpportunityTestData.DIVISION, ReqOppTestData.TGS_DIVISION);
		optyTestData.put(CreateOpportunityTestData.EXTCOMMUNITIES, ReqOppTestData.extComm);
		optyTestData.put(CreateOpportunityTestData.ADDINFO, ReqOppTestData.addInfo);
		optyTestData.put(CreateOpportunityTestData.ADDITIONALQUALIFICATION, ReqOppTestData.additionalQualification);
		optyTestData.put(CreateOpportunityTestData.BACKGROUNDCHECK, ReqOppTestData.backgroundCheck);
		optyTestData.put(CreateOpportunityTestData.BILLRATEMAX, ReqOppTestData.billRateMax);
		optyTestData.put(CreateOpportunityTestData.BILLRATEMIN, ReqOppTestData.billRateMin);
		optyTestData.put(CreateOpportunityTestData.BONUSPERCENT, ReqOppTestData.bonusPercent);
		optyTestData.put(CreateOpportunityTestData.BUSINESSCHALLENGE, ReqOppTestData.businessChallenge);
		optyTestData.put(CreateOpportunityTestData.APPROVEDSUBVENDOR, ReqOppTestData.approvedSubVendor);
		optyTestData.put(CreateOpportunityTestData.CLIENTWORKING, ReqOppTestData.clientWorking);
		optyTestData.put(CreateOpportunityTestData.COMPETITIONINFO, ReqOppTestData.competitionInfo);
		optyTestData.put(CreateOpportunityTestData.COMPLIANCE, ReqOppTestData.compliance);
		optyTestData.put(CreateOpportunityTestData.DESCRIPTION, ReqOppTestData.description);
		optyTestData.put(CreateOpportunityTestData.DRAFTREASON, ReqOppTestData.draftReason);
		optyTestData.put(CreateOpportunityTestData.DRUGTEST, ReqOppTestData.drugTest);
		optyTestData.put(CreateOpportunityTestData.DURATION, ReqOppTestData.duration);
		optyTestData.put(CreateOpportunityTestData.DURATIONUNIT, ReqOppTestData.durationUnit);
		optyTestData.put(CreateOpportunityTestData.ENDCLIENT, clientAccountId);
		optyTestData.put(CreateOpportunityTestData.EVP, ReqOppTestData.eVP);
		optyTestData.put(CreateOpportunityTestData.EXCLUSIVE, ReqOppTestData.exclusive);
		optyTestData.put(CreateOpportunityTestData.EXPERIENCELEVEL, ReqOppTestData.expLevel);
		optyTestData.put(CreateOpportunityTestData.GOTOWORK, ReqOppTestData.goToWork);
		optyTestData.put(CreateOpportunityTestData.GOVERNMENT, ReqOppTestData.goverment);
		optyTestData.put(CreateOpportunityTestData.INTERNALEXTRENALCUSTM, ReqOppTestData.internalExternalCustm);
		optyTestData.put(CreateOpportunityTestData.INTERVIEWINFO, ReqOppTestData.interviewInformation);
		optyTestData.put(CreateOpportunityTestData.OTMULTIPLIER, ReqOppTestData.otmultiplier);
		optyTestData.put(CreateOpportunityTestData.PAYRATEMAX, ReqOppTestData.payRateMax);
		optyTestData.put(CreateOpportunityTestData.PAYRATEMIN, ReqOppTestData.payRateMin);
		optyTestData.put(CreateOpportunityTestData.REDZONE, ReqOppTestData.redZone);
		optyTestData.put(CreateOpportunityTestData.SKILLS, ReqOppTestData.SKILL);
		optyTestData.put(CreateOpportunityTestData.TOPSKILLS, ReqOppTestData.topSkills);
		optyTestData.put(CreateOpportunityTestData.TOTALBURDEN, ReqOppTestData.totalBurden);
		optyTestData.put(CreateOpportunityTestData.WHOISINTEXTCUSTM, ReqOppTestData.whoIsIntrlExtnlCustm);
		optyTestData.put(CreateOpportunityTestData.WORKENVIRONMENT, ReqOppTestData.workEnviroment);
		optyTestData.put(CreateOpportunityTestData.DATECLIENTOPENPOSITION, ReqOppTestData.dateClientOpenPosition);
		optyTestData.put(CreateOpportunityTestData.INTERVIEWDATE, ReqOppTestData.interviewDate);
		optyTestData.put(CreateOpportunityTestData.PROJECTSTAGE, ReqOppTestData.projectStage);
		optyTestData.put(CreateOpportunityTestData.STARTDATE, ReqOppTestData.startDate);
		optyTestData.put(CreateOpportunityTestData.WHYPOSITIONOPEN, ReqOppTestData.whyPositionOpen);
		optyTestData.put(CreateOpportunityTestData.WORKREMOTE, ReqOppTestData.workRemote);
		optyTestData.put(CreateOpportunityTestData.LOB, ReqOppTestData.lob);
		optyTestData.put(CreateOpportunityTestData.APPROVAL, ReqOppTestData.approval);
		optyTestData.put(CreateOpportunityTestData.FEEPERCENT, ReqOppTestData.feePercent);
		optyTestData.put(CreateOpportunityTestData.FEE, ReqOppTestData.Fee);
		optyTestData.put(CreateOpportunityTestData.SALARYMAX, ReqOppTestData.salaryMax);
		optyTestData.put(CreateOpportunityTestData.SALARYMIN, ReqOppTestData.salaryMin);
		optyTestData.put(CreateOpportunityTestData.TGSREQUIREMENT, eSFServiceData.getIsTGS());
		optyTestData.put(CreateOpportunityTestData.OFCCPREQUIRED, ReqOppTestData.OFCCP_FALSE);
		String oppId = objcr.createTGSReqOppty(optyTestData);
	
		ids[2] = oppId;
		System.out.println("Opportnity ID Created by API is : " + ids[2]);
		
		if (eSFServiceData.getLapFlag().equals("true")) {
			objup.updateOpptyLAPFlag(oppId, eSFServiceData.getLapFlag());
		}
		// Step4.Create talent account
	
		String talentAcctId = objcr.createTalentAccount(TALENT_ACCOUNT_NAME, "TEK");
		ids[3] = talentAcctId;
		System.out.println("Talent Account ID Created by API is : " + ids[3]);
		
		// Step5.Create talent contact
		String talentContactId = objcr.createTalentContact(talentAcctId, TALENT_CONTACT_FIRSTNAME,
				TALENT_CONTACT_LASTNAME, TALENT_JOBTITLE, TALENT_EMAIL_PREFIX,
				WORKSITE_STREET, WORKSITE_CITY, WORKSITE_STATE,
				POSTAL_CODE, WORKSITE_COUNTRY_USA, PHONE);
		ids[4] = talentContactId;
		System.out.println("Talent Contact ID Created by API is : " + ids[4]);


		objup.updateTalentMDMId(talentContactId);

	

		if (eSFServiceData.getStatus().equals("Offer Accepted")) {
			System.out.println("Create Submittal");
			String orderId = objcr.createSubmittal(clientAccountId, "Submitted", oppId, talentContactId,
					CLOSE_DATE);
			Thread.sleep(3000);
			System.out.println("Order ID " + orderId);
			System.out.println("Update Submittal to OA");
			objup.updateOrder(orderId, eSFServiceData.getStatus());
			// query ESF to get esf id from the back-end
			query.waitForQueryResult(SalesForceQueries.getServicesESFId(orderId));
			String esfId = query.getDataFromQueryResultsNodeName("Id");
			System.out.println("ESF ID " + esfId);
			ids[5] = orderId;
			ids[6] = esfId;
			System.out.println("Order ID Created by API is : " + ids[5]);
			System.out.println("ESF ID Created by API is : " + ids[6]);

		} else {
			System.out.println("Create Submittal");
			String orderId = objcr.createSubmittal(clientAccountId, eSFServiceData.getStatus(), oppId, talentContactId,
					CLOSE_DATE);
			System.out.println("Order ID " + orderId);
	
		
			ids[5] = orderId;
		
			System.out.println("Order ID Created by API is : " + ids[5]);
	
		}

		return ids;
	}
	

	
	static class ReqOppTestData {

		// Create Opty in Draft Stage
		static String extComm = "This is a test job description to validate external communities job Description in the view details page";
		static String jobTitle = "Automation Tester";
		static String closedDate = DateTimeConversion.getSystemDate("yyyy-MM-dd");// to create backend date
		static String oppStage = "Qualified";
		static String product = "Contract";
		static String currency = "USD - U.S Dollar";
		static String workSiteStreet = "5525 Research Park Dr";
		static String worksiteCity = "Baltimore";
		static String worksiteState = "MD";
		static String worksiteCountry = "UNITED STATES";
		static String draftReason = "Qualifying";
		static String zipCode = "21043";
		static String ACCOUNT_NAME = "AutoCRMAccount" + Randomness.randomString(5) + Randomness.randomNumber(5);
		static String TGS_DIVISION = "Global Services";
		static String skills = "java";
		static String SKILL = "[{\"name\":\"java development\",\"favorite\":true},{\"name\":\"java\",\"favorite\":false}]";
		static String description = "This is a test job description to validate job Description in the view details page";
		static String qualifcation = "B Tech";
		static String duration = " 4.00";
		static String durationUnit = "Month(s)";
		static String rateFrequency = "Daily";
		static String billRateMin = "80.00";
		static String billRateMax = "100.00";
		static String payRateMin = "50.00";
		static String payRateMax = "60.00";
		static String salaryMin = "60000.00";
		static String salaryMax = "90000.00";
		static String productType = "Contract to hire";
		static String PLACEMENT_TYPE = "Contract";
		static String whyPositionOpen = "whyPositionOpen";
		static String projectStage = "projectStage";
		static String interviewDate = DateTimeConversion.getSystemDate("yyyy-MM-dd");
		static String dateClientOpenPosition = DateTimeConversion.getSystemDate("yyyy-MM-dd");
		static String workRemote = "On-site";
		static String HUNDREDPERCENTREMOTE = "100% Remote";
		static String workEnviroment = "workEnviroment";
		static String topSkills = "topSkills";
		static String reqApprovalProcess = "reqApprovalProcess";
		static String redZone = "true";
		static String otmultiplier = "x1.5";
		static String interviewInformation = "Interview Information";
		static String internalExternalCustm = "internalExternalCustm";
		static String INTERNALHIRENOTAPPLICABLE = "--None--";
		static String INTERNALHIREYES = "Yes";
		static String INTERNALHIRENO = "No";
		static String INTERNALHIRENONE = "--None--";
		static String goverment = "true";
		static String goToWork = "true";
		static String expLevel = "Intermediate Level";
		static String exclusive = "true";
		static String eVP = "EVP";
		static String drugTest = "true";
		static String OFCCP_FALSE = "false";
		static String businessChallenge = "Business Challenge";
		static String compliance = "compliance";
		static String clientWorking = "Yes";
		static String backgroundCheck = "true";
		static String competitionInfo = "competitionInfo";
		static String additionalQualification = "additionalQualification";
		static String addInfo = "addInfo";
		static String approvedSubVendor = "true";
		static String whoIsIntrlExtnlCustm = "whoIsIntrlExtnlCustm";
		static String bonusPercent = "5.00";
		static String lob = "LOB";
		static String approval = "aprroved";
		static String feePercent = "9.00";
		static String Fee = "6.00";
		static String totalBurden = "1.00";
		static String startDate = DateTimeConversion.getSystemDate("yyyy-MM-dd");// to create backend date

	}
	

	
	
}