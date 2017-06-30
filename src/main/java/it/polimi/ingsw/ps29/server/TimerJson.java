package it.polimi.ingsw.ps29.server;

/**
 * Contains all timers required for the App
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */

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
