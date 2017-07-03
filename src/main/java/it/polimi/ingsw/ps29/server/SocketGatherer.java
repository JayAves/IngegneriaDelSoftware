package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;

/**
 * Gathers new socket connections and manages SocketClientThreads, notifies the new ones to RoomCreator.
 * @author Pietro Grotti
 *
 */

public class SocketGatherer extends Observable implements Runnable{

	private ServerSocket serverSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<SocketClientThread> clients;
	boolean endOfConnection=false;
	private int turnTimer;
	
	
	public SocketGatherer (int port, int turnTimer) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server online on port "+port);
			this.turnTimer= turnTimer;
			
		} catch (IOException e) {
			System.err.println("Unable to start the server!");
			e.printStackTrace();
		}
		
		clients= new ArrayList<SocketClientThread>();
	}
	
	
	/**
	 * Accepts new connections, creates and notifies new SocketClientThreads, manages the old ones.
	 */
	
	public void startServer () {
		Socket socket;
		SocketClientThread virtualView;
		
		while(!endOfConnection) {
			
			try {
				socket = serverSocket.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				ois = new ObjectInputStream(socket.getInputStream());
				
				try {
					PlayerInfoMessage tempLogin = (PlayerInfoMessage) ois.readObject();
					virtualView = new SocketClientThread(socket, tempLogin, oos, ois);
					virtualView.setTurnTimer(turnTimer);
					SocketClientThread toDelete= null;
					
					for(SocketClientThread th: clients)
						//if finds player already in a game
						if (th.IDcode.contentEquals(virtualView.IDcode)) { 
							virtualView.setInGame(true);
							toDelete=th;
						}
					
					//notify RoomCreator
					setChanged();
					notifyObservers(virtualView);
					
					//Get rid of old ClientThread
					clients.remove(toDelete);	
					clients.add(virtualView);
					
					Thread t = new Thread (virtualView);
					t.start();
				
				} catch (ClassNotFoundException e) {
					System.err.println("Unable to convert in String!");
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				System.err.println("Unable to add a user!");
				e.printStackTrace();
			}
		}
		
	}
	
	
	public ArrayList<SocketClientThread> getClients(){
		
		return clients;
	}
	public void endOfConnection() {
		endOfConnection=true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		startServer();
	}
}
