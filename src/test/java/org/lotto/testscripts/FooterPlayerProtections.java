package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class FooterPlayerProtections {

	@Test
	
	public void footerplayerprotections() throws Exception {
		
		GeneralLotto glotto=new GeneralLotto();	
			
		glotto.openLottoApp();
		
		glotto.footerPrivacyManagementModule();
		
		glotto.footerResponsibleGamingModule();
		
		glotto.footerSelfExclusionModule();
		
		glotto.footerCustomerPolicyModule();
		
		glotto.closeLottoapp();
		
		
	
}

	
}