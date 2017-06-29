package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;

public class SocketClientThread extends ClientThread {
	
	private Socket socket;
	private String playerName;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerSerializator serializator;
	private boolean endOfConnection= false;
	private boolean newConnection=false;
	
	
	public SocketClientThread(Socket socket, PlayerInfoMessage playerLogin, ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket = socket;
		this.playerName = playerLogin.getName();
		this.oos = oos;
		System.out.println(playerName);
		
		this.ois = ois;
		IDcode= playerLogin.getToken();
		inGame=false;
		
		//turnTimer= TimerJson.turnTimer;
		
		serializator = new ServerSerializator(this,socket, this.oos, this.ois);
	}
	

	@Override
	public void run() {
		Object obj;
		
		while(!endOfConnection) {
			try{
				do {
					obj = ois.readObject();
				} while (obj==null);
				
				System.out.println("Server: msg received by "+playerName+":\n"+obj.toString()+"\n");
				
				//ha ricevuto un messaggio dal client: cancella il timer per l'azione
				serializator.beeperHandle.cancel(true);
				
				setChanged();
				notifyObservers(obj);
				
				
			} catch (IOException e) {
				
				System.err.println("Unable to receive message from client!");
				endOfThis();
				
				
			
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to cast the object");
				endOfThis();
				}
		}
		
		while (!newConnection) {
			
		}
		
	}
	
	public String getName () {
		return playerName;
	}
    
    
    @Override
    public void setInGame(boolean change){
    	inGame=change;
    }

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return playerName;
	}


	@Override
	public void startInteraction(InteractionMessage msg) {
		if (inGame) {
			serializator.serializeObject(msg);
	
		}
	}


	@Override
	protected void stopClient() {
		// TODO Auto-generated method stub
		endOfConnection=true;
		newConnection=true;
	}
	
	public void endOfThis() {
		
		//se il giocatore si disconnette cancello il timer eventuale
		if(serializator.beeperHandle != null)
			serializator.beeperHandle.cancel(false);
		
		inGame=false;
		PlayerInfoMessage msg= new PlayerInfoMessage(playerName);
		endOfConnection=true;
		setChanged();
		notifyObservers(msg);
	}

	public void notifyController(InteractionMessage msg) {
		setChanged();
		notifyObservers(msg);
	}
	


	
}

