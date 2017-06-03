package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Player {
	private String name;
	private Color color;
	private PersonalBoard board;
	private FamilyMember[] family; //familymember del package (nuovo) familymember
	private FakeFamilyMemberInterface fakeFamiliar; //test per l'effetto empowerment, sarà da cancellare
	private ExcommunicationCard [] excommunication;
	public ArrayList<Effect> specialPermanentEffects;
	
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
		specialPermanentEffects= new ArrayList<Effect>();
		//initializeStates();
	}
	
	public String getName() {
		return name;
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

	/*
	 FamilyMember neutralFamilyMember;
     FamilyMember orangeFamilyMember;
     FamilyMember blackFamilyMember;
     FamilyMember whiteFamilyMember;
     */

     /*private PlayerState HarvestState;
     private PlayerState ProductionState;
     private PlayerState CTowerState;
     private PlayerState HTowerState;
     private PlayerState PTowerState;
     private PlayerState VTowerState;
     
     public void initializeStates() {
    	 HarvestState = new Free();
    	 ProductionState = new Free();
    	 CTowerState = new Free();
    	 HTowerState = new Free();
    	 PTowerState = new Free();
    	 VTowerState = new Free();
     }
     
     
     
     
     /*
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
