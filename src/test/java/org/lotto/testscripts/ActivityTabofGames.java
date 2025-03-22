package org.lotto.testscripts;

import org.lotto.lib.GeneralLotto;
import org.testng.annotations.Test;

public class ActivityTabofGames {

	@Test
	
	public void activitytabgames() throws Exception {
	
		GeneralLotto glotto=new GeneralLotto();	
		
		//Mega Jackpot Activity
		
			glotto.openLottoApp();
		
			glotto.loginwithSignedupGmailModule();
			
			glotto.megaActivityUpcomingDrawsModule();
			
			glotto.activityPaginationModule();
			
			glotto.loggingoutgmail();
			
			glotto.openLottoApp();
			
			glotto.loginwithSignedupGmailModule();
			
			glotto.megaActivityPastDrawsModule();
			
			glotto.activityPaginationModule();
			
			glotto.loggingoutgmail();
		
		//Instant Jackpot Activity
		
			glotto.openLottoApp();
			
			glotto.loginwithSignedupGmailModule();
			
			glotto.instantActivityModule();
			
			glotto.activityPaginationModule();
			
			glotto.loggingoutgmail();
			
		//Roulotto Activity
			
			glotto.openLottoApp();
			
			glotto.loginwithSignedupGmailModule();
			
			glotto.roulottoActivityModule();
			
			glotto.activityPaginationModule();
			
			glotto.loggingoutgmail();
	 

	}
	

	
	}

