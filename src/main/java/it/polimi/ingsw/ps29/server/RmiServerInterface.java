package it.polimi.ingsw.ps29.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

/**
 * Rmi Interface clients connects to
 * @author Pietro Grotti
 *
 */

public interface RmiServerInterface extends Remote{
	
	public void messageforMyThread(String playerName, InteractionMessage msg) throws RemoteException;
	
	public void addClient(RmiClientInterface clientInterface, PlayerInfoMessage loginInfo) throws RemoteException;
	
	



}
