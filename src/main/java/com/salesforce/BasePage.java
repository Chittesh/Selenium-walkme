package com.salesforce;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollBar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.salesforce.data.Constants;

public class BasePage {
	@FindBy(xpath = "//*[contains(@class,'TalentActivityTimeline')]//*[contains(text(),'Activities')]")
	private WebElement talentPageActivites;

	@FindBy(xpath = "//*[contains(text(),'Talent Details')]")
	private WebElement talentDetails;

	@FindBy(xpath = "//table//tbody//c-lwc-data-table-cell[4]//*[contains(@title,'')]")
	private WebElement submittalStatus;

	@FindBy(xpath = "//table//tbody//c-lwc-data-table-cell[4]//*[contains(@title,'Submitted')]")
	private WebElement submittedStatus;

	@FindBy(xpath = "//table//tbody//c-lwc-data-table-cell[4]//*[contains(@title,'Offer Accepted')]")
	private WebElement offerAcceptStatus;

	@FindBy(xpath = "//*[contains(@class,'edit-icon slds-icon')]")
	private WebElement editIcon;

	@FindBy(xpath = "//table//tbody//c-lwc-data-table-cell[4]//*[contains(@class,'inline-edit')]")
	private WebElement inLineEditLinked;

	@FindBy(xpath = "//*[contains(@class,'inline-edit')]//*[contains(text(),'Submitted')]")
	private WebElement submitted;

	@FindBy(xpath = "//*[contains(@title,'Edit Employee Status Form Name')]")
	private WebElement editIconESF;
	@FindBy(xpath = "//*[contains(text(),'Delivery')]/..//input")
	private WebElement deliveryOwner;
	@FindBy(xpath = "//*[text()='Worksite State']//following-sibling::div//button")
	private WebElement workState;
	@FindBy(xpath = "//*[text()='Worksite State']//following-sibling::div//*[@title='Maryland']")
	private WebElement workStateMaryLand;
	@FindBy(xpath = "//*[contains(@name,'SaveEdit')]")
	private WebElement save;

	@FindBy(xpath = "//span[text()='Job Information']")
	public WebElement lblJobInformation;
	@FindBy(xpath = "//button[contains(@title, 'Edit: Recruiting Office')]")
	public WebElement editBtnRecruitingoffice;
	@FindBy(xpath = "//div//span[text()='Recruiting Office']/..//lightning-input")
	public WebElement eleRecruitingOffice;

	@FindBy(xpath = "//div//span[text()='Job Code']//following-sibling::div//input")
	public WebElement eleJobCode;

	@FindBy(xpath = "//div//span[text()='Global Services Division']//following-sibling::div//input")
	public WebElement eleGlobalServiceDivision;
	@FindBy(xpath = "//button[@title='Ok']")
	public WebElement divisionsOkButton;
	@FindBy(xpath = "//button[text()='OK']")
	public WebElement buttonOK;
	@FindBy(xpath = "//button[text()='Ok']")
	public WebElement buttonOk;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement buttonSave;

	protected WebDriver driver;
	protected WebElement element;
	@FindBy(xpath = "//div//span[text()='Competency Years of Experience']/..//lightning-input")
	public WebElement eleCompetencyYearOfExp;
	@FindBy(xpath = "//div//span[text()='Primary Competency Skillset']/..//lightning-input")
	public WebElement elePrimaryCompetencySkillSet;
	@FindBy(xpath = "//*[contains(text(),'Job Code')]/ancestor::section[@role='dialog']//*[contains(@part,'input-container')]//input")
	public WebElement elmJobCodeInput;

	@FindBy(xpath = "//span[text()='Talent Onboarding Information']")
	public WebElement lblJTalentOnboardingInformation;
	@FindBy(xpath = "//*[text()='Verify Hire Status']/../..//following-sibling::div//button")
	public WebElement eleHireStatus;

