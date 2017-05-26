package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberDecorator;

public class Player {
	private String name;
	private Color color;
	private PersonalBoard board;
	private FamilyMember[] family; //familymember del package (nuovo) familymember
	public FamilyMemberDecorator fakeFamiliar; //test per l'effetto empowerment, sarà da cancellare
	private ExcommunicationCard [] excommunication;
	
	public Player (String name, Color color, PersonalBonusTile pbt) {
		this.name = name;
		this.color = color;
		this.board = new PersonalBoard(pbt);
		family = new FamilyMember [4];
		family[0] = new FamilyMember (DiceColor.BLACK, color); 
		family[1] = new FamilyMember(DiceColor.ORANGE, color);
		family[2] = new FamilyMember(DiceColor.WHITE, color);
		family[3] = new FamilyMember(DiceColor.NEUTRAL, color);
		excommunication = new ExcommunicationCard [3];
	}
	
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
	
	public PersonalBoard getPersonalBoard() {
		return board;
	}
	
	/*
	 FamilyMember neutralFamilyMember;
     FamilyMember orangeFamilyMember;
     FamilyMember blackFamilyMember;
     FamilyMember whiteFamilyMember;

     private playerState HarvestState;
     private playerState ProductionState;
     private playerState CTowerState;
     private playerState HTowerState;
     private playerState PTowerState;
     private playerState VTowerState;

     public void checkPlayersFamiliarsInThisSpace(){

     public FamilyMember getSelectedFamilyMember(String member) {
            
            switch (member){
                case "neutral" return neutralFamilyMember;
                               break;
                case "orange"  return orangeFamilyMember; 
                               break;
                case "black"   return balckFamilyMember;
                               break;
                case "white"   return whiteFamilyMember;
                               break;
                               
    }


    public void resetStates() {
    
        HarvestState = new Free();
        ProductionState = new Free();
        CTowerState = new Free();
        HTowerState = newFree();
        
        }
        
	 */
	

}
