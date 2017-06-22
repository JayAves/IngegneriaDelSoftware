package it.polimi.ingsw.ps29.server;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Observable;

public class RMIGatherer extends Observable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3298080639785808439L;
	
	protected ArrayList<RMIClientThread> clients;
	
	public void startServer() {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1099);//Creo un registy sulla porta 1099 (quella di default).
		} catch (RemoteException e) {
			System.out.println("Registry già presente!");			
		}
		
		try {
			clients= new ArrayList<RMIClientThread>();
			RmiServerImplementation serverImplementation = new RmiServerImplementation();
			serverImplementation.setGatherer(this);
			Naming.rebind("Server", serverImplementation);																	  
		
		} catch (MalformedURLException e) {
			
			System.err.println("Could not register specified object");
		
		} catch (RemoteException e) {
			
			System.err.println("Connection error");
		}
		
		System.out.println("RMI is up on registry 1099");
	}

	public RMIClientThread getThread(String loginToken) {
		
		for (RMIClientThread th: clients) {
			if (th.IDcode.contentEquals(loginToken))
				return th;
		}
		
		return null;
	}
	public void notifyRoomCreator(RMIClientThread thread) {
		setChanged();
		notifyObservers(thread);
	}
}
