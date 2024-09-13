package com.salesforce.crm;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.apiCalls.RestCalls;
import com.salesforce.pages.LoginPage;
import com.salesforce.utilities.TestEnvironment;

public class ReqOpportunityTest extends TestEnvironment {

	
	@Test()
	@Parameters("role")
	public void veryfyReqOpportunityCreation(String role) throws InterruptedException {
		getDriver().navigate().to("chrome://extensions/");
		Thread.sleep(15000);
		String urlToUpdateContactId = "";
		if (role.contains("LOAD")) {
			urlToUpdateContactId = "https://allegisgroup--load.sandbox.lightning.force.com/lightning/r/Contact/%s/view";

		} else {
			urlToUpdateContactId = "https://allegisgroup--martest23.sandbox.lightning.force.com/lightning/r/Contact/%s/view";
		}

		launchSalesForceUrl();
	
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.roleLogin(role);
		
		//;
		/*String token = RestCalls.getToken();
		String clientContactId = RestCalls.CreateContactClient(token, RestCalls.CreateAccountClient(token));
		navigateToUrlWithContactId(urlToUpdateContactId, clientContactId);
		CRMWorkflowValidations crmValidations = new CRMWorkflowValidations(getDriver());

		// creating req
		crmValidations.verifyCreateReqOpportunity();

		// Editing Req Page
		crmValidations.verifyErrorMsgEditStageToQualifying();
		crmValidations.verifyBannerQualifiedAfterAutoPopulatingFields();*/
		System.out.println(loginPage.checkWalkMe());
		

	}
	
	private void navigateToUrlWithContactId(String urlToUpdateContactId,String clientContactId) {
		String urlWithContactId = String.format(urlToUpdateContactId, clientContactId);
		getDriver().navigate().to(urlWithContactId);
	}

}
