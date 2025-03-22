package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Google_Authentication {
	
	@Test	
		
	public void google_authentication() throws Exception {

		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.loginwithSignedupGmailModule();
		
		glotto.GoogleAuthTabModule();
		
		//For Negative Case
		glotto.googleAuth_NegCaseModule();
		
		glotto.loggingoutgmail();
		
		//For Positive Case
		glotto.loginwithSignedupGmailModule();
		
		glotto.GoogleAuthTabModule();
		
		glotto.googleAuth_PositiveCaseModule();
		
		glotto.loggingoutgmail();
		
		glotto.loggingwith_GoogleAuth();
		
		glotto.loggingoutgmail();
		
	
	
}


}