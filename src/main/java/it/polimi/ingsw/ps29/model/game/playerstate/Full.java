package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Full implements PlayerState{
	

	@Override
    public void checkPlayerFamiliarsInThisSpace(FamilyMember member){
          //lancia una FullException
     }
  

    @Override
    public PlayerState setNewState(){
         return new Full();
        }

}
