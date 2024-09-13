package com.salesforce.ats;

import static com.salesforce.utilities.SalesforceStaticData.CITY_LOCATION_PREFERANCE_UK;
import static com.salesforce.utilities.SalesforceStaticData.COUNTRIES_LEGALLY_AUTHORIZED_TO_WORK;
import static com.salesforce.utilities.SalesforceStaticData.COUNTRY_UK;
import static com.salesforce.utilities.SalesforceStaticData.DESIRED_SHIFT;
import static com.salesforce.utilities.SalesforceStaticData.FED_CLEARANCE;
import static com.salesforce.utilities.SalesforceStaticData.G2_COMMENTS;
import static com.salesforce.utilities.SalesforceStaticData.GOAL_INTEREST;
import static com.salesforce.utilities.SalesforceStaticData.LANGUAGE1;
import static com.salesforce.utilities.SalesforceStaticData.LANGUAGE2;
import static com.salesforce.utilities.SalesforceStaticData.MOBILE;
import static com.salesforce.utilities.SalesforceStaticData.NATIONAL_OPPORTUNITY_VALUE;
import static com.salesforce.utilities.SalesforceStaticData.OVERVIEW;
import static com.salesforce.utilities.SalesforceStaticData.PLACEMENT_TYPE;
import static com.salesforce.utilities.SalesforceStaticData.RELIABLE_TRANSPORTATION;
import static com.salesforce.utilities.SalesforceStaticData.SCHEDULE;
import static com.salesforce.utilities.SalesforceStaticData.SECURITY_CLEARANCE;
import static com.salesforce.utilities.SalesforceStaticData.SKILL;
import static com.salesforce.utilities.SalesforceStaticData.SKILLS1;
import static com.salesforce.utilities.SalesforceStaticData.SKILLS3;
import static com.salesforce.utilities.SalesforceStaticData.SKILL_COMMENTS;
import static com.salesforce.utilities.SalesforceStaticData.WILLING_TO_RELOCATE;
import static com.salesforce.utilities.SalesforceStaticData.WORKPLACE_TYPE;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.salesforce.pages.HomePage;
import com.salesforce.pages.LoginPage;
import com.salesforce.servicedata.CandidateDataService;
import com.salesforce.servicedata.CompensationWorkServiceData;
import com.salesforce.servicedata.LocationDataService;
import com.salesforce.servicedata.OverviewDataService;
import com.salesforce.utilities.Randomness;

public class TalentWorkFlow {
	
	CreateCandidatePage candidatePage;
	TalentSummaryPage summaryPage;
	UploadResAndEditTalentPage uploadEditPage;
	HomePage homePage;
	LoginPage loginPage;
	
	public TalentWorkFlow(WebDriver driver) {
		this.loginPage = new LoginPage(driver);
		this.homePage = new HomePage(driver);
		this.candidatePage = new CreateCandidatePage(driver);
		this.summaryPage = new TalentSummaryPage(driver);
		this.uploadEditPage = new UploadResAndEditTalentPage(driver);
	}	
	
	public void login(String role) {	    
	    this.loginPage.roleLogin(role);
	}
	
	public void verifyHomePageTab() {
		assertTrue(this.homePage.verifyHomePageTab(), "Validating Home Page"); 
	}
	
	public void verifyMyTalent() {
		assertTrue(homePage.verifyMyCurrentTab(), "Validating My Current Talent");
	}

	public void createCandidate(String role) {	    
	    this.candidatePage.addCandidateShort(role,getCandidateData());
	    assertTrue(this.candidatePage.isDisplayedNoResultFound(), "Verify No result found");
	    this.candidatePage.addNewTalent();
	    this.candidatePage.addProfile();
	    this.candidatePage.addOverview(getOverviewData());
	    this.candidatePage.addComensationWorkPrefrence(getCompensationWorkData());
	    this.candidatePage.addLocation(getLocationG2Data());
	}

