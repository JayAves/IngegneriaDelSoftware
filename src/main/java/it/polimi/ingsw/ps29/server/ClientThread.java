package it.polimi.ingsw.ps29.server;

import java.util.Observable;

import it.polimi.ingsw.ps29.messages.InteractionMessage;

/**
 * Acts with Controller as a View (has methods with the exact same names as View's ones Controller calls), 
 * but actually its role is to forward any message received to its Connection, wait for a response and send it to Controller.
 *  Also contains timer for server-side correct app behavior
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @see Controller
 * @see View
 *
 */

public abstract class ClientThread extends Observable implements Runnable{
	
	protected String IDcode;
	protected boolean inGame;
	protected int actionTimer;
	
	
	
	protected abstract void stopClient();
	
	public abstract String getClientName();

	protected abstract void setInGame(boolean change);
	
	public abstract void startInteraction(InteractionMessage msg);
	
	public boolean getInGame() {
		return inGame;
	}
	
	public void setTurnTimer(int timer) {
		actionTimer=timer;
	}
	
	public abstract void restoreSituation();
}
