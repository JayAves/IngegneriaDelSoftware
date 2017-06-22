package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class PlayerInfoMessage extends InteractionMessage {

	private String loginToken;
	private boolean timeExpired;
	
	
	
	public PlayerInfoMessage(String player) {
		super(player);
		timeExpired= false;
		
		
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void visit(VisitorMessages visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}
	
	public void setToken(String token) {
		loginToken= token;
	}
	
	public String getToken() {
		return loginToken;
	}
	
	public void setTimeExpired() {
		timeExpired=true;
	}
	public boolean getTimeExpired() {
		return timeExpired;
	}
}
