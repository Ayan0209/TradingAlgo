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

class rowdata{
	private double last_price = 0;
	private double last_quantity = 0;
	private Date time = null;
	private double buy_price = 0;
	private double buy_order = 0;
	private double buy_quantity = 0;
	private double sell_price = 0;
	private double sell_order = 0;
	private double sell_quantity = 0;
	
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
    public void set_time(Date st) {
    	
    	time = st;
    	
    }
    		public Date get_time() {
    			
    			return time;
    			
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


public class FullRead {
	
	
	
@SuppressWarnings("null")
public static void main(String[] args) throws IOException {
		
		
		
        String excelFilePath = "daycheck.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        //First Sheet
        Sheet firstSheet = workbook.getSheetAt(0);
        int endRow = firstSheet.getLastRowNum();
        Iterator<Row> iterator = firstSheet.iterator();
        rowdata[] sheetone = new rowdata[endRow + 20];
        for(int k = 0; k<(endRow + 20) ; k++) {
        	
        	sheetone[k] = new rowdata();
        }
        
        int i = 0;
        int j = 0; 
        int k = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        double SEBI = 0.0000005;
		double LotSize = 75;
		double StampO = 0.00003;
		double StampF = 0.00002;
		double Bro = 20;
		double TranO = 0.0005;
		double TranF = 0.000019;
		double GSTO = 0.18;
		double GSTF = 0.18;
		double STTF = 0.0001;
		double STTO = 0.0005;
		double TE = 0;
		double TF = 0;
		
        
        while (iterator.hasNext()) {
        	
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            j=0;
            while (cellIterator.hasNext()) {
            	
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        //System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                    	if(j == 0) {
                    		sheetone[i].set_last_price(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 1) {
                    		sheetone[i].set_last_quantity(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 2) {
                    		sheetone[i].set_time(cell.getDateCellValue());
                    	}
                    	else if(j == 3) {
                    		sheetone[i].set_buy_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 4) {
                    		sheetone[i].set_buy_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 5) {
                    		sheetone[i].set_buy_quantity(cell.getNumericCellValue());
                    	}
                    	else if(j == 6) {
                    		sheetone[i].set_sell_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 7) {
                    		sheetone[i].set_sell_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 8) {
                    		sheetone[i].set_sell_quantity(cell.getNumericCellValue());
                    		
                    	}                   	
                    	if (DateUtil.isCellDateFormatted(cell)) {
                            //System.out.print (nextRow.getCell(2).getDateCellValue());
                        }
                    	else {
                    		//System.out.print(cell.getNumericCellValue());
                    	}
                        break;
                }
                j++;
                //System.out.print(" - ");
                
                
            }
            i++;
            //System.out.println();
        }
        //Second Sheet
        
        Sheet secondSheet = workbook.getSheetAt(1);
        int endRowtwo = secondSheet.getLastRowNum();
        Iterator<Row> iteratortwo = secondSheet.iterator();
        rowdata[] sheettwo = new rowdata[endRowtwo + 20];
        for(k = 0; k<(endRowtwo + 20) ; k++) {
        	
        	sheettwo[k] = new rowdata();
        }
        
        i = 0;
        j = 0;
        
        while (iteratortwo.hasNext()) {
        	
            Row nextRow = iteratortwo.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            j=0;
            while (cellIterator.hasNext()) {
            	
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        //System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        //System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                    	if(j == 0) {
                    		sheettwo[i].set_last_price(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 1) {
                    		sheettwo[i].set_last_quantity(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 2) {
                    		sheettwo[i].set_time(cell.getDateCellValue());
                    	}
                    	else if(j == 3) {
                    		sheettwo[i].set_buy_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 4) {
                    		sheettwo[i].set_buy_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 5) {
                    		sheettwo[i].set_buy_quantity(cell.getNumericCellValue());
                    	}
                    	else if(j == 6) {
                    		sheettwo[i].set_sell_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 7) {
                    		sheettwo[i].set_sell_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 8) {
                    		sheettwo[i].set_sell_quantity(cell.getNumericCellValue());
                    		
                    	}                   	
                    	if (DateUtil.isCellDateFormatted(cell)) {
                            //System.out.print (nextRow.getCell(2).getDateCellValue());
                        }
                    	else {
                    		//System.out.print(cell.getNumericCellValue());
                    	}
                        break;
                }
                j++;
                //System.out.print(" - ");
                
                
            }
            i++;
            //System.out.println();
        }
        
        //Third sheet
        Sheet thirdSheet = workbook.getSheetAt(2);
        int endRowthree = thirdSheet.getLastRowNum();
        Iterator<Row> iteratorthree = thirdSheet.iterator();
        rowdata[] sheetthree = new rowdata[endRowthree + 20];
        for(k = 0; k<(endRowthree + 20) ; k++) {
        	
        	sheetthree[k] = new rowdata();
        }
        
        i = 0;
        j = 0;
        
        while (iteratorthree.hasNext()) {
        	
            Row nextRow = iteratorthree.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            j=0;
            while (cellIterator.hasNext()) {
            	
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        //System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                    	if(j == 0) {
                    		sheetthree[i].set_last_price(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 1) {
                    		sheetthree[i].set_last_quantity(cell.getNumericCellValue());
                    		
                    	}
                    	else if(j == 2) {
                    		sheetthree[i].set_time(cell.getDateCellValue());
                    	}
                    	else if(j == 3) {
                    		sheetthree[i].set_buy_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 4) {
                    		sheetthree[i].set_buy_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 5) {
                    		sheetthree[i].set_buy_quantity(cell.getNumericCellValue());
                    	}
                    	else if(j == 6) {
                    		sheetthree[i].set_sell_price(cell.getNumericCellValue());
                    	}
                    	else if(j == 7) {
                    		sheetthree[i].set_sell_order(cell.getNumericCellValue());
                    	}
                    	else if(j == 8) {
                    		sheetthree[i].set_sell_quantity(cell.getNumericCellValue());
                    		
                    	}                   	
                    	if (DateUtil.isCellDateFormatted(cell)) {
                            //System.out.print (nextRow.getCell(2).getDateCellValue());
                        }
                    	else {
                    		//System.out.print(cell.getNumericCellValue());
                    	}
                        break;
                }
                j++;
                //System.out.print(" - ");
                
                
            }
            i++;
            //System.out.println();
        }
        
        System.out.println(endRow);
        
        for(i = 1; i<(endRow+1); i++) {
        	two = 0;
        	three = 0;
        	Date timecheck = sheetone[i].get_time();
        	//System.out.println(timecheck);
        	one = i;
        	for(j = 1; j<(endRowtwo + 1) ; j++) {
        		//System.out.println(sheettwo[j].get_time());
        		if(timecheck.compareTo(sheettwo[j].get_time()) == 0) {
        			two = j;
        			//System.out.println(j);
        		}
        	}
        	for(k = 1; k<(endRowthree + 1) ; k++) {
        		//System.out.println(sheetthree[k].get_time());
        		if(timecheck.compareTo(sheetthree[k].get_time()) == 0) {
        			three = k;
        			//System.out.println(k);
        		}
        	}	
        	if(two != 0 && three !=0) {
        		double E = (sheetthree[three].get_buy_price() - (10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price()))*75;
        		double F = ((10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) - sheetthree[three].get_sell_price())*75;
        		
        		if(E>F) {
        			if((10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price())< sheetthree[three].get_buy_price() && E>0) {
        				
        				TE = (((sheettwo[two].get_buy_price() * LotSize) * (SEBI + STTO + TranO)) + ((Bro + TranO * (sheettwo[two].get_buy_price() * LotSize)) * GSTO)) +
        						((sheetone[one].get_sell_price() * LotSize) * (SEBI + StampO + TranO) + (Bro + TranO * (sheetone[one].get_sell_price() * LotSize)) * GSTO) +
        						((sheetthree[three].get_buy_price() * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (sheetthree[three].get_buy_price() * LotSize)) * GSTF) +
        						(3 * Bro);
        				System.out.println(one + " " + two + " " + three + " " + E + " " + F + " TE = " + TE);
        				//buy sheetone[one].sell_price and sell sheettwo[two].buy_price
        				//buy sheetthree[three].buy_price
        			}
        			else {
        				System.out.println(one + " " + two + " " + three + " " + E + " " + F);
        			}
        		}
        		else {
        			if((10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) > sheetthree[three].get_sell_price() && F>0) {
        				TF = (sheettwo[two].get_sell_price() * LotSize) * (SEBI + StampO + TranO) + (Bro + TranO * (sheettwo[two].get_sell_price() * LotSize)) * GSTO + 
        						(sheetone[one].get_buy_price() * LotSize) * (SEBI + STTO + TranO) + (Bro + TranO * (sheetone[one].get_buy_price() * LotSize)) * GSTO +
        						(sheetthree[three].get_sell_price() * LotSize) * (SEBI + StampF + TranF) + (Bro + TranF * (sheetthree[three].get_sell_price() * LotSize)) * GSTF +
        						(3 * Bro);
        				System.out.println(one + " " + two + " " + three + " " + E + " " + F + " TF = " + TF);
        				//buy sheettwo[two].sell_price and sell sheetone[one].buy_price
        				//sell sheetthree[three].sell_price
        			}
        			else {
        				System.out.println(one + " " + two + " " + three + " " + E + " " + F);
        			}
        		}
        	}
        	
        	else {
        		System.out.println("No match");
        	}
        	
        }
        
        
        
         
        workbook.close();
        inputStream.close();
    }

}

