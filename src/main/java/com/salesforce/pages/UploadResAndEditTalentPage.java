package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;

public class UploadResAndEditTalentPage extends BasePage {

	public boolean addcandidateIsDisplayed = false;
	public boolean candsummaryEdit = false;
	@FindBy(xpath = "//article[@class='slds-card cC_TalentResumePreview']//button[text() ='Download']")
	private WebElement btnDownloadOnLandingPage;
	@FindBy(xpath = "//*[contains(@class,'download')]//*[@data-key='download']")
	private WebElement downloadColumnInDocument;
	@FindBy(xpath = "//span[contains(text(),'Middle Name')]/../../input")
	private WebElement middleName;
	@FindBy(xpath = "//a[@title='Find or Add Talent']")
	private WebElement tabAddcandidate;
	@FindBy(xpath = "//button[contains(text(),'Upload Resume/CV')]")
	private WebElement btnUpload;
	@FindBy(xpath = "//input[@type='file']")
	private WebElement btnUploadDoc;
	@FindBy(xpath = "//div[contains(@class,'cC_TalentDuplicateSearch')]//h2[contains(text(),'File Upload')]/parent::div//following-sibling::div//button/span[text()='Save']")
	private WebElement btnUploadSave;
	@FindBy(xpath = "//*[contains(text(),'Add a New Talent')]")
	private WebElement btnAdd;
	@FindBy(xpath = "(//strong[contains(text(),'Job Title')]/../../following-sibling::div//input)[1]")
	private WebElement txtJobTitle;
	@FindBy(xpath = "//*[contains(@class,'cC_TalentAddEditNew')]//*[contains(text(),'Save')]")
	private WebElement btnCandidateSave;
	@FindBy(xpath = "//div[@class='slds-grid slds-grid_vertical-align-center']//span[contains(text(),'Save')]")
	private WebElement btnCandidateProfileSave;
	@FindBy(xpath = "//div[contains(text(),'To ensure this candidateÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â‚¬Å¾Ã‚Â¢s education and employment records are accurately reflected in Candidate Search and Do Not Recruit rules, please take a moment to link the candidateÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â‚¬Å¾Ã‚Â¢s current employment record to an account and the candidateÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â‚¬Å¾Ã‚Â¢s education record(s) to a school.')]/../div[3]/button")
	private WebElement btnalert;
	@FindBy(xpath = "//span[contains(.,'Talent Summary')]/ancestor::article//following::button[text()='Edit']")
	private WebElement btnCandidateSummaryEdit;

	public UploadResAndEditTalentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean uploadResume() {
		waitTime(6);
		addcandidateIsDisplayed = isDisplayed(tabAddcandidate);
		jsClick(tabAddcandidate);
		isDisplayed(btnUpload);
		jsClick(btnUpload);
		// btnUploadDoc.syncInFrame(Constants.ELEMENT_TIMEOUT);
		isDisplayed(btnUploadDoc); 
		btnUploadDoc.sendKeys(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\allegis\\salesforce\\ats\\Documents\\ALEXANDRE MOSIN-ResumeID-434456.doc");
		waitTime(10);
		isDisplayed(btnUploadSave);
		jsClick(btnUploadSave);
		// btnAdd.syncInFrame(Constants.PAGE_TIMEOUT);
		isDisplayed(btnAdd);
		jsClick(btnAdd);
		waitTime(6);
		txtJobTitle.sendKeys("test manager");
		txtJobTitle.sendKeys(Keys.ARROW_DOWN);
		txtJobTitle.sendKeys(Keys.RETURN);
		jsClick(btnCandidateSave);
		jsClick(btnCandidateProfileSave);
		if (isDisplayed(btnalert)) {
			jsClick(btnalert);
			return true;
		} else {
			return false;
		}
	}

	public boolean validateResumePresenceOnSidePaneOnLandingPage(String resumeName) {
		waitTime(6);
		Boolean downloadStatus = isDisplayed(btnDownloadOnLandingPage);
		int noOfRetries = 4;
		while (!downloadStatus && noOfRetries > 1) {
			noOfRetries--;
			driver.navigate().refresh();
			downloadStatus = isDisplayed(downloadColumnInDocument);
		}
		By resume = By.xpath("//span[contains(text(),'" + resumeName + "')]");
		return isDisplayed(driver.findElement(resume));
	}

	public boolean clickCandidateSummaryEdit() {
		waitTime(6);
		if (isDisplayed(btnCandidateSummaryEdit)) {
			candsummaryEdit = isDisplayed(btnCandidateSummaryEdit);

			jsClick(btnCandidateSummaryEdit);

			candsummaryEdit = true;
		} else {
			candsummaryEdit = false;
		}
		return candsummaryEdit;
	}
}