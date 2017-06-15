package it.polimi.ingsw.ps29.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.management.remote.rmi.RMIConnection;

import it.polimi.ingsw.ps29.view_client.RmiClientInterface;

public interface RmiServerInterface extends Remote{
	
	public RMIClientThread getMyThread(String playerName) throws RemoteException;
	
	public boolean isMyTurn() throws RemoteException;
	
	public void addClient(RmiClientInterface clientInterface) throws RemoteException;
	
	public boolean amIinGame() throws RemoteException;



}
