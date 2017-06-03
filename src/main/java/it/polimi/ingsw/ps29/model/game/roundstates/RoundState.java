package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.GameBoard;

public interface RoundState {
	public RoundState doAction (int roundNumber, GameBoard board);
}
