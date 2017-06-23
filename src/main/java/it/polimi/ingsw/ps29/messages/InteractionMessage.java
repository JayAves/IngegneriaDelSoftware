package it.polimi.ingsw.ps29.messages;

import java.io.Serializable;

public abstract class InteractionMessage implements Serializable {
	
	
	private static final long serialVersionUID = -7844810327964082967L;
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
