package projects.trakzee.web.projectUtility;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.asserts.SoftAssert;

public class ExcelComparator {

	private static SoftAssert softAssert = new SoftAssert();

	public static void main(String[] args) throws IOException {

		
		// Two compare excel sheet
				String REPORT_FILE_PATH = System.getProperty("user.dir")
						+ "/src/test/resources/trakzee/reportCompare/activity/travel/Comparison_Report.txt";
		
				String filePath1 = System.getProperty("user.dir")
						+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_New.xlsx";
				String filePath2 = System.getProperty("user.dir")
						+ "/src/test/resources/trakzee/reportCompare/activity/travel/Travel_Summary_Old.xlsx";
				compareTwoExcelSheet(filePath1, filePath2, REPORT_FILE_PATH);
	}



	public static void compareTwoExcelSheet(String fileOneCompleteAddress, String fileTwoCompleteAddress,
			String fileCompleteAddressTwoWriteDifferences) throws IOException {
		boolean result = false;
		try (FileInputStream fis1 = new FileInputStream(fileOneCompleteAddress);
				FileInputStream fis2 = new FileInputStream(fileTwoCompleteAddress);
				Workbook workbook1 = new XSSFWorkbook(fis1);
				Workbook workbook2 = new XSSFWorkbook(fis2);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileCompleteAddressTwoWriteDifferences))) {

			Sheet sheet1 = workbook1.getSheet("Travel Summary");
			Sheet sheet2 = workbook2.getSheet("Travel Summary");

			if (sheet1 == null || sheet2 == null) {
				writer.write("Error: 'Travel Summary' sheet not found in one of the files.\n");
				System.out.println("Error: 'Travel Summary' sheet not found in one of the files.");
				return;
			}

			result = compareSheets(sheet1, sheet2, writer);
			writer.write(result ? "✅ Both Excel sheets match successfully.\n" : "❌ Both Excel sheets do NOT match.\n");

			System.out.println("Comparison completed. Report saved to: " + fileCompleteAddressTwoWriteDifferences);

		} catch (Exception e) {
			System.out.println("Failed to load file or workbook: " + e.getMessage());
			softAssert.assertTrue(false, "Exception occurred while comparing the two excel sheet: " + e.getMessage());
		}
		softAssert.assertTrue(result, "Both Excel sheets not matched");
	}

	private static boolean compareSheets(Sheet sheet1, Sheet sheet2, BufferedWriter writer) throws IOException {
		boolean mismatchFound = false;

		int lastRow1 = sheet1.getLastRowNum();
		int lastRow2 = sheet2.getLastRowNum();
		int maxRows = Math.max(lastRow1, lastRow2) + 1;

		for (int i = 0; i < maxRows; i++) {
			Row row1 = sheet1.getRow(i);
			Row row2 = sheet2.getRow(i);

			boolean isRow1Empty = isRowEmpty(row1);
			boolean isRow2Empty = isRowEmpty(row2);

			// If both rows are empty, skip comparison
			if (isRow1Empty && isRow2Empty) {
				continue;
			}

			// If one row is empty but the other isn't, report mismatch
			if (isRow1Empty || isRow2Empty) {
				String msg = "⚠ Row " + (i + 1) + " is empty in one sheet but not in the other.\n";
				System.out.print(msg);
				writer.write(msg);
				mismatchFound = true;
				continue;
			}

			if (compareRows(row1, row2, i, writer)) {
				mismatchFound = true;
			}
		}

		return !mismatchFound;
	}

	private static boolean compareRows(Row row1, Row row2, int rowIndex, BufferedWriter writer) throws IOException {
		boolean rowMismatch = false;

		int cells1 = row1.getLastCellNum();
		int cells2 = row2.getLastCellNum();
		int maxCells = Math.max(cells1, cells2);

		for (int j = 0; j < maxCells; j++) {
			Cell cell1 = row1.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			Cell cell2 = row2.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			String value1 = getCellValue(cell1);
			String value2 = getCellValue(cell2);

			if (!value1.equals(value2)) {
				String msg = "❌ Mismatch at Row " + (rowIndex + 1) + ", Column " + (j + 1) + ": '" + value1 + "' vs '"
						+ value2 + "'\n";
				System.out.print(msg);
				writer.write(msg);
				rowMismatch = true;
			}
		}

		return rowMismatch;
	}

	private static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}

	private static boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (cell != null && !getCellValue(cell).isEmpty()) {
				return false;
			}
		}
		return true;
	}


}
