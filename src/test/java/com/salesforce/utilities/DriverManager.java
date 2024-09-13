package com.salesforce.utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	public WebDriver setupLocalDriver(String runLocation) throws Exception {
		WebDriver driver = null;

		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("test-type");
		options.addArguments("disable-popup-blocking");	
		options.addArguments("--disable-notifications");
		
		options.setAcceptInsecureCerts(true);
		options.addArguments("start-maximized");
		// options.addArguments("--incognito");
		options.addArguments("--remote-allow-origins=*");

		options.addExtensions(new File(System.getProperty("user.dir")
				+ "/src/main/resources/extension/WalkMe Extension - Chrome Web Store 4.0.193.0.crx"));

		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);

		if (runLocation.contains("local")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (runLocation.contains("docker")) {
			//String remoteUrl = "http://10.62.234.23:4444/wd/hub";
			String remoteUrl = "http://57.152.78.134/";
			
			WebDriverManager driverManager = WebDriverManager.chromedriver();

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = (RemoteWebDriver) driverManager.remoteAddress(remoteUrl).capabilities(capabilities).create();

		}
		return driver;
	}

	/*
	 * public WebDriver setupLocalDriver(String runLocation) throws Exception {
	 * WebDriver driver = null; EdgeOptions options = new EdgeOptions();
	 * options.setAcceptInsecureCerts(true);
	 * options.addArguments("start-maximized");
	 * options.addArguments("--remote-allow-origins=*"); options.addExtensions(new
	 * File("C:\\Users\\chicharles\\Downloads\\WalkMe Extension - Chrome Web Store 4.0.193.0.crx"
	 * )); options.setPageLoadStrategy(PageLoadStrategy.EAGER);
	 * 
	 * DesiredCapabilities capabilities = new DesiredCapabilities();
	 * capabilities.setCapability(EdgeOptions.CAPABILITY, options);
	 * options.merge(capabilities);
	 * 
	 * if (runLocation.contains("local")) { WebDriverManager.edgedriver().setup();
	 * driver = new EdgeDriver(options); }else if (runLocation.contains("docker")) {
	 * String remoteUrl = "http://10.62.234.23:4444/wd/hub"; WebDriverManager
	 * driverManager = WebDriverManager.edgedriver();
	 * 
	 * 
	 * capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	 * 
	 * driver = (RemoteWebDriver)
	 * driverManager.remoteAddress(remoteUrl).capabilities(capabilities).create();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * return driver; }
	 */

	public void quit(WebDriver driver) {
		if (driver != null)
			driver.quit();
	}

}
