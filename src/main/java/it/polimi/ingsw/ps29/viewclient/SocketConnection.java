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

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public class SocketConnection extends Connection {
	
	  
	private final int PORT = 9001;
	private final String ADDRESS = "localhost";
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ClientSerializator serializator;
	private PlayerInfoMessage loginMessage;
	
	
	public SocketConnection(String playerName) {
		
		loginMessage= new PlayerInfoMessage(playerName);
		
		try {
			socket = new Socket (ADDRESS, PORT);
			//System.out.println("SocketConnection: "+socket);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			//utilizzo questo oggetto per l'invio di oggetti in rete
			serializator = new ClientSerializator(socket, oos, ois);
			
			
			
			setLoginToken(loginMessage);
			
			//invio al server il nome del client
			oos.writeObject(loginMessage);
			oos.flush();
			
			//oos.writeObject(loginMessage);
			//oos.flush();
			
			//System.out.println("Client succesfully created!");
			
			
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
			Object obj;
			try {
				//notifico Client
				do {
					obj = ois.readObject();
				} while (obj==null);
				
				setChanged();
				notifyObservers(obj);
				
			} catch (IOException e) {
				System.err.println("Unable to receive message from server!");
				
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object!");
				
			}
		}
		
	}


	@Override
	public void sendMessage(InteractionMessage msg) {
		serializator.serializeObject(msg);
	}
    
    
		
	


}
	
    
    
 





