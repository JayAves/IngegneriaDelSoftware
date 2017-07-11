package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Deals with exchanges, if there are.
 * @author Pietro Melzi
 *
 */
public class ExchangeResources {
	
	private Match model;
	private ActionState state;
	
	public ExchangeResources(Match model, ActionState state) {
		super();
		this.model = model;
		this.state = state;
	}



	public ActionState exchangeHandler (Exchange msg) {
		
		Player current = model.getBoard().getPlayerByName(msg.getName());
		ExchangeResourcesEffect effect = msg.getExchange();
		//if true it's because players chose to do exchange
		if(msg.getChoice(0)<effect.getChoices().size()) {
			ExchangeResourceHandler erh = effect.getChoices().get(msg.getChoice(0));
			//player's resources update
			erh.performExchange(current.getPersonalBoard().getResources(), 
					msg.getChoice(1), msg.getChoice(2));
			
			//need to update outResourcesUpdated, too
			if(msg.getChoice(1)==0) {
				for(Resource resOut: erh.getResOut()) {
					resOut.negativeAmount();
					current.getSupport().getOutResourcesUpdate().updateResource(resOut);
				}
				current.getSupport().checkVector();
			}
			else 
				for(Resource res: erh.resOut(msg.getChoice(1))) {
					current.getSupport().getOutResourcesUpdate().updateResource(res);
				current.getSupport().checkVector();
				}
		}
		
		//setEffect return performed state if option is empty
		state = ((AskAboutExchangeState)state).setEffect(current.getSupport().getOptions());
		
		return state;
	}

}
