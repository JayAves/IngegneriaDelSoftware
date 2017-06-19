package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps29.server.RmiServerInterface;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public class RmiConnection extends Connection implements RmiClientInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143857205348640115L;
	String playerName;
	RmiServerInterface server;
	RmiClientInterface remoteRef;
	private PlayerInfoMessage loginMessage;
	
	public RmiConnection(String playerName) throws IOException {
	
		
		loginMessage= new PlayerInfoMessage(playerName);
		
		//setLoginToken();
		
		
		try {
			server = (RmiServerInterface)Naming.lookup("//localhost/Server"); //Ottengo il riferimento remoto associato alla stringa passata (contiene l'host target e l'identificativo dell'oggetto sull'host).
			remoteRef = (RmiClientInterface) UnicastRemoteObject.exportObject(this, 0 ); 
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not set up ClientInterface");
		}		
		
		/*	Tuttavia, dato che ClientImplementation non estende la classe UnicastRemoteObject, devo creare un riferimento remoto all'oggetto col metodo 
		 * UnicastRemoteObject.exportObject che prende come parametri l'oggetto da esportare e la porta dautilizzare per la connessione.
		 *  Con 0 la porta viene scelta automaticamente. Altrimenti avrebbe tentato di serializzare l'oggetto e di passarlo come copia al server. 
		 *  In questo caso non devo associare un identificativo all'oggetto (in quanto il riferimento remoto verrà passato al server																			
		*/	
		
	}

	@Override
	public void run() {

		boolean end=true;
		try {
			
			server.addClient(remoteRef,loginMessage);
			//server.addClient(remoteRef, loginMessage)
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add Client");
		}
		
		while(end) {
			
		}
	}

	//metodi di Connection//
	
	@Override
	public void sendMessage(InteractionMessage msg) {
		// TODO Auto-generated method stub
		try {
			server.messageforMyThread(loginMessage.getName(), msg);
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not message to server");
		
		}
	}

	
	//metodi di ClientInterface//
	
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

	

	
	
}
