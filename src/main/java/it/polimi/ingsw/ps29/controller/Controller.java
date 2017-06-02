package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
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
import it.polimi.ingsw.ps29.model.action.state.ActionState;
import it.polimi.ingsw.ps29.model.action.state.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.action.state.ToEstabilishState;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.view.UserChoice;
import it.polimi.ingsw.ps29.view.View;

public class Controller implements Observer{
	
	private Match model;
	private Map<String, View> views = new HashMap <String, View> ();
	private ActionState state; 
	//è lo stato dell'azione, inizialmente da stabilire, viene recuperato dopo che ho svolto l'azione
	//lo utilizzo per la corretta interazione con la view
	
	public Controller (Match model) {
		this.model = model;
		state = new ToEstabilishState();
	}
	
	public void addView (View view, String playerName) {
		if(!views.containsKey(playerName))
			views.put(playerName, view);
	}
	
	public void callCorrectView () {
		View view = views.get(model.getBoard().getPlayers().get(0).getName());
		state.beforeAction();
		//modifico lo stato appena prima di interagire con la view, così da poter fare la giusta richiesta
		if(state.getState().equals("to estabilish"))
			view.askNextAction();
		else if (state.getState().equals("bonus action"))
			view.askBonusAction();
		else if (state.getState().equals("ask exchange")) {
			int index = ((AskAboutExchangeState)state).getIndexProduction();
			ArrayList<Card> cards = model.getBoard().getPlayers().get(0).getPersonalBoard().getCards("building");
			//creare una clone della carta da passare alla view
			view.askAboutExchange(cards.get(index));
		}
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
		
		action.actionHandler();
		state = action.getState();
		//recupero lo stato dopo che ho eseguito le istruzioni
		
			if (action!=null) {
				action.actionHandler();
			}

		
		
		
	}
	
	
}
