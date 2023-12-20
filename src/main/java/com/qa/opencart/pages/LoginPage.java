package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locators for LoginPage
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	//private By footer = By.xpath("//footer/div[@class='container']");
	private By registerLink = By.linkText("Register");
	private By loginErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle() {
		//String title = eleUtil.waitForTitleIs("Account Login", 5);
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title: " + title);
		return title;
	}
	
	@Step("getting login page URL")
	public String getLoginPageURL() {
		//String url = eleUtil.waitForURLContains("account/login", 5);
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page URL: " + url);
		return url;
	}
	
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("checking app logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	/*
	 * public void isFooterAvailable() { loginPage }
	 */
	
	@Step("username is : {0} and pwd is : {1}")
	public AccountsPage doLogin(String username , String pwd) {
		System.out.println("credentials are: " +username+ " : " +pwd);
		//eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//System.out.println("user is logged in");
		//return true;
		return new AccountsPage(driver);
	}
	
	public boolean doLoginWithWrongCredentials(String username, String pwd) {
		System.out.println("Wrong credentials are: " + username + ":" + password);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMsg = eleUtil.doElementGetText(loginErrorMsg);
		System.out.println(errorMsg);
		if(errorMsg.contains(AppConstants.LOGIN_ERROR_MESSAGE)) { 
			return true;
		}
		return false;
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
	

}
