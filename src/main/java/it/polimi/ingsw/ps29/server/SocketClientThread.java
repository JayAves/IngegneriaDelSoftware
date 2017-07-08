package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

/**
 * ClientThread that uses socket technology for client-server Connection. Username is the one received by client.
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */
public class SocketClientThread extends ClientThread {
	
	private Socket socket;
	private String playerName;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerSerializator serializator;
	private boolean endOfConnection= false;
	private boolean newConnection=false;
	
	
	public SocketClientThread(Socket socket, PlayerInfoMessage playerLogin, ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket = socket;
		this.playerName = playerLogin.getName();
		this.oos = oos;
		System.out.println(playerName);
		
		this.ois = ois;
		IDcode= playerLogin.getToken();
		inGame=false;
		
		serializator = new ServerSerializator(this,socket, this.oos, this.ois);
	}
	
	/**
	 * Part that receives messages from Connection. Any exception in here will notify Controller with a PlayerInfoMessage and start a disconnection procedure
	 * 
	 */
	@Override
	public void run() {
		Object obj;
		
		while(!endOfConnection) {
			try{
				do {
					obj = ois.readObject();
				} while (obj==null);
				
				System.out.println("Server: msg received by "+playerName+":\n"+obj.toString()+"\n");
				
				//feedback arrived: timer must be reset
				serializator.beeperHandle.cancel(true);
				
				setChanged();
				notifyObservers(obj);
				
				
			} catch (IOException e) {
				
				System.err.println("Unable to receive message from client!");
				endOfThis();
				
				
			
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object");
				endOfThis();
				}
		}
		
		while (!newConnection) {
			
		}
		
	}
	
	public String getName () {
		return playerName;
	}
    
    
    @Override
    public void setInGame(boolean change){
    	inGame=change;
    }

	@Override
	public String getClientName() {
		return playerName;
	}


	@Override
	public void startInteraction(InteractionMessage msg) {
		if (inGame) {
			serializator.serializeObject(msg);
		}
	}


	@Override
	protected void stopClient() {
		endOfConnection=true;
		newConnection=true;
	}
	
	public void endOfThis() {
		
		//if player logs out, any scheduled timer is deleted
		if(serializator.beeperHandle != null)
			serializator.beeperHandle.cancel(false);
		
		inGame = false;
		PlayerInfoMessage msg= new PlayerInfoMessage(playerName);
		endOfConnection=true;
		setChanged();
		notifyObservers(msg);
		
	}

	public void notifyController(InteractionMessage msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	/**
	 * To notify Controller about a reconnected player who needs updated game situation
	 */
	@Override
	public void restoreSituation() {
		RestoreSituation restoreSituation = new RestoreSituation(playerName);
		setChanged();
		notifyObservers(restoreSituation);
	}

	
	/**
	 * Part responsible of sending messages through socket and scheduling turnTimers for good server behavior
	 * @author Pietro Melzi
	 *
	 */
	private class ServerSerializator {
		
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private SocketClientThread thread;
		private final ScheduledExecutorService scheduler;
		ScheduledFuture<?> beeperHandle;
		
		public ServerSerializator (SocketClientThread thread,Socket socket, ObjectOutputStream oos, ObjectInputStream ooi) {
			this.socket = socket;
			this.thread= thread;
			this.oos = oos;
			this.ois = ois;
			scheduler = Executors.newScheduledThreadPool(1);
			
		}
		/**
		 * Before sending messages from Controller to Connection, if they are FirstBoardInfo or InfoForView fills their timer field
		 *  (in Client visitor will be used to set inputTimer).
		 * If connection to client fails, a PlayerInfoMessage to Controller is sent and a disconnection procedure is done. 
		 * @param o object to send throgh socket
		 */
		public void serializeObject (Object o) {
			try {
				
				if (o instanceof FirstBoardInfo) 
					((FirstBoardInfo)o).setTimer(thread.actionTimer);
				
				else if (o instanceof InfoForView) 
					((InfoForView)o).setTimer(thread.actionTimer);
				
				
				oos.writeObject(o);
				oos.flush();
		
				if (((InteractionMessage)o).getBi()) { //only for bidirectional messages
					Task task= new Task();
					beeperHandle = scheduler.schedule(task, thread.actionTimer, TimeUnit.MILLISECONDS);
					System.out.println("Msg sended by: "+((InteractionMessage)o).getName());
				}	
				
				if (o instanceof FinalScores)
					stopClient();
				
				
			} catch (IOException e) {
				System.err.println("Unable to send object");
				thread.setInGame(false);
				PlayerInfoMessage msg= new PlayerInfoMessage(thread.getClientName());
				thread.notifyObservers(msg);
			}
			
		}
		
		/**
		 * Sends a PlayerInfoMessage to Controller and starts a disconnection procedure
		 * @author Pietro Grotti
		 * @author Pietro Melzi
		 *
		 */
		protected class Task implements Runnable{

			@Override
			public void run() {
				System.out.println("TIMER on the server EXPIRED");
				
				ServerSerializator.this.thread.setInGame(false);
				PlayerInfoMessage msg= new PlayerInfoMessage(thread.getName());
				
				msg.setTimeExpired();
				thread.notifyController(msg);
			}
			
		}
		
		
	}

	
}

