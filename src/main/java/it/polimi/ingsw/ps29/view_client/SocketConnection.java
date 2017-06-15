package it.polimi.ingsw.ps29.view_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class SocketConnection extends Connection {
	
	  
	private final int PORT = 9001;
	private final String ADDRESS = "localhost";
	private String playerName;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ClientSerializator serializator;
	
	public SocketConnection(String playerName) {
		this.playerName = playerName;
		try {
			socket = new Socket (ADDRESS, PORT);
			System.out.println("SocketConnection: "+socket);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			//utilizzo questo oggetto per l'invio di oggetti in rete
			serializator = new ClientSerializator(socket, oos, ois);
			
			//invio al server il nome del client
			oos.writeObject(playerName);
			oos.flush();
			System.out.println("Client succesfully created!");
			
		} catch (UnknownHostException e) {
			System.err.println("Unknown address!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to connect from client!");
			e.printStackTrace();
		}
		
	}


	@Override
	public void run() {
		while(true) {	
			System.out.println("I'm in Socket Connection for player "+playerName);
			InteractionMessage obj;
			try {
				//notifico Client
				do {
					obj = (InteractionMessage) ois.readObject();
				} while (obj==null);
				
				setChanged();
				notifyObservers(obj);
				
			} catch (IOException e) {
				System.err.println("Unable to receive message from server!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object!");
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void sendMessage(InteractionMessage msg) {
		serializator.serializeObject(msg);
	}
    
   

	
    
    
 




}
