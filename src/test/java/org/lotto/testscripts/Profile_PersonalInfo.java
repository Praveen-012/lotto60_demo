package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class Profile_PersonalInfo {

		@Test
			
		public void profile_personalinfo() throws Exception {
			
				GeneralLotto glotto=new GeneralLotto();	
				
				glotto.openLottoApp();
				
				glotto.loginwithSignedupGmailModule();
				
				glotto.profile_PersonalInfo_NegativeCase();
				
				glotto.profile_PersonalInfo_PositiveCase();
				
				glotto.reeditprofile_PersonalInfo();
				
				glotto.loggingoutgmail();
				
				
}
			
		
}