package projects.trakzee.web.projectUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// IT IS USED TO READ THE DATA FROM THE EXCEL FILE WHILE DATA DRIVEN TESTING
public class ReadDataFromExcelFile {

	// EXCEL FILE ADDRESS
	public String filePath;// = "./"+"//ExcelData//Excel_Data_For_Test.xlsx";

	// TO CONSTRUCTOR IT TAKES ARGUMENTS AS FILE PATH(BY THIS WE CAN ACCESS ALL THE
	// METHODS AND VARIBLE IN THE DATA PROVIDERS METHODS)
	public ReadDataFromExcelFile(String filePath) {
		this.filePath = filePath;
	}

	// VARIALES DECLARATIONS
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	// TO GET THE ROW COUNT IN THE EXCEL FILE (TILL ROW DATA EXIST)
	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		int lastUsedRow = -1;
		for (int i = sheet.getLastRowNum(); i >= 0; i--) {
			if (sheet.getRow(i) != null && sheet.getRow(i).getCell(0) != null) {
				// Check if the row is not null and has at least one cell
				lastUsedRow = i;
				break;
			}
		}

		workbook.close();
		fis.close();
		return lastUsedRow; // Returning 1-based row count
	}

	// TO GET THE CELL COUNT/COLUMN COUNT IN THE EXCEL SHEET(COLUMN TILL THE DATA
	// EXIST)
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);

		int cellCount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}

	// TO SET THE CELL DATA FROM THE EXCEL FILE
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File src = new File(filePath);

		if (!src.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
		}

		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		if (workbook.getSheetIndex(sheetName) == -1)// if sheet not exists then create new sheet
		{
			workbook.createSheet(sheetName);
		}

		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rownum) == null) // if row not exists then create new row
		{
			sheet.createRow(rownum);
		}

		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);

		cell.setCellValue(data);

		fos = new FileOutputStream(filePath);

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

	// TO GET THE CELL DATA FORM THE EXCEL FILE
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;

		try {
			data = formatter.formatCellValue(cell);
			// return the formatted value of the cell as string regardless of its types
		} catch (Exception e) {
			data = "";
			System.out.println(e.getCause());
		}
		workbook.close();
		fis.close();
		return data;

	}

	// TO FILL GREEN COLOR IN THE EXCEL FILE(CELL)
	public void fillGreenColorIfAlreadySheetOpen(String sheetName, int rownum, int column) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(column);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());

		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

	}

	// TO FILL RED COLOR IN THE EXCEL FILE(CELL)
	public void fillRedColorIfAlreadySheetOpen(String xlsheet, int rownum, int column) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		sheet = workbook.getSheet(xlsheet);

		row = sheet.getRow(rownum);
		cell = row.getCell(column);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

	}

	public void fillColor(String sheetName, int rownum, int column, String redGreenColorName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		fis.close(); // Close input stream after loading

		Sheet sheet = workbook.getSheet(sheetName);

		// Ensure row exists
		Row row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}

		// Ensure cell exists
		Cell cell = row.getCell(column);
		if (cell == null) {
			cell = row.createCell(column);
		}

		// Create a style and apply green color
		CellStyle style = workbook.createCellStyle();
		if (redGreenColorName.equalsIgnoreCase("red")) {
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		} else if (redGreenColorName.equalsIgnoreCase("green")) {
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		}
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		// Write changes to the Excel file
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);

		// Close resources
		fos.close();
		workbook.close();
	}



	public void setCellDataInSingleColumn(String sheetName, Map<Integer, List<String>> columnData)
			throws IOException {

		File src = new File(filePath);

		if (!src.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close(); // Close immediately after creation
		}

		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		fis.close(); // Close the input stream after loading the workbook

		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}

		sheet = workbook.getSheet(sheetName);

		for (Map.Entry<Integer, List<String>> entry : columnData.entrySet()) {
			int colIndex = entry.getKey();
			List<String> values = entry.getValue();

			for (int i = 0; i < values.size(); i++) {
				int rowIndex = i + 1; // Start from Row Index 1 (Skip Header Row)

				// Get or create row
				if (sheet.getRow(rowIndex) == null) {
					sheet.createRow(rowIndex);
				}
				row = sheet.getRow(rowIndex);

				// Create cell and set value
				cell = row.createCell(colIndex);
				cell.setCellValue(values.get(i).toString());
			}
		}

		fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

	public void setCellDataInMultipleColumns(String sheetName, List<Integer> rowCounts,
			Map<Integer, List<String>> columnData) throws IOException {
		File src = new File(filePath);

		// If file does not exist, create a new workbook
		if (!src.exists()) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				workbook.write(fos);
			}
			workbook.close();
		}

		// Open the workbook
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		fis.close(); // Close input stream after loading workbook

		// Create sheet if it does not exist
		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// Iterate through row indexes and set data in respective columns
		for (int i = 0; i < rowCounts.size(); i++) {
			int rowIndex = rowCounts.get(i); // Get row index

			// Get or create row
			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			// Iterate through columnData and add corresponding value
			for (Map.Entry<Integer, List<String>> entry : columnData.entrySet()) {
				int colIndex = entry.getKey(); // Get column index
				List<String> values = entry.getValue(); // Get list of values

				if (i < values.size()) { // Ensure there's data for this row
					XSSFCell cell = row.createCell(colIndex);
					cell.setCellValue(values.get(i));
				}
			}
		}

		// Write to the file
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}


	public void writeMultipleEntriesInSingleColumn(String sheetName, int columnIndex,
			List<Object> values) {
		//TODO: writeMultipleEntriesInSingleColumn >> This is used to set the multiple set data into the single column
		File file = new File(filePath);
		Workbook workbook = null;

		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
				fis.close();
			} else {
				workbook = new XSSFWorkbook();
			}

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}

			for (int rowIndex = 0; rowIndex < values.size(); rowIndex++) {
				Row row = sheet.getRow(rowIndex + 1);
				if (row == null) {
					row = sheet.createRow(rowIndex + 1);
				}

				Cell cell = row.getCell(columnIndex);
				if (cell == null) {
					cell = row.createCell(columnIndex);
				}

				Object value = values.get(rowIndex);

				if (value instanceof String) {
					System.out.println("value: " + value);
					cell.setCellValue((String) value);

				} else if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				} else if (value instanceof Boolean) {
					cell.setCellValue((Boolean) value);
				} else {
					cell.setCellValue(value.toString());
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();

			System.out.println("Excel file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public <T> void writeMultipleEntriesInSingleColumn(String sheetName, T columnIdentifier,
			List<Object> values) {
		//TODO: writeMultipleEntriesInSingleColumn >> This is used to set the multiple set data into the single column
		File file = new File(filePath);
		Workbook workbook = null;

		int columnIndex = -1;


		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
				fis.close();
			} else {
				workbook = new XSSFWorkbook();
			}

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}

			if (columnIdentifier instanceof Integer) {
				columnIndex = (Integer) columnIdentifier;
			} else if (columnIdentifier instanceof String) {
				columnIndex = getColumnIndexByName(sheet, (String) columnIdentifier);
				if (columnIndex == -1) {
					System.out.println("❌ Error: Column '" + columnIdentifier + "' not found.");

					//close all and return
					FileOutputStream fos = new FileOutputStream(file);
					workbook.write(fos);
					fos.close();
					workbook.close();
					return;
				}
			} else {
				//close all and throw exception
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				fos.close();
				workbook.close();
				throw new IllegalArgumentException(
						"Column identifier must be an Integer (index) or String (column name).");
			}

			for (int rowIndex = 0; rowIndex < values.size(); rowIndex++) {
				Row row = sheet.getRow(rowIndex + 1);
				if (row == null) {
					row = sheet.createRow(rowIndex + 1);
				}

				Cell cell = row.getCell(columnIndex);
				if (cell == null) {
					cell = row.createCell(columnIndex);
				}

				Object value = values.get(rowIndex);

				if (value instanceof String) {
					System.out.println("value: " + value);
					cell.setCellValue((String) value);

				} else if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				} else if (value instanceof Boolean) {
					cell.setCellValue((Boolean) value);
				} else {
					cell.setCellValue(value.toString());
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();

			System.out.println("Excel file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getColumnIndexByName(Sheet sheet, String columnName) {
		Row headerRow = sheet.getRow(0); // Assuming first row contains headers
		if (headerRow != null) {
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
					return cell.getColumnIndex();
				}
			}
		}
		return -1; // Column not found
	}

	public void setCellDataInMultipleRowAndSingleColumn(String sheetName, Map<Integer, String> columnData)
			throws IOException {
		//METHOD: setCellDataInMultipleRowAndSingleColumn >> This is used to set the multiple row entry in the single column 
		File src = new File(filePath);

		// If file does not exist, create a new workbook and save it
		if (!src.exists()) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				workbook.write(fos);
			}
			workbook.close(); // Close after creation
		}

		// Open the workbook
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		fis.close(); // Close the input stream after loading the workbook

		// Create sheet if it does not exist
		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// Iterate through columnData
		int rowIndex = 1;

		for (Map.Entry<Integer, String> entry : columnData.entrySet()) {
			int colIndex = entry.getKey(); // Get column index
			String value = entry.getValue(); // Get the value

			// Get or create row
			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			// Create cell and set value
			XSSFCell cell = row.createCell(colIndex);
			cell.setCellValue(value);
			rowIndex++;

		}
		// Write to the file
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

	public static List<Map<String, Object>> readExcelFileAndCatchDataUsingColumnName(String filePath,
			String sheetName) {
		//METHOD: While calling this in the test method add the arguments : Map<String, Object> filterData and convert data provider
		//convert it into the 2d array object.
		List<Map<String, Object>> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(new File(filePath));
				Workbook workbook = WorkbookFactory.create(fis)) {
			//Sheet sheet = workbook.getSheet(sheetName);
			Sheet sheet = getSheetNameIgnoreCase(workbook, sheetName);
			if (sheet == null) {
				System.out.println("❌ Error: Sheet '" + sheetName + "' not found in the file.");
				return dataList;
			}

			// Get physical row count (excluding empty rows)
			int rowCount = 0;
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null && row.getPhysicalNumberOfCells() > 0) {
					rowCount++;
				}
			}
			System.out.println("✅ Found " + rowCount + " physical rows (excluding empty ones).");

			Iterator<Row> rowIterator = sheet.iterator();

			// Read header row (column names)
			Row headerRow = rowIterator.next();
			List<String> headers = new ArrayList<>();
			int colCount = headerRow.getPhysicalNumberOfCells(); // Get actual number of columns
			System.out.println("✅ Found " + colCount + " physical columns in the header row.");

			for (Cell cell : headerRow) {
				headers.add(cell.getStringCellValue().trim());
			}

			// Read data rows
			int validDataRows = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getPhysicalNumberOfCells() == 0) {
					//System.out.println("⚠️ Skipping an empty row at index: " + row.getRowNum());
					continue; // Skip empty rows
				}

				Map<String, Object> rowData = new HashMap<>();
				for (int i = 0; i < headers.size(); i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData.put(headers.get(i), getCellValue(cell).toString());
				}

				dataList.add(rowData);
				validDataRows++;
			}

			System.out.println("✅ Successfully read " + validDataRows + " data rows from the sheet.");
		} catch (Exception e) {
			System.out.println("❌ Error: Failed to read the Excel sheet. " + e.getMessage());
			e.printStackTrace();
		}


		return dataList;
	}

	public static List<Map<String, Object>> readExcelFileAndCatchDataUsingColumnNameWithSkipDataSet(String filePath,
			String sheetName) {
		List<Map<String, Object>> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(new File(filePath));
				Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = getSheetNameIgnoreCase(workbook, sheetName);
			if (sheet == null) {
				System.out.println("❌ Error: Sheet '" + sheetName + "' not found in the file.");
				return dataList;
			}

			// Read header row (column names)
			Iterator<Row> rowIterator = sheet.iterator();
			if (!rowIterator.hasNext()) {
				System.out.println("⚠️ Error: The sheet is empty!");
				return dataList;
			}

			Row headerRow = rowIterator.next();
			List<String> headers = new ArrayList<>();
			int colCount = headerRow.getPhysicalNumberOfCells();
			System.out.println("✅ Found " + colCount + " columns in the header row.");

			for (Cell cell : headerRow) {
				headers.add(cell.getStringCellValue().trim());
			}
			System.out.println("Sheet column names: " + headers);

			// Check if "wantToSkip" column exists (ignoring case)
			int skipColumnIndex = -1;
			for (int i = 0; i < headers.size(); i++) {
				if (headers.get(i).trim().replaceAll(" ", "").equalsIgnoreCase("wantToSkip")) {
					skipColumnIndex = i;
					break;
				}
			}

			boolean hasSkipColumn = skipColumnIndex != -1;
			if (hasSkipColumn) {
				System.out.println("✅ 'wantToSkip' column found at index: " + skipColumnIndex);
			} else {
				System.out.println("⚠️ 'wantToSkip' column not found. No rows will be skipped.");
			}

			// Read data rows
			List<Integer> totalSkipRowsIndexCounter = new ArrayList<>();
			int rowCounter = 0;
			int validDataRows = 0, skippedRows = 0;
			while (rowIterator.hasNext()) {
				rowCounter++;
				Row row = rowIterator.next();
				if (row.getPhysicalNumberOfCells() == 0) {
					continue; // Skip empty rows
				}

				// Check if this row should be skipped
				if (hasSkipColumn) {
					Cell skipCell = row.getCell(skipColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					boolean skipRow = skipCell.toString().trim().equalsIgnoreCase("true");
					if (skipRow) {
						skippedRows++;
						totalSkipRowsIndexCounter.add(rowCounter);
						continue; // Skip this row
					}
				}

				// Store row data
				Map<String, Object> rowData = new LinkedHashMap<>(); //it is maintain the order

				//Map<String, Object> rowData = new HashMap<>();
				for (int i = 0; i < headers.size(); i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData.put(headers.get(i), getCellValue(cell));
				}

				dataList.add(rowData);
				validDataRows++;
			}

			System.out.println(
					"✅ Successfully read " + validDataRows + " data rows (Skipped: " + skippedRows
							+ ") and skipped row indexes are" + totalSkipRowsIndexCounter + ".");
			System.setProperty("skipRowIndexes", totalSkipRowsIndexCounter.toString());
		} catch (Exception e) {
			System.out.println("❌ Error: Failed to read the Excel sheet. " + e.getMessage());
			e.printStackTrace();
		}

		return dataList;
	}

	public static Sheet getSheetNameIgnoreCase(Workbook workbook, String sheetName) {
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				return workbook.getSheetAt(i);
			}
		}
		return null; // Return null if no matching sheet is found
	}


	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		default:
			return "";
		}
	}

}
