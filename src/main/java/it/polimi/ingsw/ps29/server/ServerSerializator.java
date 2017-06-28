package it.polimi.ingsw.ps29.server;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;

public class ServerSerializator {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private SocketClientThread thread;
	private Timer timer;
	private ArrayList<Task> timeOuts;
	
	public ServerSerializator (SocketClientThread thread,Socket socket, ObjectOutputStream oos, ObjectInputStream ooi) {
		this.socket = socket;
		//System.out.println("ServerSerializator: "+socket);
		this.thread= thread;
		this.oos = oos;
		this.ois = ois;
		timer= new Timer();
		timeOuts= new ArrayList<Task>();
		
	}
	
	public void serializeObject (Object o) {
		
		
		try {
			
			if (o instanceof FirstBoardInfo) {
				((FirstBoardInfo)o).setTimer(thread.actionTimer);
			}
			
			if (o instanceof InfoForView) {
				((InfoForView)o).setTimer(thread.actionTimer);
			}
			
			oos.writeObject(o);
			oos.flush();
	
			if (((InteractionMessage)o).getBi()) { //only for bidirectional messages
				Task task= new Task();
				timeOuts.add(task);
				timer.schedule(task, thread.actionTimer);
			}	
			
			
			
		} catch (IOException e) {
			System.err.println("Unable to send object");
			thread.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(thread.getClientName());
			thread.notifyObservers(msg);
		}
		
	}
	
	protected class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ServerSerializator.this.thread.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(thread.getName());
			msg.setTimeExpired();
			thread.notifyController(msg);
			
		}
		
	}
	
	public ArrayList<Task> getTasks() {
		return timeOuts;
	}
}
