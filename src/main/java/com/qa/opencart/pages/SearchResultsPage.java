package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	//Page constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	public void scrollPage() {
		jsUtil.scrollMiddlePageDown();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
