package com.salesforce.ats.home;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salesforce.BasePage;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.LoginPage;
import com.salesforce.utilities.SalesForceUtilities;
import com.salesforce.utilities.TestEnvironment;

public class HomePageValiadationTest extends TestEnvironment {

	@Parameters("role")
	@Test()
	public void verifyViewReportCountOnMyCurrent(String role) throws Exception {
		launchApplicationAndLogin(role);
		
		HomePage homePage = new HomePage(getDriver());
		Assert.assertTrue(homePage.clickOnViewReportForCurrents() > 0,
				"Verify no of records from view report of currents");
	}

	@Parameters("role")
	@Test()
	public void verifyJobTitleNavigationFomReq(String role) throws Exception {
		launchApplicationAndLogin(role);

		HomePage homePage = new HomePage(getDriver());
		Assert.assertTrue(homePage.verifyOpenReqJobTitle(), "Verify Open Req Job title is present");
		homePage.clickOnJobTitle();

		BasePage basePage = new BasePage(getDriver());
		Assert.assertTrue(basePage.validateSObjectPresenceOnURL("Opportunity"),
				"Verify user is Navigated to Opportunity page");
	}

	@Parameters("role")
	@Test()
	public void verifyTalentNameNavigationFromSubmittal(String role) throws Exception {
		launchApplicationAndLogin(role);
		HomePage homePage = new HomePage(getDriver());
		BasePage basePage = new BasePage(getDriver());

		Assert.assertTrue(homePage.verifyViewTalentDeatils(), "Verify View Talent Details");
		homePage.clickOnViewTalentDetails();
		homePage.switchLastWindow();
		Assert.assertTrue(basePage.validateSObjectPresenceOnURL("Contact"), "Verify user is Navigated to Contact page");

	}

	@Parameters("role")
	@Test()
	public void verifyAddTaskFromMyCurrentTalent(String role) throws Exception {
		BasePage basePage = new BasePage(getDriver());
		String profileName = basePage.getProfileName(role);
		String talOwnrShip = basePage.getOpcoName(role);
		SalesForceUtilities objSalesForceUtilities = new SalesForceUtilities(getDriver());
		
		/*objSalesForceUtilities.dataSetupForApplications(role, talOwnrShip,
				"false", "");*/
		
		HashMap<String, String> hashMapOfTalent = new HashMap<String, String>();
		hashMapOfTalent = objSalesForceUtilities.dataSetupForMyCurrents(role, talOwnrShip, profileName);
		
	
		basePage.waitTime(5);
		System.out.println("Waiting for 5 seconds for data to get loaded");
		launchApplicationAndLogin(role);
		
		HomePage homePage = new HomePage(getDriver());
		homePage.selectFromActionsMenuForCurrent(hashMapOfTalent.get("LastName"), "New Task");

		homePage.selectValueInTaskFieldOnTaskDetailsPage("Type", "Service Touchpoint");
		homePage.enterCommentsAndSave("Comments");

		basePage.navigateToTalentRecord(hashMapOfTalent.get("ContactId"), "Contact");
		Assert.assertTrue(basePage.verifyTalentActivity(), "Verify Talent Activity");
		WebElement activites = getDriver().findElement(
				By.xpath("//*[contains(@class,'TalentActivityTimeline')]//*[contains(text(),'Activities')]"));
		Assert.assertTrue(activites.getText().contains("(1)"), "Verify 1 Activity is added to talent");

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
