package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {
		
		LoginPage lp= new LoginPage(driver);
		lp.setUsername(user);
		logger.info("username provided");
		lp.setpassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("login passed");
			Thread.sleep(3000);
			lp.clickLogout();
			driver.switchTo().alert().accept();  // close the logout alert
			driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;		// returning true if the alert is present--> by checking if window is switched to alert windo
		}
		catch(NoAlertPresentException e){
			return false;
			
		}
		
	}
	  
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException{
		String path=System.getProperty("user.dir") +  "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rowNum=XLUtils.getRowCount(path, "Sheet1");
		int colCount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData [][] =new String [rowNum][colCount];
		
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colCount;j++) {
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
				
	}
	
}
