package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			
		}
		
		clients= new ArrayList<SocketClientThread>();
	}
	
	
	/**
	 * Accepts new connections, creates and notifies new SocketClientThreads, manages the old ones.
	 */
	
	public void startServer () {
		
		while(!endOfConnection) {
			
			try {
				Socket socket = serverSocket.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				ois = new ObjectInputStream(socket.getInputStream());
				
				try {
					PlayerInfoMessage tempLogin = (PlayerInfoMessage) ois.readObject();
					SocketClientThread virtualView = new SocketClientThread(socket, tempLogin, oos, ois);
					virtualView.setTurnTimer(turnTimer);
					SocketClientThread toDelete= null;
					
					for(SocketClientThread th: clients) 
						//if finds player already in a game
						if (th.IDcode.contentEquals(virtualView.IDcode)) { 
							virtualView.setInGame(true);
							toDelete=th;
						}
					
					handleVirtualView(virtualView, toDelete);
				
				} catch (ClassNotFoundException e) {
					System.err.println("Unable to convert in String!");
					Thread.currentThread().interrupt();
				}
				
			} catch (IOException e) {
				System.err.println("Unable to add a user!");
				Thread.currentThread().interrupt();
			}
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
		}
	}
	
	
	private void handleVirtualView (SocketClientThread virtualView, SocketClientThread toDelete) {
		
		//notify RoomCreator
		setChanged();
		notifyObservers(virtualView);
		
		//Get rid of old ClientThread
		clients.remove(toDelete);	
		clients.add(virtualView);
		
		Thread t = new Thread (virtualView);
		t.start();
	}
	
	
	public ArrayList<SocketClientThread> getClients(){
		return clients;
	}
	
	public void endOfConnection() {
		endOfConnection=true;
	}

	@Override
	public void run() {

		startServer();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
		}
	}
}
