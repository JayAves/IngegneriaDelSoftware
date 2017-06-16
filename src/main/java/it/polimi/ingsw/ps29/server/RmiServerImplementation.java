package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view_client.RmiClientInterface;

public class RmiServerImplementation extends UnicastRemoteObject implements RmiServerInterface {

	RMIGatherer myGatherer;
	
	protected RmiServerImplementation() throws RemoteException {
		super(0);
		// TODO Auto-generated constructor stub
	}

	
	private static final long serialVersionUID = 1L;

	@Override
	public void messageforMyThread(String playerName, InteractionMessage msg ) throws RemoteException {
		// TODO Auto-generated method stub
		myGatherer.getThread(playerName).notifyController(msg);
	}

	@Override
	public void addClient(RmiClientInterface clientInterface, String player) throws RemoteException {
		// TODO Auto-generated method stub
		RMIClientThread thread=new RMIClientThread(player, clientInterface);
		myGatherer.clients.add(thread);
		Thread t= new Thread (thread);
		t.start();
		myGatherer.notifyObservers(thread);
		clientInterface.print("I added you as a client");
		
	}

	@Override
	public boolean inGame(String player) throws RemoteException {
		// TODO Auto-generated method stub
		return myGatherer.getThread(player).inGame;
		
	}
	
	public void setGatherer(RMIGatherer server) {
		myGatherer= server;
	}
}
