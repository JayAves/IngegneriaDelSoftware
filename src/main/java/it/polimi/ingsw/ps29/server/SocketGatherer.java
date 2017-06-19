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
	private ArrayList<Thread> clients;
	boolean endOfConnection=false;
	
	public SocketGatherer (int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server online on port "+port);
			
		} catch (IOException e) {
			System.err.println("Unable to start the server!");
			e.printStackTrace();
		}
		
		clients= new ArrayList<Thread>();
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
				
				//
				System.out.println("Connection estabilished with: "+socket);
				try {
					PlayerInfoMessage tempLogin= new PlayerInfoMessage(null);
					tempLogin = (PlayerInfoMessage) ois.readObject();
					virtualView = new SocketClientThread(socket, tempLogin, oos, ois);
					
					//notifica RoomCreator
					setChanged();
					notifyObservers(virtualView);
					
					
					Thread t = new Thread (virtualView);
					t.start();
					clients.add(t);
					
				} catch (ClassNotFoundException e) {
					System.err.println("Unable to convert in String!");
					e.printStackTrace();
				}
				
				System.out.println("Virtual view created for: "+socket);
				
			} catch (IOException e) {
				System.err.println("Unable to connect a user!");
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Thread> getClients(){
		return clients;
	}
}
