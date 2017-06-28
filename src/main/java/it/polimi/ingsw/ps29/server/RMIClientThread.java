package it.polimi.ingsw.ps29.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
	private Timer timer;
	private ArrayList<Task> timeOuts;

	
	public RMIClientThread(PlayerInfoMessage login, RmiClientInterface clientInterface) {
		
		this.username = login.getName();
		this.clientInterface=clientInterface;
		inGame=false;
		recentlyPoked=true;
		//System.out.println(this.username);
		IDcode=login.getToken();
		timer= new Timer();
		timeOuts= new ArrayList<Task>();
		
		
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
					timeOuts.add(task);
					timer.schedule(task,actionTimer);
					
					}
				
				clientInterface.notify(msg);
				
		
			} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
			System.err.println("Could not send message to client");
			inGame=false;
			PlayerInfoMessage error= new PlayerInfoMessage(username);
			for (Task tsk: timeOuts) {
				tsk.cancel();
				//serializator.getTasks().remove(tsk);
			}
			timeOuts.clear();
			setChanged();
			notifyObservers(error);
			}
		}
	}
	
	protected class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			RMIClientThread.this.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(username);
			msg.setTimeExpired();
			setChanged();
			notifyObservers(msg);
			
		}
		
	}
	public ArrayList<Task> getTimeOuts() {
		return timeOuts;
	}
}
