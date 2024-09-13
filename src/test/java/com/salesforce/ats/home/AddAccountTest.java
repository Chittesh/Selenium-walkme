package com.salesforce.ats.home;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.BasePage;
import com.salesforce.pages.AccountPage;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.LoginPage;
import com.salesforce.utilities.TestEnvironment;

public class AddAccountTest extends TestEnvironment {

	@Parameters("role")
	@Test()
	public void verifyAddAccount(String role) throws Exception {
		launchApplicationAndLogin(role);
		AccountPage accountPage = new AccountPage(getDriver());
		accountPage.navigateToAccount();
		accountPage.clickOnNewButton();
		accountPage.clickOnCreateAccount();
		accountPage.fillFields();
		accountPage.clickOnSave();
		accountPage.checkAbbrevationErrorMessage();
		
		accountPage.changeWorkSiteState();
		accountPage.clickOnSave();
		
		
		Assert.assertTrue(accountPage.getAccountName().contains("DemoTestAccount786"),"Verify Account Name on header Account Detail Page");
		Assert.assertTrue(accountPage.getworkSiteLocation().contains("7312 Parkways DriveHanover, MD 21076USA"), "Verify WorkSite Location on Acccounts Details Page");
		
	}


	public void launchApplicationAndLogin(String role) {
		launchSalesForceUrl();

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.roleLogin(role);

		HomePage homePage = new HomePage(getDriver());
		Assert.assertTrue(homePage.verifyHomePageTab(), "Verify Home Page Tab is present");
		Assert.assertTrue(homePage.verifyTopMatch(), "Verify Match Talent Top Matches");
	}

}
