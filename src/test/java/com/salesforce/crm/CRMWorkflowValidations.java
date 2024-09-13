package com.salesforce.crm;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.allegis.salesforce.crm.OpportunityCreationPage;
import com.salesforce.BasePage;
import com.salesforce.pages.EditReqOppPage;

public class CRMWorkflowValidations extends BasePage{
	
	OpportunityCreationPage opportunityCreationPage;
	EditReqOppPage editReqOppPage;
	
	
    public CRMWorkflowValidations(WebDriver driver) {
    	super(driver);
        this.opportunityCreationPage = new OpportunityCreationPage(driver);
        this.editReqOppPage = new EditReqOppPage(driver);
    }
    
    public void verifyErrorMessagesForRequiredFields() {
        assertTrue(this.opportunityCreationPage.lblErrorCategory.isDisplayed(),"verify error for Category");
        assertTrue(this.opportunityCreationPage.lblErrorDivision.isDisplayed(),"verify error for Division");
        assertTrue(this.opportunityCreationPage.lblErrorDraftReason.isDisplayed(),"verify error for Draft Reason");
        assertTrue(this.opportunityCreationPage.lblErrorFunc_NonFunc.isDisplayed(),"verify error for Functional and Non Funtional");
        assertTrue(this.opportunityCreationPage.lblErrorIsTGSRequirement.isDisplayed(),"verify error for is TGS requirement");
        assertTrue(this.opportunityCreationPage.lblErrorJobCode.isDisplayed(),"verify error for Job Code");
        assertTrue(this.opportunityCreationPage.lblErrorJobTitle.isDisplayed(),"verify error for Job Title");
        assertTrue(this.opportunityCreationPage.lblErrorMainSkill.isDisplayed(),"verify error for Main Skill");
        assertTrue(this.opportunityCreationPage.lblErrorOffice.isDisplayed(),"verify error for Office");
        assertTrue(this.opportunityCreationPage.lblErrorPrimaryBuyer.isDisplayed(),"verify error for Primary Buyer");
        assertTrue(this.opportunityCreationPage.lblErrorSegment.isDisplayed(),"verify error for Segment");
        assertTrue(this.opportunityCreationPage.lblErrorSkillSpeciality.isDisplayed(),"verify error for Skill Speciality");
    }
    public void verifyFieldsAutoPopulatedOnCreateReq() {
    	waitTime(4);
        fluentWait("//*[@id='label-account']/..//input");
        assertTrue(this.opportunityCreationPage.isAccountNameAutoPopulated(),"verify Account Name autopopulated");
        assertTrue(this.opportunityCreationPage.isHiringManagerAutoPopulated(),"verify Hiring Manager autopopulated");
        assertTrue(this.opportunityCreationPage.isWorkSiteLoacationAutoPopulated(),"verify Work Site Location autopopulated");
        assertTrue(this.opportunityCreationPage.isPlacementTypeAutoPopulated(),"verify Placement Type autopopulated");
        assertTrue(this.opportunityCreationPage.isQualifyingStageAutoPopulated(),"verify Qualifying Stage autopopulated");
        assertTrue(this.opportunityCreationPage.isOpCoAutoPopulated(),"verify OpCo autopopulated");
    }
    
