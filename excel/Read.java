import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Read {
	class rowdata{
		private double last_price;
		private double last_quantity;
		private Date time;
		private double buy_price;
		private double buy_order;
		private double buy_quantity;
		private double sell_price;
		private double sell_order;
		private double sell_quantity;
		
		public void set_last_price(double lp) {
			
			last_price = lp;
			
		}
                public double get_last_price() {
			
			return last_price;
			
		}
		public void set_last_quantity(double lq) {
			
			last_quantity = lq;
			
		}
                public double get_last_quantity() {
			
			return last_quantity ;
			
		}
		public void set_buy_price(double bp) {
			
			buy_price = bp;
			
		}
                public double get_buy_price() {
			
			return buy_price ;
			
		}
		public void set_buy_order(double bo) {
			
			buy_order = bo;
			
		}
                public double get_buy_order() {
			
			return buy_order;
			
		}
		public void set_buy_quantity(double bq) {
			
			buy_quantity = bq;
			
		}
                public double get_buy_quantity() {
			
			return buy_quantity ;
			
		}
		public void set_sell_price(double sp) {
			
			sell_price = sp;
			
		}
                public double get_sell_price() {
			
			return sell_price ;
			
		}
		public void set_sell_order(double so) {
			
			sell_order = so;
			
		}
                public double get_sell_order() {
			
			return sell_order;
			
		}
		public void set_sell_quantity(double sq) {
			
			sell_quantity = sq;
			
		}
                public double get_sell_quantity() {
			
			return sell_quantity ;
			
		}
		
			
	}
	
	public static void main(String[] args) throws IOException {
		
		
        String excelFilePath = "day.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(14);
        Sheet secondSheet = workbook.getSheetAt(19);
        int endRow = firstSheet.getLastRowNum();
        Iterator<Row> iterator = firstSheet.iterator();
        rowdata sheetone[] = new rowdata[20];
        int i = 1; 
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
           
        	
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                    	
                    	if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.print (nextRow.getCell(2).getDateCellValue());
                        }
                    	else {
                    		System.out.print(cell.getNumericCellValue());
                    	}
                        break;
                }
                System.out.print(" - ");
                
            }
            i++;
            System.out.println();
        }
         
        workbook.close();
        inputStream.close();
    }
}