	@FindBy(xpath = "//span[text()='Commission Information']")
	public WebElement lblCommissionInformation;
	@FindBy(xpath = "//label[text()='Primary Sales Spread %']//following-sibling::div//input")
	public WebElement elePrimarySalesSpreadPercent;
	@FindBy(xpath = "//*[text()='Primary Sales Spread Type']//following-sibling::div//input")
	public WebElement elePrimarySalesSpreadType;
	@FindBy(xpath = "//label[text()='Primary Recruiter Spread %']//following-sibling::div//input")
	public WebElement elePrimaryRecruiterSpread;
	@FindBy(xpath = "//div[text()='Primary Recruiter Spread Type']//following-sibling::div//input")
	public WebElement elePrimaryRecSpreadType;
	@FindBy(xpath = "//input[@name='searchText']")
	public WebElement burdenTypeSearch;
	@FindBy(xpath = "//button[@title='OK']")
	public WebElement btnOkOnLookUp;
	@FindBy(xpath = "//button[text()='Add Project Information']")
	private WebElement btnAddProjectInfo;
	@FindBy(xpath = "//button[text()='Add Activities']")
	private WebElement btnAddActivites;
	@FindBy(xpath = "//button[text()='Save & Close']")
	private WebElement btnSaveAndClose;
	@FindBy(xpath = "//input[@name='searchText']")
	private WebElement txtProjectSearch;
	@FindBy(xpath = "//span[text()='Pay & Bill Rates']")
	public WebElement lblPayAndBillRates;
	@FindBy(xpath = "//label[text()='Pay Rate - Regular']//following-sibling::div//input")
	public WebElement elePayRateRegular;
	@FindBy(xpath = "//label[text()='Bill Rate - Regular']//following-sibling::div//input")
	public WebElement eleBillRateRegular;
	
	@FindBy(xpath = "//button[text()='Add Burden']")
	private WebElement btnAddBurden;

	//private Logger logger = LogManager.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	
	public boolean fluentWait(String xpathOfElement) {
		//System.out.println("Verifying xpath " + xpathOfElement);

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		WebElement elm;
		try {
			elm = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
		} catch (Exception E) {
			throw new WebDriverException("Unable to find " + xpathOfElement);
		}
		return elm.isDisplayed();

	}
	
	public boolean fluentWaitPresence(String xpathOfElement) {
		//System.out.println("Verifying xpath " + xpathOfElement);

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		WebElement elm;
		try {
			elm = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
		} catch (Exception E) {
			throw new WebDriverException("Unable to find " + xpathOfElement);
		}
		return elm.isDisplayed();

	}
	
	
	public boolean fluentWait(String xpath, int durarion) {
		boolean isTrue = false;
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		try {
			fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			isTrue = driver.findElements(By.xpath(xpath)).size() > 0;
		} catch (Exception E) {
			isTrue = false;
		}
		return isTrue;
	}

