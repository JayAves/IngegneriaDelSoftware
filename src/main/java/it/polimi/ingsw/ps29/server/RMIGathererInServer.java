package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIGathererInServer extends UnicastRemoteObject implements ServerRMI {

	protected RMIGathererInServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
