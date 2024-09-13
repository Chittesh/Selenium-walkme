package com.salesforce.esf;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.salesforce.BasePage;
import com.salesforce.data.ESFServiceData;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.LoginPage;
import com.salesforce.utilities.EnvironmentUtility;
import com.salesforce.utilities.SalesForceUtilities;
import com.salesforce.utilities.TestEnvironment;

public class EsfWorkflow {
	
	WebDriver driver;
	EnvironmentUtility utility;
	
	public EsfWorkflow(WebDriver _driver) {
		this.driver = _driver;
		utility = new EnvironmentUtility();
	}
	
	public void verifyApplicationIsLaunchedAndHomePageIsAvailable(String role) {
		utility.launchSalesForce();
		loginToApplication(role);
		verfiyHomePageTab();	
	}

	public void verfiyHomePageTab() {
		HomePage homePage = new HomePage(this.driver);
		Assert.assertTrue(homePage.verifyHomePageTab(), "Verify Home Page Tab is present");
	}

	public void loginToApplication(String role) {
		LoginPage loginPage = new LoginPage(this.driver);
		loginPage.roleLogin(role);
	}
	
	public String performBasicWorkflowForEsf(String role, BasePage basePage, String[] id) {
		verifyApplicationIsLaunchedAndHomePageIsAvailable(role);
		navigatedToTalentRecordAndVerifyOpportunityPageLoaded(basePage, id);		
		verifyUserOnTalentDetailsAndScoreCardIsLinked(basePage);		
		verifyScoreCardForSubmittedStatus(basePage);		
		verifyScoreCardForOfferAcceptedStaus(basePage);
		clickToESFLinkAndValidateSObject(basePage);		
		verifyErrorMessageAfterClickToMarkStage(basePage);		
		setMandatoryFieldForESF(basePage);		
		createBurdenAndValidateBurdenObject(basePage);
			
		verifyUnderReviewStage(basePage);
		
		verifyErrorMessageUnderReview(basePage);

		String esfURL = getEsfUrl();		
		logoutSalesforceAndDeleteCookie(basePage);
		return esfURL;
	}

	public void performingActionUnderTGSD1Access(String role, BasePage basePage, String esfURL) {
		String profile = basePage.getProfileForTGSD1(role);
		verifyApplicationIsLaunchedAndHomePageIsAvailable(profile);
		this.driver.get(esfURL);
		verifyErrorMessageForPeopleSoft(basePage);
		addToPeopleSoftDetailsAndSetBillRate(basePage);		
		verifyProjectUnderReviwStage(basePage);		
		basePage.clickOnMarkStage();
		logoutSalesforceAndDeleteCookie(basePage);
	}

	public void performActionsUnderPOAAccess(String role, BasePage basePage, String esfURL) {
		String profile;
		profile = basePage.getProfileForPOA(role);
		verifyApplicationIsLaunchedAndHomePageIsAvailable(profile);
		this.driver.get(esfURL);		
		verifyErrorMessagePOAUser(basePage);
		basePage.addBurden();
		verifyProjectUnderReviwStage(basePage);		
		basePage.selectMandFieldForUnderProjectReview();
		verifyPendingApprovalStage(basePage);
	}

	public void verifyPendingApprovalStage(BasePage basePage) {
		basePage.clickOnMarkStage();		
		basePage.verifyPendingApporvalStage();
	}

	public void verifyErrorMessagePOAUser(BasePage basePage) {
		basePage.clickOnMarkStage();
		basePage.verifyErrorMessagePOAUSer();
	}

	public void verifyProjectUnderReviwStage(BasePage basePage) {
		basePage.clickOnMarkStage();
		basePage.verifyUnderProjectReviewSatge();
	}

	public void addToPeopleSoftDetailsAndSetBillRate(BasePage basePage) {
		basePage.addPeopleSoftDetails();
		basePage.setBillRateRegular();
	}

	public void verifyErrorMessageForPeopleSoft(BasePage basePage) {
		basePage.clickOnMarkStage();
		basePage.verifyErrorMessagePeopleSoftId();
	}

	public String getEsfUrl() {
		return this.driver.getCurrentUrl();
	}

	public void logoutSalesforceAndDeleteCookie(BasePage basePage) {
		basePage.logoutSalesforce();
		basePage.deleteCookie();
	}

	public void verifyErrorMessageUnderReview(BasePage basePage) {
		basePage.clickOnMarkStage();
		basePage.verifyErrorMessageunderReview();
	}

	public void verifyUnderReviewStage(BasePage basePage) {
		basePage.verifyNewSatge();
		basePage.clickOnMarkStage();
		basePage.verifyUnderReviewSatge();
	}
	
	public void createBurdenAndValidateBurdenObject(BasePage basePage) {
		basePage.createNewBurdenForESF("Burden Type", "base", "AHB");
		basePage.clickOnESFIDFromBurdern();		
		Assert.assertTrue(basePage.validateSObjectPresenceOnURL("Employee_Status_Form__c"),
				"Verify user is Navigated to Employee_Status_Form__c page");
	}

	public void setMandatoryFieldForESF(BasePage basePage) {
		basePage.populateValuesForGlobalServiceAndCustomerInfo();
		basePage.selectMandatoryJobInformation();
		basePage.selectMandatoryTalentOnBoarding();
		basePage.setMandatoryPayAndBillRate();
		basePage.selectMandatoryComissionInformation("Demo User Producer", "Demo User Producer");
	}

	public void verifyErrorMessageAfterClickToMarkStage(BasePage basePage) {
		basePage.verifyNewSatge();
		basePage.clickOnMarkStage();
		basePage.verifyErrorMessage();
	}

	public void clickToESFLinkAndValidateSObject(BasePage basePage) {
		basePage.clickOnESF();
		Assert.assertTrue(basePage.validateSObjectPresenceOnURL("Employee_Status_Form__c"),
				"Verify user is Navigated to Employee_Status_Form__c page");
	}

	public void verifyUserOnTalentDetailsAndScoreCardIsLinked(BasePage basePage) {
		basePage.clickOnTalentDetails();
		Assert.assertEquals(basePage.getSubmittalStatus(), "Linked", "Verify Submittal status");
		basePage.verifyScoreCardForLinked();
	}

	public void verifyScoreCardForOfferAcceptedStaus(BasePage basePage) {
		basePage.clickOnEdit();
		basePage.chageStatusToOfferAccept();
		Assert.assertTrue(basePage.verifyESFLink(), "Verify ESF Link is present");
		basePage.verifyScoreCardForOfferAccept();
	}

	public void verifyScoreCardForSubmittedStatus(BasePage basePage) {
		basePage.clickOnEdit();
		basePage.chageStatusToSubmitted();
		basePage.verifyScoreCardForSubmitted();
	}

	public void navigatedToTalentRecordAndVerifyOpportunityPageLoaded(BasePage basePage, String[] id) {
		basePage.navigateToTalentRecord(id[2], "Opportunity");		
		basePage.verifyOpportunityPageLoaded();
	}

	public String[] getESFDataFromApiService(String role) throws Exception {
		SalesForceUtilities objSalesForceUtilities = new SalesForceUtilities(this.driver);
		String id[] = objSalesForceUtilities.createServiceESF(getCandidateData(role));
		return id;
	}
	
	private ESFServiceData getCandidateData(String role) {
		ESFServiceData data = new ESFServiceData();
		data.setRole(role);
		data.setStatus("Linked");
		data.setNoOfPos("5");
		data.setIsTGS("yes");
		data.setLapFlag("");
		return data;
	}
}
