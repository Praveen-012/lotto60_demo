package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class FooterAccountsSection {

	@Test	
	
	private void footeraccountssection() throws Exception {	
		
				GeneralLotto glotto=new GeneralLotto();	
					
				glotto.openLottoApp();
				
				glotto.footerHowToOpenAccountModule();
				
				glotto.footerBonusModule();
				
				glotto.footerTermsOfUseModule();
				
				glotto.footerKYCandAMLpolicyModule();
				
		
			}
	
}