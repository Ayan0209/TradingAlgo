import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class CompleteRead {
	
	//quicksort array of strings
		public static void quicksort(String[] a, int start, int end)
		{
			int i = start;
	        int j = end;
	        if (j - i >= 1)
	        {
	            String pivot = a[i];
	            while (j > i)
	            {
	                while (a[i].compareTo(pivot) <= 0 && i < end && j > i){
	                    i++;
	                }
	                while (a[j].compareTo(pivot) >= 0 && j > start && j >= i){
	                    j--;
	                }
	                if (j > i)
	                    swap(a, i, j);
	            }
	            swap(a, start, j);
	            quicksort(a, start, j - 1);
	            quicksort(a, j + 1, end);
	        }
		}
	    
		//swap two elements of an array
	    public static void swap(String[]a , int i , int j)
	    {
	    	String temp = a[i];
	    	a[i] = a[j];
	    	a[j] = temp;
	    }
	    
	    public static void processSheet(Sheet s)
	    {
	    	int endRow = s.getLastRowNum();
	        Iterator<Row> iterator = s.iterator();
	        rowdata[] sheet = new rowdata[endRow + 20];
	        for(int k = 0; k<(endRow + 20) ; k++) {
	        	
	        	sheet[k] = new rowdata();
	        }
	        
	        int i = 0;
	        int j = 0; 
	        int k = 0;
	        int one = 0;
	        int two = 0;
	        int three = 0;
	        
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
	                    		sheet[i].set_last_price(cell.getNumericCellValue());
	                    		
	                    	}
	                    	else if(j == 1) {
	                    		sheet[i].set_last_quantity(cell.getNumericCellValue());
	                    		
	                    	}
	                    	else if(j == 2) {
	                    		sheet[i].set_time(cell.getDateCellValue());
	                    	}
	                    	else if(j == 3) {
	                    		sheet[i].set_buy_price(cell.getNumericCellValue());
	                    	}
	                    	else if(j == 4) {
	                    		sheet[i].set_buy_order(cell.getNumericCellValue());
	                    	}
	                    	else if(j == 5) {
	                    		sheet[i].set_buy_quantity(cell.getNumericCellValue());
	                    	}
	                    	else if(j == 6) {
	                    		sheet[i].set_sell_price(cell.getNumericCellValue());
	                    	}
	                    	else if(j == 7) {
	                    		sheet[i].set_sell_order(cell.getNumericCellValue());
	                    	}
	                    	else if(j == 8) {
	                    		sheet[i].set_sell_quantity(cell.getNumericCellValue());
	                    		
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
	    }
		
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
			
			
			
	        String excelFilePath = "day.xlsx";
	        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	         
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        
			/*
			 * get names of all the sheets in the excel file
			 * sort the sheets according to names x.CE, x.PE, y.CE, y.PE ..... FUT
			 * club together two sheets at a time
			 * process the clubbed sheets together along with future sheet 
			 */
	        
	        //get the names of all the sheets
	        
	        int numberOfSheets = workbook.getNumberOfSheets();
	        Map<String, Sheet> sheets = new HashMap<String, Sheet>();
	        String[] names = new String[numberOfSheets];
	        
	        for(int i=0;i<numberOfSheets;i++)
	        {
	        	String name = workbook.getSheetName(i);
	        	Sheet sheet = workbook.getSheetAt(i);
	        	she`ets.put(name,sheet);
	        	names[i] = name;
	        }
	        
	        //sort the names of the sheets
	        quicksort(names,0,numberOfSheets-1);
	        
	        //list of list of sheets according to sorted names i.e clubbing together sheets
	        Vector<String[]> sheetsClubbed = new Vector<String[]>();
	        String futureSheet=""; 
	        for(int i = 0;i<numberOfSheets;i++)
	        {
	        	if(names[i].substring(names[i].length()-3).compareTo("FUT")==0)
	        	{
	        		futureSheet = names[i];
	        	}
	        		
	        	//System.out.println(names[i].substring(length-2, length));        	
	        	if(names[i].substring(names[i].length()-2).compareTo("CE")==0 && names[i+1].substring(names[i+1].length()-2).compareTo("PE")==0)
	        	{
	        		String[] s = new String[2];
	        		s[0] = names[i];
	        		s[1] = names[i+1];
	        		sheetsClubbed.add(s);
	        		i+=1;
	        	}
	        	
	        }
	        
	        //iterating over the list to process first, second and future sheet
	        int sizeOfClubbed = sheetsClubbed.size();
	        for(int i = 0;i<sizeOfClubbed; i++)
	        {
	        	
	        	System.out.print(sheetsClubbed.get(i)[0] + " ");
	        	processSheet(sheets.get(sheetsClubbed.get(i)[0]));
	        	System.out.print(sheetsClubbed.get(i)[1] + " ");
	        	processSheet(sheets.get(sheetsClubbed.get(i)[1]));
	        	System.out.print(futureSheet + " ");
	            processSheet(sheets.get(futureSheet));
	        }
	        
	        	
//	        //First Sheet
//	        Sheet firstSheet = workbook.getSheetAt(0);
//	        int endRow = firstSheet.getLastRowNum();
//	        Iterator<Row> iterator = firstSheet.iterator();
//	        rowdata[] sheetone = new rowdata[endRow + 20];
//	        for(int k = 0; k<(endRow + 20) ; k++) {
//	        	
//	        	sheetone[k] = new rowdata();
//	        }
//	        
//	        int i = 0;
//	        int j = 0; 
//	        int k = 0;
//	        int one = 0;
//	        int two = 0;
//	        int three = 0;
//	        
//	        while (iterator.hasNext()) {
//	        	
//	            Row nextRow = iterator.next();
//	            Iterator<Cell> cellIterator = nextRow.cellIterator();
//	            j=0;
//	            while (cellIterator.hasNext()) {
//	            	
//	                Cell cell = cellIterator.next();
//	                switch (cell.getCellType()) {
//	                    case STRING:
//	                        //System.out.print(cell.getStringCellValue());
//	                        break;
//	                    case BOOLEAN:
//	                        System.out.print(cell.getBooleanCellValue());
//	                        break;
//	                    case NUMERIC:
//	                    	if(j == 0) {
//	                    		sheetone[i].set_last_price(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 1) {
//	                    		sheetone[i].set_last_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 2) {
//	                    		sheetone[i].set_time(cell.getDateCellValue());
//	                    	}
//	                    	else if(j == 3) {
//	                    		sheetone[i].set_buy_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 4) {
//	                    		sheetone[i].set_buy_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 5) {
//	                    		sheetone[i].set_buy_quantity(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 6) {
//	                    		sheetone[i].set_sell_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 7) {
//	                    		sheetone[i].set_sell_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 8) {
//	                    		sheetone[i].set_sell_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}                   	
//	                    	if (DateUtil.isCellDateFormatted(cell)) {
//	                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//	                        }
//	                    	else {
//	                    		//System.out.print(cell.getNumericCellValue());
//	                    	}
//	                        break;
//	                }
//	                j++;
//	                //System.out.print(" - ");
//	                
//	                
//	            }
//	            i++;
//	            //System.out.println();
//	        }
//	        //Second Sheet
//	        
//	        Sheet secondSheet = workbook.getSheetAt(1);
//	        int endRowtwo = secondSheet.getLastRowNum();
//	        Iterator<Row> iteratortwo = secondSheet.iterator();
//	        rowdata[] sheettwo = new rowdata[endRowtwo + 20];
//	        for(k = 0; k<(endRowtwo + 20) ; k++) {
//	        	
//	        	sheettwo[k] = new rowdata();
//	        }
//	        
//	        i = 0;
//	        j = 0;
//	        
//	        while (iteratortwo.hasNext()) {
//	        	
//	            Row nextRow = iteratortwo.next();
//	            Iterator<Cell> cellIterator = nextRow.cellIterator();
//	            j=0;
//	            while (cellIterator.hasNext()) {
//	            	
//	                Cell cell = cellIterator.next();
//	                switch (cell.getCellType()) {
//	                    case STRING:
//	                        //System.out.print(cell.getStringCellValue());
//	                        break;
//	                    case BOOLEAN:
//	                        //System.out.print(cell.getBooleanCellValue());
//	                        break;
//	                    case NUMERIC:
//	                    	if(j == 0) {
//	                    		sheettwo[i].set_last_price(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 1) {
//	                    		sheettwo[i].set_last_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 2) {
//	                    		sheettwo[i].set_time(cell.getDateCellValue());
//	                    	}
//	                    	else if(j == 3) {
//	                    		sheettwo[i].set_buy_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 4) {
//	                    		sheettwo[i].set_buy_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 5) {
//	                    		sheettwo[i].set_buy_quantity(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 6) {
//	                    		sheettwo[i].set_sell_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 7) {
//	                    		sheettwo[i].set_sell_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 8) {
//	                    		sheettwo[i].set_sell_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}                   	
//	                    	if (DateUtil.isCellDateFormatted(cell)) {
//	                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//	                        }
//	                    	else {
//	                    		//System.out.print(cell.getNumericCellValue());
//	                    	}
//	                        break;
//	                }
//	                j++;
//	                //System.out.print(" - ");
//	                
//	                
//	            }
//	            i++;
//	            //System.out.println();
//	        }
//	        
//	        //Third sheet
//	        Sheet thirdSheet = workbook.getSheetAt(2);
//	        int endRowthree = thirdSheet.getLastRowNum();
//	        Iterator<Row> iteratorthree = thirdSheet.iterator();
//	        rowdata[] sheetthree = new rowdata[endRowthree + 20];
//	        for(k = 0; k<(endRowthree + 20) ; k++) {
//	        	
//	        	sheetthree[k] = new rowdata();
//	        }
//	        
//	        i = 0;
//	        j = 0;
//	        
//	        while (iteratorthree.hasNext()) {
//	        	
//	            Row nextRow = iteratorthree.next();
//	            Iterator<Cell> cellIterator = nextRow.cellIterator();
//	            j=0;
//	            while (cellIterator.hasNext()) {
//	            	
//	                Cell cell = cellIterator.next();
//	                switch (cell.getCellType()) {
//	                    case STRING:
//	                        //System.out.print(cell.getStringCellValue());
//	                        break;
//	                    case BOOLEAN:
//	                        System.out.print(cell.getBooleanCellValue());
//	                        break;
//	                    case NUMERIC:
//	                    	if(j == 0) {
//	                    		sheetthree[i].set_last_price(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 1) {
//	                    		sheetthree[i].set_last_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}
//	                    	else if(j == 2) {
//	                    		sheetthree[i].set_time(cell.getDateCellValue());
//	                    	}
//	                    	else if(j == 3) {
//	                    		sheetthree[i].set_buy_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 4) {
//	                    		sheetthree[i].set_buy_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 5) {
//	                    		sheetthree[i].set_buy_quantity(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 6) {
//	                    		sheetthree[i].set_sell_price(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 7) {
//	                    		sheetthree[i].set_sell_order(cell.getNumericCellValue());
//	                    	}
//	                    	else if(j == 8) {
//	                    		sheetthree[i].set_sell_quantity(cell.getNumericCellValue());
//	                    		
//	                    	}                   	
//	                    	if (DateUtil.isCellDateFormatted(cell)) {
//	                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//	                        }
//	                    	else {
//	                    		//System.out.print(cell.getNumericCellValue());
//	                    	}
//	                        break;
//	                }
//	                j++;
//	                //System.out.print(" - ");
//	                
//	                
//	            }
//	            i++;
//	            //System.out.println();
//	        }
//	        
//	        System.out.println(endRow);
	        
	        
	        
	         
	        workbook.close();
	        inputStream.close();
	    }

}
