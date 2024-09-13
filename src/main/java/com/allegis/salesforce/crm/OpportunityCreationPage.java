package com.allegis.salesforce.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.BasePage;

public class OpportunityCreationPage extends BasePage{
	public static String accNameToValidate;
	public static String hiringMngrToValidate;
	public static String workSiteLocationValidate;
	public static String placementTypeToValidate;
	public static String qualifyingStageToValidate;
	public static String opCoToValidate;
	
	@FindBy(xpath = "//li[@class='visible']//button[text()='New Opportunity']")
	public WebElement lnkNewOpportunity;
	@FindBy(xpath = "//*[@title='Next']")
	public WebElement btnNext;
	@FindBy(xpath = "//*[@id='label-account']/..//input")
	public WebElement txtAccountName;
	@FindBy(xpath = "//*[@id='label-hiring']/..//input")
	public WebElement txtHiringMgr;
	@FindBy(xpath = "//*[@id='label-office']/..//input")
	public WebElement txtOffice;
	@FindBy(xpath = "//*[@id='label-locationId']/..//input")
	public WebElement txtWrkSiteLocation;
	@FindBy(xpath = "//label[contains(text(),'Qualifying Stage')]/following-sibling::div//select")
	public WebElement lstQualifyingStage;
	@FindBy(xpath = "//label[contains(text(),'Draft Reason')]/following-sibling::div//select")
	public WebElement lstDraftReason;
	@FindBy(xpath = "//label[contains(text(),'Placement Type')]/following-sibling::div//select")
	public WebElement lstPlacementType;
	@FindBy(xpath = "//label[contains(text(),'OpCo')]/following-sibling::div//select")
	public WebElement lstOpCo;
	@FindBy(xpath = "//button[text()='Create Req']")
	public WebElement btnCreateReq;
	@FindBy(xpath = "//div/*[text()='Job Title is required.']")
	public WebElement lblErrorJobTitle;
	@FindBy(xpath = "//div/*[text()='Office is required.']")
	public WebElement lblErrorOffice;
	@FindBy(xpath = "//div/*[text()='Draft Reason is required.']")
	public WebElement lblErrorDraftReason;
	@FindBy(xpath = "//div/*[text()='Skill Specialty is required.']")
	public WebElement lblErrorSkillSpeciality;
	@FindBy(xpath = "//div/*[text()='Functional/Non-Functional Position is required.']")
	public WebElement lblErrorFunc_NonFunc;
	@FindBy(xpath = "//div/*[text()='Division is required.']")
	public WebElement lblErrorDivision;
	@FindBy(xpath = "//div/*[text()='Segment is required.']")
	public WebElement lblErrorSegment;
	@FindBy(xpath = "//div/*[text()='Job Code is required.']")
	public WebElement lblErrorJobCode;
	@FindBy(xpath = "//div/*[text()='Category is required.']")
	public WebElement lblErrorCategory;
	@FindBy(xpath = "//div/*[text()='Main Skill is required.']")
	public WebElement lblErrorMainSkill;
	@FindBy(xpath = "//div/*[text()='Primary Buyer (Next Gen) is required.']")
	public WebElement lblErrorPrimaryBuyer;
	@FindBy(xpath = "//div/*[text()='Is this a TGS Requirement is required.']")
	public WebElement lblErrorIsTGSRequirement;
	@FindBy(xpath = "//span[contains(text(),'Job Title')]/..//following-sibling::*//input")
	public WebElement txtJobTitle;
	@FindBy(xpath = "//label[contains(text(),'Skill Speciality')]/following-sibling::div//select")
	public WebElement lstSkillSpeciality;
	@FindBy(xpath = "//label[contains(text(),'Functional/Non-Functional Position')]/following-sibling::div//select")
	public WebElement lstFunc_NonFunc;
	@FindBy(xpath = "//label[contains(text(),'TGS requirement')]/following-sibling::div//select")
	public WebElement lstTGSRequirement;
	@FindBy(xpath = "//label[contains(text(),'Division')]/following::select[1]")
	public WebElement lstDivision;
	@FindBy(xpath = "//label[contains(text(),'Segment')]/following::select[1]")
	public WebElement lstSegment;
	@FindBy(xpath = "//label[contains(text(),'Jobcode')]/following::select[1]")
	public WebElement lstJobcode;
	@FindBy(xpath = "//label[contains(text(),'Category')]/following::select[1]")
	public WebElement lstCategory;
	@FindBy(xpath = "//label[contains(text(),'Main Skill')]/following::select[1]")
	public WebElement lstMainSkill;
	@FindBy(xpath = "//label[contains(text(),'Primary Buyer')]/following-sibling::div//select")
	public WebElement lstPrimaryBuyer;
	@FindBy(xpath = "//h1//span[contains(@class,'title')]")
	public WebElement lblReqOpptyName;
	
