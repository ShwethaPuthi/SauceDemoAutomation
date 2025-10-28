package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	static DataFormatter formatter=new DataFormatter();
	// Reusable method to fetch data from Excel
	 private static Object[][] readExcelData() throws IOException {
		FileInputStream fis=new FileInputStream(AppStrings.EXCEL_PATH);
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int rowCount=sheet.getPhysicalNumberOfRows();//4
		int colCount = sheet.getRow(0).getLastCellNum();//3

		Object Data[][]=new Object[rowCount-1][colCount];
		for(int i=0;i<rowCount-1;i++) {
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<colCount;j++) {
				XSSFCell cell=row.getCell(j);
				Data[i][j]=formatter.formatCellValue(cell);
				
			}
		}
		workbook.close();
		fis.close();
		return Data;
	}
	// DataProvider that calls the reusable method
    @DataProvider(name = "validData")
    public static Object[][] getValidData() throws IOException {
        Object[][] allData=readExcelData();
        List<Object[]> validList=new ArrayList<>();
        for(Object[] row:allData) {
        	if(row[2].toString().equalsIgnoreCase("TRUE")) {
        		validList.add(row);
        	}
        }
        return validList.toArray(new Object[0][]);
    }
    
    @DataProvider(name="invalidData")
    public static Object[][] getInvalidData() throws IOException{
    	Object[][] allData=readExcelData();
    	List<Object[]> invalidList=new ArrayList<>();
    	for(Object[] row:allData) {
    		String username = row[0].toString();
            String password = row[1].toString();
            String result = row[2].toString();

            if (result.equalsIgnoreCase("FALSE") && !username.isEmpty()) {
                invalidList.add(row);
            }
        }
        return invalidList.toArray(new Object[0][]);
    	}
    @DataProvider(name = "emptyData")
    public static Object[][] getEmptyData() throws IOException {
        Object[][] allData = readExcelData();
        List<Object[]> emptyList = new ArrayList<>();

        for (Object[] row : allData) {
            String username = row[0].toString();
            if (username.isEmpty()) {
                emptyList.add(row);
            }
        }
        return emptyList.toArray(new Object[0][]);
    }
}

