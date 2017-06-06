package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class RMIGathererInServer extends Observable  {

	protected RMIGathererInServer() {
		super();
		// TODO Auto-generated constructor stub
	}
	// quando aggiungo player faccio notify() al roomCreator
}
