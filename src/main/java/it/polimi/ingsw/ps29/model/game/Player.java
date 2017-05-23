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
