package it.polimi.ingsw.ps29.server;

import java.io.Serializable;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

public class RMIClientThread extends ClientThread implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2656528845360500313L;
	protected String username;
	protected boolean myTurn;
	protected boolean recentlyPoked;
	private RmiClientInterface clientInterface;
	
	
	
	
	
	public RMIClientThread(PlayerInfoMessage login, RmiClientInterface clientInterface) {
		
		this.username = login.getName();
		this.clientInterface=clientInterface;
		inGame=false;
		recentlyPoked=true;
		System.out.println(this.username);
		IDcode=login.getToken();
		
	}

	public void notifyController(InteractionMessage msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	
	@Override
	public void setInGame(boolean change){
    	inGame=change;
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
			//dopo x secondi verifico se ho ricevuto risposta
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not send message to client");
			inGame=false;
			PlayerInfoMessage error= new PlayerInfoMessage(username);
			notifyObservers(error);
		}
	}
	

}
