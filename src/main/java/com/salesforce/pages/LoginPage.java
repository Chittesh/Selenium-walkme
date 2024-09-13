package com.salesforce.pages;

import java.time.Duration;
import java.util.Base64;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.BasePage;
import com.salesforce.data.Constants;


public class LoginPage extends BasePage {
	private Logger logger = LogManager.getLogger(LoginPage.class);
	/** Page Elements **/
	@FindBy(id = "username")
	private WebElement txtUserName;
	@FindBy(id = "username")
	private WebElement txtUser;
	@FindBy(id = "password")
	private WebElement txtPassword;
	@FindBy(id = "Login")
	private WebElement btnLogin;
	
	@FindBy(xpath = "//*[contains(@class,'walkme-icon-image-div walkme-launcher-image-div')]")
	private WebElement walkMe;
	
	@FindBy(xpath = "//*[contains(@class,'assistantCardList')]//a")
	private WebElement asi;
	

	/** Constructor **/
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void roleLogin(String role) {
		logger.info("Entering User Name & Password");
		ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);
		String userName = userCredentialRepo.getString(role.toUpperCase());
		String password = userCredentialRepo.getString(role.toUpperCase() + "_PASSWORD");
		this.validLogin(userName, password);
	}

	public void validLogin(String userName, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(txtUserName));
		txtUserName.sendKeys(userName);
		byte[] decodedBytes = Base64.getDecoder().decode(password);
		String decodedString = new String(decodedBytes);
		txtPassword.sendKeys(decodedString);
		btnLogin.click();
		wait.until(ExpectedConditions.elementToBeClickable(asi));
		
		

	}
	
	public boolean checkWalkMe() {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		wait.until(ExpectedConditions.elementToBeClickable(walkMe));
		//wait.until(ExpectedConditions.elementToBeClickable(txtUserName));
		return driver.findElements(By
				.xpath("//*[contains(@class,'walkme-icon-image-div walkme-launcher-image-div')]"))
				.size()>0;
		
	}

}
