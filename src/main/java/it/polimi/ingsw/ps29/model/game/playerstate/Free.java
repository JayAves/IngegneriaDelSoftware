package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Free implements PlayerState{
	
	 @Override
	    public boolean checkPlayerFamiliarsInThisSpace(FamilyMember member){
	        System.out.println(" OK non c'è nessun tuo familiare qui");
	        return true;
	    }

	    @Override
	    public PlayerState setNewState(FamilyMember member){
	    	// in base al familiar torna o neutral o colored
	    	if (member.getFamiliarColor().compareTo(DiceColor.NEUTRAL)== 0){
	    		return new Neutral();
	    	}
	    		else{
	    			return new Colored();
	    	}
	    }
	    
}
	         


