package it.polimi.ingsw.ps29.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.View;

public class Controller implements Observer{
	
	private Match model;
	
	public Controller (Match model) {
		this.model = model;
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
