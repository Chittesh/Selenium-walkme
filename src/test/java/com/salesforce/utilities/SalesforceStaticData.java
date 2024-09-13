package com.salesforce.utilities;

public class SalesforceStaticData {
	/** Record type ids **/
	public static final String CLIENT_ACCOUNT_RECORDTYPEID = "01224000000FFqL";
	public static final String TALENT_ACCOUNT_RECORDTYPEID = "01224000000FFqQAAW";
	public static final String REFERENCE_ACCOUNT_RECORDTYPEID = "01224000000B59lAAC";
	public static final String CLIENT_CONTACT_RECORDTYPEID = "012U0000000DWoy";
	public static final String TALENT_CONTACT_RECORDTYPEID = "01224000000FFqVAAW";
	public static final String REFERENCE_TALENT_RECOM_RECORDTYPEID = "0121p000000LVXxAAO";
	public static final String REFERENCE_CONTACT_RECORDTYPEID = "01224000000B59mAAC";
	public static final String PROACTIVE_OPPORTUNITY_RECORDTYPEID = "01224000000kObb";
	public static final String REQ_OPPORTUNITY_RECORDTYPEID = "01224000000kMQ4";
	public static final String REQ_OPPORTUNITY_CLONE_RECORDTYPEID = "0068E00000Hixs8QAB";
	public static final String STRATEGIC_OPPORTUNITY_RECORDTYPEID = "01224000000gLMy";
	public static final String SERVICE_OPPORTUNITY_RECORDTYPEID = "012U0000000DWp4";
	public static final String TGS_OPPORTUNITY_RECORDTYPEID = "012U0000000DWp4IAG";
	public static final String TALENT_FIRST_OPPORTUNITY_RECORDTYPEID = "0124C0000005A0QQAU";
	public static String ACCOUNT_NAME_HOME = "AutomationTest";
	public static String SKILLS1 = "java";

	// For Talent Work History Verification
	public static String FINISH_REASON_CODE = "RES";
	public static String FINISH_REASON = "Resignation";
	public static String DIVISION_CODE = "61";
	public static String DIVISION_NAME = "GS - Applications";
	public static String PAY_RATE = "60.50";
	public static String DIVISION_LABEL = "Division";
	public static String PAY_RATE_LABEL = "Pay";
	public static String SKILL_LABEL = "Skill";
	public static String FINISH_REASON_LABEL = "Reason";
	public static String CURRENCY_LABEL = "Currency";
	public static String ESF_LABEL = "ESF";
	public static String ESF_VALUE = "View/Edit ESF";
	public static String ESF_ID = Randomness.randomNumber(6);

	/** API Query Identifier **/
	public static boolean IsQuery = false;

	public static String FIRSTNAME = "automationUserFirstName" + Randomness.randomString(4);
	public static String LASTNAME = "automationUserLastName" + Randomness.randomString(4);
	public static String MOBILE = Randomness.randomNumber(10);
	public static String JOBTITLE = "Java Devloper";
	public static String SKILL = "Javascript";
	public static String SECURITY_CLEARANCE = "No";
	public static String FED_CLEARANCE = "CIA";
	public static String OVERVIEW = "Comments";
	public static String SKILL_COMMENTS = "strong programming skills on Java script/Apex";
	public static String GOAL_INTEREST = "Salesforce CPQ Certifiation";
	public static String LANGUAGE1 = "English";

	public static String HOME_PHONE = Randomness.randomNumber(10);
	public static String BUSINESS_PHONE = Randomness.randomNumber(10);
	public static String OTHER_PHONE = Randomness.randomNumber(10);
	public static String EMAIL = Randomness.randomAlphaNumeric(10) + "testabc@randomEmail.com";
	public static String OTHER_EMAIL = Randomness.randomString(8) + "@randomOtherEmail.com";
	public static String WORK_EMAIL = Randomness.randomAlphaNumeric(6) + "abc@randomWorkEmail.com";
	public static String STREET_ADDRESS = "2260 Par Lane";

	public static String CITY = "Willoughby";
	public static String STATE = "Ohio";
	public static String ZIPCODE = "44094";
	public static String COUNTRY = "United States";

