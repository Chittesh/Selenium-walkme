package com.salesforce.ats;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;
import com.salesforce.servicedata.CandidateDataService;
import com.salesforce.servicedata.CompensationWorkServiceData;
import com.salesforce.servicedata.LocationDataService;
import com.salesforce.servicedata.OverviewDataService;

public class CreateCandidatePage extends BasePage {

	@FindBy(xpath = "//div[@class='form-element']/input")
	private WebElement txtDate;
	@FindBy(xpath = "//div[@aria-label='Desired Shift']//input")
	private WebElement txtDesiredShift;
	@FindBy(xpath = "//div[@aria-labelledby='Location Preferences']")
	private WebElement showMoreLocation;
	@FindBy(xpath = "//div[@aria-labelledby='G2 Activities']")
	private WebElement showMoreG2;
	@FindBy(xpath = "//table[@class='slds-table slds-table_bordered slds-table_cell-buffer']")
	private WebElement tblSaveSearch;
	@FindBy(xpath = "//strong[contains(text(),'Languages')]/../following-sibling::*//input")
	private WebElement txtAddCandidateLanguages;
	@FindBy(xpath = "(//strong[contains(text(),'Job Title')]/../../following-sibling::div//input)[1]")
	private WebElement txtJobTitle;
	@FindBy(xpath = "(//strong[contains(text(),'Skills')]/../../following-sibling::div//input)[1]")
	private WebElement txtSkills;
	@FindBy(xpath = "//*[contains(text(),'Security Clearance')]//following::select[1]")
	private WebElement lstSecurityClearance;
	@FindBy(xpath = "//*[contains(text(),'Fed Clearance Type')]//following::select[1]")
	private WebElement lstFedClearance;
	@FindBy(xpath = "//h2[contains(text(),'Saved/Recent Searches')]/../../../../div/div[2]//span[@class='lightningPrimitiveIcon']")
	private WebElement elmSaveSearchMinimize;
	@FindBy(xpath = "//*[@id='g2-textarea']//textarea")
	private WebElement elmG2CommentsArea;
	@FindBy(xpath = "//Strong[contains(text(),'Placement Type')]/../../following-sibling::*[1]//input")
	private WebElement elmPlacementType;
	@FindBy(xpath = "//*[contains(text(),'G2 Completed')]/../..//input")
	private WebElement chkG2Completed;
	@FindBy(xpath = "//Strong[contains(text(),'Workplace Type')]/../../following-sibling::*[1]//input")
	private WebElement eleworkplaceType;
	@FindBy(xpath = "//Strong[contains(text(),'Schedule')]/../../following-sibling::select")
	private WebElement lstSchedule;
	@FindBy(xpath = "//span[contains(text(),'First Name')]/../../input")
	private WebElement txtFirstName;
	@FindBy(xpath = "(//span[contains(text(),'Last Name')]/../../input)")
	private WebElement txtLastName;
	@FindBy(xpath = "//input[@id='googleLookup_Add']")
	private WebElement txtGoogleLookupAdd;
	@FindBy(xpath = "//a[@title='Find or Add Talent']")
	private WebElement tabAddcandidate;
	@FindBy(xpath = "//*[contains(@class,'TalentDuplicateSearch')]//button[@title='Search']")
	private WebElement btnAddcandidateSearch;
	@FindBy(xpath = "//*[contains(@class,'cC_TalentAddEditNew')]//*[contains(text(),'Save')]")
	private WebElement btnCandidateSave;
	@FindBy(xpath = "//input[@placeholder='example@email.com']")
	private WebElement txtEmail;
	@FindBy(xpath = "//*[contains(text(),'Phone')]/ancestor::label/following-sibling::input")
	private WebElement txtPhone;
	@FindBy(xpath = "//div[@class='slds-grid slds-grid_vertical slds-size_12-of-12']//h3[@class='slds-text-heading_large'][normalize-space()='No results found']")
	private WebElement txtNoResultFound;
	@FindBy(xpath = "//div[@title='Add a New Talent']")
	private WebElement btnAddTalent;
	@FindBy(xpath = "//*[contains(@class,'TalentAddEditOverviewSection')]//*[contains(text(),'Show More')]")
	private WebElement btnOverViewShowMore;
	@FindBy(xpath = "//*[contains(@class,'TalentAddEditOverviewSection')]//*[contains(text(),'Show Less')]")
	private WebElement btnOverViewShowLess;
	@FindBy(xpath = "//strong[contains(text(),'Overview')]/ancestor::*[@class='show']//textarea")
	private WebElement elmOverviewTextArea;
	@FindBy(xpath = "(//textarea[@placeholder='Add Comments'])[1]")
	private WebElement elmSkillCommentTextArea;
	@FindBy(xpath = "(//textarea[@placeholder='Add Comments'])[2]")
	private WebElement elmGoalsAndInterestTextArea;
	@FindBy(xpath = "//div[@aria-labelledby='Compensation and Work Preferences']")
	private WebElement elmMoreCompensation;
	@FindBy(xpath = "//*[@title='COUNTRY:']/ancestor::table//tbody//td[1]//input")
	private WebElement elmCountry;
	@FindBy(xpath = "//*[@title='COUNTRY:']/ancestor::table//tbody//td[2]//input")
	private WebElement elmState;
	@FindBy(xpath = "//*[@title='COUNTRY:']/ancestor::table//tbody//td[3]//input")
	private WebElement elmCity;
	@FindBy(xpath = "//Strong[contains(text(),'Countries Legally Authorized To Work')]/../../following-sibling::*//input")
	private WebElement elmCountriesLegallyAuthorizedToWorkText;
	@FindBy(xpath = "//span[text()='G2 Completed']")
	private WebElement checkBoxG2;

