package com.salesforce.utilities;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.salesforce.data.Constants;

public class TestEnvironment {

	static ResourceBundle urls = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	protected  static final TestEnvironmentExecutionContext context = new TestEnvironmentExecutionContext();
	private static   ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>();
	DriverManager driverManager;
	//private Logger logger = LogManager.getLogger(TestEnvironment.class);

	@BeforeClass(alwaysRun = true)
	@Parameters({ "role" })
	public void setupBrowserEnvironment(@Optional("") String role) {
		context.setRole(role);
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters("runLocation")
	public  void driverSetup(String runLocation,ITestContext context) throws Exception {
		System.out.println("Setting up Driver instance for "+context.getCurrentXmlTest().getName());
		driverManager = new DriverManager();
		setDriver(driverManager.setupLocalDriver(runLocation));
	}

	protected void setDriver(WebDriver driverSession) {
		threadedDriver.set(driverSession);
	}

	public WebDriver getDriver() {
		return threadedDriver.get();
	}

	public void launchSalesForceUrl() {
		System.out.println("Launching URL " + urls.getString("SALESFORCE_" + getEnvironment()));
		getDriver().get(urls.getString("SALESFORCE_" + getEnvironment()));
	}

	private String getEnvironment() {
		String[] role = context.getRole().split("_");
		return role[2];
	}

	
	
	@AfterMethod(alwaysRun = true)
	public void driverClose() {
		System.out.println("Quit Driver");
		getDriver().quit();
	}
	
	 @AfterMethod(alwaysRun = true)
		public void name(ITestResult result, ITestContext contex) {
			long ms = result.getEndMillis() - result.getStartMillis();
			long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);
			System.out.println("********************************************************");
			System.out.println(
					"Time taken to run test " + contex.getCurrentXmlTest().getName() + " is : " + seconds + " seconds");
			System.out.println("********************************************************");
		}
}
