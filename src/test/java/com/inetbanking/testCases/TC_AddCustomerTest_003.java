package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;


public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp= new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Username is provided");
		lp.setpassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addCust= new AddCustomerPage(driver);
		
		addCust.clickAddNewCustomer();
		
		logger.info("Providing Customer Details");
		
		addCust.custName("Saloni");
		addCust.custgender("Female");
		addCust.custdob("07","29","1991");
		Thread.sleep(5000);
		addCust.custaddress("INDIA");
		addCust.custcity("CHD");
		addCust.custstate("UT");
		addCust.custpinno("160047");
		addCust.custtelephoneno("9876990399");
		
		
		String email= randomString() + "@gmail.com";
		addCust.custemailid(email);
		addCust.custemailid(email);
		addCust.custpassword("abcdef");
		addCust.custsubmit();
		Thread.sleep(3000);
		
		logger.info("Validation Started");
		boolean result=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(result==true) {
			Assert.assertTrue(true);
			logger.info("Test Case Passed");
		}
		else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
			logger.info("Test Case Failed");
		}
	}
	
}
