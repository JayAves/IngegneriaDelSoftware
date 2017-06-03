package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Colored implements PlayerState{
	
    @Override
    public boolean checkPlayerFamiliarsInThisSpace(FamilyMember member){
         if (member.getFamiliarColor() != DiceColor.NEUTRAL) {
        	 return false;
         }
         else{
        	 return true;
         }
     }

    @Override
    public PlayerState setNewState(FamilyMember member){
         return new Full();
        }

}
