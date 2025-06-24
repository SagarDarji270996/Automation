package projects.trakzee.web.projectUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

// IT IS USED TO PROVIDE DATA TO THE TEST CASES CLASe BY USING CLASS "ReadDataFromExcelFile.java"
public class DataProviders {

	//TO LOG THE MESSAGES ON THE CONSOLE AND LOG FILES BOTH
	public static Logger logger = LogManager.getLogger();

	//CONSTRUCTOR TO ACCESS THE READ DATA FROM THE EXCEL FILES
	public static ReadDataFromExcelFile rdfef;

	//DATE PROVIDER METHODS CONCEPT TO ACCESS DATA FROM THE EXCEL FILES, FOR THIS IT IS ALSO CALLED TO THE ReadDataFromExcelFile CLASS AND NOTE ACTUAL DATA READING DONE AT(ReadDataFromExcelFile)
	public static String[][] dataProviderGetDataFromExcelFile(String excelFileName, String worksheetName)
			throws IOException {
		//TO TRACK THE CALLER METHOD NAME

		System.out
				.println("XLSX workbook name: " + excelFileName.trim() + " and worksheet name " + worksheetName.trim());
		try {
			String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "trakzee" + File.separator + "DataProviderXLS"
					+ File.separator + excelFileName.trim() + ".xlsx";
			//String filePath = "/home/uffizio/Eclipse Project/taskeye_watm/src/test/resources/trakzee/DataProviderXLS/CheckNavigationList.xlsx";
			System.out.println("Excel file path: " + filePath);
			rdfef = new ReadDataFromExcelFile(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int rownum = rdfef.getRowCount(worksheetName.trim()); //edfef = read data from excel file
		int colcount = rdfef.getCellCount(worksheetName.trim(), 1);
		System.out.println("Number of rows is: " + rownum + "  and column is: " + colcount);

		String UserData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				UserData[i - 1][j] = rdfef.getCellData(worksheetName.trim(), i, j);
			}
		}
		return UserData;
	}


	public static void main(String[] args) throws IOException {
		dataProviderGetDataFromExcelFile("CheckNavigationList", "Geofence");
	}

}
