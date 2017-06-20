package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public class SocketGatherer extends Observable {

	private ServerSocket serverSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<Thread> ths;
	private ArrayList<SocketClientThread> clients;
	boolean endOfConnection=false;
	
	public SocketGatherer (int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server online on port "+port);
			
		} catch (IOException e) {
			System.err.println("Unable to start the server!");
			e.printStackTrace();
		}
		
		ths= new ArrayList<Thread>();
		clients= new ArrayList<SocketClientThread>();
	}
	
	public void startServer () {
		
		Socket socket;
		SocketClientThread virtualView;
		
		while(!endOfConnection) {
			
			try {
				socket = serverSocket.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				ois = new ObjectInputStream(socket.getInputStream());
				
				
				//System.out.println("Connection estabilished with: "+socket);
				
				try {
					PlayerInfoMessage tempLogin= new PlayerInfoMessage(null);
					tempLogin = (PlayerInfoMessage) ois.readObject();
					virtualView = new SocketClientThread(socket, tempLogin, oos, ois);
					SocketClientThread toDelete= null;
					
				for( SocketClientThread th: clients) {
					
					if (th.IDcode.contentEquals(virtualView.IDcode)) { //se compare già notifico il roomCreator che dovrò allacciare alla partita giusta
						virtualView.setInGame(true);
						toDelete=th;
						System.out.println("Player recently logged");
						}
				}
					//notifica RoomCreator//
					setChanged();
					notifyObservers(virtualView);
					System.out.println(toDelete);	
					clients.remove(toDelete);	
					clients.add(virtualView);
					System.out.println(clients);
					Thread t = new Thread (virtualView);
					t.start();
					ths.add(t); //dovrò eliminare thread della vecchia virtual view
						
				
				} catch (ClassNotFoundException e) {
					System.err.println("Unable to convert in String!");
					e.printStackTrace();
				}
				
				//System.out.println("Virtual view created for: "+socket);
				
			} catch (IOException e) {
				System.err.println("Unable to add a user!");
				e.printStackTrace();
				//disconnessione del server
			}
		}
	}
	
	public ArrayList<Thread> getThreads(){
		return ths;
	}
	
	public ArrayList<SocketClientThread> getClients(){
		
		return clients;
	}
	public void endOfConnection() {
		endOfConnection=true;
	}
}
