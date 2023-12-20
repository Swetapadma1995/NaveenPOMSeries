package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	//private By productImages = By.cssSelector("ul.thumbnails img");
	private By productImages = By.xpath("div[@id='logo']/a/img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	//private Map<String , String> productMap = new HashMap<String , String>(); // Random order
	//private Map<String , String> productMap = new LinkedHashMap<String , String>(); // insertion order
	private Map<String , String> productMap = new TreeMap<String , String>(); //Alphabetic sorted order
	
	//ProductInfoPage Constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("product header: " + productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product: " + getProductHeaderName() + "images count: " + imagesCount);
		return imagesCount;
	}
	
	/*
	 * Brand: Apple 
	 * Product Code: Product 18 
	 * Reward Points: 800 
	 * Availability: In Stock 
	 */
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		for(WebElement e : metaDataList) {
			String metaData = e.getText();//Brand: Apple
			String metaKey = metaData.split(":")[0].trim();
			String metaVal = metaData.split(":")[1].trim();
			productMap.put(metaKey, metaVal);
		}
		
	}
	
	/*
	 * $2,000.00 
	 * Ex Tax: $2,000.00
	 */
	private void getProductPriceData() {
		List<WebElement> priceDataList = eleUtil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String productPrice = priceDataList.get(0).getText();
		String productExTexPrice = priceDataList.get(1).getText().split(":")[1].trim();//Ex Tax: $2,000.00
		
		productMap.put("price", productPrice);
		productMap.put("extexprice", productExTexPrice);
		
	}
	
	public Map<String , String> getProductDetails() {
		productMap.put("ProductName", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		return productMap;
	}
	

}
