package it.polimi.ingsw.ps29.model.action.actionstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * When one ore more exchanges are to be asked to some player
 * @author Pietro Melzi
 *
 */
public class AskAboutExchangeState implements ActionState {
	private ArrayList <ExchangeResourcesEffect> effect;
	private final StateOfActionIdentifier state = StateOfActionIdentifier.ASK_EXCHANGE;
	
	
	
	public AskAboutExchangeState(ArrayList<ExchangeResourcesEffect> effect) {
		this.effect = effect;
	}

	public ActionState setEffect(ArrayList<ExchangeResourcesEffect> effect) {
		this.effect = effect;
		return effect.isEmpty() ? new PerformedState() : this;
	}

	@Override
	public ActionState beforeAction() {
		return this;
	}

	

	@Override
	public ActionState afterAction(Match model) {
		//update view
		return this;
	}

	@Override
	public String getState() {

		return state.toString().toLowerCase();
	}


	public ExchangeResourcesEffect getEffect (int index) {
		return effect.get(index);
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new Exchange(player, effect.remove(0).clone());
	}


	

}
