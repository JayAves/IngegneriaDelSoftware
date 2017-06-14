package it.polimi.ingsw.ps29.view_client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiClientInterface extends Remote {
	
	public void notify(String object) throws RemoteException;
	
}
