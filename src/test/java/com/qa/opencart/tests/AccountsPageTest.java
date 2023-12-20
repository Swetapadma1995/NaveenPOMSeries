package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		//accPage = new AccountsPage(driver);
		//accPage = loginPage.doLogin("naveenaugbatch2023@opencart.com", "selenium@12345");
		accPage = loginPage.doLogin(prop.getProperty("username") , prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitileTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccountPageURL().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldkExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeader();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeader();
		System.out.println(actAccPageHeadersList);
		//sort the actual list
		//sort the expected list
		//compare
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		searchResultsPage.scrollPage();
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
	
}
