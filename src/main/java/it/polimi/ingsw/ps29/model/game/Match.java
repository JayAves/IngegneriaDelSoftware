package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.Observable;

public class Match extends Observable {
	
	private static int id = 1;
	private GameBoard board;
	private Period period;
	private int round;
	
	public Match (ArrayList<Player> players) {
		id++;
		board = new GameBoard(players);
		setPeriod(Period.FIRST);
		setRound(1);
	}

	public GameBoard getBoard() {
		return board;
	}
	
	/*
	public GameBoard getBoardForView () {
		return board.clone ();
	}
	*/
	public void informView () {
		setChanged();
		notifyObservers(/*getBoardForView()*/);
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public static int getId() {
		return id;
	}

	
	
}
