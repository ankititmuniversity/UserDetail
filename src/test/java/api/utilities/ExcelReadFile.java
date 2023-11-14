package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadFile {
	static String filePath="D:\\MyWorkstation\\RESTAPIFrameworkDesign\\TestData\\UserDetail.xlsx";
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static String getCellValue(String filePath,String sheetName, int rowNo, int cellNo) {
		try {
			fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			//	sheet=workbook.getSheet(sheetName);

			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			cell=workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);
			String j_username = formatter.formatCellValue(cell); 
			System.out.print(j_username+"|"+"\t");
			workbook.close();
			return cell.getRawValue();
		} catch (Exception e) {
			return "";
		}
	}
	public static int getRowCount(String filePath,String sheetName) {
		try {
			fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			int ttlrows=sheet.getLastRowNum()+1;
			workbook.close();
			return ttlrows;
		} catch (Exception e) {
			return 0;
		}
	}
	public static int getCellCount(String filePath,String sheetName) {
		try {
			fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			int ttlcols=sheet.getRow(0).getLastCellNum();
			workbook.close();
			return ttlcols;
		} catch (Exception e) {
			return 0;
		}
	}

}
