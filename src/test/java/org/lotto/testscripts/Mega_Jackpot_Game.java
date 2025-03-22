package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

	public class Mega_Jackpot_Game {

		@Test
		
		public void megajackpotgame() throws Exception {
		
			GeneralLotto glotto=new GeneralLotto();	
			
			glotto.openLottoApp();
			
			glotto.megajackpot_WithoutLoggedInUser();
			
			glotto.openLottoApp();
			
			glotto.gameLogin();
			
			glotto.megajackpot_WithLoggedInUser_NegativeCaseModule();
			
			glotto.logoutModule(); 
			
			glotto.openLottoApp();
			
			glotto.loginwithSignedupGmailModule();
			
			glotto.megajackpot_WithLoggedInUser_PositiveCaseModule();
			
			glotto.megaJackpot_WithLoggedInUser_Bonus();
			
			glotto.loggingoutgmail();
			

		}
				
	
	}

