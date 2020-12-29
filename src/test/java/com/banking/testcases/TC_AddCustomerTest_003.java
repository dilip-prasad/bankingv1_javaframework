package com.banking.testcases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.pageobjects.AddCustomerPage;
import com.banking.pageobjects.LoginPage;



public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("uname added");
		lp.setPassword(password);
		logger.info("pwd added");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		logger.info("adding customer");
		addcust.clickAddNewCustomer();
		addcust.custName("Dilipa");
		addcust.custgender("female");
		addcust.custdob("11", "10", "1995");
		Thread.sleep(3000);
		addcust.custaddress("Indiaa");
		addcust.custcity("chennaia");
		addcust.cusstate("TNa");
		addcust.custpinno("600066");
		addcust.custtelephoneno("9745123455");
		
		String email=randomString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("ascsax");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true) {
			Assert.assertTrue(true);
			logger.info("passed");
		}
		else {
			logger.info("failed");
			captureScreen(driver, "addnewcustomer");
			Assert.assertTrue(false);
		}
		 
		
	}
	


}
