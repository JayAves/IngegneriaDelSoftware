package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;

public class ExcommunicationCard {
	
	public final Effect effect;
	public final Period period;

	public ExcommunicationCard(Effect effect, Period period) {
		this.effect = effect;
		this.period=period;
	}
	
	
}
