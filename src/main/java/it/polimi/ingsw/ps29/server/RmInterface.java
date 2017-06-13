package it.polimi.ingsw.ps29.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmInterface extends Remote{
	
	public RMIClientThread getMyThread(String playerName) throws RemoteException;
}
