package it.polimi.ingsw.ps29.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Contains data about players. 
 * Is sent from client to server to queue in a new game or to reconnect to an existing one. 
 * Is sent from ClientThreads to Controller in server to alert about a player disconnecting.
 * Is sent from Controller to player's Views to notify some players' disconnection.
 * @author Pietro Grotti
 *
 */

public class PlayerInfoMessage extends InteractionMessage {

	private String loginToken;
	private boolean timeExpired;
	
	
	
	public PlayerInfoMessage(String player) {
		super(player,false);
		timeExpired= false;
		
		
	}
	
	// standard UID version
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
