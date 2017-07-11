package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
			System.out.println("Could not set loginMessage");
		}
		
		try {
			socket = new Socket (ADDRESS, PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			//utilizzo questo oggetto per l'invio di oggetti in rete
			serializator = new ClientSerializator(socket, oos, ois);
			
			//invio al server nome e token del client
			oos.writeObject(loginMessage);
			oos.flush();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}

		} catch (UnknownHostException e) {
			System.err.println("Unknown address!");
			Thread.currentThread().interrupt();
			
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
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			Thread.currentThread().interrupt();
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
				Thread.currentThread().interrupt();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}

	}


}
	
    
    
 





