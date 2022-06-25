
public class CashandCarry {
	
	public static void main(String[] args) {
		
		//sheetone is Fut
		//sheettwo is Underlying
		//div is dividend yeild
		//n is number of times compounded per year
		//t is number of years
		//r is riskfree rate
		
		double div = 0;
		double r = 3;
		double n = 0;
		double t = 0;
		double x = Math.pow((1 + ((r - div)/n)), (n*t));
		
		if(sheetone[i].get_buy_price > (sheettwo[i].get_last_price * x)) {
			//sell Fut
			//buy Underlying
		}
		else if(sheetone[i].get_sell_price < (sheettwo[i].get_last_price * x)) {
			//sell Underlying
			//buy Fut 
		}
			
		
	}

}
