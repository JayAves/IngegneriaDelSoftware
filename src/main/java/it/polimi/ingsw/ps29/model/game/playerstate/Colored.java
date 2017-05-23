package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Colored implements PlayerState{
	

	/*
	 * da implementare metodo colortToString();
	 */
	
    @Override
    public void checkPlayerFamiliarsInThisSpace(FamilyMember member){
         if (member.colorToString() != "neutral") throws ColoredException;
     }

    @Override
    public PlayerState setNewState(){
         return new Full();
        }

}
