package com.salesforce.ats;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;

public class TalentSummaryPage extends BasePage {

	@FindBy(xpath = "//div[@class='slds-grid']//div[contains(@title,'Java Developer')]")
	public WebElement txtJobTitle;
	@FindBy(xpath = "//li[@title='java']")
	public WebElement skillJava;
	@FindBy(xpath = "//li[@title='javascript']")
	public WebElement skillJs;
	@FindBy(xpath = "//li[@title='apex']")
	public WebElement skillApex;
	@FindBy(xpath = "//div[text()='strong programming skills on Java script/Apex']")
	public WebElement skillComment;
	@FindBy(xpath = "//div[text()='Salesforce CPQ Certifiation']")
	public WebElement goalIntrest;
	@FindBy(xpath = "//div[text()='Comments']")
	public WebElement comments;
	@FindBy(xpath = "//p[text()='No']/strong[text()='Clearance Type']")
	public WebElement clearanceType;
	@FindBy(xpath = "//p[text()='CIA']")
	public WebElement fedClearance;
	@FindBy(xpath = "//li[@title='English']")
	public WebElement langEnglish;
	@FindBy(xpath = "//li[@title='French']")
	public WebElement langFrench;
	@FindBy(xpath = "//p[text()='any']")
	public WebElement placementType;
	@FindBy(xpath = "//p[text()='full-time']")
	public WebElement schedule;
	@FindBy(xpath = "//p[text()='First']")
	public WebElement desiredShift;
	@FindBy(xpath = "//span[text()='Mar 15, 2023']")
	public WebElement date;
	@FindBy(xpath = "//p[text()='No']/strong[text()='Willing to Relocate']")
	public WebElement willingRelocate;
	@FindBy(xpath = "//p[text()='Yes']/strong[text()='National Opportunities']")
	public WebElement nationalOpp;
	@FindBy(xpath = "//p[text()='Yes']/strong[text()='Reliable Transportation']")
	public WebElement reliableTransport;
	@FindBy(xpath = "//li[@title='United States']")
	public WebElement countriesLegally;
	@FindBy(xpath = "//p[@title='United States']")
	public WebElement country;
	@FindBy(xpath = "//p[@title='England']")
	public WebElement state;
	@FindBy(xpath = "//p[@title='Baltimore']")
	public WebElement city;
	@FindBy(xpath = "//label[text()='G2 Completed']")
	public WebElement g2Comment;
	// Headers Talent Page
	@FindBy(xpath = "//h1[@id='tlp-name']")
	public WebElement talentName;
	@FindBy(xpath ="//p[@class='slds-m-right_x-small']")
	public WebElement talentId;
	@FindBy(xpath = "//div[@class='slds-icon_container widget-button linkedInIcon']")
	public WebElement linkdenIcon;
	@FindBy(xpath = "//span[@data-aura-class='uiOutputEmail']")
	public WebElement email;
	@FindBy(xpath = "//span[@data-aura-class='uiOutputPhone']")
	public WebElement phoneNumber;
	@FindBy(xpath = "//div[@title='7312 Parkway Drive, Hanover Maryland, 21076, United States']")
	public WebElement location;
	

	public TalentSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean isTalentIdPresent() {
		fluentWait("//h1[@id='tlp-name']", 60);
		//isTalentPageLoaded();
		//isDisplayed(talentName);
		mouseHover(talentName);
		waitTime(2);
		return fluentWait("//p[@class='slds-m-right_x-small']");
	}

	public void isTalentPageLoaded() {
		isDisplayed(talentName);
	}
}