    public void verifyCreatedOppValues() {
    	String office = "TEK- test-7312 Parkway Dr-22222";
		String draftReason = "Qualifying";
		String jobTitle = "Java Developer";
		String skillSpeciality = "Communications";
		String funcNonFuncPosition = "Functional";
		String isThiTGSRequirement = "No";
		String division = "Applications";
		String segment = "Staffing";
		String jobcode = "Developer";
		String category = "Application - CRM";
		String mainSkill = "Application-CRM - SAP";
		String primaryBuyerNextGen = "Core Applications";
		
		verifyElementIsPresent("//h1//span[contains(@class,'title')]");
		assertTrue(this.opportunityCreationPage.lblReqOpptyName.getDomAttribute("title").startsWith("O-"), "Verify starts with O-");
		System.out.println(driver.getCurrentUrl());
		fluentWait(getLocatorInfo(this.opportunityCreationPage.lblaccName));
		assertTrue(this.opportunityCreationPage.lblaccName.getText().equals(OpportunityCreationPage.accNameToValidate),"verify account name");
		assertTrue(this.opportunityCreationPage.lblhiringManager.getText().equals(OpportunityCreationPage.hiringMngrToValidate),"verify hiring Manager");
		verifyElementIsPresent("//label[contains(text(),'Worksite Location')]/following-sibling::div[1]");
		jsScroll(this.opportunityCreationPage.lblworksitelocation);
		System.out.println(this.opportunityCreationPage.lblworksitelocation.getText());
		assertTrue(this.opportunityCreationPage.lblworksitelocation.getText().equals(OpportunityCreationPage.workSiteLocationValidate),"Verify workSite Location");
		assertTrue(this.opportunityCreationPage.lblqualifyingStage.getDomProperty("value").equals(OpportunityCreationPage.qualifyingStageToValidate),"Verify qualifying stage");
		assertTrue(this.opportunityCreationPage.lblplacementType.getDomProperty("value").equals(OpportunityCreationPage.placementTypeToValidate),"verify placement type");
		assertTrue(this.opportunityCreationPage.lblOpco.getDomProperty("value").equals(OpportunityCreationPage.opCoToValidate));
		assertTrue(this.opportunityCreationPage.lblOffice.getText().equals(office),"verify office");
		assertTrue(this.opportunityCreationPage.lbldraftReason.getText().equals(draftReason),"verify draft reason");
		assertTrue(this.opportunityCreationPage.lbljobTitle.getText().equals(jobTitle),"veify job title");
		assertTrue(this.opportunityCreationPage.lblskillSpeciality.getDomProperty("value").equals(skillSpeciality),"verify Skill Speciality");
		assertTrue(this.opportunityCreationPage.lblfunc_NonFunc.getDomProperty("value").equals(funcNonFuncPosition),"Verify Funtional Non Funtional Position");
		assertTrue(this.opportunityCreationPage.lbltgsRequirement.getDomProperty("value").equals(isThiTGSRequirement),"Verify TGS requirement");
		assertTrue(this.opportunityCreationPage.lbldivision.getDomProperty("value").equals(division),"Verify Division");
		assertTrue(this.opportunityCreationPage.lblsegment.getDomProperty("value").equals(segment),"Verify Segment");
		assertTrue(this.opportunityCreationPage.lbljobCode.getDomProperty("value").equals(jobcode),"Verify job code");
		assertTrue(this.opportunityCreationPage.lblcategory.getDomProperty("value").equals(category),"Verify Category");
		assertTrue(this.opportunityCreationPage.lblmainSkill.getDomProperty("value").equals(mainSkill),"Verify Main Skill");
		assertTrue(this.opportunityCreationPage.lblprimaryBuyer.getDomProperty("value").equals(primaryBuyerNextGen),"verify primaryBuyer");
		
	}
    
    public void verifyCreateReqOpportunity() {
    	opportunityCreationPage.selectRecordTypeRadioBtn();
		verifyFieldsAutoPopulatedOnCreateReq();
		opportunityCreationPage.getAutoPopulatedValues();
		opportunityCreationPage.clickOnCreateReqBtn();
		verifyErrorMessagesForRequiredFields();
		opportunityCreationPage.addRequiredDataForReqCreation();
		opportunityCreationPage.clickOnCreateReqBtn();
		verifyCreatedOppValues();
    }
    
    public void verifyErrorMsgEditStageToQualifying() {
    	System.out.println("Verify Error msg and changing stage");
    	this.editReqOppPage.editQualifyingStage();
		assertTrue(this.editReqOppPage.errorMessage.getText().contains(
				"There are 13 errors that need to be addressed before creating this req in a Qualified/RedZone state"),"Validating Error message");
		
    }
    
    public void verifyBannerQualifiedAfterAutoPopulatingFields() {
    	System.out.println("Populating required fields");
    	this.editReqOppPage.addRequiredFieldsSave();
    	fluentWait("//*[contains(text(),'Match Talent')]");
    	//waitTime(2);
		assertTrue(this.editReqOppPage.bannerStage.getText().contains("Qualified"),"Validating Qualifying Stage Successfully");
		
    }
}
