package it.polimi.ingsw.ps29.server;

import java.util.Observable;

import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public abstract class ClientThread extends Observable implements Runnable{
	
	public abstract void stopClient();
	
	public abstract String getClientName();

	public abstract void setInGame();
	
	public abstract void showBoard(InfoDTO infoForView);
	
	public abstract void startInteraction(InteractionMessage msg);
}
