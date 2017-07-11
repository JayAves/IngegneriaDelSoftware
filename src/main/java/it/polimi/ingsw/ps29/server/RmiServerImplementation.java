package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

/**
 * Implementation of RmiServerInterface
 * @author Pietro Grotti
 *
 */
public class RmiServerImplementation extends UnicastRemoteObject implements RmiServerInterface {

	RMIGatherer myGatherer;
	int actionTimer;
	
	protected RmiServerImplementation() throws RemoteException {
		super(0);
		myGatherer= new RMIGatherer(0);
	}

	
	private static final long serialVersionUID = -7098548671967083832L;

	/**
	 * With the given loginToken extracts the correct RMIClientThread, resets any timer in it adn makes it send a message to Controller 
	 */
	@Override
	public void messageforMyThread(String loginToken, InteractionMessage msg ) throws RemoteException {
		// TODO Auto-generated method stub
		
		myGatherer.getThread(loginToken).beeperHandle.cancel(true);
		myGatherer.getThread(loginToken).notifyController(msg);
	}

	/**
	 * Procedure of adding a new client
	 */
	@Override
	public void addClient(RmiClientInterface clientInterface, PlayerInfoMessage player) throws RemoteException {
		// TODO Auto-generated method stub
		RMIClientThread thread=new RMIClientThread(player, clientInterface);
		thread.setTurnTimer(myGatherer.actionTimer);
		
		RMIClientThread toDelete= null;
		for(RMIClientThread th: myGatherer.clients) {
			if (th.IDcode.contentEquals(thread.IDcode)) { //if finds that client is already in a game
				thread.setInGame(true);
				toDelete=th;
				}
		}
		
		if (toDelete!=null)
			myGatherer.clients.remove(toDelete); //Get rid of player's old ClientThread 
		myGatherer.clients.add(thread);
		Thread t = new Thread (thread);
		t.start();
		myGatherer.notifyRoomCreator(thread);

	}

	public void setup(RMIGatherer server) {
		myGatherer= server;
		
	}
}
