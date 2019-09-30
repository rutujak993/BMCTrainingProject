package utilityScripts;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil 
{
	
	private static XSSFSheet ExcelSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static String Path;
	
	public void setExcelFile(String filePath, String SheetName) throws Exception
	{
		Path = "C:\\BMCSelenium\\testdata\\GMOTestData.xlsx";
				
		try {
			
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook (ExcelFile);
			ExcelSheet = ExcelWBook.getSheet(SheetName);
		}
		catch(Exception e)
		{
			throw(e);
		}
	}

	
	public int getRowCount()
	{
		return ExcelSheet.getPhysicalNumberOfRows();
	}
	
	public int getColumnCount()
	{
		return ExcelSheet.getRow(0).getPhysicalNumberOfCells();
	}
	
	public String[] getRowData(int row)
	{
		int noOfCells = ExcelSheet.getRow(row).getPhysicalNumberOfCells();
		String[] rowData = new String[noOfCells];
		for(int i=0;i<=noOfCells-1;i++)
		{
			Cell = ExcelSheet.getRow(row).getCell(i);
			if(Cell.getCellType()==CellType.STRING)
			{
				rowData[i]=Cell.getStringCellValue();
			}
			if(Cell.getCellType()==CellType.NUMERIC)
			{
				rowData[i]=String.valueOf((int)Cell.getNumericCellValue());
			}
			
		}
		return rowData;
			
	}
}
