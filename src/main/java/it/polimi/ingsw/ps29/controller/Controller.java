package it.polimi.ingsw.ps29.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.MarketAction;
import it.polimi.ingsw.ps29.model.action.NoAction;
import it.polimi.ingsw.ps29.model.action.ProductionAction;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.action.actionstates.BonusActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.StateOfActionIdentifier;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstabilishState;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.Message;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

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
		View view = views.get(model.getBoard().getCurrentPlayer().getName());
		state = state.beforeAction();
		//modifico lo stato appena prima di interagire con la view, così da poter fare la giusta richiesta
		if(state.getState().equals(StateOfActionIdentifier.TO_ESTABILISH.toString()))
			view.askNextAction();
		else if (state.getState().equals(StateOfActionIdentifier.BONUS_ACTION.toString()))
			view.askBonusAction(((BonusActionState)state).getEffect());
		else if (state.getState().equals(StateOfActionIdentifier.ASK_EXCHANGE.toString())) {
			int index = ((AskAboutExchangeState)state).getIndexProduction();
			//sistemare get(0) nell'istruzione seguente
			view.askAboutExchange((ExchangeResourcesEffect) ((AskAboutExchangeState)state).getCards().get(index).getPermanentEffects().get(0));
		}
	}
	

	@Override
	public void update(Observable o, Object arg) {
		//la notifica può arrivare da fonti diverse: view e model
		//se arriva dalla view può riguardare azione standard, azione bonus, scelta exchange, scelta scomunica
		//se arriva dal model è una richiesta di proseguire con la richiesta dell'azione
		VisitorMessages visitor = new VisitorMessages();
		if(o instanceof Match)
			callCorrectView();
		else if (o instanceof View) {
			((Message)arg).visit(visitor);
		}
		else 
			throw new IllegalArgumentException();
	}
	
	
	public void handleInputAction (ActionChoice arg) {
	
		Action action;
		ChoiceToMove adapter = new ChoiceToMove(model.getBoard());
		Move move= adapter.createMove(arg);
		
		switch (arg.getChoices(0))	{
		
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
			action= new NoAction(model,move);
			break;
		}
		
		action.actionHandler();
		state = action.getState();
		//recupero lo stato dopo che ho eseguito le istruzioni
		
		if(state.getState().equals(StateOfActionIdentifier.PERFORMED.toString())||state.getState().equals(StateOfActionIdentifier.ASK_EXCHANGE.toString())
				||state.getState().equals(StateOfActionIdentifier.BONUS_ACTION.toString())) {
			//se ho piazzato aggiorno la board da mostrare all'utente con le informazioni relative al nuovo piazzamento
			//e al nuovo stato delle risorse (eventuali carte sono aggiunte appena vengono prelevate)				
			infoForView (arg, move);
		}
		//else viewsnotifyRejection();
	}
	
	private void infoForView (ActionChoice arg, Move move) {
		model.infoForView.gameBoard.insertFamiliar(arg, move.getPlayer().getColor());
		model.getBoard().getPlayerByName(arg.getName()).updateResourcesDTO();
		
		for(HashMap.Entry <String, View> view: views.entrySet())
			view.getValue().showBoard(model.infoForView);
		
	}
	
	
	public class VisitorMessages {
		
		public void visit (ActionChoice msg) {
			handleInputAction(msg);
		}
		
		public void visit (Exchange msg) {
			//creare metodo per lo scambio risorse
		}
		
		public void visit(BonusChoice msg){
			
		}
		
		public void visit(VaticanChoice msg){
			
		}
		
		public void visit(PrivilegeChoice msg){
			
		}
	}
	
}
