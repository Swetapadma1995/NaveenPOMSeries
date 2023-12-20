package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	//maintain the excel sheet path
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	//when import use poi.ss model
	private static Sheet sheet;
	
	//data driven approach with excel file are flexible
	//excel sheet are easy to handle, user-friendly
	//it should be microsoft excel file
	//excelsheet should be the part of your framework because as you have transfer these whole source code to git repository you have to supply data as well
	//we have to create testdata folder under resources
	//copy and paste the excel file in testdata folder
	//we can not read from selenium or testng or we donot have any direct support from java
	//in that case we have to go by third party API, which is Apache poi API
	//very stable and old library, we have to add that dependency in POM.xml file
	
	
	public static Object[][] getTestData(String sheetName) {
		
		System.out.println("readingtest data from sheet: " +sheetName);
		
		Object data[][] = null;
		
		
		//FileInputStream class which is help us to connect with excel sheet.
		//that says give me file path of excel shhet
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			//now from this book we have to go to specific sheet
			//we have to use sheet method
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i = 0; i<sheet.getLastRowNum(); i++) {
				for(int j = 0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		return data;
	}

}
