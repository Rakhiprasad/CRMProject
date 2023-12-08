package utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.Constants;

public class ExcelRead {
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static FileInputStream f;


public static Object[][] getDataFromExcel(String filePath, String sheetName) throws InvalidFormatException, IOException
			 {//here getting excel data to an 2D(3x2) array using getDataFromExcel(path,value)
		// init
		Object[][] data;//array created
		FileInputStream inputStream = new FileInputStream(new File(filePath));//open file
		Workbook wb = WorkbookFactory.create(inputStream);//open workbook
		Sheet s = wb.getSheet(sheetName);//get sheet

		// sheet range
		int rowCount = s.getLastRowNum();
		int colCount = s.getRow(0).getLastCellNum();

		// set data

		data = new Object[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j <= colCount - 1; j++) {
				if (!getCellValue(s, 0, j).equals("")) {//Check whether the value in excel is not null and value will store if the data is not null
					data[i - 1][j] = getCellValue(s, i, j);//value store to array from excel
				}
			}
		}
	
		return data;
	}

// Get cell value at given row and column
	
	private static String getCellValue(Sheet sheet, int row, int col) {
		String value = "";
		if (sheet.getRow(row).getCell(col) == null) {
			value = "";
		} else if (sheet.getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_STRING) {//check value is string
			value = sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			sheet.getRow(row).getCell(col).setCellType(Cell.CELL_TYPE_STRING);//if cell type is not string then set string type as string
			value = sheet.getRow(row).getCell(col).getStringCellValue();
		}
		return value;
	}

	public static String getString(int i, int j, String sheet) {
		String file_path = Constants.testData;
		try {
		f = new FileInputStream(file_path);//open file
		} catch (FileNotFoundException e) {

		e.printStackTrace();//printStackTrace is inbuit method in exception class FileNotFoundException
		}
		try {
		wb = new XSSFWorkbook(f);//open workbook
		} catch (IOException e) {

		e.printStackTrace();//printStackTrace is inbuit method in exception class FileNotFoundException
		}
		sh = wb.getSheet(sheet);// get sheet
		Row r = sh.getRow(i);//get i th row in sh sheet
		Cell c = r.getCell(j);//get j th cell in ith row
		return c.getStringCellValue();//return the value in the cell after getting the cell value
		}
	
	
	public static String getNumeric(int i, int j, String sheet) {
		String file_path = Constants.testData;
		try {
		f = new FileInputStream(file_path);
		} catch (FileNotFoundException e) {

		e.printStackTrace();
		}
		try {
		wb = new XSSFWorkbook(f);
		} catch (IOException e) {

		e.printStackTrace();
		}
		sh = wb.getSheet(sheet);
		Row r = sh.getRow(i);
		Cell c = r.getCell(j);
		int value = (int) c.getNumericCellValue();//get the numeric value
		return String.valueOf(value);//convert the numeric value to string and return
		}

}
