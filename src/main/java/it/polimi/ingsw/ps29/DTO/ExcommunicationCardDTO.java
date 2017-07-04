package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

import it.polimi.ingsw.ps29.model.game.Period;

/**
 * DTO implementation of object ExcommunicationCard
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.cards.ExcommunicationCard
 *
 */
public class ExcommunicationCardDTO implements Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 3700954837686083468L;
	int id;
	Period period;
	String effectToString;
	
	
	public ExcommunicationCardDTO(int id, Period period, String effectToString) {
		super();
		this.id = id;
		this.period = period;
		this.effectToString = effectToString;
	}
	
	@Override
	public String toString () {
		return effectToString;
	}
	
	public int getId () {
		return id;
	}
	
	public Period getPeriod () {
		return period;
	}
}
