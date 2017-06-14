package it.polimi.ingsw.ps29.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;

public class RMIGathererInServer extends Observable implements RmInterface, Runnable{

	private ArrayList<RMIClientThread> clients;
	// quando aggiungo player faccio notify() al roomCreator

	

	@Override
	public RMIClientThread getMyThread(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
		RMIClientThread temp= new RMIClientThread(playerName);
		if (clients.contains(temp)) {
			
			for (RMIClientThread t: clients) {
				if (t.getClientName()==playerName) {
					return t;
				}
			}
		}
		
		return addClient(playerName);
			
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 try {
			RmInterface stub = (RmInterface) UnicastRemoteObject.exportObject(this, 0);
			  // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RmInterface", stub);
		
		 } catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public RMIClientThread addClient(String Username){
		
		RMIClientThread t=new RMIClientThread(Username);
		clients.add(t);
		Thread th = new Thread(t);
		th.start();
		notifyObservers(t);
		return t;
	}

}
