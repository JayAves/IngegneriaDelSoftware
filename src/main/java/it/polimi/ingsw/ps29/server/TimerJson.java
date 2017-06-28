package it.polimi.ingsw.ps29.server;

public class TimerJson {
    	
	protected int actionTimer;
	protected int roomTimer;
	
	public TimerJson(int turn, int room) {
		actionTimer=turn;
		roomTimer=room;
	}
	
	public int getTurnTimer() {
		return actionTimer;
	}
	
	public int getRoomTimer() {
		return roomTimer;
	}
	
}
