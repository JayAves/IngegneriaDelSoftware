package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;

import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view_client.RmiClientInterface;

public class RMIClientThread extends ClientThread{

	protected String username;
	protected boolean myTurn;
	protected boolean recentlyPoked;
	private RmiClientInterface clientInterface;
	private InteractionMessage buffer;
	
	
	
	
	public RMIClientThread(String username, RmiClientInterface clientInterface) {
		
		this.username = username;
		this.clientInterface=clientInterface;
		inGame=false;
		recentlyPoked=true;
		
	}

	public void notifyController(InteractionMessage msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	
	@Override
	public void setInGame(){
    	inGame=true;
    }
	
	@Override
	public void run() {
		
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
		return username;
	}




	@Override
	public void startInteraction(InteractionMessage msg) {
		// TODO Auto-generated method stub
		try {
			
			clientInterface.notify(msg);
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not send message to client");
		}
	}
	

}
