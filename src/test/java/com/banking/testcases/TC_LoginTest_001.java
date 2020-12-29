package com.banking.testcases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.pageobjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("uname entered");
		lp.setPassword(password);
		logger.info("pwd entered");
		lp.clickSubmit();
		logger.info("submitted");
		
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			
			logger.info("login test passed");
		}
		else {
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("login test failed");
			
		}
	}

}
