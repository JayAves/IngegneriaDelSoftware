package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class BonusActionState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.BONUS_ACTION;
	private BonusActionEffect effect;
	//memorizzo l'effetto su cui c'è il valore del dado e il tipo di posizionamento
	
	public BonusActionState(BonusActionEffect effect) {
		this.effect = effect;
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
		return state.toString();
	}
	
	public BonusActionEffect getEffect () {
		return effect;
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new BonusChoice(((BonusActionEffect)effect).clone(), player);
	}

}
