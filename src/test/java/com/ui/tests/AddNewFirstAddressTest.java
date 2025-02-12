package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

public class AddNewFirstAddressTest extends TestBase{
	
	private MyAccountPage myAccountPage;
	
	private AddressPOJO address;
	@BeforeMethod(description="Valid First time  user logs in to the application")
	public void setup() {
		myAccountPage= homePage.goToLoginPage().doLoginWith("nebix68940@perceint.com", "password");
		address= FakeAddressUtility.getFakeAddress();
	}
	
	
	
	
	@Test
	public void addNewAddress() {
		
		String newAddress= myAccountPage.goToAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
		
	}

}
