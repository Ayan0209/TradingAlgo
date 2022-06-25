
public class PutnCallOptions {
	
	public static void main(String[] args) {
		
		//sheetone is 10500CE
		//sheettwo is 10500PE
		//sheetthree is Fut
		//E is estimated profit
		//lot_size 
		
		if(two != 0 && three !=0) {
    		double E = (sheetthree[three].get_buy_price() - (10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price()))*75;
    		double F = ((10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) - sheetthree[three].get_sell_price())*75;
    		
    		if(E>F) {
    			if((10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price())< sheetthree[three].get_buy_price() && E>0) {
    				System.out.println(one + " " + two + " " + three + " " + E);
    				//buy sheetone[one].sell_price and sell sheettwo[two].buy_price
    				//sell sheetthree[three].buy_price
    			}
    			else {
    				System.out.println(one + " " + E);
    			}
    		}
    		else {
    			if((10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) > sheetthree[three].get_sell_price() && F>0) {
    				System.out.println(one + " " + two + " " + three + " " + F);
    				//buy sheettwo[two].sell_price and sell sheetone[one].buy_price
    				//buy sheetthree[three].sell_price
    			}
    			else {
    				System.out.println(one + " " + F);
    			}
    		}
    	}
		
	}
}
