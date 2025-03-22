package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class ForgotPassword {
	
	@Test
		
	public void forgotPassword() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();
		
		glotto.openLottoApp();
		
		glotto.Forgot_PasswordModule();
		
		
	
	
}


}