package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;

public class ExcommunicationCard {
	
	private final Effect effect;
	private final Period period;
	private final int id;
	
	public Period getPeriod() {
		return this.period;
	}

	public ExcommunicationCard(int id, Effect effect, Period period) {
		this.id = id;
		this.effect = effect;
		this.period=period;
	}
	
	@Override
	public String toString(){
		return "Excommunication for period : " + period + " effect : " + effect;
	}
	
	public int getId () {
		return id;
	}
	
	public Effect getEffect(){
		return effect;
	}
	
}
