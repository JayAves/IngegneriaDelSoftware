package it.polimi.ingsw.ps29.server;

import java.util.ArrayList;
import java.util.Observable;

public class RMIGathererInServer extends Observable {

	private ArrayList<ClientThread> clients;
	// quando aggiungo player faccio notify() al roomCreator

	public ClientThread addClient(String Username){
		
		RMIClientThread t=new RMIClientThread(Username);
		clients.add(t);
		Thread th = new Thread(t);
		th.start();
		notifyObservers(t);
		return t;
	}



}
