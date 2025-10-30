package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data Provider
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\openCart_LoginData.xlsx"; //taking xl from testData folder
		ExcelUtilities xlUtil = new ExcelUtilities(path);
		
		int totalRows = xlUtil.getRowCount("Sheet1");
		int totalCells = xlUtil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String [totalRows][totalCells];	//created for two-dimentional array
		
		for(int row=1; row<=totalRows; row++) {
			for(int cell=0; cell<totalCells; cell++) {
				logindata[row-1][cell] = xlUtil.getCelldata("Sheet1", row, cell);
			}
		}
		
		return logindata;	//returning two-dimentional array
	}
}
