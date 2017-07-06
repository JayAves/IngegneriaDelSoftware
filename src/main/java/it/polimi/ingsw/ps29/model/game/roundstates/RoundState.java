package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.Match;

/**
 * Describes current Action's status
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public interface RoundState {
	public RoundState doAction (int roundNumber, Match match);

	public StateOfRoundIdentifier getState();
	
	public int getStateNumber ();
}
