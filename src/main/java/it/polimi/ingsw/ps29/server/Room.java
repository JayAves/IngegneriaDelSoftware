package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * Thread where a single game is hosted. Contains Model, Controller and ClientThreads as Views.
 * @author Pietro Grotti
 * @author Giovanni Mele
 * @author Pietro Melzi
 *
 */
public class Room extends Thread implements Observer{
	
	private Match model;
	private Controller controller;
	private ArrayList<ClientThread> views;
	protected boolean active;
	
	public Room (ArrayList<ClientThread> playersInQueue){
		
		active=true;
		
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
		controller.addObserver(this);
    	
		for (ClientThread th: views){
			
			th.addObserver(controller);
			controller.addView(th.getClientName(), th);
			th.setInGame(true);
		}
	
		model.addObserver(controller);
    	
	}

	public Controller getController() {
		return controller;
	}

	

	public ArrayList<ClientThread> getViews() {
		return views;
	}

	@Override
	public void run() {
		controller.gameEngine();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		active=false;
	}
		
	
}	


