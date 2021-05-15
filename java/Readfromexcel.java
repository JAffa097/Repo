import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readfromexcel {
	String ExcelFath="C:\\Users\\krishna reddy\\eclipse-workspace\\Reportgeneration\\src\\test\\resources\\TestData.xlsx";
	DataFormatter formatter = new DataFormatter();
	String testcaseid="Test31";
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	int RowNumberForTestCaseID;
	int columnNumber;
	Map<String, String> data = new HashMap<String, String>();
	
public static void main(String args[]) throws IOException {
	Readfromexcel  rx=new Readfromexcel();
	File file = new File(rx.ExcelFath);
	FileInputStream fi =new FileInputStream(file);
	rx.workbook = new XSSFWorkbook(fi);
	rx.sheet =rx.workbook.getSheet("Sheet1");
	int TotalRows= rx.sheet.getLastRowNum();
	
	
	int reqRowNumber=rx.getRowNumberForTestCaseID(TotalRows);
	if(rx.RowNumberForTestCaseID==0)
		throw new IOException("TestCase not found in Data Sheet");
	for(int i=0;i<rx.sheet.getRow(0).getLastCellNum();i++) {
		rx.data.put(rx.formatter.formatCellValue(rx.sheet.getRow(0).getCell(i)), rx.formatter.formatCellValue(rx.sheet.getRow(reqRowNumber).getCell(i)));
	}
	System.out.println(rx.data);
	fi.close();
}
public int getRowNumberForTestCaseID(int TotalRows) {
	int columnNumber = getColumnNumber("TestCaseID");
	
	
	for(int i=0;i<=TotalRows;i++) {
		if(formatter.formatCellValue(sheet.getRow(i).getCell(columnNumber)).equalsIgnoreCase(testcaseid)) {
			
			RowNumberForTestCaseID = i;
			break;
		}
	}
	
	return RowNumberForTestCaseID;
	
}
public void getCellData(int rowNumber) {
	
}
public Integer getColumnNumber(String columnName) {
	
	
	//formatter.formatCellValue
	for(int i=0;i<sheet.getRow(0).getLastCellNum();i++) {
		System.out.println(formatter.formatCellValue(sheet.getRow(1).getCell(i)));
		if(formatter.formatCellValue(sheet.getRow(0).getCell(i)).equals(columnName)) {
			columnNumber=i;
			break;
		}

	}
	return columnNumber;
	
	
	
}
}
