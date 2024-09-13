package com.salesforce.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;

public class EditReqOppPage extends BasePage {

	@FindBy(xpath = "(//button[@class='slds-button slds-button_brand section-edit_button  slds-m-right_small'])[1]")
	private WebElement btnEdit;
	@FindBy(xpath = "(//select[@class='slds-select'])[1]")
	private WebElement dropDownQualifyingStage;
	@FindBy(xpath = "//button[@name='update']")
	private WebElement btnSave;
	@FindBy(xpath = "//*[@id='label-description']/..//textarea")
	private WebElement txtJobDescription;
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
	@FindBy(xpath = "//*[@id='label-inputDurationName']/..//input")
	private WebElement txtDuration;
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[3]")
	private WebElement dropDownDuration;
	@FindBy(xpath = "(//span[text()='Month(s)'])[1]")
	private WebElement dropText;
	@FindBy(xpath = "//*[@id='label-qualificationId']/..//textarea")
	private WebElement txtAddQualification;
	@FindBy(xpath = "//*[@id='label-extCommunities']/..//textarea")
	private WebElement txtExtCommunities;
	@FindBy(xpath = "//lightning-input[@id='inputStartDate']//input")
	private WebElement txtStartDate;
	@FindBy(xpath = "(//*[@class='slds-radio_button-group']//input)[1]")
	private WebElement btnsWorkplaceType;
	@FindBy(xpath = "(//div[@class='skill-field slds-grid']//input[@placeholder='Search'])[2]")
	private WebElement eleTopSkillSelected;
	@FindBy(xpath = "//*[contains(@class,'toastMessage')]")
	public WebElement errorMessage;
	@FindBy(xpath = "//p[@data-id='Qualifying Stage']")
	public WebElement bannerStage;
	@FindBy(xpath ="//lightning-base-combobox-item[@data-value='Month(s)']")
	public WebElement durationMonth;

	public EditReqOppPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void editQualifyingStage() {
		isDisplayed(btnEdit);
		jsClick(btnEdit);
		isDisplayed(dropDownQualifyingStage);
		select(dropDownQualifyingStage, "Qualified");
		waitTime(2);
		isDisplayed(btnSave);
		jsClick(btnSave);
		waitTime(1);
	}

	public void addRequiredFieldsSave() {
		isDisplayed(btnsWorkplaceType);
		jsClick(btnsWorkplaceType);
		waitTime(1);
		isDisplayed(eleTopSkillSelected);
		eleTopSkillSelected.sendKeys("JavaScript");
		eleTopSkillSelected.sendKeys(Keys.ENTER);
		waitTime(2);
		eleTopSkillSelected.sendKeys("Apex");
		eleTopSkillSelected.sendKeys(Keys.ENTER);
		waitTime(2);
		eleTopSkillSelected.sendKeys("Visual Force");
		eleTopSkillSelected.sendKeys(Keys.ENTER);
		waitTime(2);
		isDisplayed(txtJobDescription);
		jsScroll(txtJobDescription);
		txtJobDescription.sendKeys("Test req is created for testing purpose, Test req is created for testing purpose ");
		isDisplayed(txtAddQualification);
		txtAddQualification.sendKeys("Test req is created for testing purpose, Test req is created for testing purpose ");
		isDisplayed(txtExtCommunities);
		txtExtCommunities.sendKeys("Test req is created for testing purpose, Test req is created for testing purpose ");
		isDisplayed(dropDownDuration);
		jsClick(dropDownDuration);
		isDisplayed(durationMonth);
		jsClick(durationMonth);
		waitTime(2);
		isDisplayed(txtDuration);
		txtDuration.sendKeys("6");
		isDisplayed(txtBillRateMin);
		txtBillRateMin.sendKeys("50");
		isDisplayed(txtBillRateMax);
		txtBillRateMax.sendKeys("75");
		isDisplayed(txtPayRateMin);
		txtPayRateMin.sendKeys("20");
		isDisplayed(txtPayRateMax);
		txtPayRateMax.sendKeys("30");
		isDisplayed(txtTotalBurden);
		txtTotalBurden.sendKeys("1");
		isDisplayed(txtStartDate);
		txtStartDate.sendKeys("3/15/2023");
		txtStartDate.sendKeys(Keys.ENTER);
		waitTime(2);
		jsClick(btnSave);
		waitTime(2);
		isDisplayed(bannerStage);
	}

}
