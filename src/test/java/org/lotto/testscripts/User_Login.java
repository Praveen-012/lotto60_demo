package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class User_Login {

	@Test
	
	public void userLogin() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.loginNegativeCaseModule();
		
		glotto.closeLottoapp();
		
		glotto.openLottoApp();
		
		glotto.loginPositiveCaseModule();
		
		glotto.logoutModule();		
			
}

	}
	
	

