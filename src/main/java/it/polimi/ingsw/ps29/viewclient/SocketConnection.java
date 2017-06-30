package it.polimi.ingsw.ps29.viewclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;

/**
 * Connection client-server through socket
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */

public class SocketConnection extends Connection {
	
	  
	private final int PORT = 9001;
	private final String ADDRESS = "localhost";
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ClientSerializator serializator;
	private PlayerInfoMessage loginMessage;
	
	/**
	 * Sets login token, opens a new socket connection with server, sends its first info (name and login token)
	 * @author Pietro Grotti
	 * @author Pietro Melzi
	 * @param playerName  for game's playerName
	 * 
	 * 
	 */
	public SocketConnection(String playerName) {
		
		loginMessage= new PlayerInfoMessage(playerName);
		
		try {
			
			setLoginToken(loginMessage);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Could not set loginMessage");
		}
		
		try {
			socket = new Socket (ADDRESS, PORT);
			//System.out.println("SocketConnection: "+socket);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			//utilizzo questo oggetto per l'invio di oggetti in rete//
			serializator = new ClientSerializator(socket, oos, ois);
			
			
			
			
			
			//invio al server nome e token del client//
			oos.writeObject(loginMessage);
			oos.flush();

			
			
		} catch (UnknownHostException e) {
			System.err.println("Unknown address!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("\nUnable to connect to server!");
			
		}
		
	}

	
	@Override
	public void run() {
		boolean connection=true;
		while(connection) {	
			Object obj;
			try {
				//any time a new message is received it is notified to Client//
				do {
					obj = ois.readObject();
				} while (obj==null);
				
				setChanged();
				notifyObservers(obj);
				
			} catch (IOException e) {
				System.err.println("Unable to receive message from server!");
				connection=false;
				System.out.println("\nGame Over");
				
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object!");
				connection=false;
				System.out.println("\nGame Over");
				
			}
		}
		
	}


	@Override
	public void sendMessage(InteractionMessage msg) {
		serializator.serializeObject(msg);
	}
    
    
	/**
	 * Part of SocketConnection responsible for sending messages to server
	 * @author Pietro Melzi
	 * @author Pietro Grotti
	 * @see SocketConnection
	 * @see Socket
	 *
	 */

	private class ClientSerializator {
		
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		
		
		public ClientSerializator (Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
			this.socket = socket;
			this.oos = oos;
			this.ois = ois;
			
		}
		
		
		public void serializeObject (Object obj) {
			try {
				oos.writeObject((InteractionMessage)obj);
				oos.flush();
				
			} catch (IOException e) {
				System.err.println("Unable to send object!");
				e.printStackTrace();
			}
		}

	}


}
	
    
    
 





