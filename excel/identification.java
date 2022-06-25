import java.util.Date;

public class identification {
	
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
    				System.out.println(one + " " + two + " " + three + " " + E + " " + F);
    				//buy sheetone[one].sell_price and sell sheettwo[two].buy_price
    				//buy sheetthree[three].buy_price
    			}
    			else {
    				System.out.println(one + " " + two + " " + three + " " + E + " " + F);
    			}
    		}
    		else {
    			if((10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) > sheetthree[three].get_sell_price() && F>0) {
    				System.out.println(one + " " + two + " " + three + " " + E + " " + F);
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

}
