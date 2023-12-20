package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest{
	
	@BeforeTest
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username") , prop.getProperty("password"));
	}
	
	/*
	 * @DataProvider 
	 * public Object[][] getSearchData() { 
	 * return new Object[][] {
	 * 			{"MacBook" , "MacBook Pro" , 4}, 
	 * 			{"MacBook" , "MacBook Air" , 4}, 
	 * 			{"iMac" ,"iMac", 3}, 
	 * 			{"Samsung" , "Samsung SyncMaster 941BW" , 1} 
	 * 	};
	 * }
	 */
	
	@DataProvider
	public Object[][] getSearchExcelData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
		
	}
	
	@Test(dataProvider = "getSearchExcelData")
	public void productImagesTest(String searchKey , String productName , String imageCount){
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()) , imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String , String> productDetailsMap = productInfoPage.getProductDetails();
		
		Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		Assert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		
		Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		Assert.assertEquals(productDetailsMap.get("extexprice"), "$2,000.00");
		
		
		/* softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		 * softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		 * softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		 * softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		 * 
		 * softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		 * softAssert.assertEquals(productDetailsMap.get("extexprice"), "$2,000.00");
		 *
		 * softAssert.assertAll();
		 */
	}
	
	//HardAssert: If one assertion is failed then the Test Case is terminated or marked as failed &
	//			  other assertions are not varifed.
	//			  HardAssert is used when will said about specific one Assert/ single assert
	//			  if a HardAssert is failed, it will not affect the other Test cases. Only affect that Assert Testcase
	
	//SoftAssert: SoftAssert is a class in TestNG.
	//			  First we create the object of SoftAssert class, then by using reference of SoftAssert class we can use the assert methods.
	//			  If one of the assert is failed then it will go to next assert. 
	//			  It will not terminated the Test case.
	//			  softAssert.assertAll() --> will give the number of assert failed.
	//			  When we have multiple checks then only we use SoftAssert

}
