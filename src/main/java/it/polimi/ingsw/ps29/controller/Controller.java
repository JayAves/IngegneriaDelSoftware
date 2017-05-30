package it.polimi.ingsw.ps29.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.view.UserChoice;
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
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(!(o instanceof View)) {
			throw new IllegalArgumentException();
		}
		
		//switch con arg[0] che lancia la giusta action
		//ChoiceToMove prova = new ChoiceToMove (model.getBoard());
		//Move move = prova.createMove(arg[0]);
		//strategy per le action con new Action(model, move)
	}
	
	
}
