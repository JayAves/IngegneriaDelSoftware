package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;

public class ServerSerializator {
	
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
			
			
		} catch (IOException e) {
			System.err.println("Unable to send object");
			thread.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(thread.getClientName());
			thread.notifyObservers(msg);
		}
		
	}
	
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
