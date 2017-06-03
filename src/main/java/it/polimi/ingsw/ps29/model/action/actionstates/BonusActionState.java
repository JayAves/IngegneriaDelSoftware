package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.Match;

public class BonusActionState implements ActionState {
	private BonusActionEffect effect;
	//memorizzo l'effetto su cui c'è il valore del dado e il tipo di posizionamento
	
	public BonusActionState(BonusActionEffect effect) {
		this.effect = effect;
	}

	@Override
	public ActionState beforeAction() {
		// TODO Auto-generated method stub
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
		return "bonus action";
	}
	
	public BonusActionEffect getEffect () {
		return effect;
	}

}
