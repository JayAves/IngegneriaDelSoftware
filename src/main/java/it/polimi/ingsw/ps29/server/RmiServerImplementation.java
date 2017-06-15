package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.management.remote.rmi.RMIConnection;

import it.polimi.ingsw.ps29.view_client.RmiClientInterface;

public class RmiServerImplementation extends UnicastRemoteObject implements RmiServerInterface {

	protected RmiServerImplementation() throws RemoteException {
		super(0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RMIClientThread getMyThread(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean isMyTurn() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addClient(RmiClientInterface clientInterface) throws RemoteException {
		// TODO Auto-generated method stub
		
		
		// quando aggiungo player faccio notify() al roomCreator
	}

	@Override
	public boolean amIinGame() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
