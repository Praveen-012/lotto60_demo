package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Changing_Password {
	
	@Test
	
	public void changing_password() throws Exception {
		
	GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.loginwithSignedupGmailModule();
		
		glotto.changePasswordModule_NegativeCase();
		
		glotto.changePasswordModule_PositiveCase();
		
		glotto.loggingoutgmail();
		
		glotto.openLottoApp();
		
		glotto.loggingwith_ChangedPassword();
		
		glotto.restoringChangedPasswordModule();
		
		glotto.loggingoutgmail();
}


}