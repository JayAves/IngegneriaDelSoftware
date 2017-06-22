package it.polimi.ingsw.ps29.server;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public class ServerSerializator {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private SocketClientThread thread;
	
	public ServerSerializator (SocketClientThread thread,Socket socket, ObjectOutputStream oos, ObjectInputStream ooi) {
		this.socket = socket;
		//System.out.println("ServerSerializator: "+socket);
		this.thread= thread;
		this.oos = oos;
		this.ois = ois;
	}
	
	public void serializeObject (Object o) {
		try {
			oos.writeObject(o);
			oos.flush();
			//Timer timer= new Timer();
			//timer.schedule(new Task(), thread.turnTimer);
			
		} catch (IOException e) {
			System.err.println("Unable to send object");
			thread.setInGame(false);
			PlayerInfoMessage msg= new PlayerInfoMessage(thread.getClientName());
			thread.notifyObservers(msg);
		}
		
	}
	
	private class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (!thread.msgBack) {
				PlayerInfoMessage msg= new PlayerInfoMessage(thread.getName());
				msg.setTimeExpired();
				thread.notifyObservers(msg);
			}
		}
		
	}

}
