package com.salesforce.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.salesforce.BasePage;

public class HomePage extends BasePage {

	//private Logger logger = LogManager.getLogger(HomePage.class);
	/** Page Elements **/

	@FindBy(xpath = "(//a[contains(@title,'Home')])[1]")
	private WebElement lnkHome;
	
	
	@FindBy(xpath = "//button[contains(text(),'Open')]")
	private WebElement openHomePage;
	
	@FindBy(xpath = "//*[contains(@data-tab-value,'topMatches')]")
	private WebElement matchTalentTopMactches;
	@FindBy(xpath = "(//*[contains(@class,'RecruiterOpenReqReport')]//table)[last()]")
	private WebElement webTableOpenReqOpportunites;
	@FindBy(xpath = "(//button[@title='Expand'])[2]")
	private WebElement elmExpandOpportunites;
	@FindBy(xpath = "(//a[@class='view-report'])[1]")
	private WebElement elmViewReportCurrents;
	@FindBy(xpath = "//*[contains(@title,'Total Records')]//following-sibling::*")
	private WebElement elmTotalRecords;
	@FindBy(xpath = "(//*[contains(@class,'RecruiterCurrentTalentReport')]//table)[last()]")
	private WebElement webTableMyCurrents;
	@FindBy(xpath = "(//button[@title='Expand'])[1]")
	private WebElement elmExpandCurrents;
	@FindBy(xpath = "(//*[contains(@class,'RecruiterCurrentTalentReport')]//table)[last()]//tr[2]//td[3]//a")
	private WebElement webTableMyCurrentsFirst;
	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Home')]/parent::a")
	private WebElement btnHome;
	@FindBy(xpath = "//*[contains(@class,'cC_RecruiterOpenReqReport')]//table//td[4]//a")
	private WebElement reqJobTitle;
	@FindBy(xpath = "(//*[contains(@class,'report-container')])[2]//table//tr[3]//a")
	private WebElement viewTalentDetails;
	@FindBy(xpath = "//*[contains(@class,'RecruiterCurrentTalentReport')]//table//tbody//tr[1]/..//preceding-sibling::td//input")
	private WebElement myCurrentTalent;
	@FindBy(xpath = "(//button[@title='Actions'])[last()]")
	private WebElement btnActions;
	
	@FindBy(xpath = "//div[contains(text(), 'Comments')]//descendant::textarea[1]")
	private WebElement elmComments;
	@FindBy(xpath = "(//button[contains(text(),'Save')])[last()]")
	private WebElement btnSave;
	@FindBy(xpath = "//*[contains(text(), 'The activity has been saved.')]")
	private WebElement elmConfirm;
	@FindBy(xpath = "//*[contains(@data-component-id,'RecruiterCurrentTalentReport')]//table//td[3]//a")
	private WebElement myCurrentFirstTalent;
	@FindBy(xpath = "//h1[text()='My Current Talent']")
	private WebElement myCurrentTalentHead;
	
	
	
	/** Constructor **/
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean verifyHomePageTab() {
		System.out.println("Verify Home Page Tab is present");
		return verifyElementIsPresent("(//a[contains(@title,'Home')])[1]");
	}
	
	public void verifyOpenHomePageTab() {
		System.out.println("Verify Open Home Page Tab is present");
		verifyElementIsPresent("//*[contains(@title,'Refresh this feed')]");
		waitTime(3);
	}
	
	
	public boolean verifyOpenReqJobTitle() {
		System.out.println("Verify Open Req Job title is present");
		return fluentWait(getLocatorInfo(reqJobTitle));
	}
	
	public void clickOnJobTitle() {
		System.out.println("Clicking on a Job title from Open Req section");
		 jsClick(reqJobTitle);
	}
	
	public void clickOnCurrentTalent() {
		System.out.println("Clicking on Talent from My Current section");
		 jsClick(myCurrentFirstTalent);
	}
	
	
	public boolean verifyViewTalentDeatils() {
		System.out.println("Checking view Talent Deatils is present");
		return verifyElementIsPresent(getLocatorInfo(viewTalentDetails));
	}
	
	public boolean verifyMyCurrentTab() {
		System.out.println("Verifying My current talent");
		return verifyElementIsPresent(getLocatorInfo(myCurrentTalentHead));
	}
	
	
	
	public void clickOnViewTalentDetails() {
		System.out.println("Clicking on View Talent Details");
		fluentWait("(//*[contains(@class,'report-container')])[2]//table//tr[3]//a");
		jsScroll(viewTalentDetails);
		jsClick(viewTalentDetails);
	}

