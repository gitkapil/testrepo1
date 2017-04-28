package com.duckcall;

import java.io.File;
//Needed to make Selenium work with Excel
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/*
* Declare some common parameters for scripts
* You can change them to adapt your environment
*
*/

public class Util {
	
 // You can change the information of your data file here
 public static final String FILE_PATH = "//Users//Daksh-PC//Documents//workspace//GmailLogin//src//com//TestData//CI Smoke And Nightly Execution Summary_ReleaseBase.xlsm"; // File Path
 public static final String SHEET_NAME = "Brances"; // Sheet name
 //public static final String TABLE_NAME = "testData"; // Name of data table

// public static final int WAIT_TIME = 30; // Delay time to wait the website
					    // launch completely
// public static final String BASE_URL = "https://www.gmail.com/";

// Expected output
//public static final String EXPECT_URL = "https://mail.google.com/mail/";
//public static final String EXPECT_ERROR_URL = "https://accounts.google.com/ServiceLoginAuth";

// You can change the Path of FireFox base on your environment here  
//public static final String FIREFOX_PATH = "//Applications//Firefox.app//";
 
 
 /**
	 * This method reads the data from the Sheet name "Data" of file
	 * "testData.xls"
	 * 
	 * 
	 * @param xlFilePath
	 *            : Path of excel file
	 * @param sheetName
	 *            : Sheet name which contains test data
	 * @return a 2 dimensions array to store all the test data read from excel
	 * @throws Exception
	 */
 public static String[][] getDataFromExcel(String xlFilePath,
			String sheetName) throws Exception {
		// Declare a 2 dimensions array to store all the test data read from
		// excel
		String[][] tabArray = null;

		// get the workbook file. Provide the path of excel file
		Workbook workbook =Workbook.getWorkbook(new File(xlFilePath));
		// get the sheet name
		Sheet sheet = workbook.getSheet(sheetName);

		int startRow, startCol, endRow, endCol, ci, cj;

		// find cell position which contain first appear table name
		Cell tableStart = sheet.findCell(FILE_PATH);
		// Row position of FIRST appear table name
		startRow = tableStart.getRow();
		// Col position of FIRST appear table name
		startCol = tableStart.getColumn();

		// find cell position which contain last appear table name
		Cell tableEnd = sheet.findCell(FILE_PATH, startCol + 1, startRow + 1,
				100, 64000, false);
		// Row position of LAST appear table name
		endRow = tableEnd.getRow();
		// Col position of LAST appear table name
		endCol = tableEnd.getColumn();

		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		// Store all data in an array
		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				//Get content of each cell and store to each array element.
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}

		return (tabArray);
	}


}
