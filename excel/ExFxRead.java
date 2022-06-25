
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



class timestampValues{
	
	private int strike_price = 0;
	private double buy_price_CE = 0;
	private double sell_price_CE = 0;
	private double buy_price_PE = 0;
	private double sell_price_PE = 0;
	private double buy_price_fut = 0;
	private double sell_price_fut = 0;
	private double Ex = 0;
	private double Fx = 0;
	private String maxx = "";
	private double max_value = 0;

	public void set_buy_price_CE(double bp) {
		
		buy_price_CE= bp;
		
	}
    
	public double get_buy_price_CE() {
		
		return buy_price_CE;
		
	}
	public void set_sell_price_CE(double sp) {
		
		sell_price_CE = sp;
		
	}
    
	public double get_sell_price_CE() {
		
		return sell_price_CE;
		
	}
	public void set_buy_price_PE(double bp) {
		
		buy_price_PE= bp;
		
	}
    
	public double get_buy_price_PE() {
		
		return buy_price_PE;
		
	}
	public void set_sell_price_PE(double sp) {
		
		sell_price_PE = sp;
		
	}
    
	public double get_sell_price_PE() {
		
		return sell_price_PE;
		
	}
	public void set_buy_price_fut(double bp) {
		
		buy_price_fut = bp;
		
	}
    
	public double get_buy_price_fut() {
		
		return buy_price_fut;
		
	}
	public void set_sell_price_fut(double sp) {
		
		sell_price_fut = sp;
		
	}
    
	public double get_sell_price_fut() {
		
		return sell_price_fut;
		
	}
	public void set_Ex(double ex) {
		
		Ex = ex;
		
	}
    
	public double get_Ex() {
		
		return Ex;
		
	}
	public void set_Fx(double fx) {
		
		Fx = fx;
		
	}
    
	public double get_Fx() {
		
		return Fx;
		
	}
	public void set_strike_price(int sp) {
		
		strike_price = sp;
		
	}
    
	public int get_strike_price() {
		
		return strike_price;
		
	}
	public void set_max_value(double m)
	{
		max_value = m;
	}
	public double get_max_value()
	{
		return max_value;
	}
	public void set_max(String m)
	{
		maxx = m;
	}
	public String get_max()
	{
		return maxx;
	}
	
}

public class ExFxRead {
	
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
    
    public static rowdata[] processSheet(Sheet s)
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
        return sheet;
    }
	
