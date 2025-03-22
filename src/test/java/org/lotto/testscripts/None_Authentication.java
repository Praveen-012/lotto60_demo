package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class None_Authentication {
	
	@Test
		
	public void none_authentication() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.loginwithSignedupGmailModule();
		
		glotto.none_OptionAuthentication();
		
		glotto.loggingoutgmail();
		
		glotto.loggingwith_NoneAuthenticationOption();
		
		glotto.loggingoutgmail();
	
		
	
}



}