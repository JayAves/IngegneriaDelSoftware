package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class ServerSerializator {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ServerSerializator (Socket socket, ObjectOutputStream oos, ObjectInputStream ooi) {
		this.socket = socket;
		System.out.println("ServerSerializator: "+socket);
		this.oos = oos;
		this.ois = ois;
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
