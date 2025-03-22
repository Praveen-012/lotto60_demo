package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class FooterAboutUsSection {

	@Test
	
	public void footeraboutussection() throws Exception {
			

			GeneralLotto glotto=new GeneralLotto();	
				
			glotto.openLottoApp();
			
			glotto.footerNonLoggedInAboutUsModule();
			
			glotto.loginPositiveCaseModule();
			
			glotto.footerLoggedInAboutUsModule();
			
			glotto.logoutModule();
			
			glotto.openLottoApp();
			
			glotto.footerProvablyBlockchainGamingModule();
		
			glotto.footerRNGtestingMethods();
			
			glotto.footerFAQModule();
			
			glotto.closeLottoapp();
			
			
	
	
}

	
	
}