	public boolean verifyElementIsPresent(String xpathOfElement) {
		System.out.println("Verifying xpath " + xpathOfElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

		WebElement elm;
		try {
			elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
		} catch (Exception E) {
			throw new WebDriverException("Unable to find " + xpathOfElement);
		}

		return elm.isDisplayed();

	}

	public boolean verifyElementIsPresent(String xpath, int durarion) {
		boolean isTrue = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durarion));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			isTrue = driver.findElements(By.xpath(xpath)).size() > 0;
		} catch (Exception E) {
			isTrue = false;
		}
		return isTrue;
	}	

	

	public boolean isDisplayed(WebElement element) {
		boolean flag = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public String getLocatorInfo(WebElement element) {
		int startIndex = element.toString().indexOf("xpath:");
		return element.toString().substring(startIndex, element.toString().length() - 1).replaceAll("xpath:", "")
				.trim();

	}

	public String getProfileName(String strRole) {
		ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
		return userCredentialRepo.getString(strRole.toUpperCase() + "_NAME");
	}

	public String getOpcoName(String strRole) {
		ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
		return userCredentialRepo.getString(strRole.toUpperCase() + "_OWNERSHIP");
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	public void scollTillTop() {
		System.out.println("Scolling to Top");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");

	}
	
	public void scollTillBottom() {
		System.out.println("Scolling to Top");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

	}
	
	

	public void jsScroll(WebElement element) {
		System.out.println("Scolling till element is visible");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void select(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void waitTime(int timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public boolean navigateToTalentRecord(String strUrlPart2WithAccID, String sObject) {
		waitTime(5);
		String strUrl = driver.getCurrentUrl();
		String strUrlPart1 = strUrl.substring(0, strUrl.lastIndexOf("lightning.force.com"));
		String strTalenRecordAccessUrl = strUrlPart1 + "lightning.force.com" + "/lightning/r/" + sObject + "/"
				+ strUrlPart2WithAccID + "/view";
		driver.navigate().to(strTalenRecordAccessUrl);
		waitTime(3);
		driver.navigate().refresh();
		waitTime(10);
		String url = driver.getCurrentUrl();
		System.out.println("Navigating to Record " +sObject+": "+ url);
		return url.contains(strUrlPart2WithAccID);
	}



	
	public boolean validateSObjectPresenceOnURL(String sObject) {
		waitTime(10);
		String strUrl = driver.getCurrentUrl();
		System.out.println("navigated URL is : " + strUrl);
		return strUrl.contains(sObject);
	}

	public boolean verifyTalentActivity() {
		System.out.println("Checking Activity on Talent Page");
		return fluentWait(getLocatorInfo(talentPageActivites));
	}

	public void mouseHover(WebElement element) {
		System.out.println("Mouse Hover on Element");
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void verifyOpportunityPageLoaded() {
		String xpath = "//form//*[contains(text(),'Edit')]";
		 fluentWait((xpath));
	}

	public void clickOnTalentDetails() {
		talentDetails.click();
	}

	public String getSubmittalStatus() {
		System.out.println("verifying Submittals record is present ");
		fluentWait(getLocatorInfo(submittalStatus));
		String status = submittalStatus.getText();
		return status;
	}

	public void verifyScoreCardForLinked() {
		System.out.println("verifying Score Card for linked 1");
		fluentWait("//*[contains(@class,'VIEW slds-card')]//*[contains(text(),'Linked')]/../..//*[contains(text(),'1')]");

	}

	public void clickOnEdit() {
		jsScroll(editIcon);
		editIcon.click();
	}

	public void chageStatusToSubmitted() {
		System.out.println("Change Submittal Status to Submitted");
		fluentWait("//table//tbody//c-lwc-data-table-cell[4]//*[contains(@class,'inline-edit')]");
		driver.findElement(By.xpath("//table//tbody//c-lwc-data-table-cell[4]//*[contains(@class,'inline-edit')]"))
				.click();
		fluentWait("//table//tbody//c-lwc-data-table-cell[4]//*[contains(text(),'Submitted')]");
		driver.findElement(By.xpath("//table//tbody//c-lwc-data-table-cell[4]//*[contains(text(),'Submitted')]"))
				.click();
		fluentWait(getLocatorInfo(submittedStatus));
	}

	public void chageStatusToOfferAccept() {
		System.out.println("Change Status to Offer Accepted");
		fluentWait("//table//tbody//c-lwc-data-table-cell[4]//*[contains(@class,'inline-edit')]");
		driver.findElement(By.xpath("//table//tbody//c-lwc-data-table-cell[4]//*[contains(@class,'inline-edit')]"))
				.click();
		fluentWait("//table//tbody//c-lwc-data-table-cell[4]//*[contains(text(),'Offer Accepted')]");
		driver.findElement(By.xpath("//table//tbody//c-lwc-data-table-cell[4]//*[contains(text(),'Offer Accepted')]"))
				.click();
		fluentWait(getLocatorInfo(offerAcceptStatus));
	}

	public void verifyScoreCardForSubmitted() {
		System.out.println("verifying Score Card for Submitted 1");
		fluentWait("//*[contains(@class,'VIEW slds-card')]//*[contains(text(),'Submitted')]/../..//*[contains(text(),'1')]");
	}

	public boolean verifyScoreCardForOfferAccept() {
		
		boolean status = fluentWait(
				"//*[contains(@class,'VIEW slds-card')]//*[contains(text(),'Offer Accepted')]/../..//*[contains(text(),'1')]");
		System.out.println("verifying Score Card for Submitted 1 is " + status);

		return status;
	}

	public boolean verifyESFLink() {
		System.out.println("Vefify ESF Link");
		waitForViewESF();
		return fluentWait("//a[contains(text(),'View ESF')]");

	}
	
	public void waitForViewESF() {
		boolean isTrue = false;
		long startTime = System.currentTimeMillis();
		long timeDiffs;
		do {

			driver.navigate().refresh();
			verifyOpportunityPageLoaded();
			clickOnTalentDetails();
			fluentWait(getLocatorInfo(submittalStatus));
			isTrue = driver.findElements(By.xpath("//a[contains(text(),'View ESF')]")).size() > 0;

			timeDiffs = System.currentTimeMillis() - startTime;
		} while (!isTrue && (timeDiffs < 25000));
		
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffs);
		System.out.println("View ESF loaded within "+ seconds+" Seconds");
	}

	public void clickOnESF() {
		System.out.println("Click on View ESF");
		jsClick(driver.findElement(By.xpath("//a[contains(text(),'View ESF')]")));
		switchLastWindow();
	}

	public void switchLastWindow() {
		waitTime(1);
		System.out.println("Switching to last opened window");
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	public boolean verifyNewSatge() {
		System.out.println("Verify New Stage is Loaded");
		return fluentWait("//*[contains(@data-tab-name,'New')][@aria-checked='true']");
	}
	
	@FindBy(xpath = "//*[contains(@data-tab-name,'Under Review')][@aria-selected='true']")
	public WebElement elmUnderReview;

	public boolean verifyUnderReviewSatge() {
		System.out.println("Verify Under Review Stage is Loaded");
		return fluentWait(getLocatorInfo(elmUnderReview));
	}
	
	public boolean verifyUnderProjectReviewSatge() {
		System.out.println("Verify Under Porject Review Stage is Loaded");
		waitTime(2);
		return fluentWait("//*[contains(@data-tab-name,'Under Project Review')][@aria-selected='true']");
	}
	
	public boolean verifyPendingApporvalStage() {
		System.out.println("Verify Pending Review Stage is Loaded");
		waitTime(1);
		scollTillTop();
		waitTime(1);
		return fluentWait("//*[contains(@data-tab-name,'Pending Approval')][@aria-selected='true']");
	}
	
	
	
	@FindBy(xpath = "//*[contains(text(),'Mark Stage as Complete')]")
	public WebElement markSatge;
	
	public void clickOnMarkStage() {
		System.out.println("Click on Mark Stage");
		String xpath = "//*[contains(text(),'Mark Stage as Complete')]";
		fluentWait("//*[contains(text(),'Mark Stage as Complete')]");
		scollTillTop();
		jsScroll(markSatge);
		jsClick(markSatge);

	}

	public boolean verifyErrorMessage() {
		System.out.println("Verify Error Message");
		String xpath = "//*[contains(text(),'Recruiting Office, Global Services Division, Job Code, Primary Competency Skillset, Competency Years of Experience, E-mail Type, Phone Type, Start Date, End Date, Verify Hire Status, Employment Type, Position Type, Pay Rate - Regular, Primary Sales Name, Primary Sales Spread Type, Primary Sales Spread %, Primary Recruiter Name, Primary Recruiter Spread %, Primary Recruiter Spread Type, Base Burden Entered,')]";
		return verifyElementIsPresent(xpath);
	}
	
	public boolean verifyErrorMessageunderReview() {
		String xpath = "//*[contains(text(),'The ESF stage cannot be changed')]";
		return verifyElementIsPresent(xpath);
	}
	
	public boolean verifyErrorMessagePeopleSoftId() {
		String xpath = "//*[contains(text(),'PeopleSoft Customer ID, PeopleSoft Project ID, Practice, Bill Rate - Regular, Project Activities Entered,')]";
		return verifyElementIsPresent(xpath);
	}

	public boolean verifyErrorMessagePOAUSer() {
		String xpath = "//*[contains(text(),'Resource Category, Project Burden Entered,')]";
		return verifyElementIsPresent(xpath);
	}
	
	
	public void populateValuesForGlobalServiceAndCustomerInfo() {
		System.out.println("Selecting Value for Global Service and Customer Info");
		fluentWait(getLocatorInfo(editIconESF));
		jsClick(editIconESF);
		fluentWaitPresence(getLocatorInfo(deliveryOwner));

		selectValue(deliveryOwner, "Demo User Delivery TGS");
		selectState();
		save();

	}

	public void selectValue(WebElement webElement, String value) {
		fluentWait(getLocatorInfo(webElement));
		jsClick(webElement);
		waitTime(1);
		webElement.clear();
		webElement.sendKeys(value);
		
		waitTime(2);
		String xpathOfDropDown = "//*[contains(text(),'Delivery')]/..//*[contains(@class,'slds-listbox slds-listbox')]//*[(@title='"
				+ value + "')]";
		fluentWait(xpathOfDropDown);
		jsClick(driver.findElement(By.xpath(xpathOfDropDown)));
		waitTime(1);
	}

	public void selectState() {
		jsClick(workState);
		waitTime(1);
		jsClick(workStateMaryLand);
	}

	public void save() {
		jsClick(save);
		waitTime(3);
	}

	public void selectMandatoryJobInformation() {
		System.out.println("Selecting Job Information");
		fluentWait(getLocatorInfo(lblJobInformation));
		jsScroll(lblJobInformation);

		System.out.println("Clicking on Edit Recruting office");
		jsClick(editBtnRecruitingoffice);

		System.out.println("Select the recruiting office TEK-Nashville, TN-30623");
		jsClick(eleRecruitingOffice);

		waitTime(1);
		eleRecruitingOffice.sendKeys("TEK-Nashville, TN-30623");

		fluentWait("//*[contains(text(),'TEK-Nashville, TN-30623')]/ancestor::*[@role='option']");
		jsClick(driver
				.findElement(By.xpath("//*[contains(text(),'TEK-Nashville, TN-30623')]/ancestor::*[@role='option']")));
		waitTime(2);

		System.out.println("Select the Global service Division 61: GS - Applications");
		jsScroll(driver.findElement(By.xpath("//*[text()='Worksite State']")));
		eleGlobalServiceDivision.click();

		selectLookUpValue("61: GS - Applications");

		eleJobCode.click();
		System.out.println("Select the Job Code Agile-Coach/Trainer - 9166");
		setJobCode("Agile-Coach/Trainer - 9166");

		System.out.println("Set the Competency years of experience 5");
		eleCompetencyYearOfExp.sendKeys("5");

		System.out.println("Set the primary competency Java");
		elePrimaryCompetencySkillSet.sendKeys("Java");
		fluentWait(getLocatorInfo(buttonSave));

		jsClick(buttonSave);
		waitTime(4);

	}

	@FindBy(xpath = "//label[text()='End Date']//following-sibling::*//input")
	public WebElement eleEndDate;
	@FindBy(xpath = "//label[text()='Start Date']//following-sibling::*//input")
	public WebElement eleStartDate;

	public void selectMandatoryTalentOnBoarding() {
		System.out.println("Selecting Talent On Boarding");
		waitTime(3);
		fluentWait("//span[text()='Talent Onboarding Information']");
		fluentWait("//*[text()='Verify Hire Status']/../..//following-sibling::div//button");
		jsScroll(lblJTalentOnboardingInformation);

		jsScroll(lblJTalentOnboardingInformation);
		

		System.out.println("Clicking on Edit Hire Status");
		jsClick(eleHireStatus);

		System.out.println("Select the Hire status New Hire ");

		select("Verify Hire Status", "New Hire");

		String endDate = getDaysOut("+2", "MM/dd/yyyy");
		System.out.println("Set the end date " + endDate);
		eleEndDate.sendKeys(endDate);
		System.out.println("Select the Position type Temporary Status");

		String startDay = getSystemDate("MM/dd/yyyy");
		System.out.println("Set the start date " + startDay);
		eleStartDate.sendKeys(startDay);
		waitTime(1);

		System.out.println("Select the Employement type W2");
		select("Employment Type", "W2");

		System.out.println("Select the Email type Work ");
		select("E-mail Type", "Work");

		System.out.println("Select the Phone type Work");
		select("Phone Type", "Work");

		select("Position Type", "Temporary Status");

		jsClick(buttonSave);
		waitTime(2);

	}

	public static String getDaysOut(String daysOut, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt(daysOut));
		String convertedDate = dateFormat.format(cal.getTime());
		return convertedDate;
	}

	public static String getSystemDate(String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		String convertedDate = dateFormat.format(cal.getTime());
		return convertedDate;
	}

	
	

	public void setMandatoryPayAndBillRate() {
		System.out.println("Selecting Pay And Bill Rate");
		jsScroll(lblPayAndBillRates);

		clickEditIcon("Pay Rate - Regular");
		waitTime(2);
		System.out.println("Set the pay rate regular 20");
		fluentWait(getLocatorInfo(elePayRateRegular));
		elePayRateRegular.sendKeys("20");
		jsClick(buttonSave);
		waitTime(2);

	}

	public void clickEditIcon(String label) {
		String xpathOfLightiningComponentEdit = "//span[text()='" + label
				+ "']/../../following-sibling::div//button[contains(@title,'Edit')]";
		System.out.println("Click on Edit Icon");
		fluentWait(xpathOfLightiningComponentEdit);
		jsClick(driver.findElement(By.xpath(xpathOfLightiningComponentEdit)));

	}

	public void select(String label, String valueToSelect) {
		String xpathOfButtonLabel = "//*[text()='" + label + "']//following-sibling::div//button";
		String xpathOfValueToBeSelected = "//*[text()='" + label + "']//following-sibling::div//*[@title='"
				+ valueToSelect + "']";
		fluentWait(xpathOfButtonLabel);
		jsClick(driver.findElement(By.xpath(xpathOfButtonLabel)));
		System.out.println("Select "+label+" from the input field "+label+" value");
		fluentWait(xpathOfValueToBeSelected);
		jsClick(driver.findElement(By.xpath(xpathOfValueToBeSelected)));
		waitTime(2);
	}

	public void selectLookUpValue(String valueToSelect) {
		String xpathOfRadioType = "//th//lightning-base-formatted-text[contains(text(),'" + valueToSelect
				+ "')]//ancestor::tr//input[@type='radio']";
		fluentWait(xpathOfRadioType);
		jsClick(driver.findElement(By.xpath(xpathOfRadioType)));
		fluentWait(getLocatorInfo(divisionsOkButton));
		jsClick(divisionsOkButton);
		waitTime(1);

	}

	public void setJobCode(String valueToSelect) {

		fluentWait(getLocatorInfo(elmJobCodeInput));
		elmJobCodeInput.sendKeys(valueToSelect);
		String xpathOfValueTobeSlectedForLabel = "//th//*[contains(text(),'" + valueToSelect
				+ "')]/ancestor::tbody//input";
		fluentWait(xpathOfValueTobeSlectedForLabel);
		jsClick(driver.findElement(By.xpath(xpathOfValueTobeSlectedForLabel)));
		fluentWait(getLocatorInfo(buttonOK));

		jsClick(buttonOK);
		waitTime(2);
	}

	public void selectMandatoryComissionInformation(String primarySalesName, String primaryRecruiter) {
		System.out.println("Selecting Commision Informations");
		jsScroll(lblCommissionInformation);

		clickEditIcon("Primary Sales Name");

		System.out.println("Select the Primary Sales Name " + primarySalesName);
		selectSearchValue("Primary Sales Name", primarySalesName);

		System.out.println("Select the Primary Sales Spread percentage 100.00%");
		elePrimarySalesSpreadPercent.sendKeys("100.00%");
		waitTime(2);

		System.out.println("Select the Primary  Sales Spread type NSRL");
		jsScroll(elePrimarySalesSpreadType);
		jsClick(elePrimarySalesSpreadType);

		selectValueCommosion("Primary Sales Spread Type", "NSRL");
		// waitTime(2);

		System.out.println("Select the Primary recruiter Name " + primaryRecruiter);
		selectSearchValue("Primary Recruiter Name", primaryRecruiter);
		waitTime(2);

		System.out.println("Select the Primary  Recruiter Spread type NSRL");
		jsScroll(elePrimaryRecSpreadType);
		jsClick(elePrimaryRecSpreadType);

		selectValueCommosion("Primary Recruiter Spread Type", "NSRL");
		waitTime(2);

		System.out.println("Select the Primary Recruiter Spread 100.00%");
		jsScroll(elePrimaryRecruiterSpread);
		jsClick(elePrimaryRecruiterSpread);

		elePrimaryRecruiterSpread.sendKeys("100.00%");
		waitTime(2);

		jsClick(buttonSave);
		waitTime(2);

	}

	public void selectValueCommosion(String label, String valueToSelect) {
		String xpathOfInputLabel = "//*[text()='" + label + "']//following-sibling::div//input";
		String xpathOfValueTobeSlectedForLabel = "//tr[@data-row-key-value='" + valueToSelect
				+ "']//td//lightning-primitive-cell-checkbox//label";

		System.out.println("Clickon the look up field "+label+" and select "+valueToSelect);

		fluentWait(xpathOfInputLabel);
		jsClick(driver.findElement(By.xpath(xpathOfInputLabel)));

		waitTime(1);
		driver.findElement(By.xpath(xpathOfInputLabel)).sendKeys(valueToSelect);

		fluentWait(xpathOfValueTobeSlectedForLabel);
		jsClick(driver.findElement(By.xpath(xpathOfValueTobeSlectedForLabel)));

		System.out.println("Click on Ok Button");

		fluentWait(getLocatorInfo(buttonOk));
		jsClick(buttonOk);

	}

	public void selectSearchValue(String label, String valueToSelect) {
		String xpathOfInputLabel = "//*[text()='" + label + "']//following-sibling::div//input";
		fluentWait(xpathOfInputLabel);
		jsClick(driver.findElement(By.xpath(xpathOfInputLabel)));

		waitTime(1);
		driver.findElement(By.xpath(xpathOfInputLabel)).sendKeys(valueToSelect);

		String xpathOfDropDown = "//*[contains(text(),'" + label
				+ "')]/..//*[contains(@class,'slds-listbox slds-listbox')]//*[(@title='" + valueToSelect + "')]";
		fluentWait(xpathOfDropDown);
		jsClick(driver.findElement(By.xpath(xpathOfDropDown)));
		waitTime(1);

	}

	public void createNewBurdenForESF(String burdenTypeLabel, String burdenTypeSearch, String burdenValue) {
		System.out.println("Create New Burden");
		clickNewButton();
		fillBurdenDetails(burdenTypeLabel, burdenTypeSearch, burdenValue);
		fluentWait(getLocatorInfo(buttonSave));

		jsClick(buttonSave);
		//waitTime(2);
		fluentWait("//h1//*[contains(text(),'Burden')]");
	}

	public void clickOnESFIDFromBurdern() {
		System.out.println("Clicking on ESF ID from Burdern");
		fluentWait("//records-hoverable-link//a[contains(@href,'Employee_Status_Form__c')]");
		jsClick(driver.findElement(By.xpath("//records-hoverable-link//a[contains(@href,'Employee_Status_Form__c')]")));
		
	}

	@FindBy(xpath = "//a[@name='New']//span[text()='New']")
	public WebElement eleNew;

	@FindBy(xpath = "//*[contains(@data-target-reveals,'Burden__c.New')]//button")
	public WebElement eleNewDropDown;

	public void clickNewButton() {
		scollTillTop();
		fluentWait(getLocatorInfo(eleNewDropDown));
		eleNewDropDown.click();

		fluentWait(getLocatorInfo(eleNew));
		System.out.println("Click on New Button from the Burden page");
		jsClick(eleNew);

	}

	public void fillBurdenDetails(String burdenTypeLabel, String burdenTypeSearch, String burdenValue) {
		clickSearchButton(burdenTypeLabel);
		burdenTypeSearch(burdenTypeSearch);
		System.out.println("Select the Burden type from the look ");
		selectLookUpBasedOnName(burdenValue);
	}

	public void clickSearchButton(String labelName) {
		System.out.println("Click on label to search the field:" + labelName);
		String xpath = "//span[text()='" + labelName + "']//following-sibling::div//input";
		fluentWait(xpath);
		driver.findElement(By.xpath(xpath)).click();
	}

	public void burdenTypeSearch(String typeOfBurden) {
		fluentWait(getLocatorInfo(burdenTypeSearch));
		jsClick(burdenTypeSearch);
		burdenTypeSearch.sendKeys(typeOfBurden);
		burdenTypeSearch.sendKeys(Keys.ENTER);
		waitTime(3);

	}

	public void selectLookUpBasedOnName(String jobName) {
		System.out.println("Select the look up value");
		jobName = "AHB";
		String xpath = "//div[text()='" + jobName + "']";
		fluentWait(xpath);
		jsClick(driver.findElement(By.xpath(xpath)));

		xpath = "//*[text()='" + jobName + "']//parent::td//preceding-sibling::td//input";
		fluentWait(xpath);
		jsClick(driver.findElement(By.xpath(xpath)));
		jsClick(btnOkOnLookUp);
	}
	
	
	
	public void logoutSalesforce() {
		waitTime(2);
		driver.get("https://test.salesforce.com/");
		waitTime(2);
	}
	
	public void deleteCookie() {
		System.out.println("Delete cookies");
		driver.manage().deleteAllCookies();
	}
	
	public String getProfileForTGSD1(String role) {
		String profile;
		
		if (role.contains("LOAD")) {
			
			profile = "TEK_TGSD1_LOAD";
		} else {
			
			profile = "TEK_TGSD1_TEST";
		}
		System.out.println("Logout from salesforce and login using TGSD user");
		return profile;
	}
	
	public void addPeopleSoftDetails() {
		
		fluentWait(getLocatorInfo(btnAddProjectInfo));
		jsClick(btnAddProjectInfo);
		
	
	
		searchProjectDetails("procter");
		fluentWait(getLocatorInfo(btnAddActivites));
		btnAddActivites.click();
		
		addActivity();
		fluentWait(getLocatorInfo(btnSaveAndClose));
		btnSaveAndClose.click();
		
		fluentWait(getLocatorInfo(eleNewDropDown));
		
		
	}
	
	public void searchProjectDetails(String projectId) {
		waitTime(2);
		fluentWait(getLocatorInfo(txtProjectSearch));
		txtProjectSearch.sendKeys(projectId);
		waitTime(1);
		txtProjectSearch.sendKeys(Keys.ENTER);
		String xpath = "//td//input";
		fluentWait(xpath);
		jsClick(driver.findElement(By.xpath(xpath)));
	}
	
	
	public void addActivity() {
		String xpath = "//td//input[@type='checkbox']";
		fluentWait(xpath);
		jsClick(driver.findElement(By.xpath(xpath)));
	}
	
	public void setBillRateRegular() {
		jsScroll(lblPayAndBillRates);
		waitTime(1);
		clickEditIcon("Pay Rate - Regular");
		System.out.println("Set the pay rate regular 20");
	
		fluentWaitPresence(getLocatorInfo(eleBillRateRegular));
		eleBillRateRegular.sendKeys("75");
		jsClick(buttonSave);
		waitTime(2);

	}
	
	public String getProfileForPOA(String role) {
		String profile;
		if (role.contains("LOAD")) {
			profile = "TEK_POA_LOAD";
		} else {
			profile = "TEK_POA_TEST";
		}
		System.out.println("Logout from salesforce and login using POA user");
		return profile;
	}
	
	@FindBy(xpath = "//span[text()='Resource Category']/..//following-sibling::div//lightning-icon")
	public WebElement btnResourcecategoryEdit;
	@FindBy(xpath = "//*[text()='ACCT_MGR']//ancestor::tr//input")
	public WebElement acctMgrInput;
	

	public void selectMandFieldForUnderProjectReview() {
		
		fluentWait(getLocatorInfo(btnResourcecategoryEdit));
		scollTillBottom();
		jsClick(btnResourcecategoryEdit);
		waitTime(1);
		fluentWait(getLocatorInfo(acctMgrInput));
		jsClick(acctMgrInput);
		fluentWait(getLocatorInfo(buttonSave));

		jsClick(buttonSave);
		waitTime(2);

	}
	
public void addBurden() {
		
		fluentWait(getLocatorInfo(btnAddBurden));
		jsClick(btnAddBurden);
		
	
		clickSearchButton("Burden Type");
	
		searchProjectDetails("risk");
		fluentWait(getLocatorInfo(buttonOK));

		jsClick(buttonOK);
		
		fluentWait(getLocatorInfo(buttonSave));

		jsClick(buttonSave);
		
		
	}




	public String getActStatus() {
	
		System.out.println("Checking ACT satus to be updated");
		waitForACTStatus();
		fluentWait(("//span[text()='ACT Status']/../following-sibling::div//lightning-formatted-text"));
		String actualActStatusUI = driver.findElement(By.xpath("//span[text()='ACT Status']/../following-sibling::div//lightning-formatted-text")).getText();
		return actualActStatusUI;
	}
	
	public void waitForACTStatus() {
		boolean isTrue = false;
		long startTime = System.currentTimeMillis();
		long timeDiffs;
		do {

			driver.navigate().refresh();
			System.out.println("Refresh Page");
			verifyPendingApporvalStage();

			isTrue = driver
					.findElements(
							By.xpath("//span[text()='ACT Status']/../following-sibling::div//lightning-formatted-text"))
					.size() > 0;
			String actualActStatusUI = driver
					.findElement(
							By.xpath("//span[text()='ACT Status']/../following-sibling::div//lightning-formatted-text"))
					.getText();
			isTrue = actualActStatusUI.length() > 2;
			timeDiffs = System.currentTimeMillis() - startTime;
		} while (!isTrue && (timeDiffs < 75000));

		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffs);
		System.out.println("Verfiy ACT loaded within " + seconds + " Seconds");
	}
	

}