	/** Opco Name In Shortform **/
	public static final String AGS_RECRUITER = "AGS";
	public static final String AP_SD1 = "AP";
	public static final String EMEA_SD = "EMEA";
	public static final String MLA_SD2 = "MLA";
	public static final String ONS_SD = "ONS";
	public static final String TEK_SD = "TEK";
	public static final String AGS_SD = "AGS";
	public static final String AES_SD = "AES";
	/**
	 * AES OPCOs Code Added
	 * 
	 * @Date 10/06/2021
	 * @author santshah
	 */
	public static final String SJA_SD = "SJA";
	public static final String IND_SD = "IND";
	public static final String EAS_SD = "EAS";

	/** OPCO **/
	public static final String OPCO_AGS = "Allegis Global Solutions, Inc.";
	public static final String OPCO_AP = "Allegis Partners, LLC";
	public static final String OPCO_EMEA = "AG_EMEA";
	public static final String OPCO_MLA = "Major, Lindsey & Africa, LLC";
	public static final String OPCO_ONS = "Aerotek, Inc.";
	public static final String OPCO_TEK = "TEKsystems, Inc.";

	/** Segment **/
	public static final String TEK_SEGMENT = "Staffing";
	public static final String ONS_SEGMENT = "Software/Hardware";
	/**
	 * AES Opcos Segment Added @author santshah @Date 10/06/2021
	 */
	public static final String SJA_SEGMENT = "Corporate Services";
	public static final String IND_SEGMENT = "Facilities & Maint";
	public static final String EAS_SEGMENT = "Architecture & Engineering";
	/** Jobcode **/
	public static final String TEK_JOBCODE = "Developer";
	public static final String ONS_JOBCODE = "CAN - Engineer - Software Test";
	/** AES JObCode Added @author santshah @Date 10/06/202` */
	public static final String SJA_JOBCODE = "Contract Sourcer";
	public static final String IND_JOBCODE = "Aircraft Assembly";
	public static final String EAS_JOBCODE = "Architect";
	/** Category **/
	public static final String TEK_CATEGORY = "Application - CRM";
	public static final String ONS_CATEGORY = "Controller";
	/** AES Catgeory Added @author santshah @Date 10/06/202` */
	public static final String SJA_CATEGORY = "Accounts Payable Associate";
	public static final String IND_CATEGORY = "Aircraft Assembly";
	public static final String EAS_CATEGORY = "Architect";

	/** Main Skill **/
//	public static final String TEK_MAINSKILL = "Application-CRM-Salesforce.com";
	public static final String TEK_MAINSKILL = "Design";
	public static final String ONS_MAINSKILL = "Uncategorized";
	public static final String TEK_DIVISION = "Applications";

	/** Office **/
	public static final String TEK_OFFICE = "TEK-7301 Parkway Drive-00000";
	public static final String ONS_OFFICE = "TEM-7312 Parkway Dr-22222";
	public static final String TEM_OFFICE = "TEM-7312 Parkway Dr-22222";
	/** AES Office @author santshah @Date 10/06/2021 */
	public static final String SJA_OFFICE = "TEM-7312 Parkway Dr-22222";
	public static final String IND_OFFICE = "TEM-7312 Parkway Dr-22222";
	public static final String EAS_OFFICE = "TEM-7312 Parkway Dr-22222";

	public static final String OPCO_SJA = "Aston Carter, Inc.";
	public static final String OPCO_IND = "Aerotek, Inc.";
	public static final String OPCO_EAS = "Actalent, Inc.";

	public static String PLACEMENT_TYPE = "any";
	public static String WORKPLACE_TYPE = "100% Remote";
	public static String SCHEDULE = "Full-Time";
	public static String DESIRED_SHIFT = "First";
	public static String WILLING_TO_RELOCATE = "No";
	public static String NATIONAL_OPPORTUNITY_VALUE = "Yes";
	public static String RELIABLE_TRANSPORTATION = "Yes";
	public static String COUNTRIES_LEGALLY_AUTHORIZED_TO_WORK = "United States";
	public static String COUNTRY_UK = "United States";
	public static String STATE_UK = "Maryland";
	public static String CITY_LOCATION_PREFERANCE_UK = "Baltimore";
	public static String G2_COMMENTS = "G2 Comments";
	public static String LANGUAGE2 = "French";
	public static String SKILLS2 = "Java";
	public static String SKILLS3 = "Apex";
	
	public static final String[] DIVISION_LOOKUP_VALUES = { "12: GS - Digital Workplace Services",
			"33: Flexible Capacity", "90: GS - Training Services", "61: GS - Applications", "112: One North" };


}
