package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public abstract class Connection extends Observable{
	
	public abstract void sendMessage( InteractionMessage msg);
	
	
}
