package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Free implements PlayerState{
	
	 @Override
	    public void checkPlayerFamiliarsInThisSpace(FamilyMember member){
	        System.out.println(" OK non c'è nessun tuo familiare su questo spazio");
	    }

	    @Override
	    public PlayerState setNewState(){
	         // in base al familiar torna o neutral o colored
	    	return null;
	        }

}
