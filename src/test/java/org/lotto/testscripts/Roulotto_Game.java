package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Roulotto_Game {

	@Test
		
	public void roulotto_Game() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();	
		
		/*glotto.openLottoApp();
		
		//glotto.roulotto_WithoutLoggedInUser();
		
		glotto.gameLogin();
		
		glotto.roulotto_WithLoggedInUser_NegativeCaseModule();
		
		glotto.logoutModule(); */
		
		glotto.openLottoApp();
		
		glotto.loginwithSignedupGmailModule();
		
		glotto.roulotto_WithLoggedInUser_PositiveCaseModule();
		
		glotto.roulotto_WithLoggedInUser_Bonus();
		
		glotto.loggingoutgmail();
		
		
		
		
	
	
	
}



}