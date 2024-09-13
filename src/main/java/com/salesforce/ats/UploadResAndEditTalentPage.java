package com.salesforce.ats;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;

public class UploadResAndEditTalentPage extends BasePage {

	@FindBy(xpath = "//span[contains(text(),'Middle Name')]/../../input")
	private WebElement middleName;
	@FindBy(xpath = "//span[contains(.,'Talent Summary')]/ancestor::article//following::button[text()='Edit']")
	private WebElement btnCandidateSummaryEdit;
	@FindBy(xpath = "//button[@title='Resume']")
	private WebElement btnResume;
	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement upload;
	@FindBy(xpath = "//p[text()='Show More']")
	private WebElement showMoreProfile;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "(//button/span[text()='Save'])[2]")
	private WebElement btnUploadSave;
	@FindBy(xpath = "//div[@class='slds-modal slds-fade-in-open cC_TalentDocumentModal']//select[@class=' select']")
	private WebElement lstDoctype;
	@FindBy(xpath = "(//div[@id='modaldialogAddDcoument']//span[contains(text(),'Default')]/parent::label/following-sibling::input)[last()]")
	private WebElement chkDefault;
	@FindBy(xpath = "//h1[@id='tlp-name']")
	public WebElement talentName;
	@FindBy(xpath = "//button[@title='Skip Merge & Upload']")
	private WebElement skipMergeButton;
	@FindBy(xpath = "//div[@class='WordSection1']/h1//span[text()='ALEXANDRE MOSIN']")
	public WebElement resumePreview;
	@FindBy(xpath = "//input[@data-interactive-lib-uid='9']")
	private WebElement preferName;

	public UploadResAndEditTalentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void uploadResume(String docType) {

		System.out.println("Uploading Resume");
		waitTime(2);
		isDisplayed(btnResume);
		jsClick(btnResume);
		isDisplayed(upload);
		jsClick(upload);
		String resume = getResumePath();
		fluentWait("(//input[@type='file'])[2]");
		driver.findElement(By.xpath("(//input[@type='file'])[2]")).sendKeys(resume);
		if (docType.equalsIgnoreCase("Resume")) {
			waitTime(2);
			select(lstDoctype, "Resume");
			waitTime(2);
			jsClick(chkDefault);
		} else {
			select(lstDoctype, docType);
			waitTime(3);
		}
		waitTime(2);
		System.out.println("Uploaded------------------------ " + resume);
		isDisplayed(btnUploadSave);
		jsClick(btnUploadSave);
		waitTime(3);
		verifyElementIsPresent("//button[@title='Skip Merge & Upload']");
		if (isDisplayed(skipMergeButton)) {
			jsClick(skipMergeButton);
		}
		if (!isDisplayed(resumePreview)) {
			isDisplayed(upload);
			jsClick(upload);
			driver.findElement(By.xpath("(//input[@type='file'])[2]")).sendKeys(resume);
			if (docType.equalsIgnoreCase("Resume")) {
				waitTime(2);
				select(lstDoctype, "Resume");
				waitTime(2);
				jsClick(chkDefault);
			} else {
				select(lstDoctype, docType);
				waitTime(3);
			}
			waitTime(2);
			System.out.println("Uploaded------------------------ " + resume);
			isDisplayed(btnUploadSave);
			jsClick(btnUploadSave);
			waitTime(3);
			verifyElementIsPresent("//button[@title='Skip Merge & Upload']");
			if (isDisplayed(skipMergeButton)) {
				jsClick(skipMergeButton);
			}
		}
		fluentWait("//div[@class='WordSection1']/h1//span[text()='ALEXANDRE MOSIN']");
	}

	private String getResumePath() {
		String filename = "resumetest.doc";
		// return System.getProperty("user.dir") +
		// "\\src\\main\\resources\\ALEXANDRE_MOSIN-ResumeID-434456.doc";
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + filename;
		System.out.println("Systems PATH:" + path);
		return path;
	}

	public void clickCandidateSummaryEdit() {
		System.out.println("Editing Talent by adding Middle name ");
		isDisplayed(btnResume);
		jsClick(btnResume);
		isDisplayed(btnCandidateSummaryEdit);
		jsClick(btnCandidateSummaryEdit);
		isDisplayed(showMoreProfile);
		jsClick(showMoreProfile);
		isDisplayed(middleName);
		jsClick(middleName);
		waitTime(1);
		middleName.sendKeys("DemoTest");
		middleName.sendKeys(Keys.ENTER);
		waitTime(2);
		isDisplayed(btnSave);
		jsClick(btnSave);
		waitTime(6);
		isDisplayed(talentName);
	}
}