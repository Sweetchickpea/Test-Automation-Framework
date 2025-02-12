package com.ui.tests;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;



@Listeners({com.ui.listeners.TestListener.class})
public class InvalidCredLoginTest extends TestBase{

	
	Logger logger= LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_EMAIL_ADDRESS="mayadash22@gmail.com";
	private static final String INVALID_PASSWORD="Qwerty1234!";
	

	@Test(description = "Verifies if the proper error message shown for the user when they enter invalid credentials",
       groups = { "e2e",
			"sanity", "smoke" })

	public void loginTest() {

		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
				.getErrorMessage(),"Authentication failed.");

	}

	
}
