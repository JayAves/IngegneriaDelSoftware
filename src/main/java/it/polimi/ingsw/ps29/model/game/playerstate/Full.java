package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Full implements PlayerState{
	

	@Override
    public boolean checkPlayerFamiliarsInThisSpace(FamilyMember member){
          return false;
     }
  

    @Override
    public PlayerState setNewState(FamilyMember member){
         return new Full();
        }

}
