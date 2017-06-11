package it.polimi.ingsw.ps29.view.messages;

import java.io.Serializable;

import it.polimi.ingsw.ps29.view.View.VisitorServerMessages;

public abstract class InteractionMessage implements Serializable {
	String player;
	
	public InteractionMessage (String player) {
		this.player = player;
	}
	
	public String getName () {
		return player;
	}
	
	abstract public void visit (it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) ;
	
	abstract public void receive(it.polimi.ingsw.ps29.view.View.VisitorServerMessages visitor);

	

	
}
