package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.BrowserUtility;

public class LoginTestOLD {

	public static void main(String[] args) {
		
		WebDriver wd= new ChromeDriver();//launch browser window
		
		/*BrowserUtility browserUtility= new BrowserUtility(wd);
		browserUtility.goToWebsite("http://www.automationpractice.pl");
		browserUtility.maximizeWindow();
		
		//wd.get("http://www.automationpractice.pl/index.php");//hard-coded
		
		//maximize window
		
		//wd.manage().window().maximize();//chaining of methods
		
		
		
		By signInLinkLocator=By.xpath("//a[contains(text(),\"Sign in\")]");
		//WebElement signInLinkWebElement=wd.findElement(sigInLinkLocator);//Selenium webdriver will find the element
		//signInLinkWebElement.click();
		browserUtility.clickOn(signInLinkLocator);
		
		
		
		
		By emailTextBoxLocator=By.id("email");
		//WebElement emailTextBoxwebElement=wd.findElement(emailTextBoxLocator);
		//emailTextBoxwebElement.sendKeys("nebix68940@perceint.com");//invalid cred
		
		
		
		browserUtility.enterText(emailTextBoxLocator, "nebix68940@perceint.com");
		
		
		By passwordTextBoxLocator=By.id("passwd");
		//WebElement passwordTextBoxwebElement=wd.findElement(passwordTextBoxLocator);
		//passwordTextBoxwebElement.sendKeys("password");//invalid cred-submitLogin
		
		browserUtility.enterText(passwordTextBoxLocator, "password");
		
		
		By submitLoginButtonLocator=By.id("SubmitLogin");
		//WebElement submitLoginButtonwebElement=wd.findElement(submitLoginButtonLocator);
		//submitLoginButtonwebElement.click();
		browserUtility.clickOn(submitLoginButtonLocator);*/
		
		
		

	}

}
