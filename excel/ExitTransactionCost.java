
public class ExitTransactionCost {

	public static void main(String[] args) {
		
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
		double TEone = 0;
		double TFone = 0;
		double Futone = 0;
		double DTE = Math.pow(20, 0.5);
		double year = Math.pow(365, 0.5);
		double IV = 50;
		double St = 10500;
		
		if(10500 - sheettwo[two].get_sell_price() + sheetone[one].get_buy_price()) > sheetthree[three].get_sell_price()){
			
			Futone = ((sheetthree[three].get_sell_price() * IV * DTE)/year) + sheetthree[three].get_sell_price();
			
			if((St - Futone)>0) {
				TFone = (((St - Futone) * LotSize) * (SEBI + STTO + TranO)) + ((Bro + TranO) * ((St - Futone) * LotSize)) * GSTO +
						(Futone * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (Futone * LotSize)) * GSTF +
						(2 * Bro);
			}
			else {
				
				TFone = (((Futone - St) * LotSize) * (SEBI + StampO + TranO)) + ((Bro + TranO) * ((Futone - St) * LotSize)) * GSTO +
						(Futone * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (Futone * LotSize)) * GSTF +
						(2 * Bro);
				
			}
		}
		
		else if((10500 - sheettwo[two].get_buy_price() + sheetone[one].get_sell_price()) < sheetthree[three].get_buy_price()) {
			
			Futone = ((sheetthree[three].get_buy_price() * IV * DTE)/year) + sheetthree[three].get_buy_price();
			
			if((St - Futone)>0) {
				TFone = (((St - Futone) * LotSize) * (SEBI + STTO + TranO)) + ((Bro + TranO) * ((St - Futone) * LotSize)) * GSTO +
						(Futone * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (Futone * LotSize)) * GSTF +
						(2 * Bro);
			}
			else {
				
				TFone = (((Futone - St) * LotSize) * (SEBI + StampO + TranO)) + ((Bro + TranO) * ((Futone - St) * LotSize)) * GSTO +
						(Futone * LotSize) * (SEBI + STTF + TranF) + (Bro + TranF * (Futone * LotSize)) * GSTF +
						(2 * Bro);
				
			}
			
		}

	}

}
