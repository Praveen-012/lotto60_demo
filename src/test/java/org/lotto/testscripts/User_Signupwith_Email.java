package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class User_Signupwith_Email {

	@Test
		
	public void user_signupwithemail() throws Exception {

		GeneralLotto glotto=new GeneralLotto();
		
		glotto.openLottoApp();
		
		glotto.signupwithEmail_NegativeCase();
		
		glotto.signupwithEmail_PositiveCase();
	
		glotto.loginwithSignedupEmailModule();
		
		glotto.logoutModule();
	
}

}