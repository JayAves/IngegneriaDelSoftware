package it.polimi.ingsw.ps29.server;

public class TimerJson {
    	
	protected int turnTimer;
	protected int roomTimer;
	
	public TimerJson(int turn, int room) {
		turnTimer=turn;
		roomTimer=room;
	}
	
	public int getTurnTimer() {
		return turnTimer;
	}
	
	public int getRoomTimer() {
		return roomTimer;
	}
	
}
