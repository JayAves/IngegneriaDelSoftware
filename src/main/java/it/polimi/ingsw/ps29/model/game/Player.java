package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;

public class Player {
	private String name;
	public Colour colour;
	private PersonalBoard board;
	private FamilyMember[] family;
	private ExcommunicationCard [] excommunication;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
