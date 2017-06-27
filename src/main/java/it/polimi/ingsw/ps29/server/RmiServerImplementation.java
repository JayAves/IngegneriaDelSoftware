package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.viewclient.RmiClientInterface;

public class RmiServerImplementation extends UnicastRemoteObject implements RmiServerInterface {

	RMIGatherer myGatherer;
	int turnTimer;
	
	protected RmiServerImplementation() throws RemoteException {
		super(0);
		myGatherer= new RMIGatherer(0);
	}

	
	private static final long serialVersionUID = -7098548671967083832L;

	@Override
	public void messageforMyThread(String loginToken, InteractionMessage msg ) throws RemoteException {
		// TODO Auto-generated method stub
		
		myGatherer.getThread(loginToken).getTimeOuts().get(0).cancel();
		myGatherer.getThread(loginToken).notifyController(msg);
	}

	@Override
	public void addClient(RmiClientInterface clientInterface, PlayerInfoMessage player) throws RemoteException {
		// TODO Auto-generated method stub
		RMIClientThread thread=new RMIClientThread(player, clientInterface);
		thread.setTurnTimer(myGatherer.turnTimer);
		
		RMIClientThread toDelete= null;
		for(RMIClientThread th: myGatherer.clients) {
			if (th.IDcode.contentEquals(thread.IDcode)) { //se compare già notifico il roomCreator che dovrò allacciare alla partita giusta
				thread.setInGame(true);
				toDelete=th;
				}
		}
		
		if (toDelete!=null)
			myGatherer.clients.remove(toDelete);
		myGatherer.clients.add(thread);
		
		//Thread t= new Thread (thread);
		//t.start();
		myGatherer.notifyRoomCreator(thread);
		
		
		//clientInterface.print("I added you as a client");
		
	}

	@Override
	public boolean inGame(String loginToken) throws RemoteException {
		// TODO Auto-generated method stub
		return myGatherer.getThread(loginToken).inGame;
		
	}
	
	public void setup(RMIGatherer server) {
		myGatherer= server;
		
	}
}
