package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Add_N_Edit_ProfilePicture {
	
	@Test
	
		public void addNeditpfp() throws Exception {
			
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.loginwithSignedupGmailModule();
		
		glotto.addingNeditingProfilePicture();
	
		glotto.loggingoutgmail();
		
		

		}
		
		
		
		
}

	
	
