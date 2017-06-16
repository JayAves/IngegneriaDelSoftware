package it.polimi.ingsw.ps29.model.action.actionstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class AskAboutExchangeState implements ActionState {
	private ArrayList <ExchangeResourcesEffect> effect;
	private final StateOfActionIdentifier state = StateOfActionIdentifier.ASK_EXCHANGE;
	
	//indice che memorizza fino a quale effetto ho chiesto se l'utente vuole scambiare le risorse
	
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
		// aggiorna view
		return this;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return state.toString().toLowerCase();
	}

	

	
	public ActionState next () {
		return effect.isEmpty() ? new PerformedState() : this;
		
	}

	public ExchangeResourcesEffect getEffect (int index) {
		return effect.get(index);
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new Exchange(player, effect.remove(0).clone());
	}


	

}
