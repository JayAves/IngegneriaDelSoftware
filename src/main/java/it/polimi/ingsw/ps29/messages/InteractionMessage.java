package it.polimi.ingsw.ps29.messages;

import java.io.Serializable;

/**
 * Used for communication between View and Controller.
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public abstract class InteractionMessage implements Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -7844810327964082967L;
	private String player;
	private boolean biDirectional;
	
	public InteractionMessage (String player, boolean bi) {
		this.player = player;
		this.biDirectional= bi;
		
	}
	
	public String getName () {
		return player;
	}
	
	public void setName (String name) {
		player = name;
	}
	
	public boolean getBi() {
		return biDirectional;
	}
	
	abstract public void visit (it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) ;
	
	abstract public void receive (it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages visitor);

	

	
}
