package it.polimi.ingsw.ps29.server;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class RMIClientThread extends ClientThread{

	protected String Username;
	protected boolean myTurn;
	protected boolean recentlyPoked;
	
	
	
	
	public RMIClientThread(String username) {
		
		Username = username;
		inGame=false;
		recentlyPoked=true;
	}

	
	@Override
	public void setInGame(){
    	inGame=true;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//logica applicativa: se non ricevo richieste per un po' di tempo notifico server
		while(recentlyPoked) {
			//timer che se scade chiude il thread
		}
	}

	@Override
	public void stopClient() {
		// TODO Auto-generated method stub
		inGame=false;
		
	}

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return Username;
	}




	@Override
	public void startInteraction(InteractionMessage msg) {
		// TODO Auto-generated method stub
		//devo mettere il messaggio a disposizione del client
	}
	

}
