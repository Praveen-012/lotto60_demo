package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Instant_Jackpot_Game {

	@Test
	
	public void instant_Jackpot_Game() throws Exception {

	
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.instant_WithoutLoggedInUser(); 
		
		glotto.gameLogin();
	
		glotto.instant_WithLoggedInUser_NegativeCaseModule();
		
		glotto.logoutModule();
		
		glotto.openLottoApp();
	
		glotto.loginwithSignedupGmailModule();
		
		glotto.instant_WithLoggedInUser_PositiveCaseModule();
		
		glotto.instant_bonus();
		
		glotto.loggingoutgmail();
}


}