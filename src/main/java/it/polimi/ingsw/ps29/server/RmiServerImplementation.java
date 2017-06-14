package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerImplementation extends UnicastRemoteObject implements
RmInterface {

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
	
}
