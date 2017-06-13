package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;


public class Room extends Thread{
	
	Match model;
	Controller controller;
	ArrayList<ClientThread> sockets;
	
	public Room (ArrayList<ClientThread> playersInQueue) throws FileNotFoundException{
		
		ArrayList<String> names = new ArrayList<String>();
		
		for (ClientThread th: playersInQueue){
			
			names.add(th.getClientName());
		}
		
		
		
		model= new Match(names);
		
		controller = new Controller (model);
    	
		for (ClientThread th: playersInQueue){
			
			th.addObserver(controller);
			controller.addView(th, th.getClientName());
			th.setInGame();
			//devo notificare il client che è in una partita
		
		}
	
		model.addObserver(controller);
    	
    	model.gameEngine();
		
		
		}

}	


