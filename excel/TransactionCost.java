
public class TransactionCost {
	
	public static void main (String[] args) {
		
		double SEBI = 0.0000005;
		double LotSize = 75;
		double StampO = 0.00003;
		double StampF = 0.00002;
		double Bro = 20;
		double TranO = 0.0005;
		double TranF = 0.000019;
		double GSTO = 0.18*(Bro+TranO);
		double GSTF = 0.18*(Bro+TranF);
		double STTO = 0.0001;
		double STTF = 0.0005;
		double TE = 0;
		double TF = 0;
		
		if(10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) > sheetthree[three].get_sell_price()) {
			
			TF = (sheettwo[two].get_sell_price() * LotSize) * (SEBI + StampO + TranO) + (Bro + TranO * (sheettwo[two].get_sell_price() * LotSize)) * GSTO + 
					(sheetone[one].get_buy_price() * LotSize) * (SEBI + STTO + TranO) + (Bro + TranO * (sheetone[one].get_buy_price() * LotSize)) * GSTO +
					(sheetthree[three].get_sell_price() * LotSize) * (SEBI + StampF + TranF) + (Bro + TranF * (sheetthree[three].get_sell_price() * LotSize)) * GSTF +
					(3 * Bro);
			
		}
		
		else if((10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price()) < sheetthree[three].get_buy_price()) {
			
			TE = (sheettwo[two].get_buy_price() * LotSize) * (SEBI + STTO + TranO) + (Bro + TranO * (sheettwo[two].get_buy_price() * LotSize)) * GSTO +
					(sheetone[one].get_sell_price() * LotSize) * (SEBI + StampO + TranO) + (Bro + TranO * (sheetone[one].get_sell_price() * LotSize)) * GSTO +
					(sheetthree[three].get_buy_price() * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (sheetthree[three].get_buy_price() * LotSize)) * GSTF +
					(3 * Bro);
			
		}
		
		
	}

}
