package it.polimi.ingsw.ps29.model.game.playerstate;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public interface PlayerState {
	
	public void checkPlayerFamiliarsInThisSpace(FamilyMember member);

    public PlayerState setNewState();

}
