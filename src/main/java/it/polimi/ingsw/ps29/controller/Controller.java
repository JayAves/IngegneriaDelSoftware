package it.polimi.ingsw.ps29.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.MarketAction;
import it.polimi.ingsw.ps29.model.action.ProductionAction;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
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
		Action action;
		
		if(!(o instanceof View) || !(arg instanceof UserChoice)) {
			throw new IllegalArgumentException();
		}
		
		ChoiceToMove adapter = new ChoiceToMove(model.getBoard());
		Move move= adapter.createMove((UserChoice)arg);
		
		
		switch (((UserChoice)arg).getChoices(0))	{
		
		case 1:
			action= new HarvestAction(model, move);
			break;
		
		case 2:
			action= new ProductionAction(model, move);
			break;
		
		case 3:
		case 4:
		case 5:
		case 6:
			action= new TowerAction(model, move);
			break;
		
		case 7:
		case 8:
		case 9:
		case 10:
			action= new MarketAction(model, move);
			break;
		
		case 11:
			action= new CouncilPalaceAction(model, move);
			break;
		
		default:
			action=null;
			break;
		}
		
		
			if (action!=null) {
				action.actionHandler();
			}

		
		
		
	}
	
	
}
