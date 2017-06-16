package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public abstract class Connection extends Observable implements Runnable{
	
	public abstract void sendMessage( InteractionMessage msg);
	
	
}
