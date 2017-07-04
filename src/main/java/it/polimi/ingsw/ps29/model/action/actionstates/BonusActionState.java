package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * When a Bonus Action has to be done.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class BonusActionState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.BONUS_ACTION;
	private BonusActionEffect effect;
	//all info needed are into this Effect object
	
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
		return state.toString().toLowerCase();
	}
	
	public BonusActionEffect getEffect () {
		return effect;
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new BonusChoice(((BonusActionEffect)effect).clone(), player);
	}

}
