package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.messages.Exchange;

public class ExchangeResources {
	
	private Match model;
	private ActionState state;
	
	public ExchangeResources(Match model, ActionState state) {
		super();
		this.model = model;
		this.state = state;
	}



	public ActionState exchangeHandler (Exchange msg) {
		Player current = model.getBoard().getCurrentPlayer();
		ExchangeResourcesEffect effect = msg.getExchange();
		
		//se la condizione è true ho deciso di scambiare una risorsa
		if(msg.getChoice(0)<effect.getChoices().size()) {
			ExchangeResourceHandler erh = effect.getChoices().get(msg.getChoice(0));
			//aggiorno le vere risorse del player
			erh.performExchange(current.getPersonalBoard().getResources(), 
					msg.getChoice(1), msg.getChoice(2));
			
			//devo aggiornare anche le outResourcesUpdated
			for(Resource res: erh.resOut(msg.getChoice(1)))
				current.getSupport().getOutResourcesUpdate().updateResource(res);
				current.getSupport().checkVector();
		}
		state = ((AskAboutExchangeState)state).setEffect(current.getSupport().getOptions());
		
		//dopo questo comando l'azione può essere in stato di scambio o terminata 
		//perchè cancello le opzioni rimaste
		
		state = state.afterAction(model);
		return state;
	}

}
