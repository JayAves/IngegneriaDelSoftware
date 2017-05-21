package it.polimi.ingsw.ps29.model.game;

import java.util.Observable;

public class Match extends Observable {
	
	private static int id = 1;
	private GameBoard board;
	
	public Match () {
		id++;
		board = new GameBoard();
	}

	public GameBoard getBoard() {
		return board;
	}
	
	public GameBoard getBoardForView () {
		return board.clone ();
	}
	
	public void informView () {
		setChanged();
		notifyObservers(getBoardForView());
	}
	
	
}
