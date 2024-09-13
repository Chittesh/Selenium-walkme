package com.salesforce.ats;

import org.testng.annotations.Test;

import com.salesforce.utilities.TestEnvironment;

public class TalentContactTest extends TestEnvironment {

	@Test()
	public void verifyTalentSummaryWithUploadResumeAndEditTalent() {
		
		String role ="TEK_SD1_TEST";
		launchSalesForceUrl();
		TalentWorkFlow workflow = new TalentWorkFlow(getDriver());

		// Login to the application 
		workflow.login(role);
		// Verify that the Home Page Functionality
		workflow.verifyMyTalent();
		// Create new candidate
		workflow.createCandidate(role);
		// Verify that the Talent Summary page 
		workflow.verifyTalentSummaryPage();
		// Upload resume and edit candidate's details.
		workflow.uploadResumeAndEditCandidate();
	}
	
	

}