	public CreateCandidatePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void addCandidateShort(String role, CandidateDataService candidateData) {
		if(role.contentEquals("TEK_SD1_TEST")) {
			addCandidateShortTest(candidateData);
		}
		else if(role.contentEquals("TEK_SD1_LOAD")) {
			addCandidateShortRefactorLoad(candidateData);
		}
	}
	
	public void addCandidateShortTest(CandidateDataService candidateData) {

		driver.navigate().to("https://allegisgroup--martest23.sandbox.lightning.force.com/lightning/n/Find_or_Add_Candidate");
		fluentWait("//a[@title='Find or Add Talent']");
		verifyElementIsPresent("//span[contains(text(),'First Name')]/../../input");
		txtFirstName.isDisplayed();
		txtFirstName.click();
		txtFirstName.sendKeys(candidateData.getFirstName());
		txtLastName.sendKeys(candidateData.getLastName());
		txtEmail.sendKeys(candidateData.getEmail());
		txtPhone.sendKeys(candidateData.getMobilePhone());
		txtPhone.sendKeys(Keys.ENTER);
		waitTime(3);
		jsClick(btnAddcandidateSearch);

	}

	public void addCandidateShortRefactorLoad(CandidateDataService candidateData) {
		driver.navigate().to("https://allegisgroup--load.sandbox.lightning.force.com/lightning/n/Find_or_Add_Candidate");
		waitTime(2);
		fluentWait("//a[@title='Find or Add Talent']");
		System.out.println("Verify Find or Add Talent Tab");
		jsClick(tabAddcandidate);
		fluentWait("//span[contains(text(),'First Name')]/../../input");
		jsClick(txtFirstName);
		txtFirstName.sendKeys(candidateData.getFirstName());
		txtLastName.sendKeys(candidateData.getLastName());
		txtEmail.sendKeys(candidateData.getEmail());
	
		txtPhone.sendKeys(candidateData.getMobilePhone());
		txtPhone.sendKeys(Keys.ENTER);
		fluentWait(getLocatorInfo(btnAddcandidateSearch));
		jsClick(btnAddcandidateSearch);
		System.out.println("addCandidateShortRefactor jsClick");

	}
	
	public boolean isDisplayedNoResultFound() {
		return isDisplayed(txtNoResultFound);
	}

	public void addNewTalent() {
		verifyElementIsPresent("//div[@title='Add a New Talent']");
		jsClick(btnAddTalent);
	}

	public void addProfile() {

		isDisplayed(txtGoogleLookupAdd);
		txtGoogleLookupAdd.sendKeys("7312 Parkway Drive Hanover, MD 21076");
		waitTime(2);
		txtGoogleLookupAdd.sendKeys(Keys.ARROW_DOWN);
		txtGoogleLookupAdd.sendKeys(Keys.ENTER);
		waitTime(2);

	}

