package it.polimi.ingsw.ps29.server;

import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public abstract class ClientThread extends Observable implements Runnable{
	
	protected String IDcode;
	protected boolean inGame;
	protected static int turnTimer= 100000;
	protected boolean msgBack;
	
	
	protected abstract void stopClient();
	
	public abstract String getClientName();

	protected abstract void setInGame(boolean change);
	
	public abstract void startInteraction(InteractionMessage msg);
	
	public boolean getInGame() {
		return inGame;
	}
	
}
