package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.BasePage;

public class ReqOpportinityPage extends BasePage{
	
	// 1st Section Req Foundation
	@FindBy(xpath = "//li[@class='visible']//button[text()='New Opportunity']")
	private WebElement lnkNewOpportunity;
	@FindBy(xpath = "//*[@title='Next']")
	private WebElement btnNext;
	@FindBy(xpath = "//*[@role='progressbar']")
	private WebElement eleReqProgressBar;
	@FindBy(xpath = "//*[@id='label-locationId']/..//input")
	private WebElement txtLocation;
	@FindBy(xpath = "//label[contains(text(),'Reason')]/following-sibling::div//select")
	private WebElement lstdraftReason;
	@FindBy(xpath = "//*[@id='label-description']/..//textarea")
	private WebElement txtJobDescription;
	@FindBy(xpath = "//textarea[contains(@placeholder,'Enter additional qualifications')]")
	private WebElement txtAddQualification;
	@FindBy(xpath = "//*[@id='label-extCommunities']/..//textarea")
	private WebElement txtExtCommunities;
	@FindBy(xpath = "//*[contains(text(),'EVP')]/parent::div//textarea")
	private WebElement txtEVP;
	@FindBy(xpath = "//*[text()='Work Environment']/parent::div//textarea")
	private WebElement txtWorkEnv;
	@FindBy(xpath = "//*[contains(text(),'Why is the position')]/parent::div//textarea")
	private WebElement txtWhyPositonOpen;
	@FindBy(xpath = "//*[contains(text(),'Business Driver')]/parent::div//textarea")
	private WebElement txtBusinessDriver;
	@FindBy(xpath = "//*[@id='label-account']/..//input")
	private WebElement txtAccountName;
	@FindBy(xpath = "//*[@id='label-hiring']/..//input")
	private WebElement txtHiringMgr;
	@FindBy(xpath = "//*[@id='label-office']/..//input")
	private WebElement txtOffice;
	@FindBy(xpath = "//*[@id='label-positions']/..//input")
	private WebElement txtTotalPostions;
	@FindBy(xpath = "//label[text()='Client Opened Positions']/..//lightning-input//input")
	private WebElement txtDateClient;
	@FindBy(xpath = "//label[contains(text(),'Experience Level')]//following-sibling::div//select")
	private WebElement lstExperienceLevel;
	
	// 2nd second Placement Type
	@FindBy(xpath = "//*[contains(text(),'Other')]/parent::div//input")
	private WebElement txtOtherCompensationDetail;
	@FindBy(xpath = "//*[@id='label-inputSalaryMin']/..//input")
	private WebElement txtSalaryMin;
	@FindBy(xpath = "//*[@id='label-inputSalaryMax']/..//input")
	private WebElement txtSalaryMax;
	@FindBy(xpath = "//*[@id='label-inputBonus']/..//input")
	private WebElement txtBonus;
	@FindBy(xpath = "//*[@id='label-inputFeePerc']/..//input")
	private WebElement txtFee;
	@FindBy(xpath = "//*[@id='label-flatfee']/..//input")
	private WebElement txtFlatfee;
	@FindBy(xpath = "//*[@id='label-inputDurationName']/..//input")
	private WebElement txtDuration;
	@FindBy(xpath = "//*[@id='label-inputBillRateMin']/..//input")
	private WebElement txtBillRateMin;
	@FindBy(xpath = "//*[@id='label-inputBillRateMax']/..//input")
	private WebElement txtBillRateMax;
	@FindBy(xpath = "//*[@id='label-inputPayRateMin']/..//input")
	private WebElement txtPayRateMin;
	@FindBy(xpath = "//*[@id='label-inputPayRateMax']/..//input")
	private WebElement txtPayRateMax;
	@FindBy(xpath = "//*[@id='label-inputStdBurden']/..//input")
	private WebElement txtTotalBurden;
	
	// 3rd section Business Information
	@FindBy(xpath = "//*[text()='Interview Information']/..//textarea")
	private WebElement eleInterviewInfo;
	@FindBy(xpath = "//*[contains(text(),'Additional Compliance')]/..//textarea")
	private WebElement txtAdditionalCompliance;
	@FindBy(xpath = "//*[contains(text(),'Competition')]/..//textarea")
	private WebElement txtCompetitionInfo;
	@FindBy(xpath = "//*[contains(text(),'Additional Info')]/..//textarea")
	private WebElement txtAdditionalInfo;
	// 4th section Product Information
	@FindBy(xpath = "//*[contains(text(),'OpCo')]/..//following-sibling::*//select")
	private WebElement lstOpco;
	@FindBy(xpath = "//h1//span[contains(@class,'title')]")
	private WebElement lblReqOpptyName;
	// Buissness Information Section - Help Text
	@FindBy(xpath = "//*[contains(text(),'Job titles used for Match')]//following-sibling::div//input")
	private WebElement eleJobTitleUsedToMatch;
	@FindBy(xpath = "//label[contains(text(),'Division')]/following::select[1]")
	private WebElement lstDivision;
	@FindBy(xpath = "//label[contains(text(),'Segment')]/following::select[1]")
	private WebElement lstSegment;
	@FindBy(xpath = "//label[contains(text(),'Jobcode')]/following::select[1]")
	private WebElement lstJobcode;
	@FindBy(xpath = "//label[contains(text(),'Category')]/following::select[1]")
	private WebElement lstCategory;
	@FindBy(xpath = "//label[contains(text(),'Main Skill')]/following::select[1]")
	private WebElement lstMainSkill;
	@FindBy(xpath = "//*[text()='Office is required.']")
	private WebElement eleOfficeErrorMsg;
	
	public ReqOpportinityPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
