package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public FileInputStream fread;
	public FileOutputStream fwrite;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String filePath;
	
	public ExcelUtilities(String filePath) {
		this.filePath = filePath;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fread = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fread);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fread.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fread = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fread);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fread.close();
		
		return cellCount;
	}
	
	public String getCelldata(String sheetName, int rowNum, int cellNum) throws IOException {
		fread = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fread);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell); //returns formatted value of the cell as a string regardless of cell type
		} catch(Exception e) {
			data = "";
		}
		
		workbook.close();
		fread.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		File xlfile = new File(filePath);
		if(!xlfile.exists()) {
			workbook = new XSSFWorkbook();
			fwrite = new FileOutputStream(filePath);
			workbook.write(fwrite);
		}
		
		fread = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fread);
		
		if(workbook.getSheetIndex(sheetName)==-1) {	//if sheet not exists then create new sheet
			workbook.createSheet(sheetName);
		}
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null) {	//if row not exists then create new row
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		
		fwrite = new FileOutputStream(filePath);
		workbook.write(fwrite);
		
		workbook.close();
		fread.close();
		fwrite.close();
	}
}
