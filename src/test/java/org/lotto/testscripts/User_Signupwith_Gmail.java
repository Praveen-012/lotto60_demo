package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class User_Signupwith_Gmail {
	
	@Test
		
	public void user_signupwithgmail() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.signupwithGmail_NegativeCase();
		
		glotto.openLottoApp();
		
		glotto.loginEmail_withoutConfirmation();
		
		glotto.openLottoApp();
		
		glotto.signupwithGmail_PositiveCase();
		
		glotto.logoutModule();

		glotto.openLottoApp();
		
		glotto.signupwithExisting_gmailid();
		
		glotto.logoutModule();
	
}


}