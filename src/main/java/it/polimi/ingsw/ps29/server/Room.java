package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;


public class Room extends Thread{
	
	private Match model;
	private Controller controller;
	private ArrayList<ClientThread> views;
	
	public Room (ArrayList<ClientThread> playersInQueue){
		
		views= new ArrayList<ClientThread>();
		for (ClientThread th: playersInQueue) {
			views.add(th);
		}
				
		ArrayList<String> names = new ArrayList<String>();
		for (ClientThread th: playersInQueue)
			names.add(th.getClientName());	
		
		
		try {
			
			model= new Match(names);
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println();
		}
		
		controller = new Controller (model);
    	
		for (ClientThread th: views){
			
			th.addObserver(controller);
			controller.addView(th.getClientName(), th);
			th.setInGame(true);
		}
	
		model.addObserver(controller);
    	
    	controller.gameEngine();
		
		
		}

	public Controller getController() {
		return controller;
	}

	

	public ArrayList<ClientThread> getViews() {
		return views;
	}

	
		
	
}	