	// Opportunity Details
	@FindBy(xpath = "//*[contains(text(), 'Account Name')]//a")
	public WebElement lblaccName;
	@FindBy(xpath = "//*[contains(text(), 'Hiring Manager')]/following-sibling::*/*[contains(@class, 'static')]/a")
	public WebElement lblhiringManager;
	@FindBy(xpath = "//label[contains(text(),'Worksite Location')]/following-sibling::div[1]")
	public WebElement lblworksitelocation;
	@FindBy(xpath = "//*[contains(text(), 'Qualifying Stage')]//*[contains(@class,'static')]")
	public WebElement lblqualifyingStage;
	@FindBy(xpath = "//*[contains(text(), 'Placement Type')]//*[contains(@class,'static')]")
	public WebElement lblplacementType;
	@FindBy(xpath = "//*[contains(text(), 'Opco')]/following-sibling::*")
	public WebElement lblOpco;	
	@FindBy(xpath = "//*[text()='Office']/parent::*//a")
	public WebElement lblOffice;	
	@FindBy(xpath = "//*[text()='Draft Reason']//following-sibling::div/*")
	public WebElement lbldraftReason;	
	@FindBy(xpath = "//*[@class='cCRM_JobTitle']//*[contains(@class, 'label')]")
	public WebElement lbljobTitle;	
	@FindBy(xpath = "//*[contains(text(), 'Skill Speciality')]//*[contains(@class,'static')]")
	public WebElement lblskillSpeciality;	
	@FindBy(xpath = "//*[contains(text(), 'Functional/Non-Functional')]//*[contains(@class,'static')]")
	public WebElement lblfunc_NonFunc;	
	@FindBy(xpath = "//*[contains(text(), 'TGS requirement')]/..//*[contains(@class,'static')]")
	public WebElement lbltgsRequirement;	
	@FindBy(xpath = "//*[contains(text(), 'Division')]/following-sibling::*")
	public WebElement lbldivision;
	@FindBy(xpath = "(//*[contains(text(), 'Segment')])[2]/following-sibling::*")
	public WebElement lblsegment;	
	@FindBy(xpath = "//*[contains(text(), 'Jobcode')]/following-sibling::*")
	public WebElement lbljobCode;
	@FindBy(xpath = "(//*[contains(text(), 'Category')])[5]/following-sibling::*")
	public WebElement lblcategory;	
	@FindBy(xpath = "(//section[@id=\"overviewCriteria\"]//lightning-formatted-text)[6]")
	public WebElement lblmainSkill;	
	@FindBy(xpath = "(//section[@id=\"overviewCriteria\"]//lightning-formatted-text)[7]")
	public WebElement lblprimaryBuyer;
	
	
	public OpportunityCreationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectRecordTypeRadioBtn() {
		verifyElementIsPresent("//li[@class='visible']//button[text()='New Opportunity']");
		lnkNewOpportunity.click();
		verifyElementIsPresent("//div[contains(text(),'Req')]/parent::td/preceding-sibling::td//input");
		WebElement eleRecordType = driver.findElement(By.xpath("//div[contains(text(),'Req')]/parent::td/preceding-sibling::td//input"));
		eleRecordType.click();
		btnNext.click();
	}
	
	public boolean isAccountNameAutoPopulated() {
		if(txtAccountName.getDomProperty("value").isEmpty()) {
			waitTime(5);
		}
		boolean result = txtAccountName.getDomProperty("value").isEmpty();
		return !result;
	}
	public boolean isHiringManagerAutoPopulated() {
		
		boolean result = txtHiringMgr.getDomProperty("value").isEmpty();
		return !result;
	}
	public boolean isWorkSiteLoacationAutoPopulated() {
		boolean result = txtWrkSiteLocation.getDomProperty("value").isEmpty();
		return !result;
	}
	public boolean isQualifyingStageAutoPopulated() {
		boolean result = lstQualifyingStage.getDomProperty("value").isEmpty();
		return !result;
	}
	public boolean isPlacementTypeAutoPopulated() {
		boolean result = lstPlacementType.getDomProperty("value").isEmpty();
		return !result;
	}
	public boolean isOpCoAutoPopulated() {
		boolean result = lstOpCo.getDomProperty("value").isEmpty();
		return !result;
	}
	public void clickOnCreateReqBtn() {
		btnCreateReq.click();
	}
	
	public void addRequiredDataForReqCreation() {
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
		
		txtOffice.sendKeys(office);
		String officexpath=String.format("//*[contains(@title,'%s')]", office);
		verifyElementIsPresent(officexpath);
		jsClick(driver.findElement(By.xpath("//*[contains(@title,'" + office + "')]")));
		select(lstDraftReason, draftReason);
		txtJobTitle.sendKeys(jobTitle);
		select(lstDivision, division);
		waitTime(2);
		select(lstSegment, segment);
		waitTime(1);
		select(lstJobcode, jobcode);
		waitTime(3);
		select(lstCategory, category);
		waitTime(3);
		select(lstSkillSpeciality, skillSpeciality);
		waitTime(1);
		select(lstFunc_NonFunc, funcNonFuncPosition);
		waitTime(1);
		select(lstTGSRequirement, isThiTGSRequirement);
		waitTime(1);
		select(lstMainSkill, mainSkill);
		waitTime(1);
		select(lstPrimaryBuyer, primaryBuyerNextGen);
	}
		
	public void getAutoPopulatedValues() {
		accNameToValidate = txtAccountName.getDomProperty("value");
		hiringMngrToValidate = txtHiringMgr.getDomProperty("value");
		workSiteLocationValidate = txtWrkSiteLocation.getDomProperty("value");
		placementTypeToValidate = lstPlacementType.getDomProperty("value");
		qualifyingStageToValidate = lstQualifyingStage.getDomProperty("value");
		opCoToValidate = lstOpCo.getDomProperty("value");
	}
}
