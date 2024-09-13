

package com.salesforce.esf;


import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.BasePage;
import com.salesforce.utilities.SalesforceStaticData;
import com.salesforce.utilities.TestEnvironment;

public class ESFWorkFlowTest extends TestEnvironment{

	@Test()
	public void verifyEsfE2EWorkFlow() throws Exception {
		String role = "TEK_SD1_TEST";
		WebDriver driver = getDriver();
		EsfWorkflow workflow = new EsfWorkflow(driver);
		BasePage basePage = new BasePage(driver);
		
		String[] id = workflow.getESFDataFromApiService(role);
		
		String esfURL = workflow.performBasicWorkflowForEsf(role, basePage, id);		
		workflow.performingActionUnderTGSD1Access(role, basePage, esfURL);	
		workflow.performActionsUnderPOAAccess(role, basePage, esfURL);	
		
		String actualACTText = basePage.getActStatus();
		String expectedACTText = "ACT Received";
		Assert.assertEquals(actualACTText, expectedACTText,"Verify ACT status");
	}
	


}


