package com.verizon.crm.api.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	//excel file
	public String getDataFromExcelFile(String sheetname, int rownum, int celvalue) throws IOException {
		FileInputStream fis = new FileInputStream("./configAppsData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 String data = wb.getSheet(sheetname).getRow(rownum).getCell(celvalue).getStringCellValue();
		return data;
	}
	//excel file row counting
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./configAppsData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetname).getLastRowNum();
		return rowCount;
	}
	// setBack Data into excel
	public Cell setDataBackToExcel(String sheetname, int rownum, int celnum) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./configAppsData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Cell celData = wb.getSheet(sheetname).getRow(rownum).createCell(celnum);
		
		FileOutputStream fos = new FileOutputStream("./configAppsData/Book2.xlsx");
		wb.write(fos);
		return celData;
	}


}
