package it.polimi.ingsw.ps29.model.game;

public class Match {
	
	private static int id;
	private GameBoard board;
	
	public Match () {
		id++;
		board = new GameBoard();
	}

	public GameBoard getBoard() {
		return board;
	}
}
