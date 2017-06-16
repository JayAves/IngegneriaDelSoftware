package it.polimi.ingsw.ps29.server;

import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public abstract class ClientThread extends Observable implements Runnable{
	
	protected boolean inGame;
	
	public abstract void stopClient();
	
	public abstract String getClientName();

	public abstract void setInGame();
	
	public abstract void startInteraction(InteractionMessage msg);
}
