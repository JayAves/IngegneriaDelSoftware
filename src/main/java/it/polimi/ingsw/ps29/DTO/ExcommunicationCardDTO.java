package it.polimi.ingsw.ps29.DTO;

import it.polimi.ingsw.ps29.model.game.Period;

public class ExcommunicationCardDTO {
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
}
