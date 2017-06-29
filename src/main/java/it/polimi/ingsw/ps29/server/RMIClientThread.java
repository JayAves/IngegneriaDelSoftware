package it.polimi.ingsw.ps29.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.server.ServerSerializator.Task;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

public class RMIClientThread extends ClientThread implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2656528845360500313L;
	protected String username;
	protected boolean recentlyPoked;
	private RmiClientInterface clientInterface;
	private final ScheduledExecutorService scheduler;
	ScheduledFuture<?> beeperHandle;

	
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
		}
	}

	@Override
	public void stopClient() {
		// TODO Auto-generated method stub
		inGame=false;
		recentlyPoked=false;
		
	}

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return username;
	}




	@Override
	public void startInteraction(InteractionMessage msg) {
		// TODO Auto-generated method stub
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
					System.out.println("Msg sended by: "+((InteractionMessage)msg).getName());
					
					}
				
				clientInterface.notify(msg);
				
		
			} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
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
	
	protected class Task implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("TIMER on the server EXPIRED");
			RMIClientThread.this.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(username);
			msg.setTimeExpired();
			setChanged();
			notifyObservers(msg);
			
		}
		
	}

}
