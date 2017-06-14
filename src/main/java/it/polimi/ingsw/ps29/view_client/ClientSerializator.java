package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class ClientSerializator {
	
	private Socket socket;
	private ObjectOutputStream oos;
	
	
	
	public ClientSerializator (Socket socket, ObjectOutputStream oos) {
		this.socket = socket;
		System.out.println("ClientSerializator: "+socket);
		this.oos = oos;
		
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
