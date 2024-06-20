package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".//testData/LoginData.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int rowCount = xlutil.getRowCount("Sheet1");
		int columnCount = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[rowCount][columnCount];
		
		for (int r =1; r<=rowCount;r++)
		{
			for(int c = 0;c<columnCount;c++) {
				loginData [r-1][c]=xlutil.getCellData("Sheet1", r, c);
			}
		}
		return loginData;

		

//		String data [][]= 
//			{
//				{"abc","test123","valid"},
//				{"xyz","test012", "invalid"},
//				{"Admin","admin123", "valid"},
//				{"johncanedy","test","invalid"}
//			};
//		return data;
//	
//	
	}
	
	
}
