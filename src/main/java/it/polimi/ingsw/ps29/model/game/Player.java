package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Player {
	private String name;
	private Color color;
	private PersonalBoard board;
	private FamilyMember[] family; //familymember del package (nuovo) familymember
	private ExcommunicationCard [] excommunication;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public FamilyMember getFamiliarByColor (DiceColor color) {
		for (FamilyMember member: family) {
			if(member.getFamiliarColor()==color)
				return member;
		}
		return null;
	}
	
	
	

}
