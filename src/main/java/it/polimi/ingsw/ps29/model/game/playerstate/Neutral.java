package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Neutral implements PlayerState{
	
	@Override
    public void checkPlayerFamiliarsInThisSpace(FamilyMember member){
         if (member.colorToString() == "neutral") throw NeutralException;
     }

    @Override
    public PlayerState setNewState(){
         return new Full();
        }


}
