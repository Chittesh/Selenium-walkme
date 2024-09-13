package com.salesforce.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import com.salesforce.BasePage;

public class CandidateLandingPage extends BasePage {

	public CandidateLandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}