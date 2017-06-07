package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.Match;

public interface RoundState {
	public RoundState doAction (int roundNumber, Match match);
}
