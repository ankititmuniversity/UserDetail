package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	String filePath="D:\\MyWorkstation\\RESTAPIFrameworkDesign\\TestData\\UserDetail.xlsx";
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int rows;
	int cols;
	public void readOperation() throws IOException {
		fis=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheetAt(0);

		rows=sheet.getLastRowNum()+1;
		cols=sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
		System.out.println(rows+" : "+cols);
		for(int i=1;i<=rows;i++) {
			for(int j=0;j<cols;j++) {
				
				try {
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					cell = sheet.getRow(i).getCell(j);
					String j_username = formatter.formatCellValue(cell); 
					System.out.print(j_username+"|"+"\t");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+"\t");
			}
			System.out.println();
		}
		workbook.close();
	}
	public void writeOperation() throws IOException {
		rows=sheet.getLastRowNum()+1;
		cols=sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
		for(int i=0;i<rows;i++) {
			Row row=sheet.getRow(i);
			Cell cell=row.createCell(cols);

			if(i==0) {
				cell.setCellValue("Status");

			}else {
				cell.setCellValue("PASS");	
			}
		}
		workbook.close();
	}


	public static void main(String[] args) throws IOException {
		ExcelOperation eo=new ExcelOperation();
		eo.readOperation();
		//eo.writeOperation();
	}

}