	public void verifyTalentSummaryPage() {
		
		assertTrue(this.summaryPage.isTalentIdPresent(), "Talent ID verified");
		assertTrue(this.summaryPage.talentName.isDisplayed(), "Talent Name verified");
	    assertTrue(this.summaryPage.linkdenIcon.isDisplayed(), "Linkden Icon verified");
	    assertTrue(this.summaryPage.phoneNumber.isDisplayed(), "Phone Number verified");
	    assertTrue(this.summaryPage.email.isDisplayed(), "Email verified");
	    assertTrue(this.summaryPage.location.isDisplayed(), "Location verified");

	    System.out.println(this.summaryPage.txtJobTitle.getText());
	   
	    assertTrue(this.summaryPage.txtJobTitle.getText().contains("Java Developer"), "Job Title validated");

	    assertTrue(this.summaryPage.skillJava.getText().contains("java"), "Java skill validated");
	    assertTrue(this.summaryPage.skillJs.getText().contains("javascript"), "Javascript skill validated");
	    assertTrue(this.summaryPage.skillApex.getText().contains("apex"), "Apex skill validated");

	    assertTrue(this.summaryPage.skillComment.getText().toLowerCase().contains("strong programming skills on java script/apex"),
	            "strong programming skills on Java script/Apex validated");
	    assertTrue(this.summaryPage.goalIntrest.getText().contains("Salesforce CPQ Certifiation"), "Salesforce CPQ Certifiation validated");
	    assertTrue(this.summaryPage.comments.getText().contains("Comments"), "Comments validated");

	    assertTrue(this.summaryPage.clearanceType.isDisplayed(), " clearanceType validated");
	    assertTrue(this.summaryPage.fedClearance.isDisplayed(), " fedclearanceType validated");

	    //assertTrue(this.summaryPage.langEnglish.getText().contains("English"), "English Language validated");
	   // assertTrue(this.summaryPage.langFrench.getText().contains("French"), "French language validated");

	    assertTrue(this.summaryPage.placementType.getText().contains("Any"), "Placement Type validated");
	    assertTrue(this.summaryPage.schedule.getText().toLowerCase().contains("full-time"), "Schedule validated");

	    assertTrue(this.summaryPage.desiredShift.getText().toLowerCase().contains("first"), "Desired shift validated");
	    assertTrue(this.summaryPage.date.getText().contains("Mar 15, 2023"), " Date validated");

	    assertTrue(this.summaryPage.willingRelocate.isDisplayed(), "Willing To Relocate validated");
	    assertTrue(this.summaryPage.nationalOpp.isDisplayed(), "National Opp validated");
	    assertTrue(this.summaryPage.reliableTransport.isDisplayed(), "Reliable Transportation validated");

	    assertTrue(this.summaryPage.countriesLegally.getText().contains("United States"), "Country Legally validated");
	    assertTrue(this.summaryPage.country.getText().contains("United States"), "Country validated");
	    assertTrue(this.summaryPage.city.getText().contains("Baltimore"), " City validated");

	    assertTrue(this.summaryPage.g2Comment.getText().contains("G2 Completed"), "G2 Comments validated");
	}

	public void uploadResumeAndEditCandidate() {		
		this.uploadEditPage.uploadResume("Resume");
		assertTrue(this.uploadEditPage.resumePreview.getText().contains("ALEXANDRE MOSIN"), "Validating resume preview");
		this.uploadEditPage.clickCandidateSummaryEdit();
		assertTrue(this.summaryPage.talentName.getText().contains("provarFirst DemoTest provarLast"), "Validating Middle name");
	}
	
	public CandidateDataService getCandidateData() {
		CandidateDataService candidateData = new CandidateDataService();
		candidateData.setFirstName("provarFirst");
		candidateData.setLastName("provarLast");
		candidateData.setEmail("testtprovar"+ Randomness.randomAlphaNumeric(3)+"@gmail.com");
		candidateData.setMobilePhone(MOBILE);
		return candidateData;
	}

	public OverviewDataService getOverviewData() {
		OverviewDataService overviewData = new OverviewDataService();
		overviewData.setSkill(SKILL);
		overviewData.setSkill1(SKILLS1);
		overviewData.setSkill2(SKILLS3);
		overviewData.setLanguage1(LANGUAGE1);
		overviewData.setLanguage2(LANGUAGE2);
		overviewData.setSecurityClearance(SECURITY_CLEARANCE);
		overviewData.setFedClearance(FED_CLEARANCE);
		overviewData.setOverview(OVERVIEW);
		overviewData.setSkillComments(SKILL_COMMENTS);
		overviewData.setGoalInterest(GOAL_INTEREST);
		return overviewData;
	}
	
	public CompensationWorkServiceData getCompensationWorkData() {
		CompensationWorkServiceData compWorkData = new CompensationWorkServiceData();
		compWorkData.setPlacementType(PLACEMENT_TYPE);
		compWorkData.setSchedule(SCHEDULE);
		compWorkData.setWorkplaceType(WORKPLACE_TYPE);
		compWorkData.setDesiredShift(DESIRED_SHIFT);
		return compWorkData;
	}

	public LocationDataService getLocationG2Data() {
		LocationDataService locationData = new LocationDataService();
		locationData.setWillingToRelocate(WILLING_TO_RELOCATE);
		locationData.setNationalOpportunityValue(NATIONAL_OPPORTUNITY_VALUE);
		locationData.setReliableTransportation(RELIABLE_TRANSPORTATION);
		locationData.setCountriesLegallyAuthorizedToWork(COUNTRIES_LEGALLY_AUTHORIZED_TO_WORK);
		locationData.setCountryUK(COUNTRY_UK);
		locationData.setState("Maryland");
		locationData.setCityLocationPreferanceUK(CITY_LOCATION_PREFERANCE_UK);
		locationData.setG2Comments(G2_COMMENTS);
		return locationData;
	}
}