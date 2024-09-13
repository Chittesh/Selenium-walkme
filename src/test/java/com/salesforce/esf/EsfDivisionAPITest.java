
package com.salesforce.esf;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.salesforce.BasePage;
import com.salesforce.utilities.SalesforceStaticData;
import com.salesforce.utilities.TestEnvironment;

public class EsfDivisionAPITest {

	@Test()
	public void verifyDivisionDetailsAPIResponse() throws Exception {
		DivisionApiEndpointManager divisionApiEndpointManager = new DivisionApiEndpointManager();
		List<String> divisionDetails = divisionApiEndpointManager.getDivisionDetails("test", "EFI");
		Assert.assertTrue((divisionDetails.size() >= 1), "Division lookup details are displayed");
		Assert.assertTrue(divisionDetails.equals(Arrays.asList(SalesforceStaticData.DIVISION_LOOKUP_VALUES)),
				"Division lookup details are displayed as expected");
	}

	@AfterMethod
	public void name(ITestResult result, ITestContext contex) {
		long ms = result.getEndMillis() - result.getStartMillis();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);
		System.out.println(
				"Time taken to run test " + contex.getCurrentXmlTest().getName() + " is : " + seconds + " seconds");
	}
}
