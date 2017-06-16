package it.polimi.ingsw.ps29.view.messages;

import java.io.Serializable;

public abstract class InteractionMessage implements Serializable {
	
	private String player;
	
	public InteractionMessage (String player) {
		this.player = player;
	}
	
	public String getName () {
		return player;
	}
	
	public void setName (String name) {
		player = name;
	}
	
	abstract public void visit (it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) ;
	
	abstract public void receive (it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages visitor);

	

	
}
