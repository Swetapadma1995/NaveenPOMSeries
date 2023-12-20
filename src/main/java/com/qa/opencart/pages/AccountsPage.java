package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators for AccountPage
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accountHeaders = By.cssSelector("div#content > h2");
	
	//AccountPage Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//AccountPage Actions:
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page title: " + title);
		return title;
	}
	
	public String getAccountPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page URL: " + url);
		return url;
	}
	
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountsHeader() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accountHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersListValue = new ArrayList<String>();
		for(WebElement e : headersList) {
			String text = e.getText();
			headersListValue.add(text);
		}
		
		return headersListValue;
		
	}	
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);//TDD --> Test Driven Development
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	

