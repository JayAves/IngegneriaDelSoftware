package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;


public class Room extends Thread{
	
	Match model;
	Controller controller;
	ArrayList<ClientThread> sockets;
	
	public Room (ArrayList<ClientThread> playersInQueue){
		
		ArrayList<String> names = new ArrayList<String>();
		
		for (ClientThread th: playersInQueue){
			
			names.add(th.getClientName());
		}
		
		
		
		try {
			model= new Match(names);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println();
		}
		
		controller = new Controller (model);
    	
		for (ClientThread th: playersInQueue){
			
			th.addObserver(controller);
			controller.addView(th, th.getClientName());
			th.setInGame();
			//devo notificare il client che è in una partita
			
			if (th instanceof SocketClientThread) {
				
				try {
					((SocketClientThread) th).gameIsStarted();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		model.addObserver(controller);
    	
    	model.gameEngine();
		
		
		}

}	


