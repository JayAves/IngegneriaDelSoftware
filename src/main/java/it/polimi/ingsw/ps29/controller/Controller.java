package it.polimi.ingsw.ps29.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.View;

public class Controller implements Observer{
	
	private Match model;
	private Map<String, View> views = new HashMap <String, View> ();
	
	public Controller (Match model) {
		this.model = model;
	}
	
	public void addView (View view, String playerName) {
		if(!views.containsKey(playerName))
			views.put(playerName, view);
	}
	
	public void callCorrectView () {
		views.get(model.getBoard().getPlayers().get(0).getName()).askNextAction();
	}
	
	//quando richiama l'action dovrà passare il model e l'oggetto move 

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(!(o instanceof View)) {
			throw new IllegalArgumentException();
		}
	
	}

}