@SuppressWarnings("null")
public static void main(String[] args) throws IOException {
		
		
		
        String excelFilePath = "day 11.xlsx";
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
        	sheets.put(name,sheet);
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
        
        // to map timestamp to the Ex value for all sheets 
        Map<Date,timestampValues> all_sheets_Ex_Fx = new HashMap<Date, timestampValues>();
        //Map<Date,Vector<Double>> all_sheets_Fx = new HashMap<Date, Vector<Double>>();
        //iterating over the list to process first, second and future sheet
       
        int sizeOfClubbed = sheetsClubbed.size();
        for(int i = 0;i<sizeOfClubbed; i++)
        {
        	// to map timestamp value to the buy_price_CE, sell_price_CE, buy_price_PE, sell_price_PE, buy_price_fut, sell_price_fut for all timestamps
        	Map<Date, timestampValues> sheet_timestamp_values = new HashMap<Date, timestampValues>();
        	
        	String name = sheetsClubbed.get(i)[0];
        	name = name.substring(name.length()-7, name.length()-2);
//        	System.out.println(name);
        	if(Character.isDigit(name.charAt(0))==false)
        	{
        		name = name.substring(1);
        	}
        	int strike_price = Integer.parseInt(name);
        	System.out.println(strike_price);
//        	if(strike_price==10700)
//        	{
//        		break;
//        	}
        	rowdata[] sheet_CE = processSheet(sheets.get(sheetsClubbed.get(i)[0]));
        	rowdata[] sheet_PE = processSheet(sheets.get(sheetsClubbed.get(i)[1]));
        	rowdata[] sheet_fut = processSheet(sheets.get(futureSheet));
        	
        	
        	//find and store the buy_price_CE, sell_price_CE, buy_price_PE, sell_price_PE, buy_price_fut, sell_price_fut for all timestamps in above three sheets 
        	for(int f=0;f<sheet_CE.length;f++)
        	{
        		rowdata row = sheet_CE[f];
        		Date timestamp = row.get_time();
//        		System.out.println(timestamp  + " " +  row.get_buy_price());
        		if(sheet_timestamp_values.containsKey(timestamp))
        		{
        			sheet_timestamp_values.get(timestamp).set_buy_price_CE(row.get_buy_price());
        			sheet_timestamp_values.get(timestamp).set_sell_price_CE(row.get_sell_price());
        		}
        		else
        		{
        			timestampValues entry = new timestampValues();
        			entry.set_buy_price_CE(row.get_buy_price());
        			entry.set_sell_price_CE(row.get_sell_price());
        			entry.set_buy_price_PE(0);
        			entry.set_sell_price_PE(0);
        			entry.set_buy_price_fut(0);
        			entry.set_sell_price_fut(0);
        			sheet_timestamp_values.put(timestamp, entry);
        		}
        	}
        	for(int f=0;f<sheet_PE.length;f++)
        	{
        		rowdata row = sheet_PE[f];
        		Date timestamp = row.get_time();
        		
        		if(sheet_timestamp_values.containsKey(timestamp))
        		{
        			sheet_timestamp_values.get(timestamp).set_buy_price_PE(row.get_buy_price());
        			sheet_timestamp_values.get(timestamp).set_sell_price_PE(row.get_sell_price());
        		}
        		else
        		{
        			timestampValues entry = new timestampValues();
        			entry.set_Ex(-100000);
        			entry.set_Fx(-100000);
        			sheet_timestamp_values.put(timestamp, entry);
        		}
        	}
        	for(int f=0;f<sheet_fut.length;f++)
        	{
        		rowdata row = sheet_fut[f];
        		Date timestamp = row.get_time();
        		
        		if(sheet_timestamp_values.containsKey(timestamp))
        		{
        			sheet_timestamp_values.get(timestamp).set_buy_price_fut(row.get_buy_price());
        			sheet_timestamp_values.get(timestamp).set_sell_price_fut(row.get_sell_price());
        		}
        		else
        		{
        			timestampValues entry = new timestampValues();
        			entry.set_Ex(-100000);
        			entry.set_Fx(-100000);
        			sheet_timestamp_values.put(timestamp, entry);
        		}
        	}
        	
        	
        	
        	//print timestampvalues for every timestamp
        	
//        	Iterator timestampIterator = sheet_timestamp_values.entrySet().iterator();
//        	//calculate and store Ex values for all timestamps in this map
//        	while (timestampIterator.hasNext()) { 
//                Map.Entry mapElement = (Map.Entry)timestampIterator.next(); 
//                Date t = (Date)mapElement.getKey();
//                timestampValues value = (timestampValues) mapElement.getValue(); 
//                System.out.println(t + " " + value.get_buy_price_CE() + " " + value.get_buy_price_PE() +" " + value.get_buy_price_fut());
//        	}
        	
        	Iterator timestampIterator = sheet_timestamp_values.entrySet().iterator();
        	
        	//calculate and store Ex values for all timestamps in this map
        	while (timestampIterator.hasNext()) { 
                Map.Entry mapElement = (Map.Entry)timestampIterator.next(); 
                Date t = (Date)mapElement.getKey();
                timestampValues value = (timestampValues) mapElement.getValue(); 
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
//              double E = ((value.get_buy_price_fut()/2) - ((strike_price*3) - Math.pow(value.get_buy_price_PE(), 2) + Math.pow(value.get_sell_price_CE(), 0.5)))*75;
//        		double F = (((strike_price*3) - Math.pow(value.get_sell_price_PE(), 2) + Math.pow(value.get_buy_price_CE(), 0.5)) - (value.get_sell_price_fut()/2))*75;
            	// timestamp values in CE but not in PE or Fut
            	double E = 0, F=0;
            	if((value.get_Ex()==-100000 && value.get_Fx()==-100000)||(value.get_buy_price_PE()==0 && value.get_sell_price_PE()==0)||(value.get_buy_price_fut()==0 && value.get_sell_price_fut()==0))
            	{	E = -100000;
            		F = -100000;
            	}
            	double TE = (((value.get_buy_price_PE() * LotSize) * (SEBI + STTO + TranO)) + ((Bro + (TranO * (value.get_buy_price_PE() * LotSize))) * GSTO)) + ((value.get_sell_price_CE() * LotSize) * (SEBI + StampO + TranO) + (Bro + TranO * (value.get_sell_price_CE() * LotSize)) * GSTO) + ((value.get_buy_price_fut() * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (value.get_buy_price_fut() * LotSize)) * GSTF) + (3 * Bro);
                double TF = (((value.get_sell_price_PE() * LotSize) * (SEBI + StampO + TranO)) + ((Bro + TranO * (value.get_sell_price_PE() * LotSize)) * GSTO)) + ((value.get_buy_price_CE() * LotSize) * (SEBI + STTO + TranO) + (Bro + TranO * (value.get_buy_price_CE() * LotSize)) * GSTO) + ((value.get_sell_price_fut() * LotSize) * (SEBI + StampF + TranF) + (Bro + TranF * (value.get_sell_price_fut() * LotSize)) * GSTF) +(3 * Bro);
                
                if(E==0 && F==0)
                {
                	E = (((value.get_buy_price_fut()) - ((strike_price) - value.get_buy_price_PE() + value.get_sell_price_CE()))*75) - (TE + TF);
                	F = ((((strike_price) - value.get_sell_price_PE() + value.get_buy_price_CE()) - (value.get_sell_price_fut()))*75) - (TE + TF);
                	
                }
                
                if(all_sheets_Ex_Fx.containsKey(t))
                {
                	timestampValues v = all_sheets_Ex_Fx.get(t);
                	if(E!=0 && E>=F && E>=v.get_max_value())
                	{
                		v.set_strike_price(strike_price);
                		v.set_Ex(E);
                    	v.set_Fx(F);
                    	v.set_max("Ex");
                    	v.set_max_value(E);
                	}
                	else if(F!=0 && F>=E && F>=v.get_max_value())
                	{
                		v.set_strike_price(strike_price);
                		v.set_Ex(E);
                    	v.set_Fx(F);
                    	v.set_max("Fx");
                    	v.set_max_value(F);
                	}
                	all_sheets_Ex_Fx.replace(t, v);
                }
                else
                {
                	timestampValues v = new timestampValues();
                	v.set_strike_price(strike_price);
                	v.set_Ex(E);
                	v.set_Fx(F);
                	if(E>=F)
                	{
                		v.set_max("Ex");
                		v.set_max_value(E);
                	}
                	else
                	{
                		v.set_max("Fx");
                		v.set_max_value(F);
                	}
                	all_sheets_Ex_Fx.put(t, v);
                }
                
//                if(all_sheets_Fx.containsKey(t))
//                {
//                	all_sheets_Fx.get(t).add(F);
//                }
//                else
//                {
//                	Vector<Double> v = new Vector<Double>();
//                	v.add(F);
//                	all_sheets_Fx.put(t, v);
//                }
            }
        }
        
        //print max Ex or Fx values for every timestamp
        Iterator ExFxIterator = all_sheets_Ex_Fx.entrySet().iterator();
    	//calculate and store Ex values for all timestamps in this map

    	double NumberofOcc = 0;
    	while (ExFxIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)ExFxIterator.next(); 
            Date t = (Date)mapElement.getKey();
            timestampValues value = (timestampValues) mapElement.getValue(); 
            if(value.get_max_value()>0) {
            	NumberofOcc = NumberofOcc + 1;
            	System.out.println(t + " Strike Prize : " + value.get_strike_price() + " " + value.get_max() + " Value : " + value.get_max_value() + " Other: " + value.get_Fx());
            }
            
    	}
    	System.out.println("Number of Occurances = " + NumberofOcc);
    	
        // check if we can output a row of a given sheet
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter the sheet name : ");
//        String nameInput = sc.nextLine();
//        System.out.print("Enter the row number : ");
//        int rowInput = sc.nextInt();
//        
//        Sheet s = sheets.get(nameInput);
//        Row heading = s.getRow(0);
//        Row row = s.getRow(rowInput);
//        Iterator<Cell> headingIterator = heading.cellIterator();
//        Iterator<Cell> cellIterator = row.cellIterator();
//        
//        int j=0;
//        while (cellIterator.hasNext()) {
//        	
//            Cell cell = cellIterator.next();
//            Cell head = headingIterator.next();
//            switch (cell.getCellType()) {
//                case STRING:
//                    //System.out.print(cell.getStringCellValue());
//                    break;
//                case BOOLEAN:
//                    System.out.println(cell.getBooleanCellValue());
//                    break;
//                case NUMERIC:
//                	if(j == 0) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                		
//                	}
//                	else if(j == 1) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                		
//                	}
//                	else if(j == 2) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getDateCellValue());
//                	}
//                	else if(j == 3) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                	}
//                	else if(j == 4) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                	}
//                	else if(j == 5) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                	}
//                	else if(j == 6) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                	}
//                	else if(j == 7) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                	}
//                	else if(j == 8) {
//                		System.out.print(head.getStringCellValue() + ": ");
//                		System.out.println(cell.getNumericCellValue());
//                		
//                	}                   	
//                	if (DateUtil.isCellDateFormatted(cell)) {
//                        //System.out.print (nextRow.getCell(2).getDateCellValue());
//                    }
//                	else {
//                		//System.out.print(cell.getNumericCellValue());
//                	}
//                    break;
//            }
//            j++;
//            //System.out.print(" - ");
//            
//            
//        }
//        
        
        
//        //First Sheet
//        Sheet firstSheet = workbook.getSheetAt(0);
//        int endRow = firstSheet.getLastRowNum();
//        Iterator<Row> iterator = firstSheet.iterator();
//        rowdata[] sheetone = new rowdata[endRow + 20];
//        for(int k = 0; k<(endRow + 20) ; k++) {
//        	
//        	sheetone[k] = new rowdata();
//        }
//        
//        int i = 0;
//        int j = 0; 
//        int k = 0;
//        int one = 0;
//        int two = 0;
//        int three = 0;
//        
//        while (iterator.hasNext()) {
//        	
//            Row nextRow = iterator.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            j=0;
//            while (cellIterator.hasNext()) {
//            	
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case STRING:
//                        //System.out.print(cell.getStringCellValue());
//                        break;
//                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue());
//                        break;
//                    case NUMERIC:
//                    	if(j == 0) {
//                    		sheetone[i].set_last_price(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 1) {
//                    		sheetone[i].set_last_quantity(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 2) {
//                    		sheetone[i].set_time(cell.getDateCellValue());
//                    	}
//                    	else if(j == 3) {
//                    		sheetone[i].set_buy_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 4) {
//                    		sheetone[i].set_buy_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 5) {
//                    		sheetone[i].set_buy_quantity(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 6) {
//                    		sheetone[i].set_sell_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 7) {
//                    		sheetone[i].set_sell_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 8) {
//                    		sheetone[i].set_sell_quantity(cell.getNumericCellValue());
//                    		
//                    	}                   	
//                    	if (DateUtil.isCellDateFormatted(cell)) {
//                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//                        }
//                    	else {
//                    		//System.out.print(cell.getNumericCellValue());
//                    	}
//                        break;
//                }
//                j++;
//                //System.out.print(" - ");
//                
//                
//            }
//            i++;
//            //System.out.println();
//        }
//        //Second Sheet
//        
//        Sheet secondSheet = workbook.getSheetAt(1);
//        int endRowtwo = secondSheet.getLastRowNum();
//        Iterator<Row> iteratortwo = secondSheet.iterator();
//        rowdata[] sheettwo = new rowdata[endRowtwo + 20];
//        for(k = 0; k<(endRowtwo + 20) ; k++) {
//        	
//        	sheettwo[k] = new rowdata();
//        }
//        
//        i = 0;
//        j = 0;
//        
//        while (iteratortwo.hasNext()) {
//        	
//            Row nextRow = iteratortwo.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            j=0;
//            while (cellIterator.hasNext()) {
//            	
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case STRING:
//                        //System.out.print(cell.getStringCellValue());
//                        break;
//                    case BOOLEAN:
//                        //System.out.print(cell.getBooleanCellValue());
//                        break;
//                    case NUMERIC:
//                    	if(j == 0) {
//                    		sheettwo[i].set_last_price(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 1) {
//                    		sheettwo[i].set_last_quantity(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 2) {
//                    		sheettwo[i].set_time(cell.getDateCellValue());
//                    	}
//                    	else if(j == 3) {
//                    		sheettwo[i].set_buy_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 4) {
//                    		sheettwo[i].set_buy_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 5) {
//                    		sheettwo[i].set_buy_quantity(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 6) {
//                    		sheettwo[i].set_sell_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 7) {
//                    		sheettwo[i].set_sell_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 8) {
//                    		sheettwo[i].set_sell_quantity(cell.getNumericCellValue());
//                    		
//                    	}                   	
//                    	if (DateUtil.isCellDateFormatted(cell)) {
//                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//                        }
//                    	else {
//                    		//System.out.print(cell.getNumericCellValue());
//                    	}
//                        break;
//                }
//                j++;
//                //System.out.print(" - ");
//                
//                
//            }
//            i++;
//            //System.out.println();
//        }
//        
//        //Third sheet
//        Sheet thirdSheet = workbook.getSheetAt(2);
//        int endRowthree = thirdSheet.getLastRowNum();
//        Iterator<Row> iteratorthree = thirdSheet.iterator();
//        rowdata[] sheetthree = new rowdata[endRowthree + 20];
//        for(k = 0; k<(endRowthree + 20) ; k++) {
//        	
//        	sheetthree[k] = new rowdata();
//        }
//        
//        i = 0;
//        j = 0;
//        
//        while (iteratorthree.hasNext()) {
//        	
//            Row nextRow = iteratorthree.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            j=0;
//            while (cellIterator.hasNext()) {
//            	
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case STRING:
//                        //System.out.print(cell.getStringCellValue());
//                        break;
//                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue());
//                        break;
//                    case NUMERIC:
//                    	if(j == 0) {
//                    		sheetthree[i].set_last_price(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 1) {
//                    		sheetthree[i].set_last_quantity(cell.getNumericCellValue());
//                    		
//                    	}
//                    	else if(j == 2) {
//                    		sheetthree[i].set_time(cell.getDateCellValue());
//                    	}
//                    	else if(j == 3) {
//                    		sheetthree[i].set_buy_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 4) {
//                    		sheetthree[i].set_buy_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 5) {
//                    		sheetthree[i].set_buy_quantity(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 6) {
//                    		sheetthree[i].set_sell_price(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 7) {
//                    		sheetthree[i].set_sell_order(cell.getNumericCellValue());
//                    	}
//                    	else if(j == 8) {
//                    		sheetthree[i].set_sell_quantity(cell.getNumericCellValue());
//                    		
//                    	}                   	
//                    	if (DateUtil.isCellDateFormatted(cell)) {
//                            //System.out.print (nextRow.getCell(2).getDateCellValue());
//                        }
//                    	else {
//                    		//System.out.print(cell.getNumericCellValue());
//                    	}
//                        break;
//                }
//                j++;
//                //System.out.print(" - ");
//                
//                
//            }
//            i++;
//            //System.out.println();
//        }
//        
//        System.out.println(endRow);
        
        
        
         
        workbook.close();
        inputStream.close();
    }

}
