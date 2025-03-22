package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class FooterGamesSection {

	@Test
	
	public void footergames() throws Exception {
		
		GeneralLotto glotto=new GeneralLotto();	
		
		glotto.openLottoApp();
		
		glotto.footerMegaJackpotNonLoggedInModule();
		
		glotto.loginPositiveCaseModule();
		
		glotto.footerMegaJackpotLoggedInModule();
		
		glotto.logoutModule();
		
		glotto.openLottoApp();
		
		glotto.footerInstantNonLoggedInModule();
		
		glotto.loginPositiveCaseModule();
		
		glotto.footerInstantLoggedInModule();
		
		glotto.logoutModule();
		
		glotto.openLottoApp();
		
		glotto.footerRoulottoNonLoggedInModule();
		
		glotto.loginPositiveCaseModule();
		
		glotto.footerRoulottoLoggedInModule();
		
		glotto.logoutModule();
		
		glotto.openLottoApp();
		
		glotto.footerGameRulesModule();
		

	}
		
	
		
	}
	

