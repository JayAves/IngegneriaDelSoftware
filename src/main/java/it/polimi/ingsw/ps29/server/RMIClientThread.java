package it.polimi.ingsw.ps29.server;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;

public class RMIClientThread extends ClientThread{

	public String Username;
	public boolean inGame;
	
	
	
	public RMIClientThread(String username) {
		
		Username = username;
		inGame=false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return Username;
	}

	@Override
	public void askNextAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callCorrectView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askBonusAction(BonusActionEffect effect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askAboutExchange(ExchangeResourcesEffect exchangeResourcesEffect) {
		// TODO Auto-generated method stub
		
	}
	

}
