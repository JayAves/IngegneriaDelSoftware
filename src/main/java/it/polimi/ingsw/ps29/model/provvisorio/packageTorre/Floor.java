package it.polimi.ingsw.ps29.model.provvisorio.packageTorre;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.provvisorio.packageSpazio.StdActionSpace;

public class Floor {
	
	Card card;
	StdActionSpace space;
	
	public void setEmptyCard(){
		//card = null;
	}
	
	public Card isFamilyMemberPlaceable(FamilyMember dude){
		Card returnedCard = null;
		space.returnStatus();
		if (dude.getTowerPower() < space.getPowerRequired()){
			space.setFamilyMember(dude);
		    returnedCard = card;			
		    setEmptyCard();
		}
			//else lancia eccezione
		return returnedCard;
	}

}
