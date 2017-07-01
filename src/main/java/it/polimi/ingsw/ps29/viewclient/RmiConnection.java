package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.server.RmiServerInterface;

public class RmiConnection extends Connection implements RmiClientInterface {

	/**
	 * Connection client-server through RMI
	 * @author Pietro Grotti
	 */
	private static final long serialVersionUID = 9143857205348640115L;
	String playerName;
	RmiServerInterface server;
	RmiClientInterface remoteRef;
	private PlayerInfoMessage loginMessage;
	boolean end=false;
	
	/**
	 * Sets login token, opens a new RMI connection with server( obtaining server's RmiServerInterface references and exporting its RmiClientInterface), sends its first info (name and login token).
	 * @author Pietro Grotti
	 * @param playerName
	 * @throws IOException
	 */
	public RmiConnection(String playerName) throws IOException {
	
		
		loginMessage= new PlayerInfoMessage(playerName);
		
		setLoginToken(loginMessage);
		
		
		try {
			server = (RmiServerInterface)Naming.lookup("//localhost/Server"); //Ottengo il riferimento remoto associato alla stringa passata (contiene l'host target e l'identificativo dell'oggetto sull'host).
			remoteRef = (RmiClientInterface) UnicastRemoteObject.exportObject(this, 0 ); 
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not set up ClientInterface");
			
		}		
		
				
		
	}

	@Override
	public void run() {

		try {
			
			server.addClient(remoteRef,loginMessage);
			
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add Client");
			end=true;
		}
		
		while(!end) {
			
		}
	}

	//Connection's methods//
	
	@Override
	public void sendMessage(InteractionMessage msg) {
		// TODO Auto-generated method stub
		try {
			server.messageforMyThread(loginMessage.getToken(), msg);
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not message to server");
			end=true;
			System.out.println("\nGame Over");
		
		}
	}

	
	//ClientInterface's methods//
	
	@Override
	public void notify(InteractionMessage object) throws RemoteException {
		// TODO Auto-generated method stub
		setChanged();
		notifyObservers(object);
		
	}

	@Override
	public void print(String line) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Server says: "+line);
	}

	public void setEnd() {
		end=true;
	}

	
	
}
