package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID() {
		return "testautomation"+ System.currentTimeMillis() + "@opencart.com";
		//testautomation121212@opencart.com
		
		//or
		//return "testautomation" + UUID.randomUUID() + "@gmail.com";
	}
	
	/*@DataProvider
	 public Object[][] getUserRegData() {
		return new Object[][] {
			{"jack", "Automation", "123877890", "ram@123", "yes"},
			{"raju", "Auto", "109456780", "sam@123", "no"},
			{"neha", "Automation", "1228667890", "gita@123", "yes"}
		};
	}*/
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	
	
	
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmailID() , telephone, password, subscribe);
		Assert.assertTrue(isRegDone);
	}

}
