package com.salesforce.pages;

import java.time.Duration;
import java.util.Base64;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.BasePage;
import com.salesforce.data.Constants;

public class AccountPage extends BasePage {
	private Logger logger = LogManager.getLogger(AccountPage.class);
	/** Page Elements **/
	@FindBy(xpath = "//*[contains(@title,'New')]")
	private WebElement newButton;
	@FindBy(xpath = "//input[@value='Create Account Manually']")
	private WebElement createAccountManully;
	
	@FindBy(xpath = "//input[contains(@id,'AccountInformation')]")
	private WebElement accountName;
	@FindBy(xpath = "(//input[contains(@id,'AccountInformation')])[2]")
	private WebElement phoneNumber;
	@FindBy(xpath = "//*[contains(@class,'multiSelectPicklistTable')]//a[contains(@href,'MultiSelectPicklist')]")
	private WebElement rightArrow;
	@FindBy(xpath = "//*[contains(@class,'multiSelectPicklistTable')]//option[@value='1']")
	private WebElement contract;
	@FindBy(xpath = "(//textarea[contains(@id,'Address')])[2]")
	private WebElement workStreet;
	@FindBy(xpath = "(//input[contains(@id,'Address')])[2]")
	private WebElement workSiteCity;
	@FindBy(xpath = "(//input[contains(@id,'Address')])[4]")
	private WebElement workSiteState;
	@FindBy(xpath = "(//input[contains(@id,'Address')])[6]")
	private WebElement workSitePostalCode;
	@FindBy(xpath = "(//input[contains(@id,'Address')])[8]")
	private WebElement workSiteCountry;
	@FindBy(xpath = "//*[contains(text(),'Uses WMS?')]/../following-sibling::*//input")
	private WebElement wms;
	@FindBy(xpath = "(//input[@type='submit'][@value='Save'])[last()]")
	private WebElement accountsave;
	
	//Accounts Detail Page
	@FindBy(xpath = "//*[contains(text(),'Account')]/parent::h1//lightning-formatted-text")
	public WebElement txtAccountName;
	@FindBy(xpath = "//lightning-formatted-address//a")
	public WebElement workSiteLocation;
	
	
	
	
	public String getworkSiteLocation() {
		String location =workSiteLocation.getText().replaceAll("\\n", "");
		System.out.println(location);
		return location;
	}
	
	public String getAccountName() {
		fluentWait(getLocatorInfo(workSiteLocation));
		return txtAccountName.getText();
	}
	

	/** Constructor **/
	public AccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigateToAccount() {
		System.out.println("Navigating to Account Page");
		driver.navigate().to(
				"https://allegisgroup--martest23.sandbox.lightning.force.com/lightning/o/Account/list?filterName=Recent");
		waitTime(3);
		fluentWait(getLocatorInfo(newButton));

	}

	public void clickOnNewButton() {
		System.out.println("Clicking on New Button");
		newButton.click();
		findIFrameToSwitch(getLocatorInfo(createAccountManully));
		fluentWait(getLocatorInfo(createAccountManully));

	}

	public void clickOnCreateAccount() {
		System.out.println("Clicking on Create Account");
		createAccountManully.click();
		swithToFrameOnCreation();
		fluentWait(getLocatorInfo(accountName));
	}
	
	public void fillFields() {
		System.out.println("Selecting Account Name");
		jsClick(accountName);
		accountName.sendKeys("DemoTestAccount786");
		System.out.println("Selecting phone Number");
		jsClick(phoneNumber);
		phoneNumber.sendKeys("4105791234");
		System.out.println("Adding contract");
		jsScroll(contract);
		contract.click();
		waitTime(1);
		rightArrow.click();
		waitTime(1);
		System.out.println("Selecting Work Street");
		jsClick(workStreet);
		workStreet.sendKeys("7312 Parkways Drive");
		System.out.println("Selecting Work City");
		jsClick(workSiteCity);
		workSiteCity.sendKeys("Hanover");
		System.out.println("Selecting Work State");
		jsClick(workSiteState);
		workSiteState.sendKeys("Maryland");
		System.out.println("Selecting Postal Code");
		jsClick(workSitePostalCode);
		workSitePostalCode.sendKeys("21076");
		System.out.println("Selecting Work Site Country");
		jsClick(workSiteCountry);
		workSiteCountry.sendKeys("United States");
		System.out.println("Selecting WMS checkBox");
		jsClick(wms);
		
		
	}
	
	public void changeWorkSiteState() {

		jsClick(workSiteState);
		System.out.println("Clearing work site state");
		workSiteState.clear();
		workSiteState.sendKeys("MD");
	}
	public void clickOnSave() {
		System.out.println("Click on save");
		jsClick(accountsave);
	}

	public void findIFrameToSwitch(String xpath) {
		driver.switchTo().defaultContent();
		fluentWait(("//iframe"));
		driver.switchTo().frame(0);
		fluentWait(xpath);
		
	}
	
	public void swithToFrameOnCreation() {
		driver.switchTo().defaultContent();
		waitTime(2);
		driver.switchTo().frame(1);
		waitTime(2);
		
		
	}
	
	public void checkAbbrevationErrorMessage() {
		System.out.println("verify error message Worksite State/Province: A valid two-letter state abbreviation is required.");
		String xpath = "//*[contains(text(),'Worksite State/Province: A valid two-letter state abbreviation is required.')]";
		fluentWait(xpath);
	}

}
