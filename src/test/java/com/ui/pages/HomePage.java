package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility {
	Logger logger= LoggerUtility.getLogger(this.getClass());
	
	
	//follows page object design pattern
	//Locators
	//By class
	
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),\"Sign in\")]");//CLASS VAR, BECAUSE ITS STATIC
	
	
	public HomePage(Browser browser, boolean isHeadless) {
		super(browser, isHeadless);//call parent class constructor
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		
	}
	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}
	public LoginPage goToLoginPage() {//page functions,cant use void return type here
		
		logger.info("Trying to Perform login click to go to sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage= new LoginPage(getDriver());
		return loginPage;
	}
	
	
	
	
	
	
	
	
	
}
