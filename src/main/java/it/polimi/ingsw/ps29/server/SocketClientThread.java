package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class SocketClientThread extends ClientThread {
	
	private Socket socket;
	private String playerName;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerSerializator serializator;
	
	public SocketClientThread(Socket socket, String playerName, ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket = socket;
		System.out.println("SocketVirtualView: "+socket);
		this.playerName = playerName;
		this.oos = oos;
		System.out.println(playerName);
		this.ois = ois;
		
		serializator = new ServerSerializator(socket, this.oos, this.ois);
	}
	

	@Override
	public void run() {
		Object obj;
		boolean endOfConnection= false;
		while(!endOfConnection) {
			try{
				obj = ois.readObject();
				System.out.println("Server: msg received by "+playerName+":\n"+obj.toString()+"\n");
				
				//notifico Controller
				setChanged();
				notifyObservers(obj);
				
			} catch (IOException e) {
				System.err.println("Unable to receive message from client!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object");
				e.printStackTrace();
			}
		}
		
	}
	
	public String getName () {
		return playerName;
	}
    
    
    @Override
    public void setInGame(){
    	inGame=true;
    }

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return playerName;
	}


	@Override
	public void startInteraction(InteractionMessage msg) {
		serializator.serializeObject(msg);
		//dopo x secondi verifico se ho ricevuto risposta
	}
	
	


	@Override
	public void stopClient() {
		// TODO Auto-generated method stub
		
	}


	public void gameIsStarted() {
		// TODO Auto-generated method stub
		
	}
}

