package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.ps29.messages.FinalScores;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

/**
 * ClientThread that uses RMI technology for client-server Connection. Username is the one received by client.
 * @author Pietro Grotti
 * @see ClientThread
 */

public class RMIClientThread extends ClientThread {

	
	
	protected String username;
	protected boolean recentlyPoked;
	private RmiClientInterface clientInterface;
	private final ScheduledExecutorService scheduler;
	ScheduledFuture<?> beeperHandle;

	/**
	 * Id and Username are set
	 * @param login message got from Connection
	 * @param clientInterface got from Connection
	 */
	public RMIClientThread(PlayerInfoMessage login, RmiClientInterface clientInterface) {
		
		this.username = login.getName();
		this.clientInterface=clientInterface;
		inGame=false;
		recentlyPoked=true;
		IDcode=login.getToken();
		scheduler = Executors.newScheduledThreadPool(1);
		
		
		
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
		
		while(recentlyPoked) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public void stopClient() {

		inGame=false;
		recentlyPoked=false;
		
	}

	@Override
	public String getClientName() {

		return username;
	}



/**
 * Before sending messages from Controller to Connection, if they are FirstBoardInfo or InfoForView fills their timer field
 *  (in Client visitor will be used to set inputTimer).
 * If notification of client fails, a PlayerInfoMessage to Controller is sent and a disconnection procedure is done.
 */
	
	@Override
	public void startInteraction(InteractionMessage msg) {

		if (inGame){
			try {
				
				if (msg instanceof FirstBoardInfo) {
					((FirstBoardInfo)msg).setTimer(actionTimer);
				}
				
				if (msg instanceof InfoForView) {
					((InfoForView)msg).setTimer(actionTimer);
				}
				
				if ((msg).getBi()) { //only for bidirectional messages
					Task task= new Task();
					beeperHandle = scheduler.schedule(task, actionTimer, TimeUnit.MILLISECONDS);
					System.out.println("Msg sent to: "+((InteractionMessage)msg).getName());
					
					}
				
				clientInterface.notify(msg);
				
				if (msg instanceof FinalScores)
					stopClient();
				
		
			} catch (RemoteException e) {

			
			System.err.println("Could not send message to client");
			
			//if player logs out, any scheduled timer is deleted//
			if(beeperHandle != null)
				beeperHandle.cancel(false);
			
			inGame=false;
			PlayerInfoMessage error= new PlayerInfoMessage(username);
			setChanged();
			notifyObservers(error);
			
			}
		}
	}
	
	/**
	 * To notify Controller about a reconnected player who needs updated game situation
	 */
	@Override
	public void restoreSituation() {

		RestoreSituation restoreSituation = new RestoreSituation(username);
		setChanged();
		notifyObservers(restoreSituation);
	}
	
/**
 * Sends a PlayerInfoMessage to Controller and starts a disconnection procedure
 * @author Pietro Grotti
 *
 */
	protected class Task implements Runnable{

		@Override
		public void run() {

			System.out.println("TIMER on the server EXPIRED");
			RMIClientThread.this.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(username);
			msg.setTimeExpired();
			setChanged();
			notifyObservers(msg);
			
		}
		
	}

}
