package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Player {
	private String name;
	private Color color;
	private PersonalBoard board;
	private FamilyMember[] family; 
	private FakeFamilyMemberInterface fakeFamiliar;
	private ExcommunicationCard [] excommunication;
	public ArrayList<Effect> specialPermanentEffects;
	
	public Player (String name, Color color, PersonalBonusTile pbt) {
		this.name = name;
		this.color = color;
		this.board = new PersonalBoard(pbt);
		initFamily();
		excommunication = new ExcommunicationCard [3];
		specialPermanentEffects= new ArrayList<Effect>();
	}
	
	public void initFamily () {
		family = new FamilyMember [4];
		family[0] = new FamilyMember (DiceColor.BLACK, color); 
		family[1] = new FamilyMember(DiceColor.ORANGE, color);
		family[2] = new FamilyMember(DiceColor.WHITE, color);
		family[3] = new FamilyMember(DiceColor.NEUTRAL, color);
		fakeFamiliar = new FakeFamilyMember();
		
	}
	
	public String getName() {
		return name;
	}
	
	
	public FamilyMember getFamiliarByColor (DiceColor color) {
		for (FamilyMember member: family) 
			if(member.getFamiliarColor()==color)
				return member;
		
		return null;
	}
	
	public PersonalBoard getPersonalBoard() {
		return board;
	}

	public FakeFamilyMemberInterface getFakeFamiliar() {
		return fakeFamiliar;
	}

	public void setFakeFamiliar(FakeFamilyMemberInterface fakeFamiliar) {
		this.fakeFamiliar = fakeFamiliar;
	}

	public Color getColor() {
		return color;
	}
	
	public FamilyMember [] getFamily () {
		return family;
	}
	
	public void setPersonalBonusTile (PersonalBonusTile pbt) {
		board.setTile(pbt);
	}


}