	public void addOverview(OverviewDataService overviewDataService) {
		System.out.println("Adding Overview details");
		isDisplayed(btnOverViewShowMore);
		jsClick(btnOverViewShowMore);
		txtJobTitle.clear();
		txtJobTitle.sendKeys("Java Developer");
		txtJobTitle.sendKeys(Keys.ARROW_DOWN);
		txtJobTitle.sendKeys(Keys.ENTER);
		waitTime(1);
		txtSkills.sendKeys(overviewDataService.getSkill1());
		waitTime(1);
		txtSkills.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		txtSkills.sendKeys(Keys.ENTER);
		txtSkills.sendKeys(overviewDataService.getSkill());
		waitTime(2);
		txtSkills.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		txtSkills.sendKeys(Keys.ENTER);
		waitTime(2);
		txtSkills.sendKeys(overviewDataService.getSkill2());
		waitTime(2);
		txtSkills.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		txtSkills.sendKeys(Keys.ENTER);
		waitTime(1);		
		txtAddCandidateLanguages.sendKeys(overviewDataService.getLanguage1());
		waitTime(1);
		txtAddCandidateLanguages.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		txtAddCandidateLanguages.sendKeys(Keys.ENTER);
		waitTime(2);
		txtAddCandidateLanguages.sendKeys(overviewDataService.getLanguage2());
		waitTime(1);
		txtAddCandidateLanguages.sendKeys(Keys.ARROW_DOWN);
		txtAddCandidateLanguages.sendKeys(Keys.ENTER);
		waitTime(2);
		select(lstSecurityClearance, overviewDataService.getSecurityClearance());
		select(lstFedClearance, overviewDataService.getFedClearance());
		elmOverviewTextArea.sendKeys(overviewDataService.getOverview());
		elmSkillCommentTextArea.sendKeys(overviewDataService.getSkillComments());
		elmGoalsAndInterestTextArea.sendKeys(overviewDataService.getGoalInterest());
		jsClick(elmMoreCompensation);
	}

	public void addComensationWorkPrefrence(CompensationWorkServiceData compensationWorkData) {
		System.out.println("Adding compensation and work prefrence");
		jsClick(elmPlacementType);
		elmPlacementType.sendKeys(compensationWorkData.getPlacementType().toLowerCase());
		waitTime(2);
		driver.findElement(By.xpath("//strong[text()='Placement Type']//following::div[@role='listbox']//ul/li[@title='"
				+ compensationWorkData.getPlacementType().toLowerCase() + "']")).click();
		waitTime(2);
		select(lstSchedule, compensationWorkData.getSchedule());
		isDisplayed(eleworkplaceType);
		jsClick(eleworkplaceType);
		eleworkplaceType.sendKeys(compensationWorkData.getWorkplaceType().toLowerCase());
		eleworkplaceType.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		eleworkplaceType.sendKeys(Keys.ENTER);
		waitTime(1);
		fluentWait(getLocatorInfo(txtDesiredShift));
		txtDesiredShift.sendKeys(compensationWorkData.getDesiredShift());
		txtDesiredShift.sendKeys(Keys.ARROW_DOWN);
		waitTime(1);
		txtDesiredShift.sendKeys(Keys.ENTER);
		waitTime(1);
		txtDate.click();
		txtDate.sendKeys("Mar 15, 2023");
		waitTime(2);
		showMoreLocation.click();
		waitTime(2);
		jsClick(showMoreG2);

	}

	public void addLocation(LocationDataService locationData) {
		System.out.println("Adding Location and creating Talent ");
		WebElement willingToRelocate = driver.findElement(By.xpath("//Strong[contains(text(),'Willing to Relocate')]"
				+ "/../../..//*[contains(text(),'" + locationData.getWillingToRelocate() + "')]/preceding-sibling::*"));
		waitTime(2);
		jsClick(willingToRelocate);
		WebElement nationalOpportunity = driver.findElement(
				By.xpath("//Strong[contains(text(),'National Opportunities')]" + "/../../..//*[contains(text(),'"
						+ locationData.getNationalOpportunityValue() + "')]/preceding-sibling::*"));
		jsClick(nationalOpportunity);
		WebElement reliableTransportation = driver.findElement(
				By.xpath("//Strong[contains(text(),'Reliable Transportation')]" + "/../../..//*[contains(text(),'"
						+ locationData.getReliableTransportation() + "')]/preceding-sibling::*"));
		jsClick(reliableTransportation);
		elmCountriesLegallyAuthorizedToWorkText.sendKeys(locationData.getCountriesLegallyAuthorizedToWork());
		waitTime(2);
		elmCountriesLegallyAuthorizedToWorkText.sendKeys(Keys.ARROW_DOWN);
		elmCountriesLegallyAuthorizedToWorkText.sendKeys(Keys.ENTER);
		waitTime(2);
		elmCountry.sendKeys(locationData.getCountryUK());
		waitTime(2);
		elmCountry.sendKeys(Keys.ARROW_DOWN);
		elmCountry.sendKeys(Keys.ENTER);
		waitTime(2);
		elmState.sendKeys(locationData.getState());
		waitTime(1);
		elmState.sendKeys(Keys.ARROW_DOWN);
		elmState.sendKeys(Keys.ENTER);
		elmCity.click();
		waitTime(2);
		elmCity.sendKeys(locationData.getCityLocationPreferanceUK());
		elmG2CommentsArea.sendKeys(locationData.getG2Comments());
		jsClick(checkBoxG2);
		waitTime(2);
		jsClick(btnCandidateSave);
		
	}
}
