package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.messages.exception.FamiliarHereException;
import it.polimi.ingsw.ps29.messages.exception.NotEnoughPowerfulException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Spaces where FamilyMembers can be placed.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public interface ActionSpace {
	
	abstract boolean isEmpty() throws SpaceOccupiedException;
	
	abstract boolean familiarHere (PlayerColor playerColor) throws FamiliarHereException;
	
	abstract boolean isEnoughPowerful (int valuePlacement) throws NotEnoughPowerfulException;
	
	abstract void cleanSpace();
	
	abstract void placeFamiliar (FamilyMemberInterface familiar, boolean ludovicoAriosto);
	
}
