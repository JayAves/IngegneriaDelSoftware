package it.polimi.ingsw.ps29.viewclient;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps29.messages.InteractionMessage;

public interface RmiClientInterface extends Remote,Serializable {
	
	public void notify(InteractionMessage object) throws RemoteException;
	
	public void print(String line) throws RemoteException;
	
}
