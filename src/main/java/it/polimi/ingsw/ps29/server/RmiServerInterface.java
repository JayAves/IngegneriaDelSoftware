package it.polimi.ingsw.ps29.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.management.remote.rmi.RMIConnection;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view_client.RmiClientInterface;

public interface RmiServerInterface extends Remote{
	
	public void messageforMyThread(String playerName, InteractionMessage msg) throws RemoteException;
	
	public void addClient(RmiClientInterface clientInterface, String playerName) throws RemoteException;
	
	public boolean inGame(String playerName) throws RemoteException;



}
