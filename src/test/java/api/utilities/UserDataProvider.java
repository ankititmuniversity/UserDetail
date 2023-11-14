package api.utilities;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
	@DataProvider(name="AllDetailOfUser")
	public String[][] userDetails(){
		int ttlrow=ExcelReadFile.getRowCount(ExcelReadFile.filePath, "Sheet1");
		int ttlcol=ExcelReadFile.getCellCount(ExcelReadFile.filePath, "Sheet1");
		String [][]userPayload=new String[ttlrow-1][ttlcol];
		for(int rowNo=1;rowNo<ttlrow;rowNo++) {
			for(int cellNo=0;cellNo<ttlcol;cellNo++) {
				userPayload[rowNo-1][cellNo]=ExcelReadFile.getCellValue(ExcelReadFile.filePath, "Sheet1", rowNo, cellNo).toString();
			}
		}
		return userPayload;
	}

	@DataProvider(name="UserNameDetail")
	public String[] usernameDetail(){
		int row=ExcelReadFile.getRowCount(ExcelReadFile.filePath, "Sheet1");
		int col=ExcelReadFile.getCellCount(ExcelReadFile.filePath, "Sheet1");
		String []username=new String[row-1];
		for(int rowNo=1;rowNo<row;rowNo++) {
			username[rowNo]=ExcelReadFile.getCellValue(ExcelReadFile.filePath, "Sheet1", rowNo, 2);

		}
		return username;
	}
}

