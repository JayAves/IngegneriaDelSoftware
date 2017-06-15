package it.polimi.ingsw.ps29.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Observable;

public class RMIGathererInServer extends Observable implements Runnable{

	protected ArrayList<RMIClientThread> clients;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1099);//Creo un registy sulla porta 1099 (quella di default).
		} catch (RemoteException e) {
			System.out.println("Registry già presente!");			
		}
		try {
			RmiServerImplementation serverImplementation = new RmiServerImplementation();//Creo l'oggetto da esportare normalmente (in quanto la classe ServerImplementation estende UnicastRemoteObject)			
			Naming.rebind("Server", serverImplementation);//Aggiungo al registry l'associazione dell'oggetto serverImplementation con "//localhost/Server".																	  
		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}
	}



}
