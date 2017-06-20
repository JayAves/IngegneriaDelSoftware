package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;

public class ExcommunicationCard {
	
	public final Effect effect;
	public final Period period;
	private final int id;

	public ExcommunicationCard(int id, Effect effect, Period period) {
		this.id = id;
		this.effect = effect;
		this.period=period;
	}
	
	@Override
	public String toString(){
		return "Excommunication for period : " + period + " effect : " + effect;
	}
	
}
