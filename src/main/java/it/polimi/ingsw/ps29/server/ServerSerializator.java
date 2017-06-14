package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class ServerSerializator {
	
	private Socket socket;
	private ObjectOutputStream oos;
	
	public ServerSerializator (Socket socket, ObjectOutputStream oos) {
		this.socket = socket;
		System.out.println("ServerSerializator: "+socket);
		this.oos = oos;
	}
	
	public void serializeObject (InteractionMessage o) {
		System.out.println("I'm server serializator of this connection: "+socket);
		try {
			oos.writeObject((InteractionMessage) o);
			oos.flush();
			
		} catch (IOException e) {
			System.err.println("Unable to send object");
		}
		
	}

}