	public boolean verifyTopMatch() {
		System.out.println("Verify Match Talent Top Results is loaded");
		return fluentWait("(//button[@title='Actions'])[last()]",120);
	}

	public int clickOnViewReportForCurrents() {
		fluentWait(getLocatorInfo(elmExpandOpportunites));
		
		System.out.println("Clcikcing on View Reports");
		jsClick(elmViewReportCurrents);

		switchLastWindow();
		waitTime(15);
		verifyElementIsPresent("//iframe");
		findIFrameToSwitch("//*[contains(@title,'Total Records')]//following-sibling::*");
		verifyElementIsPresent("//*[contains(@title,'Total Records')]//following-sibling::*");
		String val = elmTotalRecords.getText();
		System.out.println("No Of records present are " + val);
		driver.close();
		switchLastWindow();
		return Integer.parseInt(val);

	}
	
	public void expandMyCurrents() {
		verifyElementIsPresent(getLocatorInfo(webTableMyCurrents));
		System.out.println("Expand Currents");
		jsClick(elmExpandCurrents);
		verifyElementIsPresent(getLocatorInfo(webTableMyCurrents));
		
	}

	

	public void findIFrameToSwitch(String xpath) {
		driver.switchTo().defaultContent();
		int size = driver.findElements(By.tagName("iframe")).size();
		for (int i = 0; i <= size; i++) {
			System.out.println("Switching to Fame index "+ i);
			driver.switchTo().frame(i);
			int total = driver.findElements(By.xpath(xpath)).size();
			
			driver.switchTo().defaultContent();
			if (total == 1) {
				driver.switchTo().frame(i);
				break;
			}
		}
	}
	
	
	
	public void selectFromActionsMenuForCurrent(String name, String strMenuItem) {
		System.out.println("Checking for Talent " + name);
		waitForActionMenu("//*[contains(@class,'RecruiterCurrentTalentReport')]//table//tbody//tr//*[contains(text(),'"
				+ name + "')]/..//preceding-sibling::td//input");
		waitTime(2);
		fluentWait("//*[contains(@class,'RecruiterCurrentTalentReport')]//table//tbody//tr//*[contains(text(),'"
						+ name + "')]/..//preceding-sibling::td//input");
		WebElement myCurrentTalent = driver.findElement(
				By.xpath("//*[contains(@class,'RecruiterCurrentTalentReport')]//table//tbody//tr//*[contains(text(),'"
						+ name + "')]/..//preceding-sibling::td//input"));
	
		jsClick(myCurrentTalent);
		verifyElementIsPresent(getLocatorInfo(btnActions));
		System.out.println("Clicking on Action");
		jsClick(btnActions);
		waitTime(2);
		jsClick(driver.findElement(By.xpath("(//*[contains(text(), '" + strMenuItem + "')])[last()]")));	
	} 
	
	public void waitForActionMenu(String xpath) {
		boolean isTrue = false;
		long startTime = System.currentTimeMillis();
		long timeDiffs;
		do {
			driver.navigate().refresh();
			verifyTopMatch();
			isTrue = driver.findElements(By.xpath(xpath)).size() > 0;
			timeDiffs = System.currentTimeMillis() - startTime;
		} while (!isTrue && (timeDiffs < 30000));

		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffs);
		System.out.println("Talent created from API is Loaded on Currents within " + seconds + " Seconds");
	}
	
	public void selectValueInTaskFieldOnTaskDetailsPage(String taskTypeLabel, String taskTypeValue) {
		System.out.println("Selecting TaskType" + taskTypeLabel+" and TaskType "+ taskTypeValue);
		waitTime(3);
		String xpathValue = "//div[@class='slds cC_TalentActivityAddEditModal']//div[contains(text(), '" + taskTypeLabel
				+ "')]//descendant::a[1]";
		String xpathListItem = "//li/a[@title='" + taskTypeValue + "']";
		if (verifyElementIsPresent(xpathValue)) {
			jsClick(driver.findElement(By.xpath(xpathValue)));
			verifyElementIsPresent(xpathListItem);
			jsClick(driver.findElement(By.xpath(xpathListItem)));
		}
	}
	
	public void enterCommentsAndSave(String comments) {
		System.out.println("Entering Comments "+comments);
		elmComments.clear();
		elmComments.sendKeys(comments);
		System.out.println("Hit Save");
		jsClick(btnSave);
		waitTime(10);
		//verifyElementIsPresent(getLocatorInfo(elmConfirm));
}
	
}
