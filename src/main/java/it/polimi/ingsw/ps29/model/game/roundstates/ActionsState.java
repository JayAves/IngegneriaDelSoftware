package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.Match;

/**
 * Describes current round's status.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 * @see StateOfRoundIdentifier
 *
 */
public class ActionsState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.ACTIONS;
	
	@Override
	public RoundState doAction(int roundNumber, Match match) {
		
		return (roundNumber%2==0) ? new VaticanReportState(match) : new EndOfTheRoundState();
			

	}

	@Override
	public StateOfRoundIdentifier getState() {
		return state;
	}

	@Override
	public int getStateNumber() {
		return state.getStateNumber();
	}

